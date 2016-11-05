/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class ReportOpen {
 
    public void OpenReport(String Name){
          try {
            Runtime.getRuntime().exec(Name);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
