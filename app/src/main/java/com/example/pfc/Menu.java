package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btn_retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_retour =(Button)findViewById(R.id.btn_co);
        Intent intent=getIntent();
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //finish();
            }

        });
    }
}