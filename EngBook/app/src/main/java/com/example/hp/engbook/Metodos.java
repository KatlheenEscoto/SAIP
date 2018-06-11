package com.example.hp.engbook;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Frase;
import com.example.hp.engbook.model.Nivel;
import com.example.hp.engbook.model.Nivel_Palabra;

/**
 * Created by HP on 29/3/2018.
 */

public class Metodos {
    public static void vibrateSimple(Context context){
        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(1000);
    }

    public static void vibratePatter(Context context){
        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        long [] patron = {0,500,300,1000,500,300,200,100,50};
        v.vibrate(patron,2);
    }

    public static void vibrateShort(Context context){
        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(3000);
    }


    public static void notificacion(Context context,String texto, String title,String autor){
        Bitmap icon1= BitmapFactory.decodeResource(context.getResources(), R.drawable.libro);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(texto);
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.setSummaryText(autor);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context).setSmallIcon(R.drawable.libro)
                .setContentTitle(title)
                .setContentText(texto)
                .setLargeIcon(icon1)
                .setStyle(bigTextStyle);
        //Obteniendo la instancia
        NotificationManager notificationManager= (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

    }



    public static void saveFileG(Context ctx,String fileName,int idUser){
        SharedPreferences pref = ctx.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name","logeado");
        editor.putInt("idUser",idUser);
        editor.commit();
    }

    public static void saveFileS(Context cxt,String fileName){
        SharedPreferences pref = cxt.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name"," ");
        editor.putInt("idUser",0);
        editor.commit();

        Intent intent = new Intent(cxt,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("salir",true);
        cxt.startActivity(intent);
    }


    public static void insertarNiveles(Context context,int idUser){
        DataBase db = new DataBase(context);
        db.insertNivel(new Nivel(1,"nivel 1",0,R.drawable.desbloqueado,"04/06/2018",1,1,idUser));
        db.insertNivel(new Nivel(2,"nivel 2",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(3,"nivel 3",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(4,"nivel 4",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(5,"nivel 5",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(6,"nivel 6",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(7,"nivel 7",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(8,"nivel 8",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(9,"nivel 9",0,R.drawable.bloqueado,"04/06/2018",2,1,idUser));
        db.insertNivel(new Nivel(10,"nivel 10",0,R.drawable.desbloqueado,"04/06/2018",1,1,idUser));

        db.insertNivel(new Nivel(11,"nivel 1",0,R.drawable.desbloqueado,"04/06/2018",1,2,idUser));
        db.insertNivel(new Nivel(12,"nivel 2",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(13,"nivel 3",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(14,"nivel 4",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(15,"nivel 5",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(16,"nivel 6",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(17,"nivel 7",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(18,"nivel 8",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(19,"nivel 9",0,R.drawable.bloqueado,"04/06/2018",2,2,idUser));
        db.insertNivel(new Nivel(20,"nivel 10",0,R.drawable.desbloqueado,"04/06/2018",1,2,idUser));


        //nivel 1
        db.insertFrase(new Frase(1,"escuela","school","escola",R.drawable.school));
        db.insertFrase(new Frase(2,"casa","home","lar",R.drawable.house));
        db.insertFrase(new Frase(3,"carro","car","carrinho",R.drawable.carro));
        db.insertFrase(new Frase(4,"perro","dog","cachorro",R.drawable.perro));
        db.insertFrase(new Frase(5,"libro","book","book",R.drawable.libro2));

        //nivel 2
        db.insertFrase(new Frase(6,"1 (uno)","one","um",R.drawable.uno));
        db.insertFrase(new Frase(7,"2 (dos)","two","dois",R.drawable.dos));
        db.insertFrase(new Frase(8,"3 (tres)","three","tres",R.drawable.tres));
        db.insertFrase(new Frase(9,"4 (cuatro)","four","quatro",R.drawable.catro));
        db.insertFrase(new Frase(10,"5 (cinco)","five","cinco",R.drawable.cinco));
        db.insertFrase(new Frase(11,"6 (seis)","six","seis",R.drawable.seis));
        db.insertFrase(new Frase(12,"7 (siete)","seven","sete",R.drawable.siete));
        db.insertFrase(new Frase(13,"8 (ocho)","eight","oito",R.drawable.ocho));
        db.insertFrase(new Frase(14,"9 (nueve)","nine","nove",R.drawable.neve));
        db.insertFrase(new Frase(15,"10 (diez)","ten","dez",R.drawable.diez));
        inciarLevel1(1,db);
        inciarLevel1(11,db);
    }

    private static void inciarLevel1(int id, DataBase db){
        db.insertnivel_palabra(new Nivel_Palabra(1,id,1));
        db.insertnivel_palabra(new Nivel_Palabra(2,id,2));
        db.insertnivel_palabra(new Nivel_Palabra(3,id,3));
        db.insertnivel_palabra(new Nivel_Palabra(4,id,4));
        db.insertnivel_palabra(new Nivel_Palabra(5,id,5));
        id=id+1;
        db.insertnivel_palabra(new Nivel_Palabra(1,id,6));
        db.insertnivel_palabra(new Nivel_Palabra(2,id,7));
        db.insertnivel_palabra(new Nivel_Palabra(3,id,8));
        db.insertnivel_palabra(new Nivel_Palabra(4,id,9));
        db.insertnivel_palabra(new Nivel_Palabra(5,id,10));
        db.insertnivel_palabra(new Nivel_Palabra(1,id,11));
        db.insertnivel_palabra(new Nivel_Palabra(2,id,12));
        db.insertnivel_palabra(new Nivel_Palabra(3,id,13));
        db.insertnivel_palabra(new Nivel_Palabra(4,id,14));
        db.insertnivel_palabra(new Nivel_Palabra(5,id,15));
    }

}
