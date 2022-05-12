package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.*;

public class ControllerTop {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEasy;

    @FXML
    private Button buttonHard;

    @FXML
    private Button buttonMedium;


    private void openTop(String type) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopOpen.fxml"));
        Parent root = fxmlLoader.load();
        ControllerTopOpen controller = fxmlLoader.getController();
        controller.setText(type);

        Scene scene = new Scene(root);
        GameApplication.stage.setScene(scene);
        GameApplication.stage.show();
    }

    @FXML
    void ClickedEasy(MouseEvent event) throws IOException {
        openTop(EASY);
    }

    @FXML
    void clickedBack(MouseEvent event) throws IOException {
        Launcher.launchNewWindow(getClass(), "MainMenu.fxml");
    }

    @FXML
    void clickedHard(MouseEvent event) throws IOException {
        openTop(HARD);
    }

    @FXML
    void clickedMedium(MouseEvent event) throws IOException {
        openTop(MEDIUM);
    }

    @FXML
    void mouseInBack(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonBack.setEffect(shadow);
    }

    @FXML
    void mouseInEasy(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonEasy.setEffect(shadow);
    }

    @FXML
    void mouseInHard(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonHard.setEffect(shadow);
    }

    @FXML
    void mouseInMedium(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonMedium.setEffect(shadow);
    }

    @FXML
    void mouseOutBack(MouseEvent event) {
        buttonBack.setEffect(null);
    }

    @FXML
    void mouseOutEasy(MouseEvent event) {
        buttonEasy.setEffect(null);
    }

    @FXML
    void mouseOutHard(MouseEvent event) {
        buttonHard.setEffect(null);
    }

    @FXML
    void mouseOutMedium(MouseEvent event) {
        buttonMedium.setEffect(null);
    }

    @FXML
    void initialize() {
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonEasy != null : "fx:id=\"buttonEasy\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonHard != null : "fx:id=\"buttonHard\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonMedium != null : "fx:id=\"buttonMedium\" was not injected: check your FXML file 'MainMenu.fxml'.";

    }

}
