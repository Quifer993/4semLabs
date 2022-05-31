package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.ControllerMainMenu;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers.EndGameWinController;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.ParamGame;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.TimerGame;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.BOMB_DEFUSED;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_MAIN_MENU_FXML;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PathImages.*;

public class ViewerGame {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem buttonExitGame;

    @FXML
    private MenuItem buttonMenu;

    @FXML
    private GridPane gridPane;

    @FXML
    private Text nameLabel;

    @FXML
    private Text textFlags;

    @FXML
    private Text timeLabel;

    protected void timerShow(TimerGame timerGame) {
        timerGame.timerGo(timeLabel);
    }

    protected void setLabelFlags(int countFlags) {
        textFlags.setText(((Integer)countFlags).toString());
    }
    protected void setLabelName(String name) {
        nameLabel.setText(name);
    }

    protected String getTimeLabel(){
        return timeLabel.getText();
    }

    protected void showCell(String typeBomb, int i, int j) {
        Image im = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(PATH_CELLS + typeBomb + ".png")));
        gridPane.add(new ImageView(im), i, j);
    }

    protected void loadMainMenu(String name) throws IOException {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.show();
        FXMLLoader fxmlLoaderMain = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_FXML));
        Parent root = fxmlLoaderMain.load();
        ControllerMainMenu controllerMainMenu = fxmlLoaderMain.getController();
        controllerMainMenu.setName(name);
        Scene sceneMain = new Scene(root);
        stage.setHeight(HEIGHT_MAIN_MENU);
        stage.setWidth(LENGTH_MAIN_MENU);
        stage.setX(X_MAIN_MENU);
        stage.setY(Y_MAIN_MENU);
        stage.setScene(sceneMain);
        stage.show();
    }

    protected void loadEndStage(String typeBomb, String nameFxmlEndType, ParamGame paramsGame, String timeNow) throws IOException {
        Stage stageEndGame  = new Stage();
        stageEndGame.setAlwaysOnTop(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nameFxmlEndType));
        Parent root = fxmlLoader.load();
        EndGameWinController controller;

        if(Objects.equals(typeBomb, BOMB_DEFUSED)){
            controller = fxmlLoader.getController();
            controller.setWinnerStatistics(timeNow, paramsGame);
        }

        Scene scene = new Scene(root);
        stageEndGame.setScene(scene);
        stageEndGame.showAndWait();
    }

    @FXML
    void initialize() {
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'GameController.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'GameController.fxml'.";
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'GameController.fxml'.";
    }
}
