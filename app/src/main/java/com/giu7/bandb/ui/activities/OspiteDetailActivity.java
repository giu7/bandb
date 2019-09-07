package com.giu7.bandb.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.services.DbManager;

public class OspiteDetailActivity extends AppCompatActivity {

    private DbManager dbManager;
    private TextView nome, cognome, telefono, mail;
    private Button elimina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ospite_detail);

        nome = findViewById(R.id.dettagli_nome);
        cognome = findViewById(R.id.dettagli_cognome);
        telefono = findViewById(R.id.dettagli_telefono);
        mail = findViewById(R.id.dettagli_mail);

        elimina = findViewById(R.id.elimina_ospite_btn);

        final Ospite ospite = getDbManager().ospiteDao().getById(getIntent().getIntExtra("idOspite", -1));

        nome.setText(ospite.getNome());
        cognome.setText(ospite.getCognome());
        telefono.setText(ospite.getTelefono());
        mail.setText(ospite.getMail());

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safeDelete(ospite);
            }
        });
    }

    private void safeDelete(final Ospite ospite){
        int count = getDbManager().prenotazioneDao().getPrenotazioniByIdOspite(ospite.getId()).size();

        if (count == 0){
            new AlertDialog.Builder(this)
                    .setTitle("Attenzione")
                    .setMessage("Sei sicuro di voler eliminare questo ospite?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getDbManager().ospiteDao().deleteOspite(ospite);
                            startActivity(new Intent(OspiteDetailActivity.this, OspitiActivity.class));
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        }
        else {
            new AlertDialog.Builder(this)
                    .setTitle("Errore")
                    .setMessage("Impossibile eliminare questo ospite perchè ha delle prenotazioni associate!")
                    .setNeutralButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OspiteDetailActivity.this, OspitiActivity.class));
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
