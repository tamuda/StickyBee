package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x,y;
    public int  speed;
    //BufferedImage is used to store image files
    public BufferedImage up1, up2, down;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber=1;
}
