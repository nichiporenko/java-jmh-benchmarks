package com.nichiporenko.harness.jmh.benchmarks.collections.maps.hash_map;

import com.nichiporenko.harness.jmh.benchmarks.collections.maps.BasicMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.MAPS_KEY;

/**
 * The benchmark tests the average execution time of <b>get</b> operation
 * for the {@link HashMap} with different initial number of entries.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class HashMapGetBenchmark implements BasicMap {
    private Map<String, String> map;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Setup(Level.Trial)
    public void setup() {
        map = new HashMap<>();
        fillMapWithLastSpecified(map, numEntriesPrefilled);
    }

    @Benchmark
    public void run(Blackhole blackhole) {
        blackhole.consume(map.get(MAPS_KEY));
    }
}
