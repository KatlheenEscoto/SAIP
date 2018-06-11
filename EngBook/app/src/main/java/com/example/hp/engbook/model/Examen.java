package com.example.hp.engbook.model;

public class Examen {
    private int idExamen;
    private String nivelExamen;
    private int imagen;
    private int desbloqueado;
    private int idioma;
    private int idUser;

    public Examen() {

    }

    public Examen(int idExamen, String nivelExamen, int imagen, int desbloqueado, int idioma, int idUser) {
        this.idExamen = idExamen;
        this.nivelExamen = nivelExamen;
        this.imagen = imagen;
        this.desbloqueado = desbloqueado;
        this.idioma = idioma;
        this.idUser = idUser;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getNivelExamen() {
        return nivelExamen;
    }

    public void setNivelExamen(String nivelExamen) {
        this.nivelExamen = nivelExamen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getDesbloqueado() {
        return desbloqueado;
    }

    public void setDesbloqueado(int desbloqueado) {
        this.desbloqueado = desbloqueado;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
