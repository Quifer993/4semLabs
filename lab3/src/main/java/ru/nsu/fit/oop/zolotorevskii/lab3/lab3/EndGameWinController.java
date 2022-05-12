package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.HEIGHT_TOP;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.PATH_WINNERS;

public class EndGameWinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonClose;

    @FXML
    private DialogPane buttonEndGame;

    @FXML
    private Text labelTime;
    private double time;

    @FXML
    void clickedButtonClose(MouseEvent event) throws RuntimeException {
        GameApplication.stage.close();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert buttonClose != null : "fx:id=\"buttonClose\" was not injected: check your FXML file 'EndGameWin.fxml'.";
        assert buttonEndGame != null : "fx:id=\"buttonEndGame\" was not injected: check your FXML file 'EndGameWin.fxml'.";

    }

    public void setWinnerStatistics(String time, String difficult, String name) {
        String[] number;
        number = time.split(",");
        double afterDot = 0;
        try{
            afterDot =  Double.parseDouble(number[1]);
        }
        catch (ArrayIndexOutOfBoundsException e){}

        this.time = Double.parseDouble(number[0]) + afterDot / 10;
        System.out.println(this.time);
        labelTime.setText(this.time + " сек");

        Map<String, Double> topMap = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(PATH_WINNERS + difficult + "Top.txt"));
            for (int i = 0; i < HEIGHT_TOP; i++) {
                String[] nameValue = scanner.nextLine().split(" ");
                topMap.put(nameValue[0], Double.parseDouble(nameValue[1]));
            }
            if(!topMap.containsKey(name)){
                topMap.put(name, this.time);
            }
            else if(topMap.get(name) > this.time){
                topMap.remove(name);
                topMap.put(name, this.time);
            }
//            topMap.put(name, this.time);
            topMap = topMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .limit(HEIGHT_TOP)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

//            Writer writer = new FileWriter();

            FileWriter writer = new FileWriter( PATH_WINNERS + difficult + "Top.txt", false);
            for (var item : topMap.entrySet()) {
                String nick = item.getKey();
                if(nick ==null) nick = "Stranger";
                writer.write(nick + " " + item.getValue() + "\n");
                writer.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
