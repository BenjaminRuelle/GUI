import java.io.File;  // Import the File class
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.*;


public class DataManager{  

  public static void savetoCSV(String row) {
    String urlFile = "data.csv";
    Path fileName = Path.of(urlFile);
    try {
      File myObj = new File(urlFile);

      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } 
      Files.writeString(fileName, row, StandardOpenOption.APPEND); //Write inside the csv file the "row" function parameter 
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  static public String SimulationTrame(String Mode, boolean clutch1, boolean clutch2){
    Locale.setDefault(new Locale("en", "US"));
    DecimalFormat d = new DecimalFormat("0.00");
    String KeyID = "001";           
    double tension = 11 + Math.random()*2; //simulation of 12v
    double current = Math.random()*100; //simulation of 0;100 mA
    double speed = current * 21;      

    String trame = System.currentTimeMillis() + KeyID +";"+ Mode +";"+ clutch1  + ";"+ clutch2  + ";"+ d.format(speed) +";"+ d.format(current) +";"+ d.format(tension);
    return trame;
} 

public static void main(String[] args) {
  savetoCSV(SimulationTrame(null, false, false));
  }
}