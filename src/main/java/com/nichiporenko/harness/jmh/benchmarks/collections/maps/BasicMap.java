package com.nichiporenko.harness.jmh.benchmarks.collections.maps;

import java.util.Map;

import static com.nichiporenko.harness.jmh.utils.Constants.*;
import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

/**
 * The interface with common methods for benchmarking of {@link Map} implementations.
 *
 * @author Dmitry Nichiporenko
 */
public interface BasicMap {

    default void fillMapWithLastSpecified(Map<String, String> map, int numberOfEntries) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (i == numberOfEntries - 1) {
                map.put(MAPS_KEY, COLLECTIONS_VALUE);
                continue;
            }
            map.put(generateRandomString(MAPS_KEY_LENGTH), COLLECTIONS_VALUE);
        }
    }

    default void fillMap(Map<String, String> map, int numberOfEntries) {
        for (int i = 0; i < numberOfEntries; i++) {
            map.put(generateRandomString(MAPS_KEY_LENGTH), COLLECTIONS_VALUE);
        }
    }

    default String[] generateKeysForPutting(int numberOfKeys) {
        String[] keys = new String[numberOfKeys];

        for (int i = 0; i < numberOfKeys; i++) {
            keys[i] = generateRandomString(MAPS_KEY_LENGTH);
        }
        return keys;
    }
}
