package model;

public class Configuration {
    private final   String[] inputFilePaths;
    private         String   outputFilesPath;
    private         String   outputFilesMask;
    private         String   separationCharacter;

    public Configuration(Configuration.Builder builder) {
        this.inputFilePaths         = builder.inputFilePaths;
        this.outputFilesPath        = builder.outputFilesPath;
        this.outputFilesMask        = builder.outputFilesMask;
        this.separationCharacter    = builder.separationCharacter;
    }

    public Configuration(String[] inputFilePaths) {
        this.inputFilePaths  = inputFilePaths;
    }

    public String[] getInputFilePaths() {
        return inputFilePaths;
    }

    public void setOutputFilesPath(String outputFilesPath) {
        this.outputFilesPath = outputFilesPath;
    }

    public String getOutputFilesPath() {
        return outputFilesPath;
    }

    public void setOutputFilesMask(String outputFilesMask) {
        this.outputFilesMask = outputFilesMask;
    }

    public String getOutputFilesMask() {
        return outputFilesMask;
    }

    public String getSeparationCharacter() {
        return separationCharacter;
    }

    public void setSeparationCharacter(String separationCharacter) {
        this.separationCharacter = separationCharacter;
    }

    public static class Builder {
        private String[]    inputFilePaths;
        private String      outputFilesPath;
        private String      outputFilesMask;
        private String      separationCharacter;

        public Builder(String[] inputFilePaths) {
            this.inputFilePaths  = inputFilePaths;
        }

        public Builder setOutputFilesPath(String outputFilesPath) {
            this.outputFilesPath = outputFilesPath;
            return this;
        }

        public Builder setOutputFilesMask(String mask) {
            this.outputFilesMask = mask;
            return this;
        }

        public Builder setSeparationCharacter(String separationCharacter) {
            this.separationCharacter = separationCharacter;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

}
