package io;

import model.CSVTable;

import java.io.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class CSVFileParser implements Runnable {
    private final CSVTable  table;
    private final File      file;

    public CSVFileParser(File file, CSVTable table) {
        this.table  = table;
        this.file   = file;
    }

    @Override
    public void run() {
        readFromCSVFile();
    }

    private void readFromCSVFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] headersRow = getParsedCSVArray(reader.readLine());
            initializeColumnHeaders(headersRow);
            insertValuesIntoTable(reader, headersRow);
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void initializeColumnHeaders(String[] headersRow) {
        synchronized (this) {
            for (String header : headersRow) {
                if (table.getColumnSetByKey(header) == null) {
                    table.put(header, new CopyOnWriteArraySet<>());
                }
            }
        }
    }

    private void insertValuesIntoTable(BufferedReader reader, String[] headersRow) throws IOException {
        String[]    separatedValues;
        String      readedRow;

        while ((readedRow = reader.readLine()) != null) {
            separatedValues = getParsedCSVArray(readedRow);

            for (int index = 0; index < headersRow.length; index++) {
                table.putValueInColumn(headersRow[index], separatedValues[index]);
            }
        }
    }

    private String[] getParsedCSVArray(String s) {
        return s.split(table.getSeparationCharacter());
    }

}
