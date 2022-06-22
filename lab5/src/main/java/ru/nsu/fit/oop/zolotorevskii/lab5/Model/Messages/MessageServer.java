package ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages;

import ru.nsu.fit.oop.zolotorevskii.lab5.Model.PairClientMessage;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.UserProperties;

import java.util.List;

public class MessageServer {
    int typeMessage;
    String name;
    List<String> listClients;
    String messageText;
    List<PairClientMessage> historyMessages;

    List<UserProperties> list;
    public MessageServer(int typeMessage, String nameClient, List<String> listClients, String messageText, List<PairClientMessage> listPair){
        this.typeMessage = typeMessage;
        this.name = nameClient;
        this.listClients = listClients;
        this.messageText = messageText;
        this.historyMessages = listPair;
    }


    public List<PairClientMessage> getHistoryMessages() {
        return historyMessages;
    }

    public void updateList(List<String> listUsers){
        System.out.println(listClients.size() + " ; " + listUsers.size());
        listClients = listUsers;
    }

    public List<String> getListClients() {
        return listClients;
    }

    public String getName() {
        return name;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getTypeMessage() {
        return typeMessage;
    }
}
