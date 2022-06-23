package ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages;

public class MessageUser {
//    boolean error;
    private Integer typeMessage;
    private String name;
    String messageText;

    public MessageUser(int typeMessage, String name, String messageText){
        this.typeMessage = typeMessage;
        this.name = name;
        this.messageText = messageText;
    }

    public Integer getTypeMessage() {
        return typeMessage;
    }

    public String getName() {
        return name;
    }

    public String getMessageText() {
        return messageText;
    }
}
