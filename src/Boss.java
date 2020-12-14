import java.io.IOException;

/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class for a boss object, giving bosses properties for the lists of all of the system's data
 *Method List: 
 *		Boss() 
 *		Boss(String fName, String lName, String uName, String address, String phoneNum, String pswd, String dealerFileName, String mechFileName, String clientFileName, String invFileName)
 *		boolean deleteCar(Car car)
 *		boolean addCar(Car car)
 *		static void main(String[] args)
 */

public class Boss extends Person{
	//private data 
	private DealerList dealers;
	private MechanicList mechs;
	private ClientList clients;
	private CarList inv;
	//default constructor that creates the currently existing boss using the current text files
	public Boss() throws IOException {	
		super("Takahiro", "Hachigo", "boss", "56 Tokyo Drive", "4163921922", "4321");
		this.dealers = new DealerList();
		//dealers.FormatReader("Dealers.txt");
		this.mechs = new MechanicList();
		//mechs.FormatReader(Mechanics.txt");
		this.clients = new ClientList();
		clients.FormatReader("Clients.txt");
		this.inv = new CarList();
		inv = Inventory.loadInventory();
	}

	/**
	 * @param fName
	 * @param lName
	 * @param uName
	 * @param address
	 * @param phoneNum
	 * @param pswd
	 * default constructor that creates a boss for the reasons of new bosses and/or new text files
	 * @throws IOException 
	 */
	public Boss(String fName, String lName, String uName, String address, String phoneNum, String pswd, String dealerFileName, String mechFileName, String clientFileName) throws IOException {
		super(fName, lName, uName, address, phoneNum, pswd);		
		this.dealers = new DealerList();
		dealers.ReaderFormat(dealerFileName);
		this.mechs = new MechanicList();
		mechs.ReaderFormat(mechFileName);
		this.clients = new ClientList();
		clients.FormatReader(clientFileName);
		this.inv = new CarList();
		inv = Inventory.loadInventory();
	}
	//method to delete a car from the inventory and rewrite it to the inventory text file
	public boolean deleteCar(Car car) throws IOException {
		if(!inv.delete(car)) {  //if delete was unsuccessful, return false, otherwise, return true
			return false;
		}
		Inventory.saveInventory(inv);  //rewrite without deleted car
		return true;
	}
	//method to add a car to the inventory and rewrite it to the inventory text file
	public boolean addCar(Car car) throws IOException {
		if(!inv.insert(car)) {  //if add was unsuccessful, return false, otherwise, return true
			return false;
		}
		Inventory.saveInventory(inv);//rewrite with added car
		return true;
	}

	public static void main(String[] args) {
		//all boss functionality has been tested in other classes
	}

}