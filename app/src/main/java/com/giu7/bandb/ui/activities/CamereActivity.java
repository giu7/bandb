package com.giu7.bandb.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.services.DbManager;
import com.giu7.bandb.ui.adapters.CameraAdapter;

import java.util.ArrayList;
import java.util.List;

public class CamereActivity extends AppCompatActivity {

    private DbManager dbManager;

    private RecyclerView camereRV;
    private RecyclerView.LayoutManager layoutManager;
    private CameraAdapter cameraAdapter;
    private List<Camera> camere = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camere);

        camere = getDbManager().cameraDao().loadAllCamere();

        camereRV = findViewById(R.id.camere_rv);
        layoutManager = new LinearLayoutManager(this);
        cameraAdapter = new CameraAdapter(this, camere);

        camereRV.setLayoutManager(layoutManager);
        camereRV.setAdapter(cameraAdapter);
    }

    private DbManager getDbManager(){
        if (dbManager == null){
            dbManager = DbManager.getDatabase(this);
        }
        return dbManager;
    }

}
