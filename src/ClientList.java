import java.io.IOException;

/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class for a list of clients, with methods that can be used to view, add, remove, change, sort, and search through client records
 *Method List: 
 *		ClientList() 
 *		boolean insert (Client c) 
 *		boolean delete (Client record)
 *		boolean change (Client oldRec, Client newRec)
 *		void swap( Client array[], int first, int second )
 *		void fNameQuickSort(Client array[], int aMin, int aMax)
 *		int fNameBinarySearch (String name)
 *		String toString() 
 *		void FormatWriter (String fileName)
 *		void FormatReader(String fileName) 
 *		Client[] getList()
 *		int getSize()
 *		void setSize(int size) 
 *		static void main(String[] args)
 */

public class ClientList {
	//private data
	private Client[] list;
	private int maxSize;
	private int size;

	//constructor that is used to create a new ClientList and initializes all properties to default values
	public ClientList() {
		this.maxSize = 30;
		list = new Client [maxSize];
		this.setSize(0);
	}
	//boolean method to insert a client to the client list
	public boolean insert (Client c) {
		if (this.getSize()<maxSize) {
			this.getList()[size] = c;  //adding the record to the list at next available index
			size++;			//increasing the size by 1
			return true;
		}
		return false;
	}

	//boolean method to delete a client from the client list
	public boolean delete (Client record) {
		//sorting by first name alphabetical order
		this.fNameQuickSort(list, 0, size-1);
		//finding index of record to delete
		int index = this.fNameBinarySearch(record.getfName());
		//if found
		if(index >= 0) {
			//move record to end of list and decrease list size by 1
			list[index] = list[size-1];
			size--;
			//sorting again based on first name
			this.fNameQuickSort(list, 0, size-1);
			return true;
		}
		//not found
		return false;
	}

	//boolean method to change a client in the client list
	public boolean change (Client oldRec, Client newRec) {		
		int index = this.fNameBinarySearch(oldRec.getfName());  //searching for old record
		if(index>=0) { //found
			list[index] = newRec; //replacing with new record
			this.fNameQuickSort(list, 0, size-1);  //sortinf based on first name alphabetical
			return true;
		}
		//not found
		return false;
	}

	//method to swap array elements
	public void swap( Client array[], int first, int second ){		
		Client hold; // temp variable
		hold = array[ first ];
		array[ first ] = array[ second ];
		array[ second ] = hold;
	}
	//quicksort method to sort by first name in decsending order
	public void fNameQuickSort(Client array[], int aMin, int aMax) {
		Client pivot;
		int leftPos, rightPos;

		if (aMin>aMax) {  //nothing more to sort
			return;
		}

		pivot = array[aMin]; //pivot point
		leftPos = aMin;  //starting pos
		rightPos = aMax;	

		do {
			while(pivot.getfName().compareTo(array[leftPos].getfName())>0) {
				leftPos++;
			}
			while(pivot.getfName().compareTo(array[rightPos].getfName())<0) {
				rightPos--;
			}
			if (leftPos<=rightPos) {
				if (array[leftPos] != array[rightPos]) {
					swap(array, leftPos, rightPos);
				}
				leftPos++;
				rightPos--;
			}
		}
		while(leftPos<rightPos);

		fNameQuickSort(array, aMin, rightPos);  // recur with new limits
		fNameQuickSort(array, leftPos, aMax);
	}//END FIRST NAME QUICK SORT

	//method to search for name using binary search
	public int fNameBinarySearch (String name) {
		//sorting records by first name alphabetical order
		this.fNameQuickSort(this.getList(), 0, getSize()-1);
		int low = 0;
		int high = this.size-1;
		int middle;

		while (low<=high) {  
			middle = (low+high)/2;
			if (name.compareToIgnoreCase(list[middle].getfName()) == 0) {  //check if name found
				return middle;  //return index
			}
			if (name.compareToIgnoreCase(list[middle].getfName()) <0) {  //check if name to find is in the lower half
				high = middle-1;  //move the high to one lower than the middle
			}
			else {  //if it is in higher part of the list
				low = middle+1;  //move the low to one higher than the middle
			}
		}
		//not found
		return -1;
	}

