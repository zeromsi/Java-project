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
public class LossyProductOfBetweenTwoDates {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ArrayList<String> PName = new ArrayList();

    ArrayList<Double> Profit = new ArrayList();

    public LossyProductOfBetweenTwoDates() {
        conn = Connection.DBconnector();

    }

    public void getInfo(String year, String endYear) {
        PName.clear();
        Profit.clear();
        try {
            String sql = "Select PName,SUM(Customer.profit) from Customer,Product where Product.PID=Customer.PID and Customer.Profit<0 and  "
                    + "Date between '" + year + "' and '" + endYear + "' group by Customer.PID order by SUM(Customer.profit) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Profit.add(rs.getDouble("SUM(Customer.profit)"));

            }

           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }

    }

}
