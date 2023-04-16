package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;



    public class Withdrawl extends JFrame implements ActionListener {
        JTextField amount;
        JButton withdrawl,back;
        String pinnumber;
        Withdrawl(String pinnumber){
            this.pinnumber = pinnumber;
            setLayout(null);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
            Image i2 = i1.getImage().getScaledInstance(900,700,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0,0,900,700);
            add(image);

            JLabel text = new JLabel("Enter the amount you want to withdraw");
            text.setForeground(Color.white);
            text.setFont(new Font("System", Font.BOLD,16));
            text.setBounds(180,230,700,35);
            image.add(text);

            amount  = new JTextField();
            amount.setFont(new Font("Raleway",Font.BOLD,22));
            amount.setBounds(170,280,320,25);
            image.add(amount);

            withdrawl = new JButton("Withdraw");
            withdrawl.setBounds(345,320,150,30);
            withdrawl.addActionListener(this);
            image.add(withdrawl);

            back = new JButton("Back");
            back.setBounds(345,360,150,30);
            back.addActionListener(this);
            image.add(back);


            setSize(900,700);
            setLocation(300,0);
            setVisible(true);
        }

        public static void main(String[] args) {
            new Bank.Deposit("");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == withdrawl){
                String number = amount.getText();
                Date date  = new Date();
                if (number.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                }
                else {
                    try {
                        Conn conn = new Conn();
                        String query = "insert into bank values( '" + pinnumber + "','" + date + "','Withdrawl','" + number + "')";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs " + number + " Withdrawn Successfully");
                        setVisible(false);
                        new Transaction(pinnumber).setVisible(true);

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            } else if (ae.getSource()==back) {
                setVisible(false);

                new Transaction(pinnumber).setVisible(true);

            }
        }
    }


