/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.userview;

import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class BookmarkClass {
 java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public BookmarkClass() {
         conn = Connection.DBconnector();
    }
    
    public boolean Bookmark(int PID,String EID){
        try{
            String sql="Select PID,EID from Bookmark where PID='"+PID+"' and EID='"+EID+"'";  pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR."+e.getMessage());
        }finally{
            try{
                pst.close();
                rs.close();
            }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "ERROR."+e.getMessage());
            }
        
        }
     return false;
        
    }
    }
    
//    public static void main(String[] args) {
////      BookmarkClass b=new BookmarkClass();
////        if(b.Bookmark(1,"1")==true){
////            System.out.print("Yes");
////        }else if(b.Bookmark(1,"1")==false){
////            System.out.print("No");
////        }
////    }
//    }
//}
