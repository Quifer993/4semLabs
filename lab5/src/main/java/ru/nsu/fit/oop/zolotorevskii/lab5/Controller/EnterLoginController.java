package ru.nsu.fit.oop.zolotorevskii.lab5.Controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Client;
import ru.nsu.fit.oop.zolotorevskii.lab5.Model.EnterLoginModel;
import ru.nsu.fit.oop.zolotorevskii.lab5.Viewer.EnterLoginViewer;

import java.io.IOException;

public class EnterLoginController extends EnterLoginViewer {
    EnterLoginModel model = new EnterLoginModel();

    @FXML
    void typedLoginLabel(KeyEvent event) throws IOException {
        if(event.getCode().equals(KeyCode.ENTER)){
            if(getLoginLabel().length() != 0){
                Client client = new Client();
                client.connect();
                launchChat(getLoginLabel());
            }
        }
    }


    @FXML
    void correctLengthName(KeyEvent event) {
        String name = model.correctingNick(getLoginLabel());
        setlabelNickText(name);
    }

}
