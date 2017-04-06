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
    public partial class RegsiterWindow : Window
    {

        int idHome;
        string idCaptor;

        public RegsiterWindow(int idHome, string idCaptor)
        {
            this.idHome = idHome;
            this.idCaptor = idCaptor;
            InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            AddAnimalWindow window = new AddAnimalWindow(idHome);
            window.Show();
        }

        private void buttonFinish_Click(object sender, RoutedEventArgs e)
        {
            string fileName = "config.txt";

            // This example uses a random string for the name, but you also can specify
            // a particular name.
            //string fileName = "MyNewFile.txt";

            // Use Combine again to add the file name to the path.
            string pathString = System.IO.Path.Combine(System.AppDomain.CurrentDomain.BaseDirectory, fileName);

            // Verify the path that you have constructed.
            Console.WriteLine("Path to my file: {0}\n", pathString);

            // Check that the file doesn't already exist. If it doesn't exist, create
            // the file and write integers 0 - 99 to it.
            // DANGER: System.IO.File.Create will overwrite the file if it already exists.
            // This could happen even with random file names, although it is unlikely.
            if (!System.IO.File.Exists(pathString))
            {
                using (System.IO.FileStream fs = System.IO.File.Create(pathString))
                {
                    for (byte i = 0; i < 100; i++)
                    {
                        fs.WriteByte(i);
                    }
                }
            }

            NFCReader.establishContext();
            NFCReader.SelectDevice();
            CaptorDetection captor = new CaptorDetection(this.idCaptor);
            captor.run();


        }
    }
}
