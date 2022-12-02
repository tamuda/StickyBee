package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_worm extends SuperObject {
    public Obj_worm() {
        name = "Worm";
        try {
            image = ImageIO.read(getClass().getResourceAsStream( "/res/objects/worm.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}