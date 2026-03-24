package DataStructuresAndAlgorithms.Queues;

public class LinearQueueLL<T>{
    private class Node{
        private T data;
        private Node next;

        Node(T data){
            this.data=data;
            next=null;
        }
    }
    private Node head;
    private Node tail;

    public LinearQueueLL(){
        head=null;
        tail=null;

    }

    public void display(){
        Node current=head;
        while(current!=null){
            System.out.print(current.data+" ");
            current=current.next;
        }
        System.out.println();
    }
    public void enqueue(T data){
        Node x=new Node(data);
        if(head==null){
            head=x;
            tail=x;
        }
        else{
            tail.next=x;
            tail=x;
        }
    }

    public T dequeue()throws RuntimeException{
        T result;
        if(head==null){
            throw new RuntimeException("queue is empty");
        }
        result= head.data;
        if(head.next==null){
            head=null;
            tail=null;
        }
        else{
            head=head.next;
        }
        return result;

    }

    public boolean isEmpty(){
        return head==null;
    }
}
