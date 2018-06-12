package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class IntentoExamenActivity extends AppCompatActivity {

    private DataBase db;
    private int idioma=0, idnivel=0, id_user=0;
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intento_examen);

        mChart = (LineChart) findViewById(R.id.LineChart);
        db = new DataBase(this);
        idnivel = getIntent().getExtras().getInt("idnivel");
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");


//        mChart.setOnChartGestureListener(MainActivity.this);
        //      mChart.setOnChartValueSelectedListener(MainActivity.this);


        ArrayList<Entry> yValues = new ArrayList<>();

        final Cursor c = db.puntuacionesIntentoExamen(idnivel, id_user, idioma);
        if(c!= null && c.getCount()>0) {
            int i = 1;
            while (c.moveToNext()) {
                yValues.add(new Entry(i, c.getFloat(0)));
                i++;
            }
            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(true);

            LineDataSet set1 = new LineDataSet(yValues, "Examen"+idnivel);

            set1.setFillAlpha(110);
            set1.setColor(Color.RED);
            set1.setLineWidth(2);
            set1.setValueTextSize(15f);
            set1.setCircleColor(Color.BLACK);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);

            mChart.setData(data);

        }else{
            Toast.makeText(this, "Usted NO ha realizado el examen.", Toast.LENGTH_LONG).show();
        }

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
