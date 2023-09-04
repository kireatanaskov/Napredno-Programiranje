package log_collector;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LogCollector {
    private Map<String, Service> services = new HashMap<String, Service>();

    public void addLog(String logInput) {
        Log log = Log.createLog(logInput);
        services.putIfAbsent(log.serviceName, new Service(log.serviceName));
        services.get(log.serviceName).addLog(log);
    }

    public void printServicesBySeverity() {
        this.services.values()
                .stream()
                .sorted(Comparator.comparing(Service::averageSeverity).reversed())
                .forEach(System.out::println);
    }

    public Map<Integer, Integer> getSeverityDistribution (String service, String microservice) {
        return services.get(service).getSeverityDistribution(microservice);
    }

    public void displayLogs(String service, String microservice, String order) {
        this.services.get(service).displayLogs(microservice, order);
    }
}
