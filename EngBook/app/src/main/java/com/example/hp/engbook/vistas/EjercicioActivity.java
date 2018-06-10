package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Adapter;
import com.example.hp.engbook.model.ModelAdapter;

import java.util.ArrayList;

public class EjercicioActivity extends AppCompatActivity {
    private Adapter adapter;
    private ListView lista;
    private DataBase db;
    private int idioma, id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        lista = (ListView)findViewById(R.id.listaNivel);
        db = new DataBase(this);
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");
        final Cursor c = db.getAllNivel(id_user, idioma);
        final ArrayList<ModelAdapter> model = new ArrayList<ModelAdapter>();
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                model.add(new ModelAdapter(c.getInt(0),c.getInt(3),c.getString(1),c.getInt(5)));
            }
        }else
            Toast.makeText(this,"Lo siento no hay datos",Toast.LENGTH_LONG).show();
        Adapter adapter = new Adapter(this,model);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t1 = (TextView)view.findViewById(R.id.bloq);
                String bl = t1.getHint().toString();
                String idNivel = t1.getHint().toString();
                int idN = Integer.parseInt(idNivel);
                int bloq = Integer.parseInt(bl);
                if(bloq==1){
                    Intent intent = new Intent(getApplicationContext(),NivelActivity.class);
                    TextView t = (TextView)view.findViewById(R.id.mesaje);
                    intent.putExtra("idioma",idioma);
                    intent.putExtra("nivel",t.getText().toString());
                    int level =model.get(i).getId();
                    intent.putExtra("idnivel",level);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Nivel bloqueado por favor complete el nivel anterior",Toast.LENGTH_LONG).show();
                }
            }
        });

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
