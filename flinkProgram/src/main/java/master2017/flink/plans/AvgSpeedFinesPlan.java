package master2017.flink.plans;

import master2017.flink.functions.filter.FilterEventsInSpeedControlSegment;
import master2017.flink.functions.reduce.GroupVisitedSegments;
import master2017.flink.model.CarEvent;
import org.apache.flink.api.java.DataSet;

public class AvgSpeedFinesPlan implements Plan<CarEvent, CarEvent> {
    private final int lowerSeg = 52;
    private final int upperSeg = 56;

    @Override
    public DataSet<CarEvent> plan(DataSet<CarEvent> input) {
        return input
                .filter(new FilterEventsInSpeedControlSegment(lowerSeg, upperSeg))
                .groupBy("vid")
                .reduceGroup(new GroupVisitedSegments(lowerSeg, upperSeg));
    }
}
