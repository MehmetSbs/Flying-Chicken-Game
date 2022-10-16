package src;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Actor {

    protected int screenWidth = 900;
    protected int screenHeight = 700;

    protected Point position;
    protected int height;
    protected int width;
    protected int direction;
    protected BufferedImage texture;

    public Actor(Point pos, int width, int height , int dir){
        position = new Point();
        position.setX(pos.getX());
        position.setY(pos.getY());
        this.width = width;
        this.height = height;
        direction = dir;
        
    }
    public Actor(){
        position = new Point();
        width = 150;
        height = 150;
        direction = 1;
    }

    
    public BufferedImage getTexture(String filePath){
        try{
            texture = ImageIO.read(getClass().getResourceAsStream(filePath));
        }catch(IOException e){
            e.printStackTrace();
        }
        return texture;
    }

    public void setTexture(BufferedImage tex){
        texture = tex;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(texture, position.getX(), position.getY(),width, height , null);
    }

    public void changeDirection(int dir){
        direction = dir;
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }

    public void setHeight(int h){
        height = h;
    }
    public void setWidth(int w){
        width = w;
    }

}
