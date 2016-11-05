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
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class TodaysLossyProducts {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ArrayList<String> ProductNameArrayList = new ArrayList();
    ArrayList<Double> ProductAmountArraList = new ArrayList();
    ArrayList<Double> LossArraList = new ArrayList();
    ArrayList<Double> LossPerProductArraList = new ArrayList();
   ArrayList<String> Unit = new ArrayList();
    public TodaysLossyProducts() {
        conn = Connection.DBconnector();
    }

    public void getInfo(String date) {
        ProductNameArrayList.clear();
        ProductAmountArraList.clear();
        LossArraList.clear();
        LossPerProductArraList.clear();
        Unit.clear();
        try {
            String sql = "SELECT Pname,Amount,Unit,-(Customer.Profit),"
                    + " -(Customer.Profit/Amount) as PerLoss FROM Customer,Product where "
                    + "Product.Pid=Customer.PID and Customer.profit<0 and date='" + date + "'";
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
               
                ProductNameArrayList.add(rs.getString("Pname"));

                ProductAmountArraList.add(rs.getDouble("Amount"));

                Unit.add(rs.getString("Unit"));
                
                LossArraList.add(rs.getDouble("-(Customer.Profit)"));
   
                LossPerProductArraList.add(rs.getDouble("PerLoss"));
              
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
    }

}
