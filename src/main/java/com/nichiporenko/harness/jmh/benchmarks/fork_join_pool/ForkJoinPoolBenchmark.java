package com.nichiporenko.harness.jmh.benchmarks.fork_join_pool;

import com.nichiporenko.harness.jmh.utils.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.rangeClosed;

/**
 * The benchmark tests the average execution time of 1 million checking operations
 * whether the number is prime.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 5)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 1, value = 1)
public class ForkJoinPoolBenchmark {
    private ForkJoinPool forkJoinPoolCustom = ForkJoinPool.commonPool();

    @Param(value = "1000000")
    private long upperBound;

    @Benchmark
    public void parallelStream(Blackhole blackhole) {
        List<Long> list = rangeClosed(1, upperBound).parallel().filter(Utils::isPrime).boxed().collect(toList());
        blackhole.consume(list);
    }

    @Benchmark
    public void sequentialStream(Blackhole blackhole) {
        List<Long> list = rangeClosed(1, upperBound).filter(Utils::isPrime).boxed().collect(toList());
        blackhole.consume(list);
    }

    @Benchmark
    public void forLoop(Blackhole blackhole) {
        List<Long> list = new ArrayList<>();
        for (long i = 1; i <= upperBound; i++) {
            if (Utils.isPrime(i)) {
                list.add(i);
            }
        }
        blackhole.consume(list);
    }

    @Benchmark
    public void forkJoinPoolWithParallelStream(Blackhole blackhole) throws ExecutionException, InterruptedException {
        ForkJoinTask<List<Long>> result = forkJoinPoolCustom.submit(() -> rangeClosed(1, upperBound).parallel().filter(Utils::isPrime).boxed().collect(toList()));
        blackhole.consume(result.get());
    }

    @Benchmark
    public void forkJoinPoolWithForLoop(Blackhole blackhole) throws ExecutionException, InterruptedException {
        ForkJoinTask<List<Long>> result = forkJoinPoolCustom.submit(() -> {
            List<Long> list = new ArrayList<>();
            for (long i = 1; i <= upperBound; i++) {
                if (Utils.isPrime(i)) {
                    list.add(i);
                }
            }
            return list;
        });
        blackhole.consume(result.get());
    }
}
