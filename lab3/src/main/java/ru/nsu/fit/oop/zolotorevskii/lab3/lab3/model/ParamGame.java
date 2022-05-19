package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model;

public class ParamGame {
    String name;
    String difficult;
    int length;
    int height;
    int mines;
//    Map<String, Integer> paramsOfField;

    public ParamGame(String name, String typeGame, int length, int height, int mines){
        this.name = name;
        this.difficult = typeGame;
        this.length = length;
        this.height = height;
        this.mines = mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public int getMines() {
        return mines;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public String getDifficult() {
        return difficult;
    }
}
