package BT6_8;

class MovableRectangle implements Movable{
private MovablePoint topLeft;
private MovablePoint bottomRight;

public MovableRectangle(int x1,int y1,int x2,int y2,int xSpeed,int ySpeed) {
	topLeft=new MovablePoint(x1,y1,xSpeed,ySpeed);
	bottomRight=new MovablePoint(x2,y2,xSpeed,ySpeed);
}

@Override
public void moveUp() {
	bottomRight.moveUp();
	topLeft.moveUp();
}

@Override
public void moveDown() {
	bottomRight.moveDown();
	topLeft.moveDown();
}

@Override
public void moveLeft() {
	topLeft.moveLeft();
	bottomRight.moveLeft();
}

@Override
public void moveRight() {
	bottomRight.moveRight();
	topLeft.moveRight();
}

@Override
public String toString() {
	return "MovableRectangle [topLeft=" + topLeft + ", bottomRight=" + bottomRight + "]";
}


}
