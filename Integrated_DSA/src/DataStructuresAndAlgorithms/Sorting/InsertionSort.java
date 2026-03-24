package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(Array<T> arr) {
        for(int i=1;i<arr.getCount();i++){
            T x=arr.getElement(i);
            int j=i-1;
            while(j>-1 && (arr.getElement(j).compareTo(x)>0))
            {
                arr.setElement(j+1, arr.getElement(j));
                j--;
            }
            arr.setElement(j+1,x);
        }
    }

}
