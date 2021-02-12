package io;

import model.Configuration;
import model.ContentTable;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CSVMediator {
    public static final String SEMICOLON_SEPARATOR_CHARACTER = ";";

    private final List<Thread> tasksToRead  = new LinkedList<>();
    private final List<Thread> tasksToWrite = new LinkedList<>();
    private final ContentTable table        = new ContentTable();

    private final Configuration configuration;


    public CSVMediator(Configuration configuration) {
        this.configuration = configuration;
    }

    public CSVMediator(String[] inputFilePaths) {
        this.configuration = new Configuration(inputFilePaths);
        this.configuration.setSeparationCharacter(SEMICOLON_SEPARATOR_CHARACTER);
    }

    private void setInputFilePaths(String[] paths) {
        for (String inputFileName : paths) {
            tasksToRead.add(new Thread(new CSVFileParser(new File(inputFileName), this)));
        }
    }

    public void generateOutputFileNames() {
        for (String key : table.getKeySet()) {
            tasksToWrite.add(new Thread(new CSVFileSaver(new File(assembleFullPath(key)), key, this)));
        }
    }

    private String assembleFullPath(String key) {
        return configuration.getOutputFilesPath().concat(key).concat(configuration.getOutputFilesMask());
    }

    private void executeTaskList(List<Thread> tasks) {
        for (Thread task : tasks) {
            task.start();
        }
    }

    private void waitThreadsExecution(List<Thread> tasks) throws InterruptedException {
        for (Thread thread : tasks) {
            thread.join();
        }
    }

    public ContentTable getTable() {
        return table;
    }

    public String getTableSeparationCharacter() {
        return this.configuration.getSeparationCharacter();
    }

    public void putValueInTableColumn(String s, String separatedValue) {
        table.putValueInColumn(s, separatedValue);
    }

    public Set<String> getTableColumnSetByKey(String key) {
        return table.getColumnSetByKey(key);
    }

    public void putIntoTable(String key, Set<String> set) {
        table.put(key, set);
    }

    public Set<String> getColumnSetByKey(String key) {
        return table.getColumnSetByKey(key);
    }

    public void executeTasks() {
        try {
            setInputFilePaths(configuration.getInputFilePaths());
            executeTaskList(tasksToRead);
            waitThreadsExecution(tasksToRead);
            generateOutputFileNames();
            executeTaskList(tasksToWrite);
            waitThreadsExecution(tasksToWrite);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
