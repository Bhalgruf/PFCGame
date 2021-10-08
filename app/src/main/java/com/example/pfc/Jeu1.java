package com.example.pfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    public Button quitter;
    public ImageView ComputerChoiceImg;
    public ImageView PlayerChoiceImg;
    public ImageView mainImg;
    public ImageButton rockImg;
    public ImageButton paperImg;
    public ImageButton scissorsImg;
    public TextView player;
    public TextView computer;
    public TextView playerScore;
    public TextView computerScore;
    public TextView round;
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
        quitter =(Button)findViewById(R.id.btn_quitter);
        rockImg = (ImageButton) findViewById(R.id.imageButtonRock);
        paperImg = (ImageButton) findViewById(R.id.imageButtonPaper);
        scissorsImg = (ImageButton) findViewById(R.id.imageButtonScissors);
        ComputerChoiceImg = (ImageView) findViewById(R.id.imageViewComputerChoice);
        PlayerChoiceImg = (ImageView) findViewById(R.id.imageViewPlayerChoice);
        mainImg = (ImageView) findViewById(R.id.imageViewMain);
        player = (TextView) findViewById(R.id.textViewPlayerGame1);
        computer = (TextView) findViewById(R.id.textViewComputerGame1);
        playerScore = (TextView) findViewById(R.id.textViewScorePlayerGame1);
        computerScore = (TextView) findViewById(R.id.textViewScoreComputerGame1);
        round = (TextView) findViewById(R.id.textViewRoundNbr);
        resultRound = (TextView) findViewById(R.id.textViewResultRound);
        resultFinal = (TextView) findViewById(R.id.textViewResultFinal);

        mAuth = FirebaseAuth.getInstance();
    }


    public void onClickGame1(View view){

        int playerChoice;//0 = rock ; 1 = paper ; 2 = scissors

        switch (view.getId()){
            case R.id.btn_quitter:
                finish();
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
        computerChoice = rand.nextInt(3);

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
            resultRound.setText("Tie !");
            countRound--;
        }else{

            if(plyChoice==0 && computerChoice==1){
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice==0 && computerChoice==2){
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if(plyChoice==1 && computerChoice== 2){
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice ==1 && computerChoice==0 ){
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if(plyChoice==2 && computerChoice == 0){
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice==2 && computerChoice==1){
                resultRound.setText("Player Won !");
                scorePlayer++;
            }
        }

        if(scorePlayer==3 || scoreComputer==3){

            resultRound.setText("Game Over");
            if(scorePlayer==3){
                resultFinal.setText("Player Won !");
                UptScore();


            }else{
                resultFinal.setText("Computer Won !");
                Upt1Score();
            }

            ComputerChoiceImg.setVisibility(View.INVISIBLE);
            PlayerChoiceImg.setVisibility(View.INVISIBLE);
            rockImg.setVisibility(View.INVISIBLE);
            paperImg.setVisibility(View.INVISIBLE);
            scissorsImg.setVisibility(View.INVISIBLE);
        }

        playerScore.setText(String.valueOf(scorePlayer));
        computerScore.setText(String.valueOf(scoreComputer));
        round.setText(String.valueOf(countRound));

    }

    public void UptScore() {

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
                    score=score+3;
                    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db.collection("users").document(user).update("score", score);

                } else{
                    Log.w(TAG, "Error adding document !!!!!!!!!!!!!!!");

                }
            }
        });

    }

    public void Upt1Score() {

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
                    }git
                    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db.collection("users").document(user).update("score", score);

                } else{
                    Log.w(TAG, "Error adding document !!!!!!!!!!!!!!!");

                }
            }
        });

    }






}