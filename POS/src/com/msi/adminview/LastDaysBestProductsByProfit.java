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

/**
 *
 * @author MSI
 */
public class LastDaysBestProductsByProfit {
java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     String L7BestProduct = null;
    double L7BestProductprofit = 0;
   
     ArrayList<String> L7BestSaleProduct = new ArrayList();
    ArrayList<Double> L7TBestSaleProductProfit = new ArrayList();
 
   
    public LastDaysBestProductsByProfit() {
         conn = Connection.DBconnector();
        //bestProducts();
    }
    
     public void bestProducts(String date){
       
        try{
             String sql = "SELECT PName,SUM(Amount),PPrice,SUM(TotalPrice),SUM(Customer.Profit)  from Customer,Product  where Product.PID=Customer.PID and Customer.profit>0 and Date>='"+date+"'  \n"
                    + "GROUP BY  Customer.PID ORDER BY Customer.profit DESC ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                L7BestProduct = rs.getString("PName");
                L7BestSaleProduct.add(L7BestProduct);
              //  TBestSaleProductByProfit2.add(TodaysBestProductByProfit);
                L7BestProductprofit = rs.getDouble("SUM(Customer.Profit)");
                L7TBestSaleProductProfit.add(L7BestProductprofit);
                //TBestSaleProductTotalProfit2.add(TodaysBestProductTotalProfit);
                // cmb1.addItem(TBestSaleProduct);
            }
            double [] val = new double[200];
            String[] s1 = new String[200];
       

            for (int i = 0; i < val.length; i++) {
                L7TBestSaleProductProfit.add(val[i]);
                L7BestSaleProduct.add(s1[i]);
              
                

            }
            
            
        }catch(Exception e){
            
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
    }
    
    
}
