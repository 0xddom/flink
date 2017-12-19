package master2017.flink.functions.map;

import master2017.flink.model.AccidentsEvent;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.List;

public class FlatMapAccidentEvents implements FlatMapFunction<AccidentsEvent, AccidentsEvent> {
    @Override
    public void flatMap(AccidentsEvent accidentsEvent, Collector<AccidentsEvent> collector) throws Exception {
        List<AccidentsEvent> events = accidentsEvent.splite();
        for (AccidentsEvent event : events) {
            collector.collect(event);
        }
    }
}
