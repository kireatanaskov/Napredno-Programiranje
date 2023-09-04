package task_manager;

import java.time.LocalDateTime;

public class DeadlineNotValidException extends Exception{
    public DeadlineNotValidException(LocalDateTime deadline) {
        super(String.format("The deadline %s has already passed", deadline));
    }
}
