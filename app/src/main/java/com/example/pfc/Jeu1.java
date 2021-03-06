package com.example.pfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Jeu1 extends AppCompatActivity {

    public ImageButton quitter;
    public ImageButton again;
    public ImageView ComputerChoiceImg;
    public ImageView PlayerChoiceImg;
    public ImageView mainImg;
    public ImageButton rockImg;
    public ImageButton paperImg;
    public ImageButton scissorsImg;
    public TextView player;
    public TextView computer;
    public ImageView playerScore;
    public ImageView computerScore;
    public ImageView round;
    public TextView resultRound;
    public TextView resultFinal;

    private Long score = 0L;
    public int countRound;
    public int scorePlayer;
    public int scoreComputer;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    String TAG="BddInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu1);
        quitter =findViewById(R.id.btn_quitter);
        again =  findViewById(R.id.buttonAgain1);
        rockImg = (ImageButton) findViewById(R.id.imageButtonRock);
        paperImg = (ImageButton) findViewById(R.id.imageButtonPaper);
        scissorsImg = (ImageButton) findViewById(R.id.imageButtonScissors);
        ComputerChoiceImg = (ImageView) findViewById(R.id.imageViewComputerChoice);
        PlayerChoiceImg = (ImageView) findViewById(R.id.imageViewPlayerChoice);
        mainImg = (ImageView) findViewById(R.id.imageViewMain);
        player = (TextView) findViewById(R.id.textViewPlayerGame1);
        computer = (TextView) findViewById(R.id.textViewComputerGame1);
        playerScore =  findViewById(R.id.imageViewScorePlayerGame1);
        computerScore = findViewById(R.id.imageViewScoreComputerGame1);
        round =  findViewById(R.id.imageViewRoundGame1);
        resultRound = (TextView) findViewById(R.id.textViewResultRound);
        resultFinal = (TextView) findViewById(R.id.textViewResultFinal);

        mAuth = FirebaseAuth.getInstance();

        again.setVisibility(View.INVISIBLE);
    }


    public void onClickGame1(View view){ // utilisation d'un switch case pour tout les boutons de l'activit??.

        int playerChoice;//0 = rock ; 1 = paper ; 2 = scissors

        switch (view.getId()){
            case R.id.btn_quitter:
                finish();
                break;
            case R.id.buttonAgain1:
                Intent restart = getIntent();
                finish();
                startActivity(restart);
                break;
            case R.id.imageButtonRock:
                PlayerChoiceImg.setImageResource(R.drawable.rock);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 0;
                checkGame(view,playerChoice);
                break;
            case R.id.imageButtonPaper:
                PlayerChoiceImg.setImageResource(R.drawable.paper);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 1;
                checkGame(view,playerChoice);
                break;
            case R.id.imageButtonScissors:
                PlayerChoiceImg.setImageResource(R.drawable.scissors);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 2;
                checkGame(view,playerChoice);
                break;
        }
    }

    public void checkGame(View view, int Choice){

        Random rand = new Random();
        int computerChoice; //0 = rock ; 1 = paper ; 2 = scissors
        int plyChoice = Choice;//0 = rock ; 1 = paper ; 2 = scissors
        computerChoice = Math.round(rand.nextInt(30)/10);

        countRound++;

        switch(computerChoice){
            case 0 :
                ComputerChoiceImg.setImageResource(R.drawable.rock);
                break;
            case 1 :
                ComputerChoiceImg.setImageResource(R.drawable.paper);
                break;
            case 2 :
                ComputerChoiceImg.setImageResource(R.drawable.scissors);
                break;
        }

        if(plyChoice==computerChoice){
            //Tie
            resultRound.setText("Egalit?? !");
            countRound--;
        }else{

            if(plyChoice==0 && computerChoice==1){
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            }else if(plyChoice==0 && computerChoice==2){
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }

            if(plyChoice==1 && computerChoice== 2){
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            }else if(plyChoice ==1 && computerChoice==0 ){
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }

            if(plyChoice==2 && computerChoice == 0){
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            }else if(plyChoice==2 && computerChoice==1){
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }
        }

        if(scorePlayer==3 || scoreComputer==3){

            resultRound.setText("Fin de la partie");
            if(scorePlayer==3){
                resultFinal.setText("Vous avez gagn?? !");
                UptScore(); // appel la fontion pour ajouter les points dans le champs score de l'utilisateur actuel

            }else{
                resultFinal.setText("Vous avez perdu !");
                Upt1Score(); // m??me chose mais en enlevant des points
            }

            ComputerChoiceImg.setVisibility(View.INVISIBLE);
            PlayerChoiceImg.setVisibility(View.INVISIBLE);
            rockImg.setVisibility(View.INVISIBLE);
            paperImg.setVisibility(View.INVISIBLE);
            scissorsImg.setVisibility(View.INVISIBLE);

            again.setVisibility(View.VISIBLE);
        }

        if(scorePlayer == 1){
            playerScore.setImageResource(R.drawable.onepoint);
        }else if (scorePlayer == 2){
            playerScore.setImageResource(R.drawable.twopoints);
        }else if (scorePlayer == 3){
            playerScore.setImageResource(R.drawable.threepoints);
        }

        if(scoreComputer == 1){
            computerScore.setImageResource(R.drawable.onepoint);
        }else if (scoreComputer == 2){
            computerScore.setImageResource(R.drawable.twopoints);
        }else if (scoreComputer == 3){
            computerScore.setImageResource(R.drawable.threepoints);
        }

        if(countRound == 1){
            round.setImageResource(R.drawable.onepoint);
        }else if (countRound == 2){
            round.setImageResource(R.drawable.twopoints);
        }else if (countRound == 3){
            round.setImageResource(R.drawable.threepoints);
        }else if (countRound == 4){
            round.setImageResource(R.drawable.fourpoints);
        }else if (countRound == 5){
            round.setImageResource(R.drawable.fivepoints);
        }



    }

    public void UptScore() { // si l'utilisateur gagne ajout de 3 pts dans son document

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference DocRef =rootRef.collection("users").document(user); // va dans le document qui a le m??me id que le CurrentUser
        DocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {


            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                        Long userScore = document.getLong("score");
                        score=userScore;
                    score=score+3;
                    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db.collection("users").document(user).update("score", score); // modifie la valeur de la clef "score"

                } else{
                    Log.w(TAG, "Error adding document !!!!!!!!!!!!!!!"); // ces deux fonctions sont les m??mes pour les jeux suivants, avec seulement le nombre de points qui change.

                }
            }
        });

    }

    public void Upt1Score() { // m??me principe

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference DocRef =rootRef.collection("users").document(user);
        DocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {


            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Long userScore = document.getLong("score");
                    score=userScore;
                    score=score-1;
                    if(score<0){
                        score=0L;
                    }
                    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db.collection("users").document(user).update("score", score);

                } else{
                    Log.w(TAG, "Error adding document !!!!!!!!!!!!!!!");

                }
            }
        });

    }






}