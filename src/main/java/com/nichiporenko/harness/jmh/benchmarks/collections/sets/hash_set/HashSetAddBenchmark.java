package com.nichiporenko.harness.jmh.benchmarks.collections.sets.hash_set;

import com.nichiporenko.harness.jmh.benchmarks.collections.sets.BasicSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of 100,000 <b>add</b> operations
 * for the {@link HashSet} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class HashSetAddBenchmark implements BasicSet {
    private Set<String> set;
    private String[] items;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Param(value = {"100000"})
    private int numEntriesToAdd;

    @Setup(Level.Invocation)
    public void preparePut() {
        set = new HashSet<>();
        fillSet(set, numEntriesPrefilled);
        items = generateItemsForAdding(numEntriesToAdd);
    }

    @Benchmark
    public void run(Blackhole bh) {
        for (int i = 0; i < numEntriesToAdd; i++) {
            bh.consume(set.add(items[i]));
        }
    }
}
