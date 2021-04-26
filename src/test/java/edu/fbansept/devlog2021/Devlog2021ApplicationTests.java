package edu.fbansept.devlog2021;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Sql({"/test_data.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Devlog2021ApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void initialisationMvc() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@WithMockUser(username="franck", roles = {"UTILISATEUR"})
	@Test
	void accesALaMethodePourRecupererLesUtilisateur_doitRetournerUnStatutOk() throws Exception {
		mvc.perform(get("/user/utilisateurs"))
				.andExpect(status().isOk());
	}

	@WithMockUser(username="franck", roles = {"UTILISATEUR"})
	@Test
	void accesALaMethodePourSupprimerUnUtilisateurAvecUnRoleUtilisateur_doitRetournerUnStatutForbidden() throws Exception {
		mvc.perform(delete("/admin/utilisateur/1"))
				.andExpect(status().isForbidden());
	}

	@WithMockUser(username="franck", roles = {"UTILISATEUR"})
	@Test
	void accesALaMethodeRecupererUtilisateurAvecId1_doitRetournerUnUtilisateurEnJSON() throws Exception {
		mvc.perform(get("/user/utilisateur/{$i}",1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.id").value(1))
		;
	}

	@WithMockUser(username="franck", roles = {"UTILISATEUR"})
	@Test
	void accesALaMethodeRecupererListeUtilisateur_doitRetournerUneListeUtilisateurAvecLoginFranck() throws Exception {
		mvc.perform(get("/user/utilisateurs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].login").value("franck"));
	}

	@WithMockUser(username="franck", roles = {"UTILISATEUR"})
	@Test
	void accesALaMethodeRecupererListeUtilisateur_doitUtilisateur1AvoirCompetenceJAVA() throws Exception {
		mvc.perform(get("/user/utilisateurs"))
				.andExpect(status().isOk())
				//On test le nom de la deuxième compétence du premier utilisateur de la liste
				.andExpect(jsonPath("$[0].listeCompetence[1].nom").value("SPRING"));
	}
}

