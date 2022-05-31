package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerMainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_MAIN_MENU_FXML;

public class ViewerTopOpen{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Text textDifficult;

    @FXML
    private Text textWiiners1_5;

    @FXML
    private Text textWiiners6_10;


    protected void setDiff(String type) {
        textDifficult.setText(type);
    }

    protected void setWinTime(String time) {
        textWiiners6_10.setText(time);
    }

    protected void setWinNames(String name) {
        textWiiners1_5.setText(name);
    }

    protected void loadMainMenu(String name) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        FXMLLoader fxmlLoaderMain = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_FXML));

        Parent root = fxmlLoaderMain.load();
        ControllerMainMenu controllerTop = fxmlLoaderMain.getController();
        controllerTop.setName(name);
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
    void mouseOutBack(MouseEvent event) {
        buttonBack.setEffect(null);
    }

    @FXML
    void initialize() {
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert textDifficult != null : "fx:id=\"textDifficult\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert textWiiners1_5 != null : "fx:id=\"textWiiners1_5\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert textWiiners6_10 != null : "fx:id=\"textWiiners6_10\" was not injected: check your FXML file 'MainMenu.fxml'.";

    }
}
