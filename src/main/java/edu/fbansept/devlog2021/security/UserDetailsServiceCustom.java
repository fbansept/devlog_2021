package edu.fbansept.devlog2021.security;

import edu.fbansept.devlog2021.dao.UtilisateurDao;
import edu.fbansept.devlog2021.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String loginSaisi) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .findByLogin(loginSaisi)
                .orElseThrow(() -> new UsernameNotFoundException(loginSaisi + " inconnu"));

        return new UserDetailsCustom(utilisateur);

    }
}
