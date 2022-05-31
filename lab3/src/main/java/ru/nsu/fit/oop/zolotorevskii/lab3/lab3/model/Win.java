package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.HEIGHT_TOP;
import static ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants.PATH_WINNERS;

public class Win {
    private double time;
    public void updateFile(String time, ParamGame paramGame) {
        String[] number;
        number = time.split(",");
        double afterDot = 0;
        try{
            afterDot =  Double.parseDouble(number[1]);
        }
        catch (ArrayIndexOutOfBoundsException e){}

        this.time = Double.parseDouble(number[0]) + afterDot / 10;
        System.out.println(this.time);

        Map<String, Double> topMap = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(PATH_WINNERS + paramGame.getDifficult() + "Top.txt"));
            for (int i = 0; i < HEIGHT_TOP; i++) {
                String[] nameValue = scanner.nextLine().split(" ");
                topMap.put(nameValue[0], Double.parseDouble(nameValue[1]));
            }
            if(!topMap.containsKey(paramGame.getName())){
                topMap.put(paramGame.getName(), this.time);
            }
            else if(topMap.get(paramGame.getName()) > this.time){
                topMap.remove(paramGame.getName());
                topMap.put(paramGame.getName(), this.time);
            }
            topMap = topMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .limit(HEIGHT_TOP)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            FileWriter writer = new FileWriter( PATH_WINNERS + paramGame.getDifficult() + "Top.txt",false);
            for (var item : topMap.entrySet()) {
                String nick = item.getKey();
                if(nick ==null) nick = "Stranger";
                writer.write(nick + " " + item.getValue() + "\n");
                writer.flush();
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
