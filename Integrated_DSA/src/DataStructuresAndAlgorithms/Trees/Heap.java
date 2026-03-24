package DataStructuresAndAlgorithms.Trees;

import DataStructuresAndAlgorithms.Arrays.Array;

public class Heap {//this will be a max heap class
    private Array<Integer> data;
    int count;

    public Heap() {
        data = new Array<>();
        data.append(0);
        count=0;

    }

    public Heap(int[]arr){
        data = new Array<>();
        data.append(0);
        count=0;
        heapify(arr);
    }

    private void swap(int index1,int index2){
        int temp= data.getElement(index2);
        data.setElement(index2,data.getElement(index1));
        data.setElement(index1,temp);
    }
    /*it takes an element from a starting index and checks it with all its children and subchildren
    * and puts everything in correct place*/
    private void heapifyDown(int index,int limit){
        while(index*2<=limit) {
            int leftChildIndex = 2 * index;
            int rightChildIndex = 2 * index + 1;
            int largerChildIndex = leftChildIndex;
            if(rightChildIndex<=limit && data.getElement(rightChildIndex)>data.getElement(leftChildIndex) ){
                largerChildIndex=rightChildIndex;
            }
            if(data.getElement(index)>data.getElement(largerChildIndex)){
                break;
            }
            swap(index,largerChildIndex);
            index=largerChildIndex;
        }

    }

    public void display() {
        for (int i = 1; i <= count; i++) {
            System.out.print(data.getElement(i) + " ");
        }
        System.out.println();
    }


    public void insert(int value) {
        int i = data.getCount();//the index of the new insertion
        data.append(value);
        count++;
        while (i > 1 && value >= data.getElement(i / 2)) {
            data.setElement(i,data.getElement(i / 2));
            i = i / 2;
        }
        data.setElement(i,value);

    }

    public int poll(){
        int returnValue= data.getElement(1);
        int replaced=data.getElement(count);
        data.setElement(1, replaced);
        data.delete(count);
        count--;
        int i=1;
/*my logic which works perfectly but not clean and not refined
        while (i<=count/2){
            if(2*i+1<=count){//this specific node has 2 childs
                if(data.getElement(2*i)>data.getElement(2*i+1)){
                    if(data.getElement(i)<data.getElement(2*i)){
                        swap(i,2*i);
                        i=2*i;
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(data.getElement(i)<data.getElement(2*i+1)){
                        swap(i,2*i+1);
                        i=2*i+1;
                    }
                    else{
                        break;
                    }
                }

            }
            else if(2*i==count){
                if(data.getElement(i)<data.getElement(2*i)){
                    swap(i,2*i);
                    i=2*i;
                }
            }
        }
        */
        /*the same logic clean and refined*/
        heapifyDown(i,count);
        return returnValue;


    }

    /*
    this is the heapsort it works on a heap object, and takes time complexity nlog(n) and
    space complexity o(1) as it sorts in place , heap property is destroyed, it is now just a sorted array
    * */
    public void heapSort(){
       int c=count;
       while(c>0){
           int returnValue= data.getElement(1);
           int lastElement=data.getElement(c);
           data.setElement(1, lastElement);
           data.setElement(c,returnValue);
           int i=1;
           c--;
           heapifyDown(i,c);
       }

    }

    /*static heapsort implementation
    it takes an array, creates a heap then polls each element from the heap and arranges it in an array
    copy, its advantage , it doesn't change the original heap it just returns a new array
    disadvantage it consumes memory o(n) for ne array creation
    space complexity :o(n), time complexity o(n*log(n))
     */
    public static int[] heapSort(int[] arr){
        int[] result=new int[arr.length];
        Heap temp=new Heap();
        for(int i=0;i< arr.length;i++){
            temp.insert(arr[i]);
        }
        for(int i=result.length-1;i>=0;i--){
            result[i]=temp.poll();
        }
        return result;
    }

    /*the O(n) creation of heap instantly given a list of elements, used to create priority queues*/
    public void heapify(int[] arr){
        if(count!=0)return;//heapify is only to create a heap from begining
        else{
            for(int i=1;i<=arr.length;i++){
                data.insert(i,arr[i-1]);
                count++;
            }

            int parentIndex=count/2;
            /*
            int leftChildIndex;
            int rightChildIndex;
            int largeChildIndex;
            int tempParentIndex;
            */

            while (parentIndex>=1){
                /*

                 this was my first implementation, it is working but has redundant code, cause i already
                 implemented the heapifyDown or sink logic above, just use it here

                 leftChildIndex=2*parentIndex;
                 rightChildIndex=2*parentIndex+1;
                 largeChildIndex=leftChildIndex;
                if(rightChildIndex<=count &&data.getElement(rightChildIndex)>data.getElement(leftChildIndex)){
                    largeChildIndex=rightChildIndex;
                }
                if(data.getElement(parentIndex)<data.getElement(largeChildIndex)){
                    swap(parentIndex,largeChildIndex);
                    /*if the swap occurs you should check if the element should be swapped with
                    children or not

                    heapifyDown(largeChildIndex,count);

                }*/
                heapifyDown(parentIndex,count);
                parentIndex=parentIndex-1;
            }

        }
    }

}



/*heap test
public static void main(String[] Args){
        Heap h=new Heap();

        /********inserting test********
        System.out.println("Inserting Test");
        h.insert(40);
        h.insert(35);
        h.insert(30);
        h.insert(15);
        h.insert(10);
        h.insert(25);
        h.insert(5);
        h.display();
        System.out.println();
        System.out.println();
/**************deletion test************
        System.out.println("Deletion Test");
        System.out.println(h.poll());
        h.display();
        System.out.println(h.poll());
        h.display();
        System.out.println(h.poll());
        h.display();
        System.out.println(h.poll());
        h.display();

/*static heapsort test*
        System.out.println("static heapsort Test");
int[] a={30,20,70,10,4,3,80,55,33,77,100,21,34,67};
a=Heap.heapSort(a);
        for(int i:a){
        System.out.print(i+" ");
        }
                System.out.println();
        System.out.println();
        System.out.println();
/********heapsort test********
        System.out.println("heapSort Test");
Heap h2=new Heap();
        h2.insert(40);
        h2.insert(35);
        h2.insert(30);
        h2.insert(15);
        h2.insert(10);
        h2.insert(25);
        h2.insert(5);
        h2.display();
        h2.heapSort();
        h2.display();
        System.out.println();
        System.out.println();

/*heapify  test*
        System.out.println("Heapify Test");

int[] b={30,20,70,10,4,3,80,55,33,77,100,21,34,67};
Heap h3=new Heap(b);
        h3.display();


    }

 */