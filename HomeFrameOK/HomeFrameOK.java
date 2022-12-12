import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class HomeFrameOK extends JFrame implements ActionListener{
    
    /**
                 * Expected layout:
                 * ******************************** 
                 * *        Data Panel            *
                 * *                              *
                 * *                              * 
                 * *                              * 
                 * *                              * 
                 * *  **********************      *
                 * *                              * 
                 * *       Console Panel          * 
                 * *                              * 
                 * *                              *
                 * ******************************** 
                 * 
                 * 5pix around the panel area
                 */
        
    //Initalisation of Panel
        
    JPanel dataPanel = new JPanel();   // Panel for data
    JPanel consolPanel = new JPanel(new BorderLayout());   // Panel for consol
    JPanel masterPanel = new JPanel(new BorderLayout());   // Panel for data
    JPanel connectionPanel = new JPanel(new BorderLayout()); // Panel for connection
    //Initalisation of componants 
    Choice mode = new Choice();   
    JButton modeButton=new JButton("Select mode");  
    JButton clutch1Button=new JButton("Clutch 1");  
    JButton clutch2Button=new JButton("Clutch 2");  
    JLabel speedLabel=new JLabel("Rotation speed (tr.min): ");
    JLabel speedValue=new JLabel("000 tr.min"); 
    JLabel tensionLabel=new JLabel("Output tension (V): ");
    JLabel tensionValue=new JLabel("00 V "); 
    JLabel currentLabel=new JLabel("Output current (mA): ");
    JLabel currentValue=new JLabel("000 mA");
    JTextArea console = new JTextArea( 10, 135);
    JLabel consoleLabel = new JLabel("Console:");
    JScrollPane scroll = new JScrollPane(console); 

    HomeFrameOK(){
        console.setEditable(true);
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS ); 

        //set color  
        console.setBackground(Color.BLACK);
        console.setForeground(Color.white);       
        consolPanel.setBackground(Color.LIGHT_GRAY);
        consoleLabel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        modeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        
        //Position of dataPanel
        setPosition();
        //Adding components
        addComponents();
        //Adding action
        addActionEvent();
        
        //Ad Panels to MasterPanel
        masterPanel.add(dataPanel, BorderLayout.CENTER);
        masterPanel.add(consolPanel,BorderLayout.PAGE_END);       

        Container container=getContentPane(); //Trick pour pouvoir instancié dans le void
        container.add(masterPanel);        
    }

    public void setPosition()
    {
        dataPanel.setLayout(null); 
        mode.setBounds(700,200,100,30);
        modeButton.setBounds(800,200,100,20);
        speedLabel.setBounds(600,240,200,30);     
        speedValue.setBounds(800,240,200,30);        
        tensionLabel.setBounds(600,270,200,30);
        tensionValue.setBounds(800,270,200,30);
        currentLabel.setBounds(600,300,200,30);
        currentValue.setBounds(800,300,200,30);        
        clutch1Button.setBounds(700,330,100,30);
        clutch2Button.setBounds(800,330,100,30);
    }

    public void addActionEvent()
    {
     //adding Action listener to components
     clutch1Button.addActionListener(this);        
     clutch2Button.addActionListener(this);
     modeButton.addActionListener(this); 
    }

    public void addComponents(){
     //Adding each components to the dataPanel
     dataPanel.add(modeButton);
     dataPanel.add(mode);        
     dataPanel.add(speedLabel);
     dataPanel.add(speedValue);  
     dataPanel.add(clutch1Button);
     dataPanel.add(clutch2Button);
     dataPanel.add(currentLabel);
     dataPanel.add(currentValue);
     dataPanel.add(tensionLabel);
     dataPanel.add(tensionValue);

     //Adding each components to consolPanel               
     consolPanel.add(scroll,BorderLayout.SOUTH);
     consolPanel.add(consoleLabel,BorderLayout.NORTH);   
     mode.add("Auto");
     mode.add("Manual");

     
    }

    public void actionPerformed(ActionEvent e) {
        //Coding Part of clutch1Button
        if (e.getSource() == clutch1Button) {
            console.append("["+new Timestamp(System.currentTimeMillis())+"] clutch 1 ON\n");
        }        
       //Coding Part of clutch1Button
        if (e.getSource() == clutch2Button) {
            console.append("["+new Timestamp(System.currentTimeMillis())+"] clutch 2 ON\n");
        }
        //Coding Part of modeButton
        if (e.getSource() == modeButton) {
            console.append("["+new Timestamp(System.currentTimeMillis())+"] Mode: "+ mode.getSelectedItem()+"\n");
        }   
    }   
 public static void main(String[] a){
        //Creating object of HomeFrame class and setting some of its properties
        HomeFrameOK frame = new HomeFrameOK();                 
        frame.setTitle("Home BATT_V0.1_OK");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);            
        frame.setIconImage(Toolkit.getDefaultToolkit().
         getImage(HomeFrameOK.class.getResource("/images/Icon.png")));  
            
        }
    
    
     
}