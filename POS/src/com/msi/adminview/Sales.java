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
public class Sales {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    double h1;
    double h24;
    double h2;
    double h3;
    double h4;
    double h6;
    double h5;
    double h7;
    double h8;
    double h9;
    double h10;
    double h11;
    double h12;
    double h13;
    double h14;
    double h15;
    double h17;
    double h16;
    double h18;
    double h19;
    double h20;
    double h21;
    double h23;
    double h22;
    double jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;

    ArrayList<String> date = new ArrayList();
    ArrayList<Double> TotalPrice = new ArrayList();

    ArrayList<String> PName = new ArrayList();
    ArrayList<Double> SoldAmount = new ArrayList();
    ArrayList<String> Unit = new ArrayList();
    ArrayList<Integer> Count = new ArrayList();
    ArrayList<Double> Profit = new ArrayList();

    public Sales() {
        conn = Connection.DBconnector();

    }

    public void getValOfTotalSaleByDateOfMonth(String StartTime,String endTime) {
        date.clear();
        TotalPrice.clear();
        try {
            String sql = "Select Date,SUM(TotalPrice) from Customer where date between '"+StartTime+"' and '"+endTime+"' group by date order by Sum(TotalPrice) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                date.add(rs.getString("Date"));
                TotalPrice.add(rs.getDouble("Sum(TotalPrice)"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }
    }

    public void getValOfSoldAmountOfMonth(String StartTime,String endTime) {
        PName.clear();
        SoldAmount.clear();
        Unit.clear();
        try {
            String sql = "Select PName,SUM(Amount),Unit from Customer,Product where Product.PID=Customer.PID and  date between '"+StartTime+"' and '"+endTime+"' group by PName order by Sum(Amount) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                SoldAmount.add(rs.getDouble("Sum(Amount)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            double[] SA = new double[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                SoldAmount.add(SA[i]);
                Unit.add(unit[i]);
            }

        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }
    }

    public void getValOfProductByTotalSoldPriceOfMonth(String StartTime,String endTime)  {
        PName.clear();
        TotalPrice.clear();
        Unit.clear();

        try {
            String sql = "Select PName,SUM(TotalPrice),Unit from Customer,Product where Product.PID=Customer.PID and date between '"+StartTime+"' and '"+endTime+"' group by PName order by Sum(TotalPrice) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                TotalPrice.add(rs.getDouble("Sum(TotalPrice)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            double[] SA = new double[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                TotalPrice.add(SA[i]);
                Unit.add(unit[i]);
            }

        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }
    }

    public void getValOfProductsBySoldTimesOfMonth(String StartTime,String endTime){
        PName.clear();
        Count.clear();
        Unit.clear();

        try {
            String sql = "Select PName,Count(Customer.PID),Unit from Customer,Product where Product.PID=Customer.PID and date between '"+StartTime+"' and '"+endTime+"' group by Customer.PID order by Count(Customer.PID) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Count.add(rs.getInt("Count(Customer.PID)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            Integer[] SA = new Integer[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Count.add(SA[i]);
                Unit.add(unit[i]);
            }

        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }

    }

    public void getValOfProductByProfitOfMonth(String StartTime,String endTime) {
        PName.clear();
        Profit.clear();
        try {
            String sql = "Select PName,SUM(Customer.profit) from Customer,Product where Product.PID=Customer.PID and Customer.Profit>0 and  date between '"+StartTime+"' and '"+endTime+"' group by Customer.PID order by (Customer.profit) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Profit.add(rs.getDouble("SUM(Customer.profit)"));

            }

            String[] name = new String[15];
            double[] SA = new double[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Profit.add(SA[i]);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }

    }

     public void getValOfProductByLossOfMonth(String time) {
        PName.clear();
        Profit.clear();
        try {
            String sql = "Select PName,SUM(Customer.profit) from Customer,Product where Product.PID=Customer.PID and Customer.Profit>0 and  Month='" + time + "' group by Customer.PID order by (Customer.profit) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Profit.add(rs.getDouble("SUM(Customer.profit)"));

            }

            String[] name = new String[15];
            double[] SA = new double[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Profit.add(SA[i]);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {

            }
        }

    }
    
    //Month
    public void getValOfTotalSaleByHourOfDay(String day) {

        h1 = HourlyValue(h1, "01:00:00", "01:59:59", day);
        h2 = HourlyValue(h2, "02:00:00", "02:59:59", day);
        h3 = HourlyValue(h3, "03:00:00", "03:59:59", day);
        h4 = HourlyValue(h4, "04:00:00", "04:59:59", day);
        h5 = HourlyValue(h5, "05:00:00", "05:59:59", day);
        h6 = HourlyValue(h6, "06:00:00", "06:59:59", day);
        h7 = HourlyValue(h7, "07:00:00", "07:59:59", day);
        h8 = HourlyValue(h8, "08:00:00", "08:59:59", day);
        h9 = HourlyValue(h9, "09:00:00", "09:59:59", day);
        h10 = HourlyValue(h10, "10:00:00", "10:59:59", day);

        h11 = HourlyValue(h11, "11:00:00", "11:59:59", day);
        h12 = HourlyValue(h12, "12:00:00", "12:59:59", day);
        h13 = HourlyValue(h13, "13:00:00", "13:59:59", day);
        h14 = HourlyValue(h14, "14:00:00", "14:59:59", day);
        h15 = HourlyValue(h15, "15:00:00", "15:59:59", day);
        h16 = HourlyValue(h16, "16:00:00", "16:59:59", day);
        h17 = HourlyValue(h17, "17:00:00", "17:59:59", day);
        h18 = HourlyValue(h18, "18:00:00", "18:59:59", day);
        h19 = HourlyValue(h19, "19:00:00", "19:59:59", day);

        h20 = HourlyValue(h20, "20:00:00", "20:59:59", day);
        h21 = HourlyValue(h21, "21:00:00", "21:59:59", day);
        h22 = HourlyValue(h22, "22:00:00", "22:59:59", day);
        h23 = HourlyValue(h23, "23:00:00", "23:59:59", day);
        h24 = HourlyValue(h24, "00:00:00", "00:59:59", day);
    }

    public double HourlyValue(double h, String sTime, String eTime, String day) {

        try {

            String sql = "Select SUM(TotalPrice) from Customer where Day='" + day + "' and Time BETWEEN '" + sTime + "' AND '" + eTime + "'";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                // ChartData c=new ChartData();
                h = rs.getDouble(1);
                return h;
                //System.out.print(hh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
        return h;
    }

    public void getValOfSoldAmountOfDay(String day) {
        PName.clear();
        SoldAmount.clear();
        Unit.clear();
        try {
            String sql = "Select PName,SUM(Amount),Unit from Customer,Product where Product.PID=Customer.PID and  Day='" + day + "' group by PName order by Sum(Amount) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                SoldAmount.add(rs.getDouble("Sum(Amount)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            double[] SA = new double[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                SoldAmount.add(SA[i]);
                Unit.add(unit[i]);
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

    public void getValOfProductByTotalSoldPriceOfDay(String day) {
        PName.clear();
        TotalPrice.clear();
        Unit.clear();

        try {
            String sql = "Select PName,SUM(TotalPrice),Unit from Customer,Product where Product.PID=Customer.PID and  Day='" + day + "' group by PName order by Sum(TotalPrice) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                TotalPrice.add(rs.getDouble("Sum(TotalPrice)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            double[] SA = new double[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                TotalPrice.add(SA[i]);
                Unit.add(unit[i]);
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

    public void getValOfProductsBySoldTimesOfDay(String day) {
        PName.clear();
        Count.clear();
        Unit.clear();

        try {
            String sql = "Select PName,Count(Customer.PID),Unit from Customer,Product where Product.PID=Customer.PID and  day='" + day + "' group by Customer.PID order by Count(Customer.PID) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Count.add(rs.getInt("Count(Customer.PID)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            Integer[] SA = new Integer[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Count.add(SA[i]);
                Unit.add(unit[i]);
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

    public void getValOfProductByProfitOfDay(String day) {
        PName.clear();
        Profit.clear();
        try {
            String sql = "Select PName,SUM(Customer.profit) from Customer,Product where Product.PID=Customer.PID and Customer.Profit>0 and day='" + day + "' group by Customer.PID order by SUM(Customer.profit) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Profit.add(rs.getDouble("SUM(Customer.profit)"));

            }

            String[] name = new String[15];
            double[] SA = new double[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Profit.add(SA[i]);

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
    
    
    

    //Year
    public double getValOfTotalSaleByMonthOfYear(double h, String Month, String year, String endYear) {

        try {
            String sql = "Select SUM(TotalPrice) from Customer where Month='" + Month + "' and Date between '" + year + "' and '" + endYear + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                h = rs.getDouble("Sum(TotalPrice)");
                return h;
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
        return h;
    }

    public void getValuesOfTotalSaleByMonthOfYear(String year, String endYear) {
        jan = getValOfTotalSaleByMonthOfYear(jan, "Jan", year, endYear);
        feb = getValOfTotalSaleByMonthOfYear(feb, "Feb", year, endYear);
        mar = getValOfTotalSaleByMonthOfYear(mar, "Mar", year, endYear);
        apr = getValOfTotalSaleByMonthOfYear(apr, "Apr", year, endYear);
        may = getValOfTotalSaleByMonthOfYear(may, "May", year, endYear);
        jun = getValOfTotalSaleByMonthOfYear(jun, "Jun", year, endYear);
        jul = getValOfTotalSaleByMonthOfYear(jul, "Jul", year, endYear);
        aug = getValOfTotalSaleByMonthOfYear(aug, "Aug", year, endYear);
        sep = getValOfTotalSaleByMonthOfYear(sep, "Sep", year, endYear);
        oct = getValOfTotalSaleByMonthOfYear(oct, "Oct", year, endYear);
        nov = getValOfTotalSaleByMonthOfYear(nov, "Nov", year, endYear);
        dec = getValOfTotalSaleByMonthOfYear(dec, "Dec", year, endYear);
    }

    public void getValOfSoldAmountOfYear(String year, String endYear) {
        PName.clear();
        SoldAmount.clear();
        Unit.clear();
        try {
            String sql = "Select PName,SUM(Amount),Unit from Customer,Product where Product.PID=Customer.PID and  Date between '" + year + "' and '" + endYear + "' group by PName order by Sum(Amount) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                SoldAmount.add(rs.getDouble("Sum(Amount)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            double[] SA = new double[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                SoldAmount.add(SA[i]);
                Unit.add(unit[i]);
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

    public void getValOfProductByTotalSoldPriceOfYear(String year, String endYear) {
        PName.clear();
        TotalPrice.clear();
        Unit.clear();

        try {
            String sql = "Select PName,SUM(TotalPrice),Unit from Customer,Product where Product.PID=Customer.PID and  Date between '" + year + "' and '" + endYear + "' group by PName order by Sum(TotalPrice) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                TotalPrice.add(rs.getDouble("Sum(TotalPrice)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            double[] SA = new double[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                TotalPrice.add(SA[i]);
                Unit.add(unit[i]);
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

    public void getValOfProductsBySoldTimesOfYear(String year, String endYear) {
        PName.clear();
        Count.clear();
        Unit.clear();

        try {
            String sql = "Select PName,Count(Customer.PID),Unit from Customer,Product where Product.PID=Customer.PID and  Date between '" + year + "' and '" + endYear + "' group by Customer.PID order by Count(Customer.PID) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Count.add(rs.getInt("Count(Customer.PID)"));
                Unit.add(rs.getString("Unit"));
            }
            String[] name = new String[15];
            Integer[] SA = new Integer[15];
            String[] unit = new String[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Count.add(SA[i]);
                Unit.add(unit[i]);
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

    public void getValOfProductByProfitOfYear(String year, String endYear) {
        PName.clear();
        Profit.clear();
        try {
            String sql = "Select PName,SUM(Customer.profit) from Customer,Product where Product.PID=Customer.PID and Customer.Profit>0  and "
                    + "Date between '" + year + "' and '" + endYear + "' group by Customer.PID order by SUM(Customer.profit) DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
                Profit.add(rs.getDouble("SUM(Customer.profit)"));

            }

            String[] name = new String[15];
            double[] SA = new double[15];

            for (int i = 0; i < name.length; i++) {
                PName.add(name[i]);
                Profit.add(SA[i]);

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
