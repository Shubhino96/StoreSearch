package com.example.shubhangi.storesearch;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.flipper);
        vf.startFlipping();
        spin = (Spinner) findViewById(R.id.spinner2);
        List<String> categ = new ArrayList<>();
        categ.add("Grocery");
        categ.add("Furnitures");
        categ.add("Electronics");
        categ.add("Florists");
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categ);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition(position).toString().equals("Grocery")) {
                        Intent i1 = new Intent(MainActivity.this, Grocery.class);
                        MainActivity.this.startActivity(i1);
                    }
                    if (parent.getItemAtPosition(position).toString().equals("Furnitures")) {
                        Intent i2 = new Intent(MainActivity.this, Furnitures.class);
                        MainActivity.this.startActivity(i2);
                    }
                    if (parent.getItemAtPosition(position).toString().equals("Electronics")) {
                        Intent i = new Intent(MainActivity.this, Electronics.class);
                        MainActivity.this.startActivity(i);
                    }
                    if (parent.getItemAtPosition(position).toString().equals("Florists")) {
                        Intent i3 = new Intent(MainActivity.this, Florists.class);
                        MainActivity.this.startActivity(i3);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

}
