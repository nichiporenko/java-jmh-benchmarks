package com.nichiporenko.harness.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class HashMapBenchmark {
    private Map<String, String> map;

    @Param(value = {"1", "1000", "100000", "1000000"})
    private int MAP_ENTRIES_COUNT;

    @Setup
    public void prepare() {
        map = new HashMap<>();
        for (int i = 0; i < MAP_ENTRIES_COUNT - 1; i++) {
            map.put(generateRandomString(20), "0");
        }
        map.put("Dmitry", "found");
    }

    @Benchmark
    public void normal(Blackhole blackhole) {
        blackhole.consume(map.get("Dmitry"));
    }

    @Benchmark
    public void eliminated() {
        map.get("Dmitry");
    }
}
