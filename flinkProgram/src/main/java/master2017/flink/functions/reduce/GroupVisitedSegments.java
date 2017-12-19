package master2017.flink.functions.reduce;


import master2017.flink.model.AvgSpeedFinesEvent;
import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;


public class GroupVisitedSegments implements GroupReduceFunction<CarEvent, AvgSpeedFinesEvent> {
    @Override
    public void reduce(Iterable<CarEvent> iterable, Collector<AvgSpeedFinesEvent> collector) throws Exception {
        // BUGGY. CHANGEME
        AvgSpeedFinesEvent avg = new AvgSpeedFinesEvent();
        for (CarEvent current : iterable) {
            if (avg.getVid() == 0) {
                avg = AvgSpeedFinesEvent.fromCarEvent(current);
            } else {
                avg.setTime1(current.getTime());
                avg.setTime2(current.getTime());
                avg.setAvgSpd(current.getSpd());
                avg.setMinSeg(current.getSeg());
                avg.setMaxSeg(current.getSeg());
            }
        }
        System.out.println(avg.getVid());
        collector.collect(avg);
    }
}
