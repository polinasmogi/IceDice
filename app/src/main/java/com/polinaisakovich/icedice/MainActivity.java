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

    private TextView currentScoreView; //The display of the current score (sum of all scores)

    private TextView firstPlayerScoreView; //The display of 1 player total score
    private TextView secondPlayerScoreView; //The display of 2 player total score

    private ImageButton btnDice;
    private Button btnHold;

    private int currentScore = 0; //The sum of all scores of the round

    private boolean player1Turn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnView = findViewById(R.id.txtTurn);
        turnView.setText("PLAYER 1");

        currentScoreView = findViewById(R.id.txtScore);

        firstPlayerScoreView = findViewById(R.id.txtFirstPlayerScore);
        secondPlayerScoreView = findViewById(R.id.txtSecondPlayerScore);

        btnDice = findViewById(R.id.btnDice);
        btnHold = findViewById(R.id.btnHold);

    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnDice:
                int dice = 1 + random.nextInt(7-1);

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

                if (dice != 6 ) {
                    currentScore += dice;
                    currentScoreView.setText(Integer.toString(currentScore));
                } else {
                    currentScore = 0;
                    currentScoreView.setText(Integer.toString(currentScore));
                    player1Turn = !player1Turn;
                }
                break;

            case R.id.btnHold:

                if (player1Turn) {
                    player1.score += currentScore;
                    firstPlayerScoreView.setText(Integer.toString(player1.score));
                    currentScore = 0;
                    currentScoreView.setText(Integer.toString(currentScore));
                    checkForWin();
                    player1Turn = !player1Turn;
                } else {
                    player2.score += currentScore;
                    secondPlayerScoreView.setText(Integer.toString(player2.score));
                    currentScore = 0;
                    currentScoreView.setText(Integer.toString(currentScore));
                    checkForWin();
                    player1Turn = !player1Turn;
                }
                break;

            default:
                break;

        }

        if (player1Turn) {
            turnView.setText("PLAYER 1");
        } else {
            turnView.setText("PLAYER 2");
        }
    }

    private void checkForWin() {
        if (player1.score >= 30) {
            currentScoreView.setText("Player 1 wins");
        } else if (player2.score >= 30) {
            currentScoreView.setText("Player 2 wins");
        }
    }
}
