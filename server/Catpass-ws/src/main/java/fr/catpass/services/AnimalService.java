package fr.catpass.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 28/02/2017.
 */
public interface AnimalService {

    //{GUID:<GUID>,name:<name>, years:<years>, idHome:<idHome>}
    @POST
    @Path("/")
    Response insertAnimal(String animal);

    @GET
    @Path("/{idHome}")
    Response getAllAnimaux(@PathParam("idHome") int idHome);

    @GET
    @Path("/get/{GUID}")
    Response getAnimal(@PathParam("GUID") String GUID);


}
