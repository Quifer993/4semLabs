package project;

import java.util.Map;
/////////////
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/////////////

public class Read {
    Map<String, Integer> readMap;
    int capacityWords;
    int capacityDifferentWords;

    Read() {
        // smth strange
        capacityWords = 0;
        capacityDifferentWords = 0;
    };

    public int getCapacityWords() {	return capacityWords; }
    public int getCapacityDifferentWords() { return capacityDifferentWords; }
    public Map<String, Integer> getMap() { return readMap; }


    CodeError readingFile(String nameFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String inputString;
            while ((inputString = reader.readLine()) != null) {

                String text = "Егор Алла Александр";
                Pattern pattern = Pattern.compile("[\\da-zA-Zа-яА-Я]+");
                Matcher matcher = pattern.matcher(inputString);
                while (matcher.find()) {
                    capacityWords++;
                    String newWord = inputString.substring(matcher.start(), matcher.end());

                    if (readMap.containsKey(newWord)) {
                        var value = readMap.get(newWord) + 1;
                        readMap.put(newWord, value);
                    }
                    else {
                        readMap.put(newWord, 1);
                        capacityDifferentWords++;
                    }
                    System.out.println(inputString.substring(matcher.start(), matcher.end()));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return CodeError.FILE_NOT_OPEN;
        }

        return CodeError.OK;
    }

};







