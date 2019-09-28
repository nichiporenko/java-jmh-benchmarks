package com.nichiporenko.harness.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class HashMapBenchmark {
    private Map<String, String> map;

    @Setup
    public void prepare() {
        map = new HashMap<>();
        map.put("Dmitry", "Best of the best");
    }

    @Benchmark
    public String normal() {
        return map.get("Dmitry");
    }

    @Benchmark
    public void eliminated() {
        map.get("Dmitry");
    }

}
