import hexlet.code.Differ;
import hexlet.code.util.TestUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class AppTest {
    private String generetarorResult;

    @Test
    public void testGeneratorStylishJson() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.yaml"),
                    TestUtils.returnPath("json2Test.yaml"), "stylish");
            generetarorResult = TestUtils.readFixture("expectedResultStylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorStylishYml() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.json"),
                    TestUtils.returnPath("json2Test.json"), "stylish");
            generetarorResult = TestUtils.readFixture("expectedResultStylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorPlainYaml() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.yaml"),
                    TestUtils.returnPath("json2Test.yaml"), "plain");
            generetarorResult = TestUtils.readFixture("expectedResultPlain");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorPlainJson() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.json"),
                    TestUtils.returnPath("json2Test.json"), "plain");
            generetarorResult = TestUtils.readFixture("expectedResultPlain");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorJsonJson() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.json"),
                    TestUtils.returnPath("json2Test.json"), "json");
            generetarorResult = TestUtils.readFixture("expectedResultJson.json");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorJsonYaml() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.yaml"),
                    TestUtils.returnPath("json2Test.yaml"), "json");
            generetarorResult = TestUtils.readFixture("expectedResultJson.json");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorYaml() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.yaml"),
                    TestUtils.returnPath("json2Test.yaml"));
            generetarorResult = TestUtils.readFixture("expectedResultStylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratorJson() {
        try {
            String test = Differ.generate(TestUtils.returnPath("json1Test.json"),
                    TestUtils.returnPath("json2Test.json"));
            generetarorResult = TestUtils.readFixture("expectedResultStylish");
            assertEquals(generetarorResult, test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}





