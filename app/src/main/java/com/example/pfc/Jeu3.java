package com.example.pfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Jeu3 extends AppCompatActivity {

    public Button quitter;
    public Button again;
    public Button rules;
    public Button rock;
    public Button paper;
    public Button scissors;
    public Button fire;
    public Button water;
    public Button air;
    public Button sponge;
    public ImageView ComputerChoiceImg;
    public ImageView PlayerChoiceImg;
    public TextView player;
    public TextView computer;
    public TextView playerScore;
    public TextView computerScore;
    public TextView round;
    public TextView resultRound;
    public TextView resultFinal;

    public int countRound = 0;
    public int scorePlayer = 0;
    public int scoreComputer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu3);

        quitter = (Button) findViewById(R.id.buttonRetour3);
        again = (Button) findViewById(R.id.buttonAgain3);
        rules = (Button) findViewById(R.id.buttonRules);
        rock = (Button) findViewById(R.id.buttonRock3);
        paper = (Button) findViewById(R.id.buttonPaper3);
        scissors = (Button) findViewById(R.id.buttonScissors3);
        fire = (Button) findViewById(R.id.buttonFire);
        water = (Button) findViewById(R.id.buttonWater);
        air = (Button) findViewById(R.id.buttonAir);
        sponge = (Button) findViewById(R.id.buttonSponge);
        ComputerChoiceImg = (ImageView) findViewById(R.id.imageViewComputerChoice3);
        PlayerChoiceImg = (ImageView) findViewById(R.id.imageViewPlayerChoice3);
        player = (TextView) findViewById(R.id.textViewPlayerLabel3);
        computer = (TextView) findViewById(R.id.textViewComputerLabel3);
        playerScore = (TextView) findViewById(R.id.textViewScorePlayer3);
        computerScore = (TextView) findViewById(R.id.textViewScoreComputer3);
        round = (TextView) findViewById(R.id.textViewRoundNbr3);
        resultRound = (TextView) findViewById(R.id.textViewResultRoundLabel);
        resultFinal = (TextView) findViewById(R.id.textViewResultFinal3);

        again.setVisibility(View.INVISIBLE);
    }

    public void onClickGame3(View view) {

        int playerChoice;//0 = rock ; 1 = paper ; 2 = scissors ; 3 = fire ; 4 = water ; 5 = air ; 6 = sponge

        switch (view.getId()) {
            case R.id.buttonRetour3:
                finish();
                break;
            case R.id.buttonRules:
                Intent rules = new Intent(this, Rules.class);
                startActivity(rules);
                break;
            case R.id.buttonAgain3:
                Intent restart = getIntent();
                finish();
                startActivity(restart);
                break;
            case R.id.buttonRock3:
                PlayerChoiceImg.setImageResource(R.drawable.rock);
                playerChoice = 0;
                checkGame3(view, playerChoice);
                break;
            case R.id.buttonPaper3:
                PlayerChoiceImg.setImageResource(R.drawable.paper);
                playerChoice = 1;
                checkGame3(view, playerChoice);
                break;
            case R.id.buttonScissors3:
                PlayerChoiceImg.setImageResource(R.drawable.scissors);
                playerChoice = 2;
                checkGame3(view, playerChoice);
                break;
            case R.id.buttonFire:
                PlayerChoiceImg.setImageResource(R.drawable.fire);
                playerChoice = 3;
                checkGame3(view, playerChoice);
                break;
            case R.id.buttonWater:
                PlayerChoiceImg.setImageResource(R.drawable.water);
                playerChoice = 4;
                checkGame3(view,playerChoice);
                break;
            case R.id.buttonAir:
                PlayerChoiceImg.setImageResource(R.drawable.air);
                playerChoice = 5;
                checkGame3(view,playerChoice);
                break;
            case R.id.buttonSponge:
                PlayerChoiceImg.setImageResource(R.drawable.sponge);
                playerChoice = 6;
                checkGame3(view,playerChoice);
                break;
        }
    }

    public void checkGame3(View view, int Choice) {
        Random rand = new Random();
        int computerChoice; //0 = rock ; 1 = paper ; 2 = scissors ; 3 = fire ; 4 = water ; 5 = air ; 6 = sponge
        int plyChoice = Choice;//0 = rock ; 1 = paper ; 2 = scissors ; 3 = fire ; 4 = water ; 5 = air ; 6 = sponge
        computerChoice = rand.nextInt(7);

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
                ComputerChoiceImg.setImageResource(R.drawable.fire);
                break;
            case 4:
                ComputerChoiceImg.setImageResource(R.drawable.water);
                break;
            case 5:
                ComputerChoiceImg.setImageResource(R.drawable.air);
                break;
            case 6:
                ComputerChoiceImg.setImageResource(R.drawable.sponge);
                break;
        }

        if (plyChoice == computerChoice) {
            //Tie
            resultRound.setText("Tie !");
            countRound--;
        } else {

            if (plyChoice == 0 && (computerChoice == 1 || computerChoice == 4 || computerChoice ==5)) { //player ROCK computer PAPER or AIR or WATER  (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            } else if (plyChoice == 0 && (computerChoice == 2 || computerChoice == 3 || computerChoice == 6)) { //player ROCK computer SCISSORS or FIRE or SPONGE (Player WIN)
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 1 && (computerChoice == 2 || computerChoice == 3 || computerChoice == 6)) { //player PAPER computer SCISSORS or FIRE or SPONGE (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            } else if (plyChoice == 1 && (computerChoice == 0 || computerChoice == 4 || computerChoice == 5)) { //player PAPER computer ROCK or WATER or AIR (Player WIN)
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 2 && (computerChoice == 0 || computerChoice == 3 || computerChoice == 4)) { //player SCISSORS computer ROCK or FIRE or WATER (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            } else if (plyChoice == 2 && (computerChoice == 1 || computerChoice == 5 || computerChoice == 6)) { //player SCISSORS computer PAPER or AIR or SPONGE (Player WIN)
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 3 && (computerChoice == 0 || computerChoice == 4 || computerChoice == 5)){ //player FIRE computer ROCK or WATER or AIR (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice == 3 && (computerChoice == 1 || computerChoice == 2 || computerChoice == 6)){ //player FIRE computer PAPER or SCISSORS or SPONGE (Player WIN)
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 4 && (computerChoice == 1 || computerChoice == 5 || computerChoice == 6)){ //player WATER computer PAPER or AIR or SPONGE (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice == 4 && (computerChoice == 0 || computerChoice == 2 || computerChoice == 3)){ //player WATER computer ROCK or SCISSORS or FIRE (Player WIN)
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 5 && (computerChoice == 1 || computerChoice == 2 || computerChoice == 6)){ //player AIR computer PAPER or SCISSORS or SPONGE (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice == 5 && (computerChoice == 0 || computerChoice == 3 || computerChoice == 4)){ //player AIR computer ROCK or FIRE or WATER (Player WIN)
                resultRound.setText("Player Won !");
                scorePlayer++;
            }

            if (plyChoice == 6 && (computerChoice == 0 || computerChoice == 2 || computerChoice == 3)){ //player SPONGE computer ROCK or SCISSORS or FIRE (Player LOSE)
                resultRound.setText("Computer Won !");
                scoreComputer++;
            }else if(plyChoice == 6 && (computerChoice == 1 || computerChoice == 4 || computerChoice == 5)){ //player SPONGE computer PAPER or WATER or AIR (Player WIN)
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
            rock.setVisibility(View.INVISIBLE);
            paper.setVisibility(View.INVISIBLE);
            scissors.setVisibility(View.INVISIBLE);
            fire.setVisibility(View.INVISIBLE);
            water.setVisibility(View.INVISIBLE);
            air.setVisibility(View.INVISIBLE);
            sponge.setVisibility(View.INVISIBLE);

            again.setVisibility(View.VISIBLE);
        }

        playerScore.setText(String.valueOf(scorePlayer));
        computerScore.setText(String.valueOf(scoreComputer));
        round.setText(String.valueOf(countRound));
    }
}