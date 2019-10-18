package com.nichiporenko.harness.jmh.benchmarks.collections.maps.hash_map;

import com.nichiporenko.harness.jmh.benchmarks.collections.maps.BasicMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

/**
 * The benchmark tests the average execution time of 100,000 <b>put</b> operations
 * for the {@link HashMap} with different initial number of entries.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class HashMapPutBenchmark implements BasicMap {
    private Map<String, String> map;
    private String[] keys;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ENTRIES_BEFORE;

    @Param(value = {"100000"})
    private int ENTRIES_PUT;

    @Setup(Level.Invocation)
    public void preparePut() {
        map = new HashMap<>();
        fillMap(map, ENTRIES_BEFORE);
        keys = generateKeysForPutting(ENTRIES_PUT);
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        for (int i = 0; i < ENTRIES_PUT; i++) {
            blackhole.consume(map.put(keys[i], COLLECTIONS_VALUE));
        }
    }
}
