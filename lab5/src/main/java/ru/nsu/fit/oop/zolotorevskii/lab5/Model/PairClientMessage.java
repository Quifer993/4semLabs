package ru.nsu.fit.oop.zolotorevskii.lab5.Model;

public class PairClientMessage {
    String name;
    String message;

    public PairClientMessage(String nickClient, String messageText){
        name = nickClient;
        message = messageText;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
