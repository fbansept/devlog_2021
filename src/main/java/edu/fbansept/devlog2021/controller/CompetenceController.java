package edu.fbansept.devlog2021.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.devlog2021.dao.CompetenceDao;
import edu.fbansept.devlog2021.model.Competence;
import edu.fbansept.devlog2021.model.Utilisateur;
import edu.fbansept.devlog2021.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompetenceController {

    CompetenceDao competenceDao;

    @Autowired
    CompetenceController(CompetenceDao competenceDao){
        this.competenceDao = competenceDao;
    }


    @GetMapping("/admin/competence/{id}")
    @JsonView(CustomJsonView.VueCompetence.class)
    public Competence getCompetence(@PathVariable int id) {
        return competenceDao.findById(id).orElse(null);
    }

    @GetMapping("/admin/competences")
    @JsonView(CustomJsonView.VueCompetence.class)
    public List<Competence> getCompetences () {

        return competenceDao.findAll();

    }

    @PostMapping("/admin/competence")
    public boolean addCompetence (@RequestBody Competence competence) {

        competenceDao.save(competence);

        return true;
    }

    @DeleteMapping("/admin/competence/{id}")
    public boolean deleteCompetence (@PathVariable int id) {

        competenceDao.deleteById(id);

        return true;
    }


}






