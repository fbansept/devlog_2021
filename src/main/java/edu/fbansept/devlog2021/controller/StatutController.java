package edu.fbansept.devlog2021.controller;

import edu.fbansept.devlog2021.dao.StatutDao;
import edu.fbansept.devlog2021.model.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StatutController {

    StatutDao statutDao;

    @Autowired
    StatutController(StatutDao statutDao){
        this.statutDao = statutDao;
    }

    @GetMapping("/admin/statut/{id}")
    public Statut getStatut(@PathVariable int id) {
        return statutDao.findById(id).orElse(null);
    }

    @GetMapping("/admin/statuts")
    public List<Statut> getStatuts () {

        List<Statut> listeStatut = statutDao.findAll();

        return listeStatut;
    }

    @PostMapping("/admin/statut")
    public boolean addStatut (@RequestBody Statut statut) {

        statutDao.save(statut);

        return true;
    }

    @DeleteMapping("/admin/statut/{id}")
    public boolean deleteStatut (@PathVariable int id) {

        statutDao.deleteById(id);

        return true;
    }


}






