package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class Inscription extends AppCompatActivity {

    Button btn_retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        btn_retour =(Button)findViewById(R.id.btn_retour2);
    }


    public void onClick3(View view) {

       finish();

    }
}