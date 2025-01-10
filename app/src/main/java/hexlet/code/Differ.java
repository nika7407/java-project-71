package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.io.IOException;
import java.util.Map;

import java.util.zip.DataFormatException;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        try {
            String fileType1 = path1.substring(path1.lastIndexOf(".") + 1);
            String fileType2 = path2.substring(path2.lastIndexOf(".") + 1);
            // checks if the formats are correct and understandable
            if (!fileType1.equals(fileType2) || (!fileType1.equals("json")
                    && !fileType1.equals("yaml") && !fileType1.equals("yml"))) {
                throw new DataFormatException("There's a problem with file types");
            }

            Map<String, Object> data1 = null;
            Map<String, Object> data2 = null;

            if (fileType1.equals("json")) {
                data1 = Parser.getDataJson(path1);
                data2 = Parser.getDataJson(path2);
            } else if (fileType1.equals("yaml") || fileType1.equals("yml")) {
                data1 = Parser.getDataYaml(path1);
                data2 = Parser.getDataYaml(path2);
            }
            // reads the data from files and writes them into data 1 and 2
            String answer = "";
            switch (format) {
                case "stylish":
                    answer = Stylish.stylish(data1, data2);
                    break;
                case "plain":
                    answer = Plain.plain(data1, data2);
                    break;
                case "json":
                    answer = Json.json(data1, data2);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown format: " + format);
            }
            return answer;
        } catch (DataFormatException | IOException ex) {
            throw new RuntimeException("Error processing files: " + ex.getMessage(), ex);
        }
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }
}
