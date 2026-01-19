package RecursionTypes;
/*Head recursion is when the first statement in the recursive function is the recursive call
so everything is processed on the returning time
head recursion can be converted into loop but with a different writing sequence
*/

public class HeadRecursion {
    public void meth1(int n) {
        if (n > 0) {
            meth1(n - 1);
            System.out.println(n);
        }
    }
    public void headRecursionToLoop(int n){
        int i=1;
        while(i<=n)
        {
            System.out.println(i);
            i++;
        }
    }
}
