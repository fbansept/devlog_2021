package edu.fbansept.devlog2021.controller;

import edu.fbansept.devlog2021.dao.StatutDao;
import edu.fbansept.devlog2021.model.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class StatutController {

    StatutDao statutDao;

    @Autowired
    StatutController(StatutDao statutDao){
        this.statutDao = statutDao;
    }

    @GetMapping("/user/statut/{id}")
    public ResponseEntity<Statut> getStatut(@PathVariable int id) {

        Optional<Statut> statut = statutDao.findById(id);

        if(statut.isPresent()) {
            return ResponseEntity.ok(statut.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/user/statuts")
    public ResponseEntity<List<Statut>> getStatuts () {

        return ResponseEntity.ok(statutDao.findAll());
    }

    @PostMapping("/admin/statut")
    public ResponseEntity<String> addStatut (@RequestBody Statut statut) {

        statut = statutDao.saveAndFlush(statut);
        return ResponseEntity.created(
                URI.create("/user/statut/" + statut.getId())
        ).build();
    }

    @DeleteMapping("/admin/statut/{id}")
    public ResponseEntity<Integer> deleteStatut (@PathVariable int id) {

        if(statutDao.existsById(id)) {
            statutDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}






