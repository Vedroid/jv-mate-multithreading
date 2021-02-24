package ua.vedroid.multithreading02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomRecursiveTaskTest {
    private static final long EXPECTED = 499999500000L;
    private static List<Long> longList;

    @BeforeAll
    public static void beforeAll() {
        longList = Util.getLongList();
    }

    @Test
    public void compute_Ok() {
        assertEquals(EXPECTED, new CustomRecursiveTask(longList, 1_000_000).compute());
        assertEquals(EXPECTED, new CustomRecursiveTask(longList, 100_000).compute());
        assertEquals(EXPECTED, new CustomRecursiveTask(longList, 10_000).compute());
        assertEquals(EXPECTED, new CustomRecursiveTask(longList, 1_000).compute());
        assertEquals(EXPECTED, new CustomRecursiveTask(longList, 100).compute());
    }

    @Test
    public void compute_NotOk() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomRecursiveTask(longList, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new CustomRecursiveTask(longList, -10));
        assertThrows(IllegalArgumentException.class,
                () -> new CustomRecursiveTask(Collections.emptyList(), 100));
    }
}
