package ru.nsu.zolotorevskii.lab2.workers;

import java.io.Writer;
import java.util.Map;
import java.util.Stack;

public interface IWorker {
    void work(Stack<Double> stack, String[] variables, Map<String, Double> parameters, Writer writer);
    int getNumberOfParams();

}
