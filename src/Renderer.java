import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel implements Runnable {

    static JLabel jtimer = new JLabel();
    static Sound sound = new Sound();
    public UI ui = new UI(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Worm worm = new Worm(this, keyH);

    public Renderer() {
        //JPanel Constructor
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.addKeyListener(keyH);
        jtimer.setText("You Win");
        jtimer.setFont(new Font("Helvetica", Font.BOLD, 18));
        this.add(jtimer);
        // this.setFocusable(true);
    }

    public static void stopMusic() {
        sound.stop();
    }

    @Override
    public void run() {

        while (gameThread != null) {
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


    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }


}
