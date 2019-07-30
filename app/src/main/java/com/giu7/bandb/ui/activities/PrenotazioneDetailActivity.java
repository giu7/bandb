package com.giu7.bandb.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;
import com.giu7.bandb.utils.MyUtils;

import org.apache.commons.lang3.StringUtils;

import java.time.ZoneId;
import java.util.Date;

public class PrenotazioneDetailActivity extends AppCompatActivity {

    private static final String TAG = "PrenotazioneDetailActivity";

    private DbManager dbManager;

    //prenotazione details
    TextView checkIn, checkOut, creationTime, pagato, metodoPagamento;
    LinearLayout metodoPagamentoLyt;

    //camera details
    TextView nomeCamera, postiLetto, tv, bagno, prezzo;

    //ospite details
    TextView nomeOspite, cognomeOspite, telefono, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione_detail);

        checkIn = findViewById(R.id.dettagli_checkin);
        checkOut = findViewById(R.id.dettagli_checkout);
        creationTime = findViewById(R.id.dettagli_creationtime);
        pagato = findViewById(R.id.dettagli_pagato);
        metodoPagamentoLyt = findViewById(R.id.dettagli_metodo_pagamento_layout);
        metodoPagamento = findViewById(R.id.dettagli_metodo_pagamento);

        nomeCamera = findViewById(R.id.dettagli_nome_camera);
        postiLetto = findViewById(R.id.dettagli_letti);
        tv = findViewById(R.id.dettagli_tv);
        bagno = findViewById(R.id.dettagli_bagno);
        prezzo = findViewById(R.id.dettagli_prezzo);

        nomeOspite = findViewById(R.id.dettagli_nome);
        cognomeOspite = findViewById(R.id.dettagli_cognome);
        telefono = findViewById(R.id.dettagli_telefono);
        mail = findViewById(R.id.dettagli_mail);

        Prenotazione prenotazione = getDbManager().prenotazioneDao().getById(getIntent().getIntExtra("idPrenotazione", -1));
        Ospite ospite = getDbManager().ospiteDao().getById(prenotazione.getIdOspite());
        Camera camera = getDbManager().cameraDao().getById(prenotazione.getNomeStanza());

        Date checkInDate = Date.from(prenotazione.getDataInizio().atZone(ZoneId.systemDefault()).toInstant());
        checkIn.setText(MyUtils.formatter_ddMMyyyy.format(checkInDate));
        Date checkOutDate = Date.from(prenotazione.getDataFine().atZone(ZoneId.systemDefault()).toInstant());
        checkOut.setText(MyUtils.formatter_ddMMyyyy.format(checkOutDate));
        Date creationTimeDate = Date.from(prenotazione.getCreationTime().atZone(ZoneId.systemDefault()).toInstant());
        creationTime.setText(MyUtils.formatter_ddMMyyyyHHmm.format(creationTimeDate));
        pagato.setText(prenotazione.isPagato() ? "Si" : "No");
        if (prenotazione.isPagato()){
            metodoPagamentoLyt.setVisibility(View.VISIBLE);
            metodoPagamento.setText(prenotazione.getMetodoPagamento());
        }

        nomeCamera.setText(StringUtils.capitalize(camera.getNome()));
        postiLetto.setText(String.valueOf(camera.getLetti()));
        tv.setText(MyUtils.getSiNo(camera.isTv()));
        bagno.setText(MyUtils.getSiNo(camera.isBagno()));
        prezzo.setText(String.valueOf(camera.getPrezzo()));

        nomeOspite.setText(ospite.getNome());
        cognomeOspite.setText(ospite.getCognome());
        telefono.setText(ospite.getTelefono());
        mail.setText(ospite.getMail());
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
