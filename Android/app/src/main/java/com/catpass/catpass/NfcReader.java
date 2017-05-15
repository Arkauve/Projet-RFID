package com.catpass.catpass;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class NfcReader extends AppCompatActivity {

    IntentFilter[] intentFiltersArray = null;
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    String GUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_reader);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            try {
                resolveIntent(intent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void resolveIntent(Intent intent) throws UnsupportedEncodingException {
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(action));{
            Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] id = tag.getId();
            GUID = byteArrayToHexString(id);

            Log.e("GUID : ",GUID);

            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] messages = new NdefMessage[rawMsgs.length];
            if(rawMsgs != null){
                for(int i=0;i<rawMsgs.length;i++){
                    messages[i] = (NdefMessage) rawMsgs[i];
                    NdefRecord record = messages[i].getRecords()[i];
                    byte[] id_ = record.getId();
                    short tnf = record.getTnf();
                    byte[] type = record.getType();
                    Log.i("id", String.valueOf(id_));
                    Log.i("tnf", String.valueOf(tnf));
                    Log.i("type", String.valueOf(type));
                    try {
                        String message = getTextData(record.getPayload());
                        Log.e("message "+i,message);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            getAnimal(GUID);
        }
    }

    private String getTextData(byte[] payload) throws UnsupportedEncodingException {
        String texteCode = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
        int langageCodeTaille = payload[0] & 0077;
        return new String(payload, langageCodeTaille + 1, payload.length - langageCodeTaille - 1,texteCode);
    }

    public static String byteArrayToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length * 2);
        for (byte b : bArray) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

    public void onResume() {

        super.onResume();

        NfcManager manager = (NfcManager) getBaseContext().getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, null);
        } else {
            Toast.makeText(NfcReader.this, "NFC non disponible", Toast.LENGTH_LONG).show();
        }

    }

    private void getAnimal(String GUID){

        String strUID = GUID.replaceAll(" ", "");
        strUID = strUID.substring(0,8);

        String URL = getResources().getString(R.string.URL)+"/animal/get/" + strUID;
        String params = "";
        Http requete = new Http();
        requete.execute("GET", URL, params);
        String result = "";
        try {
            result = requete.get();
            JSONObject jsonObject = new JSONObject(result);
            Animal animal = new Animal(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getInt("years"),jsonObject.getInt("idHome"));
            Intent intentValidate = new Intent(NfcReader.this, ProfilAnimalActivity.class);
            intentValidate.putExtra("Animal", animal);
            startActivity(intentValidate);

        } catch (JSONException e) {
            Log.i("",e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
