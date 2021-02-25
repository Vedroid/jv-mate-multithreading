package ua.vedroid.multithreading02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ListSupplier {
    private ListSupplier() {
    }

    public static List<Long> getLongList() {
        return LongStream.range(1, 1_000_000).boxed().collect(Collectors.toList());
    }
}
