package com.giu7.bandb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.giu7.bandb.models.Ospite;

import java.util.List;

@Dao
public interface OspiteDao {
    @Insert
    void insertOspite(Ospite ospite);

    @Query("SELECT * FROM Ospite")
    List<Ospite> getAllOspiti();

    @Query("DELETE FROM Ospite")
    void deleteAllOspiti();

    @Delete
    void deleteOspite(Ospite ospite);

    @Query("SELECT * FROM Ospite WHERE nome LIKE :nome AND cognome LIKE :cognome")
    Ospite getByNomeAndCognome(String nome, String cognome);

    @Query("SELECT * FROM Ospite where id = :id")
    Ospite getById (int id);
}
