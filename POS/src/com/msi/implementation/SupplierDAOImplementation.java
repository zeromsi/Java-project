/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.msi.DAO.SupplierDAO;
import com.msi.adminview.Supplier;
import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MSI
 */
public class SupplierDAOImplementation implements SupplierDAO {

    @Override
    public void add(Supplier supplier) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Connection.DBconnector();
            String query = "insert into Supplier (SName,SAddress,SPhone,SEmail) values(?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getAddress());
            pst.setString(3, supplier.getPhone());
            pst.setString(4, supplier.getEmail());
            pst.execute();
            JOptionPane.showMessageDialog(null, "New Supplier has been added");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A Category of this name is already exists!");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public void update(Supplier Supplier) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Connection.DBconnector();
            String query ="update Supplier set SName='" + Supplier.getName() + "',SAddress='" + Supplier.getAddress()+ "',"
                    + "SPhone='" + Supplier.getPhone() + "',SEmail='" + Supplier.getEmail() + "' where SID='" + Supplier.getId() + "'";
                    pst = conn.prepareStatement(query);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Record updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A Supplier of this name is already exists!");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public void getSupplierTable(JTable tbl) {
              java.sql.Connection conn = null;
        PreparedStatement pst = null;
         ResultSet rs = null;
        try {
            conn = Connection.DBconnector();
             String sql = "SELECT SID as ID,SName as Name,SAddress as Address,SPhone as Phone, SEmail as Email from Supplier";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
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
    public void fillcmb_Supplier(JComboBox cmb) {
         cmb.removeAllItems();
        cmb.addItem("");
       java.sql.Connection conn = null;
        PreparedStatement pst = null;
         ResultSet rs = null;
        try {
            conn = Connection.DBconnector();
             String sql ="select *from Supplier";
               pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("SName");
                cmb.addItem(name);

            }

        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
