package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.ParamGame;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerChooseDif;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.*;

public class ControllerChooseDif extends ViewerChooseDif {
    String name;

    @FXML
    void ClickedBackMainMenu(ActionEvent event) throws IOException {
        loadMainMenu(name);
    }

    @FXML
    void ClickedEasyGame(MouseEvent event) throws IOException {
        ParamGame paramsGame = new ParamGame(name,EASY, 10,10,9);
        startGame(paramsGame);
    }

    @FXML
    void ClickedHardGame(MouseEvent event) throws IOException {
        ParamGame paramsGame = new ParamGame(name,HARD, 15,15,37);
        startGame(paramsGame);
    }

    @FXML
    void ClickedMediumGame(MouseEvent event) throws IOException {
        ParamGame paramsGame = new ParamGame(name,MEDIUM, 12,12,25);
        startGame(paramsGame);
    }

    @FXML
    void ClickedPersonalGame(MouseEvent event) throws IOException {
        boolean isNumCorrect = true;
        ParamGame paramsGame = new ParamGame(name,MEDIUM, 0,0,0);

        try{
            int height = Integer.parseInt(getHeight());
            if(height > 22) height =22;
            paramsGame.setHeight(height);
        }catch(NumberFormatException e){
            isNumCorrect = false;
        }
        try{
            int length = Integer.parseInt(getLenght());
            if(length > 40) length =40;
            paramsGame.setLength(length);
        }catch(NumberFormatException e){
            isNumCorrect = false;
        }
        try{
            paramsGame.setMines(Integer.parseInt(getMines()));
        }catch(NumberFormatException e){
            isNumCorrect = false;
        }

        if(paramsGame.getHeight() * paramsGame.getLength() <= paramsGame.getMines() ){
            isNumCorrect = false;
        }

        if(isNumCorrect){
            startGame(paramsGame);
        }
    }


    public void setName(String name){
        this.name = name;
    }

    private void startGame(ParamGame paramsGame) throws IOException {
        loadGameWindow(paramsGame);
    }
}
