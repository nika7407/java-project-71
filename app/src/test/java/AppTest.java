import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static hexlet.code.Formatters.Json.json;
import static hexlet.code.Formatters.Plain.plain;
import static hexlet.code.Formatters.Stylish.stylish;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class AppTest {
    private static final int TIMEOUT_OLD = 50;
    private static final int TIMEOUT_NEW = 20;
    private String pathjson;
    private String path1json;
    private String pathyaml;
    private String pathyaml1;
    private String generetarorResult;
    private Map<String, Object> fixedMap;
    private Map<String, Object> createdMaps;
    private Map<String, Object> fixedMap1;
    private List<Map<String, Object>> diffListWithKeys;

    @BeforeEach
    public void before() {

        pathjson = "src/test/resources/json1Test.json";
        path1json = "src/test/resources/json2Test.json";
        pathyaml = "src/test/resources/json1Test.yaml";
        pathyaml1 = "src/test/resources/json2Test.yaml";

        fixedMap = new HashMap<>();
        createdMaps = null;
        fixedMap1 = new HashMap<>();

        fixedMap = new HashMap<>(Map.of(
                "host", "hexlet.io",
                "timeout", TIMEOUT_OLD,
                "proxy", "123.234.53.22",
                "follow", false
        ));

        fixedMap1 = new HashMap<>(Map.of(
                "timeout", TIMEOUT_NEW,
                "verbose", true,
                "host", "hexlet.io"
        ));

        diffListWithKeys = List.of(
                new HashMap<>(Map.of("key", "timeout", "type", "changed", "value", TIMEOUT_OLD, "value2", TIMEOUT_NEW)),
                new HashMap<>(Map.of("key", "proxy", "type", "deleted", "value", "123.234.53.22")),
                new HashMap<>(Map.of("key", "follow", "type", "deleted", "value", false)),
                new HashMap<>(Map.of("key", "verbose", "type", "added", "value", true)),
                new HashMap<>(Map.of("key", "host", "type", "unchanged", "value", "hexlet.io"))
        );


        generetarorResult = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
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
            createdMaps = Differ.getDataJson(pathjson);
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }

        assertNotNull(createdMaps, "The created map should not be null.");
        assertEquals(fixedMap, createdMaps, "The maps should be equal.");
    }

    @Test
    public void testGenerateDiff() {
        String actual = stylish(diffListWithKeys);
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() {
        try {
            createdMaps = Differ.getDataYaml(pathyaml);
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }

        assertNotNull(createdMaps, "The created map should not be null.");
        assertEquals(fixedMap, createdMaps, "The maps should be equal.");
    }

    @Test
    public void testPathFix() {
        String relativePath = "src/main/resources/file.txt";
        String absolutePath = Differ.pathFix(relativePath);
        assertTrue(Paths.get(absolutePath).isAbsolute(), "Path is not absolute.");
    }

    @Test
    public void testGeneratorStylish() {
        try {
            String test = Differ.generate(pathjson, path1json, "stylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorYaml() {
        try {
            String test = Differ.generate(pathyaml, pathyaml1, "stylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPlain() {
        String expectedResult = "Property 'follow' was removed\n"
                + "Property 'proxy' was removed\n"
                + "Property 'timeout' was updated. From 50 to 20\n"
                + "Property 'verbose' was added with value: true";

        String actualOutput = plain(diffListWithKeys);
        assertEquals(expectedResult, actualOutput);
    }

    @Test
    public void testJsonFormater() {
        String expectedResult = "[{\"type\":\"deleted\",\"value\":false,\"key\":\"follow\"}"
                + ",{\"type\":\"unchanged\",\"value\":\"hexlet.io\",\"key\":\"host\"},"
                + "{\"type\":\"deleted\",\"value\":\"123.234.53.22\",\"key\":\"proxy\"},"
                + "{\"value2\":20,\"value1\":50,\"type\":\"changed\",\"key\":\"timeout\"},"
                + "{\"type\":\"added\",\"value\":true,\"key\":\"verbose\"}]";
        String actualOutput = json(diffListWithKeys);
        assertEquals(expectedResult, actualOutput);
    }
}





