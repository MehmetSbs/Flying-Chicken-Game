package src;

public class Point {
    private int xPosition;
    private int yPosition;

    public Point(int xPos , int yPos){
        xPosition = xPos;
        yPosition = yPos;
    }
    public Point(){
        xPosition = 0;
        yPosition = 0;
    }

    public int getX(){
        return xPosition;
    }
    public int getY(){
        return yPosition;
    }
    public void setX(int value){
        xPosition = value;
    }
    public void setY(int value){
        yPosition = value;
    }
    public void transform(int xTransform, int yTransform){
        xPosition += xTransform;
        yPosition += yTransform;
    }
}
