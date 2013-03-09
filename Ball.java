/**
The Ball class includes variables which identify
and describe the state of the ball, such as coordinates
radius, vertical and horizontal speed, orientation etc.
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Ball
{
    private int radius;
    //center of ball
    private int x;
    private int yTop;
    private int verticalSpeed;
    private int horizontalSpeed;
    private boolean goingDown;
    private int diameter;

    /**
     * Constructor
     *
     * @param  x_
     * @param  yTop_
     * @param  radius_
     * @return
     */
    public Ball(int x_, int yTop_, int radius_)
    {
        x = x_;
        yTop = yTop_;
        radius = radius_;
        goingDown = true;
        verticalSpeed = 2;
        horizontalSpeed = 0;
        diameter = 2*radius_;
    }

    /**
     * Moves ball. Change orientation when side walls or top is hit
     * and prevent ball from sticking in the gutter.
     */
    public void move()
    {
        if(goingDown)
            yTop += verticalSpeed;
        else
            yTop -= verticalSpeed;

        x += horizontalSpeed;

        Commons commons = new Commons();

        if (( (x-radius) == 0) || (x + (radius) == commons.getWidth()))
            horizontalSpeed = -horizontalSpeed;
        if (x + (radius) > commons.getWidth())
        {
            x = commons.getWidth() - radius;
            horizontalSpeed = -horizontalSpeed;
        }
        if ((x-radius) < 0)
        {
            x = radius;
            horizontalSpeed = -horizontalSpeed;
        }
        if (yTop <= 0)
            goingDown = true;
        if (yTop + (2*radius) >= commons.getHeight())
            goingDown = false;
    }

    /**
     * Vertical speed setter.
     *
     * @param speed
     */
    public void setVerticalSpeed(int speed)
    {
        verticalSpeed = speed;
    }

    /**
     * Horizontal speed setter.
     *
     * @param speed
     */
    public void setHorizontalSpeed(int speed)
    {
        horizontalSpeed = speed;
    }

    public int getRadius()
    {
        return radius;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return yTop;
    }

    public void changeVerticalDirection()
    {
        goingDown = !goingDown;
    }
    public Ellipse2D.Double ballAsEllipse()
    {
        Ellipse2D.Double ball
            = new Ellipse2D.Double(x-radius, yTop, 2*radius, 2*radius);

        return ball;
    }

    /**
     * Determine where ball is placed
     * when the game starts or the player respawns.
     */
    public void reset()
    {
        Commons commons = new Commons();
        x = commons.getWidth()/2;
        yTop = commons.getHeight()/ 2;
        horizontalSpeed = 0;
        goingDown = true;
    }
}
