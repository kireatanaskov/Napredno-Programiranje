package payroll_systems;

public class HourlyEmployee extends Employee{
    private double hours;
    private double overtime;
    private double normal;
    private double rate;

    public HourlyEmployee(String ID, String level, double hours, double rate) {
        super(ID, level);
        this.hours = hours;
        this.overtime = Math.max(0, hours - 40);
        this.normal = hours - overtime;
        this.rate = rate;
    }

    @Override
    public double getSalary() {
        return this.normal * rate + this.overtime * (rate * 1.5);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f Regular hours: %.2f Overtime hours: %.2f",
                this.ID,
                this.level,
                this.getSalary(),
                this.normal,
                this.overtime);
    }
}
