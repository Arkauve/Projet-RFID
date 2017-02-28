package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import fr.catpass.resources.Capteur;
import fr.catpass.resources.User;

import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
public class CapteurProviders {
    private static String INSERT_CAPTEUR = "INSERT INTO `catpass`.`capteur`(`c_id`,`c_fk_m_id`)VALUES('%1$s','%2$d');\n";
    public static void createCapteur(Capteur capteur) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_CAPTEUR, capteur.getId(),capteur.getIdMaison()));
    }
}
