public class Recursion1 {
    public static void fun1(int n)
    {
        if(n>0)//base condition
        {
            System.out.println(n);//Ascending, gets processed at the time of calling
            fun1(n-1);
        }
    }
    public static void fun2(int n)
    {
        if(n>0)//base condition
        {
            fun2(n-1);
            System.out.println(n);//Descending gets processed at the time of returning
        }
    }

    public static void fun3(int n)
    {
        if(n>0)//base condition
        {
            System.out.println(n);//Ascending gets processed at the time of returning
            fun3(n-1);
            System.out.println(n);//Descending gets processed at the time of returning

        }
    }
/*What is the difference between recursion and loop?
loop is repeating statements, and recursion is also repeating statements
but loops are moving only in ascending way in one direction while recursion can move in both ways
statements before the recursive call are done in ascending way, statements after the recursive call
are done in descending way.... this is the power of recursion
 */
    public static void main(String[] Args)
    {
        fun1(5);
        fun2(3);
        fun3(3);
    }


}
