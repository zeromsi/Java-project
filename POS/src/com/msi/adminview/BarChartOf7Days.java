/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.msi.connection.Connection;
import java.awt.Color;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author MSI
 */
public class BarChartOf7Days extends javax.swing.JFrame {

   
          java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
      DateChange d=new DateChange();
       String s =d.s;
        String r7D;
    double h1;
    double h2;
    double h3;
    double h4;
    double h6;
    double h5;
    double h7;
    double h8;
    double h9;
    double h10;
    double h11;
    double h12;
    double h13;
    double h14;
    double h15;
    double h17;
    double h16;
    double h18;
    double h19;
    double h20;
    double h21;
    double h22;
    double h23;
    double h24;
    
    ArrayList<Double> totalPrice=new ArrayList();
    ArrayList<String> allDate=new ArrayList();
   
    public BarChartOf7Days() {
        initComponents();
         conn = Connection.DBconnector();
        getBarChart1();
       
    }
    
    
    public void Chart(){
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt=new Date();
        Date d=new Date();
Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -7);
        dt = c.getTime();

        r7D = dateFormat.format(dt).trim();
        String s=dateFormat.format(d).trim();
       try{
           
            String sql = "SELECT  SUM(TotalPrice),Date \n" +
"FROM Customer \n" +
"WHERE Date >='"+r7D+"' group by Date ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
               // ChartData c=new ChartData();
              double sum=rs.getDouble(1);
              totalPrice.add(sum);
              String Alldate=rs.getString(2);
              allDate.add(Alldate);
           //System.out.print(hh);
            }
       }catch(Exception e){
           
       }finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
   }
       
     
 }
    
    
    
    public void getBarChart1() {
        
      Chart();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        for(int i=0;i<allDate.size();i++){
            dataset.setValue(totalPrice.get(i),"Sale",allDate.get(i));
        }        
  
    JFreeChart chart = ChartFactory.createBarChart3D("Bar Chart", "Date", "Money", dataset, PlotOrientation.VERTICAL, true, false, false);
        chart.setBackgroundPaint(Color.yellow);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.white);
 
        //p.setForegroundAlpha(TOP_ALIGNMENT);
        
        ChartPanel fr = new ChartPanel(chart);
        fr.setVisible(true);
        fr.setSize(1000,480);
       
        pnl7DaysBarChart.removeAll();
        pnl7DaysBarChart.add(fr);
        pnl7DaysBarChart.validate();
    
        try{
           final ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
           final File f=new File("barchart7daysfull.png");
           ChartUtilities.saveChartAsPNG(f, chart, 1000,280,info);
       }catch(Exception e){
           
       }
 

    }
         
         

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl7DaysBarChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnl7DaysBarChartLayout = new javax.swing.GroupLayout(pnl7DaysBarChart);
        pnl7DaysBarChart.setLayout(pnl7DaysBarChartLayout);
        pnl7DaysBarChartLayout.setHorizontalGroup(
            pnl7DaysBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1046, Short.MAX_VALUE)
        );
        pnl7DaysBarChartLayout.setVerticalGroup(
            pnl7DaysBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl7DaysBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl7DaysBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnl7DaysBarChart;
    // End of variables declaration//GEN-END:variables
}
