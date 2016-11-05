/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.msi.DAO.EmployeeCategoryDAO;
import com.msi.adminview.EmployeeCategory;
import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MSI
 */
public class EmployeeCategoryDAOImplementation implements EmployeeCategoryDAO{

    @Override
    public void add(EmployeeCategory employeeCategory) {
         java.sql.Connection conn = null;
    PreparedStatement pst = null;
        try {
            conn = Connection.DBconnector();
            String query ="insert into Employee_Category (ECName) values(?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, employeeCategory.getECName());
            pst.execute();
           
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "A Category of this name is already exists!");
        }finally {
                try {
                    if(conn!=null){
                        conn.close();
                    }if(pst!=null){
                    pst.close();
                }
                 
                } catch (Exception e) {

                }
            }
    }

    @Override
    public void update(EmployeeCategory employeeCategory,String empCatName) {
        java.sql.Connection conn = null;
    PreparedStatement pst = null;
     try {
            conn = Connection.DBconnector();
            String query ="Update Employee_Category set ECName='" +employeeCategory.getECName() + "' where ECName='" +empCatName+ "'";
            pst = conn.prepareStatement(query);
           
            pst.execute();
           
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "No option is selected!"+e.getMessage());
        }finally {
                try {
                    if(conn!=null){
                        conn.close();
                    }if(pst!=null){
                    pst.close();
                }
                 
                } catch (Exception e) {

                }
            }
    }

    @Override
    public void getEmployeeCategoryList(DefaultListModel listModel,JList Employee_Category) {
      listModel.clear();
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
         ResultSet rs = null;
       try {
           conn = Connection.DBconnector();
            String sql = "select *from Employee_Category";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Employee_Category.setModel(listModel);
            while (rs.next()) {
                listModel.addElement(rs.getString("ECName"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally {
                try {
                    if(conn!=null){
                        conn.close();
                    }if(pst!=null){
                    pst.close();
                    rs.close();
                }
                 
                } catch (Exception e) {

                }
            }
    }

    @Override
    public void fillcmb_Employee(JComboBox cmbEType) {
       java.sql.Connection conn = null;
        PreparedStatement pst = null;
         ResultSet rs = null;
       try {
             conn = Connection.DBconnector();
            String sql = "select *from Employee_Category";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("ECName");
                cmbEType.addItem(name);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally {
                try {
                    if(conn!=null){
                        conn.close();
                    }if(pst!=null){
                    pst.close();
                    rs.close();
                }
                 
                } catch (Exception e) {

                }
            }
    }

  
    @Override
    public void Update_Employee_Table_By_Category(EmployeeCategory empCat, JTable tblEmployeeInfo) {
         java.sql.Connection conn = null;
        PreparedStatement pst = null;
         ResultSet rs = null;
          conn = Connection.DBconnector();
          try {
            String sql = "SELECT ECID from Employee_Category where ECName='" + empCat.getECName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString(1);
                empCat.setECATID(Integer.parseInt(add1));
                // CatID = Integer.parseInt(add1);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT EID as ID,EName as Name,EAge as Age,ESalary as Salary,ECName as Category,EImage as Image\n"
                    + "                    from Employee_Info,Employee_Category where  Employee_Info.ECID=Employee_Category.ECID and Employee_Info.ECID='" + empCat.getECATID() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tblEmployeeInfo.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }finally {
                try {
                    if(conn!=null){
                        conn.close();
                    }if(pst!=null){
                    pst.close();
                    rs.close();
                }
                 
                } catch (Exception e) {

                }
            }
    }

    @Override
    public void delete(EmployeeCategory employeeCategory) {
         java.sql.Connection conn = null;
        PreparedStatement pst = null;
         ResultSet rs = null;
          conn = Connection.DBconnector();
          int CatID=0;
           System.out.print(employeeCategory.getECName());
           try {
            String sql = "SELECT ECID from Employee_Category where ECName='" +employeeCategory.getECName()+ "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString(1);

                CatID = Integer.parseInt(add1);
                System.out.print(CatID);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "DELETE from Employee_Info where ECID='" + CatID + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (CatID != 0) {
            try {
                String sql = "DELETE from Employee_Category where ECID='" + CatID + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Category was deleted!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No Category was selected!");
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No Category was selected!");
        }
    }
    
    
    
}
