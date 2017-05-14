package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import fr.catpass.resources.Animal;
import fr.catpass.resources.Historic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Jordan on 21/02/2017.
 */
public class AnimalProviders {
    private static String AUTORISATION = "SELECT * FROM catpass.animal where a_id = '%1$s' and a_fk_m_id = " +
            "(SELECT c_fk_m_id FROM catpass.capteur WHERE c_id = '%2$s')";

    private static String INSERT_ANIMAL = "INSERT INTO `catpass`.`animal`(`a_id`,`a_nom`,`a_age`,`a_fk_m_id`)" +
            "VALUES('%1$s','%2$s',%3$d,%4$d)";

    private static String ANIMAUX = "SELECT * FROM catpass.animal where a_fk_m_id = '%1$d'";

    public static boolean autorisation(String GUID,String capteurId) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ResultSet rs = db.query(String.format(AUTORISATION, GUID, capteurId));
        if(rs.first())return true;
        return false;
    }

    public static void insertAnimal(String GUID, String name, int years, int idHome) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_ANIMAL, GUID, name, years, idHome));
        HistoricProviders.insertNewHistoric(GUID);
    }

    public static ArrayList<Animal> getAllAnimaux(int idHome) throws SQLException {
        ArrayList<Animal> animaux1 = new ArrayList<>();
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ArrayList<Map<String,String>> resultats = db.findAllAsMap(String.format(ANIMAUX,  idHome));
        for (Map<String,String> animaux : resultats){
            animaux1.add(new Animal(animaux.get("a_id"),animaux.get("a_nom"),Integer.parseInt(animaux.get("a_age")),idHome));
        }
        return animaux1;
    }
}
