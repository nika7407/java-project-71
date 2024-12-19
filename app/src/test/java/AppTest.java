import hexlet.code.App;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
      public void testHelp(){
          String args = "-h";
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0,exitCode);
    }

    @Test
    public void testVersionOption() {
        String[] args = {"-V"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
        // Verify that the version string is printed
    }

    @Test
    public void testGetData(){
         String path = "C:\\Nik\\GIT\\hexletSecondProject\\app\\src\\test\\resources\\json1Test.json";
        Map<String, Object> fixedMap = new HashMap<>();
        Map<String, Object> createdMaps = null;
       try {
            createdMaps = App.getData(path);
       } catch (IOException e){
           System.out.println("error");
       }
        fixedMap.put("host", "hexlet.io");
        fixedMap.put("timeout", 50);
        fixedMap.put("proxy", "123.234.53.22");
        fixedMap.put("follow", false);
         Boolean isitsame = createdMaps.equals(fixedMap);
        assertEquals(true,isitsame);
    }




}
