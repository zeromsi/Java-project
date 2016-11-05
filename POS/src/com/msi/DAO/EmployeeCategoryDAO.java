/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.DAO;

import com.msi.adminview.EmployeeCategory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;

/**
 *
 * @author MSI
 */
public interface EmployeeCategoryDAO {
    void add(EmployeeCategory employeeCategory);
    void update(EmployeeCategory employeeCategory,String empCatName);
    void delete(EmployeeCategory employeeCategory);
    void getEmployeeCategoryList(DefaultListModel listModel,JList Employee_Category);
    void fillcmb_Employee(JComboBox cmbEType);
    void Update_Employee_Table_By_Category(EmployeeCategory empCat,JTable tblEmployeeInfo);
    
}
