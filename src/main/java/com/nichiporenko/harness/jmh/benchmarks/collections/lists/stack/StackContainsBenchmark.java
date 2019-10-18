package com.nichiporenko.harness.jmh.benchmarks.collections.lists.stack;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

/**
 * The benchmark tests the average execution time of <b>contains</b> operation
 * for the {@link Stack} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class StackContainsBenchmark implements BasicList {
    private List<String> list;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Setup
    public void prepare() {
        list = new Stack<>();
        fillListWithLastSpecified(list, ITEMS_BEFORE);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(list.contains(COLLECTIONS_VALUE));
    }
}
