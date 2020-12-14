/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class that creates a login screen for the Honda Dealership program and allows users log in to their existing account
 *Method List: 
 *		LoginUI() 
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginUI extends JFrame implements ActionListener{
	//declaring private data for background, title and other texts, widths and height, a JComboBox for the person type selection and buttons
	private ImagePicture bg;
	private TextPicture title, textuName, textPsw, textNoAcc, textTypes, textStatus;
	private int width, height, btnWid, txtWidth;
	private JButton btnCreate, btnLogin, btnBack, btnExit;
	private JTextField txtuName;
	private JPasswordField txtPsw;
	private JComboBox types;
	private String []strTypes = {"Select Type", "Client", "Dealer", "Mechanic", "Boss"};
	private ClientList clients;
	private DealerList dealers;
	private MechanicList mechs;
	public LoginUI() throws IOException {
		super("Login");  // title for the frame
		//initializing frame width and height and button width
		width =1000;
		height = 518;
		btnWid = 180;
		txtWidth = 300;

		//creating mechanic, dealer, and client lists and loading existing accounts
		clients = new ClientList();
		clients.FormatReader("Clients.txt");
		dealers = new DealerList();
		dealers.ReaderFormat("Dealers.txt");
		mechs = new MechanicList();
		mechs.ReaderFormat("Mechanics.txt");

		//creating ImagePicture for background, TextPicture for title,status and setting font
		bg  = new ImagePicture(new ImageIcon("bg.png"), 0, 0);
		title = new TextPicture ("Login", width/2-30, 20);
		title.setFont(new Font("Sans Serif", Font.BOLD, 23));
		textStatus = new TextPicture("", width/2-150, 470);
		textStatus.setFont(new Font("Sans Serif", Font.BOLD, 18));

		//creating buttons and text fields and corresponding texts 
		txtuName = new JTextField();
		textuName = new TextPicture("Username:", width/2-txtWidth/2, 100);
		textuName.setFont(new Font("Sans Serif", Font.BOLD, 19));
		txtPsw = new JPasswordField();
		txtPsw.setEchoChar('*');
		textPsw = new TextPicture("Password:", width/2-txtWidth/2, 250);
		textPsw.setFont(new Font("Sans Serif", Font.BOLD, 19));
		textNoAcc = new TextPicture("Don't have an account?", width/2-150, 400);
		textNoAcc.setFont(new Font("Sans Serif", Font.BOLD, 14));		
		btnCreate = new JButton("Create Account");
		btnCreate.setBackground(Color.RED);
		btnCreate.setForeground(Color.white);
		btnCreate.setFont(new Font("Sans Serif", Font.BOLD, 14));
		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.RED);
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnLogin.setEnabled(false);
		btnBack = new JButton("Back");
		btnBack.setBackground(Color.RED);
		btnBack.setForeground(Color.white);
		btnBack.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.white);
		btnExit.setFont(new Font("Sans Serif", Font.BOLD, 15));

		//creating and initializing the JComboBox and corresponding TextPicture
		textTypes = new TextPicture("Account Type:", 700, 170);
		textTypes.setFont(new Font("Sans Serif", Font.BOLD, 19));
		types = new JComboBox(strTypes);
		types.setForeground(Color.WHITE);
		types.setBackground(Color.RED);
		types.setSelectedIndex(0);

		//setting bounds
		title.setBounds(0,0,width, height);
		textStatus.setBounds(0,0,width, height);
		textuName.setBounds(0,0,width, height);
		textPsw.setBounds(0,0,width, height);
		textNoAcc.setBounds(0,0,width, height);
		txtuName.setBounds(width/2-txtWidth/2, 120, txtWidth, 25);
		txtPsw.setBounds(width/2-txtWidth/2, 270, txtWidth, 25);
		btnCreate.setBounds((width/2)+20, 385, btnWid, 30);
		btnLogin.setBounds((width/2)-btnWid/2, 310, btnWid, 50);
		types.setBounds(700, 180, 150, 25);
		textTypes.setBounds(0, 0, width, height);
		btnBack.setBounds(870, 410, btnWid/2, 40);
		btnExit.setBounds(870-(btnWid-60), 410, btnWid/2, 40);

		//adding components to frame
		add(btnExit);
		add(btnBack);
		add(textStatus);
		add(textTypes);
		add(types);
		add(btnLogin);
		add(btnCreate);
		add(textNoAcc);
		add(textPsw);
		add(txtPsw);
		add(textuName);
		add(txtuName);
		add(title);
		add(bg);

		//adding ActionListeners to allow components to listen to clicks
		types.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				types = (JComboBox)e.getSource();
				if(types.getSelectedIndex()>0) {//if type is selected, enable login button
					btnLogin.setEnabled(true);
				}
			}
		});
		btnLogin.addActionListener(this);
		btnCreate.addActionListener(this);
		btnBack.addActionListener(this);
		btnExit.addActionListener(this);

		// set size and location of frame
		setSize(width,height); 
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		//variables to store index and username
		String uName = "";
		int index = 0;
		String type = (String)types.getSelectedItem();  //getting selected type and storing in a string
		if(e.getSource()==btnLogin) {//if login button was pressed
			if(type.equalsIgnoreCase("Select Type")) {//if type is not selected
				textStatus.setTitle("Please select an account type.");
			}
			else {//if type is selected
				//setting password
				String strPsw = "";
				for (int i = 0; i < txtPsw.getPassword().length; i++) {
					strPsw += txtPsw.getPassword()[i];
				}
				if((txtuName.getText().trim().equals(""))&&(strPsw.equals(""))) {//if fields are empty, highlight fields and display error
					txtuName.setBackground(new Color(255, 183, 187));
					txtPsw.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill all fields.");
				}
				//checking individual fields for emptiness
				else if(txtuName.getText().trim().equals("")) {
					txtuName.setBackground(new Color(255, 183, 187));
					txtPsw.setBackground(new Color(255, 255, 255));
					textStatus.setTitle("Please fill username.");
				}
				else if(strPsw.trim().equals("")) {
					txtPsw.setBackground(new Color(255, 183, 187));
					txtuName.setBackground(new Color(255, 255, 255));
					textStatus.setTitle("Please fill password.");
				}
				else {//if fields are filled
					if(type.equalsIgnoreCase("client")) {  //if client selected
						uName = txtuName.getText(); //getting username inputted
						strPsw = "";
						for (int i = 0; i < txtPsw.getPassword().length; i++) {
							strPsw += txtPsw.getPassword()[i];
						}
						//finding index of client
						index = clients.uNameBinarySearch(uName);
						if(index>-1) {//found
							if(clients.getList()[index].getPswd().equals(strPsw)) {  //checking if password is correct
								try {
									new ClientUI(clients.getList()[index].getUserName());  //opening corresponding UI
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, e1.toString());
								}
								this.dispose();  //close this screen
							}
							else {//password wrong, error message, highlight password
								textStatus.setTitle("Password incorrect.");
								txtuName.setBackground(new Color(255, 255, 255));
								txtPsw.setBackground(new Color(255, 183, 187));
							}
						}
						else {//not found, error message, highlight uName text field
							textStatus.setTitle("Username not found.");
							txtuName.setBackground(new Color(255, 183, 187));
							txtPsw.setBackground(new Color(255, 183, 187));
						}
					}
					//dealer selected
					if(type.equalsIgnoreCase("dealer")) {
						uName = txtuName.getText();
						strPsw = "";
						for (int i = 0; i < txtPsw.getPassword().length; i++) {
							strPsw += txtPsw.getPassword()[i];
						}
						index = dealers.search(uName);
						if(index>-1) {//found
							if(dealers.getDealer()[index].getPswd().equals(strPsw)) {
								try {
									new DealerUI(dealers.getDealer()[index].getUserName());
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, e1.toString());
								}
								this.dispose();
							}
							else {
								textStatus.setTitle("Password incorrect.");
								txtuName.setBackground(new Color(255, 255, 255));
								txtPsw.setBackground(new Color(255, 183, 187));
							}
						}
						else {//not found
							textStatus.setTitle("Username not found.");
							txtuName.setBackground(new Color(255, 183, 187));
							txtPsw.setBackground(new Color(255, 183, 187));
						}
					}
					//mechanic selected
					if(type.equalsIgnoreCase("mechanic")) {
						uName = txtuName.getText();
						strPsw = "";
						for (int i = 0; i < txtPsw.getPassword().length; i++) {
							strPsw += txtPsw.getPassword()[i];
						}
						index = mechs.search(uName);
						if(index>-1) {//found
							if(mechs.getMechanic()[index].getPswd().equals(strPsw)) {
								try {
									new MechanicUI(mechs.getMechanic()[index].getUserName());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								this.dispose();
							}
							else {
								textStatus.setTitle("Password incorrect.");
								txtuName.setBackground(new Color(255, 255, 255));
								txtPsw.setBackground(new Color(255, 183, 187));
							}
						}
						else {//not found
							textStatus.setTitle("Username not found.");
							txtuName.setBackground(new Color(255, 183, 187));
							txtPsw.setBackground(new Color(255, 183, 187));
						}
					}
					//mechanic selected
					if(type.equalsIgnoreCase("boss")) {
						try {
							Boss bossGuy = new Boss();
							uName = txtuName.getText();
							strPsw = "";
							for (int i = 0; i < txtPsw.getPassword().length; i++) {
								strPsw += txtPsw.getPassword()[i];
							}
							if(index>-1) {//found
								if(bossGuy.getPswd().equals(strPsw)) {
									new BossUI(bossGuy);
									this.dispose();
								}
								else {
									textStatus.setTitle("Password incorrect.");
									txtuName.setBackground(new Color(255, 255, 255));
									txtPsw.setBackground(new Color(255, 183, 187));
								}
							}
							else {//not found
								textStatus.setTitle("Username not found.");
								txtuName.setBackground(new Color(255, 183, 187));
								txtPsw.setBackground(new Color(255, 183, 187));
							}
						} catch (IOException e1) {
							JOptionPane.showConfirmDialog(null, e1.toString());
						}
					}
				}
			}
		}
		else if(e.getSource()==btnCreate) {  //create button pressed
			try {
				new CreateAccountUI();  //open the create account UI 
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
			this.dispose();  //close this screen
		}
		else if(e.getSource()==btnBack) {  //if back button is pressed
			new WelcomeUI();  //open welcome screen
			this.dispose(); //close this screen
		}		
		else if(e.getSource()==btnExit) {//if exit button is pressed
			this.dispose();//close this screen
		}
	}

	public static void main(String[] args) throws IOException {
		LoginUI gui = new LoginUI(); //open login UI

	}

}