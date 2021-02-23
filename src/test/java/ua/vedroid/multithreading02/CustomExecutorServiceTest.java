package ua.vedroid.multithreading02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustomExecutorServiceTest {
    private static final long EXPECTED = 499999500000L;
    private static CustomExecutorService executorService;
    private static List<Long> longList;

    @BeforeAll
    public static void beforeAll() {
        executorService = new CustomExecutorService();
        longList = Util.getLongList();
    }

    @Test
    public void getSum_Ok() {
        assertEquals(EXPECTED, executorService.getSum(longList, 10_000_000));
        assertEquals(EXPECTED, executorService.getSum(longList, 1_000_000));
        assertEquals(EXPECTED, executorService.getSum(longList, 100_000));
        assertEquals(EXPECTED, executorService.getSum(longList, 10_000));
        assertEquals(EXPECTED, executorService.getSum(longList, 1_000));
    }

    @Test
    public void getSum_NotOk() {
        assertThrows(IllegalArgumentException.class,
                () -> executorService.getSum(Collections.emptyList(), 1_000));
        assertThrows(IllegalArgumentException.class,
                () -> executorService.getSum(longList, 0));
        assertThrows(IllegalArgumentException.class,
                () -> executorService.getSum(longList, -100));
    }
}