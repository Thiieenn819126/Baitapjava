package BT6_8;

public class MovablePoint implements Movable{
private int x;
private int y;
private int xSpeed;
private int ySpeed;
public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
	this.x = x;
	this.y = y;
	this.xSpeed = xSpeed;
	this.ySpeed = ySpeed;
}
@Override
public String toString() {
	return "( "+x+","+y+")"+"speed=("+xSpeed+","+ySpeed+")";
}
@Override
public void moveUp() {
	y -= ySpeed;
	
}
@Override
public void moveDown() {
	y += ySpeed;
	
}
@Override
public void moveLeft() {
	x -= xSpeed;
	
}
@Override
public void moveRight() {
	x += xSpeed;
	
}



}
