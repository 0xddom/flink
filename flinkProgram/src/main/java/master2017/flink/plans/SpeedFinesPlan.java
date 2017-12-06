package master2017.flink.plans;

import master2017.flink.functions.filter.FilterSpeedLimitsInCarEvents;
import master2017.flink.functions.map.MarCarEventToSpeedFineEvent;
import master2017.flink.model.CarEvent;
import master2017.flink.model.SpeedFineEvent;
import org.apache.flink.api.java.DataSet;

public class SpeedFinesPlan implements Plan<CarEvent, SpeedFineEvent> {
    @Override
    public DataSet<SpeedFineEvent> plan(DataSet<CarEvent> input) {
        return input
                .filter(new FilterSpeedLimitsInCarEvents(90))
                .map(new MarCarEventToSpeedFineEvent());
    }
}
