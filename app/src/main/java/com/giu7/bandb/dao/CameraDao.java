package com.giu7.bandb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.provider.ContactsContract;

import com.giu7.bandb.models.Camera;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CameraDao {
    @Insert(onConflict = REPLACE)
    void insertCamera(Camera camera);

    @Query("SELECT * FROM Camera")
    List<Camera> getAllCamere();

    @Query("DELETE FROM Camera")
    void deleteAllCamere();

    @Delete
    void deleteCamera(Camera camera);

    @Query("SELECT * FROM Camera WHERE nome like :nome")
    Camera getById(String nome);

    @Query("UPDATE Camera SET " +
            "nome = :nome, " +
            "letti = :letti," +
            "tv = :tv," +
            "bagno = :bagno," +
            "prezzo = :prezzo " +
            "WHERE nome = :oldNome")
    void update(String oldNome, String nome, int letti, boolean tv, boolean bagno, double prezzo);
}