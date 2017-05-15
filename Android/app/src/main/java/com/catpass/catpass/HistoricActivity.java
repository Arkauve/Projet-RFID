package com.catpass.catpass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jordan on 14/05/2017.
 */

public class HistoricActivity extends AppCompatActivity {

    Animal animal;
    private ArrayList<Historic> historics;
    private HistoricAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_animal);
        animal = getIntent().getExtras().getParcelable("Animal");
        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblAge = (TextView) findViewById(R.id.lblAge);
        lblName.setText(animal.getName());
        lblAge.setText(Integer.toString(animal.getYears())+" ans");
        historics = new ArrayList<>();
        getHistorics();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getHistorics() {
        String URL = getResources().getString(R.string.URL)+"/historic/" + animal.getId();
        String params = "";
        Http requete = new Http();
        requete.execute("GET", URL, params);
        String result = "";
        try {
            result = requete.get();
            JSONArray jsonArray = new JSONArray(result.toString());
            adapter = new HistoricAdapter(getApplicationContext(), R.layout.row_historic, historics);
            ListView lisHistoric = (ListView) findViewById(R.id.listHistoric);

            for (int i = 0; i < jsonArray.length(); i++) {
                Historic historic = new Historic(jsonArray.getJSONObject(i).getInt("id"), new Date( jsonArray.getJSONObject(i).getLong("date")),
                        jsonArray.getJSONObject(i).getBoolean("out"));
                historics.add(historic);
            }
            lisHistoric.setAdapter(adapter);
        } catch (JSONException e) {
            Log.i("", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
