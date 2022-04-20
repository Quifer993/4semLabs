package ru.nsu.zolotorevskii.lab2.workers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class DefineOperationTest {
    @Test
    public void work() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        String[] variablesDefine = {"TestParam", "5"};
        IOperation defineBlock = new DefineOperation();
        defineBlock.work(stack, variablesDefine, parameters, writer);

        String[] variablesPush = {"TestParam"};
        IOperation pushBlock = new PushOperation();
        pushBlock.work(stack, variablesPush, parameters, writer);

        String[] variablesPrint = {};
        IOperation printBlock = new PrintOperation();
        printBlock.work(stack, variablesPrint, parameters, writer);

        assertEquals((Double)5.0, stack.peek());
    }
}
