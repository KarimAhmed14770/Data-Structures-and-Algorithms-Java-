package RecursionTypes;
/*tail recursion is when a function calls itself, and the recursive call is the last
* statement of a function, so all the function statements will be processed on calling time
* and no statement will be processed on returning time
* it is called tail recursion because the recursive call is in the function tail,the last line
* of it
* tail recursion can be converted into loop easily, the time complexity is the same
* but the loop is more efficient in space complexity, because it doesn't consume stack frames
*  */
public class TailRecursion {
    public void meth1(int n)
    {
        if(n>0)
        {
            System.out.println(n);
            meth1(n-1);
        }
    }
    public void tailRecursionToLoop(int n){
        while(n>0)
        {
            System.out.println(n);
            n--;
        }
    }
}
