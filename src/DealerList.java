import java.io.IOException;

/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This is a list containing an array of Dealer objects.
 * Method List:
 * 				DealerList()
 * 				Dealer[] getDealer()
 * 				void setDealer(Dealer[] dealer)
 * 				int getMaxSize()
 * 				void setMaxSize(int maxSize)
 * 				int getSize()
 * 				void setSize(int size)
 * 				boolean insert(Dealer d)
 * 				boolean delete(String userName)
 * 				int search(String userName)
 * 				void quickSort(int min, int max)
 * 				void swap(int a, int b)
 * 				ReaderFormat(String fileName)
 * 				WriterFormat(String fileName)
 * 				String toString()
 * 				static void main(String[] args)
 *
 */
public class DealerList {

	//private variables for Dealer object array, maximum size of list, and size of list
	private Dealer[] dealer;
	private int maxSize;
	private int size;

	/**
	 * default constructor
	 */
	public DealerList() {
		this.maxSize = 15;  //initialize maxSize
		this.size = 0;  //initialize size
		dealer = new Dealer[maxSize];  //create Dealer array with maxSize elements
	}

	/**
	 * @return the dealer
	 */
	public Dealer[] getDealer() {
		return dealer;
	}

	/**
	 * @param dealer the dealer to set
	 */
	public void setDealer(Dealer[] dealer) {
		this.dealer = dealer;
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

	/*
	 * method to insert a dealer
	 */
	public boolean insert(Dealer d) {  //returns boolean
		if (this.size < maxSize) {  //if size is less than maximum size
			this.size++;  //increment size
			dealer[this.size-1] = d;  //input dealer into array
			return true;
		}
		return false;
	}

	/*
	 * method to delete a dealer
	 */
	public boolean delete(String userName) {  //returns boolean
		for (int i = 0; i < this.size; i++) {  //loop through entire list
			if (this.dealer[i].getUserName().equalsIgnoreCase(userName)) {  //if dealer is found
				dealer[i] = dealer[size-1];  //over write the dealer that is found by the last dealer
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

			//if dealer is found in the middle
			if (userName.compareToIgnoreCase(dealer[middle].getUserName())==0) {
				return middle;  //return loaction found
			}
			//if in the lower part of the list
			else if (userName.compareToIgnoreCase(dealer[middle].getUserName())<0) {
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
		dealer = this.getDealer();

		int leftPos, rightPos;
		Dealer pivot;

		if (min > max) {  //if nothing can be sorted anymore
			return;
		}

		pivot = dealer[min];  //initialize pivot point
		leftPos = min;  //set starting positions
		rightPos = max;

		do {
			while (pivot.getUserName().compareTo(dealer[leftPos].getUserName())>0) {  //while pivot point is greater than array at left position
				leftPos++;  //increment left position
			}  
			while (pivot.getUserName().compareTo(dealer[rightPos].getUserName())<0) {  //while pivot point is less than array at right position
				rightPos--;  //decrement right position
			}
			if (leftPos <= rightPos) { 
				if (dealer[leftPos] != dealer[rightPos]) {  //if left position does not equal to the right position
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
		Dealer temp;  //Temporary holding variable

		temp = dealer[a];  
		dealer[a] = dealer[b];
		dealer[b] = temp;
	}

	/*
	 * method to format string from ReaderAndWriter class
	 */
	public void ReaderFormat(String fileName) throws IOException {
		String a[] = ReaderAndWriter.ReadFile(fileName);  //String array to get returned String from ReadFile in ReaderAndWriter after getting info from file
		//loop through the string 
		for (int i = 0; i < a.length; i++) {
			Dealer d = new Dealer();  //create a Dealer object
			d.process(a[i]);  //process string into d
			this.insert(d);  //insert d into list
		}
	}

	/*
	 * method to format information to String to send to ReaderAndWriter class
	 */
	public void WriterFormat(String fileName) throws IOException {
		String a[] = new String[size];  //declare and create array of size (size of the list)

		//loop through list 
		for (int i = 0; i < size; i++) {  
			//put each dealer into string
			a[i] = (dealer[i].getfName() + "/" + dealer[i].getlName() + "/" + dealer[i].getUserName() + "/" + dealer[i].getAddress() + "/" + dealer[i].getPhoneNum() + "/" + dealer[i].getPswd());
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
			list += "Dealer " + i + ": " + this.dealer[i].toString() + "\n";  //output format
		}
		return list;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DealerList dealers = new DealerList();  //create DealerList

		//create Dealers
		Dealer d1 = new Dealer("Tilak", "Shah", "Dealer789", "45 Daviselm Drive", "6475783788", "1234");
		Dealer d2 = new Dealer("Terry", "Jefferds", "Dealer123", "64 Daviselm Drive", "6475829183", "5678");
		Dealer d3 = new Dealer("Charles", "Boyle", "Dealer456", "89 Daviselm Drive", "6472819383", "StrongGuy101");
		//insert dealers
		System.out.println(dealers.insert(d1));
		System.out.println(dealers.insert(d2));
		System.out.println(dealers.insert(d3));

		//display DealerList
		//test toString() method
		System.out.println(dealers.toString());

		//delete Dealer
		System.out.println("Delete (Dealer789): " + dealers.delete("Dealer789"));
		System.out.println(dealers.toString());  //display list

		//sort list
		dealers.quickSort(0, dealers.getSize());
		//display
		System.out.println("Quick Sort by Username (Alphabetically):\n" + dealers.toString());

		//search for dealer
		Dealer d[] = dealers.getDealer();
		System.out.println("Binary Search (Dealer456) Position " + dealers.search("Dealer456") + ": " + d[dealers.search("Dealer456")]);  //display result

		//write DealerList to txt file
		dealers.WriterFormat("DealerTest.txt");
		System.out.println("Please check file - DealerTest.txt");

		//read from txt file
		DealerList dReadTest = new DealerList();  //create new DealerList to test reader
		dReadTest.ReaderFormat("DealerTest.txt");
		System.out.println("\nThe file reader tester: \n" + dReadTest.toString());  //display result
	}

}
