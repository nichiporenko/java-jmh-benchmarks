package com.nichiporenko.harness.jmh.benchmarks.collections.sets.linked_hash_set;

import com.nichiporenko.harness.jmh.benchmarks.collections.sets.BasicSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of 100,000 <b>add</b> operations
 * for the {@link LinkedHashSet} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class LinkedHashSetAddBenchmark implements BasicSet {
    private Set<String> set;
    private String[] items;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Param(value = {"100000"})
    private int ITEMS_ADD;

    @Setup(Level.Invocation)
    public void preparePut() {
        set = new LinkedHashSet<>();
        fillSet(set, ITEMS_BEFORE);
        items = generateItemsForAdding(ITEMS_ADD);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        for (int i = 0; i < ITEMS_ADD; i++) {
            blackhole.consume(set.add(items[i]));
        }
    }
}
