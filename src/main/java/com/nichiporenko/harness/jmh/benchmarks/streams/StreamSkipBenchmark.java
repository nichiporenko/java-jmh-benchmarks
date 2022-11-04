package com.nichiporenko.harness.jmh.benchmarks.streams;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * The benchmark tests the average execution time of stream's skip method of 99_000_000
 * elements on sequential and parallel streams with 100_000_000 elements.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class StreamSkipBenchmark {

    @Benchmark
    public void sequentialSkip(Blackhole blackhole) {
        blackhole.consume(IntStream.range(0, 100_000_000)
                .skip(99_000_000)
                .sum());
    }

    @Benchmark
    public void parallelSkip(Blackhole blackhole) {
        blackhole.consume(IntStream.range(0, 100_000_000)
                .parallel()
                .skip(99_000_000)
                .sum());
    }

}
