/**
The Commons class contains all
the variables which
are used by several classes. Screen
height and width are not set as constants because
content pane of the JFrame depend on the
Operating System running the program.
 */
public class Commons
{
    private int brickWidth = 38;
    private int brickHeight = 7;
    private int paddleWidth = 60;
    private int paddleHeight = 8;
    private int width = 300;
    private int height = 400;
    private boolean inGame = false;

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setHeight(int newHeight)
    {
        height = newHeight;
    }

    public void setWidth(int newWidth)
    {
        width = newWidth;
    }

    public int getBWidth()
    {
        return brickWidth;
    }

    public void setBWidth(int bWidth_)
    {
        brickWidth = bWidth_;
    }

    public int getBHeight()
    {
        return brickHeight;
    }

    public int getPWidth()
    {
        return paddleWidth;
    }

    public int getPHeight()
    {
        return paddleHeight;
    }

    public boolean getInGame()
    {
        return inGame;
    }

    public void setInGame(boolean a)
    {
        inGame = true;
    }
}
