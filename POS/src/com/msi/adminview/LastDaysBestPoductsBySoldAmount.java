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
public class LastDaysBestPoductsBySoldAmount {
  java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     String L7BestProduct = null;
    double L7BestProductAmount = 0;
    String L7BestProductUnit = null;
     ArrayList<String> L7BestSaleProduct = new ArrayList();
    ArrayList<Double> L7TBestSaleProductAmount = new ArrayList();
    ArrayList<String> L7TBestSaleProductUnit = new ArrayList();
   
    public LastDaysBestPoductsBySoldAmount() {
        conn = Connection.DBconnector();
    //    bestProducts();
    }
    
    public void bestProducts(String date){
      
        try{
             String sql = "SELECT SUM(Amount),Unit,PName FROM  Customer,Product where Customer.PID=Product.PID and Date>='"+date+"' \n"
                    + "GROUP BY Customer.PID ORDER BY SUM(Amount) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                L7BestProduct = rs.getString("PName");
                L7BestSaleProduct.add(L7BestProduct);
                  //TBestSaleProductBySaleNumber2.add(TodaysBestProductBySaleNumber);
                

                L7BestProductAmount = rs.getDouble("SUM(Amount)");
                L7TBestSaleProductAmount.add(L7BestProductAmount);
              //  TBestSaleProductTotalSaleTime2.add(TodaysBestProductTotalSale);
                // cmb1.addItem(TBestSaleProduct);
                
                
                L7BestProductUnit = rs.getString("Unit");
                L7TBestSaleProductUnit.add(L7BestProductUnit);
            }
            double [] val = new double[200];
            String[] s1 = new String[200];
            String[] s2 = new String[200];

            for (int i = 0; i < val.length; i++) {
                L7TBestSaleProductAmount.add(val[i]);
                L7BestSaleProduct.add(s1[i]);
                L7TBestSaleProductUnit.add(s2[i]);
                

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
