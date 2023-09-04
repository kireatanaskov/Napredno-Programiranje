package event_calendar;

import java.util.Date;

public class WrongDateException extends Exception{
    public WrongDateException(Date date) {
        super(String.format("Wrong date: %s", date));
    }
}
