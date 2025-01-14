package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class Json {
    public static String json(List<Map<String, Object>> list) {
        try {
            list.sort((map1, map2) -> ((String) map1.get("key")).compareTo((String) map2.get("key")));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting to JSON: " + e.getMessage(), e);
        }
    }
}

