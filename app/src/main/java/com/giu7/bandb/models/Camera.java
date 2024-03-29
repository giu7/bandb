package com.giu7.bandb.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

@Entity
public class Camera {

    @PrimaryKey
    @NonNull
    private String nome;
    private int letti;
    private boolean tv, bagno;
    private String fotoUrl;
    private double prezzo;

    public Camera(String nome, int letti, boolean tv, boolean bagno, String fotoUrl, double prezzo) {
        this.nome = nome;
        this.letti = letti;
        this.tv = tv;
        this.bagno = bagno;
        this.fotoUrl = fotoUrl;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLetti() {
        return letti;
    }

    public void setLetti(int letti) {
        this.letti = letti;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isBagno() {
        return bagno;
    }

    public void setBagno(boolean bagno) {
        this.bagno = bagno;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        return new EqualsBuilder()
                .append(letti, camera.letti)
                .append(tv, camera.tv)
                .append(bagno, camera.bagno)
                .append(prezzo, camera.prezzo)
                .append(nome, camera.nome)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(nome)
                .append(letti)
                .append(tv)
                .append(bagno)
                .append(prezzo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Camera{" +
                "nome='" + nome + '\'' +
                ", letti=" + letti +
                ", tv=" + tv +
                ", bagno=" + bagno +
                ", prezzo=" + prezzo +
                ", fotoUrl=" + fotoUrl +

                '}';
    }
}
