/*import entity.CollisionChecker;*/


import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    //set Bee default position
    int beeX = 100;
    int beeY = 100;
    int beeSpeed = 4;
    public final int playerSize = 48;
    int FPS = 60;


    Player player = new Player(this, keyH);
    Worm worm = new Worm(this, keyH);
    public UI ui = new UI(this);
    static JLabel jtimer = new JLabel();


    static Sound sound = new Sound();

    /*    public AssetSetter aSetter = new AssetSetter(this);*/


    public Renderer() {
        //JPanel Constructor
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.addKeyListener(keyH);
        jtimer.setText("You Win");
        jtimer.setFont(new Font("Helvetica", 18, 18));
        this.add(jtimer);
        // this.setFocusable(true);
    }


    @Override
    public void run() {
        //divides 1B nanoseconds(1 seconds) by 60 =. So the screen is repainted 60 times per second
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
            long currentTime = System.nanoTime();
            requestFocusInWindow();


            //UPDATE: changes the bee position
            try {
                update();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //REPAINT: repaints the bee on the new position
            repaint();


            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        playMusic(0);
        UI.countdown();
    }


    @Override
    protected void paintComponent(Graphics g) {
        //this repaints the bee everytime the method is called

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //UI
        ui.draw(g2);
        //Bee
        player.draw(g2);
        //Worm
        for (int i = 0; i < 5; i++) {
            worm.draw(g2);
        }


        g2.dispose();
    }


    public void update() throws InterruptedException {

        player.update();
        worm.update();
    }

    public void timer() {
        long milliseconds = System.currentTimeMillis();

        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        jtimer.setText(minutes + ":" + seconds);
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public static void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }


}
