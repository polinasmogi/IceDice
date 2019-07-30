package com.polinaisakovich.icedice;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    private TextView diceView;

    private TextView score;

    private TextView yourScore;

    private Button btnTake;

    private int currentScore = 0;
    private int points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceView = findViewById(R.id.txtDice);

        score = findViewById(R.id.txtScore);

        yourScore = findViewById(R.id.txtFirstPlayerScore);

        btnTake = findViewById(R.id.btnHold);

    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnStart:
                int dice = 1 + random.nextInt(7-1);
                diceView.setText(Integer.toString(dice));

                if (dice != 6 ) {
                    currentScore += dice;
                    score.setText(Integer.toString(currentScore));
                } else {
                    currentScore = 0;
                    score.setText(Integer.toString(currentScore));
                    score.setText("You lose");
                }
                break;

            case R.id.btnHold:
                points += currentScore;
                currentScore = 0;
                score.setText(Integer.toString(currentScore));
                yourScore.setText(Integer.toString(points));
                break;

            default:
                break;

        }


    }
}
