/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author MSI
 */
public class SetDatabaseEmptyClass {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public SetDatabaseEmptyClass() {
        conn = Connection.DBconnector();
    }

    public void setDabaseEmpty(JPasswordField txt, JCheckBox cb) {
        String pass = null;
        if (!cb.isSelected()) {
            JOptionPane.showMessageDialog(null, "You must agree with the terms and conditions!");
        } else if (txt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You need to enter your password!");
        } else {
            try {
                String sql = "select password from user";

                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    pass = rs.getString("password");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
                }
            }

            if (txt.getText().equals(pass)) {
                int p = JOptionPane.showConfirmDialog(null, "You really want to perform this action!", "Delete", JOptionPane.YES_NO_CANCEL_OPTION);
                if (p == 0) {
                    try {
                        String sql = "delete from Employee_Category,Employee_Info,ProductCategory,Product,Bookmark,Customer,EmployeeAttendence,Supplier";
                        pst = conn.prepareStatement(sql);

                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Database has been set new.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
                    } finally {
                        try {
                            pst.close();
                            rs.close();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password");
            }
        }
    }

}
