/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.msi.DAO.StockDAO;
import com.msi.adminview.Stock;
import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class StockDAOImplementation implements StockDAO {

    @Override
    public void AllProductStock(Stock stock) {
       stock.PName.clear();
       stock. PAmount.clear();
      stock.  Unit.clear();
        java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
        try {
             conn = Connection.DBconnector();
            String sql = "SELECT PID,PName,PAmount,unit from product group by PName order by PAmount ASC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                stock.PName.add(rs.getString("PName"));
                stock.PAmount.add(rs.getDouble("PAmount"));
                stock.Unit.add(rs.getString("unit"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
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
    
}
