/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.userview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author MSI
 */
public class DateClass {
    String day ="";
    String month ="";;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();

     String s =dateFormat.format(date);
      //String s ="2016-06-25";
 
       public void getSqlDate(){
           String DateS =date.toString();
	      char[] c=DateS.toCharArray();
	   
	   for(int i=0;i<3;i++){
		   day=day+c[i];
	   }
	   
	   for(int i=3;i<7;i++){
		   month=month+c[i];
	   }
       }
}
