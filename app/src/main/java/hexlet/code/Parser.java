package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static String PathFix(String input){
        Path path = Paths.get(input);
         return  path.toAbsolutePath().toString();
        // Converts relative path into absolute;
        // does nothing if already absolute
    }

    public static Map<String, Object> getDataJson(String filePath) throws IOException {

        String fixedPath = PathFix(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(fixedPath), Map.class);

    }

    public static Map<String, Object> getDataYaml(String filePath) throws IOException {

        String fixedPath = PathFix(filePath);
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(new File(fixedPath), Map.class);

    }

}
