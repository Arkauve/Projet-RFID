package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
public class AnimalProviders {
    private static String AUTORISATION = "SELECT count(*) as nbId FROM catpass.animal where a_id = %1$s and a_fk_m_id = " +
            "(SELECT c_fk_m_id FROM catpass.capteur WHERE c_id = '%2$s')";

    private static String INSERT_ANIMAL = "INSERT INTO `catpass`.`animal`(`a_id`,`a_nom`,`a_age`,`a_fk_m_id`)" +
            "VALUES('%1$s','%2$s',%3$d,%4$d)";

    public static boolean autorisation(String GUID,String capteurId) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ResultSet rs = db.query(String.format(AUTORISATION, GUID, capteurId));
        if(rs.first()){
            int nombreLigne = rs.getInt("nbId");
            if (nombreLigne > 0)
                return true;
            else
                return false;
        }else{
            return false;
        }
    }

    public static void insertAnimal(String GUID, String name, int years, int idHome) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_ANIMAL, GUID, name, years, idHome));
    }
}
