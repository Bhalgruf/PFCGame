package com.example.pfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class Inscription extends AppCompatActivity {

    private TextView banner, registerUser;
    private EditText editText_nom, editTextText_prénom, editText_date, editText_login, editText_mdp;
    private Button  btn_enregistrer;
    private RadioGroup sexe;
    private FirebaseAuth mAuth;

    Button btn_retour;
    String TAG="BddInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        btn_retour =(Button)findViewById(R.id.btn_retour2);
        mAuth = FirebaseAuth.getInstance();

        editText_nom=(EditText) findViewById(R.id.editText_nom);
        editTextText_prénom=(EditText) findViewById(R.id.editTextText_prénom);
        editText_date=(EditText) findViewById(R.id.editText_date);
        editText_login=(EditText) findViewById(R.id.editText_login);
        editText_mdp=(EditText) findViewById(R.id.editText_mdp);
        sexe=(RadioGroup) findViewById(R.id.radiog);
        btn_enregistrer=(Button) findViewById(R.id.btn_enregistrer);




    }

    public void onClickMain(View view) {
        switch (view.getId()){
            case R.id.btn_retour2:
                finish();
                break;

            case R.id.btn_enregistrer:
                registerUser();
                break;

        }
    }

    private void registerUser() {
        String email = editText_login.getText().toString().trim();
        String mdp = editText_mdp.getText().toString().trim();
        String nom = editText_nom.getText().toString().trim();
        String prénom = editTextText_prénom.getText().toString().trim();
        String date = editText_date.getText().toString().trim();
        String tonsexe= sexe.toString().trim();



        if(mdp.isEmpty()){
            editText_mdp.setError("PassWord is required!");
            editText_mdp.requestFocus();
            return;
        }

        if(nom.isEmpty()){
            editText_nom.setError("LastName is required!");
            editText_nom.requestFocus();
            return;
        }

        if(prénom.isEmpty()){
            editTextText_prénom.setError("FirstName is required!");
            editTextText_prénom.requestFocus();
            return;
        }

        if(date.isEmpty()){
            editText_date.setError("Age is required!");
            editText_date.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_login.setError("please provide valid email");
            editText_login.requestFocus();
            return;
        }

        if(mdp.length()<5){
            editText_mdp.setError("min length should be 5 characters!");
            editText_mdp.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            User user = new User (nom,prénom,date,email,tonsexe);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Inscription.this , "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Inscription.this,"Failed to register!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Inscription.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                            }
                        }
                    });



    }



}