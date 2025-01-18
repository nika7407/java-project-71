package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
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
