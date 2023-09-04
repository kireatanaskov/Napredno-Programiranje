package log_collector;

import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private String name;
    Map<String, Microservice> microservices = new HashMap<String, Microservice>();

    public Service(String name) {
        this.name = name;
    }

    public void addLog(Log log) {
        microservices.putIfAbsent(log.microservice_name, new Microservice(log.microservice_name));
        microservices.get(log.microservice_name).addLog(log);
    }

    public double averageSeverity() {
        return this.microservices.values()
                .stream()
                .mapToDouble(Microservice::getSeverity)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        IntSummaryStatistics allSeveritiesStats = microservices.values().stream()
                .flatMap(microservice -> microservice.logs.stream())
                // flatMap pretvara stream od objekti vo stream od elementi
                // co primerov stream go microservices go pretvara vo stream od logs
                // kade sto gi stava site logovi od site microservices
                .mapToInt(Log::severity)
                .summaryStatistics();

        return String.format("Service name: %s" + " Count of microservices: %d" + " Total logs in service: %d" + " Average severity for all logs: %.2f" + " Average number of logs per microservice: %.2f",
                name,
                microservices.size(),
                allSeveritiesStats.getCount(),
                allSeveritiesStats.getAverage(),
                allSeveritiesStats.getCount() / (float) microservices.size());
    }

    public Map<Integer, Integer> getSeverityDistribution(String microService) {
        List<Integer> severities;
        if (microService == null) {
            severities = microservices.values().stream()
                    .flatMap(microservice -> microservice.logs.stream().map(Log::severity))
                    .collect(Collectors.toList());
        } else {
            severities = microservices.get(microService).logs.stream().map(Log::severity).collect(Collectors.toList());
        }

        return severities.stream()
                .collect(Collectors.groupingBy(
                        i -> i,
                        TreeMap::new,
                        Collectors.summingInt(i -> 1)
                ));
    }

    public void displayLogs(String microservice, String order) {
        Comparator<Log> byTimestamp = Comparator.comparing(Log::getTimestamp);
        Comparator<Log> bySeverity = Comparator.comparing(Log::severity).thenComparing(Log::getTimestamp);

        Comparator<Log> logComparator;

        switch (order) {
            case "NEWEST_FIRST":
                logComparator = byTimestamp.reversed();
                break;
            case "OLDEST_FIRST":
                logComparator = byTimestamp;
                break;
            case "MOST_SEVERE_FIRST":
                logComparator = bySeverity.reversed();
                break;
            default:
                logComparator = bySeverity;
        }

        List<Log> logs;
        if (microservice == null) {
            logs = microservices.values().stream().flatMap(m -> m.logs.stream()).collect(Collectors.toList());
        } else {
            logs = microservices.get(microservice).logs;
        }

        logs.stream().sorted(logComparator).forEach(System.out::println);
    }
}
