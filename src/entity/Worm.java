package entity;

import beegame.KeyHandler;
import beegame.Renderer;
import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Worm extends Entity {
    Renderer gamePlay;
    KeyHandler keyH;
    public static int wormNum =(int) Math.floor(Math.random() * (20 - 5 + 1) + 5);;
    public static int[] worldX = new int[wormNum];
    public static int[] worldY = new int[wormNum];
    public static Rectangle r2 = new Rectangle(0,0,100,15);


    public Worm(Renderer gp, KeyHandler keyH){
        this.gamePlay = gamePlay;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        //Assigns random coordinate values to worms
        for(int k=0;k<wormNum;k++){
        worldX[k] = randomLocator(800,2000);
        }
        for(int i=0;i<wormNum;i++){
            worldY[i] = randomLocator(0,750);
        }
        speed = 4;
        direction = "down";
    }
    public static int randomLocator(int min, int max) {
        int rand = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return rand;
    }
    public void getPlayerImage(){
        try {
            moveWorm = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-05.png"));
            moveWorm2 = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-10.png"));
        }catch(IOException e){
            e.printStackTrace();}
    }
    public void update(){
        //Moves worms faster when key is pressed else they move slower
        if ((keyH.upPressed == true) ||(keyH.downPressed == true) ){
            for(int k=0;k<worldX.length;k++){
                worldX[k] -= (int) Math.floor(Math.random() * (12 - 2 + 1) + 2);;
                r2.setRect(worldX[k],worldY[k],100,100);
            }
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";


            }
            spriteCounter = spriteCounter + 1;
            if (spriteCounter > 5) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
            }

            }

        else{  for(int k=0;k<wormNum;k++) {
            worldX[k] -= speed/1.5;
            r2.setRect(worldX[k],worldY[k],100,100);}

            spriteCounter = spriteCounter + 1;
            if (spriteCounter > 3) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 2;
                }
            }
        }

        }




    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int worldX2[] = worldX;
        Arrays.sort(worldX);


        if (worldX[0]<-1500) {
            setDefaultValues();}

        switch(direction){
            case "up":
                if (spriteNumber == 1) {
                    image = moveWorm;
                }
                if (spriteNumber == 2) {
                    image = moveWorm2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = moveWorm2;
                }
                if (spriteNumber == 2) {
                    image = moveWorm;
                }
                break;
        }


            for(int j=0;j<worldX.length;j++) {
                g2.drawImage(image, worldX[j], worldY[j], 75, 75, null);
            }



    }
}

//So pushing works