package hexlet.code.Formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(List<Map<String, Object>> list) {
        StringBuilder diff = new StringBuilder();
        list.sort((map1, map2) -> ((String) map1.get("key")).compareTo((String) map2.get("key")));

        for (Map<String, Object> map : list) {
            String property = (String) map.get("key");
            Object value1 = map.get("value");
            String type = map.get("type").toString();

            switch (type) {
                case "added":
                    diff.append("Property '")
                            .append(property)
                            .append("' was added with value: ")
                            .append(typeCheck(value1))
                            .append("\n");
                    break;

                case "deleted":
                    diff.append("Property '")
                            .append(property)
                            .append("' was removed\n");
                    break;

                case "changed":
                    Object value2 = map.get("value2");
                    diff.append("Property '")
                            .append(property)
                            .append("' was updated. From ")
                            .append(typeCheck(value1))
                            .append(" to ")
                            .append(typeCheck(value2))
                            .append("\n");
                    break;

                case "unchanged":
                    break;

                default:
                    break;
            }
        }

        // Remove the trailing newline character if it exists
        String result = diff.toString();
        if (result.endsWith("\n")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    private static String typeCheck(Object input) {
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

