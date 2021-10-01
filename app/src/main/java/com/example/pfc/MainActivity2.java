package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 123;
    Button btn_con;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_con =(Button)findViewById(R.id.btn_co);
        text = (EditText) findViewById(R.id.editTextTextPersonName);
        mAuth = FirebaseAuth.getInstance();


    }

    public void onClickMain(View view) {
        switch (view.getId()){
            case R.id.btn_co:
                  Intent intent = new Intent(MainActivity2.this, Menu.class);
               // startSignInActivity();
                startActivity(intent);
                break;

            case R.id.btn_ins:
                Intent intent2 = new Intent(MainActivity2.this, Inscription.class);
                String text1 = text.getText().toString();
                intent2.putExtra("Mon_text",text1);
                startActivity(intent2);
                break;

        }
    }


       // private void startSignInActivity(){

       //      Choose authentication providers
        //    List<AuthUI.IdpConfig> providers =
         //           Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        //     Launch the activity
         //   startActivityForResult(
          //          AuthUI.getInstance()
          //                  .createSignInIntentBuilder()
          //                  .setAvailableProviders(providers)
           //                 .setIsSmartLockEnabled(false, true)
          //                  .build(),
          //          RC_SIGN_IN);
      //  }

}