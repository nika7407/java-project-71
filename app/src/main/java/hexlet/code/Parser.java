package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Parser {

    public static List<Map<String, Object>> parser(Map<String, Object> input1, Map<String, Object> input2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());

        List<Map<String, Object>> answer = new ArrayList<>();

        for (String key : allKeys) {
            var value1 = input1.get(key);
            var value2 = input2.get(key);
            Map<String, Object> map = new HashMap<>();

            if (!input1.containsKey(key)) {
                // was added
                map.put("key", key);
                map.put("type", "added");
                map.put("value", value2);
                answer.add(map);
            } else if (!input2.containsKey(key)) {
                // was removed
                map.put("key", key);
                map.put("type", "deleted");
                map.put("value", value1);
                answer.add(map);
            } else if (!Objects.deepEquals(value1, value2)) {
                // was updated
                map.put("key", key);
                map.put("type", "changed");
                map.put("value", value1);
                map.put("value2", value2);
                answer.add(map);
            } else {
                // was unchanged
                map.put("key", key);
                map.put("type", "unchanged");
                map.put("value", value1);
                answer.add(map);
            }
        }
        return answer;
    }

    public static String formattingStyle(List<Map<String, Object>> list, String format) {
        switch (format) {
            case "stylish":
                return Stylish.stylish(list);
            case "plain":
                return Plain.plain(list);
            case "json":
                return Json.json(list);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
}


