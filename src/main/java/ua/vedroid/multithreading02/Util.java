package ua.vedroid.multithreading02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Util {
    private Util() {
    }

    public static List<List<Long>> chopList(List<Long> list, int threshold) {
        if (threshold < 1) {
            throw new IllegalArgumentException("Threshold cannot be less than 1, threshold="
                    + threshold);
        }
        List<List<Long>> parts = new ArrayList<>();
        int inputListSize = list.size();
        for (int i = 0; i < inputListSize; i += threshold) {
            parts.add(list.subList(i, Math.min(inputListSize, i + threshold)));
        }
        return parts;
    }

    public static List<Long> getLongList() {
        return LongStream.range(1, 1_000_000).boxed().collect(Collectors.toList());
    }
}
