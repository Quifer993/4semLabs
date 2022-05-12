package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EndGameLoseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonClose;

    @FXML
    private DialogPane buttonEndGame;

    @FXML
    void clickedButtonClose(MouseEvent event) throws RuntimeException {
        GameApplication.stage.close();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void closeWindow(KeyEvent event) {

    }

    @FXML
    void initialize() {
        assert buttonClose != null : "fx:id=\"buttonClose\" was not injected: check your FXML file 'EndGameWin.fxml'.";
        assert buttonEndGame != null : "fx:id=\"buttonEndGame\" was not injected: check your FXML file 'EndGameWin.fxml'.";

    }

}
