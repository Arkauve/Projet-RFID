package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
public class AnimalProviders {
    private static String AUTORISATION = "SELECT count(*) as nbId FROM catpass.animal where a_id = %1$s";

    public static boolean autorisation(String GUID) throws Exception {
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            ResultSet rs = db.query(String.format(AUTORISATION, GUID));
            if(rs.first()){
                int nombreLigne = rs.getInt("nbId");
                if (nombreLigne > 0)
                    return true;
                else
                    return false;
            }else{
                return false;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
}
