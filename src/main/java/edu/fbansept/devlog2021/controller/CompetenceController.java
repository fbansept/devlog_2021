package edu.fbansept.devlog2021.controller;

import edu.fbansept.devlog2021.dao.CompetenceDao;
import edu.fbansept.devlog2021.model.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CompetenceController {

    CompetenceDao competenceDao;

    @Autowired
    CompetenceController(CompetenceDao competenceDao){
        this.competenceDao = competenceDao;
    }

    @GetMapping("/user/competence/{id}")
    public ResponseEntity<Competence> getCompetence(@PathVariable int id) {

        Optional<Competence> competence = competenceDao.findById(id);

        if(competence.isPresent()) {
            return ResponseEntity.ok(competence.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/user/competences")
    public ResponseEntity<List<Competence>> getCompetences () {

        return ResponseEntity.ok(competenceDao.findAll());
    }

    @PostMapping("/admin/competence")
    public ResponseEntity<String> addCompetence (@RequestBody Competence competence) {

        competence = competenceDao.saveAndFlush(competence);
        return ResponseEntity.created(
                URI.create("/user/competence/" + competence.getId())
        ).build();
    }

    @DeleteMapping("/admin/competence/{id}")
    public ResponseEntity<Integer> deleteCompetence (@PathVariable int id) {

        if(competenceDao.existsById(id)) {
            competenceDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}






