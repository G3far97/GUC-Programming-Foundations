import java.util.Scanner;

public class Hello3 { 

	public static void main (String[]args) {
		Scanner s = new Scanner(System.in);
		int number = s.nextInt();
		int counter = 0;
		
		while (counter < 5)
			{
				System.out.println("number entered is: " + number);
				number = s.nextInt();
			}
		
		System.out.println("DONE! ");		//will this line ever be reached?
		
	}
}

