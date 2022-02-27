import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Write {
    Map<String, Integer> map;
    int capacityWords;
    int capacityDifferentWords;

    Write( Map<String, Integer> mapInput, int capacityW, int capacityDW) {
        map = mapInput;
        capacityWords = capacityW;
        capacityDifferentWords = capacityDW;
    }

    CodeError writeCSV(String nameOutputFile) {
        try(FileWriter writer = new FileWriter(nameOutputFile, false))
        {
            for (var item : map.entrySet()){
                double procent = ((double)item.getValue() / (double)capacityWords) * 100;
                writer.write(item.getKey() + ";" + item.getValue() + ";" + procent + "%");
                writer.flush();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return CodeError.FILE_NOT_OPEN;
        }

        return CodeError.OK;
    }

}


