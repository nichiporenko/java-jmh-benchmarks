package com.nichiporenko.harness.jmh.benchmarks.maps.linked_hash_map;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class LinkedHashMapContainsKeyBenchmark {
    private Map<String, String> map;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ENTRIES_BEFORE;

    @Setup
    public void prepare() {
        map = new LinkedHashMap<>();

        for (int i = 0; i < ENTRIES_BEFORE; i++) {
            if (i == ENTRIES_BEFORE - 1) {
                map.put("Dmitry", "found");
                continue;
            }
            map.put(generateRandomString(20), "0");
        }
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        blackhole.consume(map.containsKey("Dmitry"));
    }
}