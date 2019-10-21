package com.nichiporenko.harness.jmh.regular_expressions;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomIpArray;

/**
 * The benchmark tests the average execution time of 100,000 <b>matches</b> operations for a
 * relatively complex pattern with non-capturing, capturing and named-capturing groups.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class RegexComplexBenchmark {
    private static final Pattern PATTERN_NON_CAPTURING = Pattern.compile(
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
    );
    private static final Pattern PATTERN_CAPTURING = Pattern.compile(
            "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
    );
    private static final Pattern PATTERN_NAMED_CAPTURING = Pattern.compile(
            "(?<g1>(?<g2>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?<g3>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
    );
    private String[] ipAddresses;

    @Param(value = {"100000"})
    private int PATTERN_CHECKS;

    @Setup(Level.Trial)
    public void test() {
        ipAddresses = generateRandomIpArray(PATTERN_CHECKS);
    }

    @Benchmark
    public void complexNonCapturing(final Blackhole blackhole) {
        for (String ip : ipAddresses) {
            blackhole.consume(PATTERN_NON_CAPTURING.matcher(ip).matches());
        }
    }

    @Benchmark
    public void complexCapturing(final Blackhole blackhole) {
        for (String ip : ipAddresses) {
            blackhole.consume(PATTERN_CAPTURING.matcher(ip).matches());
        }
    }

    @Benchmark
    public void complexNamedCapturing(final Blackhole blackhole) {
        for (String ip : ipAddresses) {
            blackhole.consume(PATTERN_NAMED_CAPTURING.matcher(ip).matches());
        }
    }
}
