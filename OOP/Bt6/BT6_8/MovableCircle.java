package BT6_8;

public class MovableCircle implements Movable{
private int radius;
private MovablePoint center;

public MovableCircle(int x,int y,int xSpeed,int ySpeed,int radius) {
	this.radius=radius;
	center=new MovablePoint(x,y,xSpeed,ySpeed);
}

@Override
public void moveUp() {
	center.moveUp();
	
}

@Override
public void moveDown() {
	center.moveDown();
	
}

@Override
public void moveLeft() {
	center.moveLeft();
	
}

@Override
public void moveRight() {
	center.moveRight();
	
}

public String toString() {
	return center.toString()+", radius="+radius;
}
}
