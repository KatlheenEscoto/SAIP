package com.example.hp.engbook.model;

/**
 * Created by HP on 5/6/2018.
 */

public class Nivel {
    ///ID INTEGER nivel TEXT intentos INTEGER imagen INTEGER  fecha TEXT debloqueado INTEGER idioma INTEGER
    private int ID;
    private String nivel;
    private int intentos;
    private int imagen;
    private String fecha;
    private int desbloqueado;
    private int idioma;
    private int ID_USER;

    public Nivel(int ID, String nivel, int intentos, int imagen, String fecha, int desbloqueado, int idioma, int ID_USER) {
        this.ID = ID;
        this.nivel = nivel;
        this.intentos = intentos;
        this.imagen = imagen;
        this.fecha = fecha;
        this.desbloqueado = desbloqueado;
        this.idioma = idioma;
        this.ID_USER = ID_USER;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
}
