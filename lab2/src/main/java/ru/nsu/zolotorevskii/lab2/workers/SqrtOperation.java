package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.Map;
import java.util.Stack;

public class SqrtOperation implements IWorker {
    private static final Logger logger = LogManager.getLogger(SqrtOperation.class);

    public SqrtOperation() {

    }

    @Override
    public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) throws ArithmeticException {
        if (stack.size() == 0) {
            logger.error("Stack is empty");
        } else if (stack.size() == 1) {
            logger.error("Stack have only 1 number");
        } else {
            Double lastElem = stack.pop();
//            Double f1 = new Double(lastElem);
            if(lastElem.isNaN() || lastElem < 0 || lastElem.isInfinite()){
                throw new ArithmeticException();
            }
            stack.push(Math.sqrt(lastElem));
        }
    }

    @Override
    public int getNumberOfParams() {
        return 0;
    }


}
