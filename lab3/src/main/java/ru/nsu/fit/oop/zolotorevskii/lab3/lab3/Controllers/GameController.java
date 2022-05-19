package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerGame;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.ParamGame;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.TimerGame;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.Field;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.*;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PathImages.*;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.TypeCell.*;

public class GameController extends ViewerGame {
    int openedCells;
    int countFlags;
    Field fieldFull;
    Field fieldNow;
    ParamGame paramsGame;
    TimerGame timerGame;
    boolean isGameContinue;

    public void setGameParams(ParamGame paramsGame){
        this.paramsGame = paramsGame;
        isGameContinue = true;
        //game = new Game(paramsGame);
        setLabelName(paramsGame.getName());
        setLabelFlags(paramsGame.getMines());
        openedCells = paramsGame.getMines();
        countFlags = paramsGame.getMines();
        fieldFull = new Field(paramsGame);
        fieldNow = new Field(paramsGame, EMPTY);
        int length = paramsGame.getLength();
        int height = paramsGame.getHeight();
        for(int i = 0; i < length; i ++){
            for(int j = 0; j < height; j ++){
                showCell("empty", i, j);
            }
        }
    }

    @FXML
    void clickedGridPane(MouseEvent event) throws IOException {
        if(openedCells == paramsGame.getMines()){
            timerGame = new TimerGame();
            timerShow(timerGame);

        }
        if(isGameContinue){
            double x = event.getX();
            double y = event.getY();
            int row =(int) (y / IMAGE_Y);
            int column =(int) (x / IMAGE_X);
            if(row >= paramsGame.getHeight() || column >= paramsGame.getLength()){
                return;
            }

            int cellNow = fieldNow.getTypeCell(row, column);
            int statusCell = fieldFull.getTypeCell(row, column);

            if(event.getButton().name().equals(MouseButton.SECONDARY.name())){
                if(cellNow == FLAG){
                    countFlags++;
                    showCell("empty", column, row);
                    fieldNow.setTypeCell(row, column, EMPTY);
                }else if(cellNow == EMPTY){
                    countFlags--;
                    showCell("flag", column, row);
                    fieldNow.setTypeCell(row, column, FLAG);
                }
                setLabelFlags(countFlags);
            }
            else if(cellNow == FLAG){

            }
            else if(statusCell == BOMB){
                endGame( BOMB_UNDEFUSED,PATH_LOSE_FXML);
            }else if(cellNow == EMPTY){
                int type = fieldFull.getTypeCell(row,column);
                showCell(type + "", column, row);
                fieldNow.setTypeCell(row, column, type);
                openedCells++;
                if(type == NULL) setAroundEmptyCell(column, row);
                if(checkStatusGame()){
                    endGame( BOMB_DEFUSED,PATH_WIN_FXML);
                }
            }
        }
    }

    private void endGame(String typeBomb, String nameFxmlEndType) throws IOException {
        isGameContinue = false;
        showAllMines(typeBomb);
        timerGame.shutdown();
        String timeNow = getTimeLabel();
        loadEndStage(typeBomb, nameFxmlEndType, paramsGame, timeNow);
        loadMainMenu(paramsGame.getName());
    }

    private void checkSide(boolean conf, int horisontal, int vertical, int length, int height){
        if(conf && fieldNow.getTypeCell(height + vertical, length + horisontal) == EMPTY){
            openedCells++;
            int type = fieldFull.getTypeCell(height + vertical, length + horisontal);
            fieldNow.setTypeCell(height + vertical, length + horisontal,type);
            showCell(type  + "", length + horisontal, height + vertical);
            if(fieldFull.getTypeCell(height + vertical, length + horisontal) ==NULL){
                setAroundEmptyCell(length + horisontal, height + vertical);

            }
        }
    }

    private void setAroundEmptyCell(int length, int height) {
        int maxLength = paramsGame.getLength() - 1;
        int maxHeight = paramsGame.getHeight() - 1;
        boolean isLeft = true; boolean isTop = true;
        boolean isRight = true; boolean isDown = true;
        if(length == 0) isLeft = false;
        if(length == maxLength) isRight = false;
        if(height == 0) isTop = false;
        if(height == maxHeight) isDown = false;

        checkSide(isLeft, -1, 0, length,height);
        checkSide(isLeft && isTop, -1, -1, length,height);
        checkSide(isTop, 0, -1, length,height);
        checkSide(isRight && isTop, 1, -1, length,height);
        checkSide(isRight, 1, 0, length,height);
        checkSide(isRight && isDown, 1, 1, length,height);
        checkSide(isDown, 0, 1, length,height);
        checkSide(isLeft && isDown, -1, 1, length,height);
    }

    private boolean checkStatusGame() {
        return paramsGame.getLength() * paramsGame.getHeight() == openedCells;
    }

    private void showAllMines(String typeBomb){
        int length = paramsGame.getLength();
        int height = paramsGame.getHeight();

        for(int i = 0; i < length; i ++){
            for(int j = 0; j < height; j ++){
                if(fieldFull.getTypeCell(j, i) == BOMB){
                    showCell(typeBomb, i, j);
                }
            }
        }
    }

    @FXML
    void clickedExitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void clickedMenu(ActionEvent event) throws IOException {
        if(isGameContinue){
            if(timerGame != null){
                timerGame.shutdown();
            }
            loadMainMenu(paramsGame.getName());
        }
    }
}
