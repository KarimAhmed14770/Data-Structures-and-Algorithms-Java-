package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;
import DataStructuresAndAlgorithms.LinkedLists.LinkedList;

public class BucketSort {
    public void sort(Array<Integer> arr){
        int max=(int)arr.max();
        Array<LinkedList<Integer>> count=new Array<>(max+1);
        for (int i=0;i<max;i++){
            //count.getElement(i)=new LinkedList<>();
        }
        for(int i=0;i<arr.getCount();i++){
            count.getElement(arr.getElement(i)).append(arr.getElement(i));
        }

        int index=0;
        for(int j=0;j<count.getCount();j++){

        }


    }
}
