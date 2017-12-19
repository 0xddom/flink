package master2017.flink.functions.reduce;

import master2017.flink.model.AccidentsEvent;
import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

public class GroupAccidentEvents implements GroupReduceFunction<CarEvent, AccidentsEvent> {
    @Override
    public void reduce(Iterable<CarEvent> iterable, Collector<AccidentsEvent> collector) throws Exception {
        AccidentsEvent accentEvent = new AccidentsEvent();
        for (CarEvent current : iterable) {
            if (accentEvent.getVid() == 0) {
                accentEvent = AccidentsEvent.fromCarEvent(current);
            } else {
                if (accentEvent.isTimeConsecutive(current)) { // check is consecutive
                    System.out.println("current vid: " + current.getVid() + " time: " + current.getTime());
                    accentEvent.setTime2(current.getTime());
                } else { // if not collect if there are 4 or more records
                    if (accentEvent.has4orMoreRecords()) {
                        collector.collect(accentEvent.clone());
                        accentEvent.setVid(0);
                    }
                }
            }
        }
        if (accentEvent.has4orMoreRecords()) { //collect after loop if there are 4 or more records
            collector.collect(accentEvent.clone());
        }
    }
}
