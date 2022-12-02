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
    int wormNum =(int) Math.floor(Math.random() * (20 - 3 + 1) + 3);;
    public int[] worldX = new int[wormNum];
    public int[] worldY = new int[wormNum];


    public Worm(Renderer gp, KeyHandler keyH){
        this.gamePlay = gamePlay;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
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
            moveWorm = ImageIO.read(getClass().getResourceAsStream( "/res/objects/worm.png"));
        }catch(IOException e){
            e.printStackTrace();}
    }
    public void update(){
        if ((keyH.upPressed == true) ||(keyH.downPressed == true) ){
            for(int k=0;k<worldX.length;k++){
                worldX[k] -= (int) Math.floor(Math.random() * (12 - 2 + 1) + 2);;
            }
            }

        else{  for(int k=0;k<wormNum;k++) {
            worldX[k] -= speed/1.5;
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
                    image = moveWorm;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = moveWorm;
                }
                if (spriteNumber == 2) {
                    image = moveWorm;
                }
                break;
        }


            for(int j=0;j<worldX.length;j++) {
                g2.drawImage(image, worldX[j], worldY[j], 100, 100, null);
            }



    }
}

//So pushing works