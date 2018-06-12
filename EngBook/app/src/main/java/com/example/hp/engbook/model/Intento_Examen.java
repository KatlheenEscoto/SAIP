package com.example.hp.engbook.model;

public class Intento_Examen {
    private int idIntento;
    private float puntuacion;
    private int idioma;
    private int idExamen;
    private int idUser;

    public Intento_Examen() {
    }

    public Intento_Examen(int idIntento, float puntuacion, int idioma, int idExamen, int idUser) {
        this.idIntento = idIntento;
        this.puntuacion = puntuacion;
        this.idioma = idioma;
        this.idExamen = idExamen;
        this.idUser = idUser;
    }

    public int getIdIntento() {
        return idIntento;
    }

    public void setIdIntento(int idIntento) {
        this.idIntento = idIntento;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
