import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    KeyHandler keyH;
    Sound sound2 = new Sound();

    public static int lives = 3;
    public static int collisions = 90;
    Rectangle r1 = new Rectangle(0, 0, 50, 25);
    public static long startTime;
    public boolean colliding = false;
    public boolean gameOver, youWin = false;


    public Player(Renderer gp, KeyHandler keyH) {
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.y = 10;
        solidArea.x = 10;
        solidArea.width = 48;
        solidArea.height = 48;


        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {//sets the initial state of the bee
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() { //Creates 4 different sprites that are used to animate the bee
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/OFJWBB1-01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/OFJWBB1-02.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/OFJWBB1-03.png"));
            collide = ImageIO.read(getClass().getResourceAsStream("/OFJWBB1-09.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() throws InterruptedException {
        startTime = System.currentTimeMillis(); // get the current time

        detectCollision();
        //Moves bee up and down when corresponding key is pressed
        if (Player.lives > 0 && (keyH.upPressed) || (keyH.downPressed)) {
            if (keyH.upPressed && Player.lives > 0) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed && Player.lives > 0) {
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

        } else if (Player.lives <= 0) {
            spriteNumber = 3;
            if (y <= 700) {
                y += (speed / 3);
            }
        } else {
            y += (speed / 2);
        }
        if (Player.lives <= 0 && UI.timeLeft >= 0) {//Once game is over, this plats the losing tune and stops main theme
            if (!gameOver) {
                playSE(5);
                Renderer.stopMusic();
                gameOver = true;
            }
        } else if (Player.lives > 0 && UI.timeLeft <= 0) {
            if (!youWin) {
                playSE(2);
                youWin = true;
            }
        }


    }

    public void draw(Graphics2D g2) {//responsible for animating the sprite and updating it
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) {
                    image = down;
                }
                if (spriteNumber == 2) {
                    image = up1;
                }
                if (spriteNumber == 3) {
                    image = collide;
                }
            }
            case "down" -> {
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                if (spriteNumber == 3) {
                    image = collide;
                }
            }
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
                if (colliding = true) {
                    if ((System.currentTimeMillis() % 5) == 0 && ((startTime - System.currentTimeMillis()) < 100)) {
                        collisions -= 5;
                        collidingSound();
                        if ((System.currentTimeMillis() % 20) == 0) {
                            stopMusic();
                        }

                    }
                }
            }
            lives = collisions / 30;


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

    public void collidingSound() {
        playSE(3);

    }

}




