package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference."
)
public final class App implements Runnable {

    @Option(
            names = {"-h", "--help"},
            description = "Show this help message and exit."
    )
    private boolean help;

    @Option(
            names = {"-V", "--version"},
            description = "Print version information and exit.",
            usageHelp = true
    )
    private boolean version;

    @Option(
            names = {"-f", "--format"},
            description = "Output format",
            paramLabel = "format",
            defaultValue = "stylish"
    )
    private String format;

    @Parameters(
            paramLabel = "filepath1",
            description = "Path to the first file"
    )
    private String path1;

    @Parameters(
            paramLabel = "filepath2",
            description = "Path to the second file"
    )
    private String path2;

    @Override
    public void run() {
        if (help) {
            CommandLine.usage(this, System.out);
        } else if (version) {
            System.out.println("version 0.1");
        } else {
            try {
                System.out.println(Differ.generate(path1, path2, format));
            } catch (Exception e) {
                System.out.println("There was a problem regarding file types or paths.");
            }
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}

