package fr.catpass.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jordan on 21/02/2017.
 */
public interface ConfigurationService {

    @POST
    @Path("/")
    Response createConfiguration(String config);

    @POST
    @Path("/test")
    Response postTest(String email);
}