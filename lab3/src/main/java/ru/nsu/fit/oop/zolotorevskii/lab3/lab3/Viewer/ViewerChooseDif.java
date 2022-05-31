package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerMainMenu;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.GameController;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.ParamGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_GAME_CONTROLLER_FXML;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_MAIN_MENU_FXML;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PathImages.IMAGE_X;

public class ViewerChooseDif{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private TextField buttonCountMines;

    @FXML
    private Button buttonEasy;

    @FXML
    private Button buttonHard;

    @FXML
    private TextField buttonHeight;

    @FXML
    private TextField buttonLength;

    @FXML
    private Button buttonMedium;

    @FXML
    private Button buttonPersonal;

    public void loadMainMenu(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_FXML));
        Parent root = fxmlLoader.load();
        ControllerMainMenu controllerMainMenu = fxmlLoader.getController();
        controllerMainMenu.setName(name);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public String getHeight() {
        return buttonHeight.getText();
    }

    public String getLenght() {
        return buttonLength.getText();
    }

    public String getMines() {
        return buttonCountMines.getText();
    }

    @FXML
    void mouseInBack(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonBack.setEffect(shadow);
    }

    @FXML
    void mouseInEaseGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonEasy.setEffect(shadow);
    }

    @FXML
    void mouseInHardGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonHard.setEffect(shadow);
    }

    @FXML
    void mouseInMediumGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonMedium.setEffect(shadow);
    }

    @FXML
    void mouseInPersonalGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonPersonal.setEffect(shadow);
    }

    @FXML
    void mouseOutBack(MouseEvent event) {
        buttonBack.setEffect(null);
    }

    @FXML
    void mouseOutEaseGame(MouseEvent event) {
        buttonEasy.setEffect(null);
    }

    @FXML
    void mouseOutHardGame(MouseEvent event) {
        buttonHard.setEffect(null);
    }

    @FXML
    void mouseOutMediumGame(MouseEvent event) {
        buttonMedium.setEffect(null);
    }

    @FXML
    void mouseOutPersonalGame(MouseEvent event) { buttonPersonal.setEffect(null);    }

    @FXML
    void initialize() {
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonCountMines != null : "fx:id=\"buttonCountMines\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonEasy != null : "fx:id=\"buttonEasy\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonHard != null : "fx:id=\"buttonHard\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonHeight != null : "fx:id=\"buttonHeight\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonLength != null : "fx:id=\"buttonLength\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonMedium != null : "fx:id=\"buttonMedium\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";
        assert buttonPersonal != null : "fx:id=\"buttonPersonal\" was not injected: check your FXML file 'chooseDifficulty.fxml'.";

    }

    public void loadGameWindow(ParamGame paramsGame) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource(PATH_GAME_CONTROLLER_FXML));
        Parent root = fxmlLoader.load();
        GameController controller = fxmlLoader.getController();
        controller.setGameParams(paramsGame);
        Stage stage =(Stage) buttonEasy.getScene().getWindow();
        stage.setX(0);
        stage.setY(0);
        stage.setHeight(300);
        stage.setWidth(300);

        if((paramsGame.getLength() + 1) * IMAGE_X > stage.getWidth()){
            stage.setWidth((paramsGame.getLength() + 1) * IMAGE_X );
        }

        if((paramsGame.getHeight() + 1) * IMAGE_X > stage.getHeight()){
            stage.setHeight(((paramsGame.getHeight() + 3) * IMAGE_X ));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
