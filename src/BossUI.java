/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class that creates a boss user interface screen for the Honda Dealership program and allows the boss to edit the company's details
 *Method List: 
 *		BossUI() 
 *		static void main(String[] args)
 *		void UpdateCBClients()
 *		void UpdateCBDealers() 
 *		void UpdateCBMechs()
 *		void UpdateCBInventory() 
 *		void HirePrompt(Person pers)
 *		void actionPerformed(ActionEvent e)
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BossUI extends JFrame implements ActionListener {
	//private data
	private ImagePicture bg;
	private JComboBox cbDealers, cbClients, cbMechs, cbInventory;
	private TextPicture title, textStatus, textRecords1, textDealers, textClients, textMechs, textInv, textSelected, textList, textSearch, textEnterModel, textEnterVIN, textEnterPrice, textEnterYear, textEnterStatus, textEnterIssue;
	private JTextField txtSearch, txtEnterModel, txtEnterVIN, txtEnterPrice, txtEnterYear, txtEnterStatus, txtEnterIssue;
	private Picture searchBorder, insertBorder;
	private JButton btnExit, btnHire, btnDelete, btnEdit, btnDeleteCar, btnSignOut, btnSearchClient, btnSearchDealer, btnSearchMech, btnSearchInv, btnInsertInv, btnInsertCliCar, btnViewInv, btnViewCli, btnViewMechs, btnViewDealers;
	private JTextArea recordArea1;
	private JScrollPane scroller;
	private int width, height, btnWid, index;
	private ClientList clients;
	private DealerList dealers;
	private MechanicList mechs;
	private CarList inventory;
	private final String defaultStatus = "";
	private String d[], c[], m[], inv[];
	public BossUI(Boss b) throws IOException {
		super("Boss");  // title for the frame

		//initializing frame width and height and button width 
		width =1000;
		height = 518;
		btnWid = 180;

		//creating client, dealer, and mechanic lists and loading existing accounts. as well as loading inventory and creating corresponding text picture and corresponding combo box with corresponding string array
		clients = new ClientList();
		clients.FormatReader("Clients.txt");
		dealers = new DealerList();
		dealers.ReaderFormat("Dealers.txt");
		mechs = new MechanicList();
		mechs.ReaderFormat("Mechanics.txt");
		inventory = Inventory.loadInventory();
		textInv = new TextPicture("Inventory", 850, 150);
		textInv.setFont(new Font("Sans Serif", Font.BOLD, 18));
		inv=new String[inventory.getSize()+1];
		inventory.SortByModel();
		inv[0] = "Select Car";
		for (int i = 0; i < inv.length-1; i++) {
			inv[i+1] = inventory.getList()[i].getVin();
		}
		cbInventory = new JComboBox(inv);
		cbInventory.setForeground(Color.WHITE);
		cbInventory.setBackground(Color.RED);
		cbInventory.setSelectedIndex(0);

		//creating ImagePicture for background, TextPicture for title,status and setting font
		bg  = new ImagePicture(new ImageIcon("bg.png"), 0, 0);
		title = new TextPicture ("Hello, Mr. " + b.getlName() + "!", width/2-75, 20);
		title.setFont(new Font("Sans Serif", Font.BOLD, 23));
		textStatus = new TextPicture(defaultStatus, width/2-60, 470);
		textStatus.setFont(new Font("Sans Serif", Font.BOLD, 18));

		//creating buttons, text pictures, and textfields for inserting a car
		btnInsertInv = new JButton("Insert Car to Inventory");
		btnInsertInv.setBackground(Color.RED);
		btnInsertInv.setForeground(Color.white);
		btnInsertInv.setFont(new Font("Sans Serif", Font.BOLD, 10));
		btnInsertCliCar = new JButton("Insert Car to Client");
		btnInsertCliCar.setBackground(Color.RED);
		btnInsertCliCar.setForeground(Color.white);
		btnInsertCliCar.setFont(new Font("Sans Serif", Font.BOLD, 10));
		textEnterModel = new TextPicture("Enter Model", 465, 220);
		textEnterModel.setFont(new Font("Sans Serif", Font.BOLD, 11));
		textEnterPrice = new TextPicture("Enter Price", 600, 220);
		textEnterPrice.setFont(new Font("Sans Serif", Font.BOLD, 11));
		textEnterYear = new TextPicture("Enter Year", 735, 220);
		textEnterYear.setFont(new Font("Sans Serif", Font.BOLD, 11));
		textEnterVIN = new TextPicture("Enter VIN", 870, 220);
		textEnterVIN.setFont(new Font("Sans Serif", Font.BOLD, 11));
		textEnterStatus = new TextPicture("Done/Servicing", 465, 270);
		textEnterStatus.setFont(new Font("Sans Serif", Font.BOLD, 11));
		textEnterIssue = new TextPicture("Enter Issue", 600, 270);
		textEnterIssue.setFont(new Font("Sans Serif", Font.BOLD, 11));
		txtEnterModel = new JTextField();
		txtEnterYear = new JTextField();
		txtEnterPrice = new JTextField();
		txtEnterVIN = new JTextField();
		txtEnterStatus = new JTextField();
		txtEnterStatus.setEditable(false);
		txtEnterIssue = new JTextField();
		txtEnterIssue.setEditable(false);
		insertBorder = new Picture(460, 205, 515, 150);
		insertBorder.setColor(Color.red);

		//creating the string arrays and corresponding combo boxes and text pictures
		d= new String[dealers.getSize()+1];
		dealers.quickSort(0, dealers.getSize()-1);
		d[0] = "Select Dealer";
		for (int i = 0; i < d.length-1; i++) {
			d[i+1] = dealers.getDealer()[i].getUserName();
		}
		cbDealers = new JComboBox(d);
		cbDealers.setForeground(Color.WHITE);
		cbDealers.setBackground(Color.RED);
		cbDealers.setSelectedIndex(0);
		textDealers = new TextPicture("Dealers", 500, 60);
		textDealers.setFont(new Font("Sans Serif", Font.BOLD, 18));

		c= new String[clients.getSize()+1];
		clients.uNameQuickSort(clients.getList(), 0, clients.getSize()-1);
		c[0] = "Select Client";
		for (int i = 0; i < c.length-1; i++) {
			c[i+1] = clients.getList()[i].getUserName();
		}
		cbClients = new JComboBox(c);
		cbClients.setForeground(Color.WHITE);
		cbClients.setBackground(Color.RED);
		cbClients.setSelectedIndex(0);
		textClients = new TextPicture("Clients", 650, 60);
		textClients.setFont(new Font("Sans Serif", Font.BOLD, 18));

		m= new String[mechs.getSize()+1];
		mechs.quickSort(0, mechs.getSize()-1);
		m[0] = "Select Mechanic";
		for (int i = 0; i < m.length-1; i++) {
			m[i+1] = mechs.getMechanic()[i].getUserName();
		}
		cbMechs = new JComboBox(m);
		cbMechs.setForeground(Color.WHITE);
		cbMechs.setBackground(Color.RED);
		cbMechs.setSelectedIndex(0);
		textMechs = new TextPicture("Mechanics", 800, 60);
		textMechs.setFont(new Font("Sans Serif", Font.BOLD, 18));

		//creating components for searching
		textSearch = new TextPicture("Enter username or VIN and select category to search:", 470, 120);
		textSearch.setFont(new Font("Sans Serif", Font.BOLD, 13));
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Sans Serif", Font.BOLD, 11));
		btnSearchClient = new JButton("Client");
		btnSearchClient.setBackground(Color.RED);
		btnSearchClient.setForeground(Color.white);
		btnSearchClient.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnSearchDealer = new JButton("Dealer");
		btnSearchDealer.setBackground(Color.RED);
		btnSearchDealer.setForeground(Color.white);
		btnSearchDealer.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnSearchMech = new JButton("Mechanic");
		btnSearchMech.setBackground(Color.RED);
		btnSearchMech.setForeground(Color.white);
		btnSearchMech.setFont(new Font("Sans Serif", Font.BOLD, 10));
		btnSearchInv = new JButton("Inventory");
		btnSearchInv.setBackground(Color.RED);
		btnSearchInv.setForeground(Color.white);
		btnSearchInv.setFont(new Font("Sans Serif", Font.BOLD, 12));
		searchBorder = new Picture(465, 105, 350, 95);
		searchBorder.setColor(Color.RED);

		//creating buttons and corrresponding text pictures
		textSelected = new TextPicture ("Actions for selected item:", 10, 400);
		textSelected.setFont(new Font("Sans Serif", Font.BOLD, 11));
		textList = new TextPicture ("Actions for selected list:", 10, 450);
		textList.setFont(new Font("Sans Serif", Font.BOLD, 11));
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.white);
		btnExit.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnSignOut = new JButton("Sign Out");
		btnSignOut.setBackground(Color.RED);
		btnSignOut.setForeground(Color.white);
		btnSignOut.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnHire = new JButton("Hire");
		btnHire.setBackground(Color.RED);
		btnHire.setForeground(Color.white);
		btnHire.setFont(new Font("Sans Serif", Font.BOLD, 15));
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.white);
		btnDelete.setFont(new Font("Sans Serif", Font.BOLD, 10));
		btnDelete.setEnabled(false);
		btnEdit = new JButton("Edit");
		btnEdit.setBackground(Color.RED);
		btnEdit.setForeground(Color.white);
		btnEdit.setFont(new Font("Sans Serif", Font.BOLD, 10));
		btnEdit.setEnabled(false);
		btnDeleteCar = new JButton("Delete Car");
		btnDeleteCar.setBackground(Color.RED);
		btnDeleteCar.setForeground(Color.white);
		btnDeleteCar.setFont(new Font("Sans Serif", Font.BOLD, 10));
		btnDeleteCar.setEnabled(false);
		btnViewInv = new JButton ("View Inventory");
		btnViewInv.setBackground(Color.RED);
		btnViewInv.setForeground(Color.white);
		btnViewInv.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnViewCli = new JButton ("View Clients");
		btnViewCli.setBackground(Color.RED);
		btnViewCli.setForeground(Color.white);
		btnViewCli.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnViewMechs = new JButton ("View Mechanics");
		btnViewMechs.setBackground(Color.RED);
		btnViewMechs.setForeground(Color.white);
		btnViewMechs.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnViewDealers = new JButton("View Dealers");
		btnViewDealers.setBackground(Color.RED);
		btnViewDealers.setForeground(Color.white);
		btnViewDealers.setFont(new Font("Sans Serif", Font.BOLD, 12));

		//creating text areas and corresponding text pictures
		recordArea1 = new JTextArea();
		scroller = new JScrollPane(recordArea1);
		recordArea1.setEditable(false);
		recordArea1.setFont(new Font("Sans Serif", Font.BOLD, 11));

		textRecords1 = new TextPicture("Records", 10, 60);
		textRecords1.setFont(new Font("Sans Serif", Font.BOLD, 18));

		//setting bounds
		title.setBounds(0,0,width,height);
		textDealers.setBounds(0,0,width,height);
		textClients.setBounds(0,0,width,height);
		textMechs.setBounds(0,0,width,height);
		textInv.setBounds(0,0,width,height);
		textRecords1.setBounds(0,0,width,height);
		textStatus.setBounds(0, 0, width, height);
		scroller.setBounds(10, 70, 450, 300);
		btnHire.setBounds(870, 360, btnWid/2, 40);
		btnExit.setBounds(870-(btnWid-60), 410, btnWid/2, 40);
		btnSignOut.setBounds(870, 410, btnWid/2, 40);
		cbDealers.setBounds(500, 70, 130, 25);
		cbClients.setBounds(650, 70, 130, 25);
		cbMechs.setBounds(800, 70, 130, 25);
		cbInventory.setBounds(850, 160, 130, 25);
		btnDelete.setBounds(160, 380, btnWid/2, 40);
		btnEdit.setBounds(260, 380, btnWid/2, 40);
		btnDeleteCar.setBounds(200, 430, btnWid/2, 40);
		textSelected.setBounds(0,0,width,height);
		textList.setBounds(0,0,width,height);
		textSearch.setBounds(0,0,width,height);
		txtSearch.setBounds(textSearch.getxPos(), textSearch.getyPos()+10, 300, 25);
		btnSearchClient.setBounds(textSearch.getxPos(), textSearch.getyPos()+45, btnWid/2-20, 30);
		btnSearchDealer.setBounds(textSearch.getxPos()+75, textSearch.getyPos()+45, btnWid/2-15, 30);
		btnSearchMech.setBounds(textSearch.getxPos()+155, textSearch.getyPos()+45, btnWid/2-5, 30);
		btnSearchInv.setBounds(textSearch.getxPos()+245, textSearch.getyPos()+45, btnWid/2, 30);
		searchBorder.setBounds(0, 0, width, height);
		textEnterModel.setBounds(0,0,width,height);
		textEnterPrice.setBounds(0,0,width,height);
		textEnterYear.setBounds(0,0,width,height);
		textEnterVIN.setBounds(0,0,width,height);
		txtEnterModel.setBounds(textEnterModel.getxPos(), textEnterModel.getyPos()+10, 100, 20);
		txtEnterPrice.setBounds(textEnterPrice.getxPos(), textEnterModel.getyPos()+10, 100, 20);
		txtEnterYear.setBounds(textEnterYear.getxPos(), textEnterModel.getyPos()+10, 100, 20);
		txtEnterVIN.setBounds(textEnterVIN.getxPos(), textEnterModel.getyPos()+10, 100, 20);
		btnInsertInv.setBounds(textEnterModel.getxPos()+50, textEnterModel.getyPos()+90, btnWid, 20);
		btnInsertCliCar.setBounds(textEnterModel.getxPos()+60+btnWid, textEnterModel.getyPos()+90, btnWid, 20);
		insertBorder.setBounds(0, 0, width, height);
		textEnterStatus.setBounds(0, 0, width, height);
		txtEnterStatus.setBounds(textEnterStatus.getxPos(), textEnterStatus.getyPos()+10, 100, 20);
		textEnterIssue.setBounds(0, 0, width, height);
		txtEnterIssue.setBounds(textEnterIssue.getxPos(), textEnterIssue.getyPos()+10, 100, 20);
		btnViewInv.setBounds(500, 360, btnWid, 20);
		btnViewCli.setBounds(500, 385, btnWid, 20);
		btnViewMechs.setBounds(500, 410, btnWid, 20);
		btnViewDealers.setBounds(500, 435, btnWid, 20);

		//adding to frame
		add(btnViewDealers);
		add(btnViewMechs);
		add(btnViewCli);
		add(btnViewInv);
		add(txtEnterIssue);
		add(txtEnterStatus);
		add(textEnterIssue);
		add(textEnterStatus);
		add(insertBorder);
		add(btnInsertCliCar);
		add(btnInsertInv);
		add(txtEnterPrice);
		add(txtEnterYear);
		add(txtEnterVIN);
		add(txtEnterModel);
		add(textEnterModel);
		add(textEnterPrice);
		add(textEnterYear);
		add(textEnterVIN);
		add(searchBorder);
		add(btnSearchInv);
		add(btnSearchMech);
		add(btnSearchDealer);
		add(btnSearchClient);
		add(txtSearch);
		add(textSearch);
		add(textList);
		add(textSelected);
		add(btnDeleteCar);
		add(textStatus);
		add(scroller);
		add(btnEdit);
		add(btnDelete);
		add(btnHire);
		add(cbInventory);
		add(cbMechs);
		add(cbClients);
		add(cbDealers);
		add(textRecords1);
		add(btnSignOut);
		add(btnExit);
		add(textInv);
		add(textDealers);
		add(textClients);
		add(textMechs);
		add(title);
		add(bg);

		//adding action listeners
		btnExit.addActionListener(this);
		btnSignOut.addActionListener(this);
		btnHire.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDeleteCar.addActionListener(this);
		btnSearchClient.addActionListener(this);
		btnSearchDealer.addActionListener(this);
		btnSearchMech.addActionListener(this);
		btnSearchInv.addActionListener(this);
		btnInsertInv.addActionListener(this);
		btnInsertCliCar.addActionListener(this);
		btnViewInv.addActionListener(this);
		btnViewCli.addActionListener(this);
		btnViewMechs.addActionListener(this);
		btnViewDealers.addActionListener(this);

		cbDealers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbDealers = (JComboBox)e.getSource();
				//if a dealer is selected
				if(cbDealers.getSelectedIndex()>0) {
					//find the index of the selected dealer in the dealerList
					index = dealers.search((String)cbDealers.getSelectedItem());
					//display the dealer's details
					recordArea1.setText(dealers.getDealer()[index].toString());
					//enabling/disabling appropriate functions
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					btnDeleteCar.setEnabled(false);
					cbClients.setSelectedIndex(0);
					cbMechs.setSelectedIndex(0);
					cbInventory.setSelectedIndex(0);
				}
			}
		});
		cbClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbClients = (JComboBox)e.getSource();
				//if a client is selected
				if(cbClients.getSelectedIndex()>0) {
					//dind the index of the client in the client list
					index = clients.uNameBinarySearch((String)cbClients.getSelectedItem());
					//display the client's record
					recordArea1.setText(clients.getList()[index].toString());
					//enable/disable apropirate functions 
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					btnDeleteCar.setEnabled(true);
					txtEnterIssue.setEditable(true);
					txtEnterStatus.setEditable(true);
					cbDealers.setSelectedIndex(0);
					cbMechs.setSelectedIndex(0);
					cbInventory.setSelectedIndex(0);
				}
			}
		});
		cbMechs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbMechs = (JComboBox)e.getSource();
				//if a mechanic is selected
				if(cbMechs.getSelectedIndex()>0) {
					//find the index of the mechanic in the mechanic list
					index = mechs.search((String)cbMechs.getSelectedItem());
					//display the mechanic's record
					recordArea1.setText(mechs.getMechanic()[index].toString());
					//enable/disable appropriate functions
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					btnDeleteCar.setEnabled(false);
					cbClients.setSelectedIndex(0);
					cbDealers.setSelectedIndex(0);
					cbInventory.setSelectedIndex(0);
				}
			}
		});
		cbInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbInventory = (JComboBox)e.getSource();
				//if a car in the inventory has been selected
				if(cbInventory.getSelectedIndex()>0) {
					//find the index of the car in the inventory list
					index = inventory.searchForVin((String)cbInventory.getSelectedItem());
					//display the car record
					recordArea1.setText(inventory.getList()[index].DisplayString());
					//enable/disable appropriate functions
					btnDelete.setEnabled(true);
					btnDeleteCar.setEnabled(false);
					btnEdit.setEnabled(false);
					cbDealers.setSelectedIndex(0);
					cbMechs.setSelectedIndex(0);
					cbClients.setSelectedIndex(0);
				}
			}
		});

		// set size and location of frame, making it visible
		setSize(width,height); 
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		//setting default colors for text fields (white)
		txtEnterModel.setBackground(new Color(255, 255, 255));
		txtEnterPrice.setBackground(new Color(255, 255, 255));
		txtEnterYear.setBackground(new Color(255, 255, 255));
		txtEnterVIN.setBackground(new Color(255, 255, 255));
		txtEnterStatus.setBackground(new Color(255, 255, 255));
		txtEnterIssue.setBackground(new Color(255, 255, 255));
		if(cbInventory.getSelectedIndex()>0) {  //if a car in the inventory was selected
			if(e.getSource()==btnDelete) {  //if the delete button was clicked
				index = inventory.searchForVin((String)cbInventory.getSelectedItem());  //finding index
				if(!inventory.delete(inventory.getList()[index])) {  //if not deleted
					textStatus.setTitle("This car was not found in the inventory.");
				}
				else {
					//updating inventory selection box, setting the record area text to nothing, and displaying success message in the status
					UpdateCBInventory();
					recordArea1.setText("");
					textStatus.setTitle("The car was deleted from the inventory.");
					try {
						Inventory.saveInventory(inventory);  //saving inventory
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}

				}
			}
		}

		else if(cbClients.getSelectedIndex()>0) {  //if a client selected
			index = clients.uNameBinarySearch((String)cbClients.getSelectedItem());  //find index
			if(e.getSource()==btnEdit) {  ///if edit button was selected
				try {
					//opening the userinfo UI with the client and closing this screen
					new UserInfoUI (clients.getList()[index], 'c', true);  
					this.dispose();
				} catch (IOException e1) {
					JOptionPane.showConfirmDialog(null, e1.toString());
				}
			}
			else if(e.getSource()==btnDelete) {  //if delete button was selected
				if(!clients.delete(clients.getList()[index])) {  //if not deleted
					textStatus.setTitle("This car was not found in the inventory.");
				}
				else {  //delete successful, save client, update selection boc, and display success message
					try {
						clients.FormatWriter("Clients.txt");
					} catch (IOException e1) {
						textStatus.setTitle("File not found.");
					}
					UpdateCBClients();
					recordArea1.setText("");
					textStatus.setTitle("Client deleted successfully");
				}
			}
			else if (e.getSource()==btnDeleteCar) {  //if delete car button was clicked
				try {
					String vin;
					vin = JOptionPane.showInputDialog(null, "Enter VIN of the car you wish to delete.");  //prompt boss for pin

					int indexCar = clients.getList()[index].getcList().searchForVin(vin);  //finding index
					if (indexCar>-1) {  //if not found
						if(!clients.getList()[index].getcList().delete(clients.getList()[index].getcList().getList()[indexCar])) {  //if not deleted
							textStatus.setTitle("Car not deleted.");
						}
						else {  //deleted
							textStatus.setTitle("Car deleted successfully.");
							clients.FormatWriter("Clients.txt");
							recordArea1.setText(clients.getList()[index].toString());
						}
					}
					else {//car not found
						textStatus.setTitle("Car not found.");
					}
				} catch (IOException e1) {
					textStatus.setTitle("List could not be saved.");
				}
				catch (HeadlessException e1) {
					textStatus.setTitle("Invalid input.");
				}
				catch (Exception e1) {
					textStatus.setTitle("Invalid input.");
				}

			}//end if btnDelete car clicked

		}//end if client selected

		else if(cbDealers.getSelectedIndex()>0) {  //if dealer selected
			index = dealers.search((String)cbDealers.getSelectedItem());  //find index
			if(e.getSource()==btnDelete) {  //if delete button was selected
				if(!dealers.delete(dealers.getDealer()[index].getUserName())) { //if not deleted
					textStatus.setTitle("Dealer not found.");
				}
				else {//deleted, update dealers selection and save list
					try {
						UpdateCBDealers();
						dealers.WriterFormat("Dealers.txt");
						recordArea1.setText("");
						textStatus.setTitle("Dealer has been deleted successfully.");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}
			}
			else if(e.getSource()==btnEdit) {  //if edit button was selected
				try {
					//open the userInfo UI with the selected dealer, close this UI
					new UserInfoUI (dealers.getDealer()[index], 'd', true);  
					this.dispose();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		}

		else if(cbMechs.getSelectedIndex()>0) {  //if a mechanic is selected
			index = mechs.search((String)cbMechs.getSelectedItem());//finding index
			if(e.getSource()==btnDelete) {//if delete pressed
				if(!mechs.delete(mechs.getMechanic()[index].getUserName())) {  //if not deleted
					textStatus.setTitle("Mechanic not found."); 
				}
				else {//deleted, update mechanics
					try {
						UpdateCBMechs();
						mechs.WriterFormat("Mechanics.txt");
						recordArea1.setText("");
						textStatus.setTitle("Mechanic has been deleted successfully.");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}
			}
			else if(e.getSource()==btnEdit) {  //if edit button was selected
				try {
					//open a new userInfo UI with the selected mechanic, and close this UI 
					new UserInfoUI (mechs.getMechanic()[index], 'm', true);
					this.dispose();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		}

		if(e.getSource()==btnExit) { //if exit button is rpessed, close UI
			this.dispose();
		}

		else if(e.getSource()==btnSearchClient) {  //if search client button was pressed
			index = clients.uNameBinarySearch(txtSearch.getText());  //find index
			if(index>-1) {//found
				cbClients.setSelectedItem(txtSearch.getText());  //select the found client in the selection box
			}
			else {//not found
				textStatus.setTitle("Client not found.");
			}
		}
		else if(e.getSource()==btnSearchDealer) {  //search for dealer button pressed
			index = dealers.search(txtSearch.getText());  //find index
			if(index>-1) {  //index found
				cbDealers.setSelectedItem(txtSearch.getText());  //select the found dealer in the selection box
			}
			else {  //index not found
				textStatus.setTitle("Dealer not found.");
			}
		}
		else if(e.getSource()==btnSearchMech) {  //mechanic search button pressed
			index = mechs.search(txtSearch.getText()); //find index
			if(index>-1) {  //if found
				cbMechs.setSelectedItem(txtSearch.getText());  //selecting found mechanic
			}
			else {//not found
				textStatus.setTitle("Mechanic not found.");
			}
		}
		else if(e.getSource()==btnSearchInv) {//if search inventory button pressed
			index = inventory.searchForVin(txtSearch.getText()); //find index
			if((index>-1) && (inventory.getList()[index].getVin().equals(txtSearch.getText()))){  //found
				cbInventory.setSelectedItem(txtSearch.getText()); //selecting found car in the inventory 
			}
			else {  //not found
				textStatus.setTitle("Car not found.");
			}
		}

		else if(e.getSource()==btnHire) {// hire button pressed
			try {
				//prompting user for whether to hire a mechanic or a dealer
				Object[] options1 = { "Dealer", "Mechanic"};
				JPanel panel = new JPanel();
				panel.add(new JLabel("Please Select a type of employee to hire."));
				int result = JOptionPane.showOptionDialog(null, panel, "Select Status",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options1, null);    

				if (result == JOptionPane.YES_OPTION){  //if dealer selected
					//create a new dealer and pass it thru the hireprompt method to prompt the user for employee's info
					Dealer newDealer = new Dealer();
					HirePrompt(newDealer);
					//if any input was entered null
					if((newDealer.getfName()==null) || (newDealer.getlName()==null) || (newDealer.getUserName()==null) || (newDealer.getAddress()==null) || (newDealer.getPhoneNum()==null) || (newDealer.getPswd()==null) || (newDealer.getPswd().equals("")) ){
						textStatus.setTitle("Selection not valid.");
					}
					else {//if input was proper
						if(dealers.insert(newDealer)) {  //if inserted, update the selection box, save the dealer list, and display success message
							UpdateCBDealers();
							dealers.WriterFormat("Dealers.txt");
							textStatus.setTitle(newDealer.getUserName() + " has been added.");
						}
						else {//insert error
							textStatus.setTitle("Could not add.");
						}
					}

				}
				if (result == JOptionPane.NO_OPTION){//mechanic selected
					//create a new dealer and pass it thru the hireprompt method to prompt the user for employee's info
					Mechanic newMech = new Mechanic ();
					HirePrompt(newMech);
					//if any input was entered null
					if((newMech.getfName()==null) || (newMech.getlName()==null) || (newMech.getUserName()==null) || (newMech.getAddress()==null) || (newMech.getPhoneNum()==null) || (newMech.getPswd()==null) || (newMech.getPswd().equals("")) ){
						textStatus.setTitle("Selection not valid.");
					}
					else {//if input proper
						if(mechs.insert(newMech)) {//if inserted, update the selection box, save the mechanic list, and display success message
							UpdateCBMechs();
							mechs.WriterFormat("Mechanics.txt");
							textStatus.setTitle(newMech.getUserName() + " has been added.");
						}
						else {//could not insert
							textStatus.setTitle("Could not add.");
						}
					}
				}
			} catch (HeadlessException e1) {
				textStatus.setTitle("Selection not valid.");
			} catch (IOException e1) {
				textStatus.setTitle("Could not save list.");
			}  
			catch (Exception e1) {
				textStatus.setTitle("Selection not valid.");
			}
		}
		//if view buttons were selected, display corresponding list in the record area
		else if(e.getSource()==btnViewInv) {
			recordArea1.setText(inventory.DisplayString());
		}		
		else if (e.getSource()==btnViewCli) {
			recordArea1.setText(clients.toString());
		}		
		else if (e.getSource()==btnViewMechs) {
			recordArea1.setText(mechs.toString());
		}
		else if (e.getSource()==btnViewDealers) {
			recordArea1.setText(dealers.toString());
		}
		//if insert car to inventory button was clicked
		else if(e.getSource()==btnInsertInv) {
			//store all the inputted text in String variables
			String model = txtEnterModel.getText();
			String price = txtEnterPrice.getText();
			String year = txtEnterYear.getText();
			String vin = txtEnterVIN.getText();
			//for any field left empty, highlight the field and display error message
			if(model.equals("")){  
				txtEnterModel.setBackground(new Color(255, 183, 187));
				textStatus.setTitle("Please fill the highlighted fields and retry.");
			}
			if (price.equals("")) {
				txtEnterPrice.setBackground(new Color(255, 183, 187));
				textStatus.setTitle("Please fill the highlighted fields and retry.");
			}
			if (year.equals("")) {
				txtEnterYear.setBackground(new Color(255, 183, 187));
				textStatus.setTitle("Please fill the highlighted fields and retry.");
			}
			if (vin.equals("")) {
				txtEnterVIN.setBackground(new Color(255, 183, 187));
				textStatus.setTitle("Please fill the highlighted fields and retry.");
			}
			else { //if input proper, create a car and set its properties based on input
				try {
					Car newInvCar = new Car();
					newInvCar.setBrand("Honda");
					newInvCar.setModel(model);
					newInvCar.setPrice(Double.parseDouble(price));
					newInvCar.setYear(Integer.parseInt(year));
					newInvCar.setIssue('p');
					newInvCar.setStatus('d');
					if(inventory.searchForVin(vin)<0) {  //if vin is not already existing, add it to the inventory list and save inventory and update inventory selection box and display success message
						newInvCar.setVin(vin);
						inventory.insert(newInvCar);
						Inventory.saveInventory(inventory);
						UpdateCBInventory();
						textStatus.setTitle("Car has been added to inventory.");
					}
					else {//if vin already exists
						textStatus.setTitle("This VIN already exists.");
					}
				} catch (NumberFormatException e1) {
					textStatus.setTitle("Input not valid.");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		}

		else if(e.getSource()==btnInsertCliCar) {//if insert car to client was selected
			if(cbClients.getSelectedIndex()>0) {			//if a client was selected, get the inputs selected and store them in STRINGS	
				String model = txtEnterModel.getText();
				String price = txtEnterPrice.getText();
				String year = txtEnterYear.getText();
				String vin = txtEnterVIN.getText();
				String status = txtEnterStatus.getText().toLowerCase();
				String issue = txtEnterIssue.getText().toLowerCase();
				//anything left empty
				if(model.equals("")){
					txtEnterModel.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill the highlighted fields and retry.");
				}
				if (price.equals("")) {
					txtEnterPrice.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill the highlighted fields and retry.");
				}
				if (year.equals("")) {
					txtEnterYear.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill the highlighted fields and retry.");
				}
				if (vin.equals("")) {
					txtEnterVIN.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill the highlighted fields and retry.");
				}
				if (status.equals("")) {
					txtEnterStatus.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill the highlighted fields and retry.");
				}
				if (issue.equals("")) {
					txtEnterIssue.setBackground(new Color(255, 183, 187));
					textStatus.setTitle("Please fill the highlighted fields and retry.");
				}
				else {//proper input
					try {
						//create car and set details
						Car newInvCar = new Car();
						newInvCar.setBrand("Honda");
						newInvCar.setModel(model);
						newInvCar.setPrice(Double.parseDouble(price));
						newInvCar.setYear(Integer.parseInt(year));
						newInvCar.setIssue(issue.charAt(0));
						newInvCar.setStatus(status.charAt(0));
						index = clients.uNameBinarySearch((String)cbClients.getSelectedItem());//look for index of client
						if(clients.getList()[index].getcList().searchForVin(vin)<0) {  //if vin does not exist
							newInvCar.setVin(vin);  //set the vin
							clients.getList()[index].buyCar(newInvCar);  //buy him the car
							clients.FormatWriter("Clients.txt");  //sace clients list
							textStatus.setTitle("Car has been added to " + clients.getList()[index].getUserName() + "'s cars.");//sucess message
							recordArea1.setText(clients.getList()[index].toString());  //displaying client's record
						}
						else {//vin already exists
							textStatus.setTitle("This VIN already exists within the client's list of cars.");
						}
					} catch (NumberFormatException e1) {
						textStatus.setTitle("Input not valid.");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}
			}
			else {//if client is not slected
				textStatus.setTitle("A client must be selected first.");
			}
		}

		else if (e.getSource()==btnSignOut) { //if sign out button pressed, open welcome screen and close this one
			new WelcomeUI();
			this.dispose();
		}
	}
	//method to update clients combo box
	public void UpdateCBClients() {
		cbClients.removeAllItems();//remove all existing items
		c= new String[clients.getSize()+1];  //set the c string array to the size of the clients array
		clients.uNameQuickSort(clients.getList(), 0, clients.getSize()-1);//sorting clients
		c[0] = "Select Client";  //setting the first item to the select client message
		for (int i = 0; i < c.length-1; i++) {//looping through each username, and then looping thru each item and adding it to it
			c[i+1] = clients.getList()[i].getUserName();
		}
		for (int i = 0; i < c.length; i++) {
			cbClients.addItem(c[i]);
		}
	}
	//method to update dealers combo box
	public void UpdateCBDealers() {
		cbDealers.removeAllItems();//remove all items
		d= new String[dealers.getSize()+1];//set the d string array to the size of the dealers array
		dealers.quickSort(0, dealers.getSize()-1);//sorting dealers
		d[0] = "Select Dealer";//setting the first item to the select dealer message
		for (int i = 0; i < d.length-1; i++) {//looping through each username, and then looping thru each item and adding it to it
			d[i+1] = dealers.getDealer()[i].getUserName();
		}
		for (int i = 0; i < d.length; i++) {
			cbDealers.addItem(d[i]);
		}
	}
	//method to update mechanics combo box
	public void UpdateCBMechs() {
		cbMechs.removeAllItems();//remove all items
		m= new String[mechs.getSize()+1];//set the m string array to the size of the mechanic array
		mechs.quickSort(0, mechs.getSize()-1);  //sorting mechanics
		m[0] = "Select Dealer"; //setting the first item to the select mechanic message
		for (int i = 0; i < m.length-1; i++) {//looping through each username, and then looping thru each item and adding it to it
			m[i+1] = mechs.getMechanic()[i].getUserName();
		}
		for (int i = 0; i < m.length; i++) {
			cbMechs.addItem(m[i]);
		}
	}
	//method to update inventory selection box
	public void UpdateCBInventory() {
		cbInventory.removeAllItems();//remove all items
		inv=new String[inventory.getSize()+1];//set the inv string array to the size of the inventory array
		inventory.sortByVin();//sorting cars
		inv[0] = "Select Car";//setting the first item to the select car message
		for (int i = 0; i < inv.length-1; i++) {//looping through each username, and then looping thru each item and adding it to it
			inv[i+1] = inventory.getList()[i].getVin();
		}
		for (int i = 0; i < inv.length; i++) {
			cbInventory.addItem(inv[i]);
		}
	}
	//method to prompt boss for new employeee details
	public void HirePrompt(Person pers) {
		//prompting and setting
		pers.setfName(JOptionPane.showInputDialog(null, "Enter employee's first name."));
		pers.setlName(JOptionPane.showInputDialog(null, "Enter " + pers.getfName() +  "'s last name."));
		pers.setUserName(JOptionPane.showInputDialog(null, "Enter " + pers.getfName() +  "'s username."));
		pers.setAddress(JOptionPane.showInputDialog(null, "Enter " + pers.getfName() +  "'s address."));
		pers.setPhoneNum(JOptionPane.showInputDialog(null, "Enter " + pers.getfName() +  "'s phone number."));
		// next 3 lines of code: Source - https://coderanch.com/t/341807/java/JOptionPane-password-input
		JPasswordField pwd = new JPasswordField(10);//creating a password field
		int action = JOptionPane.showConfirmDialog(null, pwd,"Enter" + pers.getfName() + "'s password.",JOptionPane.OK_CANCEL_OPTION);  //prompting for password
		pers.setPswd(new String(pwd.getPassword()));  //setting password
	}

	public static void main(String[] args) throws IOException {
		//opening boss UI with default boss inputted
		Boss b = new Boss();
		BossUI ui = new BossUI(b);
	}


}