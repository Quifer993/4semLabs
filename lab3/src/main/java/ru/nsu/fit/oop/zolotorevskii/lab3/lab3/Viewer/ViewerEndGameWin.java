package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewerEndGameWin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonClose;

    @FXML
    private DialogPane buttonEndGame;

    @FXML
    private TextField labelName;

    @FXML
    private Text labelTime;

    protected void setTimeLabel(String time){
        labelTime.setText(time + " сек!");
    }


    @FXML
    void clickedButtonClose(MouseEvent event) throws RuntimeException {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert buttonClose != null : "fx:id=\"buttonClose\" was not injected: check your FXML file 'EndGameWin.fxml'.";
        assert buttonEndGame != null : "fx:id=\"buttonEndGame\" was not injected: check your FXML file 'EndGameWin.fxml'.";

    }
}
