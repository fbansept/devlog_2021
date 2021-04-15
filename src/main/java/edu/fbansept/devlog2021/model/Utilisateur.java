package edu.fbansept.devlog2021.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.devlog2021.view.CustomJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CustomJsonView.VueUtilisateur.class, CustomJsonView.VueCompetence.class})
    private int id;

    @Column(nullable = false, length = 50)
    @JsonView({CustomJsonView.VueUtilisateur.class, CustomJsonView.VueCompetence.class})
    private String login;

    private String password;

    @ManyToOne
    @JsonView({CustomJsonView.VueUtilisateur.class})
    //@JoinColumn(name = "id_statut")
    private Statut statut;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonView({CustomJsonView.VueUtilisateur.class})
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> listeRole = new ArrayList<>();

    @ManyToMany
    @JsonView({CustomJsonView.VueUtilisateur.class})
    @JoinTable(
            name = "utilisateur_competence",
            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id", referencedColumnName = "id"))
    private List<Competence> listeCompetence = new ArrayList<>();

    public List<Role> getListeRole() {
        return listeRole;
    }

    public void setListeRole(List<Role> listeRole) {
        this.listeRole = listeRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<Competence> getListeCompetence() {
        return listeCompetence;
    }

    public void setListeCompetence(List<Competence> listeCompetence) {
        this.listeCompetence = listeCompetence;
    }
}
