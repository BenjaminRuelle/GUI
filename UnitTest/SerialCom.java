package UnitTest;

import java.io.InputStream;
import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;

public class SerialCom {

    static public String Read(String portName){      
        SerialPort port = SerialPort.getCommPort(portName); // replace with your port name
        port.setBaudRate(9600);
        port.openPort();
        InputStream inputStream = port.getInputStream();  
        Scanner scanner = new Scanner(inputStream);        
        try {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                // System.out.println(input);
                // System.out.println(input.split(";")[0]);
                // System.out.println(input.split(";")[1]);
                // System.out.println(input.split(";")[2]);
                scanner.close();
                return input;
            }

        } catch (Exception e) {
            e.printStackTrace();
         }
         scanner.close();        
         return "None;None;None";
        }
    

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println(Read("COM3"));        
        
    }

}
