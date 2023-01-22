package com.nichiporenko.harness.jmh.benchmarks.streams;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * The benchmark tests the average execution time of stream's reduce method.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class StreamSumBenchmark {

    @Param(value = {"100000000"})
    private int numInts;

    private List<Integer> ints;

    @Setup(Level.Trial)
    public void setup() {
        ints = new ArrayList<>();
        for (int i = 1; i <= numInts; i++) {
            ints.add(i);
        }
    }

    @Benchmark
    public void reduce(Blackhole bh) {
        int sum = ints.stream().reduce(0, Integer::sum);
        bh.consume(sum);
    }

    @Benchmark
    public void reduceParallel(Blackhole bh) {
        int sum = ints.stream().parallel().reduce(0, Integer::sum);
        bh.consume(sum);
    }

    @Benchmark
    public void reduceIntStream(Blackhole bh) {
        int sum = ints.stream()
                .mapToInt(value -> value)
                .reduce(0, Integer::sum);
        bh.consume(sum);
    }

    @Benchmark
    public void reduceIntStreamParallel(Blackhole bh) {
        int sum = ints.stream()
                .parallel()
                .mapToInt(value -> value)
                .reduce(0, Integer::sum);
        bh.consume(sum);
    }

    @Benchmark
    public void reduceIntStreamParallelAfterMap(Blackhole bh) {
        int sum = ints.stream()
                .mapToInt(value -> value)
                .parallel()
                .reduce(0, Integer::sum);
        bh.consume(sum);
    }

    @Benchmark
    public void sumMapToInt(Blackhole bh) {
        int sum = ints.stream()
                .mapToInt(value -> value)
                .sum();
        bh.consume(sum);
    }

    @Benchmark
    public void manual(Blackhole bh) {
        int sum = 0;
        for (int i = 0; i < numInts; i++) {
            sum += ints.get(i);
        }
        bh.consume(sum);
    }
}
