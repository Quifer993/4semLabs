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
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerChooseDif;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerTop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_CHOOSE_DIF_FXML;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_TOP_FXML;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PathImages.HEIGHT_MAIN_MENU;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PathImages.LENGTH_MAIN_MENU;

public class ViewerMainMenu{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExitGame;

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonTopGame;

    @FXML
    private TextField NameLabel;


    public void setLabelNameAndStage(String name) {
//        Stage stage = (Stage) buttonStartGame.getScene().getWindow();
//        stage.setHeight(HEIGHT_MAIN_MENU);
//        stage.setWidth(LENGTH_MAIN_MENU);
        NameLabel.setText(name);
    }

    protected String getLabelName() {
        return NameLabel.getText();
    }

    protected void loadChooseDif(String name) throws IOException {
        Stage stage = (Stage) buttonStartGame.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_CHOOSE_DIF_FXML));
        Parent root = fxmlLoader.load();
        ControllerChooseDif controllerChooseDif = fxmlLoader.getController();
        controllerChooseDif.setName(name);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    protected void loadTop(String name) throws IOException {
        Stage stage = (Stage) buttonStartGame.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TOP_FXML));
        Parent root = fxmlLoader.load();
        ControllerTop controllerTop = fxmlLoader.getController();
        controllerTop.setName(name);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void mouseInExitGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonExitGame.setEffect(shadow);
    }

    @FXML
    void mouseInStartGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonStartGame.setEffect(shadow);
    }

    @FXML
    void mouseInTopGame(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        buttonTopGame.setEffect(shadow);
    }

    @FXML
    void mouseOutExitGame(MouseEvent event) {
        buttonExitGame.setEffect(null);
    }

    @FXML
    void mouseOutStartGame(MouseEvent event) {
        buttonStartGame.setEffect(null);
    }

    @FXML
    void mouseOutTopGame(MouseEvent event) {
        buttonTopGame.setEffect(null);
    }


    @FXML
    void initialize() {
        assert buttonExitGame != null : "fx:id=\"buttonExitGame\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert buttonTopGame != null : "fx:id=\"buttonTopGame\" was not injected: check your FXML file 'MainMenu.fxml'.";
    }

}
