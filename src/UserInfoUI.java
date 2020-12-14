/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class that creates a user info user interface screen for the Honda Dealership program and allows people to edit/confirm their info 
 *Method List: 
 *		UserInfoUI() 
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserInfoUI extends JFrame implements ActionListener {
	//instance variables
	private ImagePicture bg;
	private TextPicture title, textfName, textlName, textuName, textAddress, textPhone, textPsw, textStatus;
	private int width, height, btnWid, txtWidth, txtHeight, index;
	private JButton btnDone, btnSave, btnExit, btnBac2Boss;
	private JTextField txtfName, txtlName, txtuName, txtAddress, txtPhone;
	private JPasswordField txtPsw;
	private Person initialGuy;
	private Client client;
	private ClientList clients;
	private Dealer dealer;
	private DealerList dealers;
	private Mechanic mech;
	private MechanicList mechs;
	private final String defaultStatus = "Update any information if necessary and press save to update, or done to login to your account.";
	private char accType;
	private boolean isTheBoss;  //boolean variable for whether or not the USerInfoUI is opened through the BossUI
	public UserInfoUI(Person p, char type, boolean isTheBoss) throws IOException {
		super("Confirm Info");  // title for the frame
		//initializing frame width and height and button width
		width =1000;
		height = 518;
		btnWid = 180;
		txtWidth = 200;
		txtHeight = 25;
		accType=type; //setting the accType variable to the inputted char
		initialGuy = p;  //setting the initialGuy variable to the person inputted
		this.isTheBoss = isTheBoss;
		if(type=='c') {//if a client person is inputted
			//creating client list and loading existing accounts, as well as finding the index at which this person is stored in the list
			clients = new ClientList();
			clients.FormatReader("Clients.txt");
			index = clients.uNameBinarySearch(p.getUserName());
		}
		else if(type=='d') {//if a dealer person is inputted
			//creating dealer list and loading existing accounts, as well as finding the index at which this person is stored in the list
			dealers = new DealerList();
			dealers.ReaderFormat("Dealers.txt");
			index = dealers.search(p.getUserName());
		}
		else if(type=='m') {//if a mechanic person is inputted
			//creating mechanic list and loading existing accounts, as well as finding the index at which this person is stored in the list
			mechs = new MechanicList();
			mechs.ReaderFormat("Mechanics.txt");
			index = mechs.search(p.getUserName());
		}

		//creating ImagePicture for background, TextPicture for title,status and setting font
		bg  = new ImagePicture(new ImageIcon("bg.png"), 0, 0);
		title = new TextPicture ("Confirm Information", width/2-75, 20);
		title.setFont(new Font("Sans Serif", Font.BOLD, 23));
		textStatus = new TextPicture(defaultStatus, 20, 470);
		textStatus.setFont(new Font("Sans Serif", Font.BOLD, 14));


		//creating buttons
		btnDone = new JButton("Sign In");
		btnDone.setBackground(Color.RED);
		btnDone.setForeground(Color.white);
		btnDone.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnBac2Boss = new JButton("Back to Boss Screen");
		btnBac2Boss.setBackground(Color.RED);
		btnBac2Boss.setForeground(Color.white);
		btnBac2Boss.setFont(new Font("Sans Serif", Font.BOLD, 10));
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.white);
		btnExit.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnSave = new JButton("Save");
		btnSave.setBackground(Color.RED);
		btnSave.setForeground(Color.white);
		btnSave.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnDone.setEnabled(false);

		//creating textfields and corresponding textPictures
		textfName = new TextPicture("First Name:", 10, 55);
		textfName.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtfName = new JTextField();
		txtfName.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtfName.setText(p.getfName());
		textlName = new TextPicture("Last Name:", 230, 55);
		textlName.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtlName = new JTextField();
		txtlName.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtlName.setText(p.getlName());
		textuName = new TextPicture("Username:", 10, 120);
		textuName.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtuName = new JTextField();
		txtuName.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtuName.setText(p.getUserName());
		textAddress = new TextPicture("Address:", 10, 185);
		textAddress.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtAddress.setText(p.getAddress());
		textPhone = new TextPicture("Phone Number:", 10, 250);
		textPhone.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtPhone.setText(p.getPhoneNum());
		textPsw = new TextPicture("Password:", 10, 315);
		textPsw.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtPsw = new JPasswordField();
		txtPsw.setEchoChar('*');
		txtPsw.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtPsw.setText(p.getPswd());

		//setting bounds
		title.setBounds(0,0,width, height);
		textStatus.setBounds(0,0,width, height);
		btnSave.setBounds(750, 210, btnWid, 40);
		btnExit.setBounds(870-(btnWid-60), 410, btnWid/2, 40);
		textfName.setBounds(0,0,width, height);
		txtfName.setBounds(10, 65, txtWidth, txtHeight);
		textlName.setBounds(0,0,width, height);
		txtlName.setBounds(230, 65, txtWidth, txtHeight);
		textuName.setBounds(0,0,width, height);
		txtuName.setBounds(10, 130, txtWidth, txtHeight);
		textAddress.setBounds(0,0,width, height);
		txtAddress.setBounds(10, 195, txtWidth, txtHeight);
		textPhone.setBounds(0,0,width, height);
		txtPhone.setBounds(10, 260, txtWidth, txtHeight);
		textPsw.setBounds(0,0,width, height);
		txtPsw.setBounds(10, 325, txtWidth, txtHeight);
		btnDone.setBounds(750, 260, btnWid, 40);
		btnBac2Boss.setBounds(750, 310, btnWid, 40);

		//adding components to the frame
		if(isTheBoss) {
			add(btnBac2Boss);
			btnDone.setEnabled(false);
		}
		add(textPsw);
		add(txtPsw);
		add(btnDone);
		add(btnSave);
		add(txtPhone);
		add(textPhone);
		add(txtAddress);
		add(textAddress);
		add(txtuName);
		add(textuName);
		add(txtlName);
		add(textlName);
		add(txtfName);
		add(textfName);
		add(btnExit);
		add(textStatus);
		add(title);
		add(bg);

		//adding action listeners to allow buttons to listen to actions
		btnDone.addActionListener(this);
		btnExit.addActionListener(this);
		btnSave.addActionListener(this);
		btnBac2Boss.addActionListener(this);

		// set size and location of frame
		setSize(width,height); 
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public void actionPerformed(ActionEvent e) {
		btnDone.setEnabled(false);  //setting sign in button to disabled as default

		//if the back to boss button was clicked, open boss ui with default boss and close this screen
		if(e.getSource()==btnBac2Boss) {
			try {
				Boss b = new Boss();
				new BossUI(b);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
			this.dispose();
		}
		//setting all text field backgrounds as white by default and setting default status to the defaultStatus String variable
		txtfName.setBackground(new Color(255, 255, 255));
		txtlName.setBackground(new Color(255, 255, 255));
		txtuName.setBackground(new Color(255, 255, 255));
		txtAddress.setBackground(new Color(255, 255, 255));
		txtPhone.setBackground(new Color(255, 255, 255));
		txtPsw.setBackground(new Color(255, 255, 255));
		textStatus.setTitle(defaultStatus);

		if(e.getSource()==btnExit) {  //if exit button pressed, close this screen
			this.dispose();
		}
		//if save button is clicked, save all fields
		else if(e.getSource()==btnSave) {
			//checking if any textfield is left unfilled, highlight the field
			if(((txtfName.getText().trim().equals(""))||(txtlName.getText().trim().equals(""))||(txtuName.getText().trim().equals(""))||(txtAddress.getText().trim().equals(""))||(txtPhone.getText().trim().equals(""))||(txtPsw.getPassword().length==0))== true) {
				if (txtfName.getText().trim().equals("")) {
					txtfName.setBackground(new Color(255, 183, 187));
				}
				if (txtlName.getText().trim().equals("")) {
					txtlName.setBackground(new Color(255, 183, 187));
				}
				if (txtuName.getText().trim().equals("")) {
					txtuName.setBackground(new Color(255, 183, 187));
				}
				if (txtAddress.getText().trim().equals("")) {
					txtAddress.setBackground(new Color(255, 183, 187));
				}
				if (txtPhone.getText().trim().equals("")) {
					txtPhone.setBackground(new Color(255, 183, 187));
				}
				if (txtPsw.getPassword().length==0) {
					txtPsw.setBackground(new Color(255, 183, 187));
				}
				textStatus.setTitle("Please fill any unfilled fields.");//eror message
			}//end if any unfilled
			else {
				//getting password
				String strPsw = "";
				for (int i = 0; i < txtPsw.getPassword().length; i++) {
					strPsw += txtPsw.getPassword()[i];
				}
				//looking for special characters
				if((txtfName.getText().contains(";")) || (txtfName.getText().contains("|")) || (txtfName.getText().contains(",")) || (txtfName.getText().contains("/")) || (txtlName.getText().contains(";")) || (txtlName.getText().contains("|")) || (txtlName.getText().contains(",")) || (txtlName.getText().contains("/")) || (txtuName.getText().contains(";")) || (txtuName.getText().contains("|")) || (txtuName.getText().contains(",")) || (txtuName.getText().contains("/")) || (txtAddress.getText().contains(";")) || (txtAddress.getText().contains("|")) || (txtAddress.getText().contains(",")) || (txtAddress.getText().contains("/")) || (txtPhone.getText().contains(";")) || (txtPhone.getText().contains("|")) || (txtPhone.getText().contains(",")) || (txtPhone.getText().contains("/")) || (strPsw.contains(";")) || (strPsw.contains("|")) || (strPsw.contains(",")) || (strPsw.contains("/"))) {
					textStatus.setTitle("Please ensure that none of your fields contain special characters such as ';', '|', ',', or '/'.");
				}
				//checking for already-existing usernames based on the corresponding person input
				if(accType=='c') {
					if((txtuName.getText().compareTo(initialGuy.getUserName())!=0) && (clients.uNameBinarySearch(txtuName.getText())>=0)) {
						textStatus.setTitle("This username is already taken. Please choose a different one.");
					}
				}
				else if(accType=='d') {
					if((txtuName.getText().compareTo(initialGuy.getUserName())!=0) && (dealers.search(txtuName.getText())>=0)) {
						textStatus.setTitle("This username is already taken. Please choose a different one.");
					}
				}
				else if(accType=='m') {
					if((txtuName.getText().compareTo(initialGuy.getUserName())!=0) && (mechs.search(txtuName.getText())>=0)) {
						textStatus.setTitle("This username is already taken. Please choose a different one.");
					}
				}
				strPsw = "";
				for (int i = 0; i < txtPsw.getPassword().length; i++) {
					strPsw += txtPsw.getPassword()[i];
				}

				//setting person details based on person input

				if(accType=='c') {
					clients.getList()[index].setfName(txtfName.getText());
					clients.getList()[index].setlName(txtlName.getText());
					clients.getList()[index].setUserName(txtuName.getText());
					clients.getList()[index].setAddress(txtAddress.getText());
					clients.getList()[index].setPhoneNum(txtPhone.getText());
					clients.getList()[index].setPswd(strPsw);
					try {
						clients.FormatWriter("Clients.txt");
						btnDone.setEnabled(true);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}
				else if(accType=='d') {
					dealers.getDealer()[index].setfName(txtfName.getText());
					dealers.getDealer()[index].setlName(txtlName.getText());
					dealers.getDealer()[index].setUserName(txtuName.getText());
					dealers.getDealer()[index].setAddress(txtAddress.getText());
					dealers.getDealer()[index].setPhoneNum(txtPhone.getText());
					dealers.getDealer()[index].setPswd(strPsw);
					try {
						dealers.WriterFormat("Dealers.txt");
						btnDone.setEnabled(true);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}
				else if(accType=='m') {
					mechs.getMechanic()[index].setfName(txtfName.getText());
					mechs.getMechanic()[index].setlName(txtlName.getText());
					mechs.getMechanic()[index].setUserName(txtuName.getText());
					mechs.getMechanic()[index].setAddress(txtAddress.getText());
					mechs.getMechanic()[index].setPhoneNum(txtPhone.getText());
					mechs.getMechanic()[index].setPswd(strPsw);
					btnDone.setEnabled(true);
					try {
						mechs.WriterFormat("Mechanics.txt");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}
				textStatus.setTitle("All fields have been updated.");  //success message
			}
		}

		//if done button is clicked, open corresponding UI
		else if(e.getSource()==btnDone) {
			if(accType=='c') {
				//open the client ui and close this one
				try {
					new ClientUI(clients.getList()[index].getUserName());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}  
				this.dispose();
			}
			else if(accType=='d') {
				//open the dealer ui and close this one
				try {
					DealerUI d = new DealerUI(dealers.getDealer()[index].getUserName());
					new DealerUI(dealers.getDealer()[index].getUserName());
				} catch (IOException e1) {
					JOptionPane.showInputDialog(null, e1.toString());
				}  
				this.dispose();
			}
			else if(accType=='m') {
				//open the mechanic ui and close this one
				try {
					new MechanicUI(mechs.getMechanic()[index].getUserName());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}  
				this.dispose();
			}
		}
	}


	public static void main(String[] args) throws IOException {
		//open this screen by creating an account or editing an account.
	}


}