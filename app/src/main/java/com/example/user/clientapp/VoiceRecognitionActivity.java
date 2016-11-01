package com.example.user.clientapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class VoiceRecognitionActivity extends Activity {

    private TextView txtSpeechInput, LabelMesa;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private List<Color> colors;
    private String Mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);
        Mesa = getIntent().getExtras().getString("variable_ScanResult");

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        LabelMesa = (TextView) findViewById(R.id.LabelMesa);
        LabelMesa.setText(getIntent().getExtras().getString("variable_ScanResult"));
        // hide the action bar
        //getActionBar().hide();
        initColors();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MaterialPaletteAdapter(colors));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        findViewById(R.id.buttonPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoiceRecognitionActivity.this, Pay.class);
                startActivity(intent);
            }
        });

    }
    private void initColors() {
        colors = new ArrayList<Color>();

        colors.add(new Color("2.0 ME LA COME", getResources().getString(R.color.blue)));
        colors.add(new Color(getString(R.string.indigo), getResources().getString(R.color.indigo)));
        colors.add(new Color(getString(R.string.red), getResources().getString(R.color.red)));
        colors.add(new Color(getString(R.string.green), getResources().getString(R.color.green)));
        colors.add(new Color(getString(R.string.orange), getResources().getString(R.color.orange)));
        colors.add(new Color(getString(R.string.grey), getResources().getString(R.color.bluegrey)));
        colors.add(new Color(getString(R.string.amber), getResources().getString(R.color.teal)));
        colors.add(new Color(getString(R.string.deeppurple), getResources().getString(R.color.deeppurple)));
        colors.add(new Color(getString(R.string.bluegrey), getResources().getString(R.color.bluegrey)));
        colors.add(new Color(getString(R.string.yellow), getResources().getString(R.color.yellow)));
        colors.add(new Color(getString(R.string.cyan), getResources().getString(R.color.cyan)));
        colors.add(new Color(getString(R.string.brown), getResources().getString(R.color.brown)));
        colors.add(new Color(getString(R.string.teal), getResources().getString(R.color.teal)));
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry! Your device doesn't support speech input",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}