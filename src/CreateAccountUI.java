/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class that creates a create account user interface screen for the Honda Dealership program and allows client to create a new account 
 *Method List: 
 *		CreateAccountUI() 
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

public class CreateAccountUI extends JFrame implements ActionListener{
	//declaring private data for background, title and other texts, widths and height, a JComboBox for the person type selection and buttons
	private ImagePicture bg;
	private TextPicture title, textfName, textlName, textuName, textAddress, textPhone, textPsw, textYesAcc, textStatus;
	private int width, height, btnWid, txtWidth, txtHeight;
	private JButton btnCreate, btnLogin, btnBack, btnExit;
	private JTextField txtfName, txtlName, txtuName, txtAddress, txtPhone;
	private JPasswordField txtPsw;
	private ClientList clients;
	private Client newClient;
	private final String defaultStatus = "Please fill all fields and press the Create Account button.";
	public CreateAccountUI() throws IOException {
		super("New Client");  // title for the frame
		//initializing frame width and height and button width and textfield width and height
		width =1000;
		height = 518;
		btnWid = 180;
		txtWidth = 200;
		txtHeight = 25;

		//creating client list and loading existing accounts, as well as creating a client object to store the new client
		clients = new ClientList();
		clients.FormatReader("Clients.txt");
		newClient = new Client();

		//creating ImagePicture for background, TextPicture for title,status and setting font
		bg  = new ImagePicture(new ImageIcon("bg.png"), 0, 0);
		title = new TextPicture ("Create Account", width/2-75, 20);
		title.setFont(new Font("Sans Serif", Font.BOLD, 23));
		textStatus = new TextPicture(defaultStatus, 20, 470);
		textStatus.setFont(new Font("Sans Serif", Font.BOLD, 18));

		//creating buttons
		btnBack = new JButton("Back");
		btnBack.setBackground(Color.RED);
		btnBack.setForeground(Color.white);
		btnBack.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.white);
		btnExit.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnCreate = new JButton("Create Account");
		btnCreate.setBackground(Color.RED);
		btnCreate.setForeground(Color.white);
		btnCreate.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.RED);
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(new Font("Sans Serif", Font.BOLD, 15));

		//creating textfields and corresponding textPictures
		textfName = new TextPicture("First Name:", 10, 55);
		textfName.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtfName = new JTextField();
		txtfName.setFont(new Font("Sans Serif", Font.BOLD, 13));
		textlName = new TextPicture("Last Name:", 230, 55);
		textlName.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtlName = new JTextField();
		txtlName.setFont(new Font("Sans Serif", Font.BOLD, 13));
		textuName = new TextPicture("Username:", 10, 120);
		textuName.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtuName = new JTextField();
		txtuName.setFont(new Font("Sans Serif", Font.BOLD, 13));
		textAddress = new TextPicture("Address:", 10, 185);
		textAddress.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Sans Serif", Font.BOLD, 13));
		textPhone = new TextPicture("Phone Number:", 10, 250);
		textPhone.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Sans Serif", Font.BOLD, 13));
		textPsw = new TextPicture("Password:", 10, 315);
		textPsw.setFont(new Font("Sans Serif", Font.BOLD, 14));
		txtPsw = new JPasswordField();
		txtPsw.setEchoChar('*');
		txtPsw.setFont(new Font("Sans Serif", Font.BOLD, 13));

		//creating textField for asking the user if they already have an account
		textYesAcc = new TextPicture("Already have an account? Click Login.", 720, 250);
		textYesAcc.setFont(new Font("Sans Serif", Font.BOLD, 13));

		//setting bounds
		title.setBounds(0,0,width, height);
		textStatus.setBounds(0,0,width, height);
		btnBack.setBounds(870, 410, btnWid/2, 40);
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
		btnCreate.setBounds(750, 150, btnWid, 50);
		textYesAcc.setBounds(0, 0, width, height);
		btnLogin.setBounds(790, 260, btnWid/2, 40);

		//adding components to the frame
		add(textPsw);
		add(txtPsw);
		add(btnLogin);
		add(textYesAcc);
		add(btnCreate);
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
		add(btnBack);
		add(textStatus);
		add(title);
		add(bg);

		//adding action listeners to allow buttons to listen to actions
		btnBack.addActionListener(this);
		btnExit.addActionListener(this);
		btnCreate.addActionListener(this);
		btnLogin.addActionListener(this);

		// set size and location of frame
		setSize(width,height); 
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		//setting default colors for text fields (white)
		txtfName.setBackground(new Color(255, 255, 255));
		txtlName.setBackground(new Color(255, 255, 255));
		txtuName.setBackground(new Color(255, 255, 255));
		txtAddress.setBackground(new Color(255, 255, 255));
		txtPhone.setBackground(new Color(255, 255, 255));
		txtPsw.setBackground(new Color(255, 255, 255));
		textStatus.setTitle(defaultStatus);  //setting default status

		if(e.getSource()==btnBack) {//if back button pressed, go to welcome UI and close this one
			new WelcomeUI();
			this.dispose();
		}
		else if(e.getSource()==btnExit) {  //if exit pressed, close this screen
			this.dispose();
		}
		else if(e.getSource()==btnLogin) {//if login button pressed, open the login UI and close this one
			try {
				new LoginUI();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
			this.dispose();
		}
		else if (e.getSource()==btnCreate) {//if create button is pressed
			//checking if any textfield is left unfilled
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
				textStatus.setTitle("Please fill any unfilled fields.");
			}//end if any unfilled
			else {//if all filled
				//getting password
				String strPsw = "";
				for (int i = 0; i < txtPsw.getPassword().length; i++) {
					strPsw += txtPsw.getPassword()[i];
				}
				//checking for special characters
				if((txtfName.getText().contains(";")) || (txtfName.getText().contains("|")) || (txtfName.getText().contains(",")) || (txtfName.getText().contains("/")) || (txtlName.getText().contains(";")) || (txtlName.getText().contains("|")) || (txtlName.getText().contains(",")) || (txtlName.getText().contains("/")) || (txtuName.getText().contains(";")) || (txtuName.getText().contains("|")) || (txtuName.getText().contains(",")) || (txtuName.getText().contains("/")) || (txtAddress.getText().contains(";")) || (txtAddress.getText().contains("|")) || (txtAddress.getText().contains(",")) || (txtAddress.getText().contains("/")) || (txtPhone.getText().contains(";")) || (txtPhone.getText().contains("|")) || (txtPhone.getText().contains(",")) || (txtPhone.getText().contains("/")) || (strPsw.contains(";")) || (strPsw.contains("|")) || (strPsw.contains(",")) || (strPsw.contains("/"))) {
					textStatus.setTitle("Please ensure that none of your fields contain special characters such as ';', '|', ',', or '/'.");
				}
				else if(clients.uNameBinarySearch(txtuName.getText())>=0) {//if username already exists
					textStatus.setTitle("This username is already taken. Please choose a different one.");
				}//end else if
				else{
					strPsw = "";
					for (int i = 0; i < txtPsw.getPassword().length; i++) {
						strPsw += txtPsw.getPassword()[i];
					}
					//setting properties of new client
					newClient.setfName(txtfName.getText());
					newClient.setlName(txtlName.getText());
					newClient.setUserName(txtuName.getText());
					newClient.setAddress(txtAddress.getText());
					newClient.setPhoneNum(txtPhone.getText());
					newClient.setPswd(strPsw);
					if(!clients.insert(newClient)) {//if not inserted
						textStatus.setTitle("Clients List is full. Please contact Mr. Hachigo at (647)-632-4584 to fix this issue.");  //telling user to call helpline
					}
					else {
						try {
							clients.FormatWriter("Clients.txt");//saving clients file
						} catch (IOException e2) {
							JOptionPane.showMessageDialog(null, e2.toString());
						}
						try {
							new UserInfoUI(newClient, 'c', false);//opening UserInfo UI to confirm info of client
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.toString());
						}
						this.dispose();//close this screen
					}
				}
			}

		}//end if btnCreate
	}//end actionPerformed

	public static void main(String[] args) throws IOException {
		CreateAccountUI gui  = new CreateAccountUI();  //opening this screen

	}


}