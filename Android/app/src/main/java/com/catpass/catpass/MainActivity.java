package com.catpass.catpass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

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
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    public void send(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpRequest.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        HttpRequest httpRequest = retrofit.create(HttpRequest.class);
        Call<JsonObject> call = httpRequest.createUser(editTextEmail.getText().toString(),editTextPassword.getText().toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.code() == 202){
                    Toast.makeText(getApplicationContext(),response.body().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,AccueilActivity.class));
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
    }
}
