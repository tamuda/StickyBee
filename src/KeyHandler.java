import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Acts as the game controller
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //code records the pressed key
        int code =e.getKeyCode();
        //if the code is up then it changes up to true until released
        if (code == KeyEvent.VK_UP){
            upPressed = true;

        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = true;
        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){
            upPressed = false;

        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
    }
}
