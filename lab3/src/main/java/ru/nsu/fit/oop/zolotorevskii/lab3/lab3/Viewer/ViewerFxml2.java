package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;

public class ViewerFxml2 implements ViewerType {
    @Override
    public void showMainMenu() {
        GameApplication2 gameApplication = new GameApplication2();
        gameApplication.launchGame();
    }
}
