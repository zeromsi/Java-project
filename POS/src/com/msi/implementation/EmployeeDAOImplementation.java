/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.msi.DAO.EmployeeDAO;
import com.msi.adminview.Employee;
import com.msi.adminview.EmployeeCategory;
import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class EmployeeDAOImplementation implements EmployeeDAO {

    @Override
    public void add(Employee employee, EmployeeCategory employeeCategory) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();
        try {
            String sql = "select ECID from Employee_Category where ECName='" + employeeCategory.getECName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                employeeCategory.setECATID(rs.getInt(1));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        try {

            String query = "insert into Employee_Info (EID,EName,EAge,ESalary,ECID,JoinDate,EImage) values(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, employee.getEID());

            pst.setString(2, employee.getEName());
            pst.setInt(3, employee.getEAge());
            pst.setDouble(4, employee.getESalary());
            pst.setInt(5, employeeCategory.getECATID());
            pst.setString(6, employee.getJoinDate());

            pst.setBytes(7, employee.getEImage());
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "This ID already exists!");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public void update(Employee employee, EmployeeCategory employeeCategory) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();

        try {
            String sql = "select ECID from Employee_Category where ECName='" + employeeCategory.getECName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                employeeCategory.setECATID(rs.getInt(1));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            String query = "update Employee_Info set EName='" + employee.getEName() + "',EAge='" + employee.getEAge() + "',ESalary='" + employee.getESalary() + "',ECID='" + employeeCategory.getECATID() + "' where EID='" + employee.getEID() + "'";
            //   String sql = "insert into Employee_Info (EID,EName,EAge,ESalary,ECID,EImage) values(?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Updated.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public void getImageIconAndOthers(Employee emp, EmployeeCategory empCat) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = Connection.DBconnector();
            String sql = "SELECT EID,EName,EAge,ESalary,ECName,EImage,password,username,JoinDate\n"
                    + "                    from Employee_Info,Employee_Category where  Employee_Info.ECID=Employee_Category.ECID and EID='" + emp.getEID() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                // txtEID.setText(rs.getString(1));
                emp.setEID(rs.getString(1));
                //txtEName.setText(rs.getString(2));
                emp.setEName(rs.getString(2));
                // txtAge.setText(rs.getString(3));
                emp.setEAge(rs.getInt(3));
                //txtSalary.setText(rs.getString(4));
                emp.setESalary(rs.getInt(4));
                //cmbEType.setSelectedItem(rs.getString(5));
                empCat.setECName(rs.getString(5));

                emp.setEImage(rs.getBytes(6));

                emp.setUsername(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                emp.setEImage(rs.getBytes(6));

                emp.setJoinDate(rs.getString("JoinDate"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }

    }

    @Override
    public void delete(Employee employee) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
             conn = Connection.DBconnector();
            String sql = "Update Customer set EID='null' where EID='" + employee.getEID() + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        
         try {
                String sql = "delete from Employee_Info where EID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, employee.getEID());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Employee was deleted!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Sorry! The action can't be performed!");
            }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public void Report(Employee employee,EmployeeCategory employCategory) {
        java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
      try {
           conn = Connection.DBconnector();
            String sql = "Select * from Employee_Info, Employee_Category where Employee_Category.ECID=Employee_Info.ECID and EID='" + employee.getEID() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
              employee.setEName(rs.getString("EName"));
              employee.setEAge(rs.getInt("EAge"));
              employee.setESalary(rs.getInt("ESalary"));
                //Name = rs.getString("EName");
               // age = rs.getInt("EAge");
               // salary = rs.getDouble("ESalary");
              employCategory.setECName(rs.getString("ECName"));
                //CategoryName = rs.getString("ECName");

                byte[] imagedata = rs.getBytes("EImage");
                employee.setEImage(imagedata);
                employee.setFormat(new ImageIcon(imagedata));
                //format = new ImageIcon(imagedata);
                employee.setIm(Image.getInstance(imagedata));
                //im = Image.getInstance(imagedata);

                employee.getIm().scaleAbsolute(100, 80);
                employee.getIm().setAlignment(Element.ALIGN_RIGHT);
                employee.setJoinDate(rs.getString("joinDate"));
                //joinDate=rs.getString("joinDate");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }

}
