package com.example.hp.engbook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.engbook.DataBase.DataBase;

public class InsertarUsuarioActivity extends AppCompatActivity {
    private DataBase db;
    private EditText edtUser, edtPassword;
    String fileName = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuario);
        db = new DataBase(this);
        edtUser = (EditText) findViewById(R.id.input_user);
        edtPassword= (EditText) findViewById(R.id.input_password);
    }

    public void registrar(View v){
        String user = edtUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(user.isEmpty() || password.isEmpty()){
            Toast.makeText(v.getContext(), "Campos vacíos, favor rellenar", Toast.LENGTH_LONG).show();
        }else{
            boolean resultado = db.insertDataUser(user,password);
            if(resultado == true){
                int idUser = db.getIdUser(user, password);
                if(idUser>0){
                    Metodos.insertarNiveles(this, idUser);
                    Metodos.insertarExamenes(this, idUser);
                    Metodos.saveFileG(this, fileName, idUser);
                    Toast.makeText(v.getContext(), "Usuario ingresado con éxito." + idUser, Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(v.getContext(), "Error al ingresar usuario.", Toast.LENGTH_LONG).show();
            }
        }

    }
}
