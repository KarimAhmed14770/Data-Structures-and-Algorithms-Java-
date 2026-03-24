package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(Array<T> arr) {
        recSort(arr, 0, arr.getCount() - 1);
    }

    private void recSort(Array<T> arr, int low, int high) {
        if (low < high) {
            // 1. Partition the array and get the pivot index
            int pivotIndex = partition(arr, low, high);

            // 2. Recursively sort elements before and after partition
            recSort(arr, low, pivotIndex - 1);
            recSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(Array<T> arr, int low, int high) {
        T pivot = arr.getElement(low); // Taking the first element of the CURRENT sub-array
        int i = low;
        int j = high;

        while (i < j) {
            // Move i forward while element <= pivot
            while (i <= high && arr.getElement(i).compareTo(pivot) <= 0) {
                i++;
            }
            // Move j backward while element > pivot
            while (j >= low && arr.getElement(j).compareTo(pivot) > 0) {
                j--;
            }

            if (i < j) {
                arr.swap(i, j);
            }
        }
        // Place pivot in its correct sorted position
        arr.swap(low, j);
        return j; // Return the pivot's final position
    }
}

