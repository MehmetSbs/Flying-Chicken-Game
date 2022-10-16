package src;


import java.util.Random;


public class Corn extends Actor {

    private int firstLevelsizeRatio[] = {7,8,9,10,11};
    private int secondLevelsizeRatio[] = {12,13,14,15,16};
    private int thirdLevelsizeRatio[] = {17,18,19,20,21};
    private int directions[] = {-1,1};
    private Random random;
    private int randomIndex;
    private int randomSize;

    public Corn(int lvl){

        super.position.setX(0);
        super.position.setY(700);

        if (lvl == 1) {
            randomSize = getRandomSize(firstLevelsizeRatio);
        }else if (lvl == 2) {
            randomSize = getRandomSize(secondLevelsizeRatio);
        }else if (lvl == 3) {
            randomSize = getRandomSize(thirdLevelsizeRatio);
        }


        super.width = 700 / randomSize;
        super.height = 1380 / randomSize;
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
            position.transform(0, -4);
        }
     }

    public static int getRandomSize(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public int getSize(){
        return randomSize;
    }

}
