package UnitTest;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.fazecast.jSerialComm.SerialPort;

public class SerialCom {

    static public String Read(Scanner scanner){      
               
        try {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                System.out.println(input);
                // System.out.println(input.split(";")[0]);
                // System.out.println(input.split(";")[1]);
                // System.out.println(input.split(";")[2]);                
                return input;
            }

        } catch (Exception e) {
            e.printStackTrace();
         }
                
         return "None;None;None";
        }
    

    public static void main(String[] args) throws InterruptedException {
        SerialPort port = SerialPort.getCommPort("COM3"); // replace with your port name
        port.setBaudRate(9600);
        port.openPort();
        InputStream inputStream = port.getInputStream();  
        Scanner scanner = new Scanner(inputStream);

        while(true){
         System.out.println(Read(scanner)); 
         TimeUnit.SECONDS.sleep(1);
        }
              
        
    }

}
