package fr.catpass.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 28/02/2017.
 */
public interface HistoricService {

    @GET
    @Path("/{GUID}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getHistoric(@PathParam("GUID") String GUID);
}
