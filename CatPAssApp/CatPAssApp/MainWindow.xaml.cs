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
using System.Windows.Navigation;
using System.Windows.Shapes;

using CaptorNFC;
using System.Net;

namespace CatPAssApp
{
    /// <summary>
    /// Logique d'interaction pour MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            string fileName = "config.txt";
            string pathString = System.IO.Path.Combine(System.AppDomain.CurrentDomain.BaseDirectory, fileName);
            
            if (System.IO.File.Exists(pathString))
            {
                NFCReader.establishContext();
                NFCReader.SelectDevice();
                CaptorDetection captor = new CaptorDetection(NFCReader.ListReaders()[0].ToString());
                captor.run();
            }
            else
                InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            NFCReader.establishContext();
            NFCReader.SelectDevice();
            string idCaptor = NFCReader.ListReaders()[0].ToString();
            String myMesage = "{" +
                            "email:" + TxMail.Text +
                            ",password:" + PxPass.Password.ToString() +
                            ",firstName:" + TxFirstname.Text +
                            ",lastName:" + TxName.Text +
                            ",adress:" + TxAdress.Text +
                            ",idCapteur:" + idCaptor +
                            "}";
            string CodeResponse = "201";
            string Description = "Created";
            int idHome = 4;
            Dictionary<string, string> result;
            result = HttpRequest.sendPOST("http://localhost:8080/catpass/configuration/", myMesage);
            string ContentResponse = "";
            result.TryGetValue("Code", out CodeResponse);
            result.TryGetValue("Description", out Description);
            result.TryGetValue("ContentResponse", out ContentResponse);
            idHome = int.Parse(ContentResponse);
            if (CodeResponse == HttpStatusCode.Created.GetHashCode().ToString()) {
                MessageBox.Show("Enregistrement réussi", "Enregistrement", MessageBoxButton.OK, MessageBoxImage.Information);
                RegsiterWindow window = new RegsiterWindow(idHome, idCaptor);
                window.Show();
                this.Close();
            }
            else
            {
                MessageBox.Show(Description, "ERREUR "+CodeResponse, MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
