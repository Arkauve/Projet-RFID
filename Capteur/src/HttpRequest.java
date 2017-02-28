import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by garni on 21/02/2017.
 */
public class HttpRequest {
    String message;


    public static Map<Integer, String> sendGET(String strUrl){
        Map response = new HashMap<Integer, String>();
        StringBuilder result = new StringBuilder();
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            response.put("code",conn.getResponseCode());
            response.put("response",result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            response.put(500,e.toString());
        }
        return response;
    }

    public static Map<Integer, String> sendPOST(String strUrl, Map<String, String> postParam) {
        HashMap<Integer, String> reponse = new HashMap<Integer, String>();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");

            String urlParameters="";
            int i=0;
            for (Map.Entry<String, String> entry : postParam.entrySet()) {
                if(i==0)
                    urlParameters+="?"+entry.getKey()+"="+entry.getValue();
                else
                    urlParameters+="&"+entry.getKey()+"="+entry.getValue();
                i++;
            }

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            reponse.put(responseCode, response.toString());
            //print result
            System.out.println(response.toString());
            return reponse;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return reponse;
    }
}
