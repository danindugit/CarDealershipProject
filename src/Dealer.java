/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This is a Dealer object class that inherits from Person class.
 * Method List:
 * 				Dealer()
 * 				Dealer(String fName, String lName, String userName, String address, String phoneNumber, String password)
 * 				void process(String dealer)
 * 				String toString()
 * 				static void main(String[] args)
 *
 */
public class Dealer extends Person{

	/**
	 * default constructor
	 */
	public Dealer() {
		super();
	}

	/*
	 * overloaded constructor
	 */
	public Dealer(String fName, String lName, String userName, String address, String phoneNumber, String password) {
		super(fName, lName, userName, address, phoneNumber, password);
	}

	/*
	 * method to process the string input
	 */
	public void process(String dealer) {
		String d[];
		d = dealer.split("/");
		//set elements of dealer (a Person)
		this.setfName(d[0]);
		this.setlName(d[1]);
		this.setUserName(d[2]);
		this.setAddress(d[3]);
		this.setPhoneNum(d[4]);
		this.setPswd(d[5]);
	}

	/*
	 * (non-Javadoc)
	 * @see Person#toString()
	 */
	public String toString() {
		return super.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//test default constructor
		Dealer d1 = new Dealer();  //create new Dealer with no input parameters
		System.out.println(d1.toString());  //display

		//test overloaded constructor
		Dealer d2 = new Dealer("Tilak", "Shah", "Dealer123", "45 Daviselm Drive", "6475783788", "1234");  //create new Dealer w/ input parameters
		System.out.println(d2.toString());  //display

		//test process method
		String dString = "John/Paul/j.p/68 River Way/6478598726/1234";  
		Dealer d3 = new Dealer();  //create new Dealer to process information into
		d3.process(dString); 
		System.out.println(d3.toString());  //display
	}

}
