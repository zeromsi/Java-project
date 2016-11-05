/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.userview;

import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MSI
 */

public class ProductReport {
java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
      String BarcodeText = "Barcode UPCA";
    public ProductReport() {
          conn = Connection.DBconnector();
        
    }
    
    
    
}
