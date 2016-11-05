/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.DAO;

import com.msi.adminview.Category;
import com.msi.adminview.Product;
import com.msi.adminview.Supplier;

import javax.swing.JList;
import javax.swing.JTable;

/**
 *
 * @author MSI
 */
public interface ProductDAO {

    void add(Product pro, Category cat, Supplier sup);

    void update(Product pro, Category cat, Supplier sup);

    void Update_Table_By_Category(String productCat, JTable tblProductDetails);

    void getValuesofIndividualProduct(Product pro, Category cat);

    void setProductInfoByClickingTable(String row, Product pro, Category cat, Supplier sup);

    void bookmarkProduct(Product pro, String name);

    void fillUnBookmarkedTable(Category cat, JTable tblUnBookmark, String name);

    void UnBookmarkedProduct(Product pro, String name);

    void fillBookmarkedTable(Category cat, JTable tblBookmark, String name);

}
