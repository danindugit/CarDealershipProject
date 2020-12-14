import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Picture extends JComponent{

	//iinstance variables colour, location, and dimensions
	private Color c;
	private int xPos, yPos, width, height;
	//constructor to set default propertiesof Picture properties and repaints
	public Picture() {
		// TODO Auto-generated constructor stub
		this.c = Color.RED;
		this.xPos = 140;
		this.yPos = 40;
		this.width = 100;
		this.height = 50;
		repaint();
	}

	//new more useful overloaded constructor to set size and coordinates
	public Picture (int x, int y, int w, int h) {
		this.c = Color.CYAN;
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
	}

	//create setters and getters
	public void setColor(Color c) {
		this.c=c;
		repaint();
	}

	//overload setColor method to use RGB codes
	public void setColor (int r, int g, int b) {
		this.c = new Color(r, g, b);
		repaint();
	}
	//method to get the color of the picture
	public Color getColor() {
		return c;
	}
	//method to set the x position of the picture
	public void setxPos(int x) {
		this.xPos=x;
		repaint();
	}
	//method to get the x position of the picture
	public int getxPos() {
		return xPos;
	}
	//method to set the y position of the picture
	public void setyPos(int y) {
		this.yPos=y;
		repaint();
	}
	//method to get the y position of the picture
	public int getyPos() {
		return yPos;
	}
	//method to set the width of the picture
	public void setMyWidth(int w) {
		this.width=w;
		repaint();
	}
	//method to set the height of the picture
	public void setMyHeight(int h) {
		this.height=h;
		repaint();
	}
	//method to get the height of the picture
	public int getMyHeight () {
		return height;
	}
	//method to get the width of the picture
	public int getMyWidth () {
		return width;
	}

	//my paint method
	public void paint(Graphics g) {
		g.setColor(this.c);
		g.drawRect(this.xPos, this.yPos, this.width, this.height);
	}

	public static void main(String[] args) {
		// creating a frame
		JFrame f = new JFrame("Testing");

		//create a picture object
		Picture p = new Picture();
		//setting size of frame, adding the picture to the frame, and setting the frame as visible
		f.setSize(400, 350);
		f.add(p);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "The picture will now shift and enlarge... brace yourself.");
		//creating a new picture and setting its size and coordinates and removing the first picture from the frame
		Picture p1 = new Picture(200, 50, 100, 50);
		f.remove(p);
		//adding the new picture to the frame
		f.add(p1);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "It will now change colour. You ain't ready.");
		//setting colour of picture to orange and setting the frame to visible again
		p1.setColor(Color.ORANGE);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "Wait for it.... It's about to change colour again.");
		//changing colour again using an RGB code 
		p1.setColor(49, 93, 237);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "Hold up, let me move it to the left.");
		//moving the picture in the x direction
		p1.setxPos(50);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "and a little higher...");
		//moving the picture in the y direction
		p1.setyPos(25);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "It's about to get WIDE");
		//increasing sidth using setter for width
		p1.setMyWidth(250);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "It's about to get LONG");
		//increasing sidth using setter for length
		p1.setMyHeight(250);
		f.setVisible(true);
		//output to test getters for width and height
		System.out.println("Height for last picture: " + p1.getMyHeight() + "\n"
				+ "Width for last picture: " + p1.getMyWidth());
	}

}