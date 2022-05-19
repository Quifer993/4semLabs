package ru.nsu.fit.oop.zolotorevskii.lab3.lab3;

import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants.Constants;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerFxml2;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerType;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerFxml1;

public class Main {
    public static void main(String[] args) {
        ViewerType viewer = new ViewerFxml1();
//        Constants.viewSetting(2);
        viewer.showMainMenu();
    }
}
