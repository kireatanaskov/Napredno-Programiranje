package daily_temperatures;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DailyTemperatures {
    private List<Measurement> measurements;

    public DailyTemperatures() {
        this.measurements = new ArrayList<>();
    }

    public void readTemperatures(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        this.measurements = bufferedReader.lines()
                .map(MeasurementFactory::createMeasurement)
                .collect(Collectors.toList());
    }

    public void writeDailyStats(OutputStream outputStream, char scale) {
        PrintWriter pw = new PrintWriter(outputStream);

        this.measurements.stream()
                .sorted(Comparator.comparingInt(Measurement::getDay))
                .forEach(measurement -> pw.println(String.format("%s: %s", measurement, measurement.summary(scale))));

        pw.flush();
    }
}
