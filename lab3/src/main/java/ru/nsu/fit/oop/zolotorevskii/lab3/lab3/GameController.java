package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.*;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.PathImages.*;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.TypeCell.*;

public class GameController {

    int openedCells;
    int countFlags;
    boolean isGameContinue;
    Field fieldFull;
    Field fieldNow;
    TimerGame timerGame;

    int length;
    int height;
    int mines;

    String name;
    String difficult;
    Map<String, Integer> paramsOfGame;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem buttonExitGame;

    @FXML
    private MenuItem buttonMenu;

    @FXML
    private GridPane gridPane;

    @FXML
    private Text nameLabel;

    @FXML
    private Text textFlags;

    @FXML
    private Text timeLabel;


    public void setParams(String name, Map<String, Integer> paramsOfGame, String difficult){
        this.paramsOfGame = paramsOfGame;
        length = paramsOfGame.get(LENGTH);
        height = paramsOfGame.get(HEIGHT);
        mines = paramsOfGame.get(MINES);
        this.name = name;
        this.difficult = difficult;

    }


    @FXML
    void clickedGridPane(MouseEvent event) throws IOException {
        if(isGameContinue){
            double x = event.getX();
            double y = event.getY();
            int row =(int) (y / IMAGE_Y);
            int column =(int) (x / IMAGE_X);
//            System.out.println("x: " + x + "\ny: " + y);
            if(row >= Game.height || column >= Game.length){
                return;
            }

            int cellNow = fieldNow.getTypeCell(row, column);
            int statusCell = fieldFull.getTypeCell(row, column);


            if(event.getButton().name().equals(MouseButton.SECONDARY.name())){
                if(cellNow == FLAG){
                    countFlags++;
                    textFlags.setText(((Integer)countFlags).toString());
                    gridPane.add(new ImageView(new Image(PATH_CELLS + "empty.png")), column, row);
                    fieldNow.setTypeCell(row, column, EMPTY);
                }else if(cellNow == EMPTY){
                    countFlags--;
                    textFlags.setText(((Integer)countFlags).toString());
                    gridPane.add(new ImageView(new Image(PATH_CELLS + "flag.png")), column, row);
                    fieldNow.setTypeCell(row, column, FLAG);
                }

            }
            else if(cellNow == FLAG){

            }
            else if(statusCell == BOMB){
                endGame( BOMB_UNDEFUSED,"EndGameLose.fxml");

            }else if(cellNow == EMPTY){
                int type = fieldFull.getTypeCell(row,column);
                gridPane.add(new ImageView(new Image(PATH_CELLS + type + ".png")), column, row);
                fieldNow.setTypeCell(row, column, type);
                openedCells++;
                if(type == NULL) setAroundEmptyCell(column, row);
                if(checkStatusGame()){
                    endGame( BOMB_DEFUSED,"EndGameWin.fxml");
                }
            }

        }
    }

    private void endGame(String typeBomb, String nameFxmlEndType) throws IOException {
        isGameContinue = false;
        showAllMines(typeBomb);
        timerGame.shutdown();
        GameApplication.stage.show();
        Stage stageEndGame  = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nameFxmlEndType));
        Parent root = fxmlLoader.load();
        EndGameWinController controller;
        if(Objects.equals(typeBomb, BOMB_DEFUSED)){
            controller = fxmlLoader.getController();
            controller.setWinnerStatistics(timeLabel.getText(), difficult, name);
        }

        Scene scene = new Scene(root);
        stageEndGame.setScene(scene);
        stageEndGame.showAndWait();

        Launcher.launchNewWindow( getClass(), "MainMenu.fxml");
    }

    private void checkSide(boolean conf, int horisontal, int vertical, int length, int height){
        if(conf && fieldNow.getTypeCell(height + vertical, length + horisontal) == EMPTY){
            openedCells++;
            int type = fieldFull.getTypeCell(height + vertical, length + horisontal);
            fieldNow.setTypeCell(height + vertical, length + horisontal,type);
            gridPane.add(new ImageView(new Image(PATH_CELLS + type + ".png")), length + horisontal, height + vertical);

            if(fieldFull.getTypeCell(height + vertical, length + horisontal) ==NULL){
                setAroundEmptyCell(length + horisontal, height + vertical);

            }
        }
    }

    private void setAroundEmptyCell(int length, int height) {
        int maxLength = Game.length - 1;
        int maxHeight = Game.height - 1;
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
        return Game.length * Game.height == openedCells;
    }

    private void showAllMines(String typeBomb){
        int length = Game.length;
        int height = Game.height;
        for(int i = 0; i < length; i ++){
            for(int j = 0; j < height; j ++){
                if(fieldFull.getTypeCell(j, i) == BOMB){
                    gridPane.add(new ImageView(new Image(PATH_CELLS + typeBomb + ".png")), i, j);
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
            timerGame.shutdown();
            Launcher.launchNewWindow(getClass(), "MainMenu.fxml");
        }
    }

    @FXML
    void initialize() {
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'GameController.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'GameController.fxml'.";
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'GameController.fxml'.";
        GameApplication.stage.setX(0);
        GameApplication.stage.setY(0);
        int length = Game.length;
        int height = Game.height;
        System.out.println(length + height);
        for(int i = 0; i < length; i ++){
            for(int j = 0; j < height; j ++){
                gridPane.add(new ImageView(new Image(PATH_CELLS + "empty.png")), i, j);
            }
        }
        fieldFull = new Field(Game.paramsOfGame);
        fieldNow = new Field(Game.paramsOfGame, EMPTY);
        isGameContinue = true;
        openedCells = Game.mines;
        countFlags = Game.mines;
        textFlags.setText(((Integer)countFlags).toString());
        nameLabel.setText(name);

        timerGame = new TimerGame();
        timerGame.timerGo(timeLabel);
    }
}
