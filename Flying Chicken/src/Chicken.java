package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Chicken extends Actor {

        
        private float sizeRatio = 2.5f;
        private BufferedImage left1;
        private BufferedImage left2;
        private BufferedImage right1;
        private BufferedImage right2;
        
        public Chicken(){

            super.position.setX(screenWidth/2);
            super.position.setY(screenHeight/5);
            super.height = (int)(200 / sizeRatio);
            super.width = (int)(250 / sizeRatio);
            direction = 1;
            try{
                left1 = ImageIO.read(getClass().getResourceAsStream("../assets/chickenL1.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            try{
                left2 = ImageIO.read(getClass().getResourceAsStream("../assets/chickenL2.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            try{
                right1 = ImageIO.read(getClass().getResourceAsStream("../assets/chickenR1.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            try{
                right2 = ImageIO.read(getClass().getResourceAsStream("../assets/chickenR2.png"));
            }catch(IOException e){
                e.printStackTrace();
            }

            texture = left1;
            
        }

        public Chicken(Point pos, int width, int height, int dir){
            super(pos , width, height ,dir);
            try{
                texture = ImageIO.read(getClass().getResourceAsStream("../assets/chicken.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public void move(int dir){
           if(dir == 1){
                position.transform(2, 0);
           }
           else{
                position.transform(-2, 0);
           }
        }

        public void changeTexture(int i){
            if (i == 1) {

                texture = left1;

            }else if (i == 2) {

                texture = left2;

            }else if (i == 3) {

                texture = right1;

            }else if (i == 4) {

                texture = right2;

            }
        }

        public BufferedImage returnTex(){
            return left2;
        }

        public void defaultPos(){
            this.position.setX(screenWidth/2);
            this.position.setY(screenHeight/5);
        }
        
        

        

}
