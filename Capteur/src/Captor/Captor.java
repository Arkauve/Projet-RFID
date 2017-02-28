package Captor;

import javax.smartcardio.*;
import java.util.List;
import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import java.util.List;

/**
 * Created by garni on 21/02/2017.
 */
public class Captor {
    private CardTerminal terminal;
    private Card card ;
    private CardChannel channel;
    private  byte[] myCmd = { (byte) 0xFF, (byte) 0xCA, 0x00, 0x00, 0x00};
    boolean connectionOpen = false;

    public void setTerminal(CardTerminal t){
        this.terminal = t;
    }

    public String getATR(){
        if(connectionOpen){
            byte []rep = null;
            ATR atr = card.getATR() ;
            rep = atr.getBytes() ;
            try {
                return byteArrayToHexString(rep);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }return null;
    }

    public CardTerminal getTerminal(){
        return terminal;
    }

    public static String getTerminalName() throws CardException {
        TerminalFactory factory = TerminalFactory.getDefault();
        CardTerminals cardterminals = factory.terminals();
        List<CardTerminal> terminals = cardterminals.list();
        return terminals.get(0).getName();
    }

    public int startDetection(){
        TerminalFactory factory = TerminalFactory.getDefault();
        CardTerminals cardterminals = factory.terminals();
        card = null;
        try {
            List<CardTerminal> terminals = cardterminals.list();
            if(terminal==null)
                terminal = cardterminals.getTerminal(terminals.get(0).getName());
            System.out.println("Détection sur le terminal : " + terminal.getName());
            boolean present = false;
            while (!present){
                terminal.waitForCardPresent(10000);
                if(terminal.isCardPresent()){
                    System.out.println("Captor.Card detected!");
                    present = true;
                }
            }
            card = terminal.connect("*");
            channel = card.getBasicChannel();
            connectionOpen = true;
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
//3B 8F 80 01 80 4F 0C A0 00 00 03 06 03 00 01 00 00 00 00 6A
    private void load(byte[] apdu){
        try {
            if (connectionOpen) {
                if (channel!=null) {
                    try {
                        System.out.println("Command: "+ byteArrayToHexString(apdu));
                        apdu = sendCommand(apdu, channel);
                        if (apdu!=null)
                            System.out.println("Response: "+ byteArrayToHexString(apdu));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            channel.close();
            card.disconnect(false);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public byte[] sendCommand(byte[] cmd, CardChannel channel) throws Exception {
        byte []rep = null;
        ResponseAPDU r = null;
        try {
            CommandAPDU apdu = new CommandAPDU(cmd);
            try {
                System.out.println("APDU Command: " + byteArrayToHexString(apdu.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            r = channel.transmit(apdu);
            rep = r.getBytes();
            try {
                System.out.println("APDU Response: " + byteArrayToHexString(rep));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rep;
        } catch (CardException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String byteArrayToHexString(byte[] bArray) throws Exception
    {
        if (bArray.length==0)
        {
            throw new Exception();
        }
        StringBuilder sb = new StringBuilder(bArray.length * 2);
        for (byte b : bArray)
        {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}
