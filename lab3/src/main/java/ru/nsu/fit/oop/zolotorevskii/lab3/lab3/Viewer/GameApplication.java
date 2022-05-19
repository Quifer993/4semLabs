package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.*;

public class GameApplication extends Application {
    public Stage stage;

    @Override
    public void start(Stage stageGame) throws IOException {
        stage = stageGame;
        stage.setTitle(NAME_GAME);
        stage.setResizable(false);
        InputStream iconStream = getClass().getResourceAsStream(ICON_PATH);
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_FXML));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void launchGame(){
        launch();
    }
}
