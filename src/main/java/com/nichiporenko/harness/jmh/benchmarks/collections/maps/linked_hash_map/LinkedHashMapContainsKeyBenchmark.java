package com.nichiporenko.harness.jmh.benchmarks.collections.maps.linked_hash_map;

import com.nichiporenko.harness.jmh.benchmarks.collections.maps.BasicMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.MAPS_KEY;

/**
 * The benchmark tests the average execution time of <b>containsKey</b> operation
 * for the {@link LinkedHashMap} with different initial number of entries.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class LinkedHashMapContainsKeyBenchmark implements BasicMap {
    private Map<String, String> map;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Setup(Level.Trial)
    public void setup() {
        map = new LinkedHashMap<>();
        fillMapWithLastSpecified(map, numEntriesPrefilled);
    }

    @Benchmark
    public void run(Blackhole blackhole) {
        blackhole.consume(map.containsKey(MAPS_KEY));
    }
}
