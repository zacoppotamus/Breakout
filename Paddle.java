/**
The paddle class contains the coordinates
of the paddle as well as its horizontal speed.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

class Paddle
{
    private int xLeft;
    private int pWidth;
    private int pHeight;
    private int dx;

    public Paddle(int x, int y)
    {
        xLeft = x;
        pHeight = y;
        pWidth = 60;
    }

    public int getXLeft()
    {
        return xLeft;
    }

    public int getPHeight()
    {
        return pHeight;
    }

    public int getPWidth()
    {
        return pWidth;
    }

    /**
     * Move left or right until
     * a wall is reached.
     */
    public void move()
    {
        Commons commons = new Commons();
        xLeft += dx;
        if (xLeft <= 2)
            xLeft = 2;
        if (xLeft >= commons.getWidth()-pWidth)
            xLeft = commons.getWidth()-pWidth-2;
    }

    public Rectangle paddleAsRect()
    {
        Rectangle paddle
            = new Rectangle(xLeft, pHeight, pWidth, 3);

        return paddle;
    }

    /**
     * Event listeners for controlling
     * the paddle.
     * @param e
     */
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -2;

        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }

}
