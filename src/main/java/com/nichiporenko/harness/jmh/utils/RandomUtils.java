package com.nichiporenko.harness.jmh.utils;

import java.security.SecureRandom;

public class RandomUtils {
    private static final String RANDOM_LETTERS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String RANDOM_LETTERS_UPPER = RANDOM_LETTERS_LOWER.toUpperCase();
    private static final String RANDOM_DIGITS = "0123456789";
    private static final String RANDOM_SYMBOLS = "+-*/=\\";
    private static final String RANDOM_CHARACTERS = RANDOM_LETTERS_LOWER + RANDOM_LETTERS_UPPER + RANDOM_DIGITS + RANDOM_SYMBOLS;
    private static final SecureRandom random = new SecureRandom();

    private RandomUtils() { }

    public static String generateRandomString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomCharPos = random.nextInt(RANDOM_CHARACTERS.length());
            char randomChar = RANDOM_CHARACTERS.charAt(randomCharPos);

            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String[] generateRandomIpArray(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }

        String[] ipArray = new String[size];
        for (int i = 0; i < size; i++) {
            String firstPart = String.valueOf(random.nextInt(256));
            String secondPart = String.valueOf(random.nextInt(256));
            String thirdPart = String.valueOf(random.nextInt(256));
            String fourthPart = String.valueOf(random.nextInt(256));

            ipArray[i] = firstPart + "." + secondPart + "." + thirdPart + "." + fourthPart;
        }
        return ipArray;
    }
}
