package ru.nsu.fit.oop.zolotorevskii.lab5.Viewer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloViewer {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
