package src;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Background extends Actor {

    public Background(){
        super();
        super.width = 900;
        super.height = 1400;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void move(){
        if (position.getY() <= -700) {
            position.setY(0);
        }
        position.transform(0, -1);
    }

}
