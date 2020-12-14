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
 * Description: This program is the user interface for the inventory of the dealership, where clients can view and buy cars
 * Method List: 
 * 				InventoryUI(Person p, char type) 
 * 				void actionPerformed(ActionEvent e)
 *
 */
public class InventoryUI extends JFrame implements ActionListener{

	//private integers for width and height
	private int width, height;
	//TextPicture for title and message
	private TextPicture title, message, searchModel;
	//JPanel for buttons
	private JPanel btnPanel;
	//JTextField for user input
	private JTextField txtInput;
	//JTextArea for output
	private JTextArea txtOutput;
	//JScrollPane for output
	private JScrollPane scroller;
	//JButtons to perform various actions
	private JButton btnBuy, btnSearch, btnExit, btnBack;
	//JComboBox to display inventory
	private JComboBox inventoryBox;
	private CarList inventoryList;
	//String array JComboBox elements
	private String VIN[];  
	private Person p;
	private ClientList clients;
	private DealerList dealers;
	private char type;  
	/**
	 * @throws IOException 
	 * 
	 */
	public InventoryUI(Person p, char type) throws IOException {
		super ("Inventory");
		getContentPane().setBackground(Color.DARK_GRAY);  //set background color of frame

		//set width and height
		width = 1000;
		height = 650;

		//create ClientList 
		clients = new ClientList();
		try {
			clients.FormatReader("Clients.txt");  //get ClientList from "Clients.txt"
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
		}

		//create DealerList
		dealers = new DealerList();
		try {
			dealers.ReaderFormat("Dealers.txt");  //get DealerList for "Dealers.txt"
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
		}

		//create CarList for inventory
		inventoryList = new CarList();
		try {
			inventoryList = Inventory.loadInventory();  //load the inventory into inventoryList
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File Not Found/Unreachable. No inventory to display.");  //catch error
		}

		//create panel for buttons
		btnPanel = new JPanel();
		btnPanel.setBounds(0, 535, width, height);  //set bounds
		btnPanel.setBackground(Color.BLACK);  //set color

		//create title
		title = new TextPicture("Honda - Inventory", 400, 20);
		title.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 20));  //set font
		title.setBounds(0, 0, width, height);

		//create message
		message = new TextPicture("You must create a client account to buy a vehicle.", 575, 325);
		message.setFont(new Font("Helvetica", Font.BOLD, 15));  //set font
		message.setBounds(0, 0, width, height); 
		message.setVisible(false);  //set invisible

		//create title for input field
		searchModel = new TextPicture("Search Model", 500, 65);
		searchModel.setFont(new Font("Helvetica", Font.BOLD, 10));
		searchModel.setBounds(0, 0, width, height);

		//create output area 
		txtOutput = new JTextArea();
		txtOutput.setEditable(false);  //set uneditable
		scroller = new JScrollPane(txtOutput);  //add output area to JScrollPane
		scroller.setBounds(5, 50, 475, 475);  //set bounds for scroller
		txtOutput.setText(inventoryList.DisplayString());  //set default text to display the inventory

		VIN = new String[inventoryList.getSize()+1];  //create string to put into combobox
		VIN[0] = "Inventory";  //title for combobox
		for (int i = 1; i < VIN.length; i++) {
			VIN[i] = inventoryList.getList()[i-1].getVin();
		}
		//create combobox for inventory
		inventoryBox = new JComboBox(VIN);
		inventoryBox.setBackground(Color.red);  //set colors
		inventoryBox.setForeground(Color.white);
		inventoryBox.setBounds(500, 350, 490, 25);

		//create input field
		txtInput = new JTextField();
		txtInput.setBounds(500, 75, 490, 25);

		//create all buttons
		btnSearch = new JButton("Search Model");  //btnSearch for input field (to search for user input)
		btnSearch.setBackground(Color.RED);  //set colors
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font
		btnSearch.setBounds(650, 115, 200, 25);

		btnBuy = new JButton("Buy");
		btnBuy.setBackground(Color.red);  //set colors
		btnBuy.setForeground(Color.white);
		btnBuy.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font

		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.red);  //set colors
		btnExit.setForeground(Color.white);
		btnExit.setFont(new Font("Trajan", Font.PLAIN, 15));  //set font

		//create back button (image)
		BufferedImage buttonIcon = ImageIO.read(new File("back_arrow.png"));  //find image
		btnBack = new JButton(new ImageIcon(buttonIcon));  //set button to image
		btnBack.setBorder(BorderFactory.createEmptyBorder());  //no border
		btnBack.setContentAreaFilled(false);  //no fill

		//add buttons to panel
		btnPanel.add(btnBack);
		btnPanel.add(btnBuy);
		btnPanel.add(btnExit);

		//add components to frame
		add(btnPanel);
		add(scroller);
		add(inventoryBox);
		add(txtInput);
		add(btnSearch);
		add(searchModel);
		add(message);
		add(title);

		//add action listener to all buttons
		btnBack.addActionListener(this);
		btnBuy.addActionListener(this);
		btnExit.addActionListener(this);
		btnSearch.addActionListener(this);

		setSize(width+17, height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.p = p;  //set Person p
		this.type = type;  //set account type
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		//get selected item from inventory combobox as a string
		String choice = (String)inventoryBox.getSelectedItem();

		//if search is clicked
		if (e.getSource()==btnSearch) {
			//set output area to display searched vehicle
			txtOutput.setText(inventoryList.getList()[inventoryList.SearchForModel(txtInput.getText())].DisplayString());
			//set inventoryBox combobox to display VIN accordingly
			inventoryBox.setSelectedItem(inventoryList.getList()[inventoryList.SearchForModel(txtInput.getText())].getVin());
		}
		//if buy is clicked
		else if(e.getSource()==btnBuy) {
			if (type == 'c') {  //if the account type is 'c' ('c' = client)
				if (choice.equalsIgnoreCase("Inventory")==false) {  //if a vehicle is chosen (if selected item is not "Inventory")
					//add the vehicle to the client 
					clients.getList()[clients.uNameBinarySearch(p.getUserName())].buyCar(inventoryList.getList()[inventoryList.searchForVin(choice)]);
					//delete the vehicle from the inventory
					inventoryList.delete(inventoryList.getList()[inventoryList.searchForVin(choice)]);
					try {
						Inventory.saveInventory(inventoryList);  //save the updated inventory
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File Not Found/Unreachable. Inventory Not Saved.");  //catch error
					}
					try {
						clients.FormatWriter("Clients.txt");  //save update ClientList
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File Not Found/Unreachable.");  //catch error
					}
					this.dispose();  //dispose current frame
					try {
						InventoryUI i = new InventoryUI(p, type);  //open new InventoryUI frame
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "InventoryUI unreachable.");
					}
				}
				else {  //if vehicle is not chosen
					txtOutput.setText("PLEASE CHOOSE A VEHICLE TO BUY.");  //set text
				}
			}
			else {  //if account type is not client
				message.setVisible(true);  //display message
			}
		}
		//if back is clicked
		else if (e.getSource()==btnBack) {
			this.dispose();  //dispose current frame
			if (type == 'c') {  //if account type is 'c'
				try { 
					ClientUI c = new ClientUI(p.getUserName());  //open ClientUI
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ClientUI unreachable.");  //catch error
				}
			}
			else if (type == 'd') {  //if account type is 'd'
				try {
					DealerUI d = new DealerUI(p.getUserName());  //open DealerUI
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "DealerUI unreachable.");  //catch error
				}
			}
		}
		//if exit is clicked
		else if (e.getSource()==btnExit) {
			System.exit(0);  //exit system
		}

	}

}
