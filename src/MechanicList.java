import java.io.IOException;

/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This is a list containing an array of Mechanic objects.
 * Method List:
 * 				MechanicList()
 * 				Mechanic[] getMechanic()
 * 				void setMechanic(Mechanic[] mechanic)
 * 				int getSize()
 * 				void setSize(int size)
 * 				int getMaxSize()
 * 				void setMaxSize(int maxSize)
 * 				boolean insert(Mechanic m)
 * 				boolean delete(String userName)
 * 				int search(String userName)
 * 				void quickSort(int min, int max)
 * 				void swap(int a, int b)
 * 				void ReaderFormat(String fileName)
 * 				void WriterFormat(String fileName)
 * 				String toString()
 * 				static void main(String[] args)
 *
 */
public class MechanicList {

	//private variables for Mechanic object array, maximum size of list, and size of list
	private Mechanic mechanic[];
	private int size, maxSize;

	/**
	 * default constructor
	 */
	public MechanicList() {
		this.maxSize = 15;  //initialize maxSize
		this.size = 0;  //initialize size
		mechanic = new Mechanic[maxSize];  //create Dealer array with maxSize elements
	}

	/**
	 * @return the mechanic
	 */
	public Mechanic[] getMechanic() {
		return mechanic;
	}

	/**
	 * @param mechanic the mechanic to set
	 */
	public void setMechanic(Mechanic[] mechanic) {
		this.mechanic = mechanic;
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

	/*
	 * method to insert a mechanic
	 */
	public boolean insert(Mechanic m) {  //returns boolean
		if (this.size < maxSize) {  //if size is less than maximum size
			this.size++;  //increment size
			mechanic[this.size-1] = m;  //input dealer into array
			return true;
		}
		return false;
	}

	/*
	 * method to delete a dealer
	 */
	public boolean delete(String userName) {  //returns boolean
		for (int i = 0; i < this.size; i++) {  //loop through entire list
			if (this.mechanic[i].getUserName().equals(userName)) {  //if dealer is found
				mechanic[i] = mechanic[size-1];  //over write the dealer that is found by the last dealer
				size--;  //decrease size
				return true;
			}
		}
		return false;
	}

	/*
	 * method to search by username
	 */
	public int search(String userName) {  //returns integer
		int low = 0;
		int high = this.size - 1;
		int middle;

		while (low <= high) {
			middle = (high + low)/2;  //finds middle of list

			//if mechanic is found in the middle
			if (userName.compareToIgnoreCase(mechanic[middle].getUserName())==0) {
				return middle;  //return loaction found
			}
			//if in the lower part of the list
			else if (userName.compareToIgnoreCase(mechanic[middle].getUserName())<0) {
				high = middle - 1;  //change high-end of list
			}
			//if in the higher part of list
			else {
				low = middle + 1;  //change lower-end of list
			}
		}
		return -1;  //not found
	}

	/*
	 * method to sort by username (alphabetically)
	 */
	public void quickSort(int min, int max) {
		int leftPos, rightPos;
		Mechanic pivot;

		if (min > max) {  //if nothing can be sorted anymore
			return;
		}

		pivot = mechanic[min];  //initialize pivot point
		leftPos = min;  //set starting positions
		rightPos = max;

		do {
			while (pivot.getUserName().compareTo(mechanic[leftPos].getUserName())>0) {  //while pivot point is greater than array at left position
				leftPos++;  //increment left position
			}  
			while (pivot.getUserName().compareTo(mechanic[rightPos].getUserName())<0) {  //while pivot point is less than array at right position
				rightPos--;  //decrement right position
			}
			if (leftPos <= rightPos) { 
				if (mechanic[leftPos] != mechanic[rightPos]) {  //if left position does not equal to the right position
					swap(leftPos, rightPos);  //swap them
				}
				leftPos++;  //increment
				rightPos--;  //decrement
			}
		}
		while (leftPos < rightPos);  
		//recursion
		quickSort(min, rightPos);  
		quickSort(leftPos, max);
	}

	/*
	 * swap method
	 */
	public void swap(int a, int b) {
		Mechanic temp;  //Temporary holding variable

		temp = mechanic[a];  
		mechanic[a] = mechanic[b];
		mechanic[b] = temp;
	}

	/*
	 * method to format string from ReaderAndWriter class
	 */
	public void ReaderFormat(String fileName) throws IOException {
		String a[] = ReaderAndWriter.ReadFile(fileName);  //String array to get returned String from ReadFile in ReaderAndWriter after getting info from file
		//loop through the string 
		for (int i = 0; i < a.length; i++) {
			Mechanic m = new Mechanic();  //create a Mechanic object
			m.processRecord(a[i]);  //process string into m
			this.insert(m);  //insert m into list
		}
	}

	/*
	 * method to format information to String to send to ReaderAndWriter class
	 */
	public void WriterFormat(String fileName) throws IOException {
		String a[] = new String[size];  //declare and create array of size (size of the list)

		//loop through list 
		for (int i = 0; i < size; i++) {
			//put each mechanic into string
			a[i] = (mechanic[i].getfName() + "/" + mechanic[i].getlName() + "/" + mechanic[i].getUserName() + "/" + mechanic[i].getAddress() + "/" + mechanic[i].getPhoneNum() + "/" + mechanic[i].getPswd());
		}
		ReaderAndWriter.saveFile(a, fileName);  //use ReaderAndWriter class to save to file
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String list = "";
		for (int i = 0; i < this.size; i++) {
			list += "Mechanic " + i + ": " + this.mechanic[i].toString() + "\n";  //output format
		}
		return list;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MechanicList mechanics = new MechanicList();  //create MechanicList

		//create Mechanics
		Mechanic m1 = new Mechanic("Tilak", "Shah", "Mechanic123", "45 Daviselm Drive", "6475785938", "1234");
		Mechanic m2 = new Mechanic("Danindu", "Marasinghe", "Mechanic456", "64 Daviselm Drive", "6472828191", "5678");
		Mechanic m3 = new Mechanic("Mabin", "Matthew", "Mechanic789", "89 Daviselm Drive", "6473465827", "FunnyGuy101");
		//insert mechanics
		System.out.println(mechanics.insert(m1));
		System.out.println(mechanics.insert(m2));
		System.out.println(mechanics.insert(m3));

		//display MechanicList
		//test toString() method
		System.out.println(mechanics.toString());

		//delete mechanic
		System.out.println(mechanics.delete("Mechanic456"));
		System.out.println(mechanics.toString());  //display 

		//sort list
		mechanics.quickSort(0, mechanics.getSize());
		//display
		System.out.println("Quick Sort by Username (Alphabetically):\n" + mechanics.toString());

		//search for mechanic
		Mechanic m[] = mechanics.getMechanic();
		System.out.println("Binary Search (Dealer456) Position " + mechanics.search("Mechanic123") + ": " + m[mechanics.search("Mechanic123")]);

		//write MechanicList to txt file
		mechanics.WriterFormat("MechanicsTest.txt");
		System.out.println("\nPlease Check File - MechanicsTest.txt");

		//read from txt file
		MechanicList mReadTest = new MechanicList();  //create new MechanicList to test reader
		mReadTest.ReaderFormat("MechanicsTest.txt");
		System.out.println("The file reader tester: \n" + mReadTest.toString());  //display result

	}

}
