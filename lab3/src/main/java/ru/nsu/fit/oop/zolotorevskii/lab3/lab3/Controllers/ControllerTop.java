package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerTop;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.*;

public class ControllerTop extends ViewerTop {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    private void openTop(String type) throws IOException {
        loadTopOpen(type, name);
    }

    @FXML
    void ClickedEasy(MouseEvent event) throws IOException {
        openTop(EASY);
    }

    @FXML
    void clickedBack(MouseEvent event) throws IOException {
        loadMainMenu(name);
    }

    @FXML
    void clickedHard(MouseEvent event) throws IOException {
        openTop(HARD);
    }

    @FXML
    void clickedMedium(MouseEvent event) throws IOException {
        openTop(MEDIUM);
    }


}
