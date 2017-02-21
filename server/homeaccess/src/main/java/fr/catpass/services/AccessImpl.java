package fr.catpass.services;

import fr.catpass.providers.AnimalProviders;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 07/02/2017.
 */
@Path("/access")
public class AccessImpl implements AccessService {

    @Override
    public Response getAutorisation(String GUID) {
        try {
            if(AnimalProviders.autorisation(GUID))
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
