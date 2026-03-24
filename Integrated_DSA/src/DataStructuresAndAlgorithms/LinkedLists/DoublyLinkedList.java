package DataStructuresAndAlgorithms.LinkedLists;

public class DoublyLinkedList {
    private class Node{
        int data;
        Node next;
        Node previous;

        Node(){
            this.next=null;
            this.previous=null;
        }
        Node(int data){
            this.data=data;
            next=null;
            previous=null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    DoublyLinkedList(){
        this.head=null;
        this.tail=null;
    }
    public void display(){
        Node current=head;
        System.out.println("List elements("+size+"):");
        while(current!=null){
            System.out.print(current.data+" ");
            current=current.next;
        }
        System.out.println();
    }

    public void displayReverse(){
        Node current=tail;
        System.out.println("List elements reversed("+size+"):");
        while(current!=null){
            System.out.print(current.data+" ");
            current=current.previous;
        }
        System.out.println();
    }

    /*time complexity O(1)*/
    public void append(int data){
        Node x=new Node(data);
        if(head==null){
            head=x;
            tail=x;
        }
        else{
            tail.next=x;
            x.previous=tail;
            tail=x;
        }
        size++;
    }
    public void insertAtBeginning(int data){
        Node x=new Node(data);
        if(head==null){
            head=x;
            tail=x;
        }
        else{
            x.next=head;
            head.previous=x;
            head=x;
        }
        size++;
    }
    public void insert(int index,int data)throws LinkedListADTException {
        if(index<0 || index>size){
            throw new LinkedListADTException("index out of bound");
        }
        else if(index==size){
            append(data);
        }
        else if(index==0){
            insertAtBeginning(data);
        }
        else{
            Node x=new Node(data);
            Node current=null;
            if((size-index>=(size/2))){
                current=head;
                for(int i=0;i<index-1;i++){
                    current=current.next;
                }
            }
            else{
                current=tail;
                for(int i=size-1;i>index-1;i--){
                    current=current.previous;
                }
            }
            x.next=current.next;
            current.next.previous=x;
            current.next=x;
            x.previous=current;
            size++;
        }
    }

    public void deleteFirst(){
        if(this.head==null){
            return;
        }
        else if (this.head.next==null){
            this.head=null;
        }
        else{
            Node temp=this.head.next;
            this.head.next=null;
            this.head=temp;
            this.head.previous=null;
        }
        size--;
    }
    public void deleteLast(){
        if(this.tail==null){
            return;
        }
        else{
            Node temp=this.tail.previous;
            this.tail.previous=null;
            this.tail=temp;
            this.tail.next=null;
            size--;
        }
    }
    public void delete(int index){
        if(index<0 ||index>=size){
            throw new LinkedListADTException("index out of bound");
        }
        else if(index==0){
            deleteFirst();
        }
        else if(index==size-1){
            deleteLast();
        }
        else{
            Node temp;
            Node current=null;
            if(size-index>=size/2){
                current=head;
                for(int i=0;i<index-1;i++){
                    current=current.next;
                }
            }
            else{
                current=tail;
                for(int i=size-1;i>index+1;i--){
                    current=current.previous;
                }
            }
            temp=current.previous;
            temp.next=current.next;
            current.next.previous=temp;
            current.next=null;
            current.previous=null;
            size--;
        }
    }
}
