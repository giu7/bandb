package com.giu7.bandb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.giu7.bandb.models.Prenotazione;

import java.time.LocalDateTime;
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

    @Query("SELECT * FROM Prenotazione WHERE idOspite = :id")
    List<Prenotazione> getPrenotazioniByIdOspite(int id);

    @Query("select count(*) as num_conflicts from prenotazione where nomeStanza = :camera and (dataInizio <= :checkOut) and (dataFine >= :checkIn)")
    int getConflictsForDates(String camera, LocalDateTime checkIn, LocalDateTime checkOut);

    @Query("UPDATE Prenotazione SET nomeStanza = :newNome WHERE nomeStanza = :oldNome")
    void updateFK(String oldNome, String newNome);
}
