package ru.nsu.zolotorevskii.lab2.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Writer;
import java.util.Map;
import java.util.Stack;

/**
 * Class defined (parameter -> number) and add it in the map of parameters
 * @see IOperation
 */
public class DefineOperation implements IOperation {
    private static final Logger logger = LogManager.getLogger(ru.nsu.zolotorevskii.lab2.workers.DefineOperation.class);
    private static final int NAME_PARAM = 0;
    private static final int VALUE_PARAM = 1;

    public DefineOperation(){}

    /**
     * Defined parameter and add it in the map of parameters
     * @param stack Стэк чисел с которым работает программа
     * @param variables Параметры для работы операции
     * @param parameters Параметры, задефайненые с помощью класса DefineOperation
     * @param writer Для записи в файл результата операции
     */
    @Override
    public void work(Stack<Double> stack, String[] variables, Map<String, Double> parameters, Writer writer) {
        try{
            Double.parseDouble(variables[NAME_PARAM]);
            logger.error("Define parameter is a number! Use smth else");
        }
        catch(NumberFormatException e){
            try{
                parameters.put(variables[NAME_PARAM], Double.parseDouble(variables[1]));
            }
            catch(NumberFormatException e1){
                logger.error( variables[VALUE_PARAM] + " is not a number!");
            }
        }
    }

    @Override
    public int getNumberOfParams() {
        return 2;
    }
}

