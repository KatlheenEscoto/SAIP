package com.example.hp.engbook.vistas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;
import com.example.hp.engbook.Metodos;
import com.example.hp.engbook.R;
import com.example.hp.engbook.model.Adapter;
import com.example.hp.engbook.model.ModelAdapter;

import java.util.ArrayList;

public class ExamenActivity extends AppCompatActivity {

    private Adapter adapter;
    private ListView lista;
    private DataBase db;
    private int idioma, id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);

        lista = (ListView)findViewById(R.id.listaNivel);
        db = new DataBase(this);
        idioma = getIntent().getExtras().getInt("idioma");
        id_user = getIntent().getExtras().getInt("id_user");

        final Cursor c = db.getAllExamen(id_user, idioma);
        final ArrayList<ModelAdapter> model = new ArrayList<ModelAdapter>();
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                model.add(new ModelAdapter(c.getInt(0),c.getInt(2),c.getString(1),c.getInt(3)));
            }
        }else
            Toast.makeText(this,"No existen datos.",Toast.LENGTH_LONG).show();

        adapter = new Adapter(this,model);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelAdapter modelo = model.get(i);
                int bloq = modelo.getBloqueado();
                int level = modelo.getId();
                if(bloq==1){
                    if(level==10 || level==20){
                        /*Intent intent1 = new Intent(getApplicationContext(),MusicActivity.class);
                        startActivity(intent1);
                        finish();*/
                        //La ultima leccion de Walter es que aprendan una canción creo, asi que algo relacionado con eso seria el ultimo examen.
                        // simon que canten esa cancion XD

                    }else{
                        Intent intent = new Intent(getApplicationContext(),NivelExamenActivity.class);
                        intent.putExtra("idioma",idioma);
                        intent.putExtra("idnivel",level);
                        intent.putExtra("id_user", id_user);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Examen bloqueado por favor complete el examen anterior",Toast.LENGTH_LONG).show();
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
