package com.example.pfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class Classement extends AppCompatActivity {

    private Button Retour;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        Retour = (Button) findViewById(R.id.btn_retourMenu);
        GetScore();

    }

    public void onClickClassement(View view) {

        switch (view.getId()) {
            case R.id.btn_retourMenu:
                Intent Menu = new Intent(Classement.this, Menu.class);
                startActivity(Menu);
                break;
        }
    }

    public void GetScore() {
        int i =1;
        final String[] ClassementJ = {""};

        db.collection("users").orderBy("score", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())){
                                Long userScore = documentSnapshot.getLong("score");
                                String name= documentSnapshot.getString("first");
                                ClassementJ[0]= ClassementJ[0]+i+" - "+name +" : "+ userScore +"\n";
                            }
                        }
                    }
                });
        text = (EditText) findViewById(R.id.editTextTextMultiLine);
        text.setText(ClassementJ[0]);
    }
}