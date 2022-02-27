import java.security.KeyPair;
import java.util.List;
import java.util.Map;

public class Parser {
    Map<String, Integer> wordsMap;

    Parser(Map<String, Integer> mapDictionary) { wordsMap = mapDictionary; }

    public Map<String, Integer> getMap() { return this.wordsMap; }


    public void parser() {
//        for (auto& item : inputFileMap){
//            listPair.push_back(item);
//        }
//
//        listPair.sort([](const std::pair<std::wstring, int>& element1, const std::pair<std::wstring, int>& element2)
//        {
//            if (element1.second == element2.second)
//                return element1 > element2;
//            return element1.second > element2.second;
//        }
        wordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed());

    }
};