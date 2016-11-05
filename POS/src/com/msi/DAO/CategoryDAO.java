/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.DAO;

import com.msi.adminview.Category;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;

/**
 *
 * @author MSI
 */
public interface CategoryDAO {
    void add(Category category);
    void update(Category category);
    void getProductCategoryList(DefaultListModel listModel, DefaultListModel listModel_1,JList lstProductCategory,JList lstProductCategory2);
    void  Update_Table(JTable tbl);
    void getProductCategoryID(JLabel lblId);
    void fillProductCategorycmb(JComboBox cmbPCat);
     void getCategory(Category category);
}
