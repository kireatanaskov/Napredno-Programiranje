package airports;

import java.util.*;

public class Airports {
    private Map<String, Airport> airports;

    public Airports() {
        this.airports = new TreeMap<String, Airport>();
    }

    public void addAirport(String name, String country, String code, int passengers) {
        this.airports.put(code, new Airport(name, country, code, passengers));
    }

    public void addFlights(String from, String to, int time, int duration) {
        Airport airport = airports.get(from);
        airport.addFlight(from, to, time, duration);
    }

    public void showFlightsFromAirport(String code) {
        Airport airport = this.airports.get(code);
        System.out.println(airport);
        int i = 1;
        for (String toCode : airport.flights.keySet()) {
            Set<Flight> flights = airport.flights.get(toCode);
            for (Flight flight : flights) {
                System.out.printf("%d. %s\n", i++, flight);
            }
        }
    }

    public void showDirectFlightsFromTo(String from, String to) {
        Airport airport = airports.get(from);
        Set<Flight> flights = airport.flights.get(to);
        if (flights != null) {
            for (Flight f : flights) {
                System.out.println(f);
            }
        } else {
            System.out.printf("No flights from %s to %s\n", from, to);
        }
    }

    public void showDirectFlightsTo(String to) {
        Set<Flight> flights = new TreeSet<Flight>();
        for (Airport airport : airports.values()) {
            Set<Flight> flightsTo = airport.flights.get(to);
            if (flightsTo != null) {
                flights.addAll(flightsTo);
            }
        }
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
