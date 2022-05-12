package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndGameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonClose;

    @FXML
    private Text textStatusGame;

    @FXML
    private Text textTypeFinish;

    @FXML
    private Text timeText;

    @FXML
    void clickedButtonClose(MouseEvent event) throws RuntimeException {
        GameApplication.stage.close();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert buttonClose != null : "fx:id=\"buttonClose\" was not injected: check your FXML file 'EndGameWin.fxml'.";
        assert textStatusGame != null : "fx:id=\"textStatusGame\" was not injected: check your FXML file 'EndGameWin.fxml'.";
        assert textTypeFinish != null : "fx:id=\"textTypeFinish\" was not injected: check your FXML file 'EndGameWin.fxml'.";
        assert timeText != null : "fx:id=\"timeText\" was not injected: check your FXML file 'EndGameWin.fxml'.";

    }

}
