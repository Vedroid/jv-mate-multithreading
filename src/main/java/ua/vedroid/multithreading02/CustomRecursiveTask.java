package ua.vedroid.multithreading02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Long> {
    private final int threshold;
    private final List<Long> list;

    public CustomRecursiveTask(List<Long> list, int threshold) {
        if (threshold < 1) {
            throw new IllegalArgumentException("Threshold cannot be less than 1, threshold="
                    + threshold);
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        this.list = list;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if (list.size() > threshold) {
            return ForkJoinTask.invokeAll(createSubtasks(threshold))
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(list);
        }
    }

    private Collection<CustomRecursiveTask> createSubtasks(int threshold) {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        int size = list.size();
        dividedTasks.add(new CustomRecursiveTask(list.subList(0, size / 2), threshold));
        dividedTasks.add(new CustomRecursiveTask(list.subList(size / 2, size), threshold));
        return dividedTasks;
    }

    private Long processing(List<Long> list) {
        return list.stream().reduce(0L, Long::sum);
    }
}
