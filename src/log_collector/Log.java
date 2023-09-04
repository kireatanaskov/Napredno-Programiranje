package log_collector;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Log {
    String serviceName;
    String microservice_name;
    String type;
    String message;
    long timestamp;

    public Log(String serviceName, String microservice_name, String type, String message, long timestamp) {
        this.serviceName = serviceName;
        this.microservice_name = microservice_name;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static Log createLog(String line) {
        String[] parts = line.split("\\s+");
        String serviceName = parts[0];
        String microservice = parts[1];
        String type = parts[2];
        String message = Arrays.stream(parts).skip(1).limit(parts.length - 1).collect(Collectors.joining(" "));
        long timestamp = Long.parseLong(parts[parts.length - 1]);
        return new Log(serviceName, microservice, type, message, timestamp);
    }

    public int severity() {
        int total = 0;
        if (type.equals("INFO")) {
            total += 0;
        } else if (type.equals("WARN")) {
            if (message.contains("might cause error")) {
                total += 2;
            } else total += 1;
        } else if (type.equals("ERROR")) {
            total += 3;
            if (message.contains("fatal")) {
                total += 2;
            }
            if (message.contains("exception")) {
                total += 3;
            }
        }
        return total;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s|%s [%s] %s T:%d", serviceName, microservice_name, type, message, timestamp);
    }
}
