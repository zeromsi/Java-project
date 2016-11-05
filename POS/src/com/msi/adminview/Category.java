/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import javax.swing.DefaultListModel;

/**
 *
 * @author MSI
 */
public class Category {
    private String CatName;
    private int CatID;
 public DefaultListModel listModelCat = new DefaultListModel();
    public int getCatID() {
        return CatID;
    }

    public void setCatID(int CatID) {
        this.CatID = CatID;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String CatName) {
        this.CatName = CatName;
    }
}
