package com.nichiporenko.harness.jmh.utils;

import static java.lang.Math.sqrt;
import static java.util.stream.LongStream.rangeClosed;

public final class Utils {

    private Utils() { }

    public static boolean isPrime(long n) {
        return n > 1 && rangeClosed(2, (long) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }

}
