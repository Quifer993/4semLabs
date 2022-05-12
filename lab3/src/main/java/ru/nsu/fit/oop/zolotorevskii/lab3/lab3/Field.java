package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.util.Map;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.*;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.TypeCell.*;

public class Field {
    private final int[][] matrixField;

    public Field(Map<String, Integer> paramsOfGame, int empty) {
        int length = paramsOfGame.get(LENGTH);
        int height = paramsOfGame.get(HEIGHT);
        matrixField = new int[length][height];
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                matrixField[i][j] = EMPTY;
            }
        }
    }

    public int getTypeCell(int row, int column){
//        System.out.println(matrixField.length);
        return matrixField[column][row];
    }

    public void setTypeCell(int row, int column, int type){
        matrixField[column][row] = type;
    }

    Field( Map<String, Integer> paramsOfGame){
        int length = paramsOfGame.get(LENGTH);
        int height = paramsOfGame.get(HEIGHT);
        int mines = paramsOfGame.get(MINES);
        matrixField = new int[length][height];
        for(int i = 0; i < length; i++){
            for(int j = 0; j < height; j++){
                matrixField[i][j] = NULL;
            }
        }

        for(int i = 0; i < mines; i++){
            int newLength = (int) (Math.random() * length);
            int newHeight = (int) (Math.random() * height);
            while(matrixField[newLength][newHeight] == BOMB){
                newLength = (int) (Math.random() * length);
                newHeight = (int) (Math.random() * height);
            }
            matrixField[newLength][newHeight] = BOMB;
            plusAround(newLength, newHeight, length - 1, height - 1);
        }
        for(int i = 0; i < length; i++){
            for(int j = 0; j < height; j++){
                System.out.print( matrixField[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    private void plusAround(int length, int height, int maxLength, int maxHeight){
        boolean isLeft = true; boolean isTop = true;
        boolean isRight = true; boolean isDown = true;
        if(length == 0) isLeft = false;
        if(length == maxLength) isRight = false;
        if(height == 0) isTop = false;
        if(height == maxHeight) isDown = false;

        if(isLeft  && matrixField[length - 1][height] !=BOMB) matrixField[length - 1][height]++;
        if(isLeft && isTop && matrixField[length - 1][height - 1] !=BOMB) matrixField[length - 1][height - 1]++;
        if(isTop && matrixField[length][height - 1] !=BOMB) matrixField[length][height - 1]++;
        if(isRight && isTop && matrixField[length + 1][height - 1] !=BOMB) matrixField[length + 1][height - 1]++;
        if(isRight && matrixField[length + 1][height] !=BOMB) matrixField[length + 1][height]++;
        if(isRight && isDown && matrixField[length + 1][height + 1] !=BOMB) matrixField[length + 1][height + 1]++;
        if(isDown && matrixField[length][height + 1] !=BOMB) matrixField[length][height + 1]++;
        if(isLeft && isDown && matrixField[length - 1][height + 1] !=BOMB) matrixField[length - 1][height + 1]++;
    }
}

