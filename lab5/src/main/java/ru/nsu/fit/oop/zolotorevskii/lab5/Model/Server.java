package ru.nsu.fit.oop.zolotorevskii.lab5.Model;

import com.google.gson.Gson;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageServer;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageUser;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.Sending;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.*;

public class Server {
//    ExecutorService executeIt = Executors.newCachedThreadPool();
    //newFixedThreadPool
    List<String> listUsers = new ArrayList<>();
    List<Socket> listSockets = new ArrayList<>();
    List<PairClientMessage> historyMes = new ArrayList<>();

    Map<Socket, String> mapUsers = new HashMap<Socket, String>();
    boolean serverWorking;
//    int amountClients

    public void startServer() throws IOException {
        serverWorking = true;
        for (int i = 0; i < COUNT_THREADS; i++) {
            MonoThreadClientHandler f = new MonoThreadClientHandler(i);
            f.start();
        }

        try {
            ServerSocket server = new ServerSocket(PORT_SERVER);
            System.out.println("Server socket created");

            while (!server.isClosed()) {
                Socket client = server.accept();
                listSockets.add(client);
//                executeIt.execute(new MonoThreadClientHandler(client, listUsers));
                System.out.println("Connection accepted. " + listSockets.size() );
            }

//            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverWorking = false;
    }


    class MonoThreadClientHandler extends Thread {
        //    Socket clientDialog;
        int numberThread;

        public MonoThreadClientHandler(int number) {
            numberThread = number;
//        clientDialog = client;
        }

        @Override
        public void run() {
            int dialogWithClientCount = numberThread;
            while (serverWorking) {
                dialogWithClientCount += COUNT_THREADS;
                if (dialogWithClientCount >= listSockets.size()) {
                    dialogWithClientCount = numberThread;
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(listSockets.size());
                if(listSockets.size() > dialogWithClientCount){
                    try {
                        Socket socketClient = listSockets.get(dialogWithClientCount);
//                        if (socketClient.getInputStream().read() == 0) {
//
//                        }
                        DataInputStream in = new DataInputStream(socketClient.getInputStream());
                        DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());

                        if(in.available() == 0){ continue;}

                        String entry = in.readUTF();
                        Gson gson = new Gson();
                        MessageUser messageUser = gson.fromJson(entry, MessageUser.class);
                        System.out.println("\nREAD from clientDialog message - " + entry);
                        MessageServer messageServer;
                        String nickClient = messageUser.getName();
                        if (messageUser.getTypeMessage() == LOGIN) {
                            if (listUsers.contains(nickClient)) {
                                messageServer = new MessageServer(LOGIN_ERROR, nickClient, listUsers, "" , null);
                                Sending.sendMessage(out,gson.toJson(messageServer));
                                System.out.println("Клиент " + nickClient + TEXT_NEW_CLIENT);
                            }
                            else{
                                mapUsers.put(socketClient, nickClient);
//                                historyMes.add(new PairClientMessage(nickClient, messageText));

//                                messageServer = new MessageServer(LOGIN_SUCCESS, nickClient, listUsers);
                                listUsers.add(nickClient);

                                sendMesAllUsers(gson, LOGIN_SUCCESS, nickClient, "", historyMes);
                            }
                        }
                        else if(messageUser.getTypeMessage() == MESSAGE_TYPE){
                            String messageText = messageUser.getMessageText();
                            historyMes.add(new PairClientMessage(nickClient, messageText));
                            sendMesAllUsers(gson, MESSAGE_TYPE, nickClient, messageText,null);
                            System.out.println("Клиент " + nickClient + " отправил всем сообщение");
                        }
                        else if(messageUser.getTypeMessage() == LOGOUT){

                            try{
                                listSockets.remove(socketClient);
                                mapUsers.remove(socketClient);
                                listUsers.remove(nickClient);

                                sendMesAllUsers(gson, LOGOUT, nickClient, "", null);
                                socketClient.close();
                                System.out.println("Клиент " + nickClient + TEXT_LOGOUT_CLIENT);
                            }catch(NullPointerException alreadyDel){
                                System.out.println("Socket" + socketClient.getInetAddress() + ":"
                                        + socketClient.getPort() + " already closed!");
                            }

                            in.close();
                            out.close();
//                            continue;
                        }

                        System.out.println("Thread " + numberThread + " end Work with message from client correct!");
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void sendMesAllUsers(Gson gson, int typeMes, String nickClient, String messageText, List<PairClientMessage> listPair) {
            MessageServer messageServer = new MessageServer(typeMes, nickClient, listUsers, messageText, listPair);
            String messageStr = gson.toJson(messageServer);
            for(int i = 0; i < listSockets.size(); i++){
                Socket socket = listSockets.get(i);
                try{
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    Sending.sendMessage(out, messageStr);
                    out.writeUTF(messageStr);
                    out.flush();
                }catch (IOException e) {
                    System.out.println("send to socket: " + socket.getInetAddress() + " - error. Delete this socket from list!");
                    listSockets.remove(socket);
                    listUsers.remove(mapUsers.get(socket));
                    mapUsers.remove(socket);
                    i--;
                }
            }
        }
    }
}