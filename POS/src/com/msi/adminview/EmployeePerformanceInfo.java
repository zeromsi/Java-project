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
public class EmployeePerformanceInfo {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int count = 0;
    double TotalSale = 0, AvgSale = 0;
    ArrayList<String> emp = new ArrayList<String>();
    ArrayList<String> empName = new ArrayList<String>();
    ArrayList<Integer> AttendenceInPercentage = new ArrayList<Integer>();
    ArrayList<Integer> AttendencePerEmployee = new ArrayList<Integer>();
    ArrayList<Double> TotalSalePerEmployee = new ArrayList<Double>();
    ArrayList<Double> SaleMarksPerEmployee = new ArrayList<Double>();
     ArrayList<Double> TotalMarksPerEmployee = new ArrayList<Double>();

    public EmployeePerformanceInfo() {
        conn = Connection.DBconnector();
        
    }

    public void info(String startDate,String endDate) {
        try {
            String sql = "Select date as count_date\n"
                    + "from  EmployeeAttendence where date between '"+startDate+"' and '"+endDate+"'  group by date";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                count = count + 1;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            }
        }

    }

    public void getAllEmployee(String date) {
        emp.clear();
        empName.clear();
        try {
            String sql = "Select EID,EName from Employee_Info  where Joindate<'"+date+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                emp.add(rs.getString("EID"));
                empName.add(rs.getString("EName"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            }
        }
    }

    public void getAllEmployeesAttendence(String startDate,String endDate) {
        getAllEmployee(endDate);
        // Attendence.clear();
        int c = 0;
        for (int i = 0; i < emp.size(); i++) {
            try {
                String sql = "Select date as count_date \n"
                        + "from  EmployeeAttendence where EID='" + emp.get(i) + "' and date between '"+startDate+"' and '"+endDate+"' and status='P' group by date";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    c = c + 1;

                }
                AttendencePerEmployee.add(c);
                c = 0;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
                }
            }
        }
    }

    public void getAttendencePercentage(String startDate,String endDate) {
        info(startDate,endDate);
        getAllEmployeesAttendence(startDate,endDate);
        //int percentage=0;
        for (int i = 0; i < AttendencePerEmployee.size(); i++) {
            // percentage=(AttendencePerEmployee.get(i)*100)/count;
            AttendenceInPercentage.add((AttendencePerEmployee.get(i) * 100) / count);
           // System.out.print(AttendencePerEmployee.get(i) );
        }

    }

    public void getTotalSaleByPerEmployee(String StartDate,String endDate) {
        getAllEmployee(endDate);
        TotalSalePerEmployee.clear();
        for (int i = 0; i < emp.size(); i++) {
            try {
                String sql = "select Sum(TotalPrice) from Customer where EID='" + emp.get(i) + "' and date>'"+StartDate+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    TotalSalePerEmployee.add(rs.getDouble("Sum(TotalPrice)"));

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
            } finally {
                try {

                    pst.close();
                    rs.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
                }
            }
        }
    }

    public void getTotalSale(String startDate,String endDate) {
        info(startDate,endDate);
        try {
            String sql = "select Sum(TotalPrice) from Customer where date>'"+startDate+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TotalSale = rs.getDouble("Sum(TotalPrice)");
                AvgSale = TotalSale / count;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
        } finally {
            try {

                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
            }
        }
    }

    public void getSaleMarks(String startDate,String endDate) {
        getTotalSaleByPerEmployee(startDate,endDate);
        getTotalSale(startDate,endDate);
        double temp = 0;
        for (int i = 0; i < TotalSalePerEmployee.size(); i++) {
            if (TotalSalePerEmployee.get(i) > (AvgSale + AvgSale / 2)) {
                SaleMarksPerEmployee.add(90.0);
            }else if (TotalSalePerEmployee.get(i) > (AvgSale + AvgSale / 3) && TotalSalePerEmployee.get(i) < (AvgSale + AvgSale / 2)) {
                SaleMarksPerEmployee.add(80.0);
            } 
            else if (TotalSalePerEmployee.get(i) > AvgSale && TotalSalePerEmployee.get(i) < (AvgSale + AvgSale / 3)) {
                SaleMarksPerEmployee.add(70.0);
            } else if (TotalSalePerEmployee.get(i) == AvgSale) {
                SaleMarksPerEmployee.add(50.0);
            }else if (TotalSalePerEmployee.get(i)<AvgSale && TotalSalePerEmployee.get(i)>=AvgSale/2) {
                SaleMarksPerEmployee.add(40.0);
            }else if (TotalSalePerEmployee.get(i)<=AvgSale/2 && TotalSalePerEmployee.get(i)>AvgSale/3) {
                SaleMarksPerEmployee.add(20.0);
            }else if (TotalSalePerEmployee.get(i)<=AvgSale/3 && TotalSalePerEmployee.get(i)>AvgSale/4) {
                SaleMarksPerEmployee.add(10.0);
            }else if (TotalSalePerEmployee.get(i)==0) {
                SaleMarksPerEmployee.add(0.0);
            }
        }
    }
    
    public void mergeMarks(String startDate,String endDate) {
        getSaleMarks(startDate,endDate);
       // getTotalSaleByPerEmployee();
        getAttendencePercentage(startDate,endDate);
        
        for(int i=0;i<TotalSalePerEmployee.size();i++){
            TotalMarksPerEmployee.add(((AttendenceInPercentage.get(i)+SaleMarksPerEmployee.get(i))*100)/200);
        }
    }

//    public static void main(String[] args) {
//        EmployeePerformanceInfo p=new EmployeePerformanceInfo();
////        p.getAttendencePercentage();
////        p.getSaleMarks();
////        System.out.print(p.AttendenceInPercentage+" "+p.SaleMarksPerEmployee);
//        p.getAllEmployee();
//        p.mergeMarks(); 
//       System.out.print(p.TotalMarksPerEmployee+" "+p.empName);
//        
//    }

}
