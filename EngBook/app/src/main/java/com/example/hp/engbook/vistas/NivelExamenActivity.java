package com.example.hp.engbook.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Intento_Examen;

public class NivelExamenActivity extends AppCompatActivity {
    private DataBase db;
    //Unicamente para probar gráficos.
    private EditText edtPuntaje;
    private int nivel=0, idioma=0, id_user =0;
    private int intento = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_examen);

        db = new DataBase(this);
        edtPuntaje = (EditText) findViewById(R.id.puntuacion);

        nivel = getIntent().getExtras().getInt("idnivel");
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");

    }

    public void insertarPuntaje(View v){
        String puntuacionStr = edtPuntaje.getText().toString();
        if(puntuacionStr.isEmpty()){
            Toast.makeText(v.getContext(), "Rellene campo.", Toast.LENGTH_SHORT).show();
        }else{
            float puntuacion = Float.parseFloat(puntuacionStr);
            if(puntuacion>10 || puntuacion<0){
                Toast.makeText(v.getContext(), "Error en el rango. Es de 0 a 10.", Toast.LENGTH_SHORT).show();
            }else{
                intento +=1;
                boolean res = db.insertarIntentoExamen(new Intento_Examen(intento, puntuacion, nivel, id_user));
                if(res==true){
                    Toast.makeText(v.getContext(), "Puntuación Insertada.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(), "Puntuación NO Insertada.", Toast.LENGTH_SHORT).show();
                }
                if(puntuacion>=6.0){
                    nivel +=1;
                    db.desbloquearExamen(nivel);
                    Toast.makeText(v.getContext(), "Siguiente examen desbloqueado.", Toast.LENGTH_SHORT).show();
                    nivel -=1;
                }else{
                    Toast.makeText(v.getContext(), "No paso el examen.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void limpiarTexto(View v){
        edtPuntaje.setText("");
    }
}
