package io;

import java.io.File;

public abstract class AbstractFileSaver implements Runnable, FileSaver {
    protected final File    file;
    protected final String  mapKey;

    public AbstractFileSaver(File file, String mapKey) {
        this.file = file;
        this.mapKey = mapKey;
    }

    @Override
    public void run() {
        writeOutputFile();
    }
}
