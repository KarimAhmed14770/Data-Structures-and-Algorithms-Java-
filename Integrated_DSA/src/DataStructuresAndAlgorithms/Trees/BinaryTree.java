package DataStructuresAndAlgorithms.Trees;

import DataStructuresAndAlgorithms.Queues.LinearQueueLL;
import DataStructuresAndAlgorithms.Stack.StackLinkedList;

import java.util.Scanner;

public class BinaryTree {
    private LinearQueueLL<Node> queue=new LinearQueueLL();
    private Scanner sc=new Scanner(System.in);

    private class Node{
        int data;
        Node leftChild;
        Node rightChild;

        Node(int data){
            this.data=data;
            leftChild=null;
            rightChild=null;
        }
    }

    private Node root;
    public BinaryTree(int rootData){
        root=new Node(rootData);
        queue.enqueue(root);

    }

    /*Helper functions*/

    /*time complexity O(n)
    * if we have n Nodes we will have n+1 null pointers so the total calls will be 2n+1
    * so time complexity is O(N)
    *
    * space complexity O(h):
    * he "Stack Frame" Breakdown
    In your traversal (let's say preOrderDisplay), the stack grows like this: if we have height=2
    Frame 1: preOrder(root) (Level 0)
    Frame 2: preOrder(leftChild) (Level 1)
    Frame 3: preOrder(leftChild's leftChild) (Level 2 - Your deepest node)
    Frame 4: preOrder(null) (The method call on the null child of the deepest node)
    Even though your "data" ends at level 2, the recursive call still happens for the null pointer.
    The JVM creates a frame for that call, hits your if (t != null) check, finds it false, and
    then pops it.
    Max Stack Frames = Height + 2
    * */
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

    private int countNodes(Node t){
        int x;
        int y;
        if(t!=null){
            x=countNodes(t.leftChild);
            y=countNodes(t.rightChild);
            return x+y+1;

        }

        return 0;
    }

    private int countLeafNodes(Node t){
        int x;
        int y;
        if(t!=null){
            x=countLeafNodes(t.leftChild);
            y=countLeafNodes(t.rightChild);
            if(t.leftChild==null && t.rightChild==null) {
                return x + y + 1;
            }
            else{
                return x+y;
            }

        }

        return 0;
    }

    /*public methods*/

    public void addNodes() {
        int input;
        Node p;//will point on the current node that got out of queue
        Node t;//will be used to create nodes and point to them temporarily
        while(!queue.isEmpty()){
            p=queue.dequeue();
            System.out.print("Enter Left child Of ("+p.data+"): ");//-1 means no left child
            input =sc.nextInt();
            if(input==-1){
                p.leftChild=null;
            }
            else{
                t=new Node(input);
                p.leftChild=t;
                queue.enqueue(t);
            }
            System.out.print("Enter Right child Of ("+p.data+"): ");//-1 means no left child
            input =sc.nextInt();
            if(input==-1){
                p.rightChild=null;
            }
            else{
                t=new Node(input);
                p.rightChild=t;
                queue.enqueue(t);
            }

        }
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

    /*time complexity: O(n) 2n+1, space complexity o(h) h+2*/
    /*this is much safer than recursive procedure due to
    * on high-performance Java applications (like those built with Spring Boot),
    * recursion is often avoided for very large datasets (e.g., a tree with 1 million nodes).
    * Your iterative method is safer because:
    Heap vs. Stack: Your stack lives in the Heap (where there is plenty of room).
    The recursive calls live in the Thread Stack (which is usually limited to 1MB per thread).

    Performance: You avoid the overhead of multiple function calls and frame creations.
    * */
    public void preOrderDisplayIterative(){
        StackLinkedList<Node> stack=new StackLinkedList<>();
        Node t=root;//will be used to traverse and pointing to nodes

        while(t!=null || !stack.isEmpty()){
            if(t!=null){
                System.out.print(t.data+" ");
                stack.push(t);
                t=t.leftChild;
            }
            else
            {
             t=stack.pop();
             t=t.rightChild;
            }
        }
    }

    public void inOrderDisplayIterative(){
        StackLinkedList<Node> stack=new StackLinkedList<>();
        Node t=root;//will be used to traverse and pointing to nodes
        while(t!=null || !stack.isEmpty()){
            if(t!=null){
                stack.push(t);
                t=t.leftChild;
            }
            else
            {
                t=stack.pop();
                System.out.print(t.data+" ");
                t=t.rightChild;
            }
        }
    }

    /*
    Time Complexity: O(n) You still visit every node exactly once.
    Space Complexity: O(w)Here, w is the maximum width of the tree.In a perfect binary tree,
    the last level contains half of all nodes (n/2). This means your queue might need to hold
    O(n) nodes in the worst case, unlike the O(h) space used by your stack-based DFS
    (Pre/In/Post-order).
     */
    public void levelOrderDisplay(){
        if(root==null){return;}
        LinearQueueLL<Node> q=new LinearQueueLL<>();
        Node t=root;
        q.enqueue(t);
        while(!q.isEmpty()){
            t=q.dequeue();
            if(t.leftChild!=null){
                q.enqueue(t.leftChild);
            }
            if(t.rightChild!=null){
                q.enqueue(t.rightChild);
            }
            System.out.print(t.data+" ");



        }
    }

    public int countNodes(){
        return countNodes(root);
    }

    public int countLeafNodes(){
        return countLeafNodes(root);
    }







}


/*testing


public static void main(String[] Args){
       BinaryTree bt=new BinaryTree(8);
       bt.addNodes();

       bt.preOrderDisplay();
       System.out.println("\n");
       bt.preOrderDisplayIterative();
       System.out.println("\n");
       bt.inOrderDisplay();
       System.out.println("\n");
       bt.inOrderDisplayIterative();
       System.out.println("\n");
       bt.postOrderDisplay();
       System.out.println("\n");
       bt.levelOrderDisplay();
       System.out.println("\n");

       System.out.println(bt.countNodes());

       System.out.println("\n");

       System.out.println(bt.countLeafNodes());

    }

 */