package com.catpass.catpass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btnConnexion);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getConnexion();
            }
        });
    }

    public void getConnexion() {
        String URL  = "http://192.168.1.16:8080/catpass/authentification?email=" + editTextEmail.getText().toString() + "&password=" + editTextPassword.getText().toString();
        Http requete = new Http();
        requete.execute("GET",URL,"");
        String result ="";
        try {
            result = requete.get();
            JSONObject jsonObject = new JSONObject(result.toString());
            User user = new User(jsonObject.getString("email"), jsonObject.getString("firstName"), jsonObject.getString("lastName"));
            Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
        catch(Exception e ){
            Log.i("error", e.toString());
        }
    }
}
/*
    public void send(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpRequest.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        HttpRequest httpRequest = retrofit.create(HttpRequest.class);
        Call<JsonObject> call = httpRequest.createUser(editTextEmail.getText().toString(),editTextPassword.getText().toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.code() == 202){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        User user = new User(jsonObject.getString("email"),jsonObject.getString("firstName"),jsonObject.getString("lastName"));
                        Intent intent = new Intent(MainActivity.this,AccueilActivity.class);
                        intent.putExtra("User",user);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    if(response.code() == 401)
                        Toast.makeText(getApplicationContext(),"Bad email or password", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("",t.toString());
            }
        });
    }*/

