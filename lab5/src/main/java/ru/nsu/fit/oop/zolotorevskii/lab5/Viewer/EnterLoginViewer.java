package ru.nsu.fit.oop.zolotorevskii.lab5.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.nsu.fit.oop.zolotorevskii.lab5.Controller.ChatController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.MAX_LENGTH_NAME;
import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.PATH_FXML;

public class EnterLoginViewer {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginLabel;


    protected String getLoginLabel() {
        return loginLabel.getText();
    }

    protected void setlabelNickText(String name) {
        int pos = loginLabel.getCaretPosition();
        loginLabel.setText(name);
        loginLabel.positionCaret(pos);
    }

    protected void launchChat(String name) throws IOException {
        Stage stage = (Stage) loginLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(FirstWindowViewer.class.getResource(PATH_FXML + "Chat.fxml"));
        Parent root = fxmlLoader.load();
        ChatController controller = fxmlLoader.getController();
        controller.setName(name);
        controller.connectToServer();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        assert loginLabel != null : "fx:id=\"loginLabel\" was not injected: check your FXML file 'Untitled'.";

    }
}
