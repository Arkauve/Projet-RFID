package com.catpass.catpass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilAnimalActivity extends AppCompatActivity {

    Animal animal;
    TextView name;
    TextView age;
    Button historic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_animal2);
        name = (TextView) findViewById(R.id.Tx_Name);
        age = (TextView) findViewById(R.id.Tx_Age);
        historic = (Button) findViewById(R.id.Btn_Historic);
        load();
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

    private void load(){
        animal = getIntent().getExtras().getParcelable("Animal");
        name.setText(animal.getName());
        age.setText(animal.getYears());
        historic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentValidate = new Intent(ProfilAnimalActivity.this, HistoricActivity.class);
                intentValidate.putExtra("Animal", animal);
                startActivity(intentValidate);
            }
        });
    }

}
