package hexlet.code.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class TestUtils {
    public static String readFixture(String filename) throws IOException {
        Path filePath = Path.of("src/test/resources", filename);
        // Read file content as a String
        String content = Files.readString(filePath, StandardCharsets.UTF_8);
        // Normalize line endings (convert CRLF to LF)
        return content.replace("\r\n", "\n");
    }
}
