package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.services.DbManager;

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

        setup();

        prenotazioniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prenotazioniBtn.setText(prenotazioniBtn.getText()+"-");
            }
        });

        camereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CamereActivity.class));
            }
        });
    }

    private void setup(){
        getDbManager().cameraDao().deleteAllCamere();
        getDbManager().ospiteDao().deleteAllOspiti();

        Camera verde = new Camera(getString(R.string.verde), 2, false, true, getString(R.string.foto_camera_verde), 777);
        Camera blu = new Camera(getString(R.string.blu), 3, true, false, getString(R.string.foto_camera_blu),2);
        Camera arancione = new Camera(getString(R.string.arancione), 2, true, true, getString(R.string.foto_camera_arancione), 66.6);

        getDbManager().cameraDao().insertCamera(verde);
        getDbManager().cameraDao().insertCamera(blu);
        getDbManager().cameraDao().insertCamera(arancione);

        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789098", "giuseppe.piano98@gmail.com", "giuser", "password");
        Ospite lillo = new Ospite("Alessandro", "Littera", "3453434343", "lillo@lillolandia.it", "lillo", "pecora");
        Ospite geta = new Ospite("Gaetano", "La Porta", "34545454545", "geta@ares.it", "geta", "ares");

        Log.d("MAIN", giu.toString());

        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(lillo);
        getDbManager().ospiteDao().insertOspite(geta);

        Log.d("MAIN", giu.toString());

    }
}
