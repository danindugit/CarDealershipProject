/**
 * 
 */

/**
 * @author tilak
 * Date: January 22, 2019
 * Description: This is a Mechanic object that inherits from Person class.
 * Method List:
 * 				Mechanic()
 * 				Mechanic(String fName, String lName, String userName, String address, String phoneNum, String password)
 * 				void process(String mechanic)
 * 				String toString()
 * 				static void main(String[] args)
 *
 */
public class Mechanic extends Person{

	/*
	 * default constructor
	 */
	public Mechanic() {
		super();
	}

	/*
	 * overloaded constructor
	 */
	public Mechanic(String fName, String lName, String userName, String address, String phoneNum, String password) {
		super(fName, lName, userName, address, phoneNum, password);
	}

	/*
	 * method to process the string input
	 */
	public void process(String mechanic) {
		String m[];
		m = mechanic.split("/");
		//set elements of mechanic (a Person)
		this.setfName(m[0]);
		this.setlName(m[1]);
		this.setUserName(m[2]);
		this.setAddress(m[3]);
		this.setPhoneNum(m[4]);
		this.setPswd(m[5]);
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
		Mechanic m1 = new Mechanic();  //create new Mechanic with no input parameters
		System.out.println(m1.toString());  //display

		//test overloaded constructor
		Mechanic m2 = new Mechanic("James", "Curry", "Mechanic123", "123 ABCD Drive", "6478279109", "TheBestMechanic");  //create new Mechanic w/ input parameters
		System.out.println(m2.toString());  //display

		//test process method
		String mString = "Dean/Ambrose/dean_a/35 Titls Palace/6479288190/1111";  
		Dealer m3 = new Dealer();  //create new Mechanic to process information into
		m3.process(mString); 
		System.out.println(m3.toString());  //display
	}

}
