package com.giu7.bandb;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.PreferenceMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;
import com.giu7.bandb.models.Prenotazione;
import com.giu7.bandb.services.DbManager;
import com.giu7.bandb.ui.activities.OspitiActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    private static final String TAG = "InstrumentedTest";


    private DbManager dbManager;
    private Context context = InstrumentationRegistry.getTargetContext();

    private DbManager getDbManager(){
        if (dbManager == null){
            dbManager = DbManager.getInMemoryDatabase(context);
        }
        return dbManager;
    }

    @Before
    public void cleanUp(){
        getDbManager().prenotazioneDao().deleteAllPrenotazioni();
        getDbManager().cameraDao().deleteAllCamere();
        getDbManager().ospiteDao().deleteAllOspiti();
    }

    @Test
    public void dbConnectionTest(){
        DbManager dbManagerTest = getDbManager();

        assertNotNull(dbManagerTest);

        assertNotNull(getDbManager().cameraDao());
        assertNotNull(getDbManager().ospiteDao());
        assertNotNull(getDbManager().prenotazioneDao());
    }

    //CameraDao tests

    @Test
    public void insertCameraTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        getDbManager().cameraDao().insertCamera(viola);
        assertEquals(getDbManager().cameraDao().getAllCamere().size(), 1);
    }

    @Test
    public void getAllCamereTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);

        List<Camera> camere = getDbManager().cameraDao().getAllCamere();

        assertEquals(camere.size(), 2);
        assertEquals(camere.get(0).getNome(), "Viola");
        assertEquals(camere.get(1).getNome(), "Rossa");
    }

    @Test
    public void getByNomeTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        getDbManager().cameraDao().insertCamera(viola);

        Camera camera = getDbManager().cameraDao().getByNome("Viola");

        assertEquals(camera, viola);
    }

    @Test
    public void deleteCameraTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);

        assertEquals(getDbManager().cameraDao().getAllCamere().size(), 2);

        getDbManager().cameraDao().deleteCamera(viola);

        assertEquals(getDbManager().cameraDao().getAllCamere().size(), 1);
    }

    @Test
    public void deleteAllCamereTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);

        assertEquals(getDbManager().cameraDao().getAllCamere().size(), 2);

        getDbManager().cameraDao().deleteAllCamere();

        assertEquals(getDbManager().cameraDao().getAllCamere().size(), 0);
    }

    //OspiteDao tests

    @Test
    public void insertOspite() {
        Ospite ospite = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        getDbManager().ospiteDao().insertOspite(ospite);
        assertEquals(getDbManager().ospiteDao().getAllOspiti().size(), 1);
    }

    @Test
    public void getAllOspitiTest(){
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        List<Ospite> ospiti = getDbManager().ospiteDao().getAllOspiti();

        assertEquals(ospiti.size(), 2);
        assertEquals(ospiti.get(0).getNome(), "Giuseppe");
        assertEquals(ospiti.get(1).getNome(), "Gaetano");
    }

    @Test
    public void getByNomeAndCognomeTest(){
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        getDbManager().ospiteDao().insertOspite(giu);

        Ospite ospite = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");

        assertEquals(ospite.getNome(), "Giuseppe");
    }

    @Test
    public void getById(){
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        getDbManager().ospiteDao().insertOspite(giu);

        Ospite ospite = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        int id = ospite.getId();

        Ospite ospite1 = getDbManager().ospiteDao().getById(id);

        assertEquals(ospite, ospite1);
    }

    @Test
    public void deleteOspiteTest(){
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        assertEquals(getDbManager().ospiteDao().getAllOspiti().size(), 2);

        Ospite giu2 = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");

        getDbManager().ospiteDao().deleteOspite(giu2);

        assertEquals(getDbManager().ospiteDao().getAllOspiti().size(), 1);
    }

    @Test
    public void deleteAllOspitiTest(){
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        assertEquals(getDbManager().ospiteDao().getAllOspiti().size(), 2);

        getDbManager().ospiteDao().deleteAllOspiti();

        assertEquals(getDbManager().ospiteDao().getAllOspiti().size(), 0);
    }

    //PrenotazioneDao tests

    @Test
    public void insertPrenotazioneTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().ospiteDao().insertOspite(giu);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");

        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);

        Prenotazione prenotazione = new Prenotazione(oggi, domani, true, "Contanti", giu.getId(), viola.getNome());
        getDbManager().prenotazioneDao().insert(prenotazione);

        assertEquals(getDbManager().prenotazioneDao().getAllPrenotazioni().size(), 1);
    }

    @Test
    public void getAllPrenotazioniTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);
        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        gae = getDbManager().ospiteDao().getByNomeAndCognome("Gaetano", "La Porta");

        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);

        Prenotazione pre1 = new Prenotazione(oggi, domani, true, "Contanti", giu.getId(), viola.getNome());
        Prenotazione pre2 = new Prenotazione(oggi, domani, false, "Contanti", gae.getId(), rossa.getNome());

        getDbManager().prenotazioneDao().insert(pre1);
        getDbManager().prenotazioneDao().insert(pre2);

        List<Prenotazione> prenotazioni = getDbManager().prenotazioneDao().getAllPrenotazioni();

        assertEquals(prenotazioni.size(), 2);
        assertTrue(prenotazioni.get(0).isPagato());
        assertFalse(prenotazioni.get(1).isPagato());
    }

    @Test
    public void deletePrenotazioneTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);
        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        gae = getDbManager().ospiteDao().getByNomeAndCognome("Gaetano", "La Porta");

        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);

        Prenotazione pre1 = new Prenotazione(oggi, domani, true, "Contanti", giu.getId(), viola.getNome());
        Prenotazione pre2 = new Prenotazione(oggi, domani, false, "Contanti", gae.getId(), rossa.getNome());

        getDbManager().prenotazioneDao().insert(pre1);
        getDbManager().prenotazioneDao().insert(pre2);

        assertEquals(getDbManager().prenotazioneDao().getAllPrenotazioni().size(), 2);

        pre1 = getDbManager().prenotazioneDao().getAllPrenotazioni().get(0);
        getDbManager().prenotazioneDao().delete(pre1);

        assertEquals(getDbManager().prenotazioneDao().getAllPrenotazioni().size(), 1);
    }

    @Test
    public void deleteAllPrenotazioniTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);
        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        gae = getDbManager().ospiteDao().getByNomeAndCognome("Gaetano", "La Porta");

        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);

        Prenotazione pre1 = new Prenotazione(oggi, domani, true, "Contanti", giu.getId(), viola.getNome());
        Prenotazione pre2 = new Prenotazione(oggi, domani, false, "Contanti", gae.getId(), rossa.getNome());

        getDbManager().prenotazioneDao().insert(pre1);
        getDbManager().prenotazioneDao().insert(pre2);

        assertEquals(getDbManager().prenotazioneDao().getAllPrenotazioni().size(), 2);

        getDbManager().prenotazioneDao().deleteAllPrenotazioni();

        assertEquals(getDbManager().prenotazioneDao().getAllPrenotazioni().size(), 0);
    }

    @Test
    public void getByIdTest() {
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg", "giu", "seppe");

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().ospiteDao().insertOspite(giu);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");

        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);

        Prenotazione pre1 = new Prenotazione(oggi, domani, true, "Contanti", giu.getId(), viola.getNome());

        getDbManager().prenotazioneDao().insert(pre1);
        pre1 = getDbManager().prenotazioneDao().getAllPrenotazioni().get(0);

        Prenotazione pre2 = getDbManager().prenotazioneDao().getById(pre1.getId());

        assertEquals(pre1, pre2);
    }

    @Test
    public void getPrenotazioniByIdOspiteTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera rossa = new Camera("Rossa", 2, true, false, null, 666);
        Camera bianca = new Camera("Bianca", 3, true, false, null, 1000);

        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        LocalDateTime ieri = LocalDateTime.of(2019, Month.SEPTEMBER, 2, 10, 0);
        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);
        LocalDateTime dopodomani = LocalDateTime.of(2019, Month.SEPTEMBER, 5, 10, 0);

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(rossa);
        getDbManager().cameraDao().insertCamera(bianca);
        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        gae = getDbManager().ospiteDao().getByNomeAndCognome("Gaetano", "La Porta");

        Prenotazione prenotazioneGiu1 = new Prenotazione(ieri, oggi,true,   "contanti", giu.getId(), "Viola");
        Prenotazione prenotazioneGiu2 = new Prenotazione(domani, dopodomani,true,   "assegno", giu.getId(), "Bianca");
        Prenotazione prenotazioneGae1 = new Prenotazione(ieri, dopodomani,false,   null, gae.getId(), "Rossa");

        getDbManager().prenotazioneDao().insert(prenotazioneGiu1);
        getDbManager().prenotazioneDao().insert(prenotazioneGiu2);
        getDbManager().prenotazioneDao().insert(prenotazioneGae1);

        List<Prenotazione> prenotazioniGiu = getDbManager().prenotazioneDao().getPrenotazioniByIdOspite(giu.getId());

        assertEquals(prenotazioniGiu.size(), 2);
        assertEquals(prenotazioniGiu.get(0).getMetodoPagamento(), "contanti");
        assertEquals(prenotazioniGiu.get(1).getMetodoPagamento(), "assegno");
    }

    @Test
    public void getConflictsForDatesTest(){
        Camera viola = new Camera("Viola", 2, false, true, null, 777);

        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");
        Ospite gae = new Ospite("Gaetano", "La Porta", "3542145687", "aa@a.aa","gae", "tano");

        LocalDateTime ieri = LocalDateTime.of(2019, Month.SEPTEMBER, 2, 10, 0);
        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);
        LocalDateTime domani = LocalDateTime.of(2019, Month.SEPTEMBER, 4, 10, 0);
        LocalDateTime dopodomani = LocalDateTime.of(2019, Month.SEPTEMBER, 5, 10, 0);

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().ospiteDao().insertOspite(giu);
        getDbManager().ospiteDao().insertOspite(gae);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");
        gae = getDbManager().ospiteDao().getByNomeAndCognome("Gaetano", "La Porta");

        Prenotazione prenotazioneGiu1 = new Prenotazione(ieri, domani,true,   "contanti", giu.getId(), "Viola");
        getDbManager().prenotazioneDao().insert(prenotazioneGiu1);

        int conflicts = getDbManager().prenotazioneDao().getConflictsForDates("Viola", oggi, dopodomani);

        assertEquals(conflicts,1);
    }

    @Test
    public void updateFKTest() {
        Camera viola = new Camera("Viola", 2, false, true, null, 777);
        Camera bianca = new Camera("Bianca", 2, false, true, null, 777);
        Ospite giu = new Ospite("Giuseppe", "Piano", "3456789012", "gg@g.gg","giu", "seppe");

        getDbManager().cameraDao().insertCamera(viola);
        getDbManager().cameraDao().insertCamera(bianca);
        getDbManager().ospiteDao().insertOspite(giu);

        giu = getDbManager().ospiteDao().getByNomeAndCognome("Giuseppe", "Piano");

        LocalDateTime ieri = LocalDateTime.of(2019, Month.SEPTEMBER, 2, 10, 0);
        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);

        Prenotazione prenotazioneGiu1 = new Prenotazione(ieri, oggi,true,   "contanti", giu.getId(), "Viola");
        getDbManager().prenotazioneDao().insert(prenotazioneGiu1);

        getDbManager().prenotazioneDao().updateFK("Viola", "Bianca");

        assertEquals(getDbManager().prenotazioneDao().getAllPrenotazioni().get(0).getNomeStanza(), "Bianca");

    }
}
