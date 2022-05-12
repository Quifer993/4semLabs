package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.*;

public class ControllerChooseDif {

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

    @FXML
    void ClickedBackMainMenu(ActionEvent event) throws IOException {
        Launcher.launchNewWindow(getClass(), "MainMenu.fxml");
    }

    @FXML
    void ClickedEaseGame(MouseEvent event) throws IOException {
        Map<String, Integer> paramsOfGame = new HashMap<>();
        paramsOfGame.put(HEIGHT, 9);
        paramsOfGame.put(LENGTH, 9);
        paramsOfGame.put(MINES, 1);
        startGame(EASY, paramsOfGame);

    }

    @FXML
    void ClickedHardGame(MouseEvent event) throws IOException {
        Map<String, Integer> paramsOfGame = new HashMap<>();
        paramsOfGame.put(HEIGHT, 15);
        paramsOfGame.put(LENGTH, 15);
        paramsOfGame.put(MINES, 37);
        startGame(HARD, paramsOfGame);
    }

    @FXML
    void ClickedMediumGame(MouseEvent event) throws IOException {
        Map<String, Integer> paramsOfGame = new HashMap<>();
        paramsOfGame.put(HEIGHT, 12);
        paramsOfGame.put(LENGTH, 12);
        paramsOfGame.put(MINES, 25);
        startGame(MEDIUM, paramsOfGame);
    }

    @FXML
    void ClickedPersonalGame(MouseEvent event) throws IOException {
        boolean isNumCorrect = true;
        Map<String, Integer> paramsOfGame = new HashMap<>();
        try{
            int height = Integer.parseInt(buttonHeight.getText());
            if(height > 22) height =22;
            paramsOfGame.put(HEIGHT,height );
        }catch(NumberFormatException e){
            isNumCorrect = false;
        }
        try{
            int length = Integer.parseInt(buttonLength.getText());
            if(length > 40) length =40;
            paramsOfGame.put(LENGTH, length);
        }catch(NumberFormatException e){
            isNumCorrect = false;
        }
        try{
            paramsOfGame.put(MINES, Integer.parseInt(buttonCountMines.getText()));
        }catch(NumberFormatException e){
            isNumCorrect = false;
        }

        if(paramsOfGame.get(HEIGHT) * paramsOfGame.get(LENGTH) <= paramsOfGame.get(MINES) ){
            isNumCorrect = false;
        }

        if(isNumCorrect){
            startGame(PERSONAL, paramsOfGame);
        }
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
    private void startGame(String typeGame, Map<String, Integer> paramsOfGame) throws IOException {
        Game game = new Game(typeGame, paramsOfGame);
        game.startGame(typeGame, paramsOfGame);
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("GameController.fxml"));
        Parent root = fxmlLoader.load();
        GameController controller = fxmlLoader.getController();

        controller.setParams(Game.name,  paramsOfGame, typeGame);

//        if((length + 1) * IMAGE_X > controller.getBorderPaneLength()){
//            controller.setBorderPaneLength((double)((length + 1) * IMAGE_X ));
//        }

        Scene scene = new Scene(root);
        GameApplication.stage.setScene(scene);
        GameApplication.stage.show();
    }

}
