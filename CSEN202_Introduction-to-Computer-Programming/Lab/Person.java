public class Person
{
	String name;
	int age;
	char gender;

	public Person(String name, int age, char gender)
	{
		this.name = name;
		this.age = age;
		gender = gender;
	}

	public static void incAge(Person p)
	{
		p.age++;
	}

	public void incAge()
	{
		age++;

	}
	public static boolean compare(Person p1, Person p2)
	{
		return
			p1.age == p2.age && p1.gender == p2.gender && (p1.name).equals(p2.name);
	}

	public static void changeName(Person p, String n)
	{
		p.name = n;
	}
	public void changeName(String n)
	{
		name = n;
	}

	public String toString()
	{
		return
			name + " "  + age;
	}




	public static void main(String[] args)
	{
		Person p1 = new Person("Passant", 19, 'f');
		Person p2 = new Person("Ahmed", 18, 'm');

		Person p3 = new Person("Ahmed", 18, 'm');

		String s = new String("Hello");

		System.out.println(p3.toString());
		Car c = new Car();

	}
}