package daily_temperatures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MeasurementFactory {
    public static Measurement createMeasurement(String line) {
        String[] parts = line.split("\\s+");
        int day = Integer.parseInt(parts[0]);
        List<Double> temperatures = new ArrayList<Double>();
        temperatures = Arrays.stream(parts).skip(1)
                .map(temp -> {
                    double parseDouble = Double.parseDouble(temp.substring(0, temp.length() - 1));
                    if (temp.endsWith("C")) {
                        return parseDouble;
                    } else {
                        return Measurement.toCelsius(parseDouble);
                    }
                })
                .collect(Collectors.toList());

        return new Measurement(day, temperatures);
    }
}
