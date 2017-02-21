package fr.catpass.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 07/02/2017.
 */
public interface AccessService {

    @GET
    @Path("/{GUID}")
    Response getAutorisation(@PathParam("GUID") String GUID);
}
