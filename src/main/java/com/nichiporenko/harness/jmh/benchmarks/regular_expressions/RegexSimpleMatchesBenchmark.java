package com.nichiporenko.harness.jmh.benchmarks.regular_expressions;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomIpArray;

/**
 * The benchmark tests the average execution time of 100,000 <b>matches</b> operations for a
 * relatively simple pattern with non-capturing, capturing and named-capturing groups.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class RegexSimpleMatchesBenchmark {
    private static final Pattern PATTERN_NON_CAPTURING = Pattern.compile(
            "(?:\\d{1,3}).(?:\\d{1,3}).(?:\\d{1,3}).(?:\\d{1,3})"
    );
    private static final Pattern PATTERN_CAPTURING = Pattern.compile(
            "(\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3})"
    );
    private static final Pattern PATTERN_NAMED_CAPTURING = Pattern.compile(
            "(?<name1>\\d{1,3}).(?<name2>\\d{1,3}).(?<name3>\\d{1,3}).(?<name4>\\d{1,3})"
    );
    private String[] strings;

    @Param(value = {"100000"})
    private int PATTERN_CHECKS;

    @Setup(Level.Trial)
    public void test() {
        strings = generateRandomIpArray(PATTERN_CHECKS);
    }

    @Benchmark
    public void simpleNonCapturing(final Blackhole blackhole) {
        for (String ip : strings) {
            blackhole.consume(PATTERN_NON_CAPTURING.matcher(ip).matches());
        }
    }

    @Benchmark
    public void simpleCapturing(final Blackhole blackhole) {
        for (String ip : strings) {
            blackhole.consume(PATTERN_CAPTURING.matcher(ip).matches());
        }
    }

    @Benchmark
    public void simpleNamedCapturing(final Blackhole blackhole) {
        for (String ip : strings) {
            blackhole.consume(PATTERN_NAMED_CAPTURING.matcher(ip).matches());
        }
    }
}
