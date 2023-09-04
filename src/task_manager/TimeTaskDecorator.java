package task_manager;

import java.time.LocalDateTime;

public class TimeTaskDecorator extends TaskDecorator {
    LocalDateTime deadline;

    public TimeTaskDecorator(ITask iTask, LocalDateTime deadline) {
        super(iTask);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(iTask.toString(), 0, iTask.toString().length()-1);
        sb.append(", deadline=").append(deadline);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String getCategory() {
        return iTask.getCategory();
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
