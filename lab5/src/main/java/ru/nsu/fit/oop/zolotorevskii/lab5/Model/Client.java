package ru.nsu.fit.oop.zolotorevskii.lab5.Model;

import com.google.gson.Gson;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageServer;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageUser;

import java.io.*;
import java.net.*;
import java.nio.channels.IllegalBlockingModeException;

import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.*;

public class Client {
    DataInputStream in;
    DataOutputStream out;
    String nameClient;
    Socket clientSocket;

    public Client(String name){
        nameClient = name;
    }

    public MessageServer connect(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
            clientSocket.connect(new InetSocketAddress(PORT_SERVER));

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            Gson gson = new Gson();
            MessageUser messageUser = new MessageUser(LOGIN, nameClient, "");

            String s = gson.toJson(messageUser);
//            System.out.println(s);

            out.writeUTF(s);
            out.flush();
//            out.write(s);
//            out.flush();
//            return recieveAnswerFromServer(gson, in);
            MessageServer answerServer = gson.fromJson(in.readUTF(), MessageServer.class);
             answerServer.getListClients();

            System.out.println(answerServer.getTypeMessage());
            return answerServer;


//            System.out.println("fewfe");

//        return ERROR_MES;

    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    private int recieveAnswerFromServer(Gson gson, BufferedReader in) throws IOException {
        MessageServer answerServer = gson.fromJson(in.readLine(), MessageServer.class);

        return answerServer.getTypeMessage();
    }
}
