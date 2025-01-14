package hexlet.code.Formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> list) {
        StringBuilder diff = new StringBuilder("{\n");
        list.sort((map1, map2) -> ((String) map1.get("key")).compareTo((String) map2.get("key")));
        for (Map<String, Object> map : list) {
            String property = (String) map.get("key");
            Object value1 = map.get("value");
            String type = map.get("type").toString();

            switch (type) {
                case "added":
                    diff.append("  + ").append(property).append(": ").append(value1).append("\n");
                    break;

                case "deleted":
                    diff.append("  - ").append(property).append(": ").append(value1).append("\n");
                    break;

                case "changed":
                    diff.append("  - ").append(property).append(": ").append(value1).append("\n");
                    diff.append("  + ").append(property).append(": ").append(map.get("value2")).append("\n");
                    break;

                case "unchanged":
                    diff.append("    ").append(property).append(": ").append(value1).append("\n");
                    break;

                default:
                    break;
            }
        }
        diff.append("}");
        return diff.toString();
    }
}

