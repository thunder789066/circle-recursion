/**
 * @(#)Text3.java
 *
 *
 * @author 
 * @version 1.00 2017/4/25
 */
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionListener;

public class CircleOptions extends JPanel {
	private JButton animateButton;
	private JLabel entryLabel, delayLabel;
	private JTextField depthEntry, delayEntry;
	
	private Font font;
	
	private CircleCanvas canvas;
		
	public CircleOptions(int width, int height, CircleCanvas canvas, ActionListener listener) {
		this.canvas = canvas;
		
		this.setPreferredSize( new Dimension(width, height) );
		this.setOpaque(true);
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		
		this.font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		
		this.entryLabel = new JLabel("Recursion Depth: ");
		this.entryLabel.setFont(this.font);
		this.entryLabel.setForeground(Color.WHITE);
		
		this.depthEntry = new JTextField(2);
		this.depthEntry.setFont(this.font);
		
		this.delayLabel = new JLabel("Time Delay: ");
		this.delayLabel.setFont(this.font);
		this.delayLabel.setForeground(Color.WHITE);
		
		this.delayEntry = new JTextField(5);
		this.delayEntry.setFont(this.font);
		
		this.animateButton = new JButton("Animate");
		this.animateButton.setFont(this.font);
		this.animateButton.setBackground(Color.WHITE);
		this.animateButton.setForeground(Color.DARK_GRAY);
		this.animateButton.addActionListener(listener);
		
		
		int x, y, w, h;
		
		x = (int) (width * .05);
		y = (int) (height * .1);
		w = (int) (width * .55);
		h = (int) (height * .1);
		this.entryLabel.setBounds(x, y, w, h);
		
		x = (int) (width * .60);
		y = (int) (height * .125);
		w = (int) (width * .18);
		h = (int) (height * .05);
		this.depthEntry.setBounds(x, y, w, h);
		
		x = (int) (width * .05);
		y = (int) (height * .20);
		w = (int) (width * .55);
		h = (int) (height * .1);
		this.delayLabel.setBounds(x, y, w, h);
		
		x = (int) (width * .60);
		y = (int) (height * .22);
		w = (int) (width * .18);
		h = (int) (height * .05);
		this.delayEntry.setBounds(x, y, w, h);
		
		w = (int) (width * .3);
		h = (int) (height * .06);
		x = (int) (width * .5  -  w * .5);
		y = (int) (height * .35);
		this.animateButton.setBounds(x, y, w, h);
		
		
		this.add(this.entryLabel);
		this.add(this.depthEntry);
		this.add(this.delayLabel);
		this.add(this.delayEntry);
		this.add(this.animateButton);
		
	}
	
	/**
	 * Get current delay
	 */
	public int getDelay() {
		String delay = this.delayEntry.getText();
		if (delay.length() == 0)
			return 1000;
		return  Integer.parseInt(delay);
	}
	
	/**
	 * Get current recursion depth
	 */
	public int getDepth(){
		String depth = this.depthEntry.getText();
		if (depth.length() == 0)
			return 1;
		return Integer.parseInt(depth);
	}
}