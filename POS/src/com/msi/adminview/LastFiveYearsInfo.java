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
public class LastFiveYearsInfo {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<Double> TotalPrice = new ArrayList();
    ArrayList<Double> Profit = new ArrayList();
    ArrayList<Integer> Year = new ArrayList();

    public LastFiveYearsInfo() {
        conn = Connection.DBconnector();

    }

    public void getInfo(String startDate, String endDate) {
     
        try {
            String sql = "Select Sum(TotalPrice), Sum(Profit) from Customer where date between '" + startDate + "' and '" + endDate + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                TotalPrice.add(rs.getDouble("Sum(TotalPrice)"));
                Profit.add(rs.getDouble("Sum(Profit)"));
            }

        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "ERROR:"+e.getMessage());
        }finally{
            try{
                pst.close();
                rs.close();
            }catch(Exception e){
                
            }
        }
    }

    public void getAllVaues() {
        DateChange d = new DateChange();
        int previousYear1 = Integer.parseInt(d.s.substring(0, 4)) - 1;
        int previousYear2 = previousYear1 - 1;
        int previousYear3 = previousYear2 - 1;
        int previousYear4 = previousYear3 - 1;
        int previousYear5 = previousYear4 - 1;

        Year.add(previousYear1);
        Year.add(previousYear2);
        Year.add(previousYear3);
        Year.add(previousYear4);
        Year.add(previousYear5);

      
        String startDate1 = previousYear1 + "-01-01".trim();
        String endDate1 = previousYear1 + "-12-31".trim();
        String startDate2 = previousYear2 + "-01-01".trim();
        String endDate2 = previousYear2 + "-12-31".trim();
        String startDate3 = previousYear3 + "-01-01".trim();
        String endDate3 = previousYear3 + "-12-31".trim();
        String startDate4 = previousYear4 + "-01-01".trim();
        String endDate4 = previousYear4 + "-12-31".trim();
        String startDate5 = previousYear5 + "-01-01".trim();
        String endDate5 = previousYear5 + "-12-31".trim();

        getInfo(startDate1, endDate1);
        getInfo(startDate2, endDate2);
        getInfo(startDate3, endDate3);
        getInfo(startDate4, endDate4);
        getInfo(startDate5, endDate5);
        
        double[] tp=new double[5];
double[] p=new double[5];
            

for(int i=0;i<tp.length;i++){
    TotalPrice.add(tp[i]);
    Profit.add(p[i]);
}

    }

   
}
