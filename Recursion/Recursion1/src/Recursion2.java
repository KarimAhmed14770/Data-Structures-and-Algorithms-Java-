/*This file will focus on static and global variables with recursion*/





public class Recursion2 {
    int x=5;
    static int var=0;
    static int meth1(int n)
    {
        var++;

        if(n>0)
        {
            return meth1(n-1)+var;
        }
        return 0;
    }
    int meth2(int n)
    {
        if(n>0) {
            return meth2(n-1)+x;
        }
        return 0;
    }
    public static void main(String[] Args)
    {
        System.out.println(meth1(6));
        Recursion2 r=new Recursion2();
        System.out.println(r.meth2(6));
    }
}
