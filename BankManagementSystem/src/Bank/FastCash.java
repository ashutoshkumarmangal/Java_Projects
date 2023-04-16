package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

//public class FastCash {

    public class FastCash extends JFrame implements ActionListener {
        JButton deposit, withdrawl,ministatement, pinchange,fastcash,balanceenquiry,exit;
        String pinnumber;
        FastCash(String pinnumber){
            this.pinnumber = pinnumber;

            setLayout(null);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
            Image i2 = i1.getImage().getScaledInstance(900,700,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image  = new JLabel(i3);
            image.setBounds(0,0,900,700);
            add(image);

            JLabel text = new JLabel("Select withdrawl amount");
            text.setBounds(220,230,700,35);
            text.setForeground(Color.white);
            text.setFont(new Font("System",Font.BOLD,16));
            image.add(text);//when we need to display text on image s

            deposit = new JButton("Rs 500");
            deposit.setBounds(170,325,150,22);
            deposit.addActionListener(this);
            image.add(deposit);

            withdrawl = new JButton("Rs 1000");
            withdrawl.setBounds(355,325,150,22);
            withdrawl.addActionListener(this);
            image.add(withdrawl);

            fastcash = new JButton("Rs 2000");
            fastcash.setBounds(170,350,150,22);
            fastcash.addActionListener(this);
            image.add(fastcash);


            ministatement = new JButton("Rs 5000");
            ministatement.setBounds(355,350,150,22);
            ministatement.addActionListener(this);
            image.add(ministatement);

            pinchange = new JButton("Rs 8000");
            pinchange.setBounds(170,375,150,22);
            pinchange.addActionListener(this);
            image.add(pinchange);

            balanceenquiry = new JButton("Rs 10000");
            balanceenquiry.setBounds(355,375,150,22);
            balanceenquiry.addActionListener(this);
            image.add(balanceenquiry);

            exit = new JButton("Exit");
            exit.setBounds(355,400,150,22);
            exit.addActionListener(this);
            image.add(exit);


            setSize(900, 700);
            setLocation(300,0);
            //setUndecorated(true); //this is to remove the above icons
            setVisible(true);//this should always be written in last


        }

        public static void main(String[] args) {
            new FastCash("");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == exit){
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
            } else {
                String amount = ((JButton)ae.getSource()).getText().substring(3);
                Conn c = new Conn();
                try{
                    ResultSet rs = c.s.executeQuery("Select * from bank where pin = '"+pinnumber+"'");
                    int balance =0;
                    while(rs.next()){
                        if(rs.getString("type").equals("Deposit")){
                            balance += Integer.parseInt(rs.getString("amount"));

                        }
                        else {
                            balance -= Integer.parseInt(rs.getString("amount"));

                        }
                    }
                    if(ae.getSource() != exit && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                    }
                    Date date = new Date();
                    String query  = " insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+amount+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs "+ amount +" Debited Successfully");
                     setVisible(false);
                     new Transaction(pinnumber).setVisible(true);


                }catch (Exception e){
                    System.out.println(e);
                }
            }

        }
    }


