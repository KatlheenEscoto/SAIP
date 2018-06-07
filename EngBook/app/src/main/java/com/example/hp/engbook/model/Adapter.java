package com.example.hp.engbook.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.engbook.R;

import java.util.ArrayList;

/**
 * Created by HP on 4/6/2018.
 */

public class Adapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<ModelAdapter> items;

    public Adapter(Activity activity,ArrayList<ModelAdapter> items) {
        this.activity=activity;
        this.items=items;
    }


    @Override
    public int getCount() {
        return items.size();
    }
    public void clear(){
        items.clear();
    }

    public void addAll(ArrayList<ModelAdapter> p){
        for(int i=0;i<p.size();i++){
            items.add(p.get(i));
        }
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView==null){
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.listview_item_image,null);
        }
        ModelAdapter f = items.get(position);
        TextView texto = (TextView)v.findViewById(R.id.mesaje);
        TextView texto2 = (TextView)v.findViewById(R.id.bloq);
        texto.setText(f.getName());
        texto.setHint(""+f.getId());
        int bloqueo = f.getBloqueado();
        switch (bloqueo){
            case 1:
                texto2.setText("Desbloqueado");
                break;
            case 2:
                texto2.setText("Bloqueado");
                break;
            default:
                texto2.setText("         ");
                break;
        }
        texto2.setHint(""+bloqueo);
        ImageView imges = (ImageView)v.findViewById(R.id.imagenes);
        imges.setImageResource(f.getImg());

        return v;
    }
}
