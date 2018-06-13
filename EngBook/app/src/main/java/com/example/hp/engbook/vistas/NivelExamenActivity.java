package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Frase;
import com.example.hp.engbook.model.Intento_Examen;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NivelExamenActivity extends AppCompatActivity {
    private DataBase db;
    //Unicamente para probar gráficos.
    private TextView palabra,resp;
    private EditText edtPuntaje, editRespuesta;
    private int nivel=0, idioma=0, id_user =0, fin=0, i=0,signivel=0, intentos=0;
    private int intento = 0;
    private Cursor c;
    private List<Frase> frases;
    private ImageView img;
    private TextToSpeech tts;
    public int numPalabras =0, aciertos=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_examen);

        db = new DataBase(this);


        nivel = getIntent().getExtras().getInt("idnivel");
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");
        resp =(TextView)findViewById(R.id.idioma);
        img = (ImageView)findViewById(R.id.img);
        editRespuesta = (EditText)findViewById(R.id.editRespuesta);
        nivel = getIntent().getExtras().getInt("idnivel");
        idioma = getIntent().getExtras().getInt("idioma");
        frases=new ArrayList<Frase>();
        c = db.getAllFrases(nivel);

        tts = new TextToSpeech(this,OnInit);

        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                frases.add(new Frase(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4)));
            }
        }else {
            frases.add(new Frase(1,"NO hay palabra","NONE","N",R.drawable.ues1));
        }
        fin = frases.size()-1;


        if(fin>1)
        {
            insertar();
        }
        numPalabras+=fin;

    }

    public void puntaje(View view)
    {

        String palabraIdioma = resp.getText().toString().toLowerCase().trim();
        String palabraRespuesta = editRespuesta.getText().toString().toLowerCase().trim();
        if(palabraRespuesta.isEmpty())
        {
            Toast.makeText(this, "Campo Obligatorio", Toast.LENGTH_SHORT).show();
        }
        else {
            if (palabraRespuesta.compareTo(palabraIdioma) == 0) {
                aciertos++;
                Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    TextToSpeech.OnInitListener OnInit = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub
            if (TextToSpeech.SUCCESS==status){
                if(idioma==1){
                    tts.setLanguage(Locale.US);
                }else if(idioma==2){
                    tts.setLanguage(new Locale("por","PTR"));
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "TTS no disponible",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void insertarPuntaje(){
            float puntuacion = (10/numPalabras)*aciertos ;
            if(puntuacion>10 || puntuacion<0){
                Toast.makeText(this, "Error en el rango. Es de 0 a 10.", Toast.LENGTH_SHORT).show();
            }else{
                intento +=1;
                boolean res = db.insertarIntentoExamen(new Intento_Examen(intento, puntuacion,idioma ,nivel, id_user));
                if(res==true){
                    Toast.makeText(this, "Puntuación Insertada.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Puntuación NO Insertada.", Toast.LENGTH_SHORT).show();
                }
                if(puntuacion>=6.0){
                    nivel +=1;
                    db.desbloquearExamen(nivel);
                    Toast.makeText(this, "Siguiente examen desbloqueado.", Toast.LENGTH_SHORT).show();
                    nivel -=1;
                }else{
                    Toast.makeText(this, "No paso el examen.", Toast.LENGTH_SHORT).show();
                }
            }

    }

    private void insertar() {
        Frase f = frases.get(i);
        img.setImageResource(f.getImagen());
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        img.startAnimation(animation);
        if(idioma==1){
            resp.setText(f.getIngles());
        }else if(idioma==2){
            resp.setText(f.getPortugues());
        }else{
            resp.setText("No hay traduccion en este momento");
        }
    }
    private void siguiente(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left);
        img.startAnimation(animation);
        if(fin>0){
            if(i<fin){
                i=i+1;
            }else if(i >= fin){
                signivel=1;
                intentos+=1;
                i=0;
            }
            insertar();
            editRespuesta.setText("");
        }else
            Metodos.vibrateSimple(this);
    }

    private void anterior(){
        if(fin>0){
            if(i>0){
                i=i-1;
            }else if(i <= 0){
                i=fin;
            }
            insertar();
        }
        else Metodos.vibrateShort(this);
    }

    public void limpiarTexto(View v){
        edtPuntaje.setText("");
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
            if(fin>3){
                int sig=nivel+1;
                db.desbloquearNivel(""+sig,intentos);
            }else{
                Toast.makeText(this,"Lo sentimos asi no podra subir de nivel",Toast.LENGTH_LONG).show();
                Metodos.vibrateShort(this);
            }
        }
        insertarPuntaje();
        finish();
    }

    public void escuchar(View view) {
        tts.speak(resp.getText().toString(), TextToSpeech.QUEUE_ADD, null);
    }
}
