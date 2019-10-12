package com.nichiporenko.harness.jmh.benchmarks.lists.linked_list;

import com.nichiporenko.harness.jmh.benchmarks.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class LinkedListPutBenchmark implements BasicList {
    private List<String> list;
    private String[] items;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ENTRIES_BEFORE;

    @Param(value = {"100000"})
    private int ENTRIES_PUT;

    @Setup(Level.Invocation)
    public void preparePut() {
        list = new LinkedList<>();
        fillList(list, ENTRIES_BEFORE);
        items = generateItemsForPutting(ENTRIES_PUT);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        for (int i = 0; i < ENTRIES_PUT; i++) {
            blackhole.consume(list.add(items[i]));
        }
    }
}
