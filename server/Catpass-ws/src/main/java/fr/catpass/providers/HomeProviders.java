package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import fr.catpass.resources.Home;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Jordan on 21/02/2017.
 */
public class HomeProviders {
    private static String INSERT_HOME = "INSERT INTO `catpass`.`maison`(`m_adresse`)VALUES('%1$s');";
    private static String HOMESUSER = "SELECT * FROM `catpass`.`utilisateur-maison` WHERE um_fk_u_mail = '%1$s';";
    private static String HOMES = "SELECT * FROM `catpass`.`maison` WHERE m_id = %1$d;";
    public static int createHome(Home home) throws SQLException{
            DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
            return db.insert(String.format(INSERT_HOME, home.getAdress()));
    }

    public static ArrayList<Home> getAllHomeUser(String email) throws SQLException {
        ArrayList<Home> homes1 = new ArrayList<>();
        ArrayList<Integer> idHomes = new ArrayList<>();
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ResultSet resultSet = db.query(String.format(HOMESUSER,  email));

        while(resultSet.next())
            idHomes.add(resultSet.getInt("um_fk_m_id"));

        for (int idHome:idHomes) {
            resultSet = db.query(String.format(HOMES,  idHome));
            if(resultSet.first()){
                homes1.add(new Home(resultSet.getInt("m_id"),resultSet.getString("m_adresse")));
            }
        }
        return homes1;
    }
}
