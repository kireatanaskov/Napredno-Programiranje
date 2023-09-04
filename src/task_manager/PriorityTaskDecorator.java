package task_manager;

import java.time.LocalDateTime;

public class PriorityTaskDecorator extends TaskDecorator{
    int priority;

    public PriorityTaskDecorator(ITask iTask, int priority) {
        super(iTask);
        this.priority = priority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(iTask.toString(), 0, iTask.toString().length()-1);
        sb.append(", priority=").append(priority).append("}");
        return sb.toString();
    }

    @Override
    public String getCategory() {
        return iTask.getCategory();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public LocalDateTime getDeadline() {
        return LocalDateTime.MAX;
    }
}
