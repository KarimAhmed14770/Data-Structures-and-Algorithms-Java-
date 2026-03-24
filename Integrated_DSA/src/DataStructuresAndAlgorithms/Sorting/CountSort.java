package DataStructuresAndAlgorithms.Sorting;

import DataStructuresAndAlgorithms.Arrays.Array;

public class CountSort{
    public void sort(Array<Integer> arr){
        int max=(int)arr.max();
        Array<Integer> count=new Array<>(max+1);
        for(int i=0;i<max+1;i++){
            count.append(0);
        }
        for(int i=0;i<arr.getCount();i++){
            count.setElement(arr.getElement(i),count.getElement(arr.getElement(i))+1);
        }

        int index=0;
            for(int j=0;j<count.getCount();j++){
                while(count.getElement(j)>0){
                    arr.setElement(index,j);
                    index++;
                    count.setElement(j,count.getElement(j)-1);
                }
            }


    }
}
