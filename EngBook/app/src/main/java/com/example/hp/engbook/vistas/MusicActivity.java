package com.example.hp.engbook.vistas;

import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.engbook.R;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer media;
    private FloatingActionButton btnplay;
    private FloatingActionButton btnstop;
    private FloatingActionButton btnlisto;
    private TextView txt;
    private RadioGroup grupo;
    private View viewLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        media= MediaPlayer.create(getApplicationContext(),R.raw.itsmy);
        btnstop = (FloatingActionButton)findViewById(R.id.btnstop);
        btnplay = (FloatingActionButton)findViewById(R.id.btnplay);
        btnlisto = (FloatingActionButton)findViewById(R.id.listo);
        txt = (TextView)findViewById(R.id.letra);
        grupo=(RadioGroup)findViewById(R.id.grupoRadio);
        insertarEspa();
        LayoutInflater layoutInflater = getLayoutInflater();
        viewLayout = layoutInflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast));

    }

    private void insertarEspa() {
        txt.setText("Esta no es una canción para los corazones rotos (o abatidos),\n" +
                "no es una oración silenciosa para los que han perdido la fé.\n" +
                "Y yo no voy a ser solo una cara entre la multitud,\n" +
                "vas a escuchar mi voz cuando grite bien alto.\n" +
                "\n" +
                "Es mi vida,\n" +
                "es ahora o nunca,\n" +
                "no voy a vivir para siempre (ain't),\n" +
                "solo quiero vivir mientras siga vivo.\n" +
                "\n" +
                "Es mi vida.\n" +
                "Mi corazón es como una autopista sin peaje,\n" +
                "como dijo Frankie (Sinatra): \"Lo hice a mi manera\" (my way)\n" +
                "Tan sólo quiero vivir mientras siga vivo,\n" +
                "porque es mi vida.\n" +
                "\n" +
                "Esta es por los que se mantuvieron en pie,\n" +
                "para Tommy y Gina que nunca se echaron atrás.\n" +
                "El mañana cada vez es mas difícil, no cometas errores.\n" +
                "Ni siquiera la suerte tiene suerte, \n" +
                "tienes que buscarte tus propios golpes de suerte.\n" +
                "\n" +
                "Es mi vida.\n" +
                "Es ahora o nunca,\n" +
                "no voy a vivir para siempre.\n" +
                "solo quiero vivir mientras siga vivo.\n" +
                "\n" +
                "Es mi vida.\n" +
                "Mi corazón es como una autopista sin peaje.\n" +
                "Como dijo Frankie: \"Lo hice a mi manera\".\n" +
                "Tan solo quiero vivir mientras siga vivo,\n" +
                "porque es mi vida.\n" +
                "\n" +
                "Será mejor que te mantengas erguido\n" +
                "cuando te reclamen,\n" +
                "no te doblegues, no te rindas,\n" +
                "cariño, no te eches atrás.\n" +
                "\n" +
                "Es mi vida.\n" +
                "Es ahora o nunca,\n" +
                "no voy a vivir para siempre.\n" +
                "solo quiero vivir mientras siga vivo.\n" +
                "\n" +
                "Es mi vida.\n" +
                "Mi corazón es como una autopista sin peaje.\n" +
                "Como dijo Frankie: \"Lo hice a mi manera\".\n" +
                "Tan solo quiero vivir mientras siga vivo,\n" +
                "(bis)" +
                "\n\n\n\n\n");
    }


    public void play(View view) {
        try{
            if(media.isPlaying()){
                media.pause();
                btnplay.setImageResource(R.drawable.play);
            }
            else{
                media.start();
                btnplay.setImageResource(R.drawable.pause);
            }
        }catch (Exception e){

        }
    }

    public void stop(View view) {
        try {
            media.stop();
            btnplay.setImageResource(R.drawable.play);
            media.prepare();
            Toast toast = Toast.makeText(this,"Toast:Gravity.TOP",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setView(viewLayout);
            toast.show();
            Thread.sleep(1000);
            finish();
        }catch (Exception e){

        }
    }

    public void sig(View view) {
        int check = grupo.getCheckedRadioButtonId();
        switch (check){
            case R.id.radioIngles:
                insertarIngles();
                break;
            case R.id.radioEspa:
                insertarEspa();
                break;
            case R.id.radioPort:
                insertarPort();
                break;
        }
    }

    private void insertarPort() {
        txt.setText("\n" +
                "Esta não é uma música para os quebrantados de coração\n" +
                "Nenhuma oração silenciosa pela fé que partiu\n" +
                "E eu não vou ser apenas um cara no meio da multidão\n" +
                "Você vai ouvir minha voz quando eu gritar bem alto\n" +
                "\n" +
                "é a minha vida\n" +
                "É agora ou nunca\n" +
                "Eu não vou viver para sempre\n" +
                "Eu só quero viver enquanto estou vivo\n" +
                "\n" +
                "é a minha vida\n" +
                "Meu coração é como uma estrada aberta\n" +
                "Como Frankie disse: \"Eu fiz do meu jeito\"\n" +
                "Eu só quero viver enquanto estou vivo\n" +
                "Porque é a minha vida\n" +
                "\n" +
                "Isto é para aqueles que resistiram\n" +
                "Para Tommy e Gina que nunca desistiram\n" +
                "Amanhã está ficando mais difícil, não se engane\n" +
                "Sorte nem tem sorte\n" +
                "Tem que fazer suas próprias pausas\n" +
                "\n" +
                "é a minha vida\n" +
                "E é agora ou nunca\n" +
                "Eu não vou viver para sempre\n" +
                "Eu só quero viver enquanto estou vivo\n" +
                "\n" +
                "é a minha vida\n" +
                "Meu coração é como uma estrada aberta\n" +
                "Como Frankie disse: \"Eu fiz do meu jeito\"\n" +
                "Eu só quero viver enquanto estou vivo\n" +
                "Porque é a minha vida\n" +
                "\n" +
                "É melhor você ficar alto\n" +
                "Quando eles estão chamando você\n" +
                "Não se curve, não quebre\n" +
                "Baby, não recue\n" +
                "\n" +
                "é a minha vida\n" +
                "É agora ou nunca\n" +
                "Porque eu não vou viver para sempre\n" +
                "Eu só quero viver enquanto estou vivo\n" +
                "\n" +
                "é a minha vida\n" +
                "Meu coração é como uma estrada aberta\n" +
                "Como Frankie disse: \"Eu fiz do meu jeito\"\n" +
                "Eu só quero viver enquanto estou vivo\n" +
                "(bis)" +
                "\n\n\n\n\n\n");
    }

    private void insertarIngles() {
        txt.setText("This ain't a song for the brokenhearted\n" +
                "No silent prayer for the faith departed\n" +
                "And I ain't going to be just a face in the crowd\n" +
                "You're going to hear my voice when I shout it out loud\n" +
                "\n" +
                "It's my life\n" +
                "It's now or never\n" +
                "I ain't going to live forever\n" +
                "I just want to live while I'm alive\n" +
                "\n" +
                "It's my life\n" +
                "My heart is like an open highway\n" +
                "Like Frankie said, \"I did it my way\"\n" +
                "I just want to live while I'm alive\n" +
                "Because it's my life\n" +
                "\n" +
                "This is for the ones who stood their ground\n" +
                "For Tommy and Gina who never backed down\n" +
                "Tomorrow's getting harder, make no mistake\n" +
                "Luck ain't even lucky \n" +
                "Got to make your own breaks\n" +
                "\n" +
                "It's my life\n" +
                "And it's now or never\n" +
                "I ain't going to live forever\n" +
                "I just want to live while I'm alive\n" +
                "\n" +
                "It's my life\n" +
                "My heart is like an open highway\n" +
                "Like Frankie said, \"I did it my way\"\n" +
                "I just want to live while I'm alive\n" +
                "Because it's my life\n" +
                "\n" +
                "You better stand tall\n" +
                "When they're calling you out\n" +
                "Don't bend, don't break\n" +
                "Baby, don't back down\n" +
                "\n" +
                "It's my life\n" +
                "It's now or never\n" +
                "Because I ain't going to live forever\n" +
                "I just want to live while I'm alive\n" +
                "\n" +
                "It's my life\n" +
                "My heart is like an open highway\n" +
                "Like Frankie said, \"I did it my way\"\n" +
                "I just want to live while I'm alive\n" +
                "(bis)" +
                "\n\n\n\n\n");
    }
}
