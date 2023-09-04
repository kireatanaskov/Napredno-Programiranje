package f1;

public class DriverFactory {
    static Driver createDriver(String line) {
        String[] parts = line.split("\\s+");
        String name = parts[0];
        int lap1 = Driver.stringToTime(parts[1]);
        int lap2 = Driver.stringToTime(parts[2]);
        int lap3 = Driver.stringToTime(parts[3]);
        return new Driver(name, lap1, lap2, lap3);
    }
}
