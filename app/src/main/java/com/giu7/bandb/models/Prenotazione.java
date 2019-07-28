package com.giu7.bandb.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Build;
import android.support.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity (foreignKeys = {
        @ForeignKey(
                entity = Camera.class,
                parentColumns = "nome",
                childColumns = "nome_stanza"
        ),
        @ForeignKey(
                entity = Ospite.class,
                parentColumns = "id",
                childColumns = "id_ospite"
        )
})
public class Prenotazione {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private LocalDateTime data_inizio, data_fine, creation_time;
    private boolean pagato;
    private String metodo_pagamento;

    private int id_ospite;
    private String nome_stanza;

    public Prenotazione(LocalDateTime data_inizio, LocalDateTime data_fine, boolean pagato, String metodo_pagamento, int id_ospite, String nome_stanza) {
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.pagato = pagato;
        this.metodo_pagamento = metodo_pagamento;
        this.id_ospite = id_ospite;
        this.nome_stanza = nome_stanza;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.creation_time = LocalDateTime.now();
        }
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDateTime data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDateTime getData_fine() {
        return data_fine;
    }

    public void setData_fine(LocalDateTime data_fine) {
        this.data_fine = data_fine;
    }

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public boolean isPagato() {
        return pagato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public int getId_ospite() {
        return id_ospite;
    }

    public void setId_ospite(int id_ospite) {
        this.id_ospite = id_ospite;
    }

    public String getNome_stanza() {
        return nome_stanza;
    }

    public void setNome_stanza(String nome_stanza) {
        this.nome_stanza = nome_stanza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return id_ospite == that.id_ospite &&
                Objects.equals(data_inizio, that.data_inizio) &&
                Objects.equals(data_fine, that.data_fine) &&
                Objects.equals(nome_stanza, that.nome_stanza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data_inizio, data_fine, id_ospite, nome_stanza);
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", creation_time=" + creation_time +
                ", pagato=" + pagato +
                ", metodo_pagamento='" + metodo_pagamento + '\'' +
                ", id_ospite=" + id_ospite +
                ", nome_stanza='" + nome_stanza + '\'' +
                '}';
    }
}
