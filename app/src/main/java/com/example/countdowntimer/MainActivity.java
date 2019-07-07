package com.example.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long StartTime_inMillis = 1800000;
    private TextView mViewCountdown;
    private Button StartPause;
    private Button  ResetButton;
    private CountDownTimer CountdownTime;
    private boolean TimerRun;
    private long TimeLeft = StartTime_inMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewCountdown = findViewById(R.id.ViewCountdown);
        StartPause = findViewById(R.id.buttonStartPause);
        ResetButton = findViewById(R.id.buttonReset);

        StartPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TimerRun) {
                    pauseTimer();
                } else {

                    startTimer();
                }

            }


        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();
    }
        private void startTimer(){
            CountdownTime = new CountDownTimer(TimeLeft, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    TimeLeft = millisUntilFinished;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    TimerRun = false;
                    StartPause.setText("Start");
                    StartPause.setVisibility(View.INVISIBLE);
                    ResetButton.setVisibility(View.VISIBLE);

                }
            }.start();

            TimerRun=true;
            StartPause.setText("pause");
            ResetButton.setVisibility(View.INVISIBLE);
        }

        private void pauseTimer(){
            CountdownTime.cancel();
            TimerRun =false;
            StartPause.setText("Start");
            ResetButton.setVisibility(View.VISIBLE);

        }

        private void resetTimer(){

            TimeLeft= StartTime_inMillis;
            updateCountDownText();
            ResetButton.setVisibility(View.INVISIBLE);
            StartPause.setVisibility(View.VISIBLE);

        }

        private void updateCountDownText(){
            int minutes = (int) (TimeLeft / 1000) / 60;
            int seconds = (int) (TimeLeft / 1000) % 60;

            String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);

            mViewCountdown.setText((timeLeftFormat));
        }

    }
    // still got bugs too fix the part 2 and 3 and 4
