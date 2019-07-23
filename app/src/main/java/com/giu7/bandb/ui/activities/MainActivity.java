package com.giu7.bandb.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.services.DbManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbManager dbManager;

    private Button prenotazioniBtn;
    private Button camereBtn;

    private DbManager getDbManager(){
        if (dbManager == null){
            dbManager = DbManager.getDatabase(this);
        }
        return dbManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prenotazioniBtn = findViewById(R.id.prenotazioni_btn);
        camereBtn = findViewById(R.id.camere_btn);

        setupCamere();

        prenotazioniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prenotazioniBtn.setText(prenotazioniBtn.getText()+"-");
            }
        });

        camereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //camereBtn.setText(camereBtn.getText()+"/");

                List<Camera> camere = getDbManager().cameraDao().loadAllCamere();
                camereBtn.setText(camere.toString());
            }
        });
    }

    private void setupCamere(){
        Camera verde = new Camera("verde", 2, false, true, 777);
        Camera blu = new Camera("blu", 3, true, false, 2);
        Camera arancione = new Camera("arancione", 2, true, true, 66.6);

        getDbManager().cameraDao().insertCamera(verde);
        getDbManager().cameraDao().insertCamera(blu);
        getDbManager().cameraDao().insertCamera(arancione);
    }
}
