import java.lang.Math;
import java.text.DecimalFormat;
import java.util.*;

public class Simulation {
    
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
    
   
    public static void main(String[] a){
        System.out.println(SimulationTrame("Auto", true, true));
        //String[] splited = SimulationTrame("Auto",true,true).split(";");
        //System.out.println(splited[1]);
    }
}
