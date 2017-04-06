using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

using CaptorNFC;

namespace CatPAssApp
{
    /// <summary>
    /// Logique d'interaction pour AddAnimalWindow.xaml
    /// </summary>
    public partial class AddAnimalWindow : Window
    {
        int idHome;

        public AddAnimalWindow(int idHome)
        {
            this.idHome = idHome;
            InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            string UID;

            NFCReader.establishContext();
            NFCReader.SelectDevice();
            UID = NFCReader.waitConnectCard();

            String myMesage = "{" +
                            "GUID:" + UID +
                            ",name:" + TxName.Text +
                            ",years:" + TxAge.Text +
                            ",idHome:" + this.idHome +
                            "}";
            Dictionary<string, string> result;
            string CodeResponse = "201";
            string Description = "Created";
            result = HttpRequest.sendPOST("http://localhost:8080/catpass/animal/", myMesage);
            string ContentResponse = "";
            result.TryGetValue("Code", out CodeResponse);
            result.TryGetValue("Description", out Description);
            result.TryGetValue("ContentResponse", out ContentResponse);
            int code = int.Parse(CodeResponse);
            if(code >= 400)
                MessageBox.Show("Erreur lors de l'enregistrement", "Enregistrement", MessageBoxButton.OK, MessageBoxImage.Error);
            else
                MessageBox.Show("Enregistrement réussi", "Enregistrement", MessageBoxButton.OK, MessageBoxImage.Information);
            this.Close();

        }
    }
}
