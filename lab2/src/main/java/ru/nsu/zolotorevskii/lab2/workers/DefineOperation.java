package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class DefineOperation implements IWorker{
    private static final Logger logger = LogManager.getLogger(ru.nsu.zolotorevskii.lab2.workers.DefineOperation.class);

    public DefineOperation(){

    }

    @Override
    public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) {
        try{
            Double.parseDouble(variables[0]);
            logger.error("Define parameter is a number! Use smth else");
        }
        catch(NumberFormatException e){
            try{
                parameters.put(variables[0], Double.parseDouble(variables[1]));
            }
            catch(NumberFormatException e1){
                logger.error( variables[1] + "is not a number!");
            }
        }
    }

    @Override
    public int getNumberOfParams() {
        return 2;
    }
}
