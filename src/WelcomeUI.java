/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class that creates a start screen for the Honda Dealership program and allows users to either log in to their account or create a new account
 *Method List: 
 *		WelcomeUI() 
 *		static void main(String[] args)
 *		void actionPerformed(ActionEvent e)
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class WelcomeUI extends JFrame implements ActionListener{
	//declaring private data for background, title, widths and height, and buttons
	private ImagePicture bg;
	private TextPicture title;
	private int width, height, btnWid;
	private JButton btnLogin, btnCreate, btnExit, btnHelp;
	private JTextArea areaHelp;
	//constructor
	public WelcomeUI() {
		super("Welcome");  // title for the frame
		//initializing frame width and height and button width
		width = 400;
		height = 435;
		btnWid = 180;
		//creating ImagePicture for background, TextPicture for title and setting font
		bg  = new ImagePicture(new ImageIcon("honda.gif"), 0, 0);
		title = new TextPicture ("Welcome to Honda Dealership", 32, 20);
		title.setFont(new Font("Sans Serif", Font.BOLD, 23));
		//creating buttons and setting colors
		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.RED);
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnHelp = new JButton("Help");
		btnHelp.setBackground(Color.RED);
		btnHelp.setForeground(Color.white);
		btnHelp.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.white);
		btnExit.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnCreate = new JButton("Create Account");
		btnCreate.setBackground(Color.RED);
		btnCreate.setForeground(Color.white);
		btnCreate.setFont(new Font("Sans Serif", Font.BOLD, 15));

		areaHelp = new JTextArea();
		areaHelp.setEditable(false);

		//setting bounds
		bg.setBounds(0, 0, width, height);
		title.setBounds(0,0,width, height);
		btnLogin.setBounds((width/2)-btnWid/2, 100, btnWid, 50);
		btnCreate.setBounds((width/2)-btnWid/2, 250, btnWid, 50);
		btnExit.setBounds(width-120, height-110, 100, 30);
		btnHelp.setBounds(width-(120+110), height-110, 100, 30);
		//adding components to frame
		add(btnHelp);
		add(btnExit);
		add(btnCreate);
		add(btnLogin);
		add(title);
		add(bg);
		//adding the action listener to the buttons so it can listen to clicks
		btnLogin.addActionListener(this);
		btnCreate.addActionListener(this);
		btnExit.addActionListener(this);
		btnHelp.addActionListener(this);

		// set size and location of frame
		setSize(width,height); 
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//method to decide what happens when the buttons are clicked
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogin) { //if login button was clicked
			try {
				new LoginUI();  //open login screen
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
			this.dispose();//close this screen
		}
		else if(e.getSource()==btnCreate) { //if create account button was clicked
			try {
				new CreateAccountUI();  //open create account UI
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
			this.dispose();  //close this screen
		}

		else if(e.getSource()==btnHelp) {  //if help button was pressed
			try {
				String[] strArray = ReaderAndWriter.ReadFile("HelpFunc.txt");  //read help file and store it in a string array
				for (int i = 0; i < strArray.length; i++) {//append the text to the area halp JTextArea
					areaHelp.append(strArray[i] + "\n");
				}
				JOptionPane.showMessageDialog(null, areaHelp);//displaying help
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Help file not found.");
			}
		}

		else if(e.getSource()==btnExit) { //if exit button was clicked
			this.dispose();//close this screen
		}
	}

	public static void main(String[] args) {
		//running the constructor
		WelcomeUI ui = new WelcomeUI();

	}

}