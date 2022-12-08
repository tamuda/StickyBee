import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x,y;
    public int  speed;
    //BufferedImage is used to store image files
    public BufferedImage up1, up2, down,moveWorm,moveWorm2,collide,life, background;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber=1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
