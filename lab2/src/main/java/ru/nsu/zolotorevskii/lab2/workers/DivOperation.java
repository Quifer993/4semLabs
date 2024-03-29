package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.Map;
import java.util.Stack;

/**
 * Class realizes operation division with 2 top`s numbers from stack
 * @see IOperation
 */
public class DivOperation implements IOperation {
    private static final Logger logger = LogManager.getLogger(ru.nsu.zolotorevskii.lab2.workers.DivOperation.class);

    public DivOperation() {}

    /**
     * Method realized operation division with 2 top`s numbers from stack and put result on top
     * pop 2 number // push 1 number
     * @param stack Стэк чисел с которым работает программа
     * @param variables Параметры для работы операции
     * @param parameters Параметры, задефайненые с помощью класса DefineOperation
     * @param writer Для записи в файл результата операции
     */
    @Override
    public void work(Stack<Double> stack, String[] variables, Map<String, Double> parameters, Writer writer) throws ArithmeticException {
        if (stack.size() == 0) {
            logger.error("Stack is empty");
        } else if (stack.size() == 1) {
            logger.error("Stack have only 1 number");
        } else {
            Double first = stack.pop();
            Double second = stack.pop();
            Double res = first / second;
            if(res.isNaN() || res.isInfinite()){
                throw new ArithmeticException();
            }
            stack.push(res);
        }
    }

    @Override
    public int getNumberOfParams() {
        return 0;
    }
}
