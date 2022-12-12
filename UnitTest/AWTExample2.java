package UnitTest;
// importing Java AWT class  
import java.awt.*;    
  
// class AWTExample2 directly creates instance of Frame class  
class AWTExample2 {    
  
   // initializing using constructor  
   AWTExample2() {  
  
      // creating a Frame  
      Frame f = new Frame();  
  
      // creating a Label  
      Label usernameLabel = new Label("Username (Email @icam.fr)");   
  
      // creating a Button for login
      Button b = new Button("Login");  
    
      // creating a TextField  
      TextField usernamField = new TextField();  
  
      // setting position of above components in the frame  
      usernameLabel.setBounds(20, 80, 80, 30);  
      usernamField.setBounds(20, 100, 80, 30);  
      b.setBounds(100, 100, 80, 30);  
  
      // adding components into frame    
      f.add(b);  
      f.add(usernameLabel);  
      f.add(usernamField);  
  
      // frame size 300 width and 300 height    
      f.setSize(400,300);  
  
      // setting the title of frame  
      f.setTitle("Login BATT1_V0.1");   
          
      // no layout  
      f.setLayout(null);   
  
      // setting visibility of frame  
      f.setVisible(true);  
}    
  
// main method  
public static void main(String args[]) {   
  
// creating instance of Frame class   
AWTExample2 awt_obj = new AWTExample2();    
  
}  
  
}    