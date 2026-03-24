package DataStructuresAndAlgorithms.Queues;

public class LinearQueue <T>{
    private T[] arr;
    private int size;
    private int last_ptr;

    public LinearQueue(int size){
        this.size=size;
        arr=(T[])new Object[size];
        last_ptr=-1;
    }

    /*time complexity:O(N)*/
    public void display(){
        for(int i=last_ptr;i>=0;i--){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    /*time complexity:O(1)*/
    public void enqueue(T x) throws RuntimeException{
        if(last_ptr==size-1){
            throw new RuntimeException("Queue is full");
        }
        else{
            last_ptr++;
            arr[last_ptr]=x;
        }
    }

    /*time complexity:O(N)*/
    public T dequeue()throws RuntimeException{
        T result;
        if(last_ptr==-1){
            throw new RuntimeException("Queue is empty");
        }
        else{
            result=arr[0];
            for(int i=0;i<last_ptr;i++){
                arr[i]=arr[i+1];
            }
            last_ptr--;
            return result;
        }

    }

    /*time complexity:O(1)*/
    public boolean isEmpty(){
        return last_ptr==-1;
    }
    /*time complexity:O(1)*/
    public boolean isFull(){
        return last_ptr==size-1;
    }

}
