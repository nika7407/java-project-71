import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.App;
import hexlet.code.Differ;
import hexlet.code.Parser;
import hexlet.code.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static hexlet.code.formatters.Json.json;
import static hexlet.code.formatters.Plain.plain;
import static hexlet.code.formatters.Stylish.stylish;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class AppTest {
    private static final int TIMEOUT_OLD = 50;
    private static final int TIMEOUT_NEW = 20;
    private String generetarorResult;
    private Map<String, Object> fixedMap;
    private Map<String, Object> createdMaps;
    private Map<String, Object> fixedMap1;
    private List<Map<String, Object>> diffListWithKeys;

    @BeforeEach
    public void before() throws IOException {


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
                new HashMap<>(Map.of("type", "deleted", "value", false, "key", "follow")),
                new HashMap<>(Map.of("type", "unchanged", "value", "hexlet.io", "key", "host")),
                new HashMap<>(Map.of("type", "deleted", "value", "123.234.53.22", "key", "proxy")),
                new HashMap<>(Map.of("type", "changed", "value", TIMEOUT_OLD, "value2", TIMEOUT_NEW, "key", "timeout")),
                new HashMap<>(Map.of("type", "added", "value", true, "key", "verbose"))
        );

        generetarorResult = TestUtils.readFixture("expectedResultStylish");

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
            createdMaps = Parser.getDataJson(TestUtils.returnPath("json1Test.json"));
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }

        assertNotNull(createdMaps, "The created map should not be null.");
        assertEquals(fixedMap, createdMaps, "The maps should be equal.");
    }

    @Test
    public void testGenerateDiff() {
        String actual = stylish(diffListWithKeys);
        String expected = generetarorResult;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDataYaml() {
        try {
            createdMaps = Parser.parse(TestUtils.returnPath("json1Test.yaml"));
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
            String test = Differ.generate(TestUtils.returnPath("json1Test.json"),
                    TestUtils.returnPath("json2Test.json"), "stylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorYaml() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.yaml"),
                    TestUtils.returnPath("json2Test.yaml"), "stylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPlain() throws IOException {
        String expectedResult = TestUtils.readFixture("expectedResultPlain");
        String actualOutput = plain(diffListWithKeys);
        assertEquals(expectedResult, actualOutput);
    }


    @Test
    public void testJsonFormatter() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResultJson = TestUtils.readFixture("expectedResultJson.json");
        List<Map<String, Object>> expectedResult
                = objectMapper.readValue(expectedResultJson, new TypeReference<>( ) { } );
        String actualOutputJson = json(diffListWithKeys);
        List<Map<String, Object>> actualOutput = objectMapper.readValue(actualOutputJson, new TypeReference<>() { });

        assertEquals(expectedResult, actualOutput);
    }

}





