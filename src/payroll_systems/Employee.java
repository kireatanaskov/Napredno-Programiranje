package payroll_systems;

import java.util.Comparator;

public abstract class Employee implements Comparable<Employee> {
    String ID;
    String level;

    public Employee(String ID, String level) {
        this.ID = ID;
        this.level = level;
    }
    public String getLevel() {
        return level;
    }

    public abstract double getSalary();

    @Override
    public int compareTo(Employee o) {
        return Comparator.comparing(Employee::getSalary)
                .thenComparing(Employee::getLevel).reversed()
                .compare(this, o);
    }
}
