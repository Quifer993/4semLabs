package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

/**
 * Class realizes push to stack
 * @see IOperation
 */
public class PushOperation implements IOperation {
        private static final Logger logger = LogManager.getLogger(ru.nsu.zolotorevskii.lab2.workers.PushOperation.class);
        private static final int VALUE_PARAM = 0;
        public PushOperation(){}

    /**
     * Method realized push to stack
     * @param stack Стэк чисел с которым работает программа
     * @param variables Параметры для работы операции
     * @param parameters Параметры, задефайненые с помощью класса DefineOperation
     * @param writer Для записи в файл результата операции
     */
        @Override
        public void work(Stack<Double> stack, String variables[], Map<String, Double> parameters, Writer writer) throws EmptyStackException {
            double num;
            try {
                num = Double.parseDouble(variables[0]);
                stack.push(num);
            }
            catch(NumberFormatException e) {
                String pushElem = variables[VALUE_PARAM];
                logger.info("elem is not a number. Checking parameter in the map...");
                if(parameters.containsKey(pushElem)){
                    num = parameters.get(pushElem);
                    stack.push(num);
                    logger.info("element was found");
                }
                else{
                    logger.error("element " + pushElem + " was not found");
                }
            }
        }

        @Override
        public int getNumberOfParams() {
            return 1;
        }
    }
