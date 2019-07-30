package com.giu7.bandb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.giu7.bandb.models.Prenotazione;

import java.util.List;

@Dao
public interface PrenotazioneDao {

    @Insert
    void insert(Prenotazione prenotazione);

    @Query("SELECT * FROM Prenotazione ORDER BY dataInizio ASC")
    List<Prenotazione> getAllPrenotazioni();

    @Delete
    void delete(Prenotazione prenotazione);

    @Query("DELETE FROM Prenotazione")
    void deleteAllPrenotazioni();

    @Query("SELECT * FROM Prenotazione WHERE id = :id")
    Prenotazione getById(int id);
}
