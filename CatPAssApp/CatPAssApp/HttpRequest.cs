using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace CatPAssApp
{
    public class HttpRequest {

        public static int sendGET(string url)
        {
            return 0;
        }

        public static Dictionary<string, string> sendPOST(string url,string postData)
        {
            Dictionary<string, string> result = new Dictionary<string, string>();
            try
            {
                // Create a request using a URL that can receive a post. 
                WebRequest request = WebRequest.Create(url);
                // Set the Method property of the request to POST.
                request.Method = "POST";
                // Create POST data and convert it to a byte array.
                byte[] byteArray = Encoding.UTF8.GetBytes(postData);
                // Set the ContentType property of the WebRequest.
                request.ContentType = "application/x-www-form-urlencoded";
                // Set the ContentLength property of the WebRequest.
                request.ContentLength = byteArray.Length;
                // Get the request stream.
                Stream dataStream = request.GetRequestStream();
                // Write the data to the request stream.
                dataStream.Write(byteArray, 0, byteArray.Length);
                // Close the Stream object.
                dataStream.Close();
                // Get the response.
                WebResponse response = request.GetResponse();
                // Display the status.
                result.Add("Code", ((HttpWebResponse)response).StatusCode.GetHashCode().ToString());
                result.Add("Description", ((HttpWebResponse)response).StatusDescription);
                // Get the stream containing content returned by the server.
                dataStream = response.GetResponseStream();
                // Open the stream using a StreamReader for easy access.
                StreamReader reader = new StreamReader(dataStream);
                // Read the content.
                string responseFromServer = reader.ReadToEnd();
                // Display the content.
                result.Add("ContentResponse", responseFromServer);
                // Clean up the streams.
                reader.Close();
                dataStream.Close();
                response.Close();
            }catch(Exception e)
            {
                result.Add("Code", HttpStatusCode.InternalServerError.GetHashCode().ToString());
                result.Add("Description", e.Message);
                result.Add("ContentResponse", "");
            }
            
            return result;
        }
    }

}
