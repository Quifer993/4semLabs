package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerMainMenu;

public class ControllerMainMenu extends ViewerMainMenu {


    @FXML
    void ClickedExitGame(MouseEvent event){
        System.exit(0);
    }

    String name;

    public void setName(String name) {
        this.name = name;
        setLabelNameAndStage(name);
    }

    @FXML
    void ClickedStartGame(MouseEvent event) throws IOException {
        name = getLabelName();
        try{
            int limit = 10;
            if(name.length() > limit){
                name =  name.codePointCount(0,  name.length()) > limit ?
                        name.substring(0,  name.offsetByCodePoints(0, limit)) :  name;
            }
        }catch(NullPointerException e){}


        loadChooseDif(name);

    }

    @FXML
    void ClickedTopGame(MouseEvent event) throws IOException {
        loadTop(getLabelName());
    }


}

