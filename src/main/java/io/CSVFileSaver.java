package io;

import java.io.*;

public class CSVFileSaver extends AbstractFileSaver {
    private final CSVMediator mediator;

    public CSVFileSaver(File file, String mapKey, CSVMediator mediator) {
        super(file, mapKey);
        this.mediator = mediator;
    }

    @Override
    public void writeOutputFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String value : mediator.getColumnSetByKey(mapKey)) {
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
        return value.concat(mediator.getTableSeparationCharacter());
    }
}
