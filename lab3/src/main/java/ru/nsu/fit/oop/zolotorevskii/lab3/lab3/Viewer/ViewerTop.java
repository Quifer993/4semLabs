package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerMainMenu;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerTopOpen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_MAIN_MENU_FXML;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_TOP_OPEN_FXML;

public class ViewerTop{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEasy;

    @FXML
    private Button buttonHard;

    @FXML
    private Button buttonMedium;


    protected void loadTopOpen(String type, String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TOP_OPEN_FXML));
        Parent root = fxmlLoader.load();
        ControllerTopOpen controller = fxmlLoader.getController();
        controller.setText(type);
        controller.setName(name);
        Stage stage = (Stage) buttonEasy.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void loadMainMenu(String name) throws IOException {
        Stage stage = (Stage) buttonEasy.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_FXML));
        Parent root = fxmlLoader.load();
        ControllerMainMenu controllerMainMenu = fxmlLoader.getController();
        controllerMainMenu.setName(name);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void mouseInBack(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonBack.setEffect(shadow);
    }

    @FXML
    void mouseInEasy(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonEasy.setEffect(shadow);
    }

    @FXML
    void mouseInHard(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonHard.setEffect(shadow);
    }

    @FXML
    void mouseInMedium(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonMedium.setEffect(shadow);
    }

    @FXML
    void mouseOutBack(MouseEvent event) {
        buttonBack.setEffect(null);
    }

    @FXML
    void mouseOutEasy(MouseEvent event) {
        buttonEasy.setEffect(null);
    }

    @FXML
    void mouseOutHard(MouseEvent event) {
        buttonHard.setEffect(null);
    }

    @FXML
    void mouseOutMedium(MouseEvent event) {
        buttonMedium.setEffect(null);
    }

    @FXML
    void initialize() {
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonEasy != null : "fx:id=\"buttonEasy\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonHard != null : "fx:id=\"buttonHard\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonMedium != null : "fx:id=\"buttonMedium\" was not injected: check your FXML file 'MainMenu.fxml'.";

    }

}
