package com.giu7.bandb.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Build;
import android.support.annotation.NonNull;

import com.giu7.bandb.utils.LocalDateTimeTypeConverters;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity (foreignKeys = {
        @ForeignKey(
                entity = Camera.class,
                parentColumns = "nome",
                childColumns = "nomeStanza"
        ),
        @ForeignKey(
                entity = Ospite.class,
                parentColumns = "id",
                childColumns = "idOspite"
        )
})
public class Prenotazione {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @TypeConverters(LocalDateTimeTypeConverters.class)
    private LocalDateTime dataInizio, dataFine, creationTime;
    private boolean pagato;
    private String metodoPagamento;

    private int idOspite;
    private String nomeStanza;

    public Prenotazione(LocalDateTime dataInizio, LocalDateTime dataFine, boolean pagato, String metodoPagamento, int idOspite, String nomeStanza) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.pagato = pagato;
        this.metodoPagamento = metodoPagamento;
        this.idOspite = idOspite;
        this.nomeStanza = nomeStanza;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.creationTime = LocalDateTime.now();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isPagato() {
        return pagato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public int getIdOspite() {
        return idOspite;
    }

    public void setIdOspite(int idOspite) {
        this.idOspite = idOspite;
    }

    public String getNomeStanza() {
        return nomeStanza;
    }

    public void setNomeStanza(String nomeStanza) {
        this.nomeStanza = nomeStanza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return idOspite == that.idOspite &&
                Objects.equals(dataInizio, that.dataInizio) &&
                Objects.equals(dataFine, that.dataFine) &&
                Objects.equals(nomeStanza, that.nomeStanza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataInizio, dataFine, idOspite, nomeStanza);
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", creationTime=" + creationTime +
                ", pagato=" + pagato +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", idOspite=" + idOspite +
                ", nomeStanza='" + nomeStanza + '\'' +
                '}';
    }
}
