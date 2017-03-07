package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import fr.catpass.resources.Historic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by Jordan on 28/02/2017.
 */
public class HistoricProviders {
    private static String INSERT_HISTORIC = "INSERT INTO `catpass`.`historique`(`h_horodatage`,`h_sortie`,`h_fk_a_id`)" +
            "VALUES('%1$s',%2$b,'%3$s')";
    private static String ANIMAL_IS_OUT = "SELECT `h_sortie` FROM `catpass`.`historique` WHERE `h_id` = (SELECT MAX(`h_id`) FROM `catpass`.`historique` WHERE `h_fk_a_id` = '%1$s')";
    private static String HISTORIC_ANIMAL = "SELECT * FROM `catpass`.`historique` WHERE `h_fk_a_id` = '%1$s'";
    public static void insertHistoric(String GUID) throws SQLException{
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        Date date = new Date();
        db.insert(String.format(INSERT_HISTORIC, new java.sql.Timestamp(date.getTime()), getAnimalIsOut(GUID), GUID));
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

    public static void insertNewHistoric(String GUID) throws SQLException{
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        Date date = new Date();
        db.insert(String.format(INSERT_HISTORIC, new java.sql.Timestamp(date.getTime()), false, GUID));
    }

    public static ArrayList<Historic> getHistoricAnimal(String GUID) throws SQLException, ParseException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ArrayList<Map<String, String>> resultats = db.findAllAsMap(String.format(HISTORIC_ANIMAL,GUID));
        ArrayList<Historic> historics = new ArrayList<Historic>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String,String> historic : resultats){
            boolean isOut = historic.get("h_sortie").equals("1");
            historics.add(new Historic(Integer.parseInt(historic.get("h_id")),formatter.parse(historic.get("h_horodatage")),isOut,historic.get("h_fk_a_id")));
        }
        return historics;
    }
}
