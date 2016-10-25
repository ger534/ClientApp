package com.example.user.clientapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.Result;


public class MainActivity extends AppCompatActivity{
    String pNickname;
    String pIP;
    int pPort;
    //final Connection connection;
    //static Connection connection;
    GetApp mApplication;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    Rest rest = new Rest();
                    rest.hola();

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

}