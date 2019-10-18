package com.nichiporenko.harness.jmh.benchmarks.collections.sets;

import java.util.Set;

import static com.nichiporenko.harness.jmh.utils.Constants.*;
import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

/**
 * The interface with common methods for benchmarking of {@link Set} implementations.
 *
 * @author Dmitry Nichiporenko
 */
public interface BasicSet {

    default void fillSetWithLastSpecified(Set<String> set, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            if (i == numberOfItems - 1) {
                set.add(COLLECTIONS_VALUE);
                continue;
            }
            set.add(generateRandomString(SETS_ITEM_LENGTH));
        }
    }

    default void fillSet(Set<String> set, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            set.add(generateRandomString(SETS_ITEM_LENGTH));
        }
    }

    default String[] generateItemsForAdding(int numberOfItems) {
        String[] items = new String[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            items[i] = generateRandomString(SETS_ITEM_LENGTH);
        }
        return items;
    }
}
