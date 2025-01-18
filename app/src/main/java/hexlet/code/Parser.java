package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import static hexlet.code.Differ.fileFormat;

public class Parser {

public static Map<String, Object> parse(String path) throws IOException {
    String fileType = fileFormat(path);
    if (fileType.equals("json")) {
        return getDataJson(path);
    } else if (fileType.equals("yaml") || fileType.equals("yml")) {
        return  getDataYaml(path);
    }
    return null;
}

    public static Map<String, Object> getDataJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), Map.class);
    }

    public static Map<String, Object> getDataYaml(String filePath) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(new File(filePath), Map.class);
    }


}


