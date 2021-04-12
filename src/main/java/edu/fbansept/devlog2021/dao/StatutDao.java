package edu.fbansept.devlog2021.dao;

import edu.fbansept.devlog2021.model.Statut;
import edu.fbansept.devlog2021.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutDao extends JpaRepository<Statut, Integer> {

}