import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This program is an interface for the clients of the dealership
 * Method List:
 * 				ClientUI(String userName)
 * 				actionPerformed(ActionEvent evt)
 *
 */
public class ClientUI extends JFrame implements ActionListener{
	//JPanels for buttons
	private JPanel btnPanel;
	//JTextArea for outputs
	private JTextArea txtOutput;
	//JButtons to perform various actions
	private JButton btnExit, btnViewCars, btnViewInfo, btnInventory, btnClear, btnLogout, btnChangeInfo, btnChangeStatus, btnOK1, btnBack;
	//JComboBox to display client's vehicles and the issues 
	private JComboBox vehicleBox, issueBox;
	//JScrollPane for output (JTextArea)
	private JScrollPane scroller;
	//TextPicture for title of the frame
	private TextPicture title;
	//integers for height and width
	private int height, width;
	private ClientList c;
	//String array for JComboBox elements
	private String cars[], issue[] = {"Vehicle Issue", "oil change", "brake replacement", "tuning problems", "air conditioning problem", "engine problems", "unknown issue", "perfect condition"};
	//String for username of Person 
	private String userName;

	/**
	 * @throws IOException 
	 * 
	 */
	public ClientUI(String userName) throws IOException {
		super ("Client Information Page");
		getContentPane().setBackground(Color.DARK_GRAY);  //set background color of frame

		//initialize width and height
		width = 1000;
		height = 500;

		//create a new ClientList 
		c = new ClientList();
		c.FormatReader("Clients.txt");  //read ClientList from text file

		//create panel for buttons
		btnPanel = new JPanel();
		btnPanel.setBounds(0, 385, width, height);
		btnPanel.setBackground(Color.BLACK);

		//create title 
		title = new TextPicture("Honda - Client Information", 400, 20);
		title.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 20));
		title.setBounds(0, 0, width, height);

		//create output area
		txtOutput = new JTextArea();
		txtOutput.setEditable(false);
		scroller = new JScrollPane(txtOutput);
		scroller.setBounds(5, 100, width-10, 280);

		cars = new String[c.getList()[c.uNameBinarySearch(userName)].getcList().getSize()+1];  //create string to put into combobox
		Car clientCars[] = c.getList()[c.uNameBinarySearch(userName)].getcList().getList();  
		cars[0] = "Vehicles";  //for combobox title
		for (int i = 1; i < cars.length; i++) {
			cars[i] = clientCars[i-1].getVin();  
		}
		//create new combobox to display vehicles (displays VIN)
		vehicleBox = new JComboBox(cars);
		vehicleBox.setBackground(Color.RED);
		vehicleBox.setForeground(Color.white);
		vehicleBox.setBounds(15, 50, 350, 25);

		//create button for combobox
		btnOK1 = new JButton("OK");
		btnOK1.setBackground(Color.RED);
		btnOK1.setForeground(Color.white);
		btnOK1.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnOK1.setBounds(390, 50, 70, 25);

		//create new combobox to display issues
		issueBox = new JComboBox(issue);
		issueBox.setBackground(Color.RED);
		issueBox.setForeground(Color.WHITE);
		issueBox.setBounds(550, 50, 415, 25);
		issueBox.setEnabled(false);

		//create all buttons
		btnInventory = new JButton("Inventory");
		btnInventory.setBackground(Color.RED);
		btnInventory.setForeground(Color.white);
		btnInventory.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnViewInfo = new JButton("View Personal Information");
		btnViewInfo.setBackground(Color.RED);
		btnViewInfo.setForeground(Color.WHITE);
		btnViewInfo.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnViewCars = new JButton("View Cars");
		btnViewCars.setBackground(Color.red);
		btnViewCars.setForeground(Color.white);
		btnViewCars.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnClear = new JButton("Clear");
		btnClear.setBackground(Color.RED);
		btnClear.setForeground(Color.white);
		btnClear.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnLogout = new JButton("Logout");
		btnLogout.setBackground(Color.RED);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.red);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnChangeInfo = new JButton("Change Personal Information");
		btnChangeInfo.setBackground(Color.RED);
		btnChangeInfo.setForeground(Color.white);
		btnChangeInfo.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnChangeStatus = new JButton("Change Vehicle Issue");
		btnChangeStatus.setBackground(Color.red);
		btnChangeStatus.setForeground(Color.WHITE);
		btnChangeStatus.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnChangeStatus.setVisible(false);

		BufferedImage buttonIcon = ImageIO.read(new File("back_arrow.png"));
		btnBack = new JButton(new ImageIcon(buttonIcon));
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setContentAreaFilled(false);
		btnBack.setVisible(false);

		//add buttons to button panel
		btnPanel.add(btnBack);
		btnPanel.add(btnInventory);
		btnPanel.add(btnViewInfo);
		btnPanel.add(btnViewCars);
		btnPanel.add(btnChangeInfo);
		btnPanel.add(btnChangeStatus);
		btnPanel.add(btnClear);
		btnPanel.add(btnLogout);
		btnPanel.add(btnExit);

		//add free components to frame
		add(btnPanel);
		add(scroller);
		add(vehicleBox);
		add(issueBox);
		add(btnOK1);
		add(title);

		//add action listener for buttons
		btnInventory.addActionListener(this);
		btnViewInfo.addActionListener(this);
		btnViewCars.addActionListener(this);
		btnChangeInfo.addActionListener(this);
		btnChangeStatus.addActionListener(this);
		btnClear.addActionListener(this);
		btnLogout.addActionListener(this);
		btnExit.addActionListener(this);
		btnBack.addActionListener(this);
		btnOK1.addActionListener(this);

		setSize(width+17, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.userName = userName;  //save client's username (inputted from LoginUI)
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evt) {
		//get selected items from combobox as strings
		String vehicle = (String)vehicleBox.getSelectedItem();
		String issueSelected = (String)issueBox.getSelectedItem();

		//if ok button for first combobox is clicked
		if (evt.getSource()==btnOK1) { 
			if (vehicle.equalsIgnoreCase("Vehicles")==false) {  //if selected item is not "Vehicles" from vehicleBox
				txtOutput.setText(c.getList()[c.uNameBinarySearch(userName)].getcList().getList()[c.getList()[c.uNameBinarySearch(userName)].getcList().searchForVin(vehicle)].DisplayString());
				btnChangeStatus.setVisible(true);
				btnViewInfo.setVisible(false);
				btnInventory.setVisible(false);
				btnChangeInfo.setVisible(false);
				issueBox.setEnabled(true);
				btnBack.setVisible(true);
			}
			else {  //if selected item is "Vehicles"
				txtOutput.setText("");
				btnChangeStatus.setVisible(false);
				btnViewInfo.setVisible(true);
				btnInventory.setVisible(true);
				btnChangeInfo.setVisible(true);
				issueBox.setEnabled(false);
				btnBack.setVisible(false);
			}
		}
		//if *Change Vehicle Issue* is clicked
		else if (evt.getSource()==btnChangeStatus) {
			//if selected item from vehicles combobox is not "Vehicles" and item from issues combobox is not "Vehicle Issue" (title) from issueBox
			if (vehicle.equalsIgnoreCase("Vehicles")==false && issueSelected.equalsIgnoreCase("Vehicle Issue")==false) {
				//set(change) the issue of the vehicle
				c.getList()[c.uNameBinarySearch(userName)].getcList().getList()[c.getList()[c.uNameBinarySearch(userName)].getcList().searchForVin(vehicle)].setIssue(issueSelected.charAt(0));

				try {
					c.FormatWriter("ClientListTest.txt");  //save new information 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File was not created/found.");  //catch error
				}
				this.dispose();  //dispose current frame
				try {
					ClientUI c = new ClientUI(userName);  //open ClientUI
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ClientUI unreachable.");  //catch error
				}
			}
			else {  //if selected item "Vehicles" (title) from vehicles combobox and/or "Vehicle Issue" (title) from issues combobox
				txtOutput.setText("Please select a vehicle and/or issue to change.");  //display message
			}
		}
		//if inventory is clicked
		else if (evt.getSource()==btnInventory) {
			this.dispose();  //dispose current frame
			try {
				InventoryUI inventory = new InventoryUI(c.getList()[c.uNameBinarySearch(userName)], 'c');  //open InventoryUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "InventoryUI unreachable.");  //catch error
			}
		}
		//if view personal information is clicked
		else if (evt.getSource()==btnViewInfo) {
			txtOutput.setText(c.getList()[c.uNameBinarySearch(userName)].toString());  //set output text accordingly
		}
		//if view cars is clicked
		else if (evt.getSource()==btnViewCars) {
			txtOutput.setText(c.getList()[c.uNameBinarySearch(userName)].getcList().DisplayString());  //set output text accordingly
		}
		//if clear is clicked
		else if (evt.getSource()==btnClear) {
			txtOutput.setText("");  //clear output area
		}
		//if change personal information is clicked
		else if (evt.getSource()==btnChangeInfo) {
			this.dispose();  //dispose current frame
			try {
				UserInfoUI u = new UserInfoUI(c.getList()[c.uNameBinarySearch(userName)], 'c', false);  //open UserInfoUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "UserInfoUI unreachable.");  //catch error
			}
		}
		//if back is clicked
		else if (evt.getSource()==btnBack) {
			this.dispose();  //dispose current frame
			try {
				ClientUI c = new ClientUI(userName);  //open new frame
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "ClientUI unreachable.");  //catch error
			}
		}
		//if logout is clicked
		else if (evt.getSource()==btnLogout) {
			this.dispose();  //dispose current frame
			try {
				LoginUI login = new LoginUI();  //open LoginUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "LoginUI unreachable.");  //catch error
			}
		}
		//if exit is clicked
		else if (evt.getSource()==btnExit) {
			System.exit(0);  //exit system
		}

	}

}