package com.nichiporenko.harness.jmh.benchmarks.collections.maps.tree_map;

import com.nichiporenko.harness.jmh.benchmarks.collections.maps.BasicMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.MAPS_KEY;

/**
 * The benchmark tests the average execution time of <b>get</b> operation
 * for the {@link TreeMap} with different initial number of entries.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class TreeMapGetBenchmark implements BasicMap {
    private Map<String, String> map;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ENTRIES_BEFORE;

    @Setup
    public void prepare() {
        map = new TreeMap<>();
        fillMapWithLastSpecified(map, ENTRIES_BEFORE);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(map.get(MAPS_KEY));
    }
}
