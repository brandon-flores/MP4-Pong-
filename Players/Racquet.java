package MP4.Players;
/**
 * Created by brandon on 10/23/2016.
 */

import MP4.Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public abstract class Racquet {
    private int X;
    private static final int WIDTH = 20;  //default width and height of racket
    private static final int HEIGHT = 100;
    private int score = 0;//player score
    int y = 175;//starting point
    int ya = 0;//speed
    boolean PUP = false;//powerup

    private Game game;

    public Racquet(Game game, int X) {//receives an x for either rackets
        this.game = game;
        this.X = X;
    }

    public void move() {//moves the racket
        if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT)
            y += ya;
    }

    public abstract void paint(Graphics2D g);

    public void keyReleased(KeyEvent e) {
        ya = 0;
    }

    public abstract void keyPressed(KeyEvent e);

    public Rectangle getBounds() {
        return new Rectangle(X, y, WIDTH, HEIGHT);
    }

    public abstract int getTopX();

    public int getScore() {
        return score;
    }


    public void reset(){//fresh start racket
        score = 0;
        y = 175;
    }

    public void plusScore() {
        this.score++;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Game getGame() {
        return game;
    }

    public boolean getPUP() {
        return PUP;
    }

    public void resetPUP() {
        PUP = false;
    }
}

