package com.example.pfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;


public class Inscription extends AppCompatActivity {


    private EditText editText_nom, editTextText_prénom, editText_date, editText_login, editText_mdp;
    private TextView textSexe;
    private Button  btn_enregistrer;
    private RadioGroup sexe;
    private RadioButton Homme,Femme, Autre;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatePickerDialog picker;

    ImageButton btn_retour;
    ImageButton enregistrer;
    String TAG="BddInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        btn_retour =findViewById(R.id.btn_retour2);
        mAuth = FirebaseAuth.getInstance();

        editText_nom=(EditText) findViewById(R.id.editText_nom);
        editTextText_prénom=(EditText) findViewById(R.id.editTextText_prénom);
        editText_date=(EditText) findViewById(R.id.editText_date);
        editText_login=(EditText) findViewById(R.id.editText_login);
        editText_mdp=(EditText) findViewById(R.id.editText_mdp);
        textSexe= (TextView) findViewById(R.id.textView_sexe);
        sexe=(RadioGroup) findViewById(R.id.radiog);
        enregistrer= findViewById(R.id.btn_enregistrer);
        Homme =(RadioButton) findViewById(R.id.radioButton_H);
        Femme =(RadioButton) findViewById(R.id.radioButton_F);
        Autre =(RadioButton) findViewById(R.id.radioButton_autre);







    }

    public void onClickMain(View view) {
        switch (view.getId()){
            case R.id.btn_retour2:
                finish();
                break;

            case R.id.btn_enregistrer:
                registerUser();
                finish();
                break;

//            case R.id.editText_date:
//                Calendar cldr = Calendar.getInstance();
//                int day = cldr.get(Calendar.DAY_OF_MONTH);
//                int month = cldr.get(Calendar.MONTH);
//                int year = cldr.get(Calendar.YEAR);
//
//                picker = new DatePickerDialog(Register.this);

        }
    }

    private void registerUser() {

        String email = editText_login.getText().toString().trim();
        String mdp = editText_mdp.getText().toString().trim();
        String nom = editText_nom.getText().toString().trim();
        String prénom = editTextText_prénom.getText().toString().trim();
        String date = editText_date.getText().toString().trim();




        if(!Homme.isChecked()&&!Femme.isChecked()&&!Autre.isChecked()){
            textSexe.setError("Le champ sexe est requis!");
            textSexe.requestFocus();
            return;
        }


        if(mdp.isEmpty()){
            editText_mdp.setError("Un mot de passe est requis!");
            editText_mdp.requestFocus();
            return;
        }

        if(nom.isEmpty()){
            editText_nom.setError("Votre nom est requis!");
            editText_nom.requestFocus();
            return;
        }

        if(prénom.isEmpty()){
            editTextText_prénom.setError("Votre prénom est requis!");
            editTextText_prénom.requestFocus();
            return;
        }

        if(date.isEmpty()){
            editText_date.setError("Votre âge est requis!");
            editText_date.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_login.setError("Veuillez fournir un email correct");
            editText_login.requestFocus();
            return;
        }

        if(mdp.length()<5){
            editText_mdp.setError("La taille minimum est de 5 caractères!");
            editText_mdp.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.w(TAG, "OK c'est bien tout est bon ça marche");
                            // Sign in success, update UI with the signed-in user's information
                            //  Log.d(TAG, "createUserWithEmail:success");
                            Map<String, Object> user = new HashMap<>();
                            if(Homme.isChecked()){
                                user.put("sexe","Homme");
                            }
                            if(Femme.isChecked()){
                                user.put("sexe","Femme");
                            }
                            if(Autre.isChecked()){
                                user.put("sexe","Autre");
                            }
                            user.put("first",prénom);
                            user.put("last", nom);
                            user.put("email",email);
                            user.put("date",date);
                            user.put("score",0);

                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            db.collection("users").document(userId)
                                    .set(user)
                                    //addOnSuccessListener(new OnSuccessListener<Void>() {
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void avoid) {
                                           // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                            Toast.makeText(Inscription.this , "Enregistré avec succès!", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user.
                            /// Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Inscription.this, "Echec de l'authentification.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });


    }



}