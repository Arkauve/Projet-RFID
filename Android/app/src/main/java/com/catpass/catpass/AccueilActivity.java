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
 * Created by Jordan on 25/04/2017.
 */

public class AccueilActivity extends AppCompatActivity {
    User user;
    private ArrayList<Home> homes;
    private HomeAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        user = getIntent().getExtras().getParcelable("User");
        homes = new ArrayList<Home>();
        getAllHome();

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
            case R.id.lecture:
                Intent intentValidate = new Intent(AccueilActivity.this, NfcReader.class);
                startActivity(intentValidate);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getAllHome() {
        String URL = getResources().getString(R.string.URL)+"/authentification/" + user.getEmail();
        String params = "";
        Http requete = new Http();
        requete.execute("GET", URL, params);
        String result = "";
        try {
            result = requete.get();
            JSONArray jsonArray = new JSONArray(result.toString());
            adapter = new HomeAdapter(getApplicationContext(), R.layout.row_maison, homes);
            final ListView listHome = (ListView) findViewById(R.id.listHome);

            for (int i = 0; i < jsonArray.length(); i++) {
                final Home home = new Home();
                home.setAdress(jsonArray.getJSONObject(i).getString("adress"));
                home.setId(jsonArray.getJSONObject(i).getInt("id"));
                homes.add(home);
            }
            listHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    Intent intentValidate = new Intent(AccueilActivity.this, AnimalActivity.class);
                    Home home = new Home(homes.get(position).getId(),homes.get(position).getAdress());
                    intentValidate.putExtra("Home", home);
                    startActivity(intentValidate);
                }
            });
            listHome.setAdapter(adapter);

        } catch (JSONException e) {
            Log.i("",e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public void send() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpRequest.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        HttpRequest httpRequest = retrofit.create(HttpRequest.class);
        Call<JsonArray> call = httpRequest.getAllHome(user.getEmail());
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().toString());
                    adapter = new HomeAdapter(getApplicationContext(), R.layout.row_maison, homes);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Home home = new Home();
                        home.setAdress(jsonArray.getJSONObject(i).getString("adress"));
                        homes.add(home);
                    }
                    ListView listHome = (ListView) findViewById(R.id.listHome);
                    listHome.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }*/
}
