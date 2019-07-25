package com.giu7.bandb;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.services.DbManager;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private DbManager dbManager;
    private Context context = InstrumentationRegistry.getTargetContext();

    private DbManager getDbManager(){
        if (dbManager == null){
            dbManager = DbManager.getInMemoryDatabase(context);
        }
        return dbManager;
    }

    @Test
    public void insertCamera() {
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        getDbManager().cameraDao().insertCamera(viola);
        assertEquals(getDbManager().cameraDao().getAllCamere().size(), 1);
    }

    @Test
    public void insertOspite() {
        Ospite ospite = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        getDbManager().ospiteDao().insertOspite(ospite);
        assertEquals(getDbManager().ospiteDao().getAllOspiti().size(), 1);
    }
}
