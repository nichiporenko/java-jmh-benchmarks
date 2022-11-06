package com.nichiporenko.harness.jmh.benchmarks.strings;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of prepending the string
 * to non-empty StringBuilder instance in different ways.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class StringBuilderPrependBenchmark {

    @Param(value = "INSERTED_PART_INSERTED_PART_INSERTED_PART|")
    private String stringToPrepend;
    private StringBuilder stringBuilder;

    @Setup
    public void setup() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("EXAMPLE_OF_STRING_EXAMPLE_OF_STRING_EXAMPLE_OF_STRING_EXAMPLE_OF_STRING_EXAMPLE_OF_STRING");
    }

    @Benchmark
    public void insert(Blackhole bh) {
        bh.consume(stringBuilder.insert(0, stringToPrepend));
    }

    @Benchmark
    public void reverse(Blackhole bh) {
        bh.consume(stringBuilder.reverse().append(new StringBuilder(stringToPrepend).reverse()).reverse());
    }
}
