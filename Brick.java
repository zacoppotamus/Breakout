/**
The Brick class includes variables which determine
the coordinates of each brick and whether it is
destroyed or not. Bricks are not implemented as
rectangles to allow for them to be used a sprites
in subsequent refactorings.
 */
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;

public class Brick
{
    private int xLeft;
    private int yTop;
    boolean isDestroyed;

    /**
     * Constructor
     *
     * @param  x
     * @param  y
     * @return
     */
    public Brick(int x, int y)
    {
        xLeft = x;
        yTop = y;
        isDestroyed = false;
    }

    public int getX()
    {
        return xLeft;
    }

    public int getY()
    {
        return yTop;
    }

    public boolean isDestroyed()
    {
        return isDestroyed;
    }

    public void destroyBrick()
    {
        isDestroyed = true;
    }

    /**
     * Return brick as a rectangle.
     *
     * @return Rectangle
     */
    public Rectangle brickAsRect()
    {
        Commons commons = new Commons();
        Rectangle brick
            = new Rectangle(xLeft, yTop,
            commons.getBWidth(),
            commons.getBHeight());

        return brick;
    }
}
