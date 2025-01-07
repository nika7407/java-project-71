package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.List;

public class Differ {

    public static String generateDiff(Map<String, Object> input1, Map<String, Object> input2, String format) {
        String answer = "";
        if (format.equals("stylish")) {
            answer = stylish(input1, input2);
        } else if (format.equals("plain")) {
            answer = plain(input1, input2);
        }
        return answer;
    }

    public static String stylish(Map<String, Object> input1, Map<String, Object> input2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());

        StringBuilder diff = new StringBuilder("{\n");
        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);

            if (value1 == null) {
                diff.append(" + ").append(key).append(": ").append(value2).append("\n");
            } else if (value2 == null) {
                diff.append(" - ").append(key).append(": ").append(value1).append("\n");
            } else if (value1.equals(value2)) {
                diff.append("   ").append(key).append(": ").append(value1).append("\n");
            } else {
                diff.append(" - ").append(key).append(": ").append(value1).append("\n");
                diff.append(" + ").append(key).append(": ").append(value2).append("\n");
            }
        }
        diff.append("}");
        return diff.toString();
    }

    public static String plain(Map<String, Object> input1, Map<String, Object> input2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());

        StringBuilder diff = new StringBuilder("\n");
        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);

            if (!input1.containsKey(key)) {
                diff.append("Property '" + key + "' was added with value: ").append(typeCheck(value2)).append("\n");
            } else if (!input2.containsKey(key)) {
                diff.append("Property '" + key + "' was removed").append("\n");
            } else if (!Objects.deepEquals(value1, value2)) {
                diff.append("Property '" + key + "' was updated. ")
                        .append("From " + typeCheck(value1) + " to " + typeCheck(value2)).append("\n");
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

