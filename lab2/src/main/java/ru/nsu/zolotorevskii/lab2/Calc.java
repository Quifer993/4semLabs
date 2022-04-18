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

    private static final Logger logger = LogManager.getLogger(Calc.class);
    Map<String, Double> parameters;

    String workZone;
    Scanner reader;
    PrintWriter writer;

    Calc(String nameZone, String[] args) throws Exception{
        parameters = new HashMap<>();
        workZone = nameZone;
        try {
            if (workZone.equals(FileCalc)) {
                File fileIn = new File(args[INPUT_FILE]);
                reader = new Scanner(fileIn);
                writer = new PrintWriter(args[OUTPUT_FILE]);
//                writer = new PrintWriter(new FileWriter(args[OUTPUT_FILE]));
                logger.info("Reader get file");
            } else {
                reader = new Scanner(System.in);
                writer = new PrintWriter(System.out);
//                writer = new PrintWriter();
                logger.info("Reader get stdin");
            }
        }
        catch(FileNotFoundException e){
            logger.error("File reading error: ", e);
        }
    }

    public void execute(){
        logger.info("Start work calculate`s execute");

        Factory factory = new Factory();
        Stack<Double> stack = new Stack<>();
        try{
            String workLine = reader.nextLine();
            while(!workLine.equalsIgnoreCase(EXIT)){
                if(workLine.equals("")){
                    workLine = reader.nextLine();
                    continue;
                }
                String[] partsOfLine = workLine.trim().split(SPACE);

                IWorker worker = factory.getWorker(partsOfLine[0]);

                if(partsOfLine.length - 1 < worker.getNumberOfParams()){
                    throw new amountOfParametersException(partsOfLine.length - 1,  worker.getNumberOfParams());
                }

                String[] variables = new String[worker.getNumberOfParams()];
                if (worker.getNumberOfParams() >= 0)
                    System.arraycopy(partsOfLine, 1, variables, 0, worker.getNumberOfParams());


                worker.work(stack, variables, parameters, writer);
                workLine = reader.nextLine();
//            if(parseString(workLine) == END) break;
            }
        }
        catch (amountOfParametersException e){
            logger.error(e.getMessage());
        }
        catch (ArithmeticException e){
            logger.error("Arithmetic trouble");
            System.exit(ERROR_CODE);
        }
        catch (NoSuchElementException e){
            logger.info("End of file reached");
        }


        //обработка строк из файла/терминала
        //

    }


/*    static Factory createWorkerBySpeciality{
*//*        if(){
            return new
        }*//*
    }*/
}
