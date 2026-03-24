package DataStructuresAndAlgorithms.Trees;

import DataStructuresAndAlgorithms.Stack.StackLinkedList;

public class BinarySearchTree {
    private class Node {
        int data;
        Node leftChild;
        Node rightChild;

        Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(int rootData) {
        root = new Node(rootData);
        root.leftChild = null;
        root.rightChild = null;
    }


    /*private helper functions*/

    private Node insertRecursive(int key,Node t) {
        if (root == null) {
            root = new Node(key);
            root.leftChild = null;
            root.rightChild = null;
        } else {
            Node x = new Node(key);
            if(t==null){
                return new Node(key);
            }
            if(key<t.data){

                t.leftChild=insertRecursive(key,t.leftChild);
            }
            else{
                t.rightChild=insertRecursive(key,t.rightChild);
            }
        }
        return t;

    }


    private void preOrderDisplay(Node t){
        if(t!=null){
            System.out.print(t.data+" ");
            preOrderDisplay(t.leftChild);
            preOrderDisplay(t.rightChild);
        }
    }

    private void inOrderDisplay(Node t){
        if(t!=null){
            inOrderDisplay(t.leftChild);
            System.out.print(t.data+" ");
            inOrderDisplay(t.rightChild);
        }
    }

    private void postOrderDisplay(Node t){
        if(t!=null){

            postOrderDisplay(t.leftChild);
            postOrderDisplay(t.rightChild);
            System.out.print(t.data+" ");
        }
    }

    private Node recursiveDelete(Node t,int key){
        if(t==null)return null;
        if(key<t.data)
            t.leftChild=recursiveDelete(t.leftChild,key);
        else if(key>t.data)
            t.rightChild=recursiveDelete(t.rightChild,key);
        else{
            //node is found
            //node is either leaf or having 1 child or having 2 childs
            /*this solves leaf and 1 child problem*/
            if(t.rightChild==null)
                return t.leftChild;
            if(t.leftChild==null)
                return t.rightChild;

            //the third case where t has both childs, get the successor then delete it
            Node successor =t.rightChild;
            while(successor.leftChild!=null){
                successor=successor.leftChild;
            }
            t.data=successor.data;
            t.rightChild=recursiveDelete(t.rightChild, successor.data);
        }
        return t;

    }

    /*public user functions*/
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            root.leftChild = null;
            root.rightChild = null;
        } else {
            Node t = root;//t will be used for searching
            Node r = null;//r will be trailing t by 1, so that if element isn't found we have the insertion address
            Node x = new Node(key);
            while (t != null) {
                if (t.data == key) {
                    return;
                } else if (key < t.data) {
                    r = t;
                    t = t.leftChild;
                } else {
                    r = t;
                    t = t.rightChild;
                }
            }
            if (key > r.data) {
                r.rightChild = x;
            } else {
                r.leftChild = x;
            }
        }

    }

    public void insertRecursive(int key) {
        insertRecursive(key,root);
    }

    public void preOrderDisplay(){
        preOrderDisplay(root);
    }

    public void inOrderDisplay(){
        inOrderDisplay(root);
    }

    public void postOrderDisplay(){
        postOrderDisplay(root);
    }

    /*time complexity: average case O(log(n)) worst case o(h) which is o(n) if tree is skewed
    space complexity o(1)
    * */
    public boolean contains(int key){
        Node t=root;

        while(t!=null){
            if(t.data==key){
                return true;
            }
            else if(key<t.data){
                t=t.leftChild;
            }
            else{
                t=t.rightChild;
            }
        }
        return false;
    }

    public void delete(int key) {
        Node t = root;//will be used to traverse the bst and point to the node to be deleted
        Node previous = null;//will be used to point to the node just before the deleted node
        boolean isRight=false;//will be used to know if t is a right or left child of previous
        //first loop to search for the node
        while (t!=null && t.data!=key){//either find the node and exit, or don't find it and exit
            previous=t;
            if(key<t.data){
                isRight=false;
                t=t.leftChild;
            }
            else{
                isRight=true;
                t=t.rightChild;
            }
        }
        if(t==null)return;//node is not found

        //second: delete the node

        //case 1: the node is a leaf node
        if(t.leftChild==null &&t.rightChild==null){
            if(previous==null){//this means that t is root and its the only node in bst
                root=null;
            }
            else if(isRight){
                previous.rightChild=null;
            }
            else{
                previous.leftChild=null;
            }
        }
        //case 2: it only has 1 child
        else if(t.leftChild==null||t.rightChild==null){
            if(t.leftChild==null){//t has only a right child
                if(previous==null){//this means t is root
                    root=t.rightChild;
                }
                else{
                    if(isRight){//t is the right child of previous
                        previous.rightChild=t.rightChild;

                    }else{
                        previous.leftChild=t.rightChild;
                    }
                }
            }
            else{//it has only a left child
                if(previous==null){//this means t is root
                    root=t.leftChild;
                }
                else{
                    if(isRight){//t is the right child of previous
                        previous.rightChild=t.leftChild;

                    }else{
                        previous.leftChild=t.leftChild;
                    }
                }
            }
        }else {//t has both children, it is time for predecessor
            Node predecessor = t.leftChild;
            previous = t;
            while (predecessor.rightChild != null) {
                previous = predecessor;//this will be the node previous to the predecessor
                predecessor = predecessor.rightChild;
            }
            t.data = predecessor.data;
            if (previous == t) {
                previous.leftChild = predecessor.leftChild;
            } else {
                previous.rightChild = predecessor.leftChild;
            }
        }


    }

    public void recursiveDelete(int key){
        recursiveDelete(root,key);
    }

    /*this function is O(n), the normal insertion function
    * the iterative insert: takes o(n) for searching for each element, if we put it in a loop of preorder
    * generation it becomes o(n^2), same for the recursive insert
    * so this preorder generation function is best
    *  */
    public void generateFromPreOrder(int[] preOrder){
        StackLinkedList<Node> st=new StackLinkedList<>();
        if(preOrder.length==0)return;
        root=new Node(preOrder[0]);
        Node p=root;//points to the current node
        Node t=root;//points to the next node to be created
        for(int i=1;i<preOrder.length;i++){
            t=new Node(preOrder[i]);
            if(preOrder[i]<p.data){
               p.leftChild=t;
               st.push(p);
               p=t;
            }
            else if(preOrder[i]>p.data){
                if(st.isEmpty()){
                    p.rightChild=t;
                    p=t;
                }
                else if(preOrder[i]<st.stackTop().data){
                    p.rightChild=t;
                    p=t;
                }
                else if( preOrder[i]>st.stackTop().data){
                    while(preOrder[i]>st.stackTop().data) {
                            p = st.pop();
                            if(st.isEmpty()){
                                break;
                            }
                    }
                    p.rightChild=t;
                    p=t;


                }
            }
        }
    }

    }


    /*BST TEST
    public static void main(String[] Args){
        BinarySearchTree bst=new BinarySearchTree(30);
        bst.insertRecursive(15);
        bst.insertRecursive(50);
        bst.insertRecursive(40);
        bst.insertRecursive(10);
        bst.insertRecursive(20);
        bst.insertRecursive(60);

        bst.preOrderDisplay();
        System.out.println();
        System.out.println(bst.contains(20));
        System.out.println(bst.contains(22));

        bst.delete(40);
        bst.delete(15);
        bst.delete(60);
        bst.delete(30);
        bst.preOrderDisplay();
        System.out.println();

        int[] arr={30,20,10,15,25,40,50,45};
        bst.generateFromPreOrder(arr);
        bst.preOrderDisplay();


    }
     */
