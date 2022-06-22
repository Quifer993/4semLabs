package ru.nsu.fit.oop.zolotorevskii.lab5.Viewer;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatViewer {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Text nameClientLabel;

    public void setNameClientLabel(String name) {
        nameClientLabel.setText(name);
    }



    @FXML
    void initialize() {
        assert nameClientLabel != null : "fx:id=\"nameClientLabel\" was not injected: check your FXML file 'LoginEnter.fxml'.";

    }
}
