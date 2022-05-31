package ru.nsu.zolotorevskii.lab2.workers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class SubOperationTest {
    @Test
    public void work() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        stack.push(5.0);
        stack.push(10.0);

        String[] variablesDiv = {};
        IOperation divBlock = new SubOperation();
        divBlock.work(stack, variablesDiv, parameters, writer);

        assertEquals((Double)5.0, stack.peek());
    }

    @Test
    public void errorWork() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        stack.push(5.0);

        String[] variablesDiv = {};
        IOperation divBlock = new SubOperation();
        divBlock.work(stack, variablesDiv, parameters, writer);

        assertEquals((Double)5.0, stack.peek());
    }
}
