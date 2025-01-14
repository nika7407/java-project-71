package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import java.util.zip.DataFormatException;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        try {
            String fileType1 = fileFormat(path1);
            String fileType2 = fileFormat(path2);
            // checks if the formats are correct and understandable
            if (!fileType1.equals(fileType2) || (!fileType1.equals("json")
                    && !fileType1.equals("yaml") && !fileType1.equals("yml"))) {
                throw new DataFormatException("There's a problem with file types");
            }

            Map<String, Object> data1 = null;
            Map<String, Object> data2 = null;

            if (fileType1.equals("json")) {
                data1 = getDataJson(path1);
                data2 = getDataJson(path2);
            } else if (fileType1.equals("yaml") || fileType1.equals("yml")) {
                data1 = getDataYaml(path1);
                data2 = getDataYaml(path2);
            }
            // reads the data from files and writes them into data 1 and 2
           return Parser.formattingStyle(Parser.parser(data1,data2),format);



        } catch (DataFormatException | IOException ex) {
            throw new RuntimeException("Error processing files: " + ex.getMessage(), ex);
        }
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }

    public static String fileFormat(String input){
        return input.substring(input.lastIndexOf(".") + 1);
    }

    public static String pathFix(String input) {
        Path path = Paths.get(input);
        return path.toAbsolutePath().toString();
        // Converts relative path into absolute;
        // does nothing if already absolute
    }

    public static Map<String, Object> getDataJson(String filePath) throws IOException {
        String fixedPath = pathFix(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(fixedPath), Map.class);
    }

    public static Map<String, Object> getDataYaml(String filePath) throws IOException {
        String fixedPath = pathFix(filePath);
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(new File(fixedPath), Map.class);
    }
}
