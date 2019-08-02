package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.services.DbManager;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewPrenotazioneActivity extends AppCompatActivity{

    private static final String TAG = "NewPrenotazioneActivity";

    private DbManager dbManager;

    private List<Ospite> ospiti;
    private List<Camera> camere;

    private Spinner ospiteSpinner, cameraSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prenotazione);

        ospiteSpinner = findViewById(R.id.ospite_spinner);
        getOspiteSpinner();

        cameraSpinner = findViewById(R.id.camera_spinner);
        getCameraSpinner();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewPrenotazioneActivity.this, PrenotazioniActivity.class));
    }

    private void getOspiteSpinner(){
        ospiti = getDbManager().ospiteDao().getAllOspiti();

        final List<String> ospitiNames = new ArrayList<>();
        for (Ospite ospite : ospiti){
            ospitiNames.add(ospite.getNome()+" "+ospite.getCognome());
        }

        ArrayAdapter<String> ospitiAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        ospitiAdapter.addAll(ospitiNames);
        ospiteSpinner.setAdapter(ospitiAdapter);

        ospiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Ospite ospite = ospiti.get(position);
                Log.d(TAG, ospite.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void getCameraSpinner(){
        camere = getDbManager().cameraDao().getAllCamere();

        final List<String> camereNames = new ArrayList<>();
        for (Camera camera : camere){
            camereNames.add(StringUtils.capitalize(camera.getNome()));
        }

        ArrayAdapter<String> camereAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        camereAdapter.addAll(camereNames);
        cameraSpinner.setAdapter(camereAdapter);

        cameraSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Camera camera = camere.get(position);
                Log.d(TAG, camera.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
