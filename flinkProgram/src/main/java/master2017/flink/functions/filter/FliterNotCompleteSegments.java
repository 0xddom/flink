package master2017.flink.functions.filter;

import master2017.flink.model.AvgSpeedFinesEvent;
import org.apache.flink.api.common.functions.FilterFunction;

public class FliterNotCompleteSegments implements FilterFunction<AvgSpeedFinesEvent> {
    private int startSegment;
    private int endSegment;
    private int speedLimit;

    public FliterNotCompleteSegments(int startSegment, int endSegment, int speedLimit) {
        this.startSegment = startSegment;
        this.endSegment = endSegment;
        this.speedLimit = speedLimit;
    }

    @Override
    public boolean filter(AvgSpeedFinesEvent avgSpeedFinesEvent) throws Exception {
        return avgSpeedFinesEvent.getMinSeg() == startSegment
                && avgSpeedFinesEvent.getMaxSeg() == endSegment
                && avgSpeedFinesEvent.getAvgSpd() > speedLimit;
    }
}
