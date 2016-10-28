package com.example.user.clientapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    String pNickname;
    String pIP;
    int pPort;
    //final Connection connection;
    //static Connection connection;
    GetApp mApplication;
    Connection conn;



    private VolleyS volley;
    protected RequestQueue fRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        makeRequest(pIP);


        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.TextName);
                EditText ip = (EditText) findViewById(R.id.TextIP);
                EditText port = (EditText) findViewById(R.id.TextPort);


                if(port.getText().toString().equals("") /*| ip.getText().toString().equals("") |name.getText().toString().equals("")*/){
                    pPort = 0;
                    //Toast toast11 =Toast.makeText(MainActivity.this, conn.toString(), Toast.LENGTH_LONG);
                    //toast11.show();
                }else{

                    pNickname = name.getText().toString();
                    pIP = ip.getText().toString();
                    pPort = Integer.parseInt(port.getText().toString());
                    mApplication = (GetApp) getApplicationContext();
                    mApplication.setConn(pNickname, pIP, pPort);
                    conn = ((GetApp) getApplicationContext()).getConn();
                }

                if(pPort!=0 && !pIP.equals("")&& !pNickname.equals("") /*&&conn!=null*/ ){
                    //Toast toast1 =Toast.makeText(MainActivity.this, Boolean.toString(conn.socket.isConnected()), Toast.LENGTH_LONG);
                    //toast1.show();

                    //makeRequest(pNickname);


                    Intent intent = new Intent(MainActivity.this, ReaderQR.class);
                    intent.putExtra("variable_Nickname", pNickname);
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
        //String url = "http://172.26.99.28:9080/Rest/chef";
        //String url = "http://192.168.100.11:9080/Rest/chef";
        //String url = "http://192.168.100.1:9080/Rest/chef";

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