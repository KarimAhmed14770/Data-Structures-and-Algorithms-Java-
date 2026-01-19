


class LinearQueue{//this class will be based on array and uses single pointer for the last element
    private int[] arr;
    private int size;
    private int last_ptr;

    LinearQueue(int size){
        this.size=size;
        arr=new int[size];
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
    public void enqueue(int x) throws RuntimeException{
        if(last_ptr==size-1){
            throw new RuntimeException("Queue is full");
        }
        else{
            last_ptr++;
            arr[last_ptr]=x;
        }
    }

    /*time complexity:O(N)*/
    public int dequeue()throws RuntimeException{
        int result;
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


class CircularQueue{//arr based circular queue
    private int[] arr;
    private int size;
    private int first_ptr;
    private int last_ptr;

    CircularQueue(int size){
        this.size=size;
        arr=new int[size];
        first_ptr=0;
        last_ptr=0;
    }

    /*time complexity O(n)*/
    public void display(){
        int counter=last_ptr;
        while((counter)%size!=first_ptr){
            System.out.print(arr[counter]+" ");
            counter=(counter-1)%size;
            if(counter<0){
                counter=size-1;
            }
        }
        System.out.println();

    }

    /*time complexity O(1)*/
    public void enqueue(int x)throws RuntimeException{
        if((last_ptr+1)%size==first_ptr){//queue is full condition
            throw new RuntimeException("Queue is full");
        }
        else{
            last_ptr=(last_ptr+1)%size;
            arr[last_ptr]=x;
        }
    }

    /*time complexity O(1)*/
    public int dequeue()throws RuntimeException{
        if(last_ptr==first_ptr){
            throw new RuntimeException("Queue is empty");
        }
        else{
            first_ptr=(first_ptr+1)%size;
            return arr[first_ptr];
        }
    }

    public boolean isEmpty(){
        return last_ptr==first_ptr;
    }
    public boolean isFull(){
        return (last_ptr+1)%size==first_ptr;
    }
}

class LinearQueueLL{
    private class Node{
        private int data;
        private Node next;

        Node(int data){
            this.data=data;
            next=null;
        }
    }
    private Node head;
    private Node tail;

    LinearQueueLL(){
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
    public void enqueue(int data){
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

    public int dequeue()throws RuntimeException{
        int result;
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
}

class DEQueue {//double ended queue based on doubly linked list

    private class Node {
        int data;
        Node next;
        Node previous;

        Node(int data) {
            this.data = data;
            next = null;
            previous = null;
        }
    }

    private Node head;
    private Node tail;

    DEQueue() {
        head = null;
        tail = null;
    }

    public void display(){
        Node current=head;
        while(current!=null){
            System.out.print(current.data+" ");
            current=current.next;
        }
        System.out.println();
    }

    public void insertAtHead(int data){
        Node x=new Node(data);
        if(head==null){
            head=x;
            tail=x;
        }
        else{
            head.previous=x;
            x.next=head;
            head=x;
        }
    }

    public void insertAtTail(int data){
        Node x=new Node(data);
        if(head==null){
            head=x;
            tail=x;
        }
        else{
            x.previous=tail;
            tail.next=x;
            tail=x;
        }
    }

    public int deleteHead()throws RuntimeException{
        int result;
        if(head==null){
            throw new RuntimeException("DEQueue is empty");
        }
        result=head.data;
        if(head.next==null){
            head=null;
            tail=null;
        }
        else{
            head=head.next;
            head.previous=null;
        }
        return result;
    }

    public int deleteTail(){
        int result;
        if(head==null){
            throw new RuntimeException("DEQueue is empty");
        }
        result=tail.data;
        if(head.next==null){
            head=null;
            tail=null;
        }
        else{
            tail=tail.previous;
            tail.next=null;
        }
        return result;
    }
}






public class QueueTest {
    public static void main(String[] Args){
        try{
            DEQueue q1=new DEQueue();
        //q1.dequeue();
        q1.insertAtHead(10);
        q1.insertAtHead(11);
        q1.insertAtHead(12);
        q1.insertAtTail(13);
        q1.insertAtTail(14);
        q1.deleteHead();
        q1.deleteTail();
        q1.display();

    }
        catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}


/*
* Linear queue test:
*       LinearQueue q1=new LinearQueue(10);
        q1.enqueue(10);
        q1.enqueue(11);
        q1.enqueue(12);
        q1.enqueue(13);
        q1.display();
        q1.dequeue();
        q1.dequeue();
        q1.display();
        q1.enqueue(14);
        q1.enqueue(15);
        q1.display();
*
* */