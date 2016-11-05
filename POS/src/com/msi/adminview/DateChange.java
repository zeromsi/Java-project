/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author MSI
 */
public class DateChange {
       
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();

    String s =dateFormat.format(date);
    // String s ="2016-09-03";
 
      
       
       
        
       
}
