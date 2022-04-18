package ru.nsu.zolotorevskii.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.zolotorevskii.lab2.workers.IWorker;
import ru.nsu.zolotorevskii.lab2.workers.PopOperation;

import java.io.*;
import java.util.*;
import ru.nsu.zolotorevskii.lab2.exceptions.amountOfParametersException;

import static ru.nsu.zolotorevskii.lab2.Constants.*;


public class Calc {
    private static final String EXIT = "exit";
    private static final int SHIFT = 1;
    private static final int BEGIN = 0;

    private static final Logger logger = LogManager.getLogger(Calc.class);
    Map<String, Double> parameters;

    String workZone;
    Scanner reader;
    PrintWriter writer;

    Calc(String nameZone, String[] args) {
        parameters = new HashMap<>();
        workZone = nameZone;
        try {
            if (workZone.equals(FileCalc)) {
                File fileIn = new File(args[INPUT_FILE]);
                reader = new Scanner(fileIn);
                writer = new PrintWriter(args[OUTPUT_FILE]);
                logger.info("Reader get file");
            } else {
                reader = new Scanner(System.in);
                writer = new PrintWriter(System.out);
                logger.info("Reader get stdin");
            }
        } catch (FileNotFoundException e) {
            logger.error("File reading error: ", e);
        }
    }

    public void execute() {
        logger.info("Start work calculate`s execute");

        Factory factory = new Factory();
        Stack<Double> stack = new Stack<>();
        try {
            String workLine = reader.nextLine();
            while (!workLine.equalsIgnoreCase(EXIT)) {
                if (workLine.equals("")) {
                    workLine = reader.nextLine();
                    continue;
                }
                String[] partsOfLine = workLine.trim().split(SPACE);

                IWorker worker = factory.getWorker(partsOfLine[POS_NAME_OPERATION]);

                if (partsOfLine.length - SHIFT < worker.getNumberOfParams()) {
                    throw new amountOfParametersException(partsOfLine.length - SHIFT, worker.getNumberOfParams());
                }

                String[] variables = new String[worker.getNumberOfParams()];
                System.arraycopy(partsOfLine, SHIFT, variables, BEGIN, worker.getNumberOfParams());


                worker.work(stack, variables, parameters, writer);
                workLine = reader.nextLine();
            }
        } catch (amountOfParametersException e) {
            logger.error(e.getMessage());
        } catch (ArithmeticException e) {
            logger.error("Arithmetic trouble");
            System.exit(ERROR_CODE);
        } catch (NoSuchElementException e) {
            logger.info("End of file reached");
        }
    }
}
