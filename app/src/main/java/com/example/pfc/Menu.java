package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button btn_retour1;
    Button Jeu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        btn_retour1 =(Button)findViewById(R.id.btn_retour1);
        Jeu1 =(Button)findViewById(R.id.btn_jeu1);

    }

    public void onClick1(View view) {
        Intent intent = new Intent(Menu.this, Jeu1.class);
        startActivity(intent);

    }

    public void onClick4(View view) {

        finish();

    }
}