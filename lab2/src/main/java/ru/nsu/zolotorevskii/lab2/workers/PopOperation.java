package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

/**
 * Class realizes pop from stack
 * @see IOperation
 */
public class PopOperation implements IOperation {
    private static final Logger logger = LogManager.getLogger(PopOperation.class);

    public PopOperation(){}

    /**
     * Method realized pop from stack
     * @param stack Стэк чисел с которым работает программа
     * @param variables Параметры для работы операции
     * @param parameters Параметры, задефайненые с помощью класса DefineOperation
     * @param writer Для записи в файл результата операции
     */
    @Override
    public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) throws EmptyStackException {
        try{
            stack.pop();
        }catch(EmptyStackException e){
            logger.error("Stack is empty");
        }
    }

    @Override
    public int getNumberOfParams() {
        return 0;
    }
}