	//quicksort method to sort by user name in decsending order
	public void uNameQuickSort(Client array[], int aMin, int aMax) {
		Client pivot;
		int leftPos, rightPos;

		if (aMin>aMax) {  //nothing more to sort
			return;
		}

		pivot = array[aMin]; //pivot point
		leftPos = aMin;  //starting pos
		rightPos = aMax;	

		do {
			while(pivot.getUserName().compareTo(array[leftPos].getUserName())>0) {
				leftPos++;
			}
			while(pivot.getUserName().compareTo(array[rightPos].getUserName())<0) {
				rightPos--;
			}
			if (leftPos<=rightPos) {
				if (array[leftPos] != array[rightPos]) {
					swap(array, leftPos, rightPos);
				}
				leftPos++;
				rightPos--;
			}
		}
		while(leftPos<rightPos);

		uNameQuickSort(array, aMin, rightPos);  // recur with new limits
		uNameQuickSort(array, leftPos, aMax);
	}//END USER NAME QUICK SORT

	//method to search for name using binary search
	public int uNameBinarySearch (String name) {
		//sorting records by user name alphabetical order
		this.uNameQuickSort(this.getList(), 0, getSize()-1);
		int low = 0;
		int high = this.size-1;
		int middle;

		while (low<=high) {  
			middle = (low+high)/2;
			if (name.compareToIgnoreCase(list[middle].getUserName()) == 0) {  //check if name found
				return middle;  //return index
			}
			if (name.compareToIgnoreCase(list[middle].getUserName()) <0) {  //check if name to find is in the lower half
				high = middle-1;  //move the high to one lower than the middle
			}
			else {  //if it is in higher part of the list
				low = middle+1;  //move the low to one higher than the middle
			}
		}
		//not found
		return -1;
	}

	public String toString() {
		String theList = "";

		for (int i = 0; i < this.getSize(); i++) {
			if(this.getList()[i].cList.getSize()>0) {
				this.getList()[i].getcList().sortByYear();
				//can't use client.toString because i only want to display the newest car, not the entire carList
				theList += "Client " + i + ": First Name: " + this.getList()[i].getfName() + ", Last Name: " + this.getList()[i].getlName() + ", Username: " + this.getList()[i].getUserName() +  ", Address: " + this.getList()[i].getAddress() + ", Phone Number: " + this.getList()[i].getPhoneNum() + ", Newest Car: " + this.getList()[i].getcList().getList()[0].getBrand() + " " + this.getList()[i].getcList().getList()[0].getModel() + "\n";         
			}
			else {
				theList += "Client " + i + ": First Name: " + this.getList()[i].getfName() + ", Last Name: " + this.getList()[i].getlName() + ", Username: " + this.getList()[i].getUserName() +  ", Address: " + this.getList()[i].getAddress() + ", Phone Number: " + this.getList()[i].getPhoneNum() + ", Newest Car: This Client has no cars." + "\n";
			}
		}

		return theList;
	}

	//method to that formats the string of arrays read from the file inputted using the ReadFile method from the ReaderAndWriter class into a ClientList
	public void FormatReader(String fileName) throws IOException {
		//delete all clients in existing list to prevent repeating clients
		this.setSize(0);
		//reads from the inputted filename and stores the data in an array of strings
		String array[] = ReaderAndWriter.ReadFile(fileName);
		//loops through the array of strings
		for (int i = 0; i < array.length; i++) {
			Client rec = new Client();  //creates a new client to store each record in
			rec.processRecord(array[i]);  //processes each record
			this.insert(rec);  //inserts each record into the ClientList
		}

	}

