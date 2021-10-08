package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {
    Button btn_retour1,Jeu1,Jeu2,Class;
    EditText text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        btn_retour1 = (Button) findViewById(R.id.btn_retour1);
        Jeu1 = (Button) findViewById(R.id.btn_jeu1);
        Jeu2 = (Button) findViewById(R.id.btn_jeu2);
        Class = (Button) findViewById(R.id.btn_class);


    }

    public void onClickMenu(View view) {

        switch (view.getId()) {
            case R.id.btn_retour1:
                finish();
                break;
            case R.id.btn_jeu1:
                Intent game1 = new Intent(Menu.this, Jeu1.class);
                startActivity(game1);
                break;
            case R.id.btn_jeu2:
                Intent game2 = new Intent(Menu.this, Jeu2.class);
                startActivity(game2);
                break;
            case R.id.btn_class:
                Intent Class = new Intent(Menu.this, Classement.class);
                startActivity(Class);
                break;
        }
    }
}