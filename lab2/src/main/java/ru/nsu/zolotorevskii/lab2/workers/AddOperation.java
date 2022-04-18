package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class AddOperation implements IWorker{
    private static final Logger logger = LogManager.getLogger(ru.nsu.zolotorevskii.lab2.workers.AddOperation.class);

    public AddOperation(){

    }

    @Override
    public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) {
        if (stack.size() == 0){
            logger.error("Stack is empty");
        } else if (stack.size() == 1){
            logger.error("Stack have only 1 number");
        } else {
            stack.push(stack.pop() + stack.pop());
        }
    }

    @Override
    public int getNumberOfParams() {
        return 0;
    }
}