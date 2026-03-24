package DataStructuresAndAlgorithms.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*i was converting this file from int only to generics but i didn't continue*/

/*this file is the same as TestArrayADT file, which i used to build the array abstract data type
but i made it in a package form so that i can practice some problems using this package in a professional way
 */
public class Array <T>implements Iterable<T>{
    T[] arr;//reference to the array object,it will refer to the object and its length
    int count;//the number of elements actually in the array

    /*
    for initializing an empty array with a given capacity
    * */

    public Array(){
        arr=(T[])new Object[1];
        count=0;
    }
    public Array(int length){
        arr=(T[])new Object[length];//creation if the array with the desired length
        count=0;
    }
    /*for creating an array object from an existing array*/
    public Array(T []arr){
        this.arr=arr;
        count=arr.length;
    }
    /*for creating an array object from an existing array but with bigger size*/
    public Array(T[] arr,int length){
        this.arr=(T[])new Object[length];
        for(int i=0;i<arr.length;i++){
            this.arr[i]=arr[i];
        }
        count=arr.length;
    }



    /*private methods*/
    private void resize(int newSize){
        T[] temparr=(T[])new Object[newSize];
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
        arr[startingIndex]=null;
    }

    /*getters*/
    public int getCount(){
        return count;
    }
    /*time complexity O(1)*/
    public T getElement(int index)throws ArrayADTException {

        if(index>=count){
            throw new ArrayADTException("index out of bound");
        }
        else if(index<0){
            throw new ArrayADTException("index can't be negative");
        }
        return this.arr[index];


    }

