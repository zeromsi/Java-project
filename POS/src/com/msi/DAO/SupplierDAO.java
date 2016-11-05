/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.DAO;

import com.msi.adminview.Supplier;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author MSI
 */
public interface SupplierDAO {

    void add(Supplier Supplier);

    void update(Supplier Supplier);

    void getSupplierTable(JTable tbl);

    void fillcmb_Supplier(JComboBox cmb);

}
