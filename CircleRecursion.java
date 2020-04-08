/**
 * @(#)Text1.java
 *
 *
 * @author 
 * @version 1.00 2017/4/25
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class CircleRecursion extends JFrame implements ActionListener, Runnable {
	
	//Delay timer for main method (milliseconds)
	private int delay = 100;
	
	//Size of window
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100;
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;
	
	private CircleCanvas canvas;
	private CircleOptions options;
	
	private Thread animationThread;
	
	private boolean running;
	
	
	public CircleRecursion() {
		//Nothing yet
	}
	
	public void createAndShowGUI() {
		this.setPreferredSize( new Dimension(this.WIDTH, this.HEIGHT) );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//Create and add canvas
		this.canvas = new CircleCanvas(this.WIDTH * 7 / 10, this.HEIGHT);
		this.add( canvas, BorderLayout.WEST );
		
		//Create and add options
		this.options = new CircleOptions(this.WIDTH * 3 / 10, this.HEIGHT, canvas, this);
		this.add( this.options , BorderLayout.EAST );
		
		//Start
		this.pack();
		this.setVisible(true);
		
		this.animationThread = new Thread(this);
		this.animationThread.start();
	}
	
	@Override public void run() {
		this.running = true;
		while (this.running) {
			boolean animating = true;
			while (animating) {
				//DELAY
				try {
					Thread.sleep(this.delay);
				} catch (InterruptedException e) {
					
				}
				this.canvas.repaint();
				animating = this.canvas.nextCircle();
			}
		}
	}
	
	public void onWindowClosing() {
		try {
			this.running = false;
			this.animationThread.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
		
	}
    //==============================================================================
    //				ActionListener methods
    //==============================================================================
	@Override public void actionPerformed(ActionEvent e) {
		this.delay = this.options.getDelay();
		this.canvas.setLimit( this.options.getDepth() );
		this.canvas.createNewCircles();
		
	}

    //===========================================================================================================

	public static void main(String[] args) {
		launchApp( new CircleRecursion() );
	}
	
	public static void launchApp(final CircleRecursion app) {
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.onWindowClosing();
			}
		});
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				app.createAndShowGUI();
			}
		});
	}
}