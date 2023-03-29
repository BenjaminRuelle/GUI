package UnitTest;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;

public class SerialCom {

    static public void Read(SerialPort port) {
        Scanner scanner = new Scanner(inputStream);
        Thread.sleep(1000);
        Scanner scanner = new Scanner(inputStream);
        Thread.sleep(1000);
        try {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                System.out.println(input);
                System.out.println(input.split(";")[0]);
                System.out.println(input.split(";")[1]);
                System.out.println(input.split(";")[2]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // set up serial port connection
        SerialPort port = SerialPort.getCommPort("COM20"); // replace with your port name
        port.setBaudRate(9600);
        port.openPort();
        InputStream inputStream = port.getInputStream();

        while (true) {
            Scanner scanner = new Scanner(inputStream);
            Thread.sleep(1000);
            try {
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    System.out.println(input);
                    System.out.println(input.split(";")[0]);
                    System.out.println(input.split(";")[1]);
                    System.out.println(input.split(";")[2]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            scanner.close();
        }

    }

}
