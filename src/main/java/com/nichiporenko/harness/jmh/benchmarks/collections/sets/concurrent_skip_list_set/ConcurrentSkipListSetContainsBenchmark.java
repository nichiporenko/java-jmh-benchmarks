package com.nichiporenko.harness.jmh.benchmarks.collections.sets.concurrent_skip_list_set;

import com.nichiporenko.harness.jmh.benchmarks.collections.sets.BasicSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

/**
 * The benchmark tests the average execution time of <b>contains</b> operation
 * for the {@link ConcurrentSkipListSet} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class ConcurrentSkipListSetContainsBenchmark implements BasicSet {
    private Set<String> set;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Setup
    public void prepare() {
        set = new ConcurrentSkipListSet<>();
        fillSetWithLastSpecified(set, ITEMS_BEFORE);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(set.contains(COLLECTIONS_VALUE));
    }
}
