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
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This is a user interface for the dealers of the dealership.
 * Method List: 
 * 				DealerUI(String userName)
 * 				void actionPerformed(ActionEvent evt)
 *
 */
public class DealerUI extends JFrame implements ActionListener{
	//private integers for width and height
	private int width, height;
	//TextPicture for text and title
	private TextPicture title, make, model, year, price, issue, status, VIN, message;
	//JPanel for buttons
	private JPanel btnPanel;
	//JTextArea for output
	private JTextArea txtOutput;
	//JTextField for input
	private JTextField inputMake, inputModel, inputYear, inputPrice, inputIssue, inputStatus, inputVIN;
	//JScrollPane for output
	private JScrollPane scroller;
	//JButton to perform various actions
	private JButton btnInventory, btnViewInventory, btnVehicleRecord, btnAddVehicle, btnDeleteInventory, btnChangeVehicle, btnDeleteClient, btnViewClientInfo, btnChangeClientInfo, btnViewVehicle, btnChangeClientCar, btnDeleteVehicle, btnViewInfo, btnChangeInfo, btnLogout, btnClear, btnExit, btnBack, btnOK1, btnOK2, btnOK3;
	private ClientList c;
	private DealerList d;
	private CarList inventoryList;
	//String arrays for JComboBox elements and username
	private String cUserName[], inventoryModel[], userName;
	//JComboBox to display clients, client's vehicles (by VIN), and inventory (by VIN)
	private JComboBox clientsBox, clientVehicleBox, inventoryBox;

