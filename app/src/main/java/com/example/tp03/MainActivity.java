package com.example.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.tp03.SharedHelper.sha256;
public class MainActivity extends AppCompatActivity {

    EditText _txtLogin, _txtPassword;
    Button _btnConnection;
    SQLiteDatabase bdcircuits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtLogin = (EditText) findViewById(R.id.txtLogin);
        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _btnConnection = (Button) findViewById(R.id.btnConnection);

        //création de la base de données
        bdcircuits = openOrCreateDatabase("Circuits",MODE_PRIVATE,null);
        //Création de la table users
        bdcircuits.execSQL("CREATE TABLE IF NOT EXISTS USERS (login varchar primary key, password varchar);");
        //si la table users est vide alors ajouter admin avec mdp: 123
        SQLiteStatement s = bdcircuits.compileStatement("select count(*) from users;");
        long c=s.simpleQueryForLong();
        if(c==0){
            bdcircuits.execSQL("insert into users(login,password) values (?,?)", new String[]{"admin", sha256("123")});
        }
        _btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLogin = _txtLogin.getText().toString();
                String strPwd = _txtPassword.getText().toString();
                Cursor cur= bdcircuits.rawQuery("select password from users where login=?", new String[]{strLogin});
                try {
                    cur.moveToFirst();
                    String p=cur.getString(0);
                    if (p.equals(sha256(strPwd))){
                        Toast.makeText(getApplicationContext(), "Bienvenue "+ strLogin, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),MesCircuitsActivity.class);
                        startActivity(i);
                    }else{
                        _txtLogin.setText("");
                        _txtPassword.setText("");
                        Toast.makeText(getApplicationContext(), "Echec de connexion ", Toast.LENGTH_LONG).show();

                    }

                }catch (Exception e){
                    _txtLogin.setText("");
                    _txtPassword.setText("");
                    Toast.makeText(getApplicationContext(), "Utilisateur inexistant ", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


    }


}