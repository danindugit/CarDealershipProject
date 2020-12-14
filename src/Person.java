/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class for a general person object, giving people properties such as name, address, and phone number
 *Method List: 
 *		Person() 
 *		Person(String fName, String lName, String address, String phoneNum)
 *		String toString() 
 *		void processRecord (String record) 
 *		String getfName()
 *		void setfName(String fName)
 *		String getlName()
 *		void setlName(String lName)
 *		String getAddress()
 *		void setAddress(String address)
 *		String getPhoneNum()
 *		void setPhoneNum(String phoneNum)
 *		Person (String record) 
 *		static void main(String[] args) 
 */
public class Person {
	private String fName, lName, userName, address, phoneNum, pswd;
	public Person() {
		//intializing data
		this.fName = "";
		this.lName = "";
		this.userName = "";
		this.address = "";
		this.phoneNum = "";
		this.pswd = "";
	}

	/**
	 * constructor for input params for each property
	 * @param fName
	 * @param lName
	 * @param address
	 * @param phoneNum
	 * 	@param uName
	 * @param pswd
	 */
	public Person(String fName, String lName, String uName, String address, String phoneNum, String pswd) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.userName = uName;
		this.address = address;
		this.phoneNum = phoneNum;
		this.pswd = pswd;
	}

	//overloaded constructor that takes in a string record to process
	public Person (String record) {
		this.processRecord(record);
	}

	//method to output all properties of a person
	public String toString() {

		return "Person: " + "First Name: " + this.getfName() + ", Last Name: " + this.getlName() + ", UserName: " + this.getUserName() + ", Address: " + this.getAddress() + ", Phone Number: " + this.getPhoneNum();
	}
	//method to process a person record from a string
	public void processRecord (String record) {
		String info[] = record.split("/");
		this.setfName(info[0]);
		this.setlName(info[1]);
		this.setUserName(info[2]);
		this.setAddress(info[3]);
		this.setPhoneNum(info[4]);
		this.setPswd(info[5]);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the pswd
	 */
	public String getPswd() {
		return pswd;
	}

	/**
	 * @param pswd the pswd to set
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public static void main(String[] args) {
		Person p1 = new Person();  //creating a person with default properties of "" to test the Person() constructor
		Person p2 = new Person("Danindu", "Marasinghe", "danman600", "24 Fidelity Ave", "9054950349", "4321");  //creating a person with initialized properties to test the constructor with input parameters
		String record = "Thalik/Shah/thallyCarrot/100 Younge St/9054446666/6666";

		System.out.println(p1.toString());  //output Person properties to test toString and getters
		System.out.println(p2.toString());

		p1.processRecord(record);  //testing processor and setters
		System.out.println(p1.toString()); 

		//testing password getter
		System.out.println(p1.getfName() + "'s Password: " + p1.getPswd());
	}

}