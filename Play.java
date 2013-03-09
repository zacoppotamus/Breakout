/**
The Play class acts an entry point
for the game. It initializes the contents
and dimensions of the frame, sets its position
relative to the screen and adds the Board component.
 */
import javax.swing.JFrame;
import java.awt.*;

public class Play extends JFrame
{
    public Play()
    {
        Commons commons = new Commons();

        add(new Board());
        setTitle("Breakout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(300,400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        commons.setHeight((int)getContentPane().getHeight());
        commons.setWidth((int)getContentPane().getWidth());
    }

    public static void main (String[] args)
    {
        new Play();
    }
}
