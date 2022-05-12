package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class GameApplication extends Application {
    public static Stage stage;


    @Override
    public void start(Stage stageGame) throws IOException {
        stage = stageGame;
        stage.setTitle("Sapper");
        stage.setResizable(false);
        InputStream iconStream = getClass().getResourceAsStream("icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        Launcher.launchNewWindow(GameApplication.class, "MainMenu.fxml");
//        GameApplication.stage.setOnCloseRequest();
    }


    public static void main(String[] args) {
        launch();
    }
}