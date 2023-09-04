package daily_temperatures;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Measurement {
    private int day;
    private List<Double> temperatures;
    DoubleSummaryStatistics dss;

    public Measurement(int day, List<Double> temperatures) {
        this.day = day;
        this.temperatures = temperatures;
        this.dss = temperatures.stream().collect(Collectors.summarizingDouble(x -> x));
    }

    public static double toCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double toFahrenheit(double celsius) {
        return (celsius * 9) / 5 + 32;
    }

    public int getDay() {
        return day;
    }

    public String summary(char c) {
        double min = dss.getMin();
        double max = dss.getMax();
        double average = dss.getAverage();
        if (c == 'F') {
            min = toFahrenheit(min);
            max = toFahrenheit(max);
            average = toFahrenheit(average);
        }
        return String.format("Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c",
                dss.getCount(),
                min, c,
                max, c,
                average, c);
    }

    @Override
    public String toString() {
        return String.format("%3d", day);
    }
}
