package com.nichiporenko.harness.jmh.benchmarks.collections.sets.tree_set;

import com.nichiporenko.harness.jmh.benchmarks.collections.sets.BasicSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

/**
 * The benchmark tests the average execution time of <b>contains</b> operation
 * for the {@link TreeSet} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class TreeSetContainsBenchmark implements BasicSet {
    private Set<String> set;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Setup
    public void prepare() {
        set = new TreeSet<>();
        fillSetWithLastSpecified(set, ITEMS_BEFORE);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(set.contains(COLLECTIONS_VALUE));
    }
}
