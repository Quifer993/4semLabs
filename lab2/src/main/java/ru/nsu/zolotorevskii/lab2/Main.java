package ru.nsu.zolotorevskii.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ru.nsu.zolotorevskii.lab2.Constants.ALL_ARGS_FILES;
import static ru.nsu.zolotorevskii.lab2.Constants.StdinCalc;
import static ru.nsu.zolotorevskii.lab2.Constants.FileCalc;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            logger.info("Program works with stdin");
            Calc workPlace = new Calc(StdinCalc, args);
            workPlace.execute();
        }
        else if (args.length == ALL_ARGS_FILES) {
            logger.info("Program works with input file");
            Calc workPlace = new Calc(FileCalc, args);
            workPlace.execute();
        }
        else {
            logger.error("Wrong number of input arguments");
        }
    }
}
