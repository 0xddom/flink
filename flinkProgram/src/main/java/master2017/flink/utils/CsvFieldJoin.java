package master2017.flink.utils;

public class CsvFieldJoin<T> {
    private String sep;

    public CsvFieldJoin(String sep) {
        this.sep = sep;
    }

    public CsvFieldJoin() {
        this(",");
    }

    public String join(T... fields) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < fields.length; i++) {
            sb.append(fields[i]);
            if (i < fields.length-1) sb.append(sep);
        }
        return sb.toString();
    }
}
