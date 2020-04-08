/**
 * @(#)Text2.java
 *
 *
 * @author 
 * @version 1.00 2017/4/25
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;


public class CircleCanvas extends Canvas {
	
	//Colors for circles
	private Color[] colors = {Color.BLUE, Color.GREEN, Color.ORANGE, new Color(199, 0, 255), new Color(0, 255, 216), Color.RED};
	
	//Current index of circles to draw up to
	private int currentCircle;
	
	//All circles to be drawn
	private ArrayList<Circle> circles;
	
	//Total number of circles
	private int limit;
	
	
	public CircleCanvas(int w, int h) {
		this.setPreferredSize( new Dimension(w, h) );
		
		//Initialize list
		this.circles = new ArrayList<Circle>();
		
	}
	
	/**
	 * Set limit of recursion
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Create all circles
	 */
	public void createNewCircles() {
		//Empty list of circles
		this.circles = new ArrayList<Circle>();
		this.currentCircle = 0;
		
		//Create circles
		int radius = Math.min(this.getWidth(), this.getHeight()) / 4 ;
		int centerX = this.getWidth() / 2;
		int centerY = this.getHeight() / 2;
		
		this.createCircle(centerX, centerY, radius, 0);
		
	}
	
	/************************************************************************
	 *						RECURSIVELY CREATE CIRCLES						*
	 ************************************************************************/
	private void createCircle(int centerX, int centerY, int radius, int layer) {
		/** BASE CASE **/
		if (layer >= this.limit)
			return;
		
		/** RECURSION PART **/
		else
		{
			//Set color
			Color color = this.colors[ layer % this.colors.length ];
			
			//Add circle to list
			this.circles.add(  new Circle(centerX, centerY, radius, color)  );
			
			//Create Right Circle
			this.createCircle(centerX + radius, centerY, radius / 2, layer + 1);
			//Create Left Circle
			this.createCircle(centerX - radius, centerY, radius / 2, layer + 1);
			//Create Top Circle
			this.createCircle(centerX, centerY + radius, radius / 2, layer + 1);
			//Create Bottom Circle
			this.createCircle(centerX, centerY - radius, radius / 2, layer + 1);
			
			//Create Top Right Circle
			this.createCircle(centerX + radius, centerY + radius, radius / 2, layer + 1);
			//Create Bottom Right Circle
			this.createCircle(centerX + radius, centerY - radius, radius / 2, layer + 1);
			//Create Top Left Circle
			this.createCircle(centerX - radius, centerY + radius, radius / 2, layer + 1);
			//Create Bottom Left Circle
			this.createCircle(centerX - radius, centerY - radius, radius / 2, layer + 1);
		}		
	}
	
	/**
	 * Draws the next circle in the list of created circles.
	 * Returns true if there are circles left to draw.
	 */
	public boolean nextCircle() {
		this.currentCircle ++;
		return  this.currentCircle < this.circles.size();
	}
	
	/**
	 * Draw all circles up to current
	 */
	@Override public void paint(Graphics g) {
		if (this.circles.isEmpty())
			return;
			
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		
		//White canvas
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Draw each circl in list up to current
		int i = 0;
		while ( i < this.currentCircle  &&  i < this.circles.size()) {
			Circle c = this.circles.get(i);
			
			g2.setColor(c.color);
			g2.fillOval(c.centerX - c.radius, c.centerY - c.radius, c.radius * 2, c.radius * 2);
			g2.setColor(Color.BLACK);
			g2.drawOval(c.centerX - c.radius, c.centerY - c.radius, c.radius * 2, c.radius * 2);
			
			i ++;
		}
	}
}