package com.example.hp.engbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.hp.engbook.model.Adapter;
import com.example.hp.engbook.vistas.AvanceActivity;
import com.example.hp.engbook.vistas.EstadisticasActivity;
import com.example.hp.engbook.model.ModelAdapter;
import com.example.hp.engbook.vistas.DondeEstoyActivity;
import com.example.hp.engbook.vistas.EjercicioActivity;
import com.example.hp.engbook.vistas.ExamenActivity;
import com.example.hp.engbook.vistas.MapsActivity;
import com.example.hp.engbook.vistas.OpcionesGraficoActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private String[] menu = {"Ejercicios","Examenes","Avance","Estadisticas","Configuracion"};
<<<<<<< HEAD
    private Class[] clases = {EjercicioActivity.class,ExamenActivity.class,AvanceActivity.class, OpcionesGraficoActivity.class,EjercicioActivity.class};
=======
    private Class[] clases = {EjercicioActivity.class,ExamenActivity.class,EjercicioActivity.class, OpcionesGraficoActivity.class,MapsActivity.class};
>>>>>>> a66441233a67031754236c8c462bf05cb7feb894
    private ListView lista;
    private RadioGroup radios;
    private int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView)findViewById(R.id.listaMenu);
        radios =(RadioGroup)findViewById(R.id.radioGrup);
        ArrayList<ModelAdapter>  model = new ArrayList<ModelAdapter>();
        model.add(new ModelAdapter(1,R.drawable.estudiando,"Practicas",0));
        model.add(new ModelAdapter(1,R.drawable.exam,"Examenes",0));
        model.add(new ModelAdapter(1,R.drawable.cheque,"Avance",0));
        model.add(new ModelAdapter(1,R.drawable.estadisticas,"Estadisticas",0));
        model.add(new ModelAdapter(1,R.drawable.config,"Configuracion",0));
        Adapter adapter = new Adapter(this,model);
        lista.setAdapter(adapter);
        id_user = getIntent().getExtras().getInt("idUser");
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int i1 =0;
                switch (radios.getCheckedRadioButtonId()){
                    case R.id.ingles:
                        i1=1;
                        break;
                    case R.id.port:
                        i1=2;
                        break;
                }
                Intent intent = new Intent(getApplicationContext(),clases[i]);
                intent.putExtra("id_user",id_user);
                intent.putExtra("idioma",i1);
                startActivity(intent);
            }
        });

        if( getIntent().getBooleanExtra("salir", false)){
            finish();
            return;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_salir,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.opcion:
                salir();
                break;
            case R.id.opcion2:
                Intent i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    private void salir() {
        Metodos.saveFileS(this,"log");
        System.exit(0);
    }

}
