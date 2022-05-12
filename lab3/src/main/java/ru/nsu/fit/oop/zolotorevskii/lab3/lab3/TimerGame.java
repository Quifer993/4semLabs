package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.*;

public class TimerGame {
    Timer timer = new Timer("MyTimer");
    double time = 0;

    public void shutdown(){
        timer.purge();
        timer.cancel();
    }

    public void timerGo(Text timeLabel) {
        TimerTask timerTask = new TimerTask() {
            DecimalFormat df = new DecimalFormat("#.##");
//            GameApplication.stage.setOnCloseRequest();

            @Override
            public void run() {
                System.out.println("TimerTask executing counter is: " + df.format(time));
                timeLabel.setText(df.format(time));
                time += MLS_IN_SEC100;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, MLS100);
    }

    public double getTime(){
        return time;
    }

}
