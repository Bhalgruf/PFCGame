package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Jeu1 extends AppCompatActivity {

    Button quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu1);

        quitter =(Button)findViewById(R.id.btn_quitter);


    }

    public void onClick5(View view) {

        finish();

    }
}