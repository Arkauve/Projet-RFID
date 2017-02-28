package fr.catpass.providers;

import fr.catpass.database.DataBaseAccess;
import fr.catpass.database.DataBaseAccessImpl;
import fr.catpass.resources.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
public class UserProviders {

    private static String INSERT_USER = "INSERT INTO `catpass`.`utilisateur`(`u_mail`,`u_nom`,`u_prenom`,`u_password`)" +
            "VALUES('%1$s','%2$s','%3$s','%4$s')";
    private static String INSERT_USER_HOME = "INSERT INTO `catpass`.`utilisateur-maison`(`um_fk_u_mail`,`um_fk_m_id`)" +
            "VALUES('%1$s','%2$d')";
    public static void createUser(User user)throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_USER, user.getEmail(), user.getFirstName(), user.getLastName(),user.getPassword()));
    }

    public static void insertUserHome(String email, int idHome) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_USER_HOME,email,idHome));
    }
}
