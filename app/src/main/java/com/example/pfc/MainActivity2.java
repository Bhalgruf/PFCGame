package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    Button btn_con;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_con =(Button)findViewById(R.id.btn_co);
        text = (EditText) findViewById(R.id.editTextTextPersonName);


    }
    public void onClick1(View view) {

                Intent intent = new Intent(MainActivity2.this, Menu.class);
                String text1 = text.getText().toString();
                intent.putExtra("Mon_text",text1);
                startActivity(intent);

             }

    public void onClick2(View view) {

        Intent intent = new Intent(MainActivity2.this, Inscription.class);
        String text1 = text.getText().toString();
        intent.putExtra("Mon_text",text1);
        startActivity(intent);

    }

}