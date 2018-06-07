package com.example.hp.engbook.model;

/**
 * Created by HP on 4/6/2018.
 */

public class ModelAdapter {
    private int id;
    private int img;
    private String name;
    private int bloqueado;

    public ModelAdapter(int id, int img, String name, int bloqueado) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.bloqueado = bloqueado;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

