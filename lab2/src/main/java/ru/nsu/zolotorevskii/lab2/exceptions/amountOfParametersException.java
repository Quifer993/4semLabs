package ru.nsu.zolotorevskii.lab2.exceptions;

public class amountOfParametersException extends Exception{
    public amountOfParametersException(int needLength, int realLength){
        super("Operation need " + needLength + " variables, but it has only " + realLength);
    }
}
