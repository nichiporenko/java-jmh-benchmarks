package com.nichiporenko.harness.jmh.benchmarks.collections.lists.stack;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of 100,000 <b>push</b> operations
 * for the {@link Stack} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class StackPushBenchmark implements BasicList {
    private Stack<String> list;
    private String[] items;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Param(value = {"100000"})
    private int numEntriesToAdd;

    @Setup(Level.Invocation)
    public void preparePut() {
        list = new Stack<>();
        fillStringsList(list, numEntriesPrefilled);
        items = generateStringsToAdd(numEntriesToAdd);
    }

    @Benchmark
    public void run(Blackhole bh) {
        for (int i = 0; i < numEntriesToAdd; i++) {
            list.push(items[i]);
        }
        bh.consume(list);
    }
}
