package airports;

import java.util.*;

public class Airport {
    String name;
    String country;
    String code;
    int passengers;
    Map<String, Set<Flight>> flights;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
        this.flights = new TreeMap<String, Set<Flight>>();
    }

    public void addFlight(String from, String to, int time, int duration) {
        this.flights.putIfAbsent(to, new TreeSet<Flight>());
        this.flights.get(to).add(new Flight(from, to, time, duration));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(code).append(")\n");
        sb.append(country).append("\n");
        sb.append(passengers).append("\n");
        return sb.toString();
    }
}