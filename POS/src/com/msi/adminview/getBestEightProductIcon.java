/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class getBestEightProductIcon{

        ArrayList<ImageIcon> format = new ArrayList();
        ImageIcon formatFake=null;
  
    public void getImageIcons(String date){
        java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
           conn = Connection.DBconnector();
        try{
            String sql = "SELECT SUM(Amount),Image FROM  Customer,Product where Customer.PID=Product.PID and Date='"+date+"' \n"
                    + "GROUP BY Customer.PID ORDER BY SUM(Amount) DESC";
            
              pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                 byte[] imagedata = rs.getBytes("Image");
                 
                  if(imagedata.equals("")){
                    format.add(null);
                }else{
                format.add(new ImageIcon(imagedata));
                }
            }
            for(int i=0;i<8;i++){
                format.add(null);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error."+e.getMessage());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }
}
