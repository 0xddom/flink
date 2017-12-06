package master2017.flink;

import master2017.flink.model.CarEvent;
import master2017.flink.plans.AvgSpeedFinesPlan;
import master2017.flink.plans.Plan;
import master2017.flink.plans.SpeedFinesPlan;
import master2017.flink.utils.FileSink;
import master2017.flink.utils.ResourceLocations;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

public class VehicleTelematics {

    private ResourceLocations locations;
    private ExecutionEnvironment env;

    private final String PROCESS_NAME = "Car telematics Flink process";

    private VehicleTelematics(String... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments");
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

    private void run() throws Exception {
        DataSet<CarEvent> carEvents = this.env.readCsvFile(this.locations.getInputFile())
                .pojoType(CarEvent.class,
                        "time", "vid", "spd", "xway", "lane", "dir", "seg", "pos");

        sinkPlan(new SpeedFinesPlan(), carEvents, locations.getSpeedFinesCsv());
        sinkPlan(new AvgSpeedFinesPlan(), carEvents, locations.getAvgSpeedFinesCsv());

        env.execute(PROCESS_NAME);
    }

    private void sinkPlan(Plan plan, DataSet<CarEvent> input, String filePath) {
        (new FileSink<>(plan.plan(input))).write(filePath);
    }
}
