package src;

public class Egg extends Actor{

    private int speed = 10;
    private int ratio = 15;

    public Egg(){
        super();
        super.position.setX(screenWidth/2);
        super.position.setY(screenHeight/5);
        super.width = 550/ratio;
        super.height = 432/ratio;
        super.direction = 1;
    }

    public void movement(){
        if (direction == 1) {
            position.transform(speed, 0);
        }
        else if (direction == -1) {
            position.transform(-speed, 0);
        }
    }

}
