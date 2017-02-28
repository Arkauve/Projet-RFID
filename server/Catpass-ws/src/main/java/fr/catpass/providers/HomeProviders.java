package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import fr.catpass.resources.Home;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
public class HomeProviders {
    private static String INSERT_HOME = "INSERT INTO `catpass`.`maison`(`m_adresse`)VALUES('%1$s');";
    public static int createHome(Home home) throws SQLException{
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        return db.insert(String.format(INSERT_HOME, home.getAdress()));
    }
}
