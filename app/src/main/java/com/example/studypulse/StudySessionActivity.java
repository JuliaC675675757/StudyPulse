package com.example.studypulse;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StudySessionActivity extends AppCompatActivity {

    private TextView txtTimer;
    private Button btnStart, btnPause, btnReset;
    private ImageButton btnBack;

    private CountDownTimer countDownTimer;
    private boolean timerRunning = false;

    private long timeLeftInMillis = 1500000; // 25 minutes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_session);


        // Connect XML elements
        txtTimer = findViewById(R.id.txtTimer);

        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);

        btnBack = findViewById(R.id.btnBack);


        updateTimer();


        // Back button
        btnBack.setOnClickListener(v -> {
            finish();
        });


        // Start timer
        btnStart.setOnClickListener(v -> startTimer());


        // Pause timer
        btnPause.setOnClickListener(v -> pauseTimer());


        // Reset timer
        btnReset.setOnClickListener(v -> resetTimer());

    }


    private void startTimer() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftInMillis = millisUntilFinished;
                updateTimer();

            }


            @Override
            public void onFinish() {

                timerRunning = false;
                txtTimer.setText("00:00");

            }

        }.start();


        timerRunning = true;

    }



    private void pauseTimer() {

        if (countDownTimer != null) {

            countDownTimer.cancel();

        }

        timerRunning = false;

    }



    private void resetTimer() {

        if (countDownTimer != null) {

            countDownTimer.cancel();

        }


        timeLeftInMillis = 1500000; // reset to 25 minutes

        updateTimer();

        timerRunning = false;

    }



    private void updateTimer() {

        int minutes = (int) (timeLeftInMillis / 1000) / 60;

        int seconds = (int) (timeLeftInMillis / 1000) % 60;


        String time = String.format(Locale.getDefault(),
                "%02d:%02d",
                minutes,
                seconds);


        txtTimer.setText(time);

    }

}