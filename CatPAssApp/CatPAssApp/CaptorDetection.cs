using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using CaptorNFC;
using System.Net;

namespace CatPAssApp
{
    class CaptorDetection
    {
        string idCaptor;

        public CaptorDetection(string id)
        {
            this.idCaptor = id;
        }

        public void run()
        {
            string UID;

            Console.WriteLine("----------------------------");
            Console.WriteLine("En attente d'une détecction");
            NFCReader.establishContext();
            NFCReader.SelectDevice();
            UID = NFCReader.waitConnectCard();
            Console.WriteLine("Détection d'une carte");

            Dictionary<string, string> result;
            string CodeResponse = "201";
            string Description = "Created";
            result = HttpRequest.sendGET("http://localhost:8080/catpass/access/" + UID + "?capteurId="+this.idCaptor);
            string ContentResponse = "";
            result.TryGetValue("Code", out CodeResponse);
            result.TryGetValue("Description", out Description);
            result.TryGetValue("ContentResponse", out ContentResponse);

            if (CodeResponse == HttpStatusCode.Unauthorized.GetHashCode().ToString())
                Console.WriteLine("Accès non autorisé");
            else if(CodeResponse == HttpStatusCode.Accepted.GetHashCode().ToString())
                Console.WriteLine("Accès autorisé, ouverture de la chatière");
            else
            {
                Console.WriteLine("ERREUR réponse inconnu : ");
                Console.WriteLine("Code de la réponse: "+ CodeResponse);
                Console.WriteLine("Message de la réponse: "+ ContentResponse);
            }
            Console.WriteLine("----------------------------");
            run();
        }
    }
}
