package log_collector;

import java.util.ArrayList;
import java.util.List;

public class Microservice {
    String name;
    List<Log> logs = new ArrayList<Log>();

    public Microservice(String name) {
        this.name = name;
    }

    public void addLog(Log log) {
        logs.add(log);
    }

    public int getSeverity() {
        int total = 0;
        for (Log log : logs) {
            if (log.type.equals("INFO")) {
                total += 0;
            } else if (log.type.equals("WARN")) {
                if (log.message.contains("might cause error")) {
                    total += 2;
                } else total += 1;
            } else if (log.type.equals("ERROR")) {
                total += 3;
                if (log.message.contains("fatal")) {
                    total += 2;
                }
                if (log.message.contains("exception")) {
                    total += 3;
                }
            }
        }
        return total;
    }

    public int getNumLogs() {
        return logs.size();
    }

    public double getSeverityPerLog() {
        double total = 0;
        for (Log log : logs) {
            if (log.type.equals("INFO")) {
                total += 0;
            } else if (log.type.equals("WARN")) {
                if (log.message.contains("might cause error")) {
                    total += 2;
                } else total += 1;
            } else if (log.type.equals("ERROR")) {
                total += 3;
                if (log.message.contains("fatal")) {
                    total += 2;
                }
                if (log.message.contains("exception")) {
                    total += 3;
                }
            }
        }
        return total / logs.size();
    }
}
