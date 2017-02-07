INSERT INTO `catpass`.`utilisateur` (`u_mail`, `u_nom`, `u_prenom`, `u_password`) VALUES ('jordan.dejoux@outlook.fr', 'Dejoux', 'jordan', 'azerty');
INSERT INTO `catpass`.`maison` (`m_adresse`) VALUES ('1 rue test');
INSERT INTO `catpass`.`animal` (`a_id`, `a_nom`, `a_age`, `a_fk_m_id`) VALUES ('1234567989', 'toto', '5', '1');
INSERT INTO `catpass`.`utilisateur-maison` (`um_fk_u_mail`, `um_fk_m_id`) VALUES ('jordan.dejoux@outlook.fr', '1');

