package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.zip.DataFormatException;
import static hexlet.code.Differ.generateDiff;

public class FullGenerator {

    public static String generate(String path1, String path2 ) throws Exception{
        try {

            String fileType1 = path1.substring(path1.lastIndexOf(".") + 1);
            String fileType2 = path2.substring(path2.lastIndexOf(".") + 1);

            if(!fileType1.equals(fileType2) || !fileType1.equals("json") &&
                    !fileType1.equals("yaml") && !fileType1.equals("yml")){
               throw new DataFormatException("There's problem with file Types");
            }

            Map<String, Object> data1 = null;
            Map<String, Object> data2 = null;

              if (fileType1.equals("json")){
                  data1 = Parser.getDataJson(path1);
                  data2 = Parser.getDataJson(path2);
              } else if (fileType1.equals("yaml") || fileType1.equals("yml")) {
                  data1 = Parser.getDataYaml(path1);
                  data2 = Parser.getDataYaml(path2);
              }

               return  generateDiff(data1, data2);

            } catch (IOException e) {
            System.out.println("There's issues with inputed paths");
            return "";
        }
    }

}
