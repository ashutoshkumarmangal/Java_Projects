package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JFrame implements ActionListener {
    JButton deposit, withdrawl,ministatement, pinchange,fastcash,balanceenquiry,exit;
    String pinnumber;
    Transaction(String pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,700,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image  = new JLabel(i3);
        image.setBounds(0,0,900,700);
        add(image);

        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(220,230,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);//when we need to display text on image s

        deposit = new JButton("Deposit");
        deposit.setBounds(170,325,150,22);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Cash Withdrawl");
        withdrawl.setBounds(355,325,150,22);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170,350,150,22);
        fastcash.addActionListener(this);
        image.add(fastcash);


        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(355,350,150,22);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(170,375,150,22);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Balance Enquiry");
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
        new Transaction("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);

        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);

        } else if (ae.getSource() == pinchange) {
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        }
        else if (ae.getSource() == balanceenquiry) {
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
    }else if (ae.getSource() == ministatement) {
           // setVisible(false);
            new MiniStatement(pinnumber).setVisible(true);

    }}}
