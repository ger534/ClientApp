package com.example.user.clientapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity{

    String pIP;
    int pPort;

    private VolleyS volley;
    protected RequestQueue fRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);
        String[] valores = {"Bronce", "Oro", "Platino"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });

        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText ip = (EditText) findViewById(R.id.TextIP);
                EditText port = (EditText) findViewById(R.id.TextPort);


                if(port.getText().toString().equals("") /*| ip.getText().toString().equals("") |name.getText().toString().equals("")*/){
                    pPort = 0;
                    //Toast toast11 =Toast.makeText(MainActivity.this, conn.toString(), Toast.LENGTH_LONG);
                    //toast11.show();
                }else{


                    pIP = ip.getText().toString();
                    pPort = Integer.parseInt(port.getText().toString());


                }

                if(pPort!=0 && !pIP.equals("")){


                    makeRequest(pIP);


                    //onResume();
                    Intent intent = new Intent(MainActivity.this, ReaderQR.class);
                    startActivity(intent);
                }else{
                    Toast toast =Toast.makeText(MainActivity.this, "Intente de nuevo", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }


    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            onPreStartConnection();
            fRequestQueue.add(request);
        }
    }

    public void onPreStartConnection() {
        MainActivity.this.setProgressBarIndeterminateVisibility(true);
    }

    public void onConnectionFinished() {
        MainActivity.this.setProgressBarIndeterminateVisibility(false);
    }

    public void onConnectionFailed(String error) {
        MainActivity.this.setProgressBarIndeterminateVisibility(false);
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    private void makeRequest(String IP){
        //String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=39.476245,-0.349448&sensor=true";
        //String url = "http://"+IP+"/Rest/chef";
        String url = "http://192.168.100.8:9080/RestChef/chef";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                System.out.println(jsonObject.toString());
                Toast toast1 =Toast.makeText(MainActivity.this, "SIIIIIIIIIII" + jsonObject.toString(), Toast.LENGTH_LONG);
                toast1.show();
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
                System.out.println("NO HOLA NO HOLA NO HOLA ");
            }
        });
        addToQueue(request);
    }


}