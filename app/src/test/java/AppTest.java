import hexlet.code.App;
import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {

    private String pathjson;
    private String path1json;
    private String pathyaml;
    private String pathyaml1;
    private Map<String, Object> fixedMap;
    private Map<String, Object> createdMaps;
    private Map<String, Object> fixedMap1;

    @BeforeEach
    public void before() {
        // Initialize the variables in the @BeforeEach method
        pathjson = "src/test/resources/json1Test.json";
        path1json = "src/test/resources/json2Test.json";

        pathyaml = "src/test/resources/json1Test.yaml";
        pathyaml1 = "src/test/resources/json2Test.yaml";

        fixedMap = new HashMap<>();
        createdMaps = null;
        fixedMap1 = new HashMap<>();

        fixedMap.put("host", "hexlet.io");
        fixedMap.put("timeout", 50);
        fixedMap.put("proxy", "123.234.53.22");
        fixedMap.put("follow", false);

        fixedMap1.put("timeout", 20);
        fixedMap1.put("verbose", true);
        fixedMap1.put("host", "hexlet.io");
    }

    @Test
    public void testHelp() {
        String[] args = {"-h", "file1.json", "file2.json"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    public void testVersionOption() {
        String[] args = {"-V"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    public void testGetDataJson() {
        try {
            createdMaps = Parser.getDataJson(pathjson);
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }

        assertNotNull(createdMaps, "The created map should not be null.");
        assertEquals(fixedMap, createdMaps, "The maps should be equal.");
    }

    @Test
    public void testGenerateDiff() {
        String actual = Differ.generateDiff(fixedMap, fixedMap1);
        String expected = "{\n"
               + " - follow: false\n"
               + "   host: hexlet.io\n"
               + " - proxy: 123.234.53.22\n"
               + " - timeout: 50\n"
               + " + timeout: 20\n"
               + " + verbose: true\n"
               + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() {
        try {
            createdMaps = Parser.getDataYaml(pathyaml);
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }

        assertNotNull(createdMaps, "The created map should not be null.");
        assertEquals(fixedMap, createdMaps, "The maps should be equal.");
    }

    @Test
    public void testPathFix() {
        String relativePath = "src/main/resources/file.txt";
        String absolutePath = Parser.pathFix(relativePath);
        assertTrue(Paths.get(absolutePath).isAbsolute(), "path is not absolute.");
    }
}
