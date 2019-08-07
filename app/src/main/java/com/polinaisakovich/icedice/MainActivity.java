package com.polinaisakovich.icedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    Player player1 = new Player();
    Player player2 = new Player();

    private TextView turnView;
    private boolean player1Turn = true;

    private TextView currentScoreView; //The display of the current score (sum of all scores)
    private int currentScore = 0; //The sum of all scores of the round

    private TextView firstPlayerScoreView; //The display of 1 player total score
    private TextView secondPlayerScoreView; //The display of 2 player total score

    private ImageButton btnDice;
    private Button btnHoldAndNewRound;

    private static final int SCORE_TO_WIN = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnView = findViewById(R.id.txtTurn);

        currentScoreView = findViewById(R.id.txtScore);

        firstPlayerScoreView = findViewById(R.id.txtFirstPlayerScore);
        secondPlayerScoreView = findViewById(R.id.txtSecondPlayerScore);

        btnDice = findViewById(R.id.btnDice);
        btnDice.setEnabled(false);
        btnHoldAndNewRound = findViewById(R.id.btnStartRound);

    }

    public void onClick(View view) {
        doAction(view.getId());
    }

    private void doAction(int id) {
        switch (id)  {
            case R.id.btnDice:
                actionDice();
                break;
            case R.id.btnHold:
                actionHold();
                break;
            case R.id.btnStartRound:
                actionStartRound();
                break;
            case R.id.btnNewGame:
                actionNewGame();
                break;
        }
    }

    private void actionDice() {
        int dice = 1 + random.nextInt(7-1);
        updateBtnDice(dice);

        if (dice != 6 ) {
            currentScore += dice;
            currentScoreView.setText(Integer.toString(currentScore));
        } else {
            currentScore = 0;
            currentScoreView.setText(Integer.toString(currentScore));
            changeButton();
            player1Turn = !player1Turn;
        }
    }

    public void updateBtnDice(int dice) {
        switch (dice) {
            case 1:
                btnDice.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                btnDice.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                btnDice.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                btnDice.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                btnDice.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                btnDice.setImageResource(R.drawable.dice_6);
                break;
        }
    }

    public void actionHold() {
        if (player1Turn) {
            player1.score += currentScore;
            firstPlayerScoreView.setText(Integer.toString(player1.score));
            currentScore = 0;
            currentScoreView.setText(Integer.toString(currentScore));
            displayTurn();
            checkForWin();
            player1Turn = !player1Turn;
            changeButton();
        } else {
            player2.score += currentScore;
            secondPlayerScoreView.setText(Integer.toString(player2.score));
            currentScore = 0;
            currentScoreView.setText(Integer.toString(currentScore));
            displayTurn();
            checkForWin();
            player1Turn = !player1Turn;
            changeButton();
        }
    }

    public void actionStartRound() {
        btnDice.setEnabled(true);
        btnDice.setImageResource(R.drawable.dice_start);
        btnHoldAndNewRound.setId(R.id.btnHold);
        btnHoldAndNewRound.setText(R.string.all_hold);
        displayTurn();
    }

    public void actionNewGame() {
        player1.score = 0;
        player2.score = 0;
        currentScore = 0;
        btnDice.setImageResource(R.drawable.dice_start);
        btnDice.setEnabled(true);
        btnHoldAndNewRound.setEnabled(true);
        firstPlayerScoreView.setText(Integer.toString(player1.score));
        secondPlayerScoreView.setText(Integer.toString(player2.score));
        player1Turn = true;
        displayTurn();
    }

    private void displayTurn() {
        if (player1Turn) {
            turnView.setText(R.string.player_1_name);
        } else {
            turnView.setText(R.string.player_2_name);
        }
    }

    private void checkForWin() {
        if (player1.score >= SCORE_TO_WIN) {
            turnView.setText(R.string.player_1_wins);
            currentScoreView.setText("");
            btnDice.setEnabled(false);
            btnHoldAndNewRound.setEnabled(false);
        } else if (player2.score >= SCORE_TO_WIN) {
            turnView.setText(R.string.player_2_wins);
            currentScoreView.setText("");
            btnDice.setEnabled(false);
            btnHoldAndNewRound.setEnabled(false);
        }
    }

    private void changeButton() {
        btnHoldAndNewRound.setId(R.id.btnStartRound);
        btnDice.setEnabled(false);
        btnHoldAndNewRound.setText(R.string.all_start_round);
    }
}
