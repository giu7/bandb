package com.giu7.bandb.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity
public class Ospite {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String nome;
    private String cognome;
    private String telefono;
    private String mail;

    public Ospite(String nome, String cognome, String telefono, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ospite ospite = (Ospite) o;
        return Objects.equals(nome, ospite.nome) &&
                Objects.equals(cognome, ospite.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome);
    }

    @Override
    public String toString() {
        return "Ospite{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
