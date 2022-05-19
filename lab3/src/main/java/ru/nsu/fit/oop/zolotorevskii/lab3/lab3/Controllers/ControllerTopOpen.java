package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;

import java.io.*;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerTopOpen;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.*;

public class ControllerTopOpen extends ViewerTopOpen {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String type) {
        setDiff(type);
            try(Scanner scanner = new Scanner(new File(PATH_WINNERS + type + "Top.txt"))){
            String[] nameTime;
            String name = "";
            String time = "";
            for(int i = 1; i<= 10; i++){
                nameTime = scanner.nextLine().split(" ");
                name += i + ". " + nameTime[0] + "\n";
                time += nameTime[1] + "\n";
            }

            setWinNames(name);
            setWinTime(time);
        }catch(FileNotFoundException e){
            System.out.println("file with " + type + " winners not found");
        }
    }

    @FXML
    void clickedBack(MouseEvent event) throws IOException {
        loadMainMenu(name);
    }
}