	/**
	 * @throws IOException 
	 * 
	 */
	public DealerUI(String userName) throws IOException {
		super ("Dealer Interface");
		getContentPane().setBackground(Color.DARK_GRAY);  //set background color of frame

		//initialize widht and height
		width = 1000;
		height = 650;

		//create a new ClientList
		c = new ClientList();
		c.FormatReader("Clients.txt");  //read ClientList from a file

		//create a new DealerList
		d = new DealerList();
		d.ReaderFormat("Dealers.txt");  //read DealerList from a file

		inventoryList = Inventory.loadInventory();  //load inventory

		//create panel for buttons
		btnPanel = new JPanel();
		btnPanel.setBounds(0, 540, width, height);
		btnPanel.setBackground(Color.BLACK);

		//create title 
		title = new TextPicture("Honda - Dealer Page", 410, 20);
		title.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 20));
		title.setBounds(0, 0, height, width);

		//create TextPicture: make
		make = new TextPicture("Enter Make", 5, 290);
		make.setFont(new Font("Helvetica", Font.PLAIN, 10));
		make.setBounds(0, 0, height, width);

		//create TextPicture: model
		model = new TextPicture("Enter Model", 5, 365);
		model.setFont(new Font("Helvetica", Font.PLAIN, 10));
		model.setBounds(0, 0, height, width);

		//create TextPicture: year
		year = new TextPicture("Enter Year", 5, 440);
		year.setFont(new Font("Helvetica", Font.PLAIN, 10));
		year.setBounds(0, 0, height, width);

		//create TextPicture: price
		price = new TextPicture("Enter Price", 255, 290);
		price.setFont(new Font("Helvetica", Font.PLAIN, 10));
		price.setBounds(0, 0, height, width);

		//create TextPicture: issue
		issue = new TextPicture("Enter Issue", 255, 365);
		issue.setFont(new Font("Helvetica", Font.PLAIN, 10));
		issue.setBounds(0, 0, height, width);

		//create TextPicture: status
		status = new TextPicture("Enter Status", 255, 440);
		status.setFont(new Font("Helvetica", Font.PLAIN, 10));
		status.setBounds(0, 0, height, width);

		//create TextPicture: VIN
		VIN = new TextPicture("Enter VIN", 130, 490);
		VIN.setFont(new Font("Helvetica", Font.PLAIN, 10));
		VIN.setBounds(0, 0, height, width);

		//create message
		message = new TextPicture("Please fill any unfilled fields.", 125, 225);
		message.setFont(new Font("Arial", Font.BOLD, 15));
		message.setBounds(0, 0, height, width);
		message.setVisible(false);

		//create input fields
		inputMake = new JTextField();
		inputMake.setBounds(5, 300, 200, 25);

		inputModel = new JTextField();
		inputModel.setBounds(5, 375, 200, 25);

		inputYear = new JTextField();
		inputYear.setBounds(5, 450, 200, 25);

		inputPrice = new JTextField();
		inputPrice.setBounds(255, 300, 200, 25);

		inputIssue = new JTextField();
		inputIssue.setBounds(255, 375, 200, 25);

		inputStatus = new JTextField();
		inputStatus.setBounds(255, 450, 200, 25);

		inputVIN = new JTextField();
		inputVIN.setBounds(130, 500, 200, 25);

		//create output area
		txtOutput = new JTextArea();
		txtOutput.setEditable(false);
		scroller = new JScrollPane(txtOutput);
		scroller.setBounds(500, 40, 475, 475);

		cUserName = new String[c.getSize()+1];  //create string to put into combobox
		cUserName[0] = "Clients";  //title for combobox
		for (int i = 1; i < cUserName.length; i++) {
			cUserName[i] = c.getList()[i-1].getUserName();
		}
		//create clientsBox
		clientsBox = new JComboBox(cUserName);
		clientsBox.setBackground(Color.RED);
		clientsBox.setForeground(Color.WHITE);
		clientsBox.setBounds(0, 40, 350, 25);

		//create button for clientsBox
		btnOK1 = new JButton("OK");
		btnOK1.setBackground(Color.red);
		btnOK1.setForeground(Color.white);
		btnOK1.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnOK1.setBounds(375, 40, 70, 25);

		//create ClientVehicleBox
		clientVehicleBox = new JComboBox();
		clientVehicleBox.setBackground(Color.RED);
		clientVehicleBox.setForeground(Color.white);
		clientVehicleBox.setBounds(0, 100, 350, 25);
		clientVehicleBox.setVisible(false);

		//create button for clientVehicleBox
		btnOK2 = new JButton("OK");
		btnOK2.setBackground(Color.RED);
		btnOK2.setForeground(Color.white);
		btnOK2.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnOK2.setBounds(375, 100, 70, 25);
		btnOK2.setVisible(false);

		inventoryModel = new String[inventoryList.getSize()+1];  //create string to put into combobox
		inventoryModel[0] = "Inventory";  //title for combobox
		for (int i = 1; i < inventoryModel.length; i++) {
			inventoryModel[i] = inventoryList.getList()[i-1].getVin();
		}
		//create inventoryBox
		inventoryBox = new JComboBox(inventoryModel);
		inventoryBox.setBackground(Color.red);
		inventoryBox.setForeground(Color.white);
		inventoryBox.setBounds(0, 150, 350, 25);

		//create button for inventoryBox
		btnOK3 = new JButton("OK");
		btnOK3.setBackground(Color.RED);
		btnOK3.setForeground(Color.white);
		btnOK3.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnOK3.setBounds(375, 150, 70, 25);

		//create all buttons
		btnInventory = new JButton("Inventory");
		btnInventory.setBackground(Color.red);
		btnInventory.setForeground(Color.WHITE);
		btnInventory.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnViewInventory = new JButton("View Inventory");
		btnViewInventory.setBackground(Color.RED);
		btnViewInventory.setForeground(Color.WHITE);
		btnViewInventory.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnVehicleRecord = new JButton("Vehicle Record");
		btnVehicleRecord.setBackground(Color.red);
		btnVehicleRecord.setForeground(Color.white);
		btnVehicleRecord.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnVehicleRecord.setVisible(false);

		btnAddVehicle = new JButton("Add Vehicle to Inventory");
		btnAddVehicle.setBackground(Color.RED);
		btnAddVehicle.setForeground(Color.WHITE);
		btnAddVehicle.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnDeleteInventory = new JButton("Delete Vehicle");
		btnDeleteInventory.setBackground(Color.red);
		btnDeleteInventory.setForeground(Color.WHITE);
		btnDeleteInventory.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnDeleteInventory.setVisible(false);

		btnChangeVehicle = new JButton("Update Vehicle");
		btnChangeVehicle.setBackground(Color.red);
		btnChangeVehicle.setForeground(Color.white);
		btnChangeVehicle.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnChangeVehicle.setVisible(false);

		btnDeleteClient = new JButton("Delete Client");
		btnDeleteClient.setBackground(Color.red);
		btnDeleteClient.setForeground(Color.white);
		btnDeleteClient.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnDeleteClient.setVisible(false);

		btnViewClientInfo = new JButton("View Client Information");
		btnViewClientInfo.setBackground(Color.RED);
		btnViewClientInfo.setForeground(Color.white);
		btnViewClientInfo.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnViewClientInfo.setVisible(false);

		btnChangeClientInfo = new JButton("Change Client Information");
		btnChangeClientInfo.setBackground(Color.RED);
		btnChangeClientInfo.setForeground(Color.white);
		btnChangeClientInfo.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnChangeClientInfo.setVisible(false);

		btnViewVehicle = new JButton("View Vehicle");
		btnViewVehicle.setBackground(Color.red);
		btnViewVehicle.setForeground(Color.white);
		btnViewVehicle.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnViewVehicle.setVisible(false);

		btnChangeClientCar = new JButton("Update Client Vehicle");
		btnChangeClientCar.setBackground(Color.RED);
		btnChangeClientCar.setForeground(Color.WHITE);
		btnChangeClientCar.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnChangeClientCar.setVisible(false);

		btnDeleteVehicle = new JButton("Delete Client Vehicle");
		btnDeleteVehicle.setBackground(Color.RED);
		btnDeleteVehicle.setForeground(Color.white);
		btnDeleteVehicle.setFont(new Font("Trajan", Font.PLAIN, 15));
		btnDeleteVehicle.setVisible(false);

		btnViewInfo = new JButton("View Personal Information");
		btnViewInfo.setBackground(Color.red);
		btnViewInfo.setForeground(Color.WHITE);
		btnViewInfo.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnChangeInfo = new JButton("Change Personal Information");
		btnChangeInfo.setBackground(Color.red);
		btnChangeInfo.setForeground(Color.WHITE);
		btnChangeInfo.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnLogout = new JButton("Logout");
		btnLogout.setBackground(Color.red);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnClear = new JButton("Clear");
		btnClear.setBackground(Color.red);
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Trajan", Font.PLAIN, 15));

		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.red);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Trajan", Font.PLAIN, 15));

		BufferedImage buttonIcon = ImageIO.read(new File("back_arrow.png"));
		btnBack = new JButton(new ImageIcon(buttonIcon));
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setContentAreaFilled(false);
		btnBack.setVisible(false);

		//add buttons into panel for button
		btnPanel.add(btnBack);
		btnPanel.add(btnInventory);
		btnPanel.add(btnViewInventory);
		btnPanel.add(btnVehicleRecord);
		btnPanel.add(btnAddVehicle);
		btnPanel.add(btnDeleteInventory);
		btnPanel.add(btnChangeVehicle);
		btnPanel.add(btnDeleteClient);
		btnPanel.add(btnViewClientInfo);
		btnPanel.add(btnChangeClientInfo);
		btnPanel.add(btnViewVehicle);
		btnPanel.add(btnChangeClientCar);
		btnPanel.add(btnDeleteVehicle);
		btnPanel.add(btnViewInfo);
		btnPanel.add(btnChangeInfo);
		btnPanel.add(btnClear);
		btnPanel.add(btnLogout);
		btnPanel.add(btnExit);

		//add components to frame
		add(btnPanel);
		add(inputMake);
		add(inputModel);
		add(inputYear);
		add(inputPrice);
		add(inputIssue);
		add(inputStatus);
		add(inputVIN);
		add(scroller);
		add(clientsBox);
		add(clientVehicleBox);
		add(inventoryBox);
		add(btnOK1);
		add(btnOK2);
		add(btnOK3);
		add(title);
		add(message);
		add(make);
		add(model);
		add(year);
		add(price);
		add(issue);
		add(status);
		add(VIN);

		//add action listener to all buttons
		btnInventory.addActionListener(this);
		btnViewInventory.addActionListener(this);
		btnVehicleRecord.addActionListener(this);
		btnAddVehicle.addActionListener(this);
		btnDeleteInventory.addActionListener(this);
		btnChangeVehicle.addActionListener(this);
		btnDeleteClient.addActionListener(this);
		btnViewClientInfo.addActionListener(this);
		btnChangeClientInfo.addActionListener(this);
		btnViewInfo.addActionListener(this);
		btnChangeInfo.addActionListener(this);
		btnViewVehicle.addActionListener(this);
		btnChangeClientCar.addActionListener(this);
		btnDeleteVehicle.addActionListener(this);
		btnClear.addActionListener(this);
		btnLogout.addActionListener(this);
		btnExit.addActionListener(this);
		btnBack.addActionListener(this);
		btnOK1.addActionListener(this);
		btnOK2.addActionListener(this);
		btnOK3.addActionListener(this);

		setSize(width+17, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.userName = userName;  //save dealer's username (inputted from LoginUI)
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evt) {
		//get selected items from combobox as strings
		String client = (String)clientsBox.getSelectedItem();
		String clientVehicle = (String)clientVehicleBox.getSelectedItem();
		String inventoryVehicle = (String)inventoryBox.getSelectedItem();

		//if ok button for first combobox is clicked
		if (evt.getSource()==btnOK1) {
			if (client.equalsIgnoreCase("Clients")==false) {  //if selected item is not "Clients" from clientsBox
				//set visibility to components
				btnInventory.setVisible(false);
				btnViewInventory.setVisible(false);
				btnVehicleRecord.setVisible(false);
				btnAddVehicle.setVisible(false);
				btnDeleteInventory.setVisible(false);
				btnChangeVehicle.setVisible(false);
				btnChangeInfo.setVisible(false);
				btnViewInfo.setVisible(false);
				btnOK3.setVisible(false);
				inventoryBox.setVisible(false);
				btnDeleteClient.setVisible(true);
				btnViewClientInfo.setVisible(true);
				btnChangeClientInfo.setVisible(true);
				btnBack.setVisible(true);
				clientVehicleBox.setVisible(true);
				btnOK2.setVisible(true);

				//add elements for clientVehicleBox
				clientVehicleBox.removeAllItems();
				String cars[] = new String[c.getList()[c.uNameBinarySearch(client)].getcList().getSize()+1];
				Car clientCars[] = c.getList()[c.uNameBinarySearch(client)].getcList().getList();

				cars[0] = "Client Vehicles";
				for (int i = 1; i < cars.length; i++) {
					cars[i] = clientCars[i-1].getVin();
				}
				for (int i = 0; i < cars.length; i++) {
					clientVehicleBox.addItem(cars[i]);  //add items (elements) individually into combobox
				} 
			}
			else {  //if seleted item is "Clients"
				//set visibility to components
				btnInventory.setVisible(true);
				btnViewInventory.setVisible(true);
				btnAddVehicle.setVisible(true);
				btnChangeInfo.setVisible(true);
				btnViewInfo.setVisible(true);
				btnOK3.setVisible(true);
				inventoryBox.setVisible(true);
				btnDeleteClient.setVisible(false);
				btnViewClientInfo.setVisible(false);
				btnChangeClientInfo.setVisible(false);
				btnBack.setVisible(false);
				clientVehicleBox.setVisible(false);
				btnOK2.setVisible(false);
				btnViewVehicle.setVisible(false);
				btnChangeClientCar.setVisible(false);
				btnDeleteVehicle.setVisible(false);
			}

		}
		//if ok button for second combobox is clicked
		else if (evt.getSource()==btnOK2) {
			if (client.equalsIgnoreCase("Clients")==false) {  //if selected item for clients combobox is not "Clients"
				if (clientVehicle.equalsIgnoreCase("Client Vehicles")==false) {  //if selected item is not "Client Vehicles" from clientVehicleBox
					btnDeleteVehicle.setVisible(true);
					btnChangeClientCar.setVisible(true);
					btnViewVehicle.setVisible(true);

					//set text for all input fields
					inputMake.setText(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getBrand());
					inputModel.setText(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getModel());
					inputYear.setText(Integer.toString(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getYear()));
					inputPrice.setText(Double.toString(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getPrice()));
					inputIssue.setText(Character.toString(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getIssue()));
					inputStatus.setText(Character.toString(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getStatus()));
					inputVIN.setText(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].getVin());
				}
				else {  //if it is "Client Vehicles"
					btnDeleteVehicle.setVisible(false);
					btnChangeClientCar.setVisible(false);
					btnViewVehicle.setVisible(false);
				}
			}
			else {  //if it is "Clients"
				txtOutput.setText("Please select a client.");
			}
		}
		//if ok button for 3rd combobox is clicked
		else if (evt.getSource()==btnOK3) {  
			if (inventoryVehicle.equalsIgnoreCase("Inventory")==false) {  //if selected item is not "Inventory" from inventoryBox
				btnBack.setVisible(true);
				btnVehicleRecord.setVisible(true);
				btnDeleteInventory.setVisible(true);
				btnChangeVehicle.setVisible(true);
				btnViewInfo.setVisible(false);
				btnChangeInfo.setVisible(false);

				//set text for all input fields
				inputMake.setText(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getBrand());
				inputModel.setText(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getModel());
				inputYear.setText(Integer.toString(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getYear()));
				inputPrice.setText(Double.toString(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getPrice()));
				inputIssue.setText(Character.toString(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getIssue()));
				inputStatus.setText(Character.toString(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getStatus()));
				inputVIN.setText(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].getVin());
			}
			else {  //if it is "Inventory"
				btnBack.setVisible(false);
				btnVehicleRecord.setVisible(false);
				btnDeleteInventory.setVisible(false);
				btnChangeVehicle.setVisible(false);
				btnViewInfo.setVisible(true);
				btnChangeInfo.setVisible(true);
			}
		}
		//if view personal info is clicked
		else if (evt.getSource()==btnViewInfo) {
			txtOutput.setText(d.getDealer()[d.search(userName)].toString());  //set output text to personal info
		}
		//if view inventory is clicked
		else if (evt.getSource()==btnViewInventory) {
			txtOutput.setText(inventoryList.DisplayString());  //set output text to inventory
		}
		//if vehicle record is clicked
		else if (evt.getSource()==btnVehicleRecord) {
			//if selected item in inventory combobox is not "Inventory"
			if (inventoryVehicle.equalsIgnoreCase("Inventory")==false) {
				txtOutput.setText(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)].DisplayString());  //set text output to vehicle info
			}
			else {  //if it is "Inventory"
				txtOutput.setText("Please select a vehicle from the inventory.");  //set error message in output area
			}  
		}
		//if add vehicle to inventory is clicked
		else if (evt.getSource()==btnAddVehicle) {
			//if there is missing info in fields
			if (((inputMake.getText().trim().equals("")) || (inputModel.getText().trim().equals("")) || (inputYear.getText().trim().equals("")) || (inputPrice.getText().trim().equals("")) || (inputIssue.getText().trim().equals("")) || (inputStatus.getText().trim().equals("")))==true) {
				if (inputMake.getText().trim().equals("")) {  //if nothing is inputted
					inputMake.setBackground(new Color(255, 183, 187));  //set color 
				}
				if (inputModel.getText().trim().equals("")) {
					inputModel.setBackground(new Color(255, 183, 187));
				}
				if (inputYear.getText().trim().equals("")) {
					inputYear.setBackground(new Color(255, 183, 187));
				}
				if (inputPrice.getText().trim().equals("")) {
					inputPrice.setBackground(new Color(255, 183, 187));
				}
				if (inputIssue.getText().trim().equals("")) {
					inputIssue.setBackground(new Color(255, 183, 187));
				}
				if (inputStatus.getText().trim().equals("")) {
					inputStatus.setBackground(new Color(255, 183, 187));
				}
				if (inputVIN.getText().trim().equals("")) {
					inputVIN.setBackground(new Color(255, 183, 187));
				}
				message.setVisible(true);  //display message
			}
			else {  //if all fields have inputs
				String s = inputMake.getText() + "," + inputModel.getText() + "," + inputYear.getText() + "," + inputPrice.getText() + "," + inputIssue.getText() + "," + inputStatus.getText() + "," + inputVIN.getText();
				Car newCar = new Car();
				newCar.processRecord(s);  //call processRecord to process String into Car object
				inventoryList.insert(newCar);  //add a car to inventory

				try {
					Inventory.saveInventory(inventoryList);  //save to a text file
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
				}
				this.dispose();  //dispose current frame
				try {
					DealerUI d = new DealerUI(userName);  //open new frame
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "DealerUI unreachable.");  //catch error
				}
			}
		}
		//if delete vehicle (from inventory) is clicked
		else if (evt.getSource()==btnDeleteInventory) {
			//if selected item in inventory box is not "Inventory"
			if (inventoryVehicle.equalsIgnoreCase("Inventory")==false) {
				inventoryList.delete(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)]);  //delete vehicle from inventory

				try {
					Inventory.saveInventory(inventoryList);  //save updated inventory
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File Not Found/Unreachable");  //catch error
				}
				this.dispose();  //dipose current frame
				try {
					DealerUI d = new DealerUI(userName);  //open new frame
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "DealerUI unreachable.");  //catch error
				}
			}
			else {  //if it is "Inventory"
				txtOutput.setText("Please select a vehicle from the inventory.");
			}
		}
		//if update vehicle (from inventory) is clicked
		else if (evt.getSource()==btnChangeVehicle) {
			//if there is missing info in fields
			if (((inputMake.getText().trim().equals("")) || (inputModel.getText().trim().equals("")) || (inputYear.getText().trim().equals("")) || (inputPrice.getText().trim().equals("")) || (inputIssue.getText().trim().equals("")) || (inputStatus.getText().trim().equals("")))==true) {
				if (inputMake.getText().trim().equals("")) {  //if nothing is inputted
					inputMake.setBackground(new Color(255, 183, 187));  //set color
				}
				if (inputModel.getText().trim().equals("")) {
					inputModel.setBackground(new Color(255, 183, 187));
				}
				if (inputYear.getText().trim().equals("")) {
					inputYear.setBackground(new Color(255, 183, 187));
				}
				if (inputPrice.getText().trim().equals("")) {
					inputPrice.setBackground(new Color(255, 183, 187));
				}
				if (inputIssue.getText().trim().equals("")) {
					inputIssue.setBackground(new Color(255, 183, 187));
				}
				if (inputStatus.getText().trim().equals("")) {
					inputStatus.setBackground(new Color(255, 183, 187));
				}
				if (inputVIN.getText().trim().equals("")) {
					inputVIN.setBackground(new Color(255, 183, 187));
				}
				message.setVisible(true);  //display message
			}
			//if all fields are filled in
			else {
				//if selected item in inventory combobox is not "Inventory"
				if (inventoryVehicle.equalsIgnoreCase("Inventory")==false) {
					Car newCar = new Car();
					String str = inputMake.getText() + "," + inputModel.getText() + "," + inputYear.getText() + "," + inputPrice.getText() + "," + inputIssue.getText() + "," + inputStatus.getText() + "," + inputVIN.getText();
					newCar.processRecord(str);  //process string into newCar object
					inventoryList.change(inventoryList.getList()[inventoryList.searchForVin(inventoryVehicle)], newCar);  //change vehicle

					try {
						Inventory.saveInventory(inventoryList);  //save updated inventory
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");
					}
					this.dispose();  //dispose current frame
					try {
						DealerUI d = new DealerUI(userName);  //open new frame
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "DealerUI unreachable.");
					}
				}
				else {  //if it is "Inventory"
					txtOutput.setText("Please select a vehicle from the inventory.");
				}
			}
		}
		//if update client vehicle is clicked
		else if (evt.getSource()==btnChangeClientCar) {
			//if there is missing info in fields
			if (((inputMake.getText().trim().equals("")) || (inputModel.getText().trim().equals("")) || (inputYear.getText().trim().equals("")) || (inputPrice.getText().trim().equals("")) || (inputIssue.getText().trim().equals("")) || (inputStatus.getText().trim().equals("")))==true) {
				if (inputMake.getText().trim().equals("")) {  //if nothing is inputted
					inputMake.setBackground(new Color(255, 183, 187));  //set color
				}
				if (inputModel.getText().trim().equals("")) {
					inputModel.setBackground(new Color(255, 183, 187));
				}
				if (inputYear.getText().trim().equals("")) {
					inputYear.setBackground(new Color(255, 183, 187));
				}
				if (inputPrice.getText().trim().equals("")) {
					inputPrice.setBackground(new Color(255, 183, 187));
				}
				if (inputIssue.getText().trim().equals("")) {
					inputIssue.setBackground(new Color(255, 183, 187));
				}
				if (inputStatus.getText().trim().equals("")) {
					inputStatus.setBackground(new Color(255, 183, 187));
				}
				if (inputVIN.getText().trim().equals("")) {
					inputVIN.setBackground(new Color(255, 183, 187));
				}
				message.setVisible(true);  //display message	
			}
			//if all fields are inputted
			else {
				//if selected item is not "Clients" in clients box and "Client Vehicles" in client vehicles box
				if (client.equalsIgnoreCase("Clients")==false  && clientVehicle.equalsIgnoreCase("Client Vehicles")==false) {
					//set components of object Car
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setBrand(inputMake.getText());
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setModel(inputModel.getText());
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setYear(Integer.parseInt(inputYear.getText()));
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setPrice(Double.parseDouble(inputPrice.getText()));
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setIssue(inputIssue.getText().charAt(0));
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setStatus(inputStatus.getText().charAt(0));
					c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].setVin(inputVIN.getText());;

					try {
						c.FormatWriter("Clients.txt");  //save client updated info
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
					}
					this.dispose();  //dispose current frame
					try {
						DealerUI d = new DealerUI(userName);  //create new frame
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "DealerUI unreachable.");  //catch error
					}
				}
				else {  //if it is "Clients" and/or "Client Vehicles"
					txtOutput.setText("Please select a client or client's vehicle.");
				}
			}
		}
		//if view vehicle is clicked
		else if (evt.getSource()==btnViewVehicle) {
			if (client.equalsIgnoreCase("Clients")==false && clientVehicle.equalsIgnoreCase("Client Vehicles")==false) {
				//set output area to vehicle's info
				txtOutput.setText(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)].DisplayString());
			}
			else {
				txtOutput.setText("Please select a client or client's vehicle.");
			}
		}
		//if delete client vehicle is clicked
		else if (evt.getSource()==btnDeleteVehicle) {
			if (client.equalsIgnoreCase("Clients")==false && clientVehicle.equalsIgnoreCase("Client Vehicles")==false) {
				//search for vehicle to delete by VIN
				c.getList()[c.uNameBinarySearch(client)].getcList().delete(c.getList()[c.uNameBinarySearch(client)].getcList().getList()[c.getList()[c.uNameBinarySearch(client)].getcList().searchForVin(clientVehicle)]);
				try {
					c.FormatWriter("Clients.txt");  //save updated ClientList
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
				}
				this.dispose();  //dipose current frame
				try {
					DealerUI d = new DealerUI(userName);  //create new frame
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "DealerUI unreachable.");  //catch error
				}
			}
			else {
				txtOutput.setText("Please select a client or client's vehicle.");
			}
		}
		//if inventory is clicked
		else if (evt.getSource()==btnInventory) {
			this.dispose();  //dispose current frame
			try {
				InventoryUI i = new InventoryUI(d.getDealer()[d.search(userName)], 'd');  //open InventoryUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "InventoryUI unreachable.");
			}
		}
		//if delete client is clicked
		else if (evt.getSource()==btnDeleteClient) {
			if (client.equalsIgnoreCase("Clients")==false) {
				c.delete(c.getList()[c.uNameBinarySearch(client)]);  //delete client

				try {
					c.FormatWriter("Clients.txt");  //save updated ClientList
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");
				}

				this.dispose();  //dispose current frame
				try {
					DealerUI d = new DealerUI(userName);  //open new frame
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "DealerUI unreachable.");
				}
			}
			else {
				txtOutput.setText("Please select a client to delete.");
			}

		}
		//if view client info is clicked
		else if (evt.getSource()==btnViewClientInfo) {
			if (client.equalsIgnoreCase("Clients")==false) {
				txtOutput.setText(c.getList()[c.uNameBinarySearch(client)].toString());  //set text of output area to client info.
			}
			else {
				txtOutput.setText("Please select a client.");
			}
		}
		//if change client info is clicked
		else if (evt.getSource()==btnChangeClientInfo) {
			if (client.equalsIgnoreCase("Clients")==false) {
				try {
					UserInfoUI u = new UserInfoUI(c.getList()[c.uNameBinarySearch(client)], 'c', false);  //open UserInfoUI 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "UserInfoUI unreachable.");
				}
			}
			else {
				txtOutput.setText("Please select a client.");
			}
		}
		//if back is clicked
		else if (evt.getSource()==btnBack) {
			this.dispose();  //dispose current frame
			try {
				DealerUI d = new DealerUI(userName);  //open new frame
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "DealerUI unreachable.");
			}
		}
		//if change personal info is clicked
		else if (evt.getSource()==btnChangeInfo) {
			this.dispose();  //dispose current frame
			try {
				UserInfoUI u = new UserInfoUI(d.getDealer()[d.search(userName)], 'd', false);  //open UserInfoUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "UserInfoUI unreachable.");
			}
		}
		//if view personal info is clicked
		else if (evt.getSource()==btnViewInfo) {
			txtOutput.setText(d.getDealer()[d.search(userName)].toString());  //set text to personal info.
		}
		//if clear is clicked
		else if (evt.getSource()==btnClear) {
			txtOutput.setText("");  //clear output area
		}
		//if logout is clicked
		else if (evt.getSource()==btnLogout) {
			this.dispose();  //dispose current frame
			try {
				LoginUI login = new LoginUI();  //open LoginUI
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "LoginUI unreachable.");
			}
		}
		//if exit is clicked
		else if (evt.getSource()==btnExit) {
			System.exit(0);  //exit system
		}

	}
	public static void main(String[] args) throws IOException {
		DealerUI c = new DealerUI("hello");
	}

}
