package Bank;

import java.sql.*;

//register driver class
//create connection
//create statement
//execute queries
//close connection

public class Conn {
    Connection c; //for creating connrction global objext is created
    Statement s;

    public Conn(){  //constructor
        try {                  //since mysql is external entity so we will face runtime error so handling is done

            c =DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root","Aashuakm12@");
            s = c.createStatement();


        }catch (Exception e){
            System.out.println(e);
        }

    }
}
