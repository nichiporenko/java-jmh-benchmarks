package com.nichiporenko.harness.jmh.benchmarks.arrays;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of copying an array.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class ArrayCopyBenchmark {
    private static final Random RANDOM = new Random();
    private int[] array;
    private int[] arrayCopy;

    @Param(value = {"10000000"})
    private int numEntriesPrefilled;

    @Setup(Level.Invocation)
    public void setup() {
        array = new int[numEntriesPrefilled];
        for (int i = 0; i < numEntriesPrefilled; i++) {
            array[i] = RANDOM.nextInt(Integer.MAX_VALUE - 128) + 128;
        }
        arrayCopy = new int[numEntriesPrefilled];
    }

    @Benchmark
    public void systemCopy(Blackhole bh) {
        System.arraycopy(array, 0, arrayCopy, 0, numEntriesPrefilled);
        bh.consume(arrayCopy);
    }

    @Benchmark
    public void iteration(Blackhole bh) {
        for (int i = 0; i < numEntriesPrefilled; i++) {
            arrayCopy[i] = array[i];
        }
        bh.consume(arrayCopy);
    }
}
