import java.util.Scanner;
public class Recursion
{

	public static int facIter(int x)
	{
		int r = 1;
		for(int i = 1; i <= x; i++)
			r *= i;
		return r;
	}

	public static int facRec(int x)
	{
		if(x==0)
			return 1;
		else
			return x*facRec(x-1);
	}

	public static int fib(int x)
	{
		if (x==1)
			return 1;
		else if (x==2)
			return 1;
			else
				return fib(x-1) + fib(x-2);

	}

	public static int lengthRec(String s)
	{
		if (s.equals(""))
			return 0;
		else
			return 1 + lengthRec(s.substring(1));
	}

	public static String rev(String s)
	{
		if (s.equals(""))
			return s;
		else
			return  s.charAt(s.length()-1) + rev(s.substring(0,s.length()-1)) ;

	}


	public static void binary(int x)
	{
		if (x==0)
			return;
		else
		{

			binary(x/2);
			System.out.print(x%2);
		}
	}

	public static void main(String[] args)
	{
		System.out.println(rev("GUC"));
		//binary(22);

	}

}