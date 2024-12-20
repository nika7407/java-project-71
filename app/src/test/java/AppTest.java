import hexlet.code.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private String path;
    private String path1;
    private Map<String, Object> fixedMap;
    private Map<String, Object> createdMaps;
    private Map<String, Object> fixedMap1;

   @BeforeEach
   public void before(){
       // Initialize the variables in the @BeforeEach method
       path = "C:\\Nik\\GIT\\java-project-71\\app\\src\\test\\resources\\json1Test.json";
       path1 = "C:\\Nik\\GIT\\java-project-71\\app\\src\\test\\resources\\json2Test.json";
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
      public void testHelp(){
          String[] args = {"-h", "file1.json", "file2.json"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0,exitCode);
    }

    @Test
    public void testVersionOption() {
        String[] args = {"-V"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    public void testGetData(){
        try {
            createdMaps = App.getData(path);
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }

        assertNotNull(createdMaps, "The created map should not be null.");
        assertEquals(fixedMap, createdMaps, "The maps should be equal.");
    }

   @Test
    public void testGenerateDiff(){
       String actual = App.generateDiff(fixedMap,fixedMap1);
       String excpected = "{\n" +
               " - follow: false\n" +
               "   host: hexlet.io\n" +
               " - proxy: 123.234.53.22\n" +
               " - timeout: 50\n" +
               " + timeout: 20\n" +
               " + verbose: true\n" +
               "}";
       assertEquals(excpected,actual);
   }


}
