package DataStructuresAndAlgorithms.Stack;

public class StackArray{
    private int[] arr;//array for storing the data
    private int top;//to keep track of the top element,and the number of elements

    StackArray(int length){
        arr=new int[length];
        top=-1;//when the stack is empty top value is -1,because 0 points to the element at index 0
    }

    /*time complexity O(N)*/
    public void display(){
        if(top==-1){
            return;
        }
        else{
            int counter=top;
            while(counter>=0){
                System.out.println(arr[counter]);
                counter--;
            }
        }
    }
    /*time complexity O(1)*/
    public void push(int x)throws RuntimeException{
        if(top==arr.length-1){
            throw new RuntimeException("stack is full");
        }
        else{
            top++;
            arr[top]=x;
        }
    }

    /*time complexity O(1)*/
    public int pop() throws RuntimeException{
        if(top==-1){
            throw new RuntimeException("stack is empty");
        }
        else{
            top--;
            return arr[top+1];
        }
    }

    /*time complexity O(1)*/
    public int peek(int index) throws RuntimeException{
        if(index<0 ||index >top+1 ||top==-1){
            throw new RuntimeException("out of index");
        }
        else{
            index=top+1-index;
            return arr[index];
        }
    }

    /*time complexity O(1)*/
    public int stackTop() throws RuntimeException{
        if(top==-1){
            throw new RuntimeException("stack is empty");
        }
        else{
            return arr[top];
        }
    }

    /*time complexity O(1)*/
    public boolean isEmpty(){
        return (top==-1);
    }

    /*time complexity O(1)*/
    public boolean isFull(){
        return (top==arr.length-1);
    }


}
