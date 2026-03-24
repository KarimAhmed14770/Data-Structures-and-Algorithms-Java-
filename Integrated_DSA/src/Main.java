import DataStructuresAndAlgorithms.Arrays.Array;
import DataStructuresAndAlgorithms.HashTables.MyHashTable;
import DataStructuresAndAlgorithms.Sorting.*;


public class Main {
    public static void main(String[] Args){
        /*sorting techniques test*/
        Integer[] a={9,3,7,6,3,2,5,7,2,8,10,5,7,6,1};
        Array<Integer> arr=new Array<>(a);
        BucketSort sorter=new BucketSort();
        arr.display();
        sorter.sort(arr);
        arr.display();
    }

}
