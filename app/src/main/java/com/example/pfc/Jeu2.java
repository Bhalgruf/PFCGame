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

    public Button quitter;
    public Button again;
    public ImageView ComputerChoiceImg;
    public ImageView PlayerChoiceImg;
    public ImageView mainImg;
    public ImageButton rockImg;
    public ImageButton paperImg;
    public ImageButton scissorsImg;
    public ImageButton wellImg;
    public TextView player;
    public TextView computer;
    public TextView playerScore;
    public TextView computerScore;
    public TextView round;
    public TextView resultRound;
    public TextView resultFinal;

    public int countRound;
    public int scorePlayer;
    public int scoreComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu2);

        quitter = (Button) findViewById(R.id.button_quitter2);
        again = (Button) findViewById(R.id.buttonAgain2);
        rockImg = (ImageButton) findViewById(R.id.imageButtonRock2);
        paperImg = (ImageButton) findViewById(R.id.imageButtonPaper2);
        wellImg = (ImageButton) findViewById(R.id.imageButtonWell);
        scissorsImg = (ImageButton) findViewById(R.id.imageButtonScissors2);
        ComputerChoiceImg = (ImageView) findViewById(R.id.imageViewComputerChoice2);
        PlayerChoiceImg = (ImageView) findViewById(R.id.imageViewPlayerChoice2);
        mainImg = (ImageView) findViewById(R.id.imageViewMain2);
        player = (TextView) findViewById(R.id.textViewPlayerGame2);
        computer = (TextView) findViewById(R.id.textViewComputerGame2);
        playerScore = (TextView) findViewById(R.id.textViewScorePlayerGame2);
        computerScore = (TextView) findViewById(R.id.textViewScoreComputerGame2);
        round = (TextView) findViewById(R.id.textViewNbrRound);
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
        computerChoice = rand.nextInt(4);

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
            resultRound.setText("Tie !");
            countRound--;
        } else {

            if (plyChoice == 0 && (computerChoice == 1 || computerChoice == 3)) { //player ROCK computer PAPER or WELL
                resultRound.setText("Computer Won !");
                scoreComputer++;
            } else if (plyChoice == 0 && computerChoice == 2) { //player ROCK computer SCISSORS
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 1 && computerChoice == 2) { //player PAPER computer SCISSORS
                resultRound.setText("Computer Won !");
                scoreComputer++;
            } else if (plyChoice == 1 && (computerChoice == 0 || computerChoice ==3)) { //player PAPER computer ROCK or WELL
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 2 && computerChoice == 0) { //player SCISSORS computer ROCK
                resultRound.setText("Computer Won !");
                scoreComputer++;
            } else if (plyChoice == 2 && (computerChoice == 1 || computerChoice == 3)) { //player SCISSORS computer PAPER or WELL
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 3 && computerChoice == 1){ //player WELL computer PAPER
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice == 3 && (computerChoice == 0 || computerChoice == 2)){ //player WELL computer ROCK or SCISSORS
                resultRound.setText("Player Won !");
                scorePlayer++;
            }


        }

        if (scorePlayer == 3 || scoreComputer == 3) {

            resultRound.setText("Game Over");
            if (scorePlayer == 3) {
                resultFinal.setText("Player Won !");
            } else {
                resultFinal.setText("Computer Won !");
            }

            ComputerChoiceImg.setVisibility(View.INVISIBLE);
            PlayerChoiceImg.setVisibility(View.INVISIBLE);
            rockImg.setVisibility(View.INVISIBLE);
            paperImg.setVisibility(View.INVISIBLE);
            scissorsImg.setVisibility(View.INVISIBLE);
            wellImg.setVisibility(View.INVISIBLE);

            again.setVisibility(View.VISIBLE);
        }

        playerScore.setText(String.valueOf(scorePlayer));
        computerScore.setText(String.valueOf(scoreComputer));
        round.setText(String.valueOf(countRound));
    }
}