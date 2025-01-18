package hexlet.code;

import hexlet.code.formatters.Formatter;

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
            // Checks if the formats are normal
            if (!fileType1.equals(fileType2) || (!fileType1.equals("json")
                    && !fileType1.equals("yaml") && !fileType1.equals("yml"))) {
                throw new DataFormatException("There's a problem with file types");
            }

            Map<String, Object> data1 = Parser.parse(path1);
            Map<String, Object> data2 = Parser.parse(path2);

            // Reads the data from files and writes them into data1 and data2
            // converts both data's into list with maps and difference keys
            // after that through a formatting style outputs right format
            return Formatter.formattingStyle(DifferMaker.diff(data1, data2), format);
            // crates differ map list
        } catch (DataFormatException | IOException ex) {
            throw new RuntimeException("Error processing files: " + ex.getMessage(), ex);
        }
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }

    public static String fileFormat(String input) {
        return input.substring(input.lastIndexOf(".") + 1);
    }

    public static String pathFix(String input) {
        Path path = Paths.get(input);
        return path.toAbsolutePath().toString();
        // Converts relative path into absolute;
        // Does nothing if already absolute
    }
}
