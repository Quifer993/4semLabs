package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Writer;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class PrintOperation implements IWorker{
    private static final Logger logger = LogManager.getLogger(PrintOperation.class);



    public PrintOperation(){

    }

    @Override
    public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) throws EmptyStackException {
        try{
            Double lastElem = stack.peek();
            String line = "Top element is: " + lastElem + System.lineSeparator();
            writer.write(line);
            writer.flush();
        }
        catch(IOException e){
            logger.error("Write error in Print-Operation");
        }
        catch(EmptyStackException e){
            logger.error("Stack is empty");
        }


    }

    @Override
    public int getNumberOfParams() {
        return 0;
    }

}