    /*setters*/
    /*time complexity O(1)*/
    public void setElement(int index,T value)throws ArrayADTException {

        if(index>=count){
            throw new ArrayADTException("index out of bound");
        }
        else if(index<0){
            throw new ArrayADTException("index can't be negative");
        }
        this.arr[index]=value;


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
    public void append(T x){
        if(count==this.arr.length){
            resize(2*count);
        }
        this.arr[count]=x;
        count++;
    }

    /*worst case time complexity is O(n),best case is O(1)*/
    public void insert(int index,T value) throws ArrayADTException {
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
    public void delete(int index) throws ArrayADTException {
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
    public int linearSearch(T key) {
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
    public int binarySearchIter(int low,int high,T key){
        int mid=0;
        int index=-1;
        while(low <= high){
            mid=(low+high)/2;
            int cmp = ((Comparable<T>)key).compareTo(this.arr[mid]);
            if(cmp==0)
            {
                index=mid;
                break;
            }
            else if (cmp>0)
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
    public int binarySearchRecur(int low,int high,T key){
        int mid=0;
        int index=-1;
        if(low<=high){
            mid=(low+high)/2;
            int cmp = ((Comparable<T>)key).compareTo(this.arr[mid]);
            if(cmp==0){
                index=mid;
            }
            else if(cmp>0){
                index=binarySearchRecur(mid+1,high,key);
            }
            else{
                index=binarySearchRecur(low,mid-1,key);
            }
        }

        return index;
    }

    /*time complexity: O(n)*/
    public T max(){
        T max=arr[0];
        int cmp;
        for(int i=0;i<count;i++){
            cmp=((Comparable<T>)arr[i]).compareTo(max);
            if(cmp>=0){
                max=arr[i];
            }
        }
        return max;
    }

    /*time complexity: O(n)*/
    public T min(){
        T min=arr[0];
        int cmp;
        for(int i=0;i<count;i++){
            cmp=((Comparable<T>)arr[i]).compareTo(min);
            if(cmp<=0){
                min=arr[i];
            }
        }
        return min;
    }

    /*time complexity:O(n), space complexity:O(n) because u create a temporary array*/
    public void reverse(){
        T[] temp=(T[])new Object[count];
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
        T temp=null;
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
        arr[count-1]=null;
    }
    /*time complexity O(n) space complexity O(1)*/
    public void leftRotate(){
        T temp=arr[0];
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
        arr[0]=null;
    }
    /*time complexity O(n) space complexity O(1)*/
    public void rightRotate(){
        T temp=arr[count-1];
        for(int i=count-1;i>0;i--){
            arr[i]=arr[i-1];
        }
        arr[0]=temp;
    }

    /*time complexity:best case O(1),worst case O(N)
     * this function uses a linear search to find the index of insertion
     * then shifts the array right starting from that index and insert the element*/
    public void linearInsertInSorted(T value){
        int index=0;
        if(count==this.arr.length){
            resize(2*count);
        }
        for(index =0;index<count;index++) {//this loops gives us the index for insertion in linear search method
            int cmp= ((Comparable<T>)value).compareTo(arr[index]);
            if (cmp<=0) {
                break;
            }
        }
        //now that we have the index for insertion we should shift the array to the right
        //starting from that index and then add our element at that index
        count++;
        rightShift(index);
        arr[index]=value;


    }
    public int indexOf(T value){
        int i=-1;
        if(count>0){
            int cmp;
            for(i=0;i<count;i++){
                cmp=((Comparable<T>)value).compareTo(arr[i]);
                if(cmp==0){
                    break;
                }
            }
        }
        return i;
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
    public void binaryInsertInSorted(T value) {
        int index = 0, low = 0, high = count - 1, mid = 0;
        if (count == this.arr.length) {
            resize(2 * count);
        }
        while(low<=high){
            mid=(low+high)/2;
            int cmp= ((Comparable<T>)value).compareTo(arr[mid]);
            if(cmp==0){
                low=mid;
                break;
            }
            else if(cmp<0){
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
            if(((Comparable<T>)arr[i]).compareTo(arr[i+1])>0){
                sorted=false;
                break;
            }
        }
        return sorted;
    }


    /*time complexity theta(m+n), space complexity O(m+n)
     * the given two arrays must be sorted and it merges them into a sorted array*/
    public void mergeInPlace(Array<T> arr2){
        T[] merged=(T[])new Object[this.count+arr2.count];
        int i=0,j=0,k=0;
        while(i<this.count && j<arr2.count){
            int cmp=((Comparable<T>)arr[i]).compareTo(arr2.arr[j]);
            if(cmp<=0){
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
    public Array merge(Array<T> arr2){
        Array<T> merged=new Array<T>(this.count+arr2.count);

        int i=0,j=0,k=0;
        while(i<this.count && j<arr2.count){
            int cmp=((Comparable<T>)arr[i]).compareTo(arr2.arr[j]);
            if(cmp<=0){
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
    public Array<T> union(Array<T> arr2){
        Array<T> union=new Array<T>(this.count+arr2.count);
        if(this.isSorted() &&arr2.isSorted()){
            /*if both lists are sorted then we use the merge technique*/
            int i=0,k=0,j=0;
            int cmp=0;
            while(i<count &&j<arr2.count){
                cmp=((Comparable<T>)arr[i]).compareTo(arr2.arr[j]);
                if(cmp<0){
                    union.append(arr[i++]);
                }
                else if (cmp>0){
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
    public Array<T> intersect(Array<T> arr2){
        Array<T> intersect=new Array<T>((this.count<=arr2.count)?this.count:arr2.count);//ternary operator
        if(this.isSorted() &&arr2.isSorted()){
            int i=0,j=0,cmp=0;
            while(i<count &&j<arr2.count){
                cmp=((Comparable<T>)arr[i]).compareTo(arr2.arr[j]);
                if(cmp==0){
                    intersect.append(arr[i]);
                    i++;
                    j++;
                }
                else if(cmp<0){
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
    public Array<T> difference(Array<T> arr2){
        Array<T> difference=new Array<T>(count);//ternary operator
        if(this.isSorted() &&arr2.isSorted()){
            int i=0,j=0,cmp=0;
            while(i<count &&j<arr2.count){
                cmp=((Comparable<T>)arr[i]).compareTo(arr2.arr[j]);
                if(cmp<0){
                    difference.append(arr[i]);
                    i++;
                }
                else if(cmp>0){
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

    public boolean isFull(){
        return count==this.arr.length;
    }

    public void swap(int index1,int index2){
        T temp=getElement(index1);
        setElement(index1, getElement(index2));
        setElement(index2,temp);
    }

    /*converting the class to iterable
    first step update the signature: public class Array <T>implements Iterable<T>
    second step import the required interfaces
    import java.util.Iterator;
    import java.util.NoSuchElementException;

    third step create array iterator class

    fourth step override iterator method


     */


    @Override
    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int currentIndex=0;
        @Override
        public boolean hasNext(){
            return currentIndex<count;//checks if the array has a next element
        }
        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            return arr[currentIndex++];
        }
        @Override
        public void remove() {
            // Optional but professional: allow removal during iteration
            try {
                Array.this.delete(--currentIndex);
            } catch (ArrayADTException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }

}
