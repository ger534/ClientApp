package com.example.user.clientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);




        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] valores = {"5","5","5","5","5"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // vacio

            }
        });
        findViewById(R.id.buttonGiveOpinion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this, ReaderQR.class);
                startActivity(intent);
            }
        });
    }
}
