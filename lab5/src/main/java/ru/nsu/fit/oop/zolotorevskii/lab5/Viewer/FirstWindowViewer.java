package ru.nsu.fit.oop.zolotorevskii.lab5.Viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.*;

public class FirstWindowViewer extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setTitle(APP_NAME);
        stage.setResizable(false);
        InputStream iconStream = getClass().getResourceAsStream(ICON_PATH);
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        FXMLLoader fxmlLoader = new FXMLLoader(FirstWindowViewer.class.getResource(PATH_FXML + "LoginEnter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void launchWin(String[] args) {
        launch();
    }
}