import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class HomeFrame extends JFrame implements ActionListener{
    
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
        
    JPanel dataPanel = new JPanel(new BorderLayout());   // Panel for data
    JPanel consolPanel = new JPanel(new BorderLayout());   // Panel for consol
    JPanel masterPanel = new JPanel(new BorderLayout());   // Panel wgich containt data + console + graph
    JPanel graphPanel = new JPanel(new BorderLayout());    //Panel for live graph
    JPanel bodydataPanel = new JPanel(new BorderLayout());
    JPanel middPanel = new JPanel();

    GridLayout dataGridLayout = new GridLayout(0,2); //Grid for data
    JPanel clutchRow = new JPanel(new BorderLayout()); //Row for display clutch buttons
    JPanel modeRow = new JPanel(new BorderLayout()); //w for display mode    
    JPanel dataGrid = new JPanel(); 

    //Initalisation of componants 
    Choice mode = new Choice();   
    JButton modeButton=new JButton("Select mode");  
    JLabel dataPanelLabel = new JLabel("Live Data Window");
    JButton clutch1Button=new JButton("Clutch 1");  
    JButton clutch2Button=new JButton("Clutch 2");  
    JLabel speedLabel=new JLabel("    Rotation speed (tr.min): ");
    JLabel speedValue=new JLabel("000 tr.min"); 
    JLabel tensionLabel=new JLabel("    Output tension (V): ");
    JLabel tensionValue=new JLabel("00 V "); 
    JLabel currentLabel=new JLabel("    Output current (mA): ");
    JLabel currentValue=new JLabel("000 mA");
    JTextArea console = new JTextArea( 10, 135);
    JLabel consoleLabel = new JLabel("Console");
    JScrollPane scroll = new JScrollPane(console); 

    HomeFrame(){
        console.setEditable(true);
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS ); 

        //set color  
        setColor();        
        //Position of dataPanel
        setPosition();
        //Adding components
        addComponents();
        //Adding action
        addActionEvent();           
        //Adding button in middPanel
        middPanel.add(mode);
        middPanel.add(modeButton);
        //Adding Graph and dataPanel to Midd Panel
        middPanel.add(graphPanel); 
        middPanel.add(dataPanel);   
        //Adding Panels to MasterPanel        
        masterPanel.add(middPanel);
        masterPanel.add(consolPanel,BorderLayout.PAGE_END);       

        Container container=getContentPane(); //Trick pour pouvoir instancié dans le void
        container.add(masterPanel);        
    }

    public void setColor()
    {
        console.setBackground(Color.BLACK);
        console.setForeground(Color.white);       
        consolPanel.setBackground(Color.LIGHT_GRAY);
        dataPanel.setBackground(Color.LIGHT_GRAY);
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        graphPanel.setBackground(Color.RED);
        //consoleLabel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        //modeButton.setFont(new Font("Arial", Font.PLAIN, 12));

    }

    public void setPosition()
    {
        middPanel.setLayout(null);
        dataPanel.setBounds(750,80,400,300);
        graphPanel.setBounds(10,40,600,400);
        mode.setBounds(500,2,150,30);
        modeButton.setBounds(650,2,150,30);
    }

    public void addActionEvent()
    {
     //adding Action listener to components
     clutch1Button.addActionListener(this);        
     clutch2Button.addActionListener(this);
     modeButton.addActionListener(this); 
    }

    public void addComponents(){
     //Adding each components to the dataGrid
     
     mode.add("Auto");
     mode.add("Manual");
     //modeRow.add(modeButton,BorderLayout.EAST);
     //modeRow.add(mode,BorderLayout.WEST);
         
     dataGrid.setLayout(new GridLayout(3,2));
     dataGrid.add(speedLabel);
     dataGrid.add(speedValue);  
     dataGrid.add(clutch1Button);
     dataGrid.add(clutch2Button);
     dataGrid.add(currentLabel);
     dataGrid.add(currentValue);
     dataGrid.add(tensionLabel);
     dataGrid.add(tensionValue);       

     clutchRow.add(clutch1Button,BorderLayout.EAST);
     clutchRow.add(clutch2Button,BorderLayout.WEST);

     dataPanel.add(dataPanelLabel,BorderLayout.PAGE_START); 
     dataPanel.add(dataGrid,BorderLayout.CENTER);         
     dataPanel.add(clutchRow,BorderLayout.PAGE_END);
     
     //Adding each components to consolPanel               
     consolPanel.add(scroll,BorderLayout.SOUTH);
     consolPanel.add(consoleLabel,BorderLayout.NORTH);   
          
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
        
        HomeFrame frame = new HomeFrame();                 
        frame.setTitle("Home BATT_V0.2");         
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);            
        frame.setIconImage(Toolkit.getDefaultToolkit().
         getImage(HomeFrame.class.getResource("/images/Icon.png")));  
            
        }
    
    
     
}