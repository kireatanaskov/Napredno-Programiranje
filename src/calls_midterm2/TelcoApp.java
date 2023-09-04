package calls_midterm2;

import java.util.*;
import java.util.stream.Collectors;

public class TelcoApp {
    private Map<String, Call> callsByUuid = new HashMap<>();
    private Map<String, List<Call>> callsByPhoneNumber = new HashMap<String, List<Call>>();
    private Comparator<Call> byStart = Comparator.comparing(Call::getStart).thenComparing(Call::getUuid);
    private Comparator<Call> byDuration = Comparator.comparing(Call::getDuration).thenComparing(Call::getStart);

    public void addCall(String uuid, String dialer, String receiver, long timestamp) {
        Call c = new Call(uuid, dialer, receiver, timestamp);
        callsByUuid.put(uuid, c);
        callsByPhoneNumber.putIfAbsent(dialer, new ArrayList<>());
        callsByPhoneNumber.get(dialer).add(c);
        callsByPhoneNumber.putIfAbsent(receiver, new ArrayList<>());
        callsByPhoneNumber.get(receiver).add(c);
    }

    public void updateCall(String uuid, long timestamp, String action){
        try {
            callsByUuid.get(uuid).updateCall(timestamp, action);
        } catch (InvalidOperation e) {
            System.out.println("Invalid operration " + action + " for call " + uuid);
        }
    }

    private void printCall(Call c, String phoneNumber) {
        String type = c.getDialer().equals(phoneNumber) ? "D" : "R";
        String otherPhone = c.getDialer().equals(phoneNumber) ? c.getReceiver() : c.getDialer();
        String end = c.state.end == 0 ? "MISSED CALL" : String.valueOf(c.state.end);
        System.out.printf("%s %s %d %s %s", type, otherPhone, c.getStart(), end, DurationConverter.convert(c.getDuration()));
    }

    public void printChronologicalReport(String phoneNumber) {
        callsByPhoneNumber.get(phoneNumber).stream()
                .sorted(byStart)
                .forEach(c -> printCall(c, phoneNumber));
    }

    public void printReportByDuration(String phoneNumber) {
        callsByPhoneNumber.get(phoneNumber).stream()
                .sorted(byDuration)
                .forEach(c -> printCall(c, phoneNumber));
    }

    public void printCallsDuration() {
        TreeMap<String, Long> result = callsByUuid.values().stream()
                .collect(Collectors.groupingBy(
                        c -> String.format("%s <-> %s", c.getDialer(), c.getReceiver()),
                        TreeMap::new,
                        Collectors.summingLong(Call::getDuration)
                ));

        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%s : %s%n", entry.getKey(), DurationConverter.convert(entry.getValue())));
    }
}
