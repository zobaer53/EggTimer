package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    ImageView imageView;
    TextView textView;
    Button button1,button2;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        textView.setText("0:30");
        seekBar.setProgress(30);
      seekBar.setEnabled(true);
        countDownTimer.cancel();
       button2.setText("GO!");
        counterIsActive = false;
    }

    public void timeUpdate(int second){
        int min=second/60;
        int sec=second-(min*60);
        String secString=Integer.toString(sec);

        if(sec<=9){

            secString="0"+secString;
        }
        textView.setText(Integer.toString(min)+":"+secString);

    }

    boolean counterIsActive =false;

    public void buttonClicked(View view) {
        if(counterIsActive){

            resetTimer();
        }
        else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button2.setText("STOP!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeUpdate((int) (millisUntilFinished / 1000));

                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        button2=findViewById(R.id.button2);


        seekBar.setMax(600);
        seekBar.setProgress(30);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {


                timeUpdate(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




}