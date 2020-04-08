/**
 * @(#)RunnableAppCounter.java
 *
 * @Clemens 
 * @version 1.00 2017/5/3
 */
import javax.swing.*;
import java.awt.*;
public class RunnableAppCounter extends JFrame implements Runnable {
	private Thread thread;
	private int count;
	private int delay;
	private Color[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.MAGENTA};
	
	public RunnableAppCounter(int delay, int x, int y) {
		this.delay = delay;
		this.setSize(360, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(x, y);
		
	}
	
	/**
	 *	Runnable interface .run() method. Invoked by Thread when started
	 */
	@Override public void run() {
		//Show window
		this.setVisible(true);
		
		while (true) {
			try {
				Thread.sleep(this.delay);
			} catch (InterruptedException e) {
				//Nothing
			}
			this.count ++;
			this.repaint();
		}
	}
	
	@Override public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 90));
		g.setColor( this.colors[this.count % this.colors.length] );
		g.drawString( "" + this.count, 150, 170);
	}

    //================================================================================================

	public static void main(String[] args) {
		Thread t1 = new Thread( new RunnableAppCounter(1000, 10, 10) );
		Thread t2 = new Thread( new RunnableAppCounter(5000, 500, 400) );
		
		t1.start();
		t2.start();
	}
}