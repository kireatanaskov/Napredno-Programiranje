package payroll_systems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] parts = line.split(";");
        String id = parts[1];
        String level = parts[2];
        if (parts[0].equals("F")) {
            List<Integer> ticketPoints = Arrays.stream(parts).skip(3).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            double rate = ticketRateByLevel.get(level);
            return new FreelanceEmployee(id, level, ticketPoints, rate);
        } else {
            double hours = Double.parseDouble(parts[3]);
            double rate = hourlyRateByLevel.get(level);
            return new HourlyEmployee(id, level, hours, rate);
        }
    }
}
