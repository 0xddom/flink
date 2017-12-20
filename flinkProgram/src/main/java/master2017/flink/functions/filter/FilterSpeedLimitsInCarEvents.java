package master2017.flink.functions.filter;

import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.FilterFunction;

public class FilterSpeedLimitsInCarEvents implements FilterFunction<CarEvent> {
    private int speedLimit;

    public FilterSpeedLimitsInCarEvents(int limit) {
        this.speedLimit = limit;
    }

    @Override
    public boolean filter(CarEvent event) throws Exception {
        return event.getSpd() >= this.speedLimit;
    }
}
