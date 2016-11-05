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
 * 
 * 
 */


public class LastDaysBestProductsByTotalPrice {
    
    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     String L7BestProduct = null;
    double L7BestProductTotatlPrice = 0;
    String L7BestProductUnit = null;
     ArrayList<String> L7BestSaleProduct = new ArrayList();
    ArrayList<Double> L7TBestSaleProductTotalPrice = new ArrayList();
    ArrayList<String> L7TBestSaleProductUnit = new ArrayList();

    public LastDaysBestProductsByTotalPrice() {
        conn = Connection.DBconnector();
       // getBestProducts();
    }
    
    
    public void getBestProducts(String date){
        
    
        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT SUM(TotalPrice),Unit,PName FROM  Customer,Product where Customer.PID=Product.PID and  Date>='"+date+"' \n"
                    + "GROUP BY Customer.PID ORDER BY SUM(TotalPrice) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                //TodaysBestProductId = rs.getString("PID");
                L7BestProductTotatlPrice = rs.getDouble("SUM(TotalPrice)");
                L7TBestSaleProductTotalPrice.add(L7BestProductTotatlPrice);

                L7BestProduct = rs.getString("PName");
                L7BestSaleProduct.add(L7BestProduct);

                L7BestProductUnit = rs.getString("Unit");
                L7TBestSaleProductUnit.add(L7BestProductUnit);
                // cmb1.addItem(TBestSaleProduct);
            }

            double[] d = new double[15];
            String[] s1 = new String[15];
            String[] s2 = new String[15];

            for (int i = 0; i < d.length; i++) {
                L7TBestSaleProductTotalPrice.add(d[i]);
                L7BestSaleProduct.add(s1[i]);
                L7TBestSaleProductUnit.add(s2[i]);
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
