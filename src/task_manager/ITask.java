package task_manager;

import java.time.LocalDateTime;

public interface ITask {
    String getCategory();

    int getPriority();
    LocalDateTime getDeadline();
}
