package DataStructuresAndAlgorithms.LinkedLists;

public class CircularLinkedList {
    private class Node{
        int data;
        Node next;

        Node(){
            this.data=0;
            next=null;
        }
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    private Node tail;
    private int size;

    CircularLinkedList(){
        this.tail=null;
        size=0;
    }
    CircularLinkedList(int data){
        tail=new Node(data);;
        tail.next=tail;
        size=1;
    }



    /*time complexity O(n)*/
    public void display(){
        if (tail==null){
            return;
        }
        else{
            Node current=tail.next;
            System.out.println("List elements("+size+"):");
            do{
                System.out.print(current.data+" ");
                current=current.next;
            }while(current!=tail.next);
            System.out.println();
        }
    }

    /*time complexity O(1)*/
    public void insertAtBeginning(int data){
        Node x=new Node(data);
        x.next=tail.next;
        tail.next=x;
        size++;
    }

    /*best case O(1), time complexity O(n)*/
    public void insert(int index,int data) throws LinkedListADTException {
        if(index<0 ||index>size){
            throw new LinkedListADTException("index out of bound");
        }
        else if(index==0){
            insertAtBeginning(data);
        }
        else if(index==size){//this will be the new tail
            Node x=new Node(data);
            x.next=tail.next;
            tail.next=x;
            tail=x;
            size++;
        }
        else{
            Node previous=tail.next;
            Node x=new Node(data);
            for(int i=1;i<index;i++){
                previous=previous.next;
            }
            x.next=previous.next;
            previous.next=x;
            size++;


        }
    }

    public void deleteFirst() {
        if (size == 0) {
            return;
        }
        else if (size == 1) {
            tail=null;
            size--;
        }
        else {
            Node temp = tail.next.next;
            tail.next.next = null;
            tail.next = temp;
            size--;
        }
    }

    public void delete(int index) throws LinkedListADTException{
        if(index<0 ||index>=size){
            throw new LinkedListADTException("index out of bound");
        }
        else if(index==0){
            deleteFirst();
        }
        else{
            Node previous=tail.next;
            Node current=previous.next;
            for(int i=1;i<index;i++){
                previous=previous.next;
                current=current.next;
            }
            previous.next=current.next;
            current.next=null;
            if(index==size-1){
                tail=previous;
            }
            size--;
        }
    }
}
