/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

/**
 *
 * @author MSI
 */
public class EmployeeCategory {
    private String ECName;
    private int ECATID;

    public int getECATID() {
        return ECATID;
    }

    public void setECATID(int ECATID) {
        this.ECATID = ECATID;
    }

    public String getECName() {
        return ECName;
    }

    public void setECName(String ECName) {
        this.ECName = ECName;
    }
    
}
