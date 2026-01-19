package RecursionTypes;
/*before explaining tree recursion we must explain linear recursion.
Linear recursion: is a recursive function that calls itself only once
example: void fun(int n){
        if(n>0){
            ascending operations
            fun(n-1);
            descending operations
        }
}

Tree Recursion: when a recursive function calls itself more than once
time complexity and space complexity can be known by tracing
 */
public class TreeRecursion {
    public void meth1(int n)
    {
        if(n>0)
        {
            System.out.println(n);
            meth1(n-1);
            meth1(n-1);
        }
    }
}
