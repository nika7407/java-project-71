package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import java.util.TreeSet;
import java.util.Set;
import java.lang.StringBuilder;


@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public class App implements Runnable{

    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    boolean help;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", usageHelp = true)
    boolean version;

    @Option(names = {"-f", "--format"}, description = "output format (default: stylish)", defaultValue = "stylish", paramLabel = "format")
    String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String path1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String path2;


    @Override
    public void run(){
        if (help){
         CommandLine.usage(this, System.out);
        } else if (version){
            System.out.println("version 0.1");
        } else {
           try {
               Map<String, Object> data1 = getData(path1);
               Map<String, Object> data2 = getData(path2);
               
               System.out.println("Parsed data: " + data1);
               System.out.println("Parsed data1: " + data2);
               System.out.println(generateDiff(data1,data2));

           } catch (IOException e) {
               System.out.println("meow");
           }
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public static Map<String, Object> getData(String content) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(content), Map.class);

    }

    public static String generateDiff(Map<String, Object> input1, Map<String, Object> input2){

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(input1.keySet());
        allKeys.addAll(input2.keySet());
       StringBuilder diff = new StringBuilder("{\n");

        for (String key : allKeys){
            var value1 = input1.get(key);
            var value2 = input2.get(key);

            if ( value1 == null){
                diff.append(" + "+key+": "+value2+"\n");
            } else if (value2 == null){
                diff.append(" - "+key+": "+value1+"\n");
            } else if (value1.equals(value2)){
                diff.append("  "+key+": "+value1+"\n");
            } else {
               diff.append(" - "+key+": "+value1+"\n");
               diff.append(" + "+key+": "+value2+"\n");
            }
        }
        diff.append("}");
        return diff.toString();

    }

}