/**
 * @author Danindu
 *Date: 2019 01 22
 *Desc.: A class for a client object, giving clients properties such as carList for their owned cars
 *Method List: 
 *		Client() 
 *		Client(String fName, String lName, String address, String phoneNum, String pswd, CarList list)
 *		boolean buyCar (Car c)
 *		boolean removeCar (Car c)
 *		String toString() 
 *		CarList getcList()
 *		void setcList(CarList cList) 
 *		static void main(String[] args)
 */
public class Client extends Person{
	CarList cList;

	//default constructor
	public Client() {
		super();
		this.cList = new CarList();
	}

	//overloaded constructor that takes in the properties of a person and a carlist
	public Client(String fName, String lName, String uName, String address, String phoneNum, String pswd, CarList list) {
		super(fName, lName, uName, address, phoneNum, pswd);
		this.setcList(list);
	}

	//method to process a client record from a string
	public void processRecord (String record) {
		CarList cars = new CarList();
		this.setcList(cars);
		String full[] = record.split(";");
		String info[] = full[0].split("/");
		this.setfName(info[0]);
		this.setlName(info[1]);
		this.setUserName(info[2]);
		this.setAddress(info[3]);
		this.setPhoneNum(info[4]);
		this.setPswd(info[5]);

		for (int i = 1; i < full.length; i++) {
			Car c = new Car();
			c.processRecord(full[i]);
			if(!this.cList.insert(c)) {
				System.out.println("car insert unsuccessful");
			}
		}
	}

	// method that uses insert method in the CarList class to add a car to the client's car list
	public boolean buyCar (Car c) {
		if(!cList.insert(c)) {  //if buying car was not successful 
			return false;
		}
		return true; //if car was bought successfully, return true
	}

	// method that uses insert delete in the CarList class to remove a car from the client's car list
	public boolean removeCar (Car c) {
		if(!cList.delete(c)) {  //if buying car was not successful 
			return false;
		}
		return true; //if car was bought successfully, return true
	}

	/**
	 * @return the cList
	 */
	public CarList getcList() {
		return cList;
	}

	/**
	 * @param cList the cList to set
	 */
	public void setcList(CarList cList) {
		this.cList = cList;
	}

	//method to output clients properties, including only their newest car
	public String toString() {
		return super.toString() + "\nOwned Cars:\n" +this.getcList().DisplayString();
	}

	public static void main(String[] args) {
		Client c1 = new Client();//testing default constructor
		Client c2 = new Client();//testing default constructor
		String rec = "Santhosh/Patel/santhosh101/100 Bloore St/9054526666/1234;Honda,CR-V,2018,5000,p,d,A1B1C1;Honda,Civic,2018,3400,p,s,A2B2C2;Honda,Civic,2018,3400,p,s,A3B3C3";
		//output
		System.out.println(c1.toString());
		//creating a car list
		CarList cars = new CarList();
		//creating cars
		Car car1 = new Car("Honda", "Civic", 2007, 5000, 'p', 'd', "A1B1C1");
		Car car2 = new Car("Honda", "CR-V", 2008, 10000, 'p', 'd', "A2B2C2");
		Car car3 = new Car("Honda", "Fit", 2009, 2000, 'p', 'd', "A3B3C3");
		//creating a new client and buying the created cars
		Client jerry = new Client("Jerry", "Baldwin", "Jerry600", "100 North Pole Drive", "9054950349", "4321", cars);
		if(!jerry.buyCar(car1)) {
			System.out.println("Buying unsuccessful.");
		}
		else {
			System.out.println(car1.getBrand() + " " + car1.getModel() + " bought successfully.");
		}

		if(!jerry.buyCar(car2)) {
			System.out.println("Buying unsuccessful.");
		}
		else {
			System.out.println(car2.getBrand() + " " + car2.getModel() + " bought successfully.");
		}

		if(!jerry.buyCar(car3)) {
			System.out.println("Buying unsuccessful.");
		}
		else {
			System.out.println(car3.getBrand() + " " + car3.getModel() + " bought successfully.");
		}
		//output
		System.out.println("\n" + jerry.toString());

		//removing the honda fit from jerry's list of cars to test the removeCar method
		if(!jerry.removeCar(car3)) {
			System.out.println("removing unsuccessful.");
		}
		else {
			System.out.println(car3.getBrand() + " " + car3.getModel() + " removed successfully.");
		}
		//output
		System.out.println("\n" + jerry.toString());
		//processing string record into c2
		c2.processRecord(rec);
		System.out.println("\n" + c2.toString());
	}//end main


}