package master2017.flink.utils;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.core.fs.FileSystem;

public class FileSink<T> {
    DataSet<T> dataSet;

    public FileSink(DataSet<T> dataSet) {
        this.dataSet = dataSet;
    }

    public void write(String path) {
        dataSet.writeAsText(path, FileSystem.WriteMode.OVERWRITE);
    }
}
