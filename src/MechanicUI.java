import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
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

/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This program is a user interface for the mechanics of the dealership.
 * Method List:
 * 				MechanicUI(String userName)
 * 				void actionPerformed(ActionEvent evt)
 *
 */
public class MechanicUI extends JFrame implements ActionListener{

	//private integers for width and height
	private int width, height;
	//TextPicture for title
	private TextPicture title;
	//JPanle for buttons
	private JPanel btnPanel;
	//JTextArea for output
	private JTextArea txtOutput;
	//JScrollPane for output
	private JScrollPane scroller;
	//JButtons to perform various actions
	private JButton btnViewClients, btnViewCars, btnVehicleRecord, btnLogout, btnClear, btnExit, btnOK1, btnOK2, btnUpdateIssue, btnUpdateStatus, btnRefresh, btnBack;
	private MechanicList m;
	private ClientList clients;
	//JComboBox for clients, client's vehicles, vehicle issues and status 
	private JComboBox clientBox, serviceCarsBox, issueBox, statusBox;
	//String arrays for JComboBox elements
	private String cUserName[], issue[] = {"Issue", "oil change", "perfect condition", "brake replacement", "tuning problems", "air conditioning problem", "engine problems", "unknown issue"};
	private String status[] = {"Status", "under service", "service complete", "status unknown"};
	//String for user name
	private String userName;
	/**
	 * @throws IOException 
	 * 
	 */
	public MechanicUI(String userName) throws IOException {
		super("Mechanic Interface");
		getContentPane().setBackground(Color.DARK_GRAY);  //set color of frame

		//initialize width and height
		width = 1000;
		height = 650;

		//create MechanicList
		m = new MechanicList();  
		try {
			m.ReaderFormat("Mechanics.txt");  //read MechanicList from "Mechanics.txt" file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
		}

		//create ClientList
		clients = new ClientList();
		try {
			clients.FormatReader("Clients.txt");  //read ClientList from "Clients.txt" file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");
		}

		//create panel for buttons
		btnPanel = new JPanel();
		btnPanel.setBounds(0, 540, width, height);  //set bounds for panel
		btnPanel.setBackground(Color.BLACK);

		//create title
		title = new TextPicture("Honda - Mechanic Page", 390, 20);
		title.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 20));
		title.setBounds(0, 0, height, width);

		//create output area 
		txtOutput = new JTextArea();
		txtOutput.setEditable(false);  //set uneditable
		scroller = new JScrollPane(txtOutput);  //add output area to JScrollPane
		scroller.setBounds(350, 40, 300, 400);  //set bounds for scroller

		int counter=0;

		ClientList needService = new ClientList();

		//loop through clients
		for (int i = 0; i < clients.getSize(); i++) {
			//loop through client's car list 
			for (int j = 0; j < clients.getList()[i].getcList().getSize(); j++) {
				//if a car needs service (is not in perfect condition)
				if (clients.getList()[i].getcList().getList()[j].getIssue() != 'p') {
					counter++;  //add counter
					needService.insert(clients.getList()[i]);  //insert the client list into a new client list
					break;  //break out of car list loop
				}
			}
		}
		cUserName = new String[counter+1];  //create string to put into combobox
		cUserName[0] = "Clients";  //title for combobox
		for (int i = 1; i < cUserName.length; i++) {
			cUserName[i] = needService.getList()[i-1].getUserName();

		}
		//create combobox for clients
		clientBox = new JComboBox(cUserName);
		clientBox.setBackground(Color.red);  //set colors
		clientBox.setForeground(Color.WHITE);
		clientBox.setBounds(5, 80, 300, 25);

		//create "OK" button for clients combobox
		btnOK1 = new JButton("OK");
		btnOK1.setBackground(Color.RED);  //set colors
		btnOK1.setForeground(Color.white);
		btnOK1.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnOK1.setBounds(5, 110, 300, 25);

		//create combobox for client's vehicles
		serviceCarsBox = new JComboBox();
		serviceCarsBox.setBackground(Color.RED);  //set colors
		serviceCarsBox.setForeground(Color.white);
		serviceCarsBox.setBounds(695, 80, 300, 25);
		serviceCarsBox.setVisible(false);  //set invisible

		//create "OK" button for client vehicles combobox
		btnOK2 = new JButton("OK");
		btnOK2.setBackground(Color.red);  //set colors
		btnOK2.setForeground(Color.WHITE);
		btnOK2.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnOK2.setBounds(695, 110, 300, 25);
		btnOK2.setVisible(false);  //set invisible

		//create combobox for vehicle issues
		issueBox = new JComboBox(issue);
		issueBox.setBackground(Color.red);  //set colors
		issueBox.setForeground(Color.WHITE);
		issueBox.setBounds(150, 450, 300, 25);
		issueBox.setVisible(false);  //set invisible

		//create "Update Issue" button for issues combobox
		btnUpdateIssue = new JButton("Update Issue");
		btnUpdateIssue.setBackground(Color.red);  //set colors
		btnUpdateIssue.setForeground(Color.WHITE);
		btnUpdateIssue.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font
		btnUpdateIssue.setBounds(150, 480, 300, 25);
		btnUpdateIssue.setVisible(false);  //set invisible

		//create combobox for status
		statusBox = new JComboBox(status);
		statusBox.setBackground(Color.red);  //set colors
		statusBox.setForeground(Color.white);
		statusBox.setBounds(575, 450, 300, 25);
		statusBox.setVisible(false);  //set invisible

		//create all buttons
		btnUpdateStatus = new JButton("Update Status");
		btnUpdateStatus.setBackground(Color.red);  //set colors
		btnUpdateStatus.setForeground(Color.WHITE);
		btnUpdateStatus.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font
		btnUpdateStatus.setBounds(575, 480, 300, 25);
		btnUpdateStatus.setVisible(false);  //set invisible

		btnViewClients = new JButton("View Clients");
		btnViewClients.setBackground(Color.RED);  //set colors
		btnViewClients.setForeground(Color.white);
		btnViewClients.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font

		btnViewCars = new JButton("View Vehicles");
		btnViewCars.setBackground(Color.RED);  //set colors
		btnViewCars.setForeground(Color.WHITE);
		btnViewCars.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font
		btnViewCars.setVisible(false);

		btnVehicleRecord = new JButton("Vehicle Record");
		btnVehicleRecord.setBackground(Color.RED);  //set colors
		btnVehicleRecord.setForeground(Color.WHITE);
		btnVehicleRecord.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font
		btnVehicleRecord.setVisible(false);  //set invisible

		btnClear = new JButton("Clear");
		btnClear.setBackground(Color.RED);  //set colors
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font 

		btnLogout = new JButton("Logout");
		btnLogout.setBackground(Color.red);  //set colors
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font

		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.red);  //set colors
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font

		btnRefresh = new JButton("Refresh");
		btnRefresh.setVisible(false);  //set invisible

		//back button (image)
		BufferedImage buttonIcon = ImageIO.read(new File("back_arrow.png"));
		btnBack = new JButton(new ImageIcon(buttonIcon)); 
		btnBack.setBorder(BorderFactory.createEmptyBorder());  //no border
		btnBack.setContentAreaFilled(false);  //no fill
		btnBack.setVisible(false);  //set invisible

		//add buttons to panel for buttons
		btnPanel.add(btnBack);
		btnPanel.add(btnRefresh);
		btnPanel.add(btnViewClients);
		btnPanel.add(btnViewCars);
		btnPanel.add(btnVehicleRecord);
		btnPanel.add(btnClear);
		btnPanel.add(btnLogout);
		btnPanel.add(btnExit);

		//add components to frame
		add(btnPanel);
		add(scroller);
		add(clientBox);
		add(serviceCarsBox);
		add(issueBox);
		add(statusBox);
		add(btnOK1);
		add(btnOK2);
		add(btnUpdateIssue);
		add(btnUpdateStatus);
		add(title);

		//add action listener to all buttons
		btnViewClients.addActionListener(this);
		btnViewCars.addActionListener(this);
		btnVehicleRecord.addActionListener(this);
		btnClear.addActionListener(this);
		btnLogout.addActionListener(this);
		btnExit.addActionListener(this);
		btnOK1.addActionListener(this);
		btnOK2.addActionListener(this);
		btnUpdateIssue.addActionListener(this);
		btnUpdateStatus.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnBack.addActionListener(this);

		setSize(width+17, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.userName = userName;  //save username (input parameter)
	}

	public void actionPerformed(ActionEvent evt) {
		//get selected items from combobox as strings
		String client = (String)clientBox.getSelectedItem();
		String car = (String)serviceCarsBox.getSelectedItem();
		String issue = (String)issueBox.getSelectedItem();
		String status = (String)statusBox.getSelectedItem();

		//if button for first combobox is clicked
		if (evt.getSource()==btnOK1) {
			if (client.equalsIgnoreCase("Clients")==false) {  //if selected item is not "Clients"
				//set visibility for components
				serviceCarsBox.setVisible(true);
				btnOK2.setVisible(true);
				btnViewCars.setVisible(true);

				//add elements for serviceCarsBox
				serviceCarsBox.removeAllItems();
				String cars[] = new String[clients.getList()[clients.uNameBinarySearch(client)].getcList().getSize()+1];
				Car sCars[] = clients.getList()[clients.uNameBinarySearch(client)].getcList().getList();

				cars[0] = "Client Vehicles";  //title for combobox
				for (int i = 1; i < cars.length; i++) {
					cars[i] = sCars[i-1].getVin();
				}
				for (int i = 0; i < cars.length; i++) {
					serviceCarsBox.addItem(cars[i]);  //add items (elements) individually into combobox
				}
			}
			else {  //if it is "Clients"
				//set visibility
				serviceCarsBox.setSelectedIndex(0);
				issueBox.setSelectedIndex(0);
				statusBox.setSelectedIndex(0);
				btnOK2.setVisible(false);
				btnViewCars.setVisible(false);
			}
		}
		//if button for second combobox is clicked
		else if (evt.getSource()==btnOK2) {
			if (car.equalsIgnoreCase("Client Vehicles")==false) {  //if selected item is not "Client Vehicles"
				//set visibilty for components
				btnVehicleRecord.setVisible(true);
				issueBox.setVisible(true);
				statusBox.setVisible(true);
				btnUpdateIssue.setVisible(true);
				btnUpdateStatus.setVisible(true);
			}
			else {  //if it is "Client Vehicles"
				//set visibility for components
				btnVehicleRecord.setVisible(false);
				issueBox.setVisible(false);
				statusBox.setVisible(false);
				btnUpdateIssue.setVisible(false);
				btnUpdateStatus.setVisible(false);
			}
		}
		//if view clients is clicked
		else if (evt.getSource()==btnViewClients) {
			txtOutput.setText(clients.toString());  //set ouput area to display all clients
		}
		//if view cars is clicked
		else if (evt.getSource()==btnViewCars) {
			if (client.equalsIgnoreCase("Clients")==false) {  //if selected item is not "Clients"
				txtOutput.setText(clients.getList()[clients.uNameBinarySearch(client)].getcList().DisplayString());  //set output area to display all cars once a client is selected
			}
			else {  //if "Clients" is selected
				txtOutput.setText("Please select a client.");  //set output area text
			}
		}
		//if vehicle record is clicked
		else if (evt.getSource()==btnVehicleRecord) {
			if (client.equalsIgnoreCase("Clients")==false  && car.equalsIgnoreCase("Client Vehicles")==false) {  //if selected item is not "Clients"
				//set output area to display the selected vehicle's record
				txtOutput.setText(clients.getList()[clients.uNameBinarySearch(client)].getcList().getList()[clients.getList()[clients.uNameBinarySearch(client)].getcList().searchForVin(car)].DisplayString());
			}
			else {
				txtOutput.setText("Please select a client or client vehicle.");  //set output area text
			}

		}
		//if update issue is clicked
		else if (evt.getSource()==btnUpdateIssue) {
			if (client.equalsIgnoreCase("Clients")==false  && car.equalsIgnoreCase("Client Vehicles")==false) {  //if selected item is not "Clients"
				//set issue for the vehicle
				clients.getList()[clients.uNameBinarySearch(client)].getcList().getList()[clients.getList()[clients.uNameBinarySearch(client)].getcList().searchForVin(car)].setIssue(issue.charAt(0));
				try {
					clients.FormatWriter("Clients.txt");  //update ClientList by writing updated list to file
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");
				}
				btnRefresh.setVisible(true);  //set visibility for button
			}
			else {
				txtOutput.setText("Please select a client or client vehicle.");  //set output area text
			}

		}
		//if update status is clicked 
		else if (evt.getSource()==btnUpdateStatus) {
			if (client.equalsIgnoreCase("Clients")==false  && car.equalsIgnoreCase("Client Vehicles")==false) {  //if selected item is not "Clients"
				char type;  //variable to set type 

				if (status.equalsIgnoreCase("under service")) {  //if under service is selected
					type = 's';  //set type to 's'
				}
				else if (status.equalsIgnoreCase("service complete")) {  //if service complete is selected
					type = 'd';  //set type to 'd'
				}
				else {  //if unknown is selected
					type = 'u';  //set type to 'u'
				}
				//update the vehicle record by setting new status
				clients.getList()[clients.uNameBinarySearch(client)].getcList().getList()[clients.getList()[clients.uNameBinarySearch(client)].getcList().searchForVin(car)].setStatus(type);
				try {
					clients.FormatWriter("Clients.txt");  //save updated ClientList
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");
				}
				btnRefresh.setVisible(true);  //set visibility for button
			}
			else {
				txtOutput.setText("Please select a client or client vehicle.");  //set output area text
			}

		}
		//if refresh is clicked
		else if (evt.getSource()==btnRefresh) {
			this.dispose();  //dispose current frame
			btnRefresh.setVisible(false);  //set button to invisible
			try {
				MechanicUI m = new MechanicUI(userName);  //open new frame
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "MechanicUI unreachable.");
			}
		}
		//if clear is clicked
		else if (evt.getSource()==btnClear) {
			txtOutput.setText("");  //clear output area
			//set combobox to display first item (title)
			clientBox.setSelectedIndex(0);
			serviceCarsBox.setSelectedIndex(0);
		}
		//if logout is clicked
		else if (evt.getSource()==btnLogout) {
			this.dispose();  //dispose current frame
			try {
				LoginUI l = new LoginUI();  //open LoginUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "LoginUI unreachable.");
			}
		}
		//if back image is clicked
		else if(evt.getSource()==btnBack) {
			this.dispose();  //dispose current frame
			try {
				MechanicUI m = new MechanicUI(userName);  //open new frame 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "MechanicUI unreachable.");
			}
		}
		//if exit button is clicked
		else if (evt.getSource()==btnExit) {
			System.exit(0);  //exit system
		}

	}

}

