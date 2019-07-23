package com.giu7.bandb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.provider.ContactsContract;

import com.giu7.bandb.models.Camera;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CameraDao {
    @Insert(onConflict = REPLACE)
    void insertCamera(Camera camera);

    @Query("SELECT * FROM Camera")
    List<Camera> loadAllCamere();

    @Query("DELETE FROM Camera")
    void deleteAll();

    @Delete
    void deleteCamera(Camera camera);
}