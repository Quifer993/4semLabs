package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.*;

public class TimerGame {
    Timer timer = new Timer("MyTimer");
    double time = 0;

    public void shutdown(){
        timer.purge();
        timer.cancel();
    }

    public void timerGo(Text timeLabel) {
        Stage stage = (Stage) timeLabel.getScene().getWindow();
        stage.setOnCloseRequest(event -> this.shutdown());
        TimerTask timerTask = new TimerTask() {
            DecimalFormat df = new DecimalFormat("#.##");

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
