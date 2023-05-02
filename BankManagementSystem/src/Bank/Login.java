package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.*;

public class Login extends JFrame implements ActionListener{   //now since we implements interface Actionlistener so we need to override all the method present in that interface

    JButton login,clear,signup; // now its globally defined and can be accessed outside the constructor
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){
        setTitle("ATM Simulator");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));//use image by using ImageIcon class and by creating object and importing by using classloader class and static method getsystemResource
        Image i2  = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);//setting the size of the img and this is called by AWT
        //we can't add img directly to the jlabel so we convert it into img icons
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label  = new JLabel(i3);
        label.setBounds(70,10,100,100);//to position the img since it os on label to place that label to correct place
        //since we are using custom layout but swing provide its layout so we need to null that layout by setLayout(null);
        add(label);

        //adding text on the label
        JLabel text  = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));//setting font
        text.setBounds(200,40,400,40);
        add(text);

        JLabel cardno  = new JLabel("Card No: ");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));//setting font
        cardno.setBounds(120,150,150,30);//since the above label take 40+40 so we need to take height more than that
        add(cardno);

        //this to add the textfield
        cardTextField = new JTextField(); // since using outside constructor also defined as global
        cardTextField.setBounds(300,150,230,30);
        cardTextField.setFont(new Font("Arial", Font.BOLD,14));
        add(cardTextField);

        JLabel pin  = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway",Font.BOLD,28));//setting font
        pin.setBounds(120,220,250,30);//again since above takes 150+40 so we need to take height more than 190
        add(pin);

        //this to add the textfield
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
        pinTextField.setFont(new Font("Arial", Font.BOLD,14));
        add(pinTextField);

        login = new JButton("SIGN IN"); //now since this JButton is globally defined so we remove it from here
        login.setBounds(300,300,100,30);
        login.setBackground(Color.black);
        login.setBackground(Color.white);
        login.addActionListener(this); // to notice the capture when the button is clicked
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.black);
        clear.setBackground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.black);
        signup.setBackground(Color.white);
        signup.addActionListener(this);
        add(signup);


        //change frame colour
        getContentPane().setBackground(Color.white);//changes from default window colour to white

        setSize(800, 480);//creates frame but not visible
        setVisible(true);// this help to visible frame
        setLocation(350,200);// since it start from default top-left we need to set location
    }

    @Override// override method of the actionlistner
    public void actionPerformed(ActionEvent ae) {
        if( ae.getSource() == clear ){
             cardTextField.setText(""); //when clear pressed everything disappear
             pinTextField.setText("");

        } else if (ae.getSource() == login) {
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            if (cardnumber.matches("[0-9]+") && pinnumber.matches("[0-9]+")) { // Input validation for card number and pin
                Conn conn = new Conn();
                String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'";
                try {
                    ResultSet rs = conn.s.executeQuery(query);
                    if (rs.next()) {
                        setVisible(false);
                        new Transaction(pinnumber).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter valid Card Number or Pin");
            }

            } else if (ae.getSource() == signup) {
            setVisible(false);
            new Signupone().setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Login(); //created object and this calls constructor so create constructor
    }
}
