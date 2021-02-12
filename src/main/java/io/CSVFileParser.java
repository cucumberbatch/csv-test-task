package io;

import java.io.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class CSVFileParser extends AbstractFileParser {
    private final CSVMediator   mediator;

    public CSVFileParser(File file, CSVMediator mediator) {
        super(file);
        this.mediator = mediator;
    }

    @Override
    public void parseInputFile() {
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
                if (mediator.getTableColumnSetByKey(header) == null) {
                    mediator.putIntoTable(header, new CopyOnWriteArraySet<>());
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
                mediator.putValueInTableColumn(headersRow[index], separatedValues[index]);
            }
        }
    }

    private String[] getParsedCSVArray(String s) {
        return s.split(mediator.getTableSeparationCharacter());
    }

}
