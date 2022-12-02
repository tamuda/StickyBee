package objects;

import beegame.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;

    int maxX = 400;
    int minY = 0;
    int maxY = 750;
    int worldX = randomLocator(800,maxX);
    int  worldY = randomLocator(minY,maxY);
    public void draw (Graphics2D g2, Renderer gp){

        g2.drawImage(image,worldX,worldY,72,72,null);
    }

    //Generate random location
    public int randomLocator(int min, int max) {
        int rand = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return rand;
    }

}
