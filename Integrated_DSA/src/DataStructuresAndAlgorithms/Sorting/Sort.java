package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;

public interface Sort<T extends Comparable<T>> {
    abstract public void sort(Array<T> arr);
}
