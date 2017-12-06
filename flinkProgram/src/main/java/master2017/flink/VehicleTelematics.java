package master2017.flink;

import master2017.flink.functions.filter.FilterSpeedLimitsInCarEvents;
import master2017.flink.functions.map.MarCarEventToSpeedFineEvent;
import master2017.flink.model.CarEvent;
import master2017.flink.model.SpeedFineEvent;
import master2017.flink.utils.ResourceLocations;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.core.fs.FileSystem;

public class VehicleTelematics implements Runnable {

    private ResourceLocations locations;
    private ExecutionEnvironment env;

    private VehicleTelematics(String... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        this.locations = new ResourceLocations(args[0], args[1]);
        this.env = ExecutionEnvironment.getExecutionEnvironment();
        //env.getConfig().enableForceAvro();
    }

    public static void main(String... args) throws Exception {
        (new VehicleTelematics(args)).run();
    }

    @Override
    public void run() {
        DataSet<CarEvent> carEvents = this.env.readCsvFile(this.locations.getInputFile())
                .pojoType(CarEvent.class,
                        "time", "vid", "spd", "xway", "lane", "dir", "seg", "pos");

        DataSet<SpeedFineEvent> speedfineEvents = carEvents
                .filter(new FilterSpeedLimitsInCarEvents(90))
                .map(new MarCarEventToSpeedFineEvent());

        speedfineEvents.writeAsText(locations.getSpeedFinesCsv(), FileSystem.WriteMode.OVERWRITE);


        try {
            env.execute("Car telematics process");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
