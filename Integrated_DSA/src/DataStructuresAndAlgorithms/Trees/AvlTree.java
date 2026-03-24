package DataStructuresAndAlgorithms.Trees;

public class AvlTree {
    private class Node{
        int data;
        int height;
        Node leftChild;
        Node rightChild;

        Node(int data){
            this.data=data;
            this.height=1;
        }
    }
    private Node root;

    public AvlTree(){
        root=null;
    }
    public AvlTree(int rootData){
        root=new Node(rootData);
    }


    /*private methods*/
    private Node recInsert(Node t,int key){
        //phase 1 : insertion phase
        if(t==null){
            return new Node(key);
        }
        else if(key==t.data){
            return t;
        }
        else if(key<t.data){
            t.leftChild=recInsert(t.leftChild,key);
        }
        else{
            t.rightChild=recInsert(t.rightChild,key);
        }
        //here now the node is inserted and we are going from the bottom up back to the root through
        // the inserted node
        //so first we calculate the height of each node
        t.height=updateHeight(t);

        //calculate the balance factor
        int balanceFactor=getBalance(t);
        if(balanceFactor==2){
            if(key<t.leftChild.data){
                return LLRotation(t);
            }
            else{
                return LRRotation(t);
            }
        }
        else if(balanceFactor==-2){
            if(key>t.rightChild.data){
                return RRRotation(t);
            }
            else{
                return RLRotation(t);
            }
        }
        return t;
    }

    private int getHeight(Node t){
        if(t==null){
            return 0;
        }
        else{
            return t.height;
        }
    }
    private int updateHeight(Node t){
        int leftHeight=getHeight(t.leftChild);
        int rightHeight=getHeight(t.rightChild);
        return leftHeight>rightHeight?leftHeight+1:rightHeight+1;
    }
    private int getBalance(Node t){
        if(t==null){
            return 0;
        }
        else{
            return getHeight(t.leftChild)-getHeight(t.rightChild);
        }
    }
    private Node LLRotation(Node t){
        //we don't need a previous pointer because the parent is already expecting a pointer
        //we are doing it recursively
        //we only operate on three nodes
        //t,t.leftChild,t.leftChild.leftChild

        Node newRoot=t.leftChild;
        Node temp=newRoot.rightChild;//to keep the child of the new root
        newRoot.rightChild=t;
        t.leftChild=temp;
        //now we have adjusted pointers only, we should adjust heights too after changes
        //update height
        t.height=updateHeight(t);
        newRoot.height=updateHeight(newRoot);

        return newRoot;

    }

    private Node RRRotation(Node t){
        //we don't need a previous pointer because the parent is already expecting a pointer
        //we are doing it recursively
        //we only operate on three nodes
        //t,t.leftChild,t.leftChild.leftChild

        Node newRoot=t.rightChild;
        Node temp=newRoot.leftChild;//to keep the child of the new root
        newRoot.leftChild=t;
        t.rightChild=temp;
        //now we have adjusted pointers only, we should adjust heights too after changes
        //update height
        t.height=updateHeight(t);
        newRoot.height=updateHeight(newRoot);

        return newRoot;
    }

    private Node LRRotation(Node t){
        t.leftChild=RRRotation(t.leftChild);
        return LLRotation(t);
    }

    private Node RLRotation(Node t){
        t.rightChild=LLRotation(t.rightChild);
        return RRRotation(t);
    }

    private void preOrderDisplay(Node t){
        if(t!=null){
            System.out.print(t.data+" ");
            preOrderDisplay(t.leftChild);
            preOrderDisplay(t.rightChild);
        }
    }

    private Node delete(Node t,int key){
        if(t==null) return null;

        if(key<t.data){
            t.leftChild=delete(t.leftChild,key);
        }
        else if(key>t.data){
            t.rightChild=delete(t.rightChild,key);
        }
        else{//node is found
            //it either has 1 child or 2 children or 0 child
            /*solution for 0 child or 1 child*/
            if(t.rightChild==null)return t.leftChild;
            if(t.leftChild==null)return t.rightChild;

            Node successor=t.rightChild;
            while(successor.leftChild!=null){
                successor=successor.leftChild;
            }
            t.data=successor.data;
            t.rightChild=delete(t.rightChild,successor.data);

        }
        /*height update*/
        t.height=updateHeight(t);

        /*balance factor calculation and performing rotations*/
        int balanceFactor=getBalance(t);
        if(balanceFactor==2){
            if(getBalance(t.leftChild)>=0){
                return LLRotation(t);
            }
            else{
                return LRRotation(t);
            }
        }
        else if(balanceFactor==-2){
            if(getBalance(t.rightChild)<=0){
                return RRRotation(t);
            }
            else{
                return RLRotation(t);
            }
        }
        return t;

    }


    public void insert(int key){
        //phase 1 inserting the node as insertion in BST
        root=recInsert(root,key);
    }

    public void preOrderDisplay(){
        preOrderDisplay(root);
    }

    public void delete(int key){
        root=delete(root,key);
    }

}



/*avl tree test
*
* public static void main(String[] Args){
        AvlTree avl=new AvlTree();
        int[] nodes = {10, 20, 30, 40, 50, 25};

        for (int n : nodes) {
            avl.insert(n);
        }
        avl.preOrderDisplay();
        System.out.println();
        avl.insert(26);
        avl.preOrderDisplay();
        System.out.println();
        avl.insert(27);
        avl.preOrderDisplay();
        System.out.println();
    }
* */