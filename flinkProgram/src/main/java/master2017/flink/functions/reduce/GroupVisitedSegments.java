package master2017.flink.functions.reduce;

import master2017.flink.model.AvgSpeedFinesEvent;
import master2017.flink.model.CarEvent;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;


public class GroupVisitedSegments implements GroupReduceFunction<CarEvent, AvgSpeedFinesEvent> {
    private int lower, upper;

    public GroupVisitedSegments(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;

    }

    private boolean[] createCheckTable() {
        boolean[] t = new boolean[this.upper - this.lower + 1];
        for (int i = 0; i < t.length; i++) {
            t[i] = false;
        }
        return t;
    }

    @Override
    public void reduce(Iterable<CarEvent> iterable, Collector<AvgSpeedFinesEvent> collector) throws Exception {
        AvgSpeedFinesEvent avg = null;
        boolean[] table = createCheckTable();
        for (CarEvent e : iterable) {
            table[e.getSeg()-this.lower] = true;
            if (avg == null) {
                avg = AvgSpeedFinesEvent.fromCarEvent(e);
            } else {
                avg.addSpeed(e.getSpd());
                avg.setTime1(e.getTime());
                avg.setTime2(e.getTime());
            }
        }

        boolean visited = true;
        for (boolean b : table) {
            visited = b;
            if (!visited) break;
        }

        if (visited) {
            collector.collect(avg);
        }

    }
}