	//method to that formats the ClientList into an array of strings that uses the ReaderAndWriter class to write the data to a file
	public void FormatWriter (String fileName) throws IOException {
		String array[] = new String[size];  //creates an array of Strings that will be used to store the client records

		for (int i = 0; i < size; i++) { //loop through each client in the list and format their record
			array[i] = (list[i].getfName() + "/" + list[i].getlName() + "/" + list[i].getUserName() + "/" + list[i].getAddress() + "/" + list[i].getPhoneNum() + "/" + list[i].getPswd());
			for (int j = 0; j < this.getList()[i].getcList().getSize(); j++) { //nested for loop to format the car records
				array[i] += (";" + list[i].getcList().getList()[j].getBrand() + "," + list[i].getcList().getList()[j].getModel() + "," + list[i].getcList().getList()[j].getYear() + "," + list[i].getcList().getList()[j].getPrice() + "," + list[i].getcList().getList()[j].getIssue() + "," + list[i].getcList().getList()[j].getStatus() + "," + list[i].getcList().getList()[j].getVin());
			}
		}
		ReaderAndWriter.saveFile(array, fileName);  //writes the array to the inputted file name
	}//end write

	/**
	 * @return the list
	 */
	public Client[] getList() {
		return list;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}
	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(Client[] list) {
		this.list = list;
	}
	public static void main(String[] args) throws IOException {
		ClientList clients = new ClientList();
		ClientList clientsText = new ClientList();//creating a new client list that will be loaded from the text file

		System.out.println("ClientList made with default constructor:\n" + clientsText.toString());

		//creating a car list
		CarList cars = new CarList();
		//creating and adding cars to the car list
		Car car1 = new Car("Honda", "Civic", 2007, 5000, 'p', 'd', "A1B1C1");
		Car car2 = new Car("Honda", "CR-V", 2008, 10000, 'p', 'd', "A2B2C2");
		Car car3 = new Car("Honda", "Fit", 2009, 2000, 'p', 'd', "A3B3C3");				
		cars.insert(car1);
		cars.insert(car2);
		cars.insert(car3);

		//creating new clients to add to the client list
		Client jerry = new Client("Jerry", "Baldwin", "jerry600",  "100 North Pole Drive", "9054950349", "4321", cars);
		Client thalik = new Client("Thalik", "Shah", "thalbhal600", "100 Younge St", "9054446666", "6666", cars);
		Client dan = new Client("Danindu", "Marasinghe", "danman600",  "24 Fidelity Ave", "9054950349", "1234", cars);
		clients.insert(jerry);
		clients.insert(thalik);
		clients.insert(dan);
		//output
		System.out.println("ClientList with jerry, thalik, and Danindu inserted:" + clientsText.toString());
		//testing search
		System.out.println("Searching for Danindu's record.");
		int index = clients.fNameBinarySearch("Danindu");
		if(index>=0) { //if found
			System.out.println("Record Found:\n" + clients.getList()[index].toString());
		}
		else {  //if not found
			System.out.println("Record not found");
		}

		//deleting jerry
		System.out.println("Deleting " + jerry.getfName() + " " + jerry.getlName() + " and sorting list.");
		if (!clients.delete(jerry)) { //unsuccessful 
			System.out.println("Delete Unsuccesful.\n");
		}
		else {  //success
			System.out.println("Delete successful.");
			System.out.println(clients.toString());  //outputting list
		}

		//replacing Thalik with jerry
		System.out.println("Replacing " + thalik.getfName() + " " + thalik.getlName() + " with " + jerry.getfName() + " " + jerry.getlName());
		if (!clients.change(thalik, jerry)) { //unsuccessful 
			System.out.println("Change Unsuccesful.\n");
		}
		else {  //success
			System.out.println("Change successful.");
			System.out.println(clients.toString());  //outputting list
		}
		//reading from the text file and displaying the list
		clientsText.FormatReader("ClientListTest.txt");
		System.out.println("Clients read from TextFile:\n" + clientsText.toString());

		//writing clients to the text file and reading and displaying
		clients.FormatWriter("ClientListTest2.txt");		
		clients.FormatReader("ClientListTest2.txt");
		System.out.println("Clients read from TextFile after writing the new list:\n" + clients.toString());

		System.out.println("Searching for client with the username, 'santhosh101':");
		int index2 = clientsText.uNameBinarySearch("santhosh101");  //searching for santhosh101 and getting index and storing it in index 2, an integer variable
		if (index2>0) {//if found
			System.out.println("Found:\n" + clientsText.getList()[index2].toString());  //displaying found client
		}
		else {//if not found
			System.out.println("Cannot find santhosh101");
		}

	}//end main

}