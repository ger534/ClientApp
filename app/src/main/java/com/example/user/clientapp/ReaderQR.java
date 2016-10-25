package com.example.user.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReaderQR extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_qr);
        findViewById(R.id.QR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScannerView = new ZXingScannerView(ReaderQR.this);
                setContentView(mScannerView);
                mScannerView.setResultHandler(ReaderQR.this);
                mScannerView.startCamera();
            }

        });
    }

    @Override
    protected void onPause(){
        ReaderQR.super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //aquí se puede hacer lo que se quiera con el dato
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Intent intent = new Intent(ReaderQR.this, VoiceRecognitionActivity.class);
        intent.putExtra("variable_ScanResult", result.getText());
        startActivity(intent);
        //resume scanning
        //mSacannerView.resumeCameraPreview(this); //uncommet out this line when you want scan again
    }
}
