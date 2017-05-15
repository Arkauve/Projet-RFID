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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Jordan on 14/05/2017.
 */

public class AnimalActivity extends AppCompatActivity {

    Home home;
    private ArrayList<Animal> animals;
    private AnimalAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        home = getIntent().getExtras().getParcelable("Home");
        animals = new ArrayList<>();
        getAllAnimal();
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

    public void getAllAnimal() {
        String URL = getResources().getString(R.string.URL)+"/animal/" + home.getId();
        String params = "";
        Http requete = new Http();
        requete.execute("GET", URL, params);
        String result = "";
        try {
            result = requete.get();
            JSONArray jsonArray = new JSONArray(result.toString());
            adapter = new AnimalAdapter(getApplicationContext(), R.layout.row_animal, animals);
            ListView listAnimal = (ListView) findViewById(R.id.listAnimal);

            for (int i = 0; i < jsonArray.length(); i++) {
                final Animal animal = new Animal(jsonArray.getJSONObject(i).getString("id"), jsonArray.getJSONObject(i).getString("name"),
                        jsonArray.getJSONObject(i).getInt("years"), jsonArray.getJSONObject(i).getInt("idHome"));
                animals.add(animal);
            }
            listAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    Intent intentValidate = new Intent(AnimalActivity.this, HistoricActivity.class);
                    Animal animal = new Animal(animals.get(position).getId(),animals.get(position).getName(),animals.get(position).getYears(),animals.get(position).getIdHome());
                    intentValidate.putExtra("Animal", animal);
                    startActivity(intentValidate);
                }
            });
            listAnimal.setAdapter(adapter);
        } catch (JSONException e) {
            Log.i("", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
