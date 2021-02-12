package io;

import java.io.File;

public abstract class AbstractFileParser implements Runnable, FileParser {
    protected final File file;

    public AbstractFileParser(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        parseInputFile();
    }
}
