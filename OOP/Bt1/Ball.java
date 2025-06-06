package ex1;

public class Ball {
 private float x;
 private float y;
 private int  radius;
 private float xDelta;
 private float yDelta;
public Ball(float x, float y, int radius, float xDelta, float yDelta) {
	this.x = x;
	this.y = y;
	this.radius = radius;
	this.xDelta = xDelta;
	this.yDelta = yDelta;
}
public float getX() {
	return x;
}
public void setX(float x) {
	this.x = x;
}
public float getY() {
	return y;
}
public void setY(float y) {
	this.y = y;
}
public int getRadius() {
	return radius;
}
public void setRadius(int radius) {
	this.radius = radius;
}
public float getxDelta() {
	return xDelta;
}
public void setxDelta(float xDelta) {
	this.xDelta = xDelta;
}
public float getyDelta() {
	return yDelta;
}
public void setyDelta(float yDelta) {
	this.yDelta = yDelta;
}
 public void move() {
	 this.x+=xDelta;
	 this.y+=yDelta;
 }
 public void reflectHorizontal() {
	 this.xDelta=-xDelta;
 
 }
 public void reflectVertical() {
	 this.yDelta=-yDelta;
 }
 public String toString() {
	  return //x+y+xDelta+yDelta+"";
		  "Ball[("+x+","+y+"),speed=("+xDelta+","+yDelta+")]";
 }
 public static void main(String[] args) {
	  // Test constructor and toString()
     Ball ball = new Ball(1.1f, 2.2f, 10, 3.3f, 4.4f);
     System.out.println(ball);  // toString()

     // Test Setters and Getters
     ball.setX(80.0f);
     ball.setY(35.0f);
     ball.setRadius(5);
     ball.setxDelta(4.0f);
     ball.setyDelta(6.0f);
     System.out.println(ball);  // toString()
     System.out.println("x is: " + ball.getX());
     System.out.println("y is: " + ball.getY());
    System.out.println("radius is: " + ball.getRadius());
     System.out.println("xDelta is: " + ball.getxDelta());
     System.out.println("yDelta is: " + ball.getyDelta());

     // Bounce the ball within the boundary
     float xMin = 0.0f;
     float xMax = 100.0f;
     float yMin = 0.0f;
     float yMax = 50.0f;
     for (int i = 0; i < 15; i++) {
        ball.move();
        System.out.println(ball);
        float xNew = ball.getX();
        float yNew = ball.getY();
        int radius = ball.getRadius();
        // Check boundary value to bounce back
        if ((xNew + radius) > xMax || (xNew - radius) < xMin) {
           ball.reflectHorizontal();
        }
        if ((yNew + radius) > yMax || (yNew - radius) < yMin) {
           ball.reflectVertical();
        }
     }
}
}
