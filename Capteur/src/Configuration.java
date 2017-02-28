import Captor.Captor;

import javax.smartcardio.CardException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by garni on 28/02/2017.
 */
public class Configuration extends JFrame{
    private JPanel panel1;
    private JTextField TxMail;
    private JTextField TxAdress;
    private JTextField TxName;
    private JTextField TxFirstname;
    private JPasswordField TxPwd;
    private JButton validerButton;

    public Configuration(){
        super();

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myMesage = null;
                try {
                    myMesage = "{"+
                            "email:"+TxMail.getText()+
                            ",password:"+ String.valueOf(TxPwd.getPassword())+
                            ",firstName:"+ TxFirstname.getText()+
                            ",lastName:"+ TxName.getText()+
                            ",adress:"+ TxAdress.getText()+
                            ",idCapteur:" + Captor.getTerminalName() +
                            "}";

                    JOptionPane.showMessageDialog(null,myMesage);
                } catch (CardException e1) {
                    JOptionPane.showMessageDialog(null,"Branchez le capteur");
                }
            }
        });
    }



}
