package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;

public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(Array<T> arr){
        boolean flag=false;
        int cmp;
        T temp;
        for(int i=0;i<arr.getCount()-1;i++){
            flag=false;
            for(int j=0;j<arr.getCount()-i-1;j++){
                cmp=arr.getElement(j).compareTo(arr.getElement(j+1));
                if(cmp>0){
                    arr.swap(j,j+1);
                    flag=true;

                }
            }
            if(!flag){
                break;
            }
        }
    }

}
