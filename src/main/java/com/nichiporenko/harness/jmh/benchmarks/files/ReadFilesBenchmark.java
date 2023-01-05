package com.nichiporenko.harness.jmh.benchmarks.files;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The benchmark tests the average execution time of reading a file.
 *
 * @author Dmitry Nichiporenko
 */
@State(value = Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 1, iterations = 3)
@Measurement(time = 1, iterations = 5)
@Fork(warmups = 0, value = 1)
public class ReadFilesBenchmark {
    private static ExecutorService executor;
    private static final List<String> PATHS = new ArrayList<>();

    @Param(value = {"1"})
    private int numFiles;

    @Setup(Level.Invocation)
    public void setupExecution() {
        executor = Executors.newSingleThreadExecutor();
    }

    @Setup(Level.Trial)
    public void setup() throws IOException {
        File file;

        for (int i = 1; i <= numFiles; i++) {
            String path = "files/" + i + ".txt";
            file = new File(path);
            PATHS.add(path);

            try (FileWriter fileWriter = new FileWriter(file)) {
                // 1 mb per iteration
                int mb = 200;
                for (int j = 0; j < mb; j++) {
                    for (int k = 0; k < 1024; k++) {
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        if (j == mb - 1 && k == 1023) {
                            fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234abc\n");
                        } else {
                            fileWriter.write("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\n");
                        }
                    }
                }
            }
        }
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        File file;

        for (int i = 0; i < numFiles; i++) {
            file = new File(PATHS.get(i));
            file.delete();
        }
    }

    @Benchmark
    public void bufferedReader(Blackhole bh) throws InterruptedException {
        for (int i = 0; i < numFiles; i++) {
            executor.submit(new BufferedReaderThread(PATHS.get(i), bh));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

    @Benchmark
    public void scanner(Blackhole bh) throws InterruptedException {
        for (int i = 0; i < numFiles; i++) {
            executor.submit(new ScannerThread(PATHS.get(i), bh));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

    private static class BufferedReaderThread implements Runnable {
        private final String path;
        private final Blackhole bh;

        public BufferedReaderThread(String path, Blackhole bh) {
            this.path = path;
            this.bh = bh;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
                boolean isFound = false;
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("abc")) {
                        isFound = true;
                    }
                }
                bh.consume(isFound);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class ScannerThread implements Runnable {
        private final String path;
        private final Blackhole bh;

        public ScannerThread(String path, Blackhole bh) {
            this.path = path;
            this.bh = bh;
        }

        @Override
        public void run() {
            try (Scanner scanner = new Scanner(new File(path))) {
                boolean isFound = false;
                String line;

                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.contains("abc")) {
                        System.out.println("found in scanner");
                        isFound = true;
                    }
                }
                bh.consume(isFound);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
