package ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages;

import ru.nsu.fit.oop.zolotorevskii.lab5.Model.UserProperties;

import java.util.List;

public class MessageServer {
    int typeMessage;

    String name;

    List<UserProperties> list;
    public MessageServer(int typeMessage, String nameClient){
        this.typeMessage = typeMessage;
        this.name = nameClient;
    }

    public String getName() {
        return name;
    }

    public int getTypeMessage() {
        return typeMessage;
    }
}
