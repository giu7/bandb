package com.giu7.bandb.services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.giu7.bandb.dao.CameraDao;
import com.giu7.bandb.dao.OspiteDao;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.models.Ospite;

@Database(entities = {Camera.class, Ospite.class}, version = 2)
public abstract class DbManager extends RoomDatabase {
    private static DbManager INSTANCE;

    public abstract CameraDao cameraDao();
    public abstract OspiteDao ospiteDao();

    public static DbManager getInMemoryDatabase(Context context) {
        if (INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), DbManager.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static DbManager getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DbManager.class, "note_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
