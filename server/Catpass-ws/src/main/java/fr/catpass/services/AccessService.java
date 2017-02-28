package fr.catpass.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 07/02/2017.
 */
public interface AccessService {

    @GET
    @Path("/{GUID}")
    Response getAutorisation(@PathParam("GUID") String GUID, @QueryParam("capteurId") String capteurId);
}
