package com.nichiporenko.harness.jmh.benchmarks.sets.hash_set;

import com.nichiporenko.harness.jmh.benchmarks.sets.BasicSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class HashSetContainsBenchmark implements BasicSet {
    private Set<String> set;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Setup
    public void prepare() {
        set = new HashSet<>();
        fillSetWithLastSpecified(set, ITEMS_BEFORE);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(set.contains(COLLECTIONS_VALUE));
    }
}
