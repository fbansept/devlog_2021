package edu.fbansept.devlog2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Devlog2021Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Devlog2021Application.class, args);
		} catch (Exception e) {
			System.out.println("erreur");
		}

	}

}
