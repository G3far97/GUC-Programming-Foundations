
public class MultiD2 {


	public static void display(int[] a)
	{
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	public static void change(int[] a)
	{
		a = new int[a.length];
		//display(a);

		//for(int i = 0; i < a.length; i++)
		//	a[i] = 0;
		//return a;
	}

	public static void display(int[][] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			for(int j = 0; j < a[i].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
	}

	public static int[][] init(int n)
	{
		int[][] r = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n ; j++)
				r[i][j] = i+1;
		return r;
	}

	public static int[][] initRagged(int n)
	{
		int[][] r = new int[n][];
		for(int i = 0; i < n; i++)
		{
			r[i] = new int[i+1];
			for(int j = 0; j < r[i].length; j++)
				r[i][j] = i+1;
		}
		return r;
	}

	public static boolean search(int[] a, int k)
	{
		for(int i = 0; i < a.length; i++)
			if(k == a[i])
				return true;
		return false;
	}

	public static boolean searchRec(int[] a, int k)
	{
		return helper(a,k,0);
	}

	private static boolean helper(int[] a, int k, int c)
	{
		if(c == a.length)
			return false;
		if(k == a[c])
			return true;
		return helper(a,k,c+1);
	}



	public static void main(String[] args)
	{
		int[] x = {55,55,2,1,1,1};
		System.out.println(helper(x,55,2));


	}

}