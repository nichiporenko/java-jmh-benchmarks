package com.nichiporenko.harness.jmh.benchmarks.collections.lists.linked_list;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

/**
 * The benchmark tests the average execution time of <b>contains</b> operation
 * for the {@link LinkedList} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class LinkedListContainsBenchmark implements BasicList {
    private List<String> list;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Setup
    public void prepare() {
        list = new LinkedList<>();
        fillListWithLastSpecified(list, numEntriesPrefilled);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(list.contains(COLLECTIONS_VALUE));
    }
}
