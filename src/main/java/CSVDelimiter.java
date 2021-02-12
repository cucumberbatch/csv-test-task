import io.CSVMediator;
import model.Configuration;

public class CSVDelimiter {

    public static void main(String[] args) {
        String[] inputFilePaths       = args;
        String   outputFilesPath      = "";
        String   outputFileMask       = "";
        String   separationCharacter  = ";";

        Configuration configuration = new Configuration.Builder(inputFilePaths)
                .setOutputFilesPath(outputFilesPath)
                .setOutputFilesMask(outputFileMask)
                .setSeparationCharacter(separationCharacter)
                .build();

        CSVMediator mediator = new CSVMediator(configuration);

        mediator.executeTasks();

        System.out.println(mediator.getTable());
    }
}
