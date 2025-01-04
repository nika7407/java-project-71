package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generateDiff(Map<String, Object> input1, Map<String, Object> input2, String format) {
             String answer = "";
              if (format.equals("stylish")){
                   answer = stylish(input1,input2);
              }
              return  answer;
    }

    public static String stylish(Map<String, Object> input1, Map<String, Object> input2){
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());
        StringBuilder diff = new StringBuilder("{\n");
        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);

            if (value1 == null) {
                diff.append(" + " + key + ": " + value2 + "\n");
            } else if (value2 == null) {
                diff.append(" - " + key + ": " + value1 + "\n");
            } else if (value1.equals(value2)) {
                diff.append("   " + key + ": " + value1 + "\n");
            } else {
                diff.append(" - " + key + ": " + value1 + "\n");
                diff.append(" + " + key + ": " + value2 + "\n");
            }
        }
        diff.append("}");
        return diff.toString();
    }

}
