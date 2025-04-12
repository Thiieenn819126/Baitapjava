package Ex_onClass;

public class Circle {
private double radius;
private String color;

     public Circle() {
     this.radius = 1.0;
     this.color = "red";
}
     
	public Circle(double r) {
	this.radius = r;
	this.color = "red";
}
	public double  Getradius() {
		return radius;
		
		
	}
	public double GetArea() {
		return radius*radius*Math.PI;
		
	}

	
}
 class TestCircle{
	 
	public static void main(String[] args) {
	 Circle c1 = new Circle();
	  System.out.println("The circle has radius of " 
		         + c1.Getradius() + " and area of " + c1.GetArea());
	  Circle c2 = new Circle(5.0);
	  System.out.println("The circle has radius of " 
		         + c1.Getradius() + " and area of " + c1.GetArea());

	}

	
	
	
	
}