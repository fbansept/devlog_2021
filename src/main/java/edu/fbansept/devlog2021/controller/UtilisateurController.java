package edu.fbansept.devlog2021.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.devlog2021.dao.UtilisateurDao;
import edu.fbansept.devlog2021.model.Competence;
import edu.fbansept.devlog2021.model.Utilisateur;
import edu.fbansept.devlog2021.security.JwtUtil;
import edu.fbansept.devlog2021.security.UserDetailsServiceCustom;
import edu.fbansept.devlog2021.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    UtilisateurDao utilisateurDao;
    JwtUtil jwtUtil;
    AuthenticationManager authenticationManager;
    UserDetailsServiceCustom userDetailsServiceCustom;

    @Autowired
    UtilisateurController(
            UtilisateurDao utilisateurDao,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailsServiceCustom userDetailsServiceCustom
    ){
        this.utilisateurDao = utilisateurDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
    }

    @PostMapping("/authentification")
    public String authentification(@RequestBody Utilisateur utilisateur) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        utilisateur.getLogin(), utilisateur.getPassword()
                )
        );

        UserDetails userDetails = this.userDetailsServiceCustom.loadUserByUsername(utilisateur.getLogin());

        return jwtUtil.generateToken(userDetails);
    }


    @JsonView(CustomJsonView.VueUtilisateur.class)
    @GetMapping("/user/utilisateur/{id}")
    public Utilisateur getUtilisateur(@PathVariable int id) {
        Utilisateur utilisateur = utilisateurDao.findById(id).orElse(null);
        return utilisateurDao.findById(id).orElse(null);
    }

    @JsonView(CustomJsonView.VueUtilisateur.class)
    @GetMapping("/user/utilisateurs")
    public List<Utilisateur> getUtilisateurs () {
        return utilisateurDao.findAll();
    }

    @PostMapping("/admin/utilisateur")
    public boolean addUtilisateur (@RequestBody Utilisateur utilisateur) {

        utilisateurDao.save(utilisateur);

        return true;
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    public boolean deleteUtilisateur (@PathVariable int id) {

        utilisateurDao.deleteById(id);

        return true;
    }


}






