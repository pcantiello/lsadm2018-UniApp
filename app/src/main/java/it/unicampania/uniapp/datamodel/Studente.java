package it.unicampania.uniapp.datamodel;

import java.io.Serializable;

/**
 * Classe per rappresentare uno studente
 */
public class Studente implements Serializable {

    // Attributi
    private String matricola;
    private String cognome;
    private String nome;
    private int cfu;
    private double media;

    public Studente() {
    }

    public Studente(String matricola, String cognome, String nome, int cfu, double media) {
        this.matricola = matricola;
        this.cognome = cognome;
        this.nome = nome;
        this.cfu = cfu;
        this.media = media;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
