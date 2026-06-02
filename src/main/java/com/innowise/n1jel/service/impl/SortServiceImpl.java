package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.IntCustomArray;
import com.innowise.n1jel.service.SortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortServiceImpl implements SortService {

    private static final Logger log = LogManager.getLogger(SortServiceImpl.class);

    @Override
    public void bubbleSort(IntCustomArray intCustomArray) {
        if (intCustomArray == null || intCustomArray.isEmpty()) {
            log.debug("Array is null or empty, nothing to sort");
            return;
        }

        int[] array = intCustomArray.getArray();
        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        // Update the internal array
        for (int i = 0; i < length; i++) {
            intCustomArray.setElement(i, array[i]);
        }

        log.debug("Bubble sort completed");
    }

    @Override
    public void quickSort(IntCustomArray intCustomArray) {
        if (intCustomArray == null || intCustomArray.isEmpty()) {
            log.debug("Array is null or empty, nothing to sort");
            return;
        }

        int[] array = intCustomArray.getArray();
        quickSortRecursive(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            intCustomArray.setElement(i, array[i]);
        }

        log.debug("Quick sort completed");
    }

    private void quickSortRecursive(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            quickSortRecursive(array, low, partitionIndex - 1);
            quickSortRecursive(array, partitionIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
