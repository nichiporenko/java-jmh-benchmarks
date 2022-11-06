package com.nichiporenko.harness.jmh.benchmarks.collections.maps.hashtable;

import com.nichiporenko.harness.jmh.benchmarks.collections.maps.BasicMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;

/**
 * The benchmark tests the average execution time of 100,000 <b>put</b> operations
 * for the {@link Hashtable} with different initial number of entries.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class HashtablePutBenchmark implements BasicMap {
    private Map<String, String> map;
    private String[] keys;

    @Param(value = {"0", "1", "1000", "100000", "1000000"})
    private int numEntriesPrefilled;

    @Param(value = {"100000"})
    private int entriesToAdd;

    @Setup(Level.Invocation)
    public void preparePut() {
        map = new Hashtable<>();
        fillMap(map, numEntriesPrefilled);
        keys = generateKeysForPutting(entriesToAdd);
    }

    @Benchmark
    public void run(Blackhole bh) {
        for (int i = 0; i < entriesToAdd; i++) {
            bh.consume(map.put(keys[i], COLLECTIONS_VALUE));
        }
    }
}
