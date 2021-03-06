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

    default void fillListWithLastSpecified(List<String> list, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            if (i == numberOfItems - 1) {
                list.add(COLLECTIONS_VALUE);
                continue;
            }
            list.add(generateRandomString(LISTS_ITEM_LENGTH));
        }
    }

    default void fillList(List<String> list, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            list.add(generateRandomString(LISTS_ITEM_LENGTH));
        }
    }

    default String[] generateItemsForAdding(int numberOfItems) {
        String[] items = new String[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            items[i] = generateRandomString(LISTS_ITEM_LENGTH);
        }
        return items;
    }
}
