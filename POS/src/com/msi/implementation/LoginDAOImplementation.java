/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.msi.DAO.LoginDAO;
import com.msi.connection.Connection;
import com.msi.login.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class LoginDAOImplementation implements LoginDAO{

    @Override
    public int login(Login login) {
         java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();
        
      try{
            String sql=" select *from ( select Username as username,Password as pass from Employee_Info\n" +
"    union all\n" +
"    select username as username,Password as pass from User) a where Username='"+login.getUsername()+"' and Pass='"+login.getPassword()+"'";
             pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            int i=0;
            if (rs.next()) {
                i=i+1;   
            }
          if(login.getUsername().equals("Admin") && i==1){
                return 2;
            } else if(i==1){
                return 1;     
            } 
            
            else if(i<1){
                return 0;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR:"+e.getMessage());
        }finally{
            try{
                  pst.close();
                rs.close();
            }catch(Exception ex){
              
            }
        }
        return 0;
        
    }
    
}
