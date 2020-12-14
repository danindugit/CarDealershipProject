import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author Mabin Mathew
 * Date: Jan 12th,2019
 * Description: A class to read and write from and to a .txt file
 */
public class ReaderAndWriter {

	/**
	 * @param args
	 */

	//Reads a .txt file and returns a array of strings
	public static String[] ReadFile(String FileName) throws IOException{
		FileReader fr = new FileReader(FileName);
		BufferedReader input = new BufferedReader(fr);

		int count = 0;

		// counts the length that the arrays needs to be 
		while (true) {

			if (input.readLine() == null) {
				//close file reader
				input.close();
				//and go back
				break;
			}

			count ++;

		}

		// adds the information into the array
		FileReader file = new FileReader(FileName);
		BufferedReader inputs = new BufferedReader(file);
		String line [] = new String [count];


		for (int i = 0; i < count; i++) {
			line [i] = inputs.readLine();
		}

		inputs.close(); // close the file

		return line;

	}

	//takes in an array where it is convert into a txt file 
	public static void saveFile(String list[],String fileName) throws IOException {
		FileWriter fileW = new FileWriter (fileName);
		PrintWriter output = new PrintWriter(fileW);

		// loop to save information into the file
		for (int l = 0; l < list.length; l++) {
			output.println(list[l]);

		} // ends for loop l

		fileW.close();
	}

	//method to append record to text file  -- MADE BY DANINDU
	public static void appendToFile(String rec, String fileName) throws IOException {
		// Open given file in append mode. 
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
		out.newLine();  //skip a line
		out.write(rec);  //write the added string
		out.close();  //close the buffered writer
	}

	public static void main(String[] args) throws IOException {
		String line [] = new String [6];
		String output [] = new String [6];

		line[0]= "we";
		line[1]= "will";
		line[2]= "we";
		line[3]= "will";
		line[4]= "rock";
		line[5]= "you";

		//		ReaderAndWriter.saveFile(line, "TestForR&W.txt");
		output= ReaderAndWriter.ReadFile("Inventory.txt");


		System.out.println(Arrays.toString(output));

	}

}