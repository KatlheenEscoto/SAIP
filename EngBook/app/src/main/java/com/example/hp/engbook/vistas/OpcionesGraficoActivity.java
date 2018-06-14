package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;

public class OpcionesGraficoActivity extends AppCompatActivity {

    private int idioma, id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_grafico);
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");
    }

    public void intentosGraficos(View v){
        Intent intent = new Intent(getApplicationContext(),EstadisticasActivity.class);
        intent.putExtra("id_user",id_user);
        intent.putExtra("idioma",idioma);
        startActivity(intent);
    }

    public void generalGrafico(View v){
        Intent intent = new Intent(getApplicationContext(),EstadisticaGeneralActivity.class);
        intent.putExtra("id_user",id_user);
        intent.putExtra("idioma",idioma);
        startActivity(intent);
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
}
