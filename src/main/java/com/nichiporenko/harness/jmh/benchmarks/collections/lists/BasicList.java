package com.nichiporenko.harness.jmh.benchmarks.collections.lists;

import java.util.List;

import static com.nichiporenko.harness.jmh.utils.Constants.COLLECTIONS_VALUE;
import static com.nichiporenko.harness.jmh.utils.Constants.LISTS_ITEM_LENGTH;
import static com.nichiporenko.harness.jmh.utils.RandomUtils.generateRandomString;

/**
 * The interface with common methods for benchmarking of {@link List} implementations.
 *
 * @author Dmitry Nichiporenko
 */
public interface BasicList {

    default void fillListWithLastSpecified(List<String> list, int numOfEntries) {
        for (int i = 0; i < numOfEntries; i++) {
            if (i == numOfEntries - 1) {
                list.add(COLLECTIONS_VALUE);
                continue;
            }
            list.add(generateRandomString(LISTS_ITEM_LENGTH));
        }
    }

    default void fillStringsList(List<String> list, int numOfEntries) {
        for (int i = 0; i < numOfEntries; i++) {
            list.add(generateRandomString(LISTS_ITEM_LENGTH));
        }
    }

    default String[] generateStringsToAdd(int numOfEntries) {
        String[] entries = new String[numOfEntries];

        for (int i = 0; i < numOfEntries; i++) {
            entries[i] = generateRandomString(LISTS_ITEM_LENGTH);
        }
        return entries;
    }
}
