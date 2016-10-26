package MP4;
/**
 * Created by brandon on 10/23/2016.
 */

import MP4.Players.Player1;
import MP4.Players.Player2;
import MP4.Players.Racquet;
import MP4.Soundfx.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

    private boolean start = false; //indicates if the game has already started or not
    Ball ball;
    public Racquet P1;
    public Racquet P2;

    public Game() {
        ball = new Ball(this);      //
        P1 = new Player1(this);     // instantiates ball and players
        P2 = new Player2(this);     //
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) { //notifies keyListener on Racquet classes if the user has stopped pressing the key
                P1.keyReleased(e);
                P2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(start){ //notifies Player classes's keyListener only if the game has already started; used to determine if the key to move/power up was already pressed
                    P1.keyPressed(e);
                    P2.keyPressed(e);
                }
                if(!start){ //if the game has not started yet, checks if the space key has been pressed in order for the game to start
                    if(e.getKeyCode() == KeyEvent.VK_SPACE)
                        start = true;
                }
            }
        });
        setFocusable(true);// used in order for keyListener to work
        Sound.BACK.loop();//BGM!!
    }

    private void move() {//calls all instantiated objects move function
        if(start){ //moves only if the game has started
            ball.move();
            P2.move();
            P1.move();
        }
    }

    @Override
    public void paint(Graphics g) { //responsible for the objects we see on the screen
        super.paint(g); //clears screen every motion; trails of the objects can be seen if this is commented out
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); //for smooth edges
        g2d.drawOval(getWidth() / 2 - 25, getHeight() / 2 - 25, 50, 50);//draws center circle
        Line2D lin = new Line2D.Float(getWidth()/2, 0, getWidth()/2, getHeight());//instantiates center line
        g2d.draw(lin);//draws line
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));//sets font specs
        g2d.drawString(String.valueOf(P1.getScore()), getWidth()/2 - 35, 50);//draws player scores
        g2d.drawString(String.valueOf(P2.getScore()), getWidth()/2 + 10, 50);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        if(!start) g2d.drawString("PRESS SPACE TO START", 85, getHeight()/2 - 30);//notifies player to press space to start th game
        ball.paint(g2d);//draws objects
        P1.paint(g2d);
        P2.paint(g2d);
    }

    public void gameOver(int i) {//receives an i that determines which player scored
        boolean flag = false;
        if(i == 1) P1.plusScore();//adds score to players who scored
        if(i == 2) P2.plusScore();
        String temp = "";
        if(P2.getScore() >= 3){//checks kung kinsay nakadaog
            temp = "Game Over Player 2 won!";//sets string nga mag ingon kinsay nakadaog
            flag = true;//notes that the round has already ended
        }
        if(P1.getScore() >= 3) {
            temp = "Game Over Player 1 won!";
            flag = true;
        }
        if(flag){
            repaint();//called repaint so that the score updates to 3 even if the game has already ended
            Sound.BACK.stop();//stops BGM
            Sound.GAMEOVER.play();//plays gameover music
            int dialogButton = JOptionPane.showConfirmDialog (this, temp + "\nWould You Like to Start a New Game","GAME OVER",JOptionPane.YES_NO_OPTION);;//used to know which option has been chosen
            if(dialogButton == JOptionPane.YES_OPTION){
                RESTART();//restarts the game
            }else
                System.exit(ABORT);//exits the game
        }
    }

    public void RESTART(){//resets all; fresh start
        start = false;
        P1.reset();
        P2.reset();
        ball.resetBall();
        Sound.BACK.loop();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pong");//main frame of the game
        Game game = new Game();
        frame.add(game); // adds the game inside the frame
        frame.setSize(620, 480);//w x h size of frame
        frame.setVisible(true); //sets frame visible lul
        frame.setResizable(false); //so that the frame could not be resized
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default window close

        while (true) { //loops until the game ends
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}