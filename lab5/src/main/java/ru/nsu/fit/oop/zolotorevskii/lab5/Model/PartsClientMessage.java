package ru.nsu.fit.oop.zolotorevskii.lab5.Model;

public class PartsClientMessage {
    String name;
    String message;
    int type;

    public PartsClientMessage(String nickClient, String messageText, int typeMes) {
        name = nickClient;
        message = messageText;
        type = typeMes;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }
}
