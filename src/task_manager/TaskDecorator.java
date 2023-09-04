package task_manager;

import java.time.LocalDateTime;

public abstract class TaskDecorator implements ITask{
    ITask iTask;

    public TaskDecorator(ITask iTask) {
        this.iTask = iTask;
    }
}
