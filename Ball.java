package MP4;
/**
 * Created by brandon on 10/23/2016.
 */

import MP4.Players.Racquet;
import MP4.Soundfx.Sound;

import java.awt.*;

public class Ball {
    private static final int DIAMETER = 35;

    private int x = 290;//starting point
    private int y = 208;
    private int xa = 1;//speed
    private int ya = 1;
    private int cl = 1;//speed multiplier
    private int bumps = 1;//number of collisions sa ball ug rackets
    private boolean score = false; //determines if someone has scored
    private Game game;//adapter of game; did not extend game so that not all functions that can be done to game kay magamit pud diri

    public Ball(Game game) {
        this.game = game;
    }

    void move() {
        boolean changeDirection = true;
        if(score){
            sleep(2500);// kung naay makascore, ihunong kadali ang game para di ma shock ang players
            score = false;
        }
        if (x + xa < 0){
            reset(); //resets ball location
            game.gameOver(2);
        }
        else if(x + xa > game.getWidth() - DIAMETER){
            reset();
            game.gameOver(1);
        }
        else if (y + ya < 0)
            ya = 1;
        else if (y + ya > game.getHeight() - DIAMETER)
            ya = -1;
        else if (collision(game.P2)){
            xa = -1;
            x = game.P2.getTopX() - DIAMETER;
            speedIncrease();//increases ball speed
        }
        else if (collision(game.P1)){
            xa = 1;
            x = game.P1.getTopX();
            speedIncrease();
        } else
            changeDirection = false;

        if (changeDirection)
            Sound.BALL.play();

        x = x + xa * cl;//responsible for changing the coordinates of the bal; para nis motion lul
        y = y + ya * cl;
    }

    private void speedIncrease(){
        bumps++;
        if(bumps % 5 == 0 && cl < 4) cl++;//increases speed after reaching certain bumbs
    }

    private boolean collision(Racquet P) { //determines if the ball and the racket has collided
        return P.getBounds().intersects(getBounds());
    }

    private void reset(){ //ball reset position and power up
        resetBall();
        game.P1.resetPUP();
        game.P2.resetPUP();
        score = true;
    }

    public void resetBall(){ //used to start a new game
        x = 289;
        y = 207;
        xa = 1;
        ya = 1;
        cl = 1;
        bumps = 1;
        score = false;
    }

    private void sleep(int x) { //try-catch of the sleep method
        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void paint(Graphics2D g) { // paints ball
        g.setColor(Color.RED);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    } // returns ball dimensions/coordinates
}
