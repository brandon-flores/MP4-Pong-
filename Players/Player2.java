package MP4.Players;
/**
 * Created by brandon on 10/23/2016.
 */

import MP4.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player2 extends Racquet{//just the same with player 1
    private static final int X = 585;

    public Player2(Game game) {
        super(game, X);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if(getGame().P1.getPUP()) ya = -1;
            else ya = -3;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if(getGame().P1.getPUP()) ya = 1;
            else ya = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_H && !(getGame().P1.getPUP()))
            PUP = true;
    }

    public int getTopX() {
        return X;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(X, y, getWIDTH(), getHEIGHT());
    }
}

