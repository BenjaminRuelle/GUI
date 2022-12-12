package UnitTest;
import javax.swing.*;

public class ConsoleTuto extends JFrame{

   {
      
   }

 public static void main ( String[] args )
 {
    JPanel middlePanel = new JPanel ();
    
    middlePanel.setBounds(150,200,100,30);

    // create the middle panel components

    JTextArea display = new JTextArea ( 16, 58 );
    
    display.setEditable(true); // set textArea non-editable
    JScrollPane scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    JLabel speedLabel=new JLabel("xxxxx");
    speedLabel.setBounds(150,250,150,30);

    //Add Textarea in to middle panel
    middlePanel.add ( scroll);
    middlePanel.add ( speedLabel );

    
    // My code
    JFrame frame = new JFrame ();
    frame.add ( middlePanel );
    frame.pack ();
    frame.setLocationRelativeTo ( null );
    frame.setVisible ( true );
 }
}