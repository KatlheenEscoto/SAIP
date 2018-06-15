package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class GraficoAvanceActivity extends AppCompatActivity {

    private int idioma, id_user,idnivel;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_avance);

        db = new DataBase(this);
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");
        idnivel = getIntent().getExtras().getInt("idnivel");

        BarChart chart = (BarChart) findViewById(R.id.barChart);

        ArrayList<BarEntry> BarEntry = new ArrayList<>();

        int res = db.contarExamenes(idioma);
        if(res>0){
                Cursor cursor =  db.avanceExamen(idnivel,id_user,idioma);
                if(cursor!= null && cursor.getCount()>0){
                    while(cursor.moveToNext()){
                        BarEntry.add(new BarEntry(id_user,cursor.getFloat(0)));
                    }
                }else{
                    Toast.makeText(this, "No existe avance.", Toast.LENGTH_SHORT).show();
                }

        }else{
            Toast.makeText(this, "No existen examenes para este idioma.", Toast.LENGTH_SHORT).show();
        }




        BarDataSet dataSet = new BarDataSet(BarEntry, "Porcentaje de avance exitoso del examen "+idnivel);
        dataSet.setValueTextSize(30f);

        ArrayList<String> labels = new ArrayList<>();
        BarData data = new BarData(dataSet);
        chart.setData(data);

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
