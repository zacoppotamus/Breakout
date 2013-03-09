import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

class Breakout implements Runnable
{
	public static void main(String[] args)
	{
		Breakout program = new Program();
		SwingUtilities.invokeLater(program);
	}

	public void run()
	{
		JFrame frame = new JFrame();

		frame.setSize(300,400);
		frame.setTitle("Breakout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BrickComponent component = new BrickComponent();
		frame.add(component);

		frame.setVisible(true);
		frame.setResizable(false);
	}
}