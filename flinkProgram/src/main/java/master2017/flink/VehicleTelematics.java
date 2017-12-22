package master2017.flink;

import master2017.flink.functions.filter.FilterAvgSpedGreaterThanValue;
import master2017.flink.functions.filter.FilterEventsInSpeedControlSegment;
import master2017.flink.functions.filter.FilterSpeedLimitsInCarEvents;
import master2017.flink.functions.filter.FliterNotCompleteSegments;
import master2017.flink.functions.map.FlatMapAccidentEvents;
import master2017.flink.functions.map.MarCarEventToSpeedFineEvent;
import master2017.flink.functions.reduce.GroupAccidentEvents;
import master2017.flink.functions.reduce.GroupVisitedSegments;
import master2017.flink.model.AccidentsEvent;
import master2017.flink.model.AvgSpeedFinesEvent;

import master2017.flink.model.CarEvent;
import master2017.flink.model.SpeedFineEvent;
import master2017.flink.utils.ResourceLocations;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.core.fs.FileSystem;

public class VehicleTelematics {

    private ResourceLocations locations;
    private ExecutionEnvironment env;
    private final static int SPEED_LIMIT_SPEED = 90;

    private final static int AVG_SPEED_CONTROL_MIN_SEG = 52;
    private final static int AVG_SPEED_CONTROL_MAX_SEG = 56;
    private final static int AVG_SPEED_CONTROL_LIMIT_SPEED = 60;

    private final String PROCESS_NAME = "Car telematics Flink process";

    private VehicleTelematics(String... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("[USAGE] <input file> <output dir>");
        }
        this.locations = new ResourceLocations(args[0], args[1]);
        this.env = ExecutionEnvironment.getExecutionEnvironment();
        //env.getConfig().enableForceAvro();
    }

    public static void main(String... args) throws Exception {
        try {
            (new VehicleTelematics(args)).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        DataSet<CarEvent> carEvents = this.env.readCsvFile(locations.getInputFile())
                .pojoType(CarEvent.class,
                        "time", "vid", "spd", "xway", "lane", "dir", "seg", "pos");

        DataSet<SpeedFineEvent> speedfineEvents = carEvents
                .filter(new FilterSpeedLimitsInCarEvents(SPEED_LIMIT_SPEED))
                .map(new MarCarEventToSpeedFineEvent());

        speedfineEvents.writeAsText(locations.getSpeedFinesCsv(), FileSystem.WriteMode.OVERWRITE);

        DataSet<AvgSpeedFinesEvent> avgSpeedEvents = carEvents
                .filter(new FilterEventsInSpeedControlSegment(AVG_SPEED_CONTROL_MIN_SEG, AVG_SPEED_CONTROL_MAX_SEG))
                .groupBy("vid")
                .reduceGroup(new GroupVisitedSegments(AVG_SPEED_CONTROL_MIN_SEG, AVG_SPEED_CONTROL_MAX_SEG))
                .filter(new FilterAvgSpedGreaterThanValue(AVG_SPEED_CONTROL_LIMIT_SPEED));

        avgSpeedEvents.writeAsText(locations.getAvgSpeedFinesCsv(), FileSystem.WriteMode.OVERWRITE);
        
        DataSet<AccidentsEvent> accidentsEvents = carEvents
                .groupBy("vid", "xway", "lane", "dir", "seg", "pos")
                .sortGroup("time", Order.ASCENDING)
                .reduceGroup(new GroupAccidentEvents())
                .flatMap(new FlatMapAccidentEvents());

        accidentsEvents.writeAsText(locations.getAccidentsCsv(), FileSystem.WriteMode.OVERWRITE);

        try {
            env.execute(PROCESS_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
