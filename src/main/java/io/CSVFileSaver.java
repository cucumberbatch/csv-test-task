package io;

import model.CSVTable;

import java.io.*;

public class CSVFileSaver implements Runnable {
    private final CSVTable table;
    private final File file;

    public CSVFileSaver(File file, CSVTable table) {
        this.table = table;
        this.file = file;
    }

    public void run() {
        writeIntoCSVFile();
    }

    private void writeIntoCSVFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String value : table.getColumnSetByKey(file.getName())) {
                writeCSVValueIntoFile(writer, value);
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void writeCSVValueIntoFile(BufferedWriter writer, String value) throws IOException {
        writer.write(getCSVFormattedRow(value));
    }

    private String getCSVFormattedRow(String value) {
        return value.concat(table.getSeparationCharacter());
    }
}
