package DataStructuresAndAlgorithms.LinkedLists;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;//i will use this to keep tracking of number of elements in the list
    //so that i don't use a function o(n) for getting this number whenever i need

    public LinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public LinkedList(T[] arr) {
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
    public void append(T data) {
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
    /*
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
    */

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
    public int indexOf(T key) {
        int index = 0;//the result will be the logical index of the node if found
        boolean isfound = false;
        Node current = head;
        while (current != null) {
            int cmp=((Comparable<T>)current.data).compareTo(key);
            if (cmp==0) {
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

    public T find(T key) {
        Node current = head;
        while (current != null) {
            int cmp=((Comparable<T>)current.data).compareTo(key);
            if (cmp==0) {
                return current.data;
            }
            current = current.next;
        }
        return null;

    }

    public void update(T key) {
        int index = 0;//the result will be the logical index of the node if found
        Node current = head;
        while (current != null) {
            int cmp=((Comparable<T>)current.data).compareTo(key);
            if (cmp==0) {
                current.data=key;
                break;
            }
            index++;
            current = current.next;
        }

    }

    /*same as linear search but when it finds an element it makes it the new head
    so if we try to search for it again, it will take only 1 iteration or comparison
    this is good only if we search for the same element multiple times
    but however time complexity still o(n), space complexity o(1)
    here i will not return an index i will return a boolean, because if the element is found
    it is automatically at index 0
     */
    /*time complexity: O(n), space complexity O(1)*/
    public boolean searchModified(T key) {
        boolean isFound = false;
        if (head == null) {

        } else if (((Comparable<T>)head.data).compareTo(key)== 0) {
            isFound = true;
        } else {
            Node current = head.next;
            Node previous = head;
            while (current != null) {
                int cmp = ((Comparable<T>)current.data).compareTo(key);
                if (cmp==0) {
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
    public void insert(int index, T value) {
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
            size++;
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
    public void insertInSorted(T value) {
        Node x = new Node(value);
        if (size == 0) {
            append(value);
            return;
        } else if (((Comparable<T>)value).compareTo(head.data) <=0 ) {
            insert(0, value);
            size++;
            return;
        } else {
            Node previous = head;

            while (previous.next != null) {
                if (((Comparable<T>)value).compareTo(previous.next.data) <=0 ) {
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
            if(next==null){
                tail=null;
            }
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
            if (!(((Comparable<T>)current.data).compareTo(current.next.data) <=0 )) {
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
    public void concatInPlace(LinkedList<T> l2){
        this.size+=l2.size;
        this.tail.next=l2.head;
        this.tail=l2.tail;

    }

    /*time complexity O(n+m)*/
    public LinkedList<T> concat(LinkedList<T> l2){
        LinkedList<T> concatenated=new LinkedList<>();
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
    public LinkedList<T> merge(LinkedList<T> l2){
        LinkedList<T> merged=new LinkedList<>();
        Node firstCurrent=this.head;
        Node secondCurrent=l2.head;

        while(firstCurrent!=null && secondCurrent!=null){
            if(((Comparable<T>)firstCurrent.data).compareTo(secondCurrent.data)<=0){
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
    public void mergeInPlace(LinkedList<T> l2) {
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


        if(((Comparable<T>)first.data).compareTo(second.data)<=0){
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
            if(((Comparable<T>)first.data).compareTo(second.data)<=0){
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

    public T cycleStart() {
        int cyclePoint = 0;
        boolean isCycle=false;
        if (this.head == null || this.head.next == null) {
            return null;//there is no cycle
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
            return null;
        }
    }

    /*I am doing this function to check the working of the checkCycle function */
    public void makeCircular(){
        this.tail.next=head;
    }

    public T[] toArray(){
        Node current=head;
        T[] arr=(T[])new Object[size];
        int i=0;
        while(current!=null){
            arr[i]=current.data;
            i++;
            current=current.next;
        }
        return arr;
    }

    /*converting the class to iterable*/
    @Override
    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node current=head;
        @Override
        public boolean hasNext(){
            return current!=null;//checks if the array has a next element
        }
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            // 1. Grab the data from the current node
            T data = current.data;

            // 2. MOVE the pointer to the next node in the chain
            current = current.next;

            // 3. Return the data we grabbed
            return data;
        }

    }
}
