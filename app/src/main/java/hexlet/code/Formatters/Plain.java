package hexlet.code.Formatters;

import java.util.*;

public class Plain {
    public static String plain(Map<String, Object> input1, Map<String, Object> input2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());

        StringBuilder diff = new StringBuilder("");
        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);

            if (!input1.containsKey(key)) {
                diff.append("\nProperty '" + key + "' was added with value: ").append(typeCheck(value2));
            } else if (!input2.containsKey(key)) {
                diff.append("\nProperty '" + key + "' was removed");
            } else if (!Objects.deepEquals(value1, value2)) {
                diff.append("\nProperty '" + key + "' was updated. ")
                        .append("\nFrom " + typeCheck(value1) + " to " + typeCheck(value2));
            }
        }
        return diff.toString();
    }

    public static String typeCheck(Object input) {
        if (input instanceof String) {
            return "'" + input + "'";
        } else if (input instanceof Map || input instanceof List) {
            return "[complex value]";
        } else if (input == null) {
            return "null";
        } else {
            return input.toString();
        }
    }

}
