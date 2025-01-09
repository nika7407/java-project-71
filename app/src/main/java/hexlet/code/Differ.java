package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Differ {

    public static String generate(Map<String, Object> input1, Map<String, Object> input2, String format) throws JsonProcessingException {
        String answer = "";
        switch (format) {
            case "stylish":
                answer = stylish(input1, input2);
                break;
            case "plain":
                answer = plain(input1, input2);
                break;
            case "json":
                answer = json(input1, input2);
                break;
            default:
                System.out.println("Unknown format: " + format);
                break;
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

    public static String json(Map<String, Object> input1, Map<String, Object> input2) throws JsonProcessingException {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());

        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> answer = new ArrayList<>();

        StringBuilder diff = new StringBuilder("\n");
        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);
            Map<String, Object> map = new HashMap<>();
            if (!input1.containsKey(key)) {
                // was added
                map.put("key",key);
                map.put("type","added");
                map.put("value",value2);
                answer.add(map);
            } else if (!input2.containsKey(key)) {
                // was removed
                map.put("key",key);
                map.put("type","deleted");
                map.put("value",value1);
                answer.add(map);
            } else if (!Objects.deepEquals(value1, value2)) {
                // was updated
                map.put("key",key);
                map.put("type","changed");
                map.put("value1",value1);
                map.put("value2",value2);
                answer.add(map);
            } else {
                // was unchanged
                map.put("key",key);
                map.put("type","unchanged");
                map.put("value",value1);
                answer.add(map);
            }
        }

        return  objectMapper.writeValueAsString(answer);

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

