/*Author: Mabin Mathew
 * Date:19th dec 2018
 * Description: a class that creates an object car that has information like:
 * model,brand,year,issue,status 
 */


public class Car {

	private String model,brand;
	private int year;
	private double price;
	private char issue,status;
	private String vin;


	//main construtor
	public Car () {
		this.model = "";
		this.brand = "";
		this.year = 0000;
		this.price =0000;
		this.issue =  'n';
		this.status = 'n';
		this.vin = "";
	}
	
	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	//returns the price for the specified object
	public double getPrice() {
		return price;
	}
	//set the price for the specified object
	public void setPrice(double price) {
		this.price = price;
	}

	//overloaded construtor
	public Car (String brand,String model,int year,double price, char issue,char status, String vin) {

		this.model = model;
		this.brand = brand;
		this.year = year;
		this.price = price;
		this.issue =  issue;
		this.status = status;
		this.vin = vin;
	}

	//returns the Model for the specified object
	public String getModel() {
		return model;
	}
	//sets the model for the specified object
	public void setModel(String model) {
		this.model = model;
	}

	//returns the brand for the specified object
	public String getBrand() {
		return brand;
	}

	//sets the brand for the specified object
	public void setBrand(String brand) {
		this.brand = brand;
	}

	//returns the year for the specified object
	public int getYear() {
		return year;
	}
	//sets the year for the specified object
	public void setYear(int year) {
		this.year = year;
	}

	/* returns the issue for the specified object as a string 
	 * instead of a char for the ease of use
	 */
	public char getIssue() {
		return this.issue;
	}
	////sets the issue for the specified object 
	public void setIssue(char issue) {
		this.issue = issue;
	}

	//returns the status for the specified object as a string for the ease of use
	public char getStatus() {
		return this.status;
	}
	//sets the status for the specified object
	public void setStatus(char status) {
		this.status = status;
	}

	//processes the parsed string to then convert into a vehicle record 
	public void processRecord(String record) {
		String word [];
		//converts the parsed string into an array using split 
		word = record.split(",");
		this.brand = word[0];
		this.model = word[1];
		this.year = Integer.parseInt(word[2]);
		this.price= Double.parseDouble(word[3]);
		this.issue =word[4].charAt(0);
		this.status=word[5].charAt(0);
		this.vin = word[6];

	}

	//convert the car into a more user friendly string 
	public String toString() {
		String issue = "";


		//uses a switch case to identify the characters assigned to the char issue
//		switch(this.issue) {
//		case 'o': {
//
//			issue = "Oil Change";
//			break;	 
//		}
//		case 'p': {
//			issue = "Perfect Condition";
//			break;
//		}
//		case 'b':{
//			issue = "Brake repalcement";
//			break;
//		}
//		case 't': {
//			issue = "tuning problems";
//			break;
//		}
//		case'a': {
//			issue = "Air Conditioning problem";
//			break;
//		}
//		case'e': {
//			issue = "Engine Problems";
//			break;
//		}
//		default :{
//			issue = "Unkown Issue";
//		}
//		//CAR ISSUES		 
//		/*Oil changes
//		 *  Brake replacement
//		 * tuning problems
//		 * Air conditioning problems
//		 * Engine problems
//		 */
//		}//end switch issue
//		
//		
//		String status = "";
//
//
//		//uses a switch case to identify the characters assigned to the char status
//		switch(this.status) {
//		//STATUS : under SERVICe AND SERVICED
//
//		case's':{
//			status = "Under Service";
//			break;			 
//		}
//		case'd':{
//			status = "Service Complete";
//			break;
//		}
//		default :{
//			status= "Status Unknown";
//		}
//		}//end switch
		
		return  this.brand + "," + this.model + "," + this.year + "," + this.price + "," + this.issue 
		+ ","+ this.status + "," + this.vin;

	}//end toString

	public String DisplayString() {
		String issue = "";


		//uses a switch case to identify the characters assigned to the char issue
		switch(this.issue) {
		case 'o': {

			issue = "Oil Change";
			break;	 
		}
		case 'p': {
			issue = "Perfect Condition";
			break;
		}
		case 'b':{
			issue = "Brake repalcement";
			break;
		}
		case 't': {
			issue = "tuning problems";
			break;
		}
		case'a': {
			issue = "Air Conditioning problem";
			break;
		}
		case'e': {
			issue = "Engine Problems";
			break;
		}
		default :{
			issue = "Unkown Issue";
		}
		//CAR ISSUES		 
		/*Oil changes
		 *  Brake replacement
		 * tuning problems
		 * Air conditioning problems
		 * Engine problems
		 */
		}//end switch issue
		
		
		String status = "";


		//uses a switch case to identify the characters assigned to the char status
		switch(this.status) {
		//STATUS : under SERVICe AND SERVICED

		case's':{
			status = "Under Service";
			break;			 
		}
		case'd':{
			status = "Service Complete";
			break;
		}
		default :{
			status= "Status Unknown";
		}
		}//end switch

		return "Car: [Brand=" + this.brand
				+ ", Model=" + this.model + ", year=" + this.year
				+ ", Price=" + this.price + ", Issue=" + issue + ", Status="+status + ", VIN=" + this.vin + "]";

	}//end toString




	public static void main(String[] args) {
		String carRec = "Toyota,Rav4,2018,3400,o,s, A1B2C3";

		Car car1 = new Car();

		System.out.println(car1.getBrand());
		System.out.println(car1.getModel());
		System.out.println(car1.getPrice());
		System.out.println(car1.getIssue());
		System.out.println(car1.getStatus() + "\n\n");

		car1.processRecord(carRec);

		System.out.println(car1.getBrand());
		System.out.println(car1.getModel());
		System.out.println(car1.getPrice());
		System.out.println(car1.getIssue());
		System.out.println(car1.getStatus());

		car1.setBrand("Kia");
		car1.setModel("Santa Fe");
		car1.setPrice(4000);
		car1.setIssue('p');
		car1.setStatus('d');

		System.out.println(car1.toString());
		System.out.println(car1.DisplayString());


	}//end main


}//end class