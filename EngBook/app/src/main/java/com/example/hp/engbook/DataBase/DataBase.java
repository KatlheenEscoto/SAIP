package com.example.hp.engbook.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Frase;
import com.example.hp.engbook.model.Nivel;
import com.example.hp.engbook.model.Nivel_Palabra;
import com.example.hp.engbook.model.User;


/**
 * Created by HP on 28/3/2018.
 */

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database1.db";

    public static final String TABLE_NAME1 = "user_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "PASSWORD";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME1+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT UNIQUE ,PASSWORD TEXT NOT NULL )");
        db.execSQL("CREATE TABLE ejercicio (ID INTEGER PRIMARY KEY AUTOINCREMENT,nivel TEXT NOT NULL, intentos INTEGER NOT NULL,imagen INTEGER NOT NULL, fecha TEXT NOT NULL, debloqueado INTEGER NOT NULL,idioma INTEGER NOT NULL, ID_USER INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE frase (ID INTEGER PRIMARY KEY AUTOINCREMENT,palabra TEXT NOT NULL, ingles TEXT NOT NULL,portugues NOT NULL,imagen INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE nivel_palabra (idejercicio INTEGER NOT NULL, idfrase INTEGER NOT NULL, PRIMARY KEY(idejercicio,idfrase))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS ejercicio");
        db.execSQL("DROP TABLE IF EXISTS frase");
        db.execSQL("DROP TABLE IF EXISTS nivel_palabra");
    }



    public boolean insertDataUser(String nombre, String password){
        try {
            boolean valor=false;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,nombre);
            contentValues.put(COL_2,password);
            long resultado = db.insert(TABLE_NAME1,null,contentValues);
            db.close();
            if (resultado==-1){
                valor=false;
            }else{
                valor=true;
            }
            return valor;
        }catch (Exception e){

        }
        return false;
    }


    public boolean updateUser(String id, String nombre, String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,nombre);
            contentValues.put(COL_2,password);
            int result = db.update(TABLE_NAME1,contentValues,"ID = ?",new String[]{id});
            if (result>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){

        }
        return false;
    }

    public Integer deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME1,"ID=?",new String[]{id});
        return i;
    }


    public User getUser(String nom) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from "+ TABLE_NAME1 + " WHERE NAME= '"+nom +"'",null);
            if (res.moveToFirst()){
               User user= new User(res.getInt(0),res.getString(1),res.getString(2));
               return user;
            }
            return null;
        }catch (Exception e){

        }
        return null;
    }

    public boolean getAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c =db.rawQuery("select * from "+TABLE_NAME1,null);
        if(c.moveToFirst()){
            return true;
        }
        return false;
    }
    ///imagen INTEGER NOT NULL, fecha TEXT NOT NULL, debloqueado INTEGER NOT NULL,idioma INTEGER NOT NULL)

    public boolean insertNivel(Nivel nivel){
        try {
            boolean valor=false;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nivel",nivel.getNivel());
            contentValues.put("intentos",nivel.getIntentos());
            contentValues.put("imagen",nivel.getImagen());
            contentValues.put("fecha",nivel.getFecha());
            contentValues.put("debloqueado",nivel.getDesbloqueado());
            contentValues.put("idioma",nivel.getIdioma());
            contentValues.put("ID_USER",nivel.getID_USER());
            long resultado = db.insert("ejercicio",null,contentValues);
            db.close();
            if (resultado==-1){
                valor=false;
            }else{
                valor=true;
            }
            return valor;
        }catch (Exception e){

        }
        return false;
    }


    public boolean insertFrase(Frase frase){
        try {
            boolean valor=false;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("palabra",frase.getPalabra());
            contentValues.put("ingles",frase.getIngles());
            contentValues.put("portugues",frase.getPortugues());
            contentValues.put("imagen",frase.getImagen());
            long resultado = db.insert("frase",null,contentValues);
            db.close();
            if (resultado==-1){
                valor=false;
            }else{
                valor=true;
            }
            return valor;
        }catch (Exception e){

        }
        return false;
    }

    public boolean insertnivel_palabra(Nivel_Palabra nivelPalabra){
        try {
            boolean valor=false;
            if(existeFrase(nivelPalabra.getIdgrase()) && existeEjercicio(nivelPalabra.getIdejercicio())){
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("idejercicio",nivelPalabra.getIdejercicio());
                contentValues.put("idfrase",nivelPalabra.getIdgrase());
                long resultado = db.insert("nivel_palabra",null,contentValues);
                db.close();
                if (resultado==-1){
                    valor=false;
                }else{
                    valor=true;
                }
            }
            return valor;
        }catch (Exception e){

        }
        return false;
    }

    private boolean existeEjercicio(int idejercicio) {
        boolean existe = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from ejercicio where ID ="+idejercicio,null);
        if(c!=null && c.getCount()==1){
            existe=true;
        }
        return existe;
    }

    private boolean existeFrase(int idgrase) {
        boolean existe = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from frase where ID ="+idgrase,null);
        if(c!=null && c.getCount()==1){
            existe=true;
        }
        return existe;
    }

    public boolean desbloquearNivel(String id, int intentos){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("intentos",intentos);
            contentValues.put("imagen", R.drawable.desbloqueado);
            contentValues.put("debloqueado",1);
            int result = db.update("ejercicio",contentValues,"ID = ?",new String[]{id});
            if (result>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){

        }
        return false;
    }

    public Cursor getAllNivel(int id_user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c =db.rawQuery("select * from ejercicio where ID_USER = "+id_user,null);
        return c;
    }

    public Cursor getAllFrases(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c =db.rawQuery("select frase.ID,palabra,ingles,portugues,frase.imagen from frase inner join nivel_palabra on nivel_palabra.idfrase=frase.ID inner join ejercicio on ejercicio.ID = nivel_palabra.idejercicio where nivel_palabra.idejercicio="+id,null);
            return c;
        }catch (Exception e){

        }
        return null;
    }
}
