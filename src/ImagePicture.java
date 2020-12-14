/**
 * 
 */

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ImagePicture extends Picture{
	//class instance data
	private ImageIcon image;

	//constructor with image passed in
	public ImagePicture(ImageIcon img) {
		// TODO Auto-generated constructor stub
		super();
		this.image = img;
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
		repaint();
	}

	public ImagePicture(ImageIcon img, int x, int y) {
		super();
		this.image = img;
		setxPos(x);
		setyPos(y);
		setMyWidth(img.getIconWidth()) ;
		setMyHeight(img.getIconHeight());
		repaint();		
	}

	//my paint method
	public void paint(Graphics g) {
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create a frameaz
		JFrame f = new JFrame("Testing");

		//create an ImagePicture object
		ImagePicture p = new ImagePicture(new ImageIcon("minion.png"));

		f.setSize(400, 350);
		f.add(p);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "wait");
		p.setxPos(200);
		p.setyPos(100);
	}

}