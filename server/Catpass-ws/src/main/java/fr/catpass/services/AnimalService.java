package fr.catpass.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 28/02/2017.
 */
public interface AnimalService {

    //{GUID:<GUID>,name:<name>, years:<years>, idHome:<idHome>}
    @POST
    @Path("/")
    Response insertAnimal(String animal);
}
