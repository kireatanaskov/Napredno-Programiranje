package f1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<Driver>();
    }

    public void readResults(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        this.drivers = bufferedReader.lines()
                .map(DriverFactory::createDriver)
                .collect(Collectors.toList());
    }

    public void printSorted(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        List<Driver> sortedDrivers = this.drivers.stream()
                .sorted(Comparator.comparing(Driver::getBest))
                .collect(Collectors.toList());
        int i = 0;
        for (Driver driver : sortedDrivers) {
            pw.printf("%d. %s\n", ++i, driver);
        }
        pw.close();
    }
}
