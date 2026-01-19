

class StackArr{
    private int[] arr;//array for storing the data
    private int top;//to keep track of the top element,and the number of elements

    StackArr(int length){
        arr=new int[length];
        top=-1;//when the stack is empty top value is -1,because 0 points to the element at index 0
    }

    /*time complexity O(N)*/
    public void display(){
        if(top==-1){
            return;
        }
        else{
            int counter=top;
            while(counter>=0){
                System.out.println(arr[counter]);
                counter--;
            }
        }
    }
    /*time complexity O(1)*/
    public void push(int x)throws RuntimeException{
        if(top==arr.length-1){
            throw new RuntimeException("stack is full");
        }
        else{
            top++;
            arr[top]=x;
        }
    }

    /*time complexity O(1)*/
    public int pop() throws RuntimeException{
        if(top==-1){
            throw new RuntimeException("stack is empty");
        }
        else{
            top--;
            return arr[top+1];
        }
    }

    /*time complexity O(1)*/
    public int peek(int index) throws RuntimeException{
        if(index<0 ||index >top+1 ||top==-1){
            throw new RuntimeException("out of index");
        }
        else{
            index=top+1-index;
            return arr[index];
        }
    }

    /*time complexity O(1)*/
    public int stackTop() throws RuntimeException{
        if(top==-1){
            throw new RuntimeException("stack is empty");
        }
        else{
            return arr[top];
        }
    }

    /*time complexity O(1)*/
    public boolean isEmpty(){
        return (top==-1);
    }

    /*time complexity O(1)*/
    public boolean isFull(){
       return (top==arr.length-1);
    }


}

class StackList{
    private class Node{
        int data;
        Node next;

        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    private Node top;//will point to the top element in the stack
    private int size;//will carry the number of elements in the stack

    StackList(){
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
    public void push(int data){
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
    public int pop()throws RuntimeException{
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
    public int peek(int index)throws RuntimeException{
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
    public int stackTop()throws RuntimeException{
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

class StackStr{
    private class Node{
        char data;
        Node next;

        Node(char data){
            this.data=data;
            this.next=null;
        }
    }
    private Node top;//will point to the top element in the stack
    private int size;//will carry the number of elements in the stack

    StackStr(){
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
    public void push(char data){
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
    public char pop()throws RuntimeException{
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
    public char peek(int index)throws RuntimeException{
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
    public char stackTop()throws RuntimeException{
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






public class StackTest {

    /*challenge 1:Parenthesis matching*/
    static public boolean parenthesisMatching(String Expression){
        //note u can replace the multiple else if with a method, so u don't repeat ur code
        StackStr test=new StackStr();
        for(int i=0;i<Expression.length();i++){
            if(Expression.charAt(i)=='(' ||Expression.charAt(i)=='['||Expression.charAt(i)=='{'){
                test.push(Expression.charAt(i));
            }
            else if(Expression.charAt(i)==')'){
                if(test.isEmpty()){
                    return false;
                }
                else if(test.peek(1)=='('){
                    test.pop();
                }
                else{
                    return false;
                }
            }
            else if(Expression.charAt(i)==']'){
                if(test.isEmpty()){
                    return false;
                }
                else if(test.peek(1)=='['){
                    test.pop();
                }
                else{
                    return false;
                }
            }
            else if(Expression.charAt(i)=='}'){
                if(test.isEmpty()){
                    return false;
                }
                else if(test.peek(1)=='{'){
                    test.pop();
                }
                else{
                    return false;
                }
            }
        }
        return test.isEmpty();
    }
    public static void main(String[] Args) {
        try {

        }
        catch (RuntimeException e){
            System.out.println(e);
        }
    }

}


/*array-based stack test
*                   try {
            StackArr stack1 = new StackArr(10);
            System.out.println(stack1.isEmpty());
            //stack1.peek(2); raise exception
            //stack1.pop(); raise exception
            stack1.push(9);
            stack1.push(8);
            stack1.push(7);
            stack1.push(6);
            stack1.display();
            System.out.println();
            System.out.println(stack1.stackTop());
            System.out.println(stack1.peek(2));
            System.out.println(stack1.peek(4));
            //System.out.println(stack1.peek(5)); raise exception
            System.out.println();
            stack1.push(5);
            stack1.push(4);
            stack1.push(3);
            stack1.push(2);
            stack1.push(1);
            stack1.push(0);
            stack1.display();
            //stack1.push(10); raise exception
            System.out.println(stack1.isFull());

            stack1.pop();
            stack1.pop();
            stack1.pop();
            stack1.display();
        }
        catch (RuntimeException e){
            System.out.println(e);
        }
*
*
*
* linked-list based stack test:
*           StackList stack1=new StackList();
            //stack1.peek(2); //raise exception
            //stack1.pop(); //raise exception
            stack1.push(9);
            stack1.push(8);
            stack1.push(7);
            stack1.push(6);
            stack1.display();
            System.out.println();
            System.out.println(stack1.stackTop());
            System.out.println(stack1.peek(2));
            System.out.println(stack1.peek(4));
            //System.out.println(stack1.peek(5)); raise exception
            System.out.println();
            stack1.push(5);
            stack1.push(4);
            stack1.push(3);
            stack1.push(2);
            stack1.push(1);
            stack1.push(0);
            stack1.display();
            System.out.println();
            System.out.println();
            System.out.println(stack1.pop());
            System.out.println(stack1.pop());
            System.out.println(stack1.pop());
            System.out.println(stack1.pop());
            System.out.println();
            System.out.println();

            stack1.display();
            //stack1.push(10); raise exception

*
*
* parenthesis matching test:
*           System.out.println(parenthesisMatching("((a+b)*(c-d))"));
            System.out.println(parenthesisMatching("((a+b*)(c-d))"));//it will pass this
            System.out.println(parenthesisMatching("(((a+b)*(c-d))"));
            System.out.println(parenthesisMatching("(((a+b)*(c-d))))"));

            System.out.println(parenthesisMatching("{[(a+b)-(c*d)]+[(x*y)*(x^2)]}"));
            System.out.println(parenthesisMatching("{[(a+b)-(c*d)]+[(x*y)*(x^2)]})"));
            System.out.println(parenthesisMatching("{[(a+b)-(c*d)]+[(x*y)*(x^2)]]"));

*
* */