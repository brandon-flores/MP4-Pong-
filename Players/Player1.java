package MP4.Players;
/**
 * Created by brandon on 10/23/2016.
 */

import MP4.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player1 extends Racquet{
    private static final int X = 10;

    public Player1(Game game) {
        super(game, X);
    }

    public void keyPressed(KeyEvent e) {//keys for the motion of racket
        if (e.getKeyCode() == KeyEvent.VK_W){
            if(getGame().P2.getPUP()) ya = -1;
            else ya = -3;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            if(getGame().P2.getPUP()) ya = 1;
            else ya = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_G && !(getGame().P2.getPUP()))//powerup
            PUP = true;
    }

    public int getTopX() {
        return X + getWIDTH();
    }

    public void paint(Graphics2D g) {//racket on screen
        g.setColor(Color.ORANGE);
        g.fillRect(X, y, getWIDTH(), getHEIGHT());
    }

}
