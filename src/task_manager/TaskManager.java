package task_manager;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private Map<String, List<ITask>> tasks;

    public void readTasks(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        this.tasks = bufferedReader.lines()
                .map(line -> {
                    try {
                        return TaskFactory.CreateTask(line);
                    } catch (DeadlineNotValidException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        ITask::getCategory,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }

    public void printTasks(OutputStream os, boolean includePriority, boolean includeCategory) {
        PrintWriter printWriter = new PrintWriter(os);

        Comparator<ITask> priorityComparator = Comparator.comparing(ITask::getPriority).thenComparing(task -> Duration.between(LocalDateTime.now(), task.getDeadline()));
        Comparator<ITask> simpleComparator = Comparator.comparing(task -> Duration.between(LocalDateTime.now() , task.getDeadline()));

        if (includeCategory) {
            tasks.forEach((category, t) -> {
                printWriter.println(category.toUpperCase());
                t.stream().sorted(includePriority ? priorityComparator : simpleComparator).forEach(printWriter::println);
            });
        } else {
            tasks.values().stream()
                    .flatMap(Collection::stream)
                    .sorted(includePriority ? priorityComparator : simpleComparator)
                    .forEach(printWriter::println);
        }
    }
}
