package fr.catpass.services;

import fr.catpass.providers.AnimalProviders;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Jordan on 07/02/2017.
 */
@Path("/access")
public class AccessImpl implements AccessService {

    @Override
    public Response getAutorisation(String GUID, String capteurId) {
        try {
            if(AnimalProviders.autorisation(GUID, capteurId))
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (SQLException sqle) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
