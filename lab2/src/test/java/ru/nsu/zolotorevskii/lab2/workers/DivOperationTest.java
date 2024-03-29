package ru.nsu.zolotorevskii.lab2.workers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class DivOperationTest {
    @Test
    public void work() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        stack.push(5.0);
        stack.push(10.0);

        String[] variablesDiv = {};
        IOperation divBlock = new DivOperation();
        divBlock.work(stack, variablesDiv, parameters, writer);

        assertEquals((Double)2.0, stack.peek());
    }

    @Test
    public void ErrorWork1() {
        boolean isError = true;
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        stack.push(0.0);
        stack.push(0.0);

        String[] variablesDiv = {};
        IOperation divBlock = new DivOperation();
        try{
            divBlock.work(stack, variablesDiv, parameters, writer);
        }catch (ArithmeticException e){
            isError = false;
        }

        if(isError){
            fail();
        }

    }

    @Test
    public void ErrorWork2() {
        Stack<Double> stack = new Stack<>();
        StringWriter writer = new StringWriter(50);
        Map<String, Double> parameters = new HashMap<>();

        stack.push(5.0);

        String[] variablesDiv = {};
        IOperation divBlock = new DivOperation();
        divBlock.work(stack, variablesDiv, parameters, writer);

        assertEquals((Double)5.0, stack.peek());
    }
}
