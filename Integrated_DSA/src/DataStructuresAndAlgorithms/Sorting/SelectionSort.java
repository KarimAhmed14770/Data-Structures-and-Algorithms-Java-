package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;

public class SelectionSort<T extends Comparable<T>> implements Sort<T>  {
    @Override
    public void sort(Array<T> arr){
    int i,j,k,cmp;
        for(i=0;i<arr.getCount();i++){
            for(j=i,k=i;j<arr.getCount()-1;j++){
                cmp=arr.getElement(j+1).compareTo(arr.getElement(k));
                if(cmp<0){
                    k=j+1;
                }
            }
            arr.swap(i,k);

        }
    }

}
