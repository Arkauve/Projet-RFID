package fr.catpass.services;

import fr.catpass.providers.HomeProviders;
import fr.catpass.providers.UserProviders;
import fr.catpass.resources.Home;
import fr.catpass.resources.User;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Jordan on 28/02/2017.
 */
@Path("/authentification")
public class AuthentificationImpl implements AuthentificationService {

    @Override
    public Response connexion(String email, String password) {
        try {
            User user = UserProviders.getUser(email,password);
            if(!user.getEmail().isEmpty())
                return Response.status(Response.Status.ACCEPTED).entity(user).build();
            else
                return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (SQLException sqle) {
            return Response.status(Response.Status.BAD_REQUEST).entity(sqle.getMessage()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response getAllHome(String email) {
        try {
            ArrayList<Home> homes = HomeProviders.getAllHomeUser(email);
            return Response.ok().entity(homes).build();
        } catch (SQLException sqle) {
            return Response.status(Response.Status.BAD_REQUEST).entity(sqle.getMessage()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }    }


}
