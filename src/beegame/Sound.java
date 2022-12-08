package beegame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/Sound/main1.wav");
        soundURL[1] = getClass().getResource("/res/Sound/collision.wav");
        soundURL[2] = getClass().getResource("/res/Sound/life.wav");
        soundURL[3] = getClass().getResource("/res/Sound/collision3.wav");
        soundURL[4] = getClass().getResource("/res/Sound/collision4.wav");
        soundURL[5] = getClass().getResource("/res/Sound/lose.wav");
        soundURL[6] = getClass().getResource("/res/Sound/complete2.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (
                Exception e) {

        }
    }


        public void play () {
        clip.start();
        }
        public void loop () {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        public void stop(){
        clip.close();
        }

    }

