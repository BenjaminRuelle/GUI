import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
//Creating LoginFrame class
public class LoginFrame extends JFrame implements ActionListener {

    Container container=getContentPane();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("LOGIN");    
    JCheckBox showPassword=new JCheckBox("Show Password");
    
    //Creating constructor of LoginFrame() class
    LoginFrame()    
    {
     //Calling setLayoutManger() method inside the constructor.
     setLayoutManager();
     setLocationAndSize();
     addComponentsToContainer();   
     addActionEvent();
    }
 
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
        int x_field = 282;
        int x_label = 202;
             
        userLabel.setBounds(x_label,100,100,30);
        passwordLabel.setBounds(x_label,170,100,30);
        userTextField.setBounds(x_field,100,150,30);
        passwordField.setBounds(x_field,170,150,30);
        showPassword.setBounds(x_field,200,150,30);
        loginButton.setBounds(x_field,250,100,30);        
    }

    public void addComponentsToContainer()
    {
       //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);        
    }

    public void addActionEvent()
    {
       //adding Action listener to components
        loginButton.addActionListener(this);        
        showPassword.addActionListener(this);
    }

    //Overriding actionPerformed() method
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("Batt1") && pwdText.equalsIgnoreCase("gravity")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                HomeFrame.main(null);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
 
        }        
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            } 
        }
    } 
    
    public void setLayoutManager()
   {
       //Setting layout manager of Container to null
       container.setLayout(null);
   }
   
    public class Login 
    {
        public static void main(String[] a){
            //Creating object of LoginFrame class and setting some of its properties
            LoginFrame frame=new LoginFrame();
            frame.setTitle("Login BATT_V0.1");
            frame.setVisible(true);
            frame.setBounds(10,10,644,400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setIconImage(Toolkit.getDefaultToolkit().
             getImage(Login.class.getResource("/images/Icon.png")));
     
        }
    }
    
}