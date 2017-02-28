package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Jordan on 28/02/2017.
 */
public class HistoricProviders {
    private static String INSERT_HISTORIC = "INSERT INTO `catpass`.`historique`(`h_horodatage`,`h_sortie`,`h_fk_a_id`)" +
            "VALUES('%1$s',%2$b,'%3$s')";
    private static String ANIMAL_IS_OUT = "SELECT `h_sortie` FROM `catpass`.`historique` WHERE `h_id` = (SELECT MAX(`h_id`) FROM `catpass`.`historique` WHERE `h_fk_a_id` = '%1$s')";

    public static void insertHistoric(String GUID) throws SQLException{
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        Date date = new Date();
        db.insert(String.format(INSERT_HISTORIC, new java.sql.Date(date.getTime()), getAnimalIsOut(GUID), GUID));
    }

    public static boolean getAnimalIsOut(String GUID) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ResultSet rs = db.query(String.format(ANIMAL_IS_OUT, GUID));
        if(rs.first()) {
            System.out.println(rs.getBoolean("h_sortie"));
            return !rs.getBoolean("h_sortie");
        }
        else
            return false;
    }
}
