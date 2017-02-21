package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;

import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
public class AnimalProviders {
    private static String AUTORISATION = "SELECT count(*) as nbId FROM catpass.animal where a_id = %1$d";

    public static boolean autorisation(String GUID) throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            int nombreLigne = db.query(String.format(AUTORISATION, GUID)).getInt("nbId");
            if(nombreLigne > 0)
                return true;
            else
                return false;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
}
