package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class ControllerMainMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExitGame;

    @FXML
    private TextField buttonSetName;

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonTopGame;

    @FXML
    void ClickedExitGame(MouseEvent event){
        System.exit(0);
    }

    @FXML
    void ClickedStartGame(MouseEvent event) throws IOException {
        Game.name = (buttonSetName.getText());
        try{
            int limit = 10;
            if(Game.name.length() > limit){
                Game.name =  Game.name.codePointCount(0,  Game.name.length()) > limit ?
                        Game.name.substring(0,  Game.name.offsetByCodePoints(0, limit)) :  Game.name;
            }
        }catch(NullPointerException e){

        }

        Launcher.launchNewWindow(getClass(), "chooseDifficulty.fxml");
    }

    @FXML
    void ClickedTopGame(MouseEvent event) throws IOException {
        Game.name = buttonSetName.getText();
        Launcher.launchNewWindow(getClass(), "Top.fxml");
    }

    @FXML
    void mouseInExitGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonExitGame.setEffect(shadow);
    }

    @FXML
    void mouseInStartGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonStartGame.setEffect(shadow);
    }

    @FXML
    void mouseInTopGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonTopGame.setEffect(shadow);
    }

    @FXML
    void mouseOutExitGame(MouseEvent event) {
        buttonExitGame.setEffect(null);
    }

    @FXML
    void mouseOutStartGame(MouseEvent event) {
        buttonStartGame.setEffect(null);
    }

    @FXML
    void mouseOutTopGame(MouseEvent event) {
        buttonTopGame.setEffect(null);
    }


    @FXML
    void initialize() {
        assert buttonExitGame != null : "fx:id=\"buttonExitGame\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonTopGame != null : "fx:id=\"buttonTopGame\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonSetName != null : "fx:id=\"buttonSetName\" was not injected: check your FXML file 'MainMenu.fxml'.";
        buttonSetName.setText(Game.name);
    }

}

