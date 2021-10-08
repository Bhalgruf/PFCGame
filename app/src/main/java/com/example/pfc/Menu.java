package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton jeu1;
    ImageButton jeu2;
    ImageButton jeu3;
    ImageButton btn_retour1;
    EditText text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        btn_retour1 =  findViewById(R.id.btn_retour1);
        jeu1 = findViewById(R.id.imageButtonGame1);
        jeu2 = findViewById(R.id.imageButtonGame2);
        jeu3 = findViewById(R.id.imageButtonGame3);


    }

    public void onClickMenu(View view) {

        switch (view.getId()) {
            case R.id.btn_retour1:
                finish();
                break;
            case R.id.imageButtonGame1:
                Intent game1 = new Intent(Menu.this, Jeu1.class);
                startActivity(game1);
                break;
            case R.id.imageButtonGame2:
                Intent game2 = new Intent(Menu.this, Jeu2.class);
                startActivity(game2);
                break;
            case R.id.imageButtonGame3:
                Intent game3 = new Intent(Menu.this, Jeu3.class);
                startActivity(game3);
                break;
        }
    }
}