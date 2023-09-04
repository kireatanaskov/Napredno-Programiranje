package event_calendar;

import java.util.*;

public class EventCalendar {
    private int year;
    private Map<Integer, Set<Event>> events;
    private Map<Integer, Integer> months;

    public EventCalendar(int year) {
        this.year = year;
        this.events = new HashMap<Integer, Set<Event>>();
        this.months = new HashMap<Integer, Integer>();
    }

    private static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    private static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    private static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public void addEvent(String name, String location, Date date) throws WrongDateException {
        int year = getYear(date);
        if (year != this.year)
            throw new WrongDateException(date);
        Event event = new Event(name, location, date);
        int day = getDayOfYear(date);
        Set<Event> list = events.get(day);
        if (list == null) {
            list = new TreeSet<Event>();
        }
        list.add(event);
        int monthKey = getMonth(date);
        Integer count = months.get(monthKey);
        if (count == null) {
            count = 0;
        }
        ++count;
        months.put(monthKey, count);
        events.put(day, list);
    }

    public void listEvents(Date date) {
        int day = getDayOfYear(date);
        Set<Event> eventSet = this.events.get(day);
        if (eventSet != null) {
            for (Event e : eventSet)
                System.out.println(e);
        } else {
            System.out.println("No events on this day!");
        }
    }

    public void listByMonth() {
        for (int i = 1; i <= 12; i++) {
            System.out.printf("%d : %d\n", i, months.get(i) == null ? 0 : months.get(i));
        }
    }
}
