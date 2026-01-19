package RecursionTypes;
/*Nested recursion is when a recursive function has its recursive call with the parameter
of a recursive call, therefor it is called nested recursion
 */
public class NestedRecursion {
    public int meth1(int n)
    {
        if(n>100)
            return n-10;
        else
            return meth1(meth1(n+11));
    }
}
