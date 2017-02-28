package fr.catpass.services;

import fr.catpass.providers.AnimalProviders;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Jordan on 28/02/2017.
 */
@Path("/animal")
public class AnimalImpl implements AnimalService {
    @Override
    public Response insertAnimal(String animal) {
        try {
            JSONObject obj = new JSONObject(animal);
            AnimalProviders.insertAnimal(obj.getString("GUID"),obj.getString("name"), obj.getInt("years"), obj.getInt("idHome"));
            return Response.status(Response.Status.CREATED).build();
        }catch(JSONException jsone){
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(jsone.getMessage()).build();
        }catch(SQLException sqle){
            return Response.status(Response.Status.BAD_REQUEST).entity(sqle.getMessage()).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
