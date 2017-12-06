package master2017.flink.functions.reduce;

import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

public class GroupVisitedSegments implements GroupReduceFunction<CarEvent, CarEvent> {
    private int lowerSeg;
    private int upperSeg;

    public GroupVisitedSegments(int lowerSeg, int upperSeg) {
        this.lowerSeg = lowerSeg;
        this.upperSeg = upperSeg;
    }

    @Override
    public void reduce(Iterable<CarEvent> iterable, Collector<CarEvent> collector) throws Exception {
        // TODO
    }
}
