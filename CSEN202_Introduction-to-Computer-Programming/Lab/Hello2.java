import java.util.Scanner;
public class Hello2
{

	public static void add(double x,double y)
	{
		int r = (int) (x + y);
		System.out.println(r);
	}

	public static String add(int x, int y)
	{
		return ""+x +  y;
	}

	public static int factorial(int x)
	{
		int r = 1;
		for(int i = 1; i <= x; i++)
			r *= i;
		return r;
	}

	public static boolean contains(String s, char c)
	{
		for(int i = 0; i < s.length(); i++)
		{
			if(c == s.charAt(i))
				return true;
		}
		return false;
	}

	public static int count(String s, char c)
	{
		int r = 0;
		for(int i = 0; i < s.length(); i++)
			if (c == s.charAt(i))
				r++;
		return r;
	}

	public static String manipulate(String s)
	{
		String r = "";
		for(int i = 0; i < s.length(); i++)
		{
			int x = count(s,s.charAt(i));
			r =  r + s.charAt(i)+ ""+ x;
		}
		return r;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String x = sc.next();
		//String a = sc.next();
		System.out.println(count(x,'e'));
		System.out.println(manipulate(x));



	}

}