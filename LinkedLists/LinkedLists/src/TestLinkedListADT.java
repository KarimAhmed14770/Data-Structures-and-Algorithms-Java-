import java.util.Optional;

class LinkedList {
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;//i will use this to keep tracking of number of elements in the list
    //so that i don't use a function o(n) for getting this number whenever i need

    LinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    LinkedList(int[] arr) {
        this.head = null;
        this.tail = null;
        for (int i = 0; i < arr.length; i++) {
            append(arr[i]);
        }
    }

    /*private methods*/
    /*this takes time complexity of O(n) and space complexity of o(n)*/

    private void recursiveDisplay(final Node head) {
        if (head != null) {
            recursiveDisplay(head.next);
            System.out.print(head.data + " ");
        }
    }

    /*time complexity O(n),space complexity O(n)*/
    private void recursiveReverse(Node r,Node p){
        if(p==null){
            head=r;
            return;
        }
        recursiveReverse(p,p.next);
        p.next=r;
        if(r==null){
            tail=p;
        }

    }


    /*public methods*/
    /*time complexity o(n), space complexity o(1)*/
    /*version of append without tail pointer
    public void append(int data) {
        Node x = new Node(data);
        if (head == null) {
            head = x;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = x;
        }
        size++;
    }*/

    /*using tail pointer with append logic, makes the append or insertion of last element o(1)
     * time complexity as well as o(1) space complexity*/
    public void append(int data) {
        Node x = new Node(data);
        if (head == null && tail == null) {
            head = x;
            tail = x;
        } else {
            tail.next = x;
            tail = x;
        }
        size++;
    }

    /*time complexity o(n), space complexity o(1)*/
    public void display() {
        Node current = head;
        System.out.println("List elements(" + size + "):");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /*time complexity o(n) space complexity o(n)*/
    public void displayReverse() {
        System.out.println("List Elements(" + size + "):");
        recursiveDisplay(head);
        System.out.println();
    }

    /*time complexity o(n) space complexity o(1)*/
    public int count() {
        return size;
    }

    /*time complexity o(n) space complexity o(1)*/
    public int sum() {
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    public int max() {
        if (head == null) {
            return -1; //to protect from empty list
        }
        int max = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    public int min() {
        if (head == null) {
            return -1; //to protect from empty list
        }
        int min = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }
    /*i am now making a function that searches for a node in the linked list,
    should this function return a node or what ?*/
    /*answer:In professional development, the answer depends on whether the function is for internal use
    (private) or for the user of the ADT (public).
    1. For the Public User: Return boolean or int
    If a user is using your LinkedList class, you generally should not return a Node object.
    Why? Returning a Node breaks Encapsulation. If the user gets a Node object,
    they could manually change node.next = null from outside your class,
    which would "break" your list without the class knowing.
    Better approach: Return the index where the element was found, or a simple boolean (true/false).

    2.For Internal Logic: Return Node
        If you are writing a helper method to be used by other methods inside the class
        (like delete or insert), then returning a Node is very useful.
    * */

    /*linear search algorithm: time complexity O(n), space complexity O(1)*/
    public int search(int key) {
        int index = 0;//the result will be the logical index of the node if found
        boolean isfound = false;
        Node current = head;
        while (current != null) {
            ;
            if (current.data == key) {
                isfound = true;
                break;
            }
            index++;
            current = current.next;
        }
        if (!isfound) {
            index = -1;
        }

        return index;
    }

    /*same as linear search but when it finds an element it makes it the new head
    so if we try to search for it again, it will take only 1 iteration or comparison
    this is good only if we search for the same element multiple times
    but however time complexity still o(n), space complexity o(1)
    here i will not return an index i will return a boolean, because if the element is found
    it is automatically at index 0
     */
    /*time complexity: O(n), space complexity O(1)*/
    public boolean searchModified(int key) {
        boolean isFound = false;
        if (head == null) {

        } else if (head.data == key) {
            isFound = true;
        } else {
            Node current = head.next;
            Node previous = head;
            while (current != null) {
                if (current.data == key) {
                    previous.next = current.next;
                    current.next = head;
                    head = current;
                    isFound = true;
                    break;
                }
                previous = current;
                current = current.next;
            }
        }

        return isFound;
    }

    /*time complexity: O(n), space complexity O(1)*/
    public void insert(int index, int value) {
        Node x = new Node(value);
        if (index < 0) {
            throw new LinkedListADTException("index can't be negative");
        } else if (index > size) {
            throw new LinkedListADTException("index can't be greater than number of elements");
        } else if (index == 0) {
            x.next = head;
            head = x;
            if (size == 0) {
                tail = x;
            }
        } else if (index == size) {
            append(value);
        } else {
            Node previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            x.next = previous.next;
            previous.next = x;
            size++;
        }

    }

    /*time complexity: O(n), space complexity O(1)*/
    public void insertInSorted(int value) {
        Node x = new Node(value);
        if (size == 0) {
            append(value);
            return;
        } else if (value <= head.data) {
            insert(0, value);
            return;
        } else {
            Node previous = head;

            while (previous.next != null) {
                if (value <= previous.next.data) {
                    x.next = previous.next;
                    previous.next = x;
                    size++;
                    return;
                }
                previous = previous.next;
            }


        }
        append(value);
    }

    /*time complexity: O(1), space complexity O(1)*/
    public void deleteHead() {
        if (head == null) {
            return;
        } else {
            Node next = head.next;
            head.next = null;
            head = next;
            size--;
        }
    }

    /*time complexity: O(n), space complexity O(1)*/
    public void delete(int index) throws LinkedListADTException {
        if (head == null) {
            return;
        } else if (index < 0) {
            throw new LinkedListADTException("negative index not allowed");
        } else if (index >= size) {
            throw new LinkedListADTException("index out of bound");
        } else if (index == 0) {
            deleteHead();
        } else {
            Node previous = head;
            Node current = head.next;
            for (int i = 0; i < index - 1; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
            current.next = null;
            if (index == size - 1) {
                tail = previous;
            }
            size--;

        }

    }

    /*time complexity: O(n), space complexity O(1)*/
    public boolean isSorted() {
        boolean isSorted = true;
        if (head == null || head.next == null) {
            return isSorted;
        }
        Node current = head;
        while (current.next != null) {
            if (!(current.data <= current.next.data)) {
                isSorted = false;
                break;
            }
            current = current.next;
        }
        return isSorted;
    }

    /*removes duplicates from a sorted list*/
    /*time complexity: O(n), space complexity O(1)*/
    public void removeDuplicatesSorted() {
        if (head == null || head.next == null) {
            return;
        } else {
            if (head.data == head.next.data) {
                deleteHead();
            }
            Node previous = head;
            Node current = head.next;
            while (current.next != null) {
                if (current.data == current.next.data) {
                    previous.next = current.next;
                    current.next = null;
                    current = previous.next;
                    size--;

                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    /*time complexity: O(n), space complexity O(1)*/
    public void reverse(){
        if(head==null ||head.next==null){
            return;
        }
        else{
            Node r=null;
            Node q=null;
            Node p=head;
            while(p!=null){
                r=q;
                q=p;
                p=p.next;

                q.next=r;
            }
            tail=head;
            head=q;

        }
    }

    public void reverseRecursively(){
        Node r=null;
        Node p=head;
        recursiveReverse(r,p);

    }

    /*complexity O(1), but its in place, any change in any list is change in the inplace list*/
    public void concatInPlace(LinkedList l2){
       this.size+=l2.size;
       this.tail.next=l2.head;
       this.tail=l2.tail;

    }

    /*time complexity O(n+m)*/
    public LinkedList concat(LinkedList l2){
        LinkedList concatenated=new LinkedList();
        Node current=head;
        while(current!=null){
            concatenated.append(current.data);
            current=current.next;
        }
        current=l2.head;
        while(current!=null){
            concatenated.append(current.data);
            current=current.next;
        }
        return concatenated;
    }

    /*time complexity O(n+m), takes two sorted lists and merge them in a sorted way
    * space complexity O(n+m) we created a new list with the size of n+m
    * */
    public LinkedList merge(LinkedList l2){
        LinkedList merged=new LinkedList();
        Node firstCurrent=this.head;
        Node secondCurrent=l2.head;

        while(firstCurrent!=null && secondCurrent!=null){
            if(firstCurrent.data<=secondCurrent.data){
                merged.append(firstCurrent.data);
                firstCurrent=firstCurrent.next;
            }
            else{
                merged.append(secondCurrent.data);
                secondCurrent=secondCurrent.next;
            }

        }
        while(firstCurrent!=null){
            merged.append(firstCurrent.data);
            firstCurrent=firstCurrent.next;
        }
        while (secondCurrent!=null){
            merged.append(secondCurrent.data);
            secondCurrent=secondCurrent.next;
        }
        return merged;
    }

    /*time complexity O(n+m), space com[plexity O(1) we didn't create new list, we used available space
    of both lists
     */
    public void mergeInPlace(LinkedList l2) {
        Node first=this.head;
        Node second=l2.head;
        Node p=this.head;

        if(l2.head==null){
            return;
        }
        if(this.head==null){
                this.head=l2.head;
                this.tail=l2.tail;
                this.size=l2.size;
                return;
        }


        if(first.data<=second.data){
            //the head is correct,p points to the head
            this.tail=first;//make the tail point to the head
            first=first.next;//move first pointer to the next element in the first list
            tail.next=null;//make the first element point to null


        }
        else{
            this.head=second;//making the head of the list as the head of the second list
            this.tail=second;//making the tail also points at the first element of the second list
            second=second.next;//making second pointer points to the next element in the second list
            this.tail.next=null;
        }
        size=1;//now the new list has one element
        p=this.tail;//making the p pointer point to the current element in the new list
        while(first!=null &&second!=null){
            if(first.data<=second.data){
                this.tail=first;//making the tail point to the element of the first list
                first=first.next;//moving the first pointer to the next element in the first list

            }
            else{
                this.tail=second;//making the tail point to the element of the second list
                second=second.next;//moving the second pointer to the next element in the second list
            }
            p.next=this.tail;//making the current element in the new list points to the new tail
            p=this.tail;//now the tail is the current element in the new list
            this.tail.next=null;//making tail point to null
            size++;//increasing size by 1
        }
        while(first!=null){
            this.tail=first;
            first=first.next;
            p.next=this.tail;//making the current element in the new list points to the new tail
            p=this.tail;//now the tail is the current element in the new list
            this.tail.next=null;//making tail point to null
            size++;//increasing size by 1
        }
        while(second!=null){
            this.tail=second;
            second=second.next;
            p.next=this.tail;//making the current element in the new list points to the new tail
            p=this.tail;//now the tail is the current element in the new list
            this.tail.next=null;//making tail point to null
            size++;//increasing size by 1
        }
    }

    public boolean cycleCheck(){
        boolean isCycled=false;
        if(this.head==null || this.head.next==null){
            return isCycled;
        }
        Node fast=this.head;//will move by 2 nodes
        Node slow=this.head;//will move by 1 node

        while(fast!=null &&fast.next!=null){//the second condition because if fast.next is null theline
            //having fast.next.next would crash
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                isCycled=true;
                break;
            }
        }


        return isCycled;

    }

    public int cycleStart() {
        int cyclePoint = 0;
        boolean isCycle=false;
        if (this.head == null || this.head.next == null) {
            return -1;//there is no cycle
        }
        Node fast = this.head;//will move by 2 nodes
        Node slow = this.head;//will move by 1 node

        while (fast != null && fast.next != null) {//the second condition because if fast.next is null theline
            //having fast.next.next would crash
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle=true;
                break;
            }
        }
        if(isCycle) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }
        else{
            return -1;
        }
    }

    /*i am doing this function to check the working of the checkCycle function */
    public void makeCircular(){
        this.tail.next=head;
    }
}

class CircularLinkedList{
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
    public void insert(int index,int data) throws LinkedListADTException{
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

class DoublyLinkedList{
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
    public void insert(int index,int data)throws LinkedListADTException{
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






class LinkedListADTException extends RuntimeException{
    public LinkedListADTException (String message) {
        super(message);
    }
}

public class TestLinkedListADT {


    public static void main(String[] Args){
        try {

           DoublyLinkedList l1=new DoublyLinkedList();
           l1.append(10);
           l1.append(20);
           l1.append(30);
           l1.insertAtBeginning(0);
           l1.insert(1,1);
           l1.insert(3,3);
           l1.insert(6,60);
           l1.insert(5,50);

           l1.display();

           l1.deleteFirst();
           l1.deleteFirst();
           l1.deleteLast();
           l1.display();

           l1.insert(0,0);
           l1.insert(3,30);
           l1.insert(4,40);
           l1.insert(6,60);
           l1.delete(2);
           l1.delete(4);
           l1.deleteLast();
           l1.deleteLast();
            l1.insert(2,20);
            l1.insert(5,50);
           l1.display();

           l1.delete(1);
           l1.delete(4);
           l1.display();
        }
        catch(LinkedListADTException e){
            System.out.println(e);
        }



    }
}


/*testing display,append,insert,search,count,sum,max,min:
        LinkedList l1 = new LinkedList();
            l1.append(3);
            l1.append(4);
            l1.append(5);
            l1.append(6);
            l1.append(10);
            l1.append(25);
            l1.append(1);
            l1.append(12);
            l1.append(50);
            l1.append(43);
            l1.display();
            System.out.println();
            l1.displayReverse();

            System.out.println();
            System.out.println(l1.count());
            System.out.println(l1.sum());
            System.out.println(l1.max());
            System.out.println(l1.min());
            System.out.println(l1.search(43));

            System.out.println();
            System.out.println();
            System.out.println(l1.searchModified(50));
            System.out.println(l1.searchModified(50));
            System.out.println(l1.searchModified(53));
            System.out.println(l1.searchModified(25));

            l1.display();
            System.out.println();
            System.out.println();
            l1.insert(0, 10);
            l1.insert(3, 55);
            l1.insert(l1.count(),60);
            //l1.insert(-1, 55); will raise exception
            //l1.insert(20, 55); will raise exception
            l1.display();

            LinkedList l2 = new LinkedList();
            l2.insert(0,10);
            l2.display();
        }
        catch(LinkedListADTException e){
            System.out.println(e);
        }


delete test:
            LinkedList l1 = new LinkedList();
            int[] arr={1,2,3,4,5,6,7,89,10};
            LinkedList l2=new LinkedList(arr);
            l2.display();
            l1.insertInSorted(3);
            l1.append(4);
            l1.append(8);
            l1.append(9);
            l1.append(10);
            l1.append(13);
            l1.append(20);
            l1.append(25);
            l1.append(26);
            l1.insertInSorted(5);
            l1.insertInSorted(2);
            l1.insertInSorted(30);
            l1.insertInSorted(30);
            l1.insertInSorted(30);
            l1.insertInSorted(2);
            l1.append(35);
            l1.display();
            l1.deleteHead();
            l1.deleteHead();
            l1.display();
            l1.delete(3);
            l1.delete(6);
            l1.delete(0);
            l1.display();
            l1.delete(10);
            l1.display();

duplicates and reversing test:
            int[] arr={1,1,2,3,4,4,4,4,5,6,7,8,9,10,10,10};
            int[] arr2={1,2,3,4,5,6,7,12,9,10};
            LinkedList l1=new LinkedList(arr);
            LinkedList l2 = new LinkedList(arr2);
            l1.removeDuplicatesSorted();
            l1.display();
            l1.append(12);
            l1.display();
            l1.reverse();
            l1.display();
            l1.reverseRecursively();
            l1.display();

merge,mergeInPlace,cycle,cycleStart test:
            int[] arr={1,2,13,24,35};
            int[] arr2={6,17,28,29,40};
            LinkedList l1=new LinkedList(arr);
            LinkedList l2 = new LinkedList(arr2);
            LinkedList l3=l1.concat(l2);
            LinkedList l4=l1.merge(l2);

            l3.display();
            l4.display();

            l1.mergeInPlace(l2);
            l1.display();
            System.out.println(l1.cycleCheck());
            l1.makeCircular();
            System.out.println(l1.cycleCheck());
            System.out.println(l1.cycleStart());

circular linked list test:
            CircularLinkedList l1=new CircularLinkedList(10);
            l1.display();
            l1.insertAtBeginning(1);
            l1.insertAtBeginning(2);
            l1.insertAtBeginning(3);
            l1.display();
            l1.insert(0,1);
            l1.insert(5,0);
            l1.insert(2,7);
            l1.display();
            l1.deleteFirst();
            l1.deleteFirst();
            l1.display();
            l1.delete(2);
            l1.delete(0);
            l1.display();
            l1.insertAtBeginning(1);
            l1.insert(2,3);
            l1.display();
            l1.delete(4);
            l1.display();
            l1.delete(0);

*
 */