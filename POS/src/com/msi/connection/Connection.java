package com.msi.connection;

import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Connection {
    public static java.sql.Connection DBconnector(){
        
        try {
            String url = "jdbc:mysql://localhost:3306/"; 
            Class.forName("org.sqlite.JDBC");
            java.sql.Connection conn=DriverManager.getConnection("jdbc:sqlite:JJSPOSDB.sqlite");
            return conn;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
           return null;
        }
        
    }
}
