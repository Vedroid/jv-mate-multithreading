package ua.vedroid.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadB implements Runnable {
    private static final Logger log = LogManager.getLogger();
    private static final int MAX = 100;
    private final Counter counter;

    public ThreadB(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        log.info(threadName + " is running...");
        while (counter.getNum() < MAX) {
            int newValue = counter.incrementNumber();
            log.info(threadName + " - " + newValue);
        }
    }
}
