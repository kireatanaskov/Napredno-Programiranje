package event_calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Event implements Comparable<Event> {
    private String name;
    private String location;
    private Date date;

    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Event other) {
        return Comparator.comparing(Event::getDate)
                .thenComparing(Event::getName)
                .compare(this, other);
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd MMM, YYY HH:mm");
        return String.format("%s at %s, %s", df.format(date), location, name);
    }
}
