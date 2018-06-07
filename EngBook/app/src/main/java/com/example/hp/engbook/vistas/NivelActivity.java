package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Frase;

import java.util.ArrayList;
import java.util.List;

public class NivelActivity extends AppCompatActivity {
    private TextView palabra,resp;
    private ImageView img;
    private List<Frase> frases;
    private int i=0,fin=0,idioma=0,signivel=0,nivel=0,intentos=0;
    private Cursor c;
    private DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        db = new DataBase(this);
        palabra = (TextView)findViewById(R.id.palabra);
        resp =(TextView)findViewById(R.id.idioma);
        img = (ImageView)findViewById(R.id.img);
        nivel = getIntent().getExtras().getInt("idnivel");
        idioma = getIntent().getExtras().getInt("idioma");
        frases=new ArrayList<Frase>();
        c = db.getAllFrases(nivel);

        Toast.makeText(this,"Nivel: "+nivel,Toast.LENGTH_LONG).show();

        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                frases.add(new Frase(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4)));
            }
        }else {
            frases.add(new Frase(1,"NO hay palabra","NONE","N",R.drawable.ues1));
        }
        fin = frases.size()-1;

        if(fin>1) insertar();

    }

    private void insertar() {
        Frase f = frases.get(i);
        palabra.setText(f.getPalabra());
        img.setImageResource(f.getImagen());
        if(idioma==1){
            resp.setText(f.getIngles());
        }else if(idioma==2){
            resp.setText(f.getPortugues());
        }else{
            resp.setText("No hay traduccion en este momento");
        }
    }

    private void siguiente(){
        if(i<fin){
            i=i+1;
        }else if(i >= fin){
            signivel=1;
            intentos+=1;
            i=0;
        }
        insertar();
    }

    private void anterior(){
        if(i>0){
            i=i-1;
        }else if(i <= 0){
            i=fin;
        }
        insertar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_salir,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.opcion:
                salir();
                break;
            case R.id.opcion2:
                Intent i = new Intent(this, DondeEstoyActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    private void salir() {
        Metodos.saveFileS(this,"log");
        System.exit(0);
    }

    public void siguiente(View view) {
        siguiente();
    }

    public void anterior(View view) {
        anterior();
    }

    public void listo(View view) {
        if(signivel==1){
            int sig=nivel+1;
            db.desbloquearNivel(""+sig,intentos);
        }
        finish();
    }
}
