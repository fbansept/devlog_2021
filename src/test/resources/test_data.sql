SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM utilisateur_competence WHERE 1 = 1;
DELETE FROM utilisateur_role WHERE 1 = 1;
DELETE FROM utilisateur WHERE 1 = 1;
DELETE FROM role WHERE 1 = 1;
DELETE FROM statut WHERE 1 = 1;
DELETE FROM competence WHERE 1 = 1;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE utilisateur AUTO_INCREMENT = 1;
ALTER TABLE role AUTO_INCREMENT = 1;
ALTER TABLE statut AUTO_INCREMENT = 1;
ALTER TABLE competence AUTO_INCREMENT = 1;

INSERT INTO `statut` (`id`, `denomination`)
VALUES (1, 'en ligne'), (2, 'occupé');

INSERT INTO `utilisateur` (`id`, `login`, `password`, `statut_id`)
VALUES (1, 'franck', '$2a$10$uz5dB8kKtjb37GwBLXUtEeALDOq4Ge/DHx2CmXWOf.hd1ave7Al0i', '1');

INSERT INTO `competence` (`id`, `nom`)
VALUES (1, 'JAVA'), (2, 'SPRING');

INSERT INTO `utilisateur_competence` (`utilisateur_id`, `competence_id`)
VALUES (1, 1), (1, 2);