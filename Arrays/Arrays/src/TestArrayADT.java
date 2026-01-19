

class Array{
    int[] arr;//reference to the array object,it will refer to the object and its length
    int count;//the number of elements actually in the array

    /*
    for initializing an empty array with a given capacity
    * */
    Array(int length){
        arr=new int[length];//creation if the array with the desired length
        count=0;
    }
    /*for creating an array object from an existing array*/
    Array(int []arr){
        this.arr=arr;
        count=arr.length;
    }
    /*for creating an array object from an existing array but with bigger size*/
    Array(int[] arr,int length){
        this.arr=new int[length];
        for(int i=0;i<arr.length;i++){
            this.arr[i]=arr[i];
        }
        count=arr.length;
    }


    /*private methods*/
    private void resize(int newSize){
        int[] temparr=new int[newSize];
        for(int i=0;i<count;i++){
            temparr[i]=this.arr[i];
        }
        this.arr=temparr;
        temparr=null;
    }

    private void rightShift(int startingIndex){
        for(int i=count-1;i>startingIndex;i--){
            arr[i]=arr[i-1];
        }
        arr[startingIndex]=0;
    }

    /*public methods*/
   /*time complexity O(n)*/
    public void display(){
        System.out.println("Array count= "+count);
        System.out.println("Array capacity= "+arr.length);
        System.out.println("Array elements:");
        for(int i=0;i<count;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.println();
    }

    /*time complexity O(1) except when achieving the resizing logic its o(N)*/
    public void append(int x){
        if(count==this.arr.length){
            resize(2*count);
        }
        this.arr[count]=x;
        count++;
    }

    /*worst case time complexity is O(n),best case is O(1)*/
    public void insert(int index,int value) throws ArrayADTException{
        if(count==this.arr.length){
            resize(2*count);
        }
        if(index>count){
            throw new ArrayADTException("can't insert at a gap from last element");
        }
        if(index<0){
            throw new ArrayADTException("index can't be negative");
        }
        for(int i=count;i>index;i--){
            this.arr[i]=arr[i-1];
        }
            this.arr[index] = value;
            count++;
        }

    /*worst case time complexity is O(n),best case is O(1)*/
    public void delete(int index) throws ArrayADTException{
        if(index <0){
            throw new ArrayADTException("negative index not allowed");
        }
        if(count==0){
            throw new ArrayADTException("Can't delete from an empty array");
        }
        if(index >=count){
            throw new ArrayADTException("can't delete non existing element");
        }
        for(int i=index;i<count-1;i++){
            this.arr[i]=this.arr[i+1];
        }
        this.count--;
        if(count<= (arr.length/4)){
            resize(arr.length/2);
            /*why not divided 2?
            *While this works, it can lead to something called "Thrashing." Imagine your capacity is 10
            * and count is 5. You delete one element, it shrinks to capacity 5. Then you immediately append
            * one element, it doubles back to 10. If you keep adding and removing that one element,
            * the computer spends all its time copying arrays!
            * The Professional Solution: Only shrink when the array is 1/4 full, and shrink it to 1/2 size.
            * This gives the array "breathing room" so it doesn't have to resize again immediately.*/
        }
    }

    /*time complexity:best case O(1) worst case O(n), average case (1+2+3+...+n)/n which means O(n)*/
    public int linearSearch(int key) {
        int searchResult=-1;
        for(int i=0;i<count;i++){
            if(key==arr[i]){
                searchResult=i;
                break;
            }
        }
        return searchResult;
    }

    /*time complexity: worst and average :O(log n),best case O(1),space complexityO(1)*/
    public int binarySearchIter(int low,int high,int key){
        int mid=0;
        int index=-1;
        while(low <= high){
            mid=(low+high)/2;
            if(this.arr[mid]==key)
            {
                index=mid;
                break;
            }
            else if (key>this.arr[mid])
            {
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }
        }
        return index;
    }

    /*time complexity: worst and average :O(log n),best case O(1),
    space complexityO(log n) because each recursive call adds a stack frame in the memory*/
    public int binarySearchRecur(int low,int high,int key){
        int mid=0;
        int index=-1;
        if(low<=high){
            mid=(low+high)/2;
            if(arr[mid]==key){
                index=mid;
            }
            else if(arr[mid]<key){
                index=binarySearchRecur(mid+1,high,key);
            }
            else{
                index=binarySearchRecur(low,mid-1,key);
            }
        }

        return index;
    }

    /*time complexity O(1)*/
    public int getElement(int index)throws ArrayADTException{

        if(index>=count){
            throw new ArrayADTException("index out of bound");
        }
        else if(index<0){
            throw new ArrayADTException("index can't be negative");
        }
        return this.arr[index];


    }

    /*time complexity O(1)*/
    public void setElement(int index,int value)throws ArrayADTException{

        if(index>=count){
            throw new ArrayADTException("index out of bound");
        }
        else if(index<0){
            throw new ArrayADTException("index can't be negative");
        }
        this.arr[index]=value;


    }

    /*time complexity: O(n)*/
    public int max(){
        int max=arr[0];
        for(int i=0;i<count;i++){
            if(arr[i]>=max){
                max=arr[i];
            }
        }
        return max;
    }

    /*time complexity: O(n)*/
    public int min(){
        int min=arr[0];
        for(int i=0;i<count;i++){
            if(arr[i]<=min){
                min=arr[i];
            }
        }
        return min;
    }
    /*time complexity: O(n)*/
    public int sum(){
        int sum=0;
        for(int i=0;i<count;i++){
            sum+=arr[i];
        }
        return sum;
    }
    /*time complexity: O(n)*/
    public double avg(){
        return sum()/(double)count;
    }

    /*time complexity:O(n), space complexity:O(n) because u create a temporary array*/
    public void reverse(){
        int[] temp=new int[count];
        for(int i=0;i<count;i++){
            temp[i]=arr[(count-1)-i];
        }
        arr=temp;
        temp=null;
    }

    /*time complexity:O(n) it is n/2 but still O(n), space complexity became o(1) so this is more
    professional
     */
    public void reverseInPlace(){
        int i=0;
        int j=count-1;
        int temp=0;
        while(i<j){
            temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;

        }
    }

    /*time complexity O(n) space complexity O(1)*/
    public void leftShift(){
        for(int i=0;i<count-1;i++){
            arr[i]=arr[i+1];
        }
        arr[count-1]=0;
    }
    /*time complexity O(n) space complexity O(1)*/
    public void leftRotate(){
        int temp=arr[0];
        for(int i=0;i<count-1;i++){
            arr[i]=arr[i+1];
        }
        arr[count-1]=temp;
    }
    /*time complexity O(n) space complexity O(1)*/
    public void rightShift(){
        for(int i=count-1;i>0;i--){
            arr[i]=arr[i-1];
        }
        arr[0]=0;
    }
    /*time complexity O(n) space complexity O(1)*/
    public void rightRotate(){
        int temp=arr[count-1];
        for(int i=count-1;i>0;i--){
            arr[i]=arr[i-1];
        }
        arr[0]=temp;
    }

    /*time complexity:best case O(1),worst case O(N)
    * this function uses a linear search to find the index of insertion
    * then shifts the array right starting from that index and insert the element*/
    public void linearInsertInSorted(int value){
        int index=0;
        if(count==this.arr.length){
            resize(2*count);
        }
        for(index =0;index<count;index++) {//this loops gives us the index for insertion in linear search method
            if (value <=arr[index]) {
                break;
            }
        }
        //now that we have the index for insertion we should shift the array to the right
        //starting from that index and then add our element at that index
        count++;
        rightShift(index);
        arr[index]=value;


    }

    /*time complexity:best case O(1),worst case O(n)
     *because binary search only improves the time complexity of search to be o(log n), but
     *the shifting technique for the insertion is always o(n) so the overall time complexity is o(N)
     *but the search function is improved
     * this function uses a Binary search to find the index of insertion
     * then shifts the array right starting from that index and insert the element*/


    /* old working version
    public void binaryInsertInSorted(int value){
        int index=0,low=0,high=count-1,mid=0;
        boolean found=false;
        if(count==this.arr.length){
            resize(2*count);
        }
        while(low<high){
            mid=(low+high)/2;
            if(value<=arr[low]){
                index=low;
                found=true;
                break;
            }
            else if(value==arr[mid]){
                index=mid;
                found=true;
                break;
            }
            else if(value>arr[mid]){
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }
        }
        count++;
        if(!found){
            if(value>arr[count-2]) {
                index=count-1;
            }
            else{
                index=low+1;
            }
        }

            rightShift(index);
            arr[index] = value;


    }*/

    /*best version*/
    public void binaryInsertInSorted(int value) {
        int index = 0, low = 0, high = count - 1, mid = 0;
        if (count == this.arr.length) {
            resize(2 * count);
        }
            while(low<=high){
                mid=(low+high)/2;
                if(value==arr[mid]){
                    low=mid;
                    break;
                }
                else if(value<arr[mid]){
                    high=mid-1;
                }
                else{
                    low=mid+1;
                }
            }
            index=low;
            count++;
            rightShift(index);
            arr[index]=value;
    }

    /*checks if the array is sorted or not, complexity o(N)*/
    public boolean isSorted(){
        boolean sorted=true;
        for(int i=0;i<count-1;i++){
            if(arr[i]>arr[i+1]){
                sorted=false;
                break;
            }
        }
        return sorted;
    }

    public void arrangeOnSign(){
        //this function places all negative values on the left and all positive values on the right
        //not sorting by value
        int i=0;//points to positive values
        int j=count-1;//points to negative values
        int temp=0;//for swapping operation
        while(i<j) {
            while (arr[i] < 0) {
                i++;
            }
            while (arr[j] > 0) {
                j--;
            }
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

    }

    /*time complexity theta(m+n), space complexity O(m+n)
    * the given two arrays must be sorted and it merges them into a sorted array*/
    public void mergeInPlace(Array arr2){
        int[] merged=new int[this.count+arr2.count];
        int i=0,j=0,k=0;
        while(i<this.count && j<arr2.count){
            if(arr[i]<=arr2.arr[j]){
                merged[k++]=arr[i++];
            }
            else
            {
                merged[k++]=arr2.arr[j++];
            }
        }
        while(i<count){
            merged[k++]=arr[i++];
        }
        while (j<arr2.count){
            merged[k++]=arr2.arr[j++];
        }
        this.arr=merged;
        this.count=this.count+arr2.count;
    }
    /*time complexity theta(m+n), space complexity O(m+n)
    * the given two arrays must be sorted and it merges them into a sorted array*/
    public Array merge(Array arr2){
        Array merged=new Array(this.count+arr2.count);

        int i=0,j=0,k=0;
        while(i<this.count && j<arr2.count){
            if(arr[i]<=arr2.arr[j]){
                merged.arr[k++]=arr[i++];
            }
            else
            {
                merged.arr[k++]=arr2.arr[j++];
            }
            merged.count++;
        }
        while(i<count){
            merged.arr[k++]=arr[i++];
            merged.count++;
        }
        while (j<arr2.count){
            merged.arr[k++]=arr2.arr[j++];
            merged.count++;
        }
        return merged;
    }

/*if both are sorted time complexity becomes theta(n+m) which can be considered O(n)
* but if they are not sorted it is considered O(n^2) due to the linear search loop inside the insertion loop
* note:input must be in a set form(no duplicates allowed)
* */
    public Array union(Array arr2){
        Array union=new Array(this.count+arr2.count);
        if(this.isSorted() &&arr2.isSorted()){
            /*if both lists are sorted then we use the merge technique*/
            int i=0,k=0,j=0;
            while(i<count &&j<arr2.count){
                if(arr[i]<arr2.arr[j]){
                    union.append(arr[i++]);
                }
                else if (arr[i]>arr2.arr[j]){
                    union.append(arr2.arr[j++]);
                }
                else{
                    union.append(arr[i++]);
                    j++;
                }
            }
            while(i<count){
                union.append(arr[i++]);
            }
            while(j<arr2.count){
                union.append(arr2.arr[j++]);
            }
        }
        else{
            for(int i=0;i<count;i++){
                union.append(arr[i]);
            }
            for(int j=0;j<arr2.count;j++){
               //this loop searches for each element in the first array
                //if it didn't find it it will add it to the list
                if(linearSearch(arr2.arr[j])==-1){//this linear search uses a loop internally
                        union.append(arr2.arr[j]);
                }
            }

        }
        return union;

    }

    /*if both are sorted time complexity becomes theta(n+m) which can be considered O(n)
    * * but if they are not sorted it is considered O(n^2) due to the linear search loop inside the insertion loop
    * note:input must be in a set form(no duplicates allowed)
    * */
    public Array intersect(Array arr2){
        Array intersect=new Array((this.count<=arr2.count)?this.count:arr2.count);//ternary operator
        if(this.isSorted() &&arr2.isSorted()){
            int i=0,j=0;
            while(i<count &&j<arr2.count){
                if(arr[i]==arr2.arr[j]){
                    intersect.append(arr[i]);
                    i++;
                    j++;
                }
                else if(arr[i]<arr2.arr[j]){
                    i++;
                }
                else{
                    j++;
                }
            }
        }
        else{
            for(int i=0;i<count;i++){
                if(arr2.linearSearch(arr[i])!=-1){
                    intersect.append(arr[i]);
                }
            }
        }
        return intersect;
    }

    /*if both are sorted time complexity becomes theta(n+m) which can be considered O(n)
     * * but if they are not sorted it is considered O(n^2) due to the linear search loop inside the insertion loop
     * note:input must be in a set form(no duplicates allowed)
     * */
    public Array difference(Array arr2){
        Array difference=new Array(count);//ternary operator
        if(this.isSorted() &&arr2.isSorted()){
            int i=0,j=0;
            while(i<count &&j<arr2.count){
                if(arr[i]<arr2.arr[j]){
                    difference.append(arr[i]);
                    i++;
                }
                else if(arr[i]>arr2.arr[j]){
                    j++;
                }
                else{
                    i++;j++;
                }
            }
            while(i<count){
                difference.append(arr[i]);
                i++;
            }
        }
        else{
            for(int i=0;i<count;i++){
                if(arr2.linearSearch(arr[i])==-1){
                    difference.append(arr[i]);
                }
            }

        }
        return difference;
    }



class ArrayADTException extends RuntimeException{//RuntimeException to make it unchecked exception
    public ArrayADTException(String message){
        super(message);//this is the best way so that JVM also understands
    }
    /*public String toString(){ return " ";}
     */
   /* Why your toString() was a bit "off"
    When you throw an exception, the JVM looks for the getMessage() method to print the error.
    By using super(message), you satisfy all the standard Java logging tools. If you only override toString(),
    some debuggers might not show the specific message clearly.
    */

}
}


public class TestArrayADT {
    public static void main(String[] Args) {
        try {
            int[] a = {3,4,7,10,12, 20,25, 30, 40, 50, 60, 70, 80, 90, 100,110};
            int[] b = {1,4,7,12,13,25,77,88,92};
            int[] c={-1,3,2,10,-6,100,50,40,33,21};
            int[] d={1,3,-1,100,51,33,22,41,66,-5,21};
            Array arr1 = new Array(a);
            Array arr2=new Array(b);
            Array arr3=new Array(c);
            Array arr4=new Array(d);
            //arr2.mergeInPlace(arr3);
            //arr2.display();

            Array arr5=arr1.difference(arr2);
            arr5.display();
            arr5=arr2.difference(arr1);
            arr5.display();

            arr5=arr3.difference(arr4);
            arr5.display();
            arr5=arr4.difference(arr3);
            arr5.display();



        } catch (Exception e) {
            System.out.println(e);
        }

    }
}


/*test log
tested display

append test:
 Array arr1=new Array(5);
        int[] a={1,2,3,4,5,6,7,8,9,10};
        Array arr2=new Array(a);
        Array arr3=new Array(a,20);

        arr1.append(30);
        arr1.append(40);
        arr1.display();
        arr1.append(50);
        arr1.append(60);
        arr1.append(70);
        arr1.display();
        arr1.append(80);
        arr1.display();
        arr2.append(10);
        arr2.display();


insert test:
          try {
            Array arr1 = new Array(5);
            int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);
            arr2.insert(4, 100);
            arr2.display();
            //arr2.insert(30, 10); raises exception
            //arr1.insert(-3, 10); raises exception
        } catch (Exception e) {
            System.out.println(e);
        }

delete test:
  Array arr1 = new Array(5);
            int[] a = {0,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            arr2.delete(3);
            arr2.delete(3);
            arr2.delete(3);
            arr2.delete(3);
            arr2.delete(3);
            arr2.display();
            arr2.delete(3);
            arr2.delete(5);
            arr2.delete(4);
            arr2.delete(3);
            arr2.display();
            //arr2.delete(-1); will raise exception
            //arr2.delete(20); will raise exception
            //arr1.delete(0); will raise exception

linear search test:
            Array arr1 = new Array(5);
            int[] a = {0,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            System.out.println(arr2.linearSearch(30));
            System.out.println(arr2.linearSearch(200));


Binary Search test:
            Array arr1 = new Array(5);
            int[] a = {0,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            System.out.println(arr2.linearSearch(30));
            System.out.println(arr2.linearSearch(200));
            System.out.println(arr2.binarySearchIter(0,arr2.count-1,50));
            System.out.println(arr2.binarySearchIter(0,arr2.count-1,100));
            System.out.println(arr2.binarySearchIter(0,arr2.count-1,250));
            System.out.println(arr2.binarySearchRecur(0,arr2.count-1,50));
            System.out.println(arr2.binarySearchRecur(0,arr2.count-1,100));
            System.out.println(arr2.binarySearchRecur(0,arr2.count-1,250));
        } catch (Exception e) {
            System.out.println(e);
        }

get,set,max,min,sum,avg test:
        Array arr1 = new Array(5);
            int[] a = {0,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            System.out.println(arr2.getElement(4));//40
            arr2.setElement(4,200);
            System.out.println(arr2.getElement(4));//200
            arr2.setElement(0,65);
            arr2.display();
            System.out.println(arr2.max());
            System.out.println(arr2.min());
            System.out.println(arr2.sum());
            System.out.println(arr2.avg());


reverse test:
            Array arr1 = new Array(5);
            int[] a = {0,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            arr2.display();
            arr2.reverse();
            arr2.display();
            arr2.reverseInPlace();
            arr2.display();

shift and rotate test:
            Array arr1 = new Array(5);
            int[] a = {0,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            arr2.display();
            arr2.leftShift();
            arr2.display();
            arr2.leftRotate();
            arr2.display();
            arr2.rightShift();
            arr2.display();
            arr2.rightRotate();
            arr2.display();


Insert in a sorted array test+ sorted array check:
             Array arr1 = new Array(5);
            int[] a = {3,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            Array arr3 = new Array(a, 20);

            arr2.display();
            arr2.linearInsertInSorted(66);
            arr2.display();
            arr2.linearInsertInSorted(32);
            arr2.linearInsertInSorted(101);
            arr2.linearInsertInSorted(112);
            arr2.linearInsertInSorted(1);
            arr2.display();
            arr2.binaryInsertInSorted(0);
            arr2.binaryInsertInSorted(130);
            arr2.binaryInsertInSorted(99);
            arr2.binaryInsertInSorted(56);
            arr2.display();
            if(arr2.isSorted()){
                System.out.println("is sorted");
            }

merging test:
            Array arr1 = new Array(5);
            int[] a = {3,10, 20, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            int[] b = {1,4,7,12,13,25,77,88,92};
            Array arr3=new Array(b);

            //arr2.mergeInPlace(arr3);
            //arr2.display();

            Array arr4=arr2.merge(arr3);
            arr4.display();


test for union:
            Array arr1 = new Array(5);
            int[] a = {3,4,7,10,12, 20,25, 30, 40, 50, 60, 70, 80, 90, 100,110};
            Array arr2 = new Array(a);
            int[] b = {1,4,7,12,13,25,77,88,92};
            Array arr3=new Array(b);
            int[] c={-1,3,2,10,-6,100,50,40,33,21};
            int[] d={1,3,-1,100,51,33,22,41,66,-5,21};
            Array arr5=new Array(c);
            Array arr6=new Array(d);
            //arr2.mergeInPlace(arr3);
            //arr2.display();

            Array arr4=arr2.union(arr3);
            arr4.display();
            arr4=arr5.union(arr6);
            arr4.display();

test for intersection:
                    int[] a = {3,4,7,10,12, 20,25, 30, 40, 50, 60, 70, 80, 90, 100,110};
            int[] b = {1,4,7,12,13,25,77,88,92};
            int[] c={-1,3,2,10,-6,100,50,40,33,21};
            int[] d={1,3,-1,100,51,33,22,41,66,-5,21};
            Array arr1 = new Array(a);
            Array arr2=new Array(b);
            Array arr3=new Array(c);
            Array arr4=new Array(d);
            //arr2.mergeInPlace(arr3);
            //arr2.display();

            Array arr5=arr1.intersect(arr2);
            arr5.display();
            arr5=arr3.intersect(arr4);
            arr5.display();

test for difference:
                    int[] a = {3,4,7,10,12, 20,25, 30, 40, 50, 60, 70, 80, 90, 100,110};
            int[] b = {1,4,7,12,13,25,77,88,92};
            int[] c={-1,3,2,10,-6,100,50,40,33,21};
            int[] d={1,3,-1,100,51,33,22,41,66,-5,21};
            Array arr1 = new Array(a);
            Array arr2=new Array(b);
            Array arr3=new Array(c);
            Array arr4=new Array(d);
            //arr2.mergeInPlace(arr3);
            //arr2.display();

            Array arr5=arr1.difference(arr2);
            arr5.display();
            arr5=arr2.difference(arr1);
            arr5.display();

            arr5=arr3.difference(arr4);
            arr5.display();
            arr5=arr4.difference(arr3);
            arr5.display();


 */