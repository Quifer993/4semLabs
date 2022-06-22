package ru.nsu.fit.oop.zolotorevskii.lab5.Controller;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Client;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.EnterLoginModel;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageServer;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Messages.MessageUser;
import ru.nsu.fit.oop.zolotorevskii.lab5.Viewer.EnterLoginViewer;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.*;

public class EnterLoginController extends EnterLoginViewer {
    EnterLoginModel model = new EnterLoginModel();
    String name;

    @FXML
    void typedLoginLabel(KeyEvent event) throws IOException {
        correctLengthName(event);
        if(event.getCode().equals(KeyCode.ENTER)){
            if(getLoginLabel().length() != 0){
                Client client = new Client(name);
                Socket clientSocket = new Socket();
                try{
                    MessageServer answerServ = client.connect(clientSocket);
                    if(answerServ.getTypeMessage() == LOGIN_SUCCESS){
                        launchChat(getLoginLabel(), client, answerServ);
                    }
                    else if(answerServ.getTypeMessage() == LOGIN_ERROR){
                        showErrorMessage("This login already exist, input new login!");
                    }
                    else{
                        showErrorMessage("WTF");
                    }
                }catch(IOException e){
                    showErrorMessage("Сервер недоступен. Повторите попытку позже...");
                } catch (IllegalBlockingModeException e) {
                    System.out.println("Server doesn't work");
                }

            }
        }
    }


    void correctLengthName(KeyEvent event) {
        name = model.correctingNick(getLoginLabel());
        setlabelNickText(name);
    }

}
