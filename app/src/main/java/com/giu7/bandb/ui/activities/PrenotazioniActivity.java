package com.giu7.bandb.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;

import java.util.List;

public class PrenotazioniActivity extends AppCompatActivity {

    private DbManager dbManager;

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
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < 5; j++) {
                TextView button = new TextView(this);
                final int buttonNumber = (j);
                button.setText("" + buttonNumber);
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                tableRow.addView(button);
            }

            table.addView(tableRow);
        }
    }

    private DbManager getDbManager(){
        if (dbManager == null){
            dbManager = DbManager.getDatabase(this);
        }
        return dbManager;
    }
}
