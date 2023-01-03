// Importing required classes
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// Main class
public class data {
	Path fileName = Path.of("/Users/bruelle/Documents/GUI/data.csv");

	public void savetoCSV(String row)throws IOException {
			
		Files.writeString(fileName, row, StandardOpenOption.APPEND);
		
	}
	// Main driver method
	public static void main(String[] args)throws IOException
	{
		// Assigning the content of the file
		String text = Simulation.SimulationTrame("Auto",true,true)+"\n";
		// Defining the file name of the file
		Path fileName = Path.of("/Users/bruelle/Documents/GUI/data.csv");
		// Writing into the file
		Files.writeString(fileName, text, StandardOpenOption.APPEND);
		// Reading the content of the file
		String file_content = Files.readString(fileName);
		// Printing the content inside the file
		System.out.println(file_content);

	}
}
