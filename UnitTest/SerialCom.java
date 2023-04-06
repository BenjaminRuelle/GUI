package UnitTest;

import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.fazecast.jSerialComm.SerialPort;

public class SerialCom {
    public static Scanner scanner;

     public static String Read() { //Return a string value "None;None;None" if no NextLine is detected

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
        SerialPort port = SerialPort.getCommPort("COM20"); // replace with your port name
        port.setBaudRate(9600);
        port.openPort();
        InputStream inputStream = port.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        SerialCom serialCom = new SerialCom(); // création d'une instance de la classe SerialCom
        serialCom.scanner = scanner; // assignation de la variable scanner à la variable membre scanner de l'instance de la classe

        while (true) {
            System.out.println(SerialCom.Read()); // appel de la méthode Read() sur l'instance de la classe
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
