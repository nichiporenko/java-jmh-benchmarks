package com.nichiporenko.harness.jmh.benchmarks.sets.copy_on_write_array_set;

import com.nichiporenko.harness.jmh.benchmarks.sets.BasicSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class CopyOnWriteArraySetAddBenchmark implements BasicSet {
    private Set<String> set;
    private String[] items;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Param(value = {"100000"})
    private int ITEMS_ADD;

    @Setup(Level.Invocation)
    public void preparePut() {
        set = new CopyOnWriteArraySet<>();
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
