package com.nichiporenko.harness.jmh.benchmarks.maps.hashtable;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.MAPS_KEY_LENGTH;
import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class HashtablePutBenchmark {
    private Map<String, String> map = new Hashtable<>();
    private String[] keys;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int ENTRIES_BEFORE;

    @Param(value = {"100000"})
    private int ENTRIES_TO_PUT;

    @Setup(Level.Invocation)
    public void preparePut() {
        map.clear();

        for (int i = 0; i < ENTRIES_BEFORE; i++) {
            map.put(generateRandomString(MAPS_KEY_LENGTH), "0");
        }

        keys = new String[ENTRIES_TO_PUT];

        for (int i = 0; i < ENTRIES_TO_PUT; i++) {
            keys[i] = generateRandomString(MAPS_KEY_LENGTH);
        }
    }

    @Benchmark
    public void normal(final Blackhole blackhole) {
        for (int i = 0; i < ENTRIES_TO_PUT; i++) {
            blackhole.consume(map.put(keys[i], "value"));
        }
    }
}
