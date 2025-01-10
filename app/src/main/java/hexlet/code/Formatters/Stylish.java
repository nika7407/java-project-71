package hexlet.code.Formatters;

import java.util.*;

public class Stylish {
    public static String stylish(Map<String, Object> input1, Map<String, Object> input2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());

        StringBuilder diff = new StringBuilder("{\n");
        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);

            if (!input1.containsKey(key)) {
                diff.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (!input2.containsKey(key)) {
                diff.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (Objects.deepEquals(value1, value2)) {
                diff.append("    ").append(key).append(": ").append(value1).append("\n");
            } else {
                diff.append("  - ").append(key).append(": ").append(value1).append("\n");
                diff.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }
        diff.append("}");
        return diff.toString();
    }

}
