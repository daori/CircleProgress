package com.github.lzyzsd.circleprogressexample;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by daori on 6/5/15.
 */
public class NetzmeActivity extends Activity{
    private Timer timer;
    private DonutProgress netzmeProgress;
    private DonutProgress netzmeProgress2;
    private TextView timeTextView;
    private int second = 0;
    private int x;
    private int y=0;
    private TextView timeTextView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.netzme_activity);

        netzmeProgress = (DonutProgress) findViewById(R.id.custom_progress);
        netzmeProgress2 = (DonutProgress) findViewById(R.id.custom_progress2);
        timeTextView = (TextView) findViewById(R.id.time_text_view);
        timeTextView2 = (TextView) findViewById(R.id.time_text_view2);

        ObjectAnimator animation = ObjectAnimator.ofInt(netzmeProgress2, "progress",0,300);// 500 : jumlah target dalam 30detik
        animation.setDuration(30000); //in milliseconds
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setRepeatCount(2);
        animation.start();
        netzmeProgress2.clearAnimation();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        netzmeProgress.setProgress(netzmeProgress.getProgress() + 1);
//                        timeTextView.setText(String.valueOf(netzmeProgress.getProgress() + 1));
                        x +=100;

                        if(x % 1000 == 0){
                            y++;
                            timeTextView.setText(String.valueOf(y) + "s");
                            timeTextView2.setText(String.valueOf(y) + "s");
                        }

                        if(y == 30){
                            y=0;
                        }
                    }
                });
            }
        }, 0, 100);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
