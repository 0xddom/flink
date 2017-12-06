package master2017.flink.functions.filter;

import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.FilterFunction;

public class FilterEventsInSpeedControlSegment implements FilterFunction<CarEvent> {
    private int lowerSeg;
    private int upperSeg;

    public FilterEventsInSpeedControlSegment(int lowerSeg, int upperSeg) {
        this.lowerSeg = lowerSeg;
        this.upperSeg = upperSeg;
    }

    @Override
    public boolean filter(CarEvent carEvent) throws Exception {
        return carEvent.getSeg() >= lowerSeg && carEvent.getSeg() <= this.upperSeg;
    }
}
