package fr.catpass.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 28/02/2017.
 */
public interface AuthentificationService {
    @GET
    @Path("/")
    Response connexion(@QueryParam("email") String email, @QueryParam("password") String password);
}
