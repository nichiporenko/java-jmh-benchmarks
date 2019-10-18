package com.nichiporenko.harness.jmh.benchmarks.collections.lists.copy_on_write_array_list;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of <b>get</b> operation
 * for the {@link CopyOnWriteArrayList} with different initial number of items.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class CopyOnWriteArrayListGetBenchmark implements BasicList {
    private List<String> list;

    @Param(value = {"1", "1000", "100000", "1000000"})
    private int ITEMS_BEFORE;

    @Setup
    public void prepare() {
        list = new CopyOnWriteArrayList<>();
        fillListWithLastSpecified(list, ITEMS_BEFORE);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(list.get(ITEMS_BEFORE - 1));
    }
}
