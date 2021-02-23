package ua.vedroid.multithreading02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomExecutorService {
    public Long getSum(List<Long> list, int threshold) {
        if (threshold < 1) {
            throw new IllegalArgumentException("Threshold cannot be less than 1, threshold="
                    + threshold);
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        List<Callable<Long>> tasks = getTasks(list, threshold);
        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());
        Long sum = 0L;
        try {
            for (Future<Long> future : executorService.invokeAll(tasks)) {
                sum += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        return sum;
    }

    private List<Callable<Long>> getTasks(List<Long> list, int threshold) {
        List<Callable<Long>> callableTasks = new ArrayList<>();
        List<List<Long>> choppedLists = Util.chopList(list, threshold);
        for (int i = 0; i < choppedLists.size(); i++) {
            int index = i;
            Long reduce = choppedLists.get(index).stream().reduce(0L, Long::sum);
            callableTasks.add(
                    () -> choppedLists.get(index).stream().reduce(0L, Long::sum));
        }
        return callableTasks;
    }
}
