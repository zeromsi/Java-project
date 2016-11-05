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
public class MonthlyBudgetingInfo {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<String> PName = new ArrayList();
    ArrayList<Double> Amount = new ArrayList();
    ArrayList<Double> TotalPrice = new ArrayList();
    ArrayList<String> unitExistingProduct = new ArrayList();
    ArrayList<String> PNameOfThreeMonth = new ArrayList();
    ArrayList<Double> AmountOfThreeMonth = new ArrayList();
    ArrayList<Double> TotalPriceOfThreeMonth = new ArrayList();

    ArrayList<String> PNameOfExistingProduct = new ArrayList();
    ArrayList<Double> ExistingAmount = new ArrayList();
    ArrayList<Double> ProductPurchasePrice = new ArrayList();

    ArrayList<String> PNameOfPreviousYearSoldProduct = new ArrayList();
    ArrayList<Double> PSoldAmountOfPreviousYearSoldProduct = new ArrayList();
    ArrayList<Double> PExistingAmountOfPreviousYearSoldProduct = new ArrayList();
    ArrayList<Double> PPurchasingPriceOfPreviousYearSoldProduct = new ArrayList();
    ArrayList<String> PunitOfPreviousYearSoldProduct = new ArrayList();

    ArrayList<Double> newAmount = new ArrayList();
    ArrayList<Double> newPurchasepricet = new ArrayList();
    ArrayList<String> unit = new ArrayList();
    ArrayList<String> newName = new ArrayList();

    ArrayList<Double> newAmount2 = new ArrayList();
    ArrayList<Double> newPurchasepricet2 = new ArrayList();
    ArrayList<String> unit2 = new ArrayList();
    ArrayList<String> newName2 = new ArrayList();

    public MonthlyBudgetingInfo() {
        conn = Connection.DBconnector();
    }

    public void getValueOfMonth_PreviousYear() {
        PName.clear();
        Amount.clear();
        TotalPrice.clear();
        //  unit.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String Month = "";
        String val = date.toString();
        Month = val.substring(4, 8).trim();
        String Date = dateFormat.format(date);
        int previousYear = Integer.parseInt(Date.substring(0, 4)) - 1;

        String startDate = previousYear + "-01-01".trim();
        String endDate = previousYear + "-12-31".trim();

        try {
            String sql = "SELECT PName,SUM(Amount),Sum(TotalPrice) FROM Customer,Product where  Customer.PID=Product.PID and date between '" + startDate + "' and '" + endDate + "' and Month='" + Month + "' group by PName";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Amount.add(rs.getDouble("SUM(Amount)"));
                TotalPrice.add(rs.getDouble("Sum(TotalPrice)"));
                // unit.add(rs.getString("unit"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
    }

    public void getValOfMonth_PreviousThree() {
        PNameOfThreeMonth.clear();
        AmountOfThreeMonth.clear();
        TotalPriceOfThreeMonth.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        //Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -91);
        dt = c.getTime();

        String r90D = dateFormat.format(dt).trim();
        try {
            String sql = "Select PName,SUM(Amount)/3,Sum(TotalPrice)/3 FROM Customer,Product where  Customer.PID=Product.PID and date>='" + r90D + "'  group by PName order by Sum(TotalPrice) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PNameOfThreeMonth.add(rs.getString("PName"));
                AmountOfThreeMonth.add(rs.getDouble("SUM(Amount)/3"));
                TotalPriceOfThreeMonth.add(rs.getDouble("Sum(TotalPrice)/3"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }

    }

    public void getExistingAmountConsideringPreviousYear() {
        PName.clear();
        Amount.clear();
        TotalPrice.clear();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String Month = "";
        String val = date.toString();
        Month = val.substring(4, 8).trim();
        String Date = dateFormat.format(date);
        int previousYear = Integer.parseInt(Date.substring(0, 4)) - 1;

        String startDate = previousYear + "-01-01".trim();
        String endDate = previousYear + "-12-31".trim();

        getExistingAmount();
        getValueOfMonth_PreviousYear();
        if (PNameOfThreeMonth.contains(PName)) {
            PNameOfPreviousYearSoldProduct.remove(PName);
         // PNameOfAmount.remove(Amount);
            //PNameOfunit.remove(unit);
        }
        PNameOfPreviousYearSoldProduct.addAll(PName);

        for (int i = 0; i < PNameOfPreviousYearSoldProduct.size(); i++) {
            try {
                String sql = "SELECT PName,SUM(Amount),Sum(TotalPrice),unit,PAmount,purchasePricePerUnit FROM Customer,Product where  Customer.PID=Product.PID and date between '" + startDate + "' and '" + endDate + "' and Month='" + Month + "' and PName='" + PNameOfPreviousYearSoldProduct.get(i) + "' group by PName";

                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {

                    PSoldAmountOfPreviousYearSoldProduct.add(rs.getDouble("SUM(Amount)"));
                    PunitOfPreviousYearSoldProduct.add(rs.getString("unit"));
                    PExistingAmountOfPreviousYearSoldProduct.add(rs.getDouble("PAmount"));
                    PPurchasingPriceOfPreviousYearSoldProduct.add(rs.getDouble("purchasePricePerUnit"));
                }

            } catch (Exception e) {
JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }
            }
        }
    }

    public void getExistingAmount() {
        ExistingAmount.clear();
        PNameOfExistingProduct.clear();
        ProductPurchasePrice.clear();
        unitExistingProduct.clear();
        getValOfMonth_PreviousThree();

        for (int i = 0; i < PNameOfThreeMonth.size(); i++) {
            try {
                String sql = "Select PName,PAmount,PurchasePricePerUnit,unit FROM Product where  PName='" + PNameOfThreeMonth.get(i) + "'  group by PName order by PAmount DESC";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    PNameOfExistingProduct.add(rs.getString("PName"));
                    ExistingAmount.add(rs.getDouble("PAmount"));
                    ProductPurchasePrice.add(rs.getDouble("PurchasePricePerUnit"));
                    unitExistingProduct.add(rs.getString("unit"));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());

            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception ex) {

                }
            }
        }
    }

//    public void getAllValue() {
//        MonthlyBudgetingInfo m = new MonthlyBudgetingInfo();
//        m.getExistingAmount();
//        m.getValueOfMonth_PreviousYear();
//        m.getValOfMonth_PreviousThree();
//
////        m.PNameOfThreeMonth.addAll(m.PName);
//        // System.out.println(m.PNameOfThreeMonth);
//        String temp = null;
//        double Temp = 0;
//        /// System.out.print(m.PNameOfExistingProduct+" "+m.ExistingAmount+" "+m.AmountOfThreeMonth+" "+m.unit);
//        for (int j = 0; j < m.ExistingAmount.size(); j++) {
//            if (m.ExistingAmount.get(j) < m.AmountOfThreeMonth.get(j)) {
//                temp = m.PNameOfExistingProduct.get(j);
//                Temp = m.AmountOfThreeMonth.get(j) - m.ExistingAmount.get(j);
//                m.newName.add(temp);
//                m.newAmount.add(Temp);
//            }
//
//        }
//        m.getExistingAmountConsideringPreviousYear();
//    }


}
