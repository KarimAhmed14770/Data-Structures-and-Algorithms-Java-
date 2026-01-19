package RecursionTypes;
/*indirect recursion is when a function calls a function that calls a function that calls
the first function
 */
public class IndirectRecursion {
    public void funA(int n){
        if(n>0)
        {
            System.out.println(n);
            funB(n-1);
        }
    }

    public void funB(int n)
    {
        if(n>1)
        {
            System.out.println(n);
            funA(n/2);
        }
    }

}
