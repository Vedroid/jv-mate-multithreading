package ua.vedroid.multithreading02;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class AppStarter {
    private static final int THRESHOLD = 10_000;

    public static void main(String[] args) {
        List<Long> longList1 = Util.getLongList();
        List<Long> longList2 = Util.getLongList();

        CustomExecutorService customExecutorService = new CustomExecutorService();
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        long start = System.currentTimeMillis();

        long sum1 = 0L;
        for (int i = 0; i < 1_000_000; i++) {
            sum1 += i;
        }

        long point1 = System.currentTimeMillis();

        long sum2 = customExecutorService.getSum(longList1, THRESHOLD);

        long point2 = System.currentTimeMillis();

        long sum3 = forkJoinPool.invoke(new CustomRecursiveTask(longList2, THRESHOLD));

        long end = System.currentTimeMillis();

        System.out.println("sum1=" + sum1 + " time=" + (point1 - start));
        System.out.println("sum2=" + sum2 + " time=" + (point2 - point1));
        System.out.println("sum3=" + sum3 + " time=" + (end - point2));
    }
}
