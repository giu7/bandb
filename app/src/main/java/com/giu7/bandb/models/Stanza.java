package com.giu7.bandb.models;

import java.util.Objects;

public class Stanza {

    private String nome;
    private int letti;
    private boolean tv, bagno;
    private double prezzo;

    public Stanza(String nome, int letti, boolean tv, boolean bagno, double prezzo) {
        this.nome = nome;
        this.letti = letti;
        this.tv = tv;
        this.bagno = bagno;
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
        Stanza stanza = (Stanza) o;
        return Objects.equals(nome, stanza.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Stanza{" +
                "nome='" + nome + '\'' +
                ", letti=" + letti +
                ", tv=" + tv +
                ", bagno=" + bagno +
                ", prezzo=" + prezzo +
                '}';
    }
}
