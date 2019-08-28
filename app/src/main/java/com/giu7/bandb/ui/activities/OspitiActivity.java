package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class OspitiActivity extends AppCompatActivity {

    private DbManager dbManager;

    private TableLayout table;
    private Button addOspite;
    private List<Ospite> ospiti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ospiti);

        table = findViewById(R.id.ospiti_table);
        addOspite = findViewById(R.id.add_ospite_btn);

        addOspite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OspitiActivity.this, NewOspiteActivity.class));
            }
        });

        ospiti = getDbManager().ospiteDao().getAllOspiti();

        for (Ospite ospite : ospiti){
            final TableRow newRow = generateRow(ospite);
            table.addView(newRow);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OspitiActivity.this, MainActivity.class));
    }

    private TableRow generateRow(final Ospite ospite){
        final TableRow tableRow = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 150);
        tableRow.setLayoutParams(layoutParams);
        tableRow.setOrientation(LinearLayout.HORIZONTAL);
        tableRow.setWeightSum(2);

        TextView nomeTV = new TextView(this);
        nomeTV.setId(R.id.ospiti_nome_tv);
        nomeTV.setText(ospite.getNome());
        setGeneralAttributes(nomeTV);
        tableRow.addView(nomeTV);

        TextView cognomeTV = new TextView(this);
        cognomeTV.setId(R.id.ospiti_cognome_tv);
        cognomeTV.setText(ospite.getCognome());
        setGeneralAttributes(cognomeTV);
        tableRow.addView(cognomeTV);

        tableRow.setBackgroundResource(R.drawable.border);

        tableRow.setPadding(10,10,10,10);

        //TODO ospite onClick

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
