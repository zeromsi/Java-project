/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.DAO;

import com.msi.adminview.Employee;
import com.msi.adminview.EmployeeCategory;

/**
 *
 * @author MSI
 */
public interface EmployeeDAO {
    void add(Employee employee,EmployeeCategory employCategory);
    void update(Employee employee,EmployeeCategory employCategory);
    void delete(Employee employee);
    void getImageIconAndOthers(Employee emp,EmployeeCategory empCat);
    void Report(Employee employee,EmployeeCategory employCategory);
}
