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
public class InsufficientAmountProducts {
java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
     String insufficientProductName = null;
    double InsufficientProductAmount = 0;
    String insufficientProductUnit = null;

    ArrayList<String> insufficientProductNameArrayList = new ArrayList();
    ArrayList<Double> InsufficientProductAmountArraList = new ArrayList();
    ArrayList<String> insufficientProductUnitArrayList = new ArrayList();
    public InsufficientAmountProducts() {
        conn = Connection.DBconnector();
        getInsufficientAmountProduct();
    }
    
    
    public void getInsufficientAmountProduct() {
        insufficientProductNameArrayList.clear();
        InsufficientProductAmountArraList.clear();
        insufficientProductUnitArrayList.clear();
        try {

            String sql = "SELECT PName, PAmount,Unit from Product where PAmount<6  \n"
                    + "Group by PName order by PAmount ASC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                insufficientProductName = rs.getString("PName");
                insufficientProductNameArrayList.add(insufficientProductName);

                InsufficientProductAmount = rs.getDouble("PAmount");
                InsufficientProductAmountArraList.add(InsufficientProductAmount);
                insufficientProductUnit = rs.getString("Unit");
                insufficientProductUnitArrayList.add(insufficientProductUnit);
                // cmb1.addItem(TBestSaleProduct);
            }
//
//            double[] d = new double[200];
//            String[] s1 = new String[200];
//            String[] u = new String[200];
//
//            for (int i = 0; i < d.length; i++) {
//                InsufficientProductAmountArraList.add(d[i]);
//                insufficientProductNameArrayList.add(s1[i]);
//                insufficientProductUnitArrayList.add(u[i]);
//            }

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
