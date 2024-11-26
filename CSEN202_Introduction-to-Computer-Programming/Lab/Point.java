class Point{
	double x;
	double y;
	static int count = 0;

	Point() {
		this(0,0);
	}

	Point(double x, double y) {
		this.x = x;
		this.y = y;
		count++;
	}

	public void add(Point p)
    	{
        	x+=p.x;
        	y+=p.y;
    	}


	static Point add (Point p1, Point p2) {
		return new Point(p1.x + p2.x, p1.y + p2.y);
	}

	public void swap(Point p) {
		double tempX = p.x ;
		double tempY = p.y;
		p.x = this.x;
		p.y = this.y;
		this.x = tempX;
		this.y = tempY;
	}

	public static void swap(Point p1, Point p2) {
		double tempX = p1.x ;
		double tempY = p1.y;
		p1.x = p2.x;
		p1.y = p2.y;
		p2.x = tempX;
		p2.y = tempY;
	}

	public String toString() {
		return "x = " + x + " y = " + y;
	}

	public static void main(String []args){
		Point p1 = new Point();
		Point p2 = new Point(30,50);

		p1.add(p2);

		System.out.println(p1);
		Point p3 = add(p1,p2);
		System.out.println(p3);
		p1.swap(p3);
		System.out.println(p1);
		System.out.println(p3);

		swap(p2,p1);
		System.out.println(p1);
		System.out.println(p2);

		System.out.println(count);


	}
}