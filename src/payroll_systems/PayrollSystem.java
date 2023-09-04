package payroll_systems;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class PayrollSystem {
    private List<Employee> employees;
    private Map<String, Double> hourlyRateByLevel = new HashMap<String, Double>();
    private Map<String, Double> ticketRateByLevel = new HashMap<String, Double>();

    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
    }

    public void readEmployees(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        this.employees = bufferedReader.lines()
                .map(line -> EmployeeFactory.createEmployee(line, hourlyRateByLevel, ticketRateByLevel))
                .collect(Collectors.toList());
    }

    public Map<String, Set<Employee>> printEmployeesByLevels(OutputStream os, Set<String> levels) {
        return this.employees.stream()
                .filter(employee -> levels.contains(employee.getLevel()))
                .sorted(Comparator.comparing(Employee::getSalary).reversed()
                        .thenComparing(Employee::getLevel).reversed())
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        TreeMap::new,
                        Collectors.toCollection(TreeSet::new)
                ));
    }
}
