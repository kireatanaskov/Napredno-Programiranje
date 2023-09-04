package payroll_systems;

import java.util.List;

public class FreelanceEmployee extends Employee{
    List<Integer> ticketPoints;
    double rate;

    public FreelanceEmployee(String ID, String level, List<Integer> ticketPoints, double rate) {
        super(ID, level);
        this.ticketPoints = ticketPoints;
        this.rate = rate;
    }

    @Override
    public double getSalary() {
        int sum = ticketPoints.stream().mapToInt(i -> i).sum();
        return sum * rate;
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f Tickets count: %d Tickets points: %d",
                this.ID,
                this.level,
                this.getSalary(),
                this.ticketPoints.size(),
                this.ticketPoints.stream().mapToInt(i -> i).sum());
    }
}
