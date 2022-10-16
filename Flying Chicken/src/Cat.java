package src;


import java.util.Random;


public class Cat extends Actor {

    private int sizeRatio[] = {2,3,4,5,6};
    private int directions[] = {-1,1};
    private Random random;
    private int randomIndex;

    public Cat(){

        super.position.setX(0);
        super.position.setY(700);
        int randomSize = getSize(sizeRatio);
        super.width = 180 / randomSize;
        super.height = 490 / randomSize;
        random = new Random();
        randomIndex = random.nextInt(directions.length);
        super.direction = directions[randomIndex];

    }

    public void move(int lvl){

        if (lvl == 1) {
            position.transform(0, -2);
        }else if (lvl == 2) {
            position.transform(0, -3);
        }else if (lvl == 3) {
            position.transform(0, -3);
        }
     }

    public static int getSize(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

}
