package ru.nsu.fit.oop.zolotorevskii.lab5.Model;

import static ru.nsu.fit.oop.zolotorevskii.lab5.Constants.MAX_LENGTH_NAME;

public class EnterLoginModel {
    public String correctingNick(String name) {
        if(name.length() > MAX_LENGTH_NAME){
            name = name.substring(0,MAX_LENGTH_NAME);
        }
        return name;
    }

}
