package fr.catpass.services;

import fr.catpass.providers.UserProviders;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Jordan on 28/02/2017.
 */
@Path("/authentification")
public class AuthentificationImpl implements AuthentificationService {

    @Override
    public Response connexion(String email, String password) {
        try {
            if(UserProviders.getUser(email,password))
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (SQLException sqle) {
            return Response.status(Response.Status.BAD_REQUEST).entity(sqle.getMessage()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
