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
            InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            NFCReader.establishContext();
            NFCReader.SelectDevice();
            Dictionary<string, string> result;
            String myMesage = "{" +
                            "email:" + TxMail.Text +
                            ",password:" + PxPass.Password.ToString() +
                            ",firstName:" + TxFirstname.Text +
                            ",lastName:" + TxName.Text +
                            ",adress:" + TxAdress.Text +
                            ",idCapteur:" + NFCReader.ListReaders()[0].ToString() +
                            "}";
            //result = HttpRequest.sendPOST("http://localhost:8080/catpass/configuration/",myMesage);
            string CodeResponse = "201";
            string Description = "Created";
            string ContentResponse = "";
            //result.TryGetValue("Code", out CodeResponse);
            //result.TryGetValue("Description", out Description);
            //result.TryGetValue("ContentResponse", out ContentResponse);
            if(CodeResponse == HttpStatusCode.Created.GetHashCode().ToString()) {
                MessageBox.Show("Enregistrement réussi", "Enregistrement", MessageBoxButton.OK, MessageBoxImage.Information);
                RegsiterWindow window = new RegsiterWindow();
                window.Show();
            }
            else
            {
                MessageBox.Show(Description, "ERREUR "+CodeResponse, MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
