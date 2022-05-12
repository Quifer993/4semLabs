package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PATH_WINNERS;

public class ControllerTopOpen {

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


    public void setText(String type) {
        textDifficult.setText(type);

        try(BufferedReader reader = new BufferedReader(new FileReader(PATH_WINNERS + type + "Top.txt"))){

            String[] nameTime;
            String name = "";
            String time = "";
            for(int i = 1; i< 10; i++){
                nameTime = reader.readLine().split(" ");
                name += i + ". " + nameTime[0] + "\n";
                time += nameTime[1] + "\n";
            }


            textWiiners1_5.setText(name);
            textWiiners6_10.setText(time);

        }catch(FileNotFoundException e){
            System.out.println("file with " + type + " winners not found");
        }catch (IOException e){
            System.out.println("file with " + type + " winners was damaged");
        }
    }
    @FXML
    void clickedBack(MouseEvent event) throws IOException {
        Launcher.launchNewWindow(getClass(), "MainMenu.fxml");
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
