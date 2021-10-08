package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Jeu2 extends AppCompatActivity {

    public ImageButton quitter;
    public ImageButton again;
    public ImageView ComputerChoiceImg;
    public ImageView PlayerChoiceImg;
    public ImageView mainImg;
    public ImageButton rockImg;
    public ImageButton paperImg;
    public ImageButton scissorsImg;
    public ImageButton wellImg;
    public TextView player;
    public TextView computer;
    public ImageView playerScore;
    public ImageView computerScore;
    public ImageView round;
    public TextView resultRound;
    public TextView resultFinal;

    public int countRound;
    public int scorePlayer;
    public int scoreComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu2);

        quitter =  findViewById(R.id.button_quitter2);
        again =  findViewById(R.id.buttonAgain2);
        rockImg = (ImageButton) findViewById(R.id.imageButtonRock2);
        paperImg = (ImageButton) findViewById(R.id.imageButtonPaper2);
        wellImg = (ImageButton) findViewById(R.id.imageButtonWell);
        scissorsImg = (ImageButton) findViewById(R.id.imageButtonScissors2);
        ComputerChoiceImg = (ImageView) findViewById(R.id.imageViewComputerChoice2);
        PlayerChoiceImg = (ImageView) findViewById(R.id.imageViewPlayerChoice2);
        mainImg = (ImageView) findViewById(R.id.imageViewMain2);
        player = (TextView) findViewById(R.id.textViewPlayerGame2);
        computer = (TextView) findViewById(R.id.textViewComputerGame2);
        playerScore =  findViewById(R.id.imageViewScorePlayerGame2);
        computerScore =  findViewById(R.id.imageViewScoreComputerGame2);
        round =  findViewById(R.id.imageViewRoundGame2);
        resultRound = (TextView) findViewById(R.id.textViewResultRound2);
        resultFinal = (TextView) findViewById(R.id.textViewResultFinal2);

        again.setVisibility(View.INVISIBLE);
    }

    public void onClickGame2(View view) {

        int playerChoice;//0 = rock ; 1 = paper ; 2 = scissors ; 3 = well

        switch (view.getId()) {
            case R.id.button_quitter2:
                finish();
                break;
            case R.id.buttonAgain2:
                Intent restart = getIntent();
                finish();
                startActivity(restart);
                break;
            case R.id.imageButtonRock2:
                PlayerChoiceImg.setImageResource(R.drawable.rock);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 0;
                checkGame2(view, playerChoice);
                break;
            case R.id.imageButtonPaper2:
                PlayerChoiceImg.setImageResource(R.drawable.paper);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 1;
                checkGame2(view, playerChoice);
                break;
            case R.id.imageButtonScissors2:
                PlayerChoiceImg.setImageResource(R.drawable.scissors);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 2;
                checkGame2(view, playerChoice);
                break;
            case R.id.imageButtonWell:
                PlayerChoiceImg.setImageResource(R.drawable.well);
                mainImg.setVisibility(View.INVISIBLE);
                playerChoice = 3;
                checkGame2(view, playerChoice);
                break;
        }
    }

    public void checkGame2(View view, int Choice) {
        Random rand = new Random();
        int computerChoice; //0 = rock ; 1 = paper ; 2 = scissors ; 3 = well
        int plyChoice = Choice;//0 = rock ; 1 = paper ; 2 = scissors ; 3 = well
        computerChoice = Math.round(rand.nextInt(40)/10);

        countRound++;

        switch (computerChoice) {
            case 0:
                ComputerChoiceImg.setImageResource(R.drawable.rock);
                break;
            case 1:
                ComputerChoiceImg.setImageResource(R.drawable.paper);
                break;
            case 2:
                ComputerChoiceImg.setImageResource(R.drawable.scissors);
                break;
            case 3:
                ComputerChoiceImg.setImageResource(R.drawable.well);
                break;
        }

        if (plyChoice == computerChoice) {
            //Tie
            resultRound.setText("Egalité !");
            countRound--;
        } else {

            if (plyChoice == 0 && (computerChoice == 1 || computerChoice == 3)) { //player ROCK computer PAPER or WELL
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            } else if (plyChoice == 0 && computerChoice == 2) { //player ROCK computer SCISSORS
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }

            if (plyChoice == 1 && computerChoice == 2) { //player PAPER computer SCISSORS
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            } else if (plyChoice == 1 && (computerChoice == 0 || computerChoice ==3)) { //player PAPER computer ROCK or WELL
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }

            if (plyChoice == 2 && computerChoice == 0) { //player SCISSORS computer ROCK
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            } else if (plyChoice == 2 && (computerChoice == 1 || computerChoice == 3)) { //player SCISSORS computer PAPER or WELL
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }

            if (plyChoice == 3 && computerChoice == 1){ //player WELL computer PAPER
                resultRound.setText("L'Ordinateur gagne la manche !");
                scoreComputer++;
            }else if(plyChoice == 3 && (computerChoice == 0 || computerChoice == 2)){ //player WELL computer ROCK or SCISSORS
                resultRound.setText("Vous gagnez la manche !");
                scorePlayer++;
            }


        }

        if (scorePlayer == 3 || scoreComputer == 3) {

            resultRound.setText("Fin de la partie");
            if (scorePlayer == 3) {
                resultFinal.setText("Vous avez gagné !");
            } else {
                resultFinal.setText("Vous avez perdu !");
            }

            ComputerChoiceImg.setVisibility(View.INVISIBLE);
            PlayerChoiceImg.setVisibility(View.INVISIBLE);
            rockImg.setVisibility(View.INVISIBLE);
            paperImg.setVisibility(View.INVISIBLE);
            scissorsImg.setVisibility(View.INVISIBLE);
            wellImg.setVisibility(View.INVISIBLE);

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
}