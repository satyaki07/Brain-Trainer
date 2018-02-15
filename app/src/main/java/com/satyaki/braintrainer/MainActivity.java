package com.satyaki.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    TextView highScoreTextView;
    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    Button playAgainButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAns;
    int numOfQuestions = 0;
    int score = 0;
    int highScore = 0;


    public void playAgain(View view) {

        score = 0;
        numOfQuestions = 0;
        timerTextView.setText("30S");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        highScoreTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        generateQuestion();

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "S");
            }

            @Override
            public void onFinish() {
                if(score > highScore) {
                    highScore = score;
                    highScoreTextView.setText("New Highscore: " + Integer.toString(highScore) + "/" + Integer.toString(numOfQuestions));
                }
                else{
                    highScoreTextView.setText("Highscore: " + Integer.toString(highScore));
                }
                highScoreTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0S");
                resultTextView.setText("Your score " + Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();


    }


    public void generateQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAns = rand.nextInt(4);
        answers.clear();

        int incorrectAns;
        for(int i=0;i<4;i++){

            if(i == locationOfCorrectAns)
                answers.add(a+b);
            else{
                incorrectAns = rand.nextInt(41);
                while(incorrectAns == a+b)
                    incorrectAns = rand.nextInt(41);

                answers.add(incorrectAns);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){

            score++;
            resultTextView.setText("Correct!");

        }
        else{
            resultTextView.setText("Wrong!");
        }

        numOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        generateQuestion();

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(ConstraintLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);

        resultTextView = (TextView)findViewById(R.id.resultTextView);

        pointsTextView = (TextView)findViewById(R.id.pointsTextView);



    }
}
