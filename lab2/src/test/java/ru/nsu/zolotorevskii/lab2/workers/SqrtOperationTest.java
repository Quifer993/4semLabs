package ru.nsu.zolotorevskii.lab2.workers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class SqrtOperationTest {
    @Test
    public void work() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        stack.push(4.0);

        String[] variables = {};
        IOperation block = new SqrtOperation();
        block.work(stack, variables, parameters, writer);

        assertEquals((Double)2.0, stack.peek());
    }
}
