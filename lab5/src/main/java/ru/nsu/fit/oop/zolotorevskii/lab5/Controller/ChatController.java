package ru.nsu.fit.oop.zolotorevskii.lab5.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import ru.nsu.fit.oop.zolotorevskii.lab5.Viewer.ChatViewer;

public class ChatController extends ChatViewer {
    String name;

    public void setName(String name) {
        this.name = name;
        setNameClientLabel(name);
    }

    public void connectToServer() {


    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text nameClientLabel;

    @FXML
    void initialize() {
        assert nameClientLabel != null : "fx:id=\"nameClientLabel\" was not injected: check your FXML file 'LoginEnter.fxml'.";

    }

}
