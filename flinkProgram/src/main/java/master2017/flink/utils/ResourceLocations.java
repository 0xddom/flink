package master2017.flink.utils;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ResourceLocations {
    private final String inputFile;
    private final String outputDir;
    private final String speedFinesCsv;
    private final String avgSpeedFinesCsv;
    private final String accidentsCsv;

    private static final String SPEEDFINES = "speedfines.csv";
    private static final String AVGSPEEDFNES = "avgspeedfines.csv";
    private static final String ACCIDENTS = "accidents.csv";


    public ResourceLocations(String inputFile, String outputDir) {
        final FileSystem fs = FileSystems.getDefault();
        Path outputDirPath = fs.getPath(outputDir);
        Path inputFilePath = fs.getPath(inputFile);
        Path speedFinesPath = fs.getPath(outputDir, SPEEDFINES);
        Path avgSpeedFinesPath = fs.getPath(outputDir, AVGSPEEDFNES);
        Path accidentsPath = fs.getPath(outputDir, ACCIDENTS);

        this.inputFile = inputFilePath.toAbsolutePath().toString();
        this.outputDir = outputDirPath.toAbsolutePath().toString();
        this.speedFinesCsv = speedFinesPath.toAbsolutePath().toString();
        this.avgSpeedFinesCsv = avgSpeedFinesPath.toAbsolutePath().toString();
        this.accidentsCsv = accidentsPath.toAbsolutePath().toString();
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getSpeedFinesCsv() {
        return speedFinesCsv;
    }

    public String getAvgSpeedFinesCsv() {
        return avgSpeedFinesCsv;
    }

    public String getAccidentsCsv() {
        return accidentsCsv;
    }
}

