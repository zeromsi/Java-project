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
public class SpecificSupplierAndProducts {

  
    ArrayList<String>PName=new ArrayList();
    ArrayList<String>CatID=new ArrayList();
    ArrayList<Double>purchasePricePerUnit=new ArrayList(); 
    
   
    
    public void getValue(Supplier supplier){
        java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
        try{
              conn = Connection.DBconnector();
            String sql="Select PName,CatName,purchasePricePerUnit from product,ProductCategory where product.CatID=ProductCategory.CatID and SID='"+supplier.getId()+"' group by PName order by purchasePricePerUnit DESC";
             pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
             
               PName.add(rs.getString("PName"));
               CatID.add(rs.getString("CatName"));
               purchasePricePerUnit.add(rs.getDouble("purchasePricePerUnit"));
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR.");
        }finally{
            try{
                  pst.close();
                rs.close();
            }catch(Exception ex){
              
            }
        }
        
    }
}
