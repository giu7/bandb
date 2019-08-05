package com.giu7.bandb.ui.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;
import com.giu7.bandb.utils.MyUtils;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewPrenotazioneActivity extends AppCompatActivity{

    private static final String TAG = "NewPrenotazioneActivity";

    private DbManager dbManager;

    private List<Ospite> ospiti;
    private List<Camera> camere;

    private DatePickerDialog checkInPicker, checkOutPicker;

    private Camera camera;
    private Ospite ospite;
    private LocalDateTime checkInDateTime, checkOutDateTime;

    private Spinner ospiteSpinner, cameraSpinner;
    private EditText checkIn, checkOut;
    private Button prenota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prenotazione);

        ospiteSpinner = findViewById(R.id.ospite_spinner);
        getOspiteSpinner();

        cameraSpinner = findViewById(R.id.camera_spinner);
        getCameraSpinner();

        checkIn = findViewById(R.id.checkin_et);
        getCheckInPicker();

        checkOut = findViewById(R.id.checkout_et);
        getCheckOutPicker();

        prenota = findViewById(R.id.crea_prenotazione_btn);
        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDate()){
                    Log.d(TAG, checkInDateTime.toString());
                    Log.d(TAG, checkOutDateTime.toString());


                    return;
                }
                Log.d(TAG, "Errori a caso");
            }
        });
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
                ospite = ospiti.get(position);
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
                camera = camere.get(position);
                Log.d(TAG, camera.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void getCheckInPicker(){
        checkIn.setInputType(InputType.TYPE_NULL);
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                checkInPicker = new DatePickerDialog(NewPrenotazioneActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                checkIn.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                checkInDateTime = LocalDateTime.of(year, monthOfYear+1, dayOfMonth, 12, 0);
                            }
                        }, year, month, day);
                checkInPicker.show();
            }
        });
    }

    private void getCheckOutPicker(){
        checkOut.setInputType(InputType.TYPE_NULL);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                checkOutPicker = new DatePickerDialog(NewPrenotazioneActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        checkOut.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        checkOutDateTime = LocalDateTime.of(year, monthOfYear+1, dayOfMonth, 10, 0);
                    }
                }, year, month, day);
                checkOutPicker.show();
            }
        });
    }

    private boolean checkDate(){
        if (checkInDateTime == null) {
            return false;
        }
        if (checkOutDateTime == null){
            return false;
        }
        if (checkInDateTime.isAfter(checkOutDateTime)) {
            return false;
        }
        if (checkInDateTime.isEqual(checkOutDateTime)) {
            return false;
        }

        /*List<Prenotazione> prenotazioni = getDbManager().prenotazioneDao().getAllPrenotazioni();

       for (Prenotazione prenotazione : prenotazioni){
           if (prenotazione.getNomeStanza().equals(camera)){
               if (prenotazione.getDataInizio().isBefore(checkInDateTime))&&(prenotazione.getDataFine().isAfter(checkInDateTime))
                       return false;

           }
       }*/

        return true;
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
