import RecursionTypes.TailRecursion;
import RecursionTypes.HeadRecursion;
import RecursionTypes.TreeRecursion;
import RecursionTypes.IndirectRecursion;
import RecursionTypes.NestedRecursion;
/*This file will use different types of recursion*/

public class Recursion3 {
    public static void main(String[] Args)
    {
        TailRecursion tr=new TailRecursion();
        tr.meth1(4);
        tr.tailRecursionToLoop(4);

        System.out.println();
        System.out.println();

        HeadRecursion hr=new HeadRecursion();
        hr.meth1(4);
        hr.headRecursionToLoop(4);

        System.out.println();
        System.out.println();

        TreeRecursion tree=new TreeRecursion();
        tree.meth1(3);

        System.out.println();
        System.out.println();

        IndirectRecursion ir=new IndirectRecursion();
        ir.funA(20);

        System.out.println();
        System.out.println();

        NestedRecursion nr=new NestedRecursion();
        System.out.println(nr.meth1(95));
    }
}
