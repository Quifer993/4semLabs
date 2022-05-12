package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher {

    public static void launchNewWindow(Class thisClass, String nameFxml ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(thisClass.getResource(nameFxml));
        Scene scene = new Scene(fxmlLoader.load());
        GameApplication.stage.setScene(scene);
        GameApplication.stage.show();
    }

    public static void launchNewWindowStage(Stage stage, Class thisClass, String nameFxml ) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(thisClass.getResource(nameFxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.showAndWait();
    }

}
