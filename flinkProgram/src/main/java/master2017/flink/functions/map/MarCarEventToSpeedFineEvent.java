package master2017.flink.functions.map;

import master2017.flink.model.CarEvent;
import master2017.flink.model.SpeedFineEvent;
import org.apache.flink.api.common.functions.MapFunction;

public class MarCarEventToSpeedFineEvent implements MapFunction<CarEvent, SpeedFineEvent> {
    @Override
    public SpeedFineEvent map(CarEvent carEvent) throws Exception {
        return SpeedFineEvent.fromCarEvent(carEvent);
    }
}
