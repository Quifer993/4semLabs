package ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants;

public class Constants {
    public static final String NAME_GAME = "Sapper";
    public static final String ICON_PATH = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/icon.png";

    public static final String PERSONAL = "PERSONAL";
    public static final String MEDIUM = "MEDIUM";
    public static final String EASY = "EASY";
    public static final String HARD = "HARD";

    public static final String MINES = "Mines";
    public static final String LENGTH = "Length";
    public static final String HEIGHT = "Height";

    public static final String BOMB_UNDEFUSED = "bomb";
    public static final String BOMB_DEFUSED = "bombDefuse";

    public static final String PATH_WINNERS = "src/main/resources/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/WinnersTops/";
    public static final String PATH_WINNERS_SHORT = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/WinnersTops/";

    public static String PATH_TOP_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/Top.fxml";
    public static String PATH_TOP_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/Top2.fxml";
    public static String PATH_MAIN_MENU_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/MainMenu.fxml";
    public static final String PATH_MAIN_MENU_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/MainMenu2.fxml";
    public static String PATH_GAME_CONTROLLER_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/GameController.fxml";
    public static final String PATH_GAME_CONTROLLER_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/GameController2.fxml";
    public static String PATH_CHOOSE_DIF_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/chooseDifficulty.fxml";
    public static final String PATH_CHOOSE_DIF_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/chooseDifficulty2.fxml";
    public static String PATH_TOP_OPEN_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/TopOpen.fxml";
    public static final String PATH_TOP_OPEN_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/TopOpen2.fxml";
    public static String PATH_WIN_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/EndGameWin.fxml";
    public static final String PATH_WIN_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/EndGameWin2.fxml";
    public static String PATH_LOSE_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/EndGameLose.fxml";
    public static final String PATH_LOSE_FXML2 = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/EndGameLose2.fxml";

    static public void viewSetting(int value){
        if(value == 2){
            PATH_TOP_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/Top2.fxml";

            PATH_MAIN_MENU_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/MainMenu2.fxml";

            PATH_GAME_CONTROLLER_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/GameController2.fxml";

            PATH_CHOOSE_DIF_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/chooseDifficulty2.fxml";

            PATH_TOP_OPEN_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/TopOpen2.fxml";

            PATH_WIN_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/EndGameWin2.fxml";

            PATH_LOSE_FXML = "/ru/nsu/fit/oop/zolotorevskii/lab3/lab3/EndGameLose2.fxml";

        }
    }

    public static final double MLS_IN_SEC100 = 0.100;
    public static final long MLS = 1;
    public static final long MLS100 = 100;
    public static final int HEIGHT_TOP = 10;

}