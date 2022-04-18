package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.Map;
import java.util.Stack;

/**
 * Class realizes subtraction with 2 top`s numbers from stack and push it on stack
 * @see IOperation
 */
public class SubOperation implements IOperation {
    private static final Logger logger = LogManager.getLogger(SubOperation.class);

    public SubOperation() {}

    /**
     * Method realized subtraction with 2 top`s numbers from stack and put result on top
     * pop 2 number // push 1 number
     * @param stack Стэк чисел с которым работает программа
     * @param variables Параметры для работы операции
     * @param parameters Параметры, задефайненые с помощью класса DefineOperation
     * @param writer Для записи в файл результата операции
     */
    @Override
    public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) throws ArithmeticException {
        if (stack.size() == 0) {
            logger.error("Stack is empty");
        } else if (stack.size() == 1) {
            logger.error("Stack have only 1 number");
        } else {
            stack.push(stack.pop() - stack.pop());
        }
    }

    @Override
    public int getNumberOfParams() {
        return 0;
    }
}
