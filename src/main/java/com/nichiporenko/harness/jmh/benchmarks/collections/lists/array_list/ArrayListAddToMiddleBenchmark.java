package com.nichiporenko.harness.jmh.benchmarks.collections.lists.array_list;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of 100,000 <b>add</b> operations
 * to the middle of the {@link ArrayList} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class ArrayListAddToMiddleBenchmark implements BasicList {
    private List<String> list;
    private String[] entriesToAdd;

    @Param(value = {"1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Param(value = {"100000"})
    private int numEntriesToAdd;

    @Setup(Level.Iteration)
    public void setup() {
        list = new ArrayList<>();
        fillStringsList(list, numEntriesPrefilled);
        entriesToAdd = generateStringsToAdd(numEntriesToAdd);
    }

    @Benchmark
    public void run() {
        for (int i = 0; i < numEntriesToAdd; i++) {
            list.add(numEntriesPrefilled / 2, entriesToAdd[i]);
        }
    }


}
