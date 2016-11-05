/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class LastDaysAllProductWithSoldTimes {
 java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     String L7BestProduct = null;
    Integer L7BestProductSoldTimes = 0;
     ArrayList<String> L7BestSaleProduct = new ArrayList();
    ArrayList<Integer> L7TBestSaleProductSoldTimes = new ArrayList();

    public LastDaysAllProductWithSoldTimes() {
         conn = Connection.DBconnector();
       // bestProducts();
    }
    
      public void bestProducts(String Date){
   
        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT PName,Count(Customer.PID)\n"
                    + " FROM  Customer,Product where Customer.PID=Product.PID and Date>='"+Date+"' \n"
                    + " GROUP BY  Customer.PID\n"
                    + "    ORDER BY COUNT(*) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                //TodaysBestProductId = rs.getString("PID");
                L7BestProductSoldTimes = rs.getInt("Count(Customer.PID)");
                L7TBestSaleProductSoldTimes.add(L7BestProductSoldTimes);

                L7BestProduct = rs.getString("PName");
                L7BestSaleProduct.add(L7BestProduct);

             
            }

        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
    }
    
}
