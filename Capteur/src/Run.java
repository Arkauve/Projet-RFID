import Captor.Captor;
import javax.smartcardio.ATR;
import javax.swing.*;
import java.io.*;

/**
 * Created by garni on 21/02/2017.
 */
public class Run {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String fileName = "licence.txt";

        File myFile = new File(fileName);
        if(!myFile.exists()){
            Configuration myConfig = new Configuration();
//            try	{
//                FileWriter fw = new FileWriter(fileName,true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.close();
//            }
//            catch (IOException e){
//                System.out.println("exception"+e);
//            }
        }
    }
}
