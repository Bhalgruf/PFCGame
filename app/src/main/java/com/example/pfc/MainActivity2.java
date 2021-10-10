package com.example.pfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 123;
    ImageButton btn_con;
    ImageButton btn_ins;
    EditText editTextEmail,editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_con =findViewById(R.id.imageButtonConnexion);
        btn_ins = findViewById(R.id.btn_ins);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.Password);
        mAuth = FirebaseAuth.getInstance();


    }

    public void onClickMain(View view) {
        switch (view.getId()){
            case R.id.imageButtonConnexion:
                startSignInActivity();
                break;

            case R.id.btn_ins:
                Intent intent2 = new Intent(MainActivity2.this, Inscription.class);
                startActivity(intent2);
                break;

        }
    }

    private void startSignInActivity() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){ // champs non vide
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ // si l'adresse mail match avec le login d'un utilisateur dans Firestore
            editTextEmail.setError("please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.length()<5){ // condition pas nécessaire
            editTextPassword.setError("min length should be 5 characters!");
            editTextPassword.requestFocus();
            return;
        }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){ // si l'authentification est faite, l'utilisateur est connecté et il peut jouer (lancement de l'activité)

                            startActivity(new Intent(MainActivity2.this, Menu.class));

                    }else{

                        Toast.makeText(MainActivity2.this,"Failed to login!",Toast.LENGTH_LONG).show();
                    }
                }
            });

    }


}