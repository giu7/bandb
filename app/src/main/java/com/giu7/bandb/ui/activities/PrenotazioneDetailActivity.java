package com.giu7.bandb.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;

public class PrenotazioneDetailActivity extends AppCompatActivity {

    private static final String TAG = "PrenotazioneDetailActivity";

    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione_detail);

        Prenotazione prenotazione = getDbManager().prenotazioneDao().getById(getIntent().getIntExtra("idPrenotazione", -1));
        Ospite ospite = getDbManager().ospiteDao().getById(prenotazione.getIdOspite());
        Camera camera = getDbManager().cameraDao().getById(prenotazione.getNomeStanza());

        Log.d(TAG, prenotazione.toString());
        Log.d(TAG, ospite.toString());
        Log.d(TAG, camera.toString());
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
