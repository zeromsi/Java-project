/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.msi.DAO.CategoryDAO;
import com.msi.adminview.Category;
import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MSI
 */
public class CategoryDAOImplementation implements CategoryDAO {

    @Override
    public void add(Category category) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Connection.DBconnector();
            String query = "insert into ProductCategory(CatName) values(?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, category.getCatName());
            pst.execute();
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
    public void update(Category category) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Connection.DBconnector();
            String query = "update ProductCategory set CatName='" + category.getCatName() + "' where CatID='" + category.getCatID() + "'";
            pst = conn.prepareStatement(query);
            pst.execute();
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
    public void getProductCategoryList(DefaultListModel listModel, DefaultListModel listModel_1, JList lstProductCategory, JList lstProductCategory2) {

        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        listModel.clear();
        listModel_1.clear();
        try {
            conn = Connection.DBconnector();
            String sql = "select *from ProductCategory";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                listModel.addElement(rs.getString("CatName"));
                listModel_1.addElement(rs.getString("CatName"));
            }

            lstProductCategory.setModel(listModel);
            lstProductCategory2.setModel(listModel_1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
    public void Update_Table(JTable tbl) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = Connection.DBconnector();
            String sql = "SELECT CatID as ID,CatName as Category from ProductCategory";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
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
    public void getProductCategoryID(JLabel lblId) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = Connection.DBconnector();
            String sql = "select max(CatID) from ProductCategory";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString(1);

                int a = Integer.parseInt(add1);

                lblId.setText(a + 1 + "");

                // lblId.setText(a + "");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
    public void fillProductCategorycmb(JComboBox cmbPCat) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        cmbPCat.removeAllItems();
        cmbPCat.addItem("");
        try {
            conn = Connection.DBconnector();
            String sql = "select *from ProductCategory ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("CatName");
                //  cmbProCat.addItem(name);
                cmbPCat.addItem(name);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
    public void getCategory(Category category){
        category.listModelCat.clear();
            java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     conn = Connection.DBconnector();
            try {
            String sql = "Select CatName from ProductCategory";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                // CatName.add(rs.getString("CatName"));
                category.listModelCat.addElement(rs.getString("CatName"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
        }  finally {
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
