package com.nichiporenko.harness.jmh.benchmarks.collections.lists.array_list;

import com.nichiporenko.harness.jmh.benchmarks.collections.lists.BasicList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of <b>remove</b> operation
 * from the middle for the {@link ArrayList} with different initial number of items.
 *
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class ArrayListRemoveFromMiddleBenchmark implements BasicList {
    private List<String> list;

    @Param(value = {"1", "1000", "100000"})
    private int numEntriesPrefilled;

    @Setup(Level.Invocation)
    public void setup() {
        list = new ArrayList<>();
        fillListWithLastSpecified(list, numEntriesPrefilled);
    }

    @Benchmark
    public void run(Blackhole bh) {
        bh.consume(list.remove(numEntriesPrefilled / 2));
    }
}
