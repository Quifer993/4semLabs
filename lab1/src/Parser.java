import java.util.*;
import java.util.stream.Collectors;

public class Parser {
    Map<String, Integer> wordsMap;

    Parser(Map<String, Integer> mapDictionary) {
        wordsMap = mapDictionary;
    }

    public Map<String, Integer> getMap() { return this.wordsMap; }


    public void parser() {
        wordsMap = wordsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
};