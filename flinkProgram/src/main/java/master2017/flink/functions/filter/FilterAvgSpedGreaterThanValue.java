package master2017.flink.functions.filter;

import master2017.flink.model.AvgSpeedFinesEvent;
import org.apache.flink.api.common.functions.FilterFunction;

public class FilterAvgSpedGreaterThanValue implements FilterFunction<AvgSpeedFinesEvent> {
    private int limit;

    public FilterAvgSpedGreaterThanValue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean filter(AvgSpeedFinesEvent avgSpeedFinesEvent) throws Exception {
        return avgSpeedFinesEvent.getAvgSpd() >= limit;
    }
}
