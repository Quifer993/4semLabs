package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

public class ViewerFxml1 implements ViewerType {
    public void showMainMenu() {
        GameApplication gameApplication = new GameApplication();
        gameApplication.launchGame();
    }


}
