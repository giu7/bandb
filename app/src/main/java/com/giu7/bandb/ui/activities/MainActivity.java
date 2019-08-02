package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;

import java.time.LocalDateTime;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DbManager dbManager;

    private Button prenotazioniBtn;
    private Button camereBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prenotazioniBtn = findViewById(R.id.prenotazioni_btn);
        camereBtn = findViewById(R.id.camere_btn);

        prenotazioniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PrenotazioniActivity.class));
            }
        });

        camereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CamereActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void setup(){
        getDbManager().prenotazioneDao().deleteAllPrenotazioni();
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

        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(lillo);
        getDbManager().ospiteDao().insertOspite(geta);

        LocalDateTime checkIn1 = LocalDateTime.of(2019,7,1,0,0);
        LocalDateTime checkOut1 = LocalDateTime.of(2020, 7, 1, 0, 0);
        Ospite ospite1 = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        Prenotazione p1 = new Prenotazione(checkIn1, checkOut1, true, "Bonifico",  ospite1.getId(), "verde");

        LocalDateTime checkIn2 = LocalDateTime.of(2019,7,2,0,0);
        LocalDateTime checkOut2 = LocalDateTime.of(2020, 7, 2, 0, 0);
        Ospite ospite2 = getDbManager().ospiteDao().getByNomeAndCognome("Gaetano", "La Porta");
        Prenotazione p2 = new Prenotazione(checkIn2, checkOut2, true, "Assegno",  ospite2.getId(), "blu");

        LocalDateTime checkIn3 = LocalDateTime.of(2019,7,3,0,0);
        LocalDateTime checkOut3 = LocalDateTime.of(2020, 7, 3, 0, 0);
        Ospite ospite3 = getDbManager().ospiteDao().getByNomeAndCognome("Alessandro", "Littera");
        Prenotazione p3 = new Prenotazione(checkIn3, checkOut3, false, "Contanti",  ospite3.getId(), "arancione");

        getDbManager().prenotazioneDao().insert(p1);
        getDbManager().prenotazioneDao().insert(p2);
        getDbManager().prenotazioneDao().insert(p3);
    }

    private void clearAll(){
        getDbManager().prenotazioneDao().deleteAllPrenotazioni();
        getDbManager().cameraDao().deleteAllCamere();
        getDbManager().ospiteDao().deleteAllOspiti();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.preload){
            setup();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.clear_all){
            clearAll();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
