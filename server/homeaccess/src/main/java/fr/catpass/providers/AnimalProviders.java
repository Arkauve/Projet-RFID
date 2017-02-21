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

    public static boolean autorisation(String GUID,String capteurId) throws Exception, SQLException {
        System.out.println("0");
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        System.out.println("1");
        ResultSet rs = db.query(String.format(AUTORISATION, GUID, capteurId));
        System.out.println("2");
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
}
