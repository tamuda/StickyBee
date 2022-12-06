package entity;

import beegame.KeyHandler;
import beegame.Renderer;
import beegame.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class Player extends Entity {
    Renderer gamePlay;
    KeyHandler keyH;
    Sound sound2 = new Sound();

    Rectangle r1 = new Rectangle(0, 0, 50, 25);

    int recSize = 50;
    int i=0;
    public static long startTime;
    boolean playing;
    public boolean colliding = false;

    public Player(Renderer gp, KeyHandler keyH) {
        this.gamePlay = gamePlay;
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.y = 10;
        solidArea.x = 10;
        solidArea.width = 48;
        solidArea.height = 48;


        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-02.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-03.png"));
            collide = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-09.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() throws InterruptedException {
        startTime = System.currentTimeMillis(); // get the start time

        detectCollision();
        //Moves bee up and down when corresponding key is pressed
        if ((keyH.upPressed == true) || (keyH.downPressed == true)) {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;

            }

            spriteCounter = spriteCounter + 1;
            if (spriteCounter > 5) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                } else if (spriteNumber == 3) {
                    spriteNumber = 2;
                }

                spriteCounter = 0;
            }

        } else y += (speed / 2);


    }

    public void draw(Graphics2D g2) {
    /*    g2.setColor(Color.blue);
        g2.fillRect(x,y, 48, 48);*/
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = down;
                }
                if (spriteNumber == 2) {
                    image = up1;
                }
                if (spriteNumber == 3) {
                    image = collide;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                if (spriteNumber == 3) {
                    image = collide;
                }
                break;


        }

        g2.drawImage(image, x, y, 100, 100, null);
        //   stopMusic();


    }

    public void detectCollision() throws InterruptedException {
        // Check for collisions
        for (int k = 0; k < Worm.wormNum; k++) {
            r1.setLocation(x, y + 40);
            Worm.r2.setLocation(Worm.worldX[k], Worm.worldY[k]);
            if (r1.intersects(Worm.r2)) {
                //System.out.println("Collided");
                spriteNumber = 3;
                colliding = true;
                if (colliding=true) {
                    if ((System.currentTimeMillis()%10)==0 && ((startTime-System.currentTimeMillis())<100)) {
                        collidingSound();

                        }
                    }
                }



        }
    }

    public void playMusic(int i) {
        sound2.setFile(i);
        sound2.play();
        sound2.loop();
    }

    public void stopMusic() {
        sound2.stop();
    }

    public void playSE(int i) {
        sound2.setFile(i);
        sound2.play();
    }

    public void collidingSound()  {
        playSE(3);
        //System.out.println(System.currentTimeMillis() - startTime);
     /*   if ((System.currentTimeMillis() - startTime) < 5) { // check if the time difference is less than 1 second
                playSE(1);
            }
        if ((System.currentTimeMillis() - startTime) > 5000) { // check if the time difference is less than 1 second
            startTime =System.currentTimeMillis();*/
        }

    }




        //So pushing works




