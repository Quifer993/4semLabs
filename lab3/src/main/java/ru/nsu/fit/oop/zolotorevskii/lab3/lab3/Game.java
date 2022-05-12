package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.IOException;
import java.util.Map;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.*;

public class Game {
    static int length;
    static int height;
    static int mines;
    static String name;
   // Field field;
    String typeGame;
    static Map<String, Integer> paramsOfGame;

    Game(String typeGameNew, Map<String, Integer> paramsOfMatrix){
        length = paramsOfMatrix.get(LENGTH);
        height =  paramsOfMatrix.get(HEIGHT);
        mines = paramsOfMatrix.get(MINES);
        typeGame = typeGameNew;
        paramsOfGame = paramsOfMatrix;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getMines() {
        return mines;
    }

    public void startGame(String typeGame, Map<String, Integer> paramsOfGame) throws IOException {


    }

}
