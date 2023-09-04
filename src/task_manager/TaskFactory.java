package task_manager;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class TaskFactory {
    public static ITask CreateTask(String line) throws DeadlineNotValidException {
        String[] parts = line.split(",");
        String category = parts[0];
        String name = parts[1];
        String description = parts[2];
        SimpleTask task = new SimpleTask(category, name, description);
        if (parts.length == 3)
            return task;
        else if (parts.length == 4) {
            try {
                int priority = Integer.parseInt(parts[3]);
                return new PriorityTaskDecorator(task, priority);
            } catch (Exception e) {
                LocalDateTime deadline = LocalDateTime.parse(parts[3]);
                checkDeadline(deadline);
                return new TimeTaskDecorator(task, deadline);
            }
        } else {
            LocalDateTime deadline = LocalDateTime.parse(parts[3]);
            checkDeadline(deadline);
            int priority = Integer.parseInt(parts[4]);
//            return new PriorityTaskDecorator(new TimeTaskDecorator(task, deadline), priority);
            return new TimeTaskDecorator(new PriorityTaskDecorator(task, priority), deadline);
        }
    }

    private static void checkDeadline(LocalDateTime deadline) throws DeadlineNotValidException {
        if (deadline.isBefore(LocalDateTime.of(2020, 6, 2, 23, 23, 0)))
            throw new DeadlineNotValidException(deadline);
    }
}
