package audition;

import java.util.*;

public class Audition {
    private HashMap<String, HashSet<Participant>> participants;

    public Audition() {
        participants = new HashMap<String, HashSet<Participant>>();
    }

    public void addParticipant(String city, String code, String name, int age) {
        participants.putIfAbsent(city, new HashSet<Participant>());
        participants.get(city).add(new Participant(code, name, age));
    }

    public void listByCity(String city) {
        participants.getOrDefault(city, new HashSet<>())
                .stream()
                .sorted()
                .forEach(System.out::println);
    }
}
