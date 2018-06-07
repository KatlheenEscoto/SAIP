package com.example.hp.engbook.model;

/**
 * Created by HP on 6/6/2018.
 */

//frase ID INTEGER ,palabra TEXT , ingles TEXT ,portugues ,imagen INTEGER
//db.execSQL("CREATE TABLE nivel (ID INTEGER PRIMARY KEY AUTOINCREMENT,idejercicio INTEGER NOT NULL, idfrase INTEGER NOT NULL)");

public class Frase {
    private Integer ID;
    private String palabra;
    private String ingles;
    private String portugues;
    private int imagen;

    public Frase(Integer ID, String palabra, String ingles, String portugues, int imagen) {
        this.ID = ID;
        this.palabra = palabra;
        this.ingles = ingles;
        this.portugues = portugues;
        this.imagen = imagen;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public String getPortugues() {
        return portugues;
    }

    public void setPortugues(String portugues) {
        this.portugues = portugues;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
