package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Classement extends AppCompatActivity {

    private Button Retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        Retour=(Button) findViewById(R.id.btn_retourMenu);


    }

    public void onClickClassement(View view) {

        switch (view.getId()) {
            case R.id.btn_retourMenu:
                Intent Menu = new Intent(Classement.this, Menu.class);
                startActivity(Menu);
                break;
        }
    }
}