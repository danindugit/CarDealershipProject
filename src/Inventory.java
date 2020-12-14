import java.io.IOException;
/*Author: Mabin Mathew
 * Date: Dec 23rd, 2018
 * Description: A class that loads up the inventory of the Dealership from a txt using the ReaderAndWriter class
 */

/*Method List:
 * CarList loadInventory() : loads the inventory infromaion from a txt useing the ReadFile() method
 *  							from the ReaderAndWrtier class.
 *  void saveInventory(CarList list) : saves a carList as inventory using the saveFile() method
 *  							from the ReaderAndWrtier class. 
 */

public class Inventory {

	static int countMain = 0;
	static 	String line [];
	static Car Car [];
	
//Loads the inventory from the txt 
	public static CarList loadInventory() throws IOException {

		//gets the carlist in string form from the reader class
		String list[] = ReaderAndWriter.ReadFile("Inventory.txt");

		Car Car [] = new Car [20];
		CarList inventoryRecord = new CarList();

		//loops to process the String array into a carList
		for(int k = 0; k <list.length;k++)
		{
			Car[k] =new Car();
			Car[k].processRecord(list[k]);
			inventoryRecord.insert(Car[k]);
		}

		return inventoryRecord;
	}//end loadInventory

	//saves a carlist to a txt
	public static void saveInventory(CarList list) throws IOException {
		String outList[] = new String[list.getSize()];

		//loops to convert the CarList into a string array
		for(int w = 0; w < outList.length;w++)
		{
			outList[w] = list.toString(w);
		}

		ReaderAndWriter.saveFile(outList,"Inventory.txt");


	}



	public static void main(String[] args) throws IOException {

		CarList output = new CarList();

		//	ReaderAndWriter.saveFile(line, "TestForR&W.txt");
		output=loadInventory();


		System.out.println(output.DisplayString());
		output.SortByModel();
		System.out.println("Sorted inventory\n" + output.DisplayString());

		saveInventory(output);
	}




}
