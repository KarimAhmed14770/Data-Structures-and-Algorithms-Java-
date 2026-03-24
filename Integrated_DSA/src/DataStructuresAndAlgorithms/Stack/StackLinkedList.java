package DataStructuresAndAlgorithms.Stack;

public class StackLinkedList<T>{
    private class Node{
        T data;
        Node next;

        Node(T data){
            this.data=data;
            this.next=null;
        }
    }
    private Node top;//will point to the top element in the stack
    private int size;//will carry the number of elements in the stack

    public StackLinkedList(){
        top=null;
    }

    /*time complexity O(n)*/
    public void display(){
        Node current=top;
        while(current!=null){
            System.out.println(current.data);
            current=current.next;
        }
    }
    /*time complexity O(1)*/
    public void push(T data){
        Node x=new Node(data);
        if(top==null){
            top=x;
            //x.next is already null
        }
        else{
            x.next=top;
            top=x;
        }
        size++;
    }

    /*time complexity O(1)*/
    public T pop()throws RuntimeException{
        if(top==null){
            throw new RuntimeException("stack is empty");
        }
        else{
            Node temp=top;
            top=top.next;
            temp.next=null;
            size--;
            return temp.data;
        }
    }

    /*time complexity O(n)*/
    public T peek(int index)throws RuntimeException{
        if(index<0 || index>size){
            throw new RuntimeException("index out of bound");
        }
        else if(top==null){
            throw new RuntimeException("stack is empty");
        }
        else{
            Node current=top;
            for(int i=1;i<index;i++){
                current=current.next;
            }
            return current.data;
        }
    }

    /*time complexity O(1)*/
    public T stackTop()throws RuntimeException{
        if(top==null){
            throw new RuntimeException("stack is empty");
        }
        else{
            return top.data;
        }
    }
    /*time complexity O(1)*/
    public boolean isEmpty(){
        return (top==null);
    }
}