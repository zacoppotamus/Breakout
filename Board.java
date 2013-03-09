/**
The Board class includes the game logic as well
as game objects, such as the brick, paddle and ball,
and all the methods to draw components to screen
*/

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Board extends JPanel
{
    private Paddle paddle;
    private ArrayList<Brick> bricks;
    private Ball ball;
    private Timer timer;
    private boolean inGame = false;
    private boolean gamePaused = false;
    private boolean skippedMainMenu = false;
    private boolean playerIsDead = false;
    private int points = 0;
    private int lives = 3;
    private int level = 1;
    private Commons commons;
    private String bg = "/landingScreen.jpg";

    /**
     * Constructor. Configures refresh rate and initiates threads
     */
    public Board()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 8);
    }

    public void addNotify()
    {
        super.addNotify();
        gameInit();
    }
    /**
     * Initializes game logic and variables
     * Bricks are initialized for the first
     * level and randomly initialized for every other
     * level
     */
    public void gameInit()
    {
        paddle = new Paddle(120,360);
        ball = new Ball(150, 100, 5);
        commons = new Commons();
        bricks = new ArrayList<Brick>();
        ball.reset();
        if (level == 1)
            initializeBricks();
        else
            randomizeBricks();
    }

    /**
     * Draws all components to screen
     * depending on the game state.
     * Game states include main menu, pause menu,
     * game over screen and game screen.
     *
     * @param g
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        ImageIcon img = new ImageIcon(this.getClass().getResource(bg));
        Image image = img.getImage();
        g.drawImage(image, 0, 0, null);

        if (inGame)
        {
            drawGameState(g);
        }
        else
        {
            if (playerIsDead)
            {
                drawGameState(g);
                drawGameOver(g);
            }
            else if (gamePaused)
            {
                drawGameState(g);
                drawGamePaused(g);
            }
            else if (!skippedMainMenu)
            {
                drawTitles(g);
            }
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Draw all game components (paddle, bricks, ball, points, lives)
     *
     * @param g
     */
    public void drawGameState(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        drawPaddle(g2);
        drawBricks(g2);
        drawBall(g2);
        drawPoints(g);
        drawLives(g);
    }

    /**
     * Draw main menu. Set font, coordinates
     * and color for every string drawn to screen.
     *
     * @param g
     */
    public void drawTitles(Graphics g)
    {
        String drawnString = "Breakout";

        Font font = new Font("Bank Gothic", Font.BOLD, 35);
        FontMetrics metr = this.getFontMetrics(font);

        Color letterColor = new Color(248,207,144);
        g.setColor(letterColor);
        g.setFont(font);
        g.drawString(drawnString,
            (commons.getWidth() - metr.stringWidth(drawnString)) / 2,
            (int)(commons.getHeight() * 0.15));
        drawnString = "AN IZAC JOINT";
        font = new Font("OCR A Std", Font.BOLD, 10);
        g.setFont(font);
        g.drawString(drawnString, 6, (int)(commons.getHeight() * 0.98));
        drawnString = "PRESS <SPACE> TO PLAY";
        font = new Font("OCR A Std", Font.BOLD, 15);
        metr = this.getFontMetrics(font);
        g.setFont(font);
        g.drawString(drawnString,
            (commons.getWidth() - metr.stringWidth(drawnString)) / 2,
            (int)(commons.getHeight() * 0.65));
    }
    /**
     * Event Listener. Determines when SPACE
     * is pressed to start the game and transition
     * to the inGame game state. Also used to restart
     * the game in the game over state
     *
     * @param e
     */
    public void spaceToStart(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE)
        {
            if (playerIsDead)
            {
                lives = 3;
                points = 0;
                level = 1;
                playerIsDead = false;
                inGame = true;
                gameInit();
            }
            if (!skippedMainMenu)
            {
                inGame = true;
                skippedMainMenu = true;
            }
            if (gamePaused)
            {
                gamePaused = !gamePaused;
                inGame = true;
            }
        }
    }

    /**
     * Event listener. Pauses game when
     * ESC is pressed
     *
     * @param e
     */
    public void pauseGame(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE)
        {
            if (skippedMainMenu)
            {
                inGame = !inGame;
                gamePaused = !gamePaused;
            }
        }
    }

    /**
     * Draw points to screen.
     *
     * @param g
     */
    public void drawPoints(Graphics g)
    {
        String score = Integer.toString(points);
        String totalPoints = "";

        int i = score.length();
        while (i < 8)
        {
            totalPoints += "0";
            i++;
        }

        totalPoints += score;
        Font font = new Font("OCR A Std", Font.BOLD, 12);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(totalPoints,
            (int)(commons.getWidth() * 0.75),
            (int)(commons.getHeight() * 0.98));

    }

    /**
     * Draw number of lives left
     * to screen.
     *
     * @param g
     */
    public void drawLives(Graphics g)
    {
        Font font = new Font("OCR A Std", Font.PLAIN, 12);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("LIVES "+lives,
            7,
            (int)(commons.getHeight() * 0.98));
    }

    /**
     * Draw game over message to screen
     *
     * @param g
     */
    public void drawGameOver(Graphics g)
    {
        Font font = new Font("OCR A Std", Font.BOLD, 30);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("GAME OVER",
            (int)(commons.getWidth() * 0.17),
            (int)(commons.getHeight() * 0.5));
        font = new Font("OCR A Std", Font.PLAIN, 11);
        g.setFont(font);
        g.drawString("Press <SPACE> to start a new game",
            (int)(commons.getWidth() * 0.07),
            (int)(commons.getHeight() * 0.55));
    }

    /**
     * Draw game paused message to screen
     *
     * @param g
     */
    public void drawGamePaused(Graphics g)
    {
        Font font = new Font("OCR A Std", Font.BOLD, 35);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("PAUSED",
            (int)(commons.getWidth() * 0.25),
            (int)(commons.getHeight() * 0.5));
        font = new Font("OCR A Std", Font.PLAIN, 11);
        g.setFont(font);
        g.drawString("Press <SPACE> to return to game",
            (int)(commons.getWidth() * 0.1),
            (int)(commons.getHeight() * 0.55));
    }

    /**
     * Convert paddle to rectangle and
     * draw it to screen.
     *
     * @param g2
     */
    public void drawPaddle(Graphics2D g2)
    {
        g2.setColor(Color.WHITE);
        Rectangle paddleRect = paddle.paddleAsRect();
        g2.draw(paddleRect);
        g2.fill(paddleRect);
    }

    /**
     * Convert ArrayList of bricks to rectangles and
     * draw them to screen.
     *
     * @param g2
     */
    public void drawBricks(Graphics2D g2)
    {
        //Rectangle brickRect[] = new Rectangle[40];
        ArrayList<Rectangle> brickRect;
        brickRect = new ArrayList<Rectangle>();
        int i = 0;
        g2.setColor(Color.WHITE);
        for (Brick a : bricks)
        {
            brickRect.add(bricks.get(i).brickAsRect());

            if (!bricks.get(i).isDestroyed())
            {
                g2.draw(brickRect.get(i));
                g2.fill(brickRect.get(i));
            }

            i++;
        }

    }

    /**
     * Convert bal to Ellipse and draw it to screen.
     *
     * @param g2
     */
    public void drawBall(Graphics2D g2)
    {
        Ellipse2D.Double ballShape = ball.ballAsEllipse();
        g2.setColor(Color.WHITE);
        g2.fill(ballShape);
        g2.draw(ballShape);
    }

    /**
     * Initialize brick position and add
     * bricks to the ArrayList for the first
     * level.
     */
    public void initializeBricks()
    {
        commons.setBWidth(38);

        for (int i=20; i<commons.getHeight()*0.2; i += commons.getBHeight()+6)
        {
            for (int j=2; j<commons.getWidth()-15; j += commons.getBWidth()+5)
            {
                bricks.add(new Brick(j,i));
            }
        }
    }

    /**
     * Randomly initialize brick position and add
     * bricks to the ArrayList for every
     * next level.
     */
    public void randomizeBricks()
    {
        Random r = new Random();
        int minBWidth = 38;
        int maxBWidth = 65;
        int newBWidth = r.nextInt(maxBWidth - minBWidth + 1) + minBWidth;
        commons.setBWidth(newBWidth);
        int randomInt;
        for (int i=20; i < commons.getHeight()*0.4; i+=commons.getBHeight()+6)
        {
            for (int j=10; j<commons.getWidth()-15; j += commons.getBWidth()+5)
            {
                // Generate int between 0 or 1
                randomInt = r.nextInt(2);
                if (randomInt == 1)
                    bricks.add(new Brick(j,i));

            }
        }
    }

    /**
     * Check if ball intersects paddle
     * and change its speed and direction
     * accordingly.
     */
    public void collisionCheck()
    {
        if ((ball.ballAsEllipse()).intersects(paddle.paddleAsRect()))
        {
            ball.changeVerticalDirection();
            adjustBallSpeedRelativeToPaddleIntersection();
        }
        // If ball hits a brick, remove it
        removeBrick();
        checkIfPlayerIsDead();
        checkForVictory();
    }

    /**
     * Remove brick from the ArrayList
     * when it is hit by the ball.
     */
    public void removeBrick()
    {
        int i = 0;
        //ArrayList copy to avoid ConcurrentModificationError
        ArrayList<Brick> bricksCopy = new ArrayList<Brick>(bricks);
        for (Brick a : bricks)
        {
            if ((ball.ballAsEllipse()).intersects(bricks.get(i).brickAsRect()))
            {
                ball.changeVerticalDirection();
                bricks.get(i).destroyBrick();
                //System.out.println("Brick "+i+" is destroyed!");
                points+=100;
                try
                {
                    bricksCopy.remove(i);
                }
                catch (Exception IndexOutOfBoundsException)
                {
                }
            }

            i++;
        }
        bricks = bricksCopy;
    }

    /**
     * Determine if player is dead by checking
     * the number of lives left, and deduce a
     * life when ball hits bottom of screen.
     */
    public void checkIfPlayerIsDead()
    {
        if (lives == 0)
        {
            inGame = false;
            playerIsDead = true;
            return;
        }
        if (ball.getY() + (2 * ball.getRadius()) == commons.getHeight())
        {
            inGame = !inGame;
            gamePaused = !gamePaused;
            lives -= 1;
            ball.reset();
            paddle = new Paddle(120,360);
        }
    }

    /**
     * Determine if game must proceed
     * to next level.
     */
    public void checkForVictory()
    {
        if (bricks.size() == 0)
        {
            level += 1;
            gameInit();
        }
    }

    /**
     * The paddle is split into 4 positions
     * which determine the resulting horizontal
     * speed of the ball when it hits each one.
     * This allows for better player control over
     * the ball and enables better gameplay.
     */
    public void adjustBallSpeedRelativeToPaddleIntersection()
    {
        Ellipse2D.Double ballShape = ball.ballAsEllipse();
        Rectangle paddleRect = paddle.paddleAsRect();
        // Split paddle in four parts thus allowing player to control
        // direction of the ball

        // Get leftmost position of paddle and ball
        float pLeft = paddle.getXLeft();
        float bLeft = ball.getX();

        if (bLeft > pLeft + (0.75*commons.getPWidth()))
        {
            ball.setHorizontalSpeed(2);
        }
        else if (bLeft > pLeft + (0.5*commons.getPWidth()))
        {
            ball.setHorizontalSpeed(1);
        }
        else if (bLeft > pLeft + (0.25*commons.getPWidth()))
        {
            ball.setHorizontalSpeed(-1);
        }
        else
        {
            ball.setHorizontalSpeed(-2);
        }

    }

    /**
     * Key Listener.
     */
    private class TAdapter extends KeyAdapter
    {
        public void keyReleased(KeyEvent e)
        {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e)
        {
            paddle.keyPressed(e);
            spaceToStart(e);
            pauseGame(e);
        }
    }

    class ScheduleTask extends TimerTask
    {
        public void run()
        {
            if (inGame)
            {
                paddle.move();
                ball.move();
                collisionCheck();
            }

            repaint();
        }
    }

}
