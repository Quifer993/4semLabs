package ru.nsu.fit.oop.zolotorevskii.lab5.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Client;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageServer;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageUser;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.Sending;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.PairClientMessage;
import ru.nsu.fit.oop.zolotorevskii.lab5.Viewer.ChatViewer;
import ru.nsu.fit.oop.zolotorevskii.lab5.Viewer.FirstWindowViewer;

import static java.lang.Thread.sleep;
import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.*;
import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.LOGOUT;

public class ChatController extends ChatViewer {
    String name;
    List<String> listUsers = new ArrayList<>();
    DataInputStream in;
    DataOutputStream out;
    boolean isWorkingChat = true;
    Client client;
    Socket clientSocket;
    double heightBoxWithMessages = 0;

    public void setWorkingChat(boolean workingChat) {
        isWorkingChat = workingChat;
    }

    public void setListUsers(List<String> listUsers) {
        this.listUsers = listUsers;
//        this.listUsers.add(name);
        updateListUsers();
    }

    public void setName(String name) {
        this.name = name;
        setNameClientLabel(name);
    }

    public void connectToServer() {


    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Text namesClientsField1;

    @FXML
    private VBox boxWithMessages;

    @FXML
    private TextArea inputMessageField;

    @FXML
    private Text nameClientLabel;

    @FXML
    private Button buttonDisconnect;

    @FXML
    private TextArea namesClientsField;

    @FXML
    private AnchorPane pane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void keyPressedInMessage(KeyEvent event) {
        String messageText = inputMessageField.getText();
        if(event.getCode().equals(KeyCode.ENTER)){
            if(messageText.length() != 0){
                try{


//                Socket socket = new Socket();
//                socket.getOutputStream()
//                int answerServ = client.connect();
//                if(answerServ == LOGIN_SUCCESS){
//                    launchChat(getLoginLabel());
//                }
//                else if(answerServ == LOGIN_ERROR){
//                    showErrorMessage("This login already exist, input new login!");
//                }
//                else{
//                    showErrorMessage("WTF");
//                }
                Gson gson = new Gson();
                MessageUser messageUser = new MessageUser(MESSAGE_TYPE ,name, messageText);
                Sending.sendMessage(out,gson.toJson(messageUser));


//                addNewMessage(inputMessageField.getText(), name);
                inputMessageField.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @FXML
    void logoutPressed(MouseEvent event) throws IOException {
        logout();

        Stage stage = (Stage) boxWithMessages.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(FirstWindowViewer.class.getResource(PATH_FXML + "LoginEnter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }

    void updateListUsers(){
        String newListClient ="";
        for(String s : listUsers){
            newListClient += s + "\n";
        }
        namesClientsField.setText(newListClient);
    }


    void addNewMessage(String text, String nameClient){
        Label boxMes = new Label();
        boxMes.setWrapText(true);
//        boxMes.setStyle();
//        boxMes.setMaxHeight(newMes.getLayoutBounds().getHeight());
//        boxMes.setMaxWidth(newMes.getLayoutBounds().getWidth());
//        boxMes.setStyle("-fx-background-color: #5a55ff");//5a55ff e0ffff

        String fulMes = "\t";
        if(name.equals(nameClient)){
            boxMes.setAlignment(Pos.BASELINE_RIGHT);
            fulMes += "Вы";
        }
        else{
            System.out.println(name + "  " + nameClient);
            fulMes +=nameClient;
            boxMes.setAlignment(Pos.BASELINE_LEFT);
        }
        fulMes += " :" + "\n" + text;
        boxMes.setText(fulMes);

        boxMes.layout();
        int height = 10 + (int)((text.length() / 40  + 2) * 25);
        heightBoxWithMessages += 5 + height;

        if(pane.getHeight() < heightBoxWithMessages){
            pane.setPrefHeight(heightBoxWithMessages);
        }
        boxWithMessages.setPrefHeight(pane.getPrefHeight());

//        System.out.println(heightBoxWithMessages + " ; " + pane.getHeight());
//        pane.setPrefHeight(pane.getHeight() + 40);

//        System.out.println(pane.getHeight());
//        System.out.println(boxWithMessages.getHeight());
//        System.out.println(boxMes.getLayoutBounds().getHeight());
//        System.out.println(boxMes.getHeight());


        boxMes.styleProperty().bind(
                new SimpleStringProperty("-fx-background-color: #e0fff;")
                        .concat("-fx-font-size: 13px;")
                        .concat("-fx-font-color: #000000;")
                        .concat("-fx-background-radius: 5;")
//                        .concat("-fx-background-insets: 0;")
                        .concat("-fx-border-size: 2;")
                        .concat("-fx-border-color: #5a55ff;")
                        .concat("-fx-border-radius: 5;")
                        .concat("-fx-min-height:" + 40 + ";")
                        .concat("-fx-pref-height:" + height + ";")
        );

        try{
            Platform.runLater((() -> boxWithMessages.getChildren().add(boxMes)));
        }catch (UnsupportedOperationException e){
            e.printStackTrace();
        }
//        scrollPane.setOnScrollFinished();

    }


    private void showWorkMessage(String text) {

        Label boxMes = new Label();
        boxMes.setWrapText(true);
        boxMes.setTextAlignment(TextAlignment.CENTER);
        boxMes.setAlignment(Pos.BASELINE_CENTER);
        boxMes.setText(text);
        heightBoxWithMessages += 35;

        if(pane.getHeight() < heightBoxWithMessages){
            pane.setPrefHeight(heightBoxWithMessages);
        }
        boxWithMessages.setPrefHeight(pane.getHeight());

        System.out.println(heightBoxWithMessages + " ; " + pane.getHeight());

        boxMes.styleProperty().bind(
                new SimpleStringProperty("-fx-background-color: #e3fef;")
                        .concat("-fx-font-size: 12px;")
                        .concat("-fx-font-color: #000000;")
                        .concat("-fx-background-radius: 5;")
//                        .concat("-fx-background-insets: 0;")
                        .concat("-fx-border-size: 2;")
                        .concat("-fx-border-color: #5a55ff;")
                        .concat("-fx-border-radius: 5;")
                        .concat("-fx-min-height:" + 35 + ";")
        );

        try{
            Platform.runLater((() -> boxWithMessages.getChildren().add(boxMes)));
        }catch (UnsupportedOperationException e){
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        assert boxWithMessages != null : "fx:id=\"boxWithMessages\" was not injected: check your FXML file 'Chat.fxml'.";
        assert buttonDisconnect != null : "fx:id=\"buttonDisconnect\" was not injected: check your FXML file 'Chat.fxml'.";
        assert inputMessageField != null : "fx:id=\"inputMessageField\" was not injected: check your FXML file 'Chat.fxml'.";
        assert nameClientLabel != null : "fx:id=\"nameClientLabel\" was not injected: check your FXML file 'Chat.fxml'.";
        assert namesClientsField != null : "fx:id=\"namesClientsField\" was not injected: check your FXML file 'Chat.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'Chat.fxml'.";
    }

    public void createClientListener(Client client) {
//        ClientListener clientListener = new ClientListener(client);
//        clientListener.start();
        this.client = client;
        clientSocket = client.getClientSocket();
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        try{
                            in = new DataInputStream(clientSocket.getInputStream());
                            out = new DataOutputStream(clientSocket.getOutputStream());
                            while (isWorkingChat) {
//
                                String entr = in.readUTF(); // странная хрень с чтением одного и того же, пофиксил как мог
                                String entry = in.readUTF();

                                Gson gson = new Gson();
                                MessageServer messageServer = gson.fromJson(entry, MessageServer.class);
                                System.out.println("READ from clientDialog message - " + entry);

//                    MessageUser messageUser = null;
//                    String nickClient = messageUser.getName();
                                if (messageServer.getTypeMessage() ==  LOGIN_SUCCESS) {
                                    //list увеличивается, появляется сообщение о новом челиксе
                                    listUsers.add(messageServer.getName());
                                    updateListUsers();
                                    showWorkMessage(messageServer.getName() + TEXT_NEW_CLIENT);
                                }
                                else if(messageServer.getTypeMessage() == MESSAGE_TYPE){
                                    addNewMessage(messageServer.getMessageText(), messageServer.getName());
                                }
                                else if(messageServer.getTypeMessage() == LOGOUT){
                                    listUsers.remove(messageServer.getName());
                                    updateListUsers();
                                    showWorkMessage(messageServer.getName() + TEXT_LOGOUT_CLIENT);
                                }

                                System.out.println("Input packet was processed.");
                                System.out.println(in.available());

                            }

                        } catch (IOException | NullPointerException e) {
//                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    public void logout(){
        setWorkingChat(false);
        Gson gson = new Gson();
        MessageUser messageUser = new MessageUser(LOGOUT ,name, "");
        try {
            Sending.sendMessage(out, gson.toJson(messageUser));
        }catch(IOException e){
            System.out.println("--------Trouble with out of socket!-----------");
        }
    }

    public void showHistory(List<PairClientMessage> historyMessages) {
        for(PairClientMessage pair : historyMessages){
            addNewMessage(pair.getMessage(), pair.getName());
        }
    }


//    class ClientListener extends Thread {
//        Client client;
//        Socket clientSocket;
//
//        public ClientListener(Client client) {
//            this.client = client;
//            this.clientSocket = client.getClientSocket();
//        }
//
//        @Override
//        public void run() {
//            try{
//                in = new DataInputStream(clientSocket.getInputStream());
//                out = new DataOutputStream(clientSocket.getOutputStream());
//                while (true) {
////
//                    String entry = in.readUTF();
//                    Gson gson = new Gson();
//                    MessageServer messageServer = gson.fromJson(entry, MessageServer.class);
//                    System.out.println("READ from clientDialog message - " + entry);
//
////                    MessageUser messageUser = null;
////                    String nickClient = messageUser.getName();
//                    if (messageServer.getTypeMessage() ==  LOGIN_SUCCESS) {
//                        //list увеличивается, появляется сообщение о новом челиксе
//                        listUsers.add(messageServer.getName());
//                        showWorkMessage(messageServer.getName() + TEXT_NEW_CLIENT);
//                    }
//                    else if(messageServer.getTypeMessage() == MESSAGE_TYPE){
//                        addNewMessage(messageServer.getMessageText(), messageServer.getName());
//                    }
//                    else if(messageServer.getTypeMessage() == LOGOUT){
//                        listUsers.remove(messageServer.getName());
//                    }
//
//                    System.out.println("Input packet was processed.");
//                }
//
//            } catch (IOException | NullPointerException e) {
//                e.printStackTrace();
//            }
//        }


//    }
}

