package com.example.hp.engbook.model;

/**
 * Created by HP on 6/6/2018.
 */

//nivel_palabra ID INTEGER ,idejercicio , idfrase INTEGER
public class Nivel_Palabra {
    private int ID;
    private int idejercicio;
    private int idgrase;

    public Nivel_Palabra(int ID, int idejercicio, int idgrase) {
        this.ID = ID;
        this.idejercicio = idejercicio;
        this.idgrase = idgrase;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdejercicio() {
        return idejercicio;
    }

    public void setIdejercicio(int idejercicio) {
        this.idejercicio = idejercicio;
    }

    public int getIdgrase() {
        return idgrase;
    }

    public void setIdgrase(int idgrase) {
        this.idgrase = idgrase;
    }
}
