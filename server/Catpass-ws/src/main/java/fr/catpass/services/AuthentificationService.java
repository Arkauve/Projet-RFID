package fr.catpass.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 28/02/2017.
 */
public interface AuthentificationService {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response connexion(@QueryParam("email") String email, @QueryParam("password") String password);

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllHome(@PathParam("email") String email);
}
