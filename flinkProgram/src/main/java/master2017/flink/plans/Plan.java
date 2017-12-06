package master2017.flink.plans;

import org.apache.flink.api.java.DataSet;

public interface Plan<I,O> {
    DataSet<O> plan(DataSet<I> input);
}
