package ru.nsu.zolotorevskii.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.zolotorevskii.lab2.workers.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.nsu.zolotorevskii.lab2.Constants.*;


/**
 * Class for create a new workers for calculator
 */
public class Factory {
    private static final Logger logger = LogManager.getLogger(Factory.class);
    private Map<String, IOperation> workerMap;

    /**
     * Create workers for calculator
     */
    Factory(){
        workerMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(WORKER_NAMES_FILE));
            String line = reader.readLine();
            while (line != null) {
                String[] arguments = line.trim().split(SPACE);
                Class worker = Class.forName(arguments[CLASS_PATH_POS]);
                if (!workerMap.containsKey(arguments[OPERATION_POS])) {
                    IOperation work = (IOperation) worker.newInstance();
                    workerMap.put(arguments[OPERATION_POS], work);
                }
                else {
                    logger.warn("Operation" + arguments[OPERATION_POS] + "is already in the map. Rewrite workersNames-file!");
                }

                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            logger.error("File " + WORKER_NAMES_FILE + " not found. Check path: ", ex);
        } catch (IOException ex2) {
            logger.error("There is a readLine() error: ", ex2);
        } catch (ClassNotFoundException ex3) {
            logger.error("No such class found: ", ex3);
        } catch (IllegalAccessException | InstantiationException ex4) {
            logger.error("Access Error", ex4);
        }
    }


    /**
     * Return worker(IOperation) from the map of operations
     * @param operation name of operation, that will take from the mapWorker
     * @return Return worker whose name called
     */
    public IOperation getWorker(String operation) {
        if (workerMap.containsKey(operation)) {
            return workerMap.get(operation);
        }
        else {
            logger.error("No such command found: " + operation);
            return null;
        }
    }
}
