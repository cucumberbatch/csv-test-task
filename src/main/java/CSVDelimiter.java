import io.CSVFileParser;
import io.CSVFileSaver;
import model.CSVTable;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CSVDelimiter {

    public static void main(String[] args) throws InterruptedException {

        CSVTable table            = new CSVTable();
        List<Thread> tasksToRead  = new LinkedList<>();
        List<Thread> tasksToWrite = new LinkedList<>();

        for (String inputFileName : args) {
            tasksToRead.add(new Thread(new CSVFileParser(new File(inputFileName), table)));
        }

        for (Thread thread : tasksToRead) {
            thread.start();
        }

        for (Thread thread : tasksToRead) {
            thread.join();
        }

        for (String outputFileName : table.getKeySet()) {
            tasksToWrite.add(new Thread(new CSVFileSaver(new File(outputFileName), table)));
        }

        for (Thread thread : tasksToWrite) {
            thread.start();
        }
    }
}
