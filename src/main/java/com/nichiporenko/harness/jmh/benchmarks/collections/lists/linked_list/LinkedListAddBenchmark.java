package com.nichiporenko.harness.jmh.benchmarks.collections.lists.linked_list;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of 100,000 <b>add</b> operations
 * for the {@link LinkedList} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class LinkedListAddBenchmark implements BasicList {
    private List<String> list;
    private String[] items;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Param(value = {"100000"})
    private int numEntriesToAdd;

    @Setup(Level.Invocation)
    public void preparePut() {
        list = new LinkedList<>();
        fillStringsList(list, numEntriesPrefilled);
        items = generateStringsToAdd(numEntriesToAdd);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        for (int i = 0; i < numEntriesToAdd; i++) {
            blackhole.consume(list.add(items[i]));
        }
    }
}
