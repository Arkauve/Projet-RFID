package fr.catpass.services;

import fr.catpass.providers.CapteurProviders;
import fr.catpass.providers.HomeProviders;
import fr.catpass.providers.UserProviders;
import fr.catpass.resources.Capteur;
import fr.catpass.resources.Home;
import fr.catpass.resources.User;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.sql.SQLException;

/**
 * Created by Jordan on 21/02/2017.
 */
@Path("/configuration")
public class ConfigurationImpl implements ConfigurationService {
    @Override
    public Response createConfiguration(String config) {
        try {
            JSONObject obj = new JSONObject(config);

            UserProviders.createUser(new User(obj.getString("email"),obj.getString("password"),obj.getString("firstName"),obj.getString("lastName")));
            int idHome = HomeProviders.createHome(new Home(obj.getString("adress")));
            if(idHome>0){
                UserProviders.insertUserHome(obj.getString("email"), idHome);
                CapteurProviders.createCapteur(new Capteur(obj.getString("idCapteur"),idHome));
                return Response.status(Response.Status.CREATED).entity(idHome).build();
            }
            else
                return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException sqle) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(sqle.getMessage()).build();
        } catch (JSONException jsone) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsone.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(e.getMessage()).build();
        }
    }
}
