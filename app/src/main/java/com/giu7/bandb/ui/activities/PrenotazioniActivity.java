package com.giu7.bandb.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PrenotazioniActivity extends AppCompatActivity {

    private DbManager dbManager;

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    private TableLayout table;
    private List<Prenotazione> prenotazioni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni);

        table = findViewById(R.id.prenotazioni_table);
        table.setStretchAllColumns(true);

        prenotazioni = getDbManager().prenotazioneDao().getAllPrenotazioni();

        for (Prenotazione prenotazione : prenotazioni){
            TableRow newRow = generateRow(prenotazione);
            table.addView(newRow);
        }
    }

    private TableRow generateRow(Prenotazione prenotazione){
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        Ospite ospite = getDbManager().ospiteDao().getById(prenotazione.getIdOspite());
        TextView ospiteTV = new TextView(this);
        ospiteTV.setText(ospite.getNome()+" "+ospite.getCognome());
        ospiteTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.addView(ospiteTV);

        TextView cameraTV = new TextView(this);
        cameraTV.setText(StringUtils.capitalize(prenotazione.getNomeStanza()));
        cameraTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.addView(cameraTV);

        TextView checkInTV = new TextView(this);
        Date checkIn = Date.from(prenotazione.getDataInizio().atZone(ZoneId.systemDefault()).toInstant());
        checkInTV.setText(formatter.format(checkIn));
        checkInTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.addView(checkInTV);

        TextView checkOutTV = new TextView(this);
        Date checkOut = Date.from(prenotazione.getDataFine().atZone(ZoneId.systemDefault()).toInstant());
        checkOutTV.setText(formatter.format(checkOut));
        checkOutTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.addView(checkOutTV);

        return tableRow;
    }

    private DbManager getDbManager(){
        if (dbManager == null){
            dbManager = DbManager.getDatabase(this);
        }
        return dbManager;
    }
}
