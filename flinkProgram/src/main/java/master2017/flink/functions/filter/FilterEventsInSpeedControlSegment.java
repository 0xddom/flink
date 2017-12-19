package master2017.flink.functions.filter;

import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.FilterFunction;

/**
 * Created by nuonuo-jtl on 2017/12/18.
 */
public class FilterEventsInSpeedControlSegment implements FilterFunction<CarEvent> {
    private int startSegment;
    private int endSegment;

    public FilterEventsInSpeedControlSegment(int startSegment, int endSegment) {
        this.startSegment = startSegment;
        this.endSegment = endSegment;
    }

    @Override
    public boolean filter(CarEvent carEvent) throws Exception {
        return carEvent.getSeg() >= startSegment && carEvent.getSeg() <= endSegment;
    }
}
