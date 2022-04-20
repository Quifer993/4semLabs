package ru.nsu.zolotorevskii.lab2.workers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class PushOperationTest {
    @Test
    public void work() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        String[] variablesPop = {"5"};
        IOperation block = new PushOperation();
        block.work(stack, variablesPop, parameters, writer);

        assertEquals((Double)5.0, stack.peek());
    }
}