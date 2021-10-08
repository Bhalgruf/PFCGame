package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Classement extends AppCompatActivity {

   public ImageButton Retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        Retour=findViewById(R.id.btn_retourClassement);


    }

    public void onClickClassement(View view) {

        switch (view.getId()) {
            case R.id.btn_retourClassement:
                Intent Menu = new Intent(Classement.this, Menu.class);
                startActivity(Menu);
                break;
        }
    }
}