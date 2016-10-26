package MP4.Soundfx;
/**
 * Created by brandon on 10/23/2016.
 */

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound { //sound fx
    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("Sounds/ball.wav"));
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("Sounds/gameover.wav"));
    public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("Sounds/back.wav"));
}
