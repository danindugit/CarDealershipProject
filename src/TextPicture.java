import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextPicture extends Picture{

	private String text;
	private Font style;

	public TextPicture() {
		// TODO Auto-generated constructor stub
		super();
		this.style = new Font("Helvetica", Font.PLAIN, 14);
		this.text = "";		
	}

	public TextPicture(String title, int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
		this.style = new Font("Helvetica", Font.PLAIN, 14);
		this.text = title;
		repaint();
	}

	public void setFont(Font myFont) {
		this.style = myFont;		
		repaint();
	}

	public void setTitle(String title) {
		this.text = title;
		repaint();
	}

	// paint method
	public void paint (Graphics g) {
		g.setColor (this.getColor());
		g.setFont(this.style);
		g.drawString(this.text, getxPos(), getyPos());
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Testing");
		String title = "This is the text picture";

		TextPicture p = new TextPicture (title, 0, 20);

		f.setSize(400, 350);
		f.add(p);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "The colour will change soon...");
		p.setColor(Color.BLUE);

		JOptionPane.showMessageDialog(null, "The font will change soon...");
		p.setFont(new Font("Helvetica", Font.ITALIC+Font.BOLD, 16));	


		JOptionPane.showMessageDialog(null, "The text will change soon...");
		p.setTitle("Campos is the greatest ICS teacher of all time!");

		JOptionPane.showMessageDialog(null, "It will soon change positioning...");
		p.setxPos(30);
		p.setyPos(f.getHeight()/2);	

		p.repaint();

	}

}