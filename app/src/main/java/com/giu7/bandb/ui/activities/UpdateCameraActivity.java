package com.giu7.bandb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.services.DbManager;

import org.apache.commons.lang3.StringUtils;

public class UpdateCameraActivity extends AppCompatActivity {

    private static final String TAG = "UpdateCameraActivity";

    private DbManager dbManager;

    private EditText nome, prezzo;
    private NumberPicker letti;
    private RadioGroup tvGroup, bagnoGroup;
    private RadioButton tvTrue, tvFalse, bagnoTrue, bagnoFalse;

    private Button salva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_camera);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        nome = findViewById(R.id.nome_et);
        letti = findViewById(R.id.letti_picker);

        tvGroup = findViewById(R.id.tv_radio);
        tvTrue = findViewById(R.id.tvSi);
        tvFalse = findViewById(R.id.tvNo);

        bagnoGroup = findViewById(R.id.bagno_radio);
        bagnoTrue = findViewById(R.id.bagnoSi);
        bagnoFalse = findViewById(R.id.bagnoNo);

        prezzo = findViewById(R.id.prezzo_et);

        salva = findViewById(R.id.salva_btn);

        String nomeCamera = getIntent().getStringExtra("nome");

        final Camera camera = getDbManager().cameraDao().getByNome(nomeCamera);

        nome.setText(StringUtils.capitalize(camera.getNome()));
        letti.setMinValue(1);
        letti.setMaxValue(3);
        letti.setValue(camera.getLetti());
        letti.setWrapSelectorWheel(true);

        if (camera.isTv()) {
            tvTrue.setChecked(true);
        } else {
            tvFalse.setChecked(true);
        }

        if (camera.isBagno()) {
            bagnoTrue.setChecked(true);
        } else {
            bagnoFalse.setChecked(true);
        }

        prezzo.setText(String.valueOf(camera.getPrezzo()));

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tv = tvTrue.isChecked();
                boolean bagno = bagnoTrue.isChecked();

                Camera nuova = new Camera(nome.getText().toString().toLowerCase(), letti.getValue(), tv, bagno, camera.getFotoUrl(), Double.valueOf(prezzo.getText().toString()));

                Log.d(TAG+1, camera.toString());
                Log.d(TAG+1, nuova.toString());
                Log.d(TAG+1, String.valueOf(camera.equals(nuova)));

                if (!camera.equals(nuova)){
                    Log.d(TAG+1, "Updating camera");
                    updateCameraName(nuova, camera);
                }

                startActivity(new Intent(UpdateCameraActivity.this, CamereActivity.class));
            }
        });
    }

    private void updateCameraName(Camera nuova, Camera vecchia) {
        if (!vecchia.getNome().equals(nome.getText().toString().toLowerCase())){
            getDbManager().cameraDao().insertCamera(nuova);
            getDbManager().prenotazioneDao().updateFK(vecchia.getNome(), nome.getText().toString().toLowerCase());
            getDbManager().cameraDao().deleteCamera(vecchia);
        }
        else{
            getDbManager().cameraDao().update(vecchia.getNome(), nome.getText().toString().toLowerCase(), letti.getValue(), nuova.isTv(), nuova.isBagno(), Double.valueOf(prezzo.getText().toString()));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateCameraActivity.this, CamereActivity.class));
    }

    private DbManager getDbManager(){
        return dbManager == null ? dbManager = DbManager.getDatabase(this) : dbManager;
    }
}
