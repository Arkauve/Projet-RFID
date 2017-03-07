package fr.catpass.services;

import fr.catpass.providers.HistoricProviders;
import fr.catpass.resources.Historic;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Jordan on 28/02/2017.
 */
@Path("/historic")
public class HistoricImpl implements HistoricService {
    @Override
    public Response getHistoric(String GUID) {
        try {
            ArrayList<Historic> historics = HistoricProviders.getHistoricAnimal(GUID);
            return Response.ok().entity(historics).build();
        } catch (SQLException sqle) {
            return Response.status(Response.Status.BAD_REQUEST).entity(sqle.getMessage()).build();
        } catch (ParseException parsee) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(parsee.getMessage()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
