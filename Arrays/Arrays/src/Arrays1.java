/*in this file we make a revision of array concepts in java*/

/* an array is a collection of elements of the same data type,stored contigously in memory
 * 1D array
 * array creation in java:
 * DataType[] arr_name=new Datatype[size];the brackets can be after datatype or variable name
 * but java style is putting the brackets after datatype
 * DataType[] arr_name={elements seperated by comma};
 * arr_name[] the square brackets must be empty, and the arr_name is a reference to the array object
 * arrays are always objects in java and always created in heap even if u don't use new keyword
 * an array in java has a property(attribute) called length, it is a number that carries the size of the array
 * arr_name.length return this number
 * conclusion:arr_name is a reference to the array object in the heap which has a property called length
 * array elements can be accessed through indexing by square brackets
 * the arr_reference is stored in stack and it refers to the array object that is stored in heap
 *
 * in java after using an array, we might set its reference to null, so that the garbage collector sees
 * that nothing is referring to this array object in heap and it deletes it automatically
 *
 * static array: array that its size is constant in the program
 * Dynamic array: array that its size might change
 *
 * how to increase size of an array?
 * once an array is created its size can't be changed, to change the size u must create a new array of the
 * desired size and then copy the elements of the old array to the new array and make the reference point to
 * the new array
 */



/*2D arrays
creating a 2d array in java
there are 3 ways of creating 2d arrays in java
1)predetermined size
DataType[][] arr_name=new Datatype[rows_size][cols_size];
2)array initializer:
Datatype[][] arr_name={{1,2,3},{4,5,6},{7,8,9}};
3)jagged array:
DataType[][] jagged=new DataType[3][];
jagged[0]=new DataType[2];
jagged[1]=new DataType[5];
jagged[2]=new DataType[3];

what happens in memory? arr_name (in stack) becomes a reference to an array object (size=rows) in heap that
contains references of array objects in heap,each reference in that array refers to an array object in heap
this happens in java
*/


import java.util.Scanner;

public class Arrays1 {

    public static void main(String[] Args) {
        int size=0;
        Scanner sc=new Scanner(System.in);
        int[] arr = new int[5];//this is static array, its size is 5 and it will always be 5
        System.out.println("Enter array size");
        try{size=sc.nextInt();}catch(Exception e){System.out.println(e);}
        int[] dynArr=new int[size];//this is a dynamic array because its size may change depending on input
        for(int i=0;i< dynArr.length;i++){
            System.out.println("Enter element("+i+"): ");
            try{dynArr[i]=sc.nextInt();}catch(Exception e){System.out.println(e);}
        }
        /*now i will check if the array size is less than 20, i want to increase this size to 20*/
        if(dynArr.length<20){//increasing its size to 20
            int[] tempArr=new int[20];
            for(int i=0;i< dynArr.length;i++){
                tempArr[i]=dynArr[i];
            }
            dynArr=tempArr;//making the reference point to the new array
            tempArr=null;//making the temparr point to nothing
            System.out.println(dynArr.length);

        }



    }


}

