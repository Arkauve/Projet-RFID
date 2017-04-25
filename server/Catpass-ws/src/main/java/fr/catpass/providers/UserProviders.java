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
    private static String CONNEXION = "SELECT * FROM `catpass`.`utilisateur` WHERE `u_mail`='%1$s' AND `u_password`='%2$s'";

    public static void createUser(User user)throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_USER, user.getEmail(), user.getFirstName(), user.getLastName(),user.getPassword()));
    }

    public static void insertUserHome(String email, int idHome) throws SQLException {
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        db.insert(String.format(INSERT_USER_HOME,email,idHome));
    }

    public static User getUser(String email, String password)throws SQLException{
        User user = null;
        DataBaseAccess db = DataBaseAccessImpl.getDbConnection();
        ResultSet rs = db.query(String.format(CONNEXION,email,password));
        if(rs.first()) {
            user = new User(rs.getString("u_mail"), rs.getString("u_prenom"), rs.getString("u_nom"));
        }
        return user;
    }
}
