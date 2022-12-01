package entity;

import beegame.KeyHandler;
import beegame.Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    Renderer gamePlay;
    KeyHandler keyH;

    public Player(Renderer gp, KeyHandler keyH){
        this.gamePlay = gamePlay;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y= 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream( "/res/other/OFJWBB1-01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-02.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-03.png"));
        }catch(IOException e){
        e.printStackTrace();}
    }
    public void update(){
        if ((keyH.upPressed == true) ||(keyH.downPressed == true) ){
            if (keyH.upPressed == true){
                direction="up";
                y -= speed;

            }
            else if (keyH.downPressed == true){
                direction="down";
                y += speed;
            }
            spriteCounter=spriteCounter+1;
            if (spriteCounter>5) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                System.out.println(spriteNumber);
                spriteCounter = 0;
            }

        }
        else y += (speed/2);



    }
    public void draw(Graphics2D g2) {
    /*    g2.setColor(Color.blue);
        g2.fillRect(x,y, 48, 48);*/
        BufferedImage image = null;
        switch(direction){
            case "up":
                if (spriteNumber == 1) {
                    image = down;
                }
                if (spriteNumber == 2) {
                    image = up1;
                }
                break;
            case "down":
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                    break;
                }


        g2.drawImage(image, x, y, 100, 100, null);

            }
        }

        //Checking to see if I can push




