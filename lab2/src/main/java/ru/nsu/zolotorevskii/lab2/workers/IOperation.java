package ru.nsu.zolotorevskii.lab2.workers;

import java.io.Writer;
import java.util.Map;
import java.util.Stack;

/**
 * Interface for operation which used in calculator
 * @see AddOperation
 * @see DefineOperation
 * @see DivOperation
 * @see MulOperation
 * @see PopOperation
 * @see PrintOperation
 * @see PushOperation
 * @see SqrtOperation
 * @see SubOperation
 */
public interface IOperation {
    /**
     * Метод, который переопределяется в классах-наследниках
     * @param stack Стэк чисел с которым работает программа
     * @param variables Параметры для работы операции
     * @param parameters Параметры, задефайненые с помощью класса DefineOperation
     * @param writer Для записи в файл результата операции
     */
    void work(Stack<Double> stack, String[] variables, Map<String, Double> parameters, Writer writer);

    /**
     * Возвращает колличество параметров для работы операции
     * @return Колличество параметров для работы операции
     */
    int getNumberOfParams();
}
