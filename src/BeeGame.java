import javax.swing.*;

public class BeeGame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;


    public static void main(String[] args) {
        //creating a new instance of the Bee Class
        JFrame jframe = new JFrame();
        Renderer renderer;
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //adding the JPanel (renderer) to the jframe
        renderer = new Renderer();
        jframe.add(renderer);
        jframe.pack();
        renderer.startGameThread();


    }

}

