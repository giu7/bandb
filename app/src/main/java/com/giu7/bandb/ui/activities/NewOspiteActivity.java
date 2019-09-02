package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.services.DbManager;

public class NewOspiteActivity extends AppCompatActivity {

    private static final String TAG = "NewOspiteActivity";

    private DbManager dbManager;
    private EditText nome, cognome, telefono, email;
    private Button aggiungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ospite);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        nome = findViewById(R.id.nome_et);
        cognome = findViewById(R.id.cognome_et);
        telefono = findViewById(R.id.telefono_et);
        email = findViewById(R.id.email_et);

        aggiungi = findViewById(R.id.crea_ospite_btn);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOspite();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewOspiteActivity.this, OspitiActivity.class));
    }

    private void addOspite(){
        Ospite ospite = new Ospite(nome.getText().toString(), cognome.getText().toString(), telefono.getText().toString(), email.getText().toString(), null, null);
        getDbManager().ospiteDao().insertOspite(ospite);
        startActivity(new Intent(NewOspiteActivity.this, OspitiActivity.class));
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
