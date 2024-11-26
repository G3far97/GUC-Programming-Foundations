public class Group {

	String name;
	Person[] p;
	int maxSize;
	int c;


    public Group(String name, int maxSize) {

    	this.maxSize = maxSize;
    	this.p = new Person[maxSize];
    	this.name = name;
    	this.c = 0;
    }

    public Group(String name, Person[] p, int maxSize)
    {
    	//assume that the lenght of the input array p is smaller than the max size
    	this.name = name;
    	this.maxSize = maxSize;
    	//this.p = p; shallow cloning
    	this.p = new Person[this.maxSize];
    	for(int i =0; i < p.length; i++)
    		if (!(p[i] == null))
    			this.p[i] = new Person(p[i].name, p[i].age, p[i].gender);
    }

    public static void main(String[] args)
    {
    	Person[] y = new Person[5];
    	y[0] = new Person("Sarah", 19, 'f');
    	Group g = new Group("GUC", y,100);
    	y[0].name = "Alia";
    	System.out.println(y[0].name);
    	System.out.println(g.p[0].name);
    }


}