package com.nichiporenko.harness.jmh.benchmarks.maps.concurrent_skip_list_map;

import com.nichiporenko.harness.jmh.benchmarks.maps.BasicMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;
import static com.nichiporenko.harness.jmh.utils.Constants.MAPS_KEY_LENGTH;
import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class ConcurrentSkipListMapPutBenchmark implements BasicMap {
    private Map<String, String> map;
    private String[] keys;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ENTRIES_BEFORE;

    @Param(value = {"100000"})
    private int ENTRIES_PUT;

    @Setup(Level.Invocation)
    public void preparePut() {
        map = new ConcurrentSkipListMap<>();
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