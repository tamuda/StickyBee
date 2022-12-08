package beegame;

import entity.Entity;
import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static entity.Player.lives;

public class UI extends Entity{
     Renderer gp;

     Font helvetiva_40;
     public static int timeLeft = 30;
     public UI(Renderer gp){
         this.gp = gp;
         helvetiva_40 =  new Font("Helvetica", Font.BOLD,20);
         getLifeImage();
     }

     public void draw(Graphics2D g2){
         g2.setFont(helvetiva_40);
         g2.setColor(Color.BLACK);
         g2.drawImage(background,0,0,800,800,null);
         for(int i=0;i<lives;i++){
         g2.drawImage(life,585+(i*50),50,48,48,null);}
         g2.drawString("Timeleft: "+ String.valueOf(timeLeft),605,50);
         if(Player.lives<=0 && UI.timeLeft>=0){
             g2.drawString("Game Over",400,400);
         }
         else if(Player.lives>0 && UI.timeLeft<=0){
             g2.drawString("You Win",400,400);}
     }
    public static void countdown(){
        for(int i =30; i > -1 ; i --){
            try {
                timeLeft = i;
                Thread.sleep(1000);

            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Win!");

    }

    public void getLifeImage() {
        try {
            life = ImageIO.read(getClass().getResourceAsStream("/res/other/OFJWBB1-01.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/bgMain-02.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
