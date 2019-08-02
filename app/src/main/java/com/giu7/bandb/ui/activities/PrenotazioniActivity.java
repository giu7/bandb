package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;
import com.giu7.bandb.utils.MyUtils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PrenotazioniActivity extends AppCompatActivity {

    private DbManager dbManager;

    //private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private TableLayout table;
    private Button addPrenotazione;
    private List<Prenotazione> prenotazioni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni);

        table = findViewById(R.id.prenotazioni_table);
        addPrenotazione = findViewById(R.id.add_prenotazione_btn);

        prenotazioni = getDbManager().prenotazioneDao().getAllPrenotazioni();

        for (Prenotazione prenotazione : prenotazioni){
            final TableRow newRow = generateRow(prenotazione);
            table.addView(newRow);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PrenotazioniActivity.this, MainActivity.class));
    }

    private TableRow generateRow(final Prenotazione prenotazione){
        final TableRow tableRow = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 150);
        tableRow.setLayoutParams(layoutParams);
        tableRow.setOrientation(LinearLayout.HORIZONTAL);
        tableRow.setWeightSum(4);

        Ospite ospite = getDbManager().ospiteDao().getById(prenotazione.getIdOspite());

        TextView nomeTV = new TextView(this);
        nomeTV.setId(R.id.prenotazioni_nome_tv);
        nomeTV.setText(ospite.getNome());
        setGeneralAttributes(nomeTV);
        tableRow.addView(nomeTV);

        TextView cognomeTV = new TextView(this);
        cognomeTV.setId(R.id.prenotazioni_cognome_tv);
        cognomeTV.setText(ospite.getCognome());
        setGeneralAttributes(cognomeTV);
        tableRow.addView(cognomeTV);

        TextView cameraTV = new TextView(this);
        cameraTV.setId(R.id.prenotazioni_camera_tv);
        cameraTV.setText(StringUtils.capitalize(prenotazione.getNomeStanza()));
        setGeneralAttributes(cameraTV);
        tableRow.addView(cameraTV);

        TextView checkInTV = new TextView(this);
        checkInTV.setId(R.id.prenotazioni_checkin_tv);
        Date checkIn = Date.from(prenotazione.getDataInizio().atZone(ZoneId.systemDefault()).toInstant());
        checkInTV.setText(MyUtils.formatter_ddMMyyyy.format(checkIn));
        setGeneralAttributes(checkInTV);
        tableRow.addView(checkInTV);

        tableRow.setBackgroundResource(R.drawable.border);

        tableRow.setPadding(10,10,10,10);

        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrenotazioniActivity.this, PrenotazioneDetailActivity.class);
                intent.putExtra("idPrenotazione", prenotazione.getId());
                startActivity(intent);
            }
        });

        return tableRow;
    }

    private void setGeneralAttributes(TextView textView){
        textView.setLayoutParams(new TableRow.LayoutParams(0, 150, 1f));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setGravity(Gravity.CENTER);
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
