package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer.ViewerEndGameWin;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.ParamGame;
import ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model.Win;

public class EndGameWinController extends ViewerEndGameWin {
    public void setWinnerStatistics(String time, ParamGame paramGame) {
        Win win = new Win();
        setTimeLabel(time);
        win.updateFile(time, paramGame);
    }
}
