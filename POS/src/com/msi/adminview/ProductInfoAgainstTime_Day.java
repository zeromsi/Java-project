/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.msi.connection.Connection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author MSI
 */
public class ProductInfoAgainstTime_Day {
java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String ProName,Unit;
    double SoldAmount,TotalSaleMoney,profit,AllSoldAmount,TotalSaleMoneyOfAllProduct,AllProductProfit;
    public ProductInfoAgainstTime_Day() {
         conn = Connection.DBconnector();
    }
      public void getProductDailInfo(String Product,String Day){
        try{
             String sql="SELECT PName,SUM(Amount),SUM(TotalPrice),SUM(Customer.Profit),Unit  from Customer,Product  where Product.PID=Customer.PID and  day='"+Day+"'  and PName='"+Product+"'";
        
          pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                ProName=rs.getString("PName");
                SoldAmount=rs.getDouble("SUM(Amount)");
                //SoldTimes=rs.getInt("Count(Customer.PID)");
                TotalSaleMoney=rs.getDouble("SUM(TotalPrice)");
                profit=rs.getDouble("SUM(Customer.Profit)");
                Unit=rs.getString("Unit");
            
             
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Sorry! Something was worng!");
        }finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
   }
    }
    
    public void getAllProductDailyInfo(String day){
        try{
             String sql="SELECT SUM(Amount),SUM(TotalPrice),SUM(Customer.Profit) from Customer,Product  where Product.PID=Customer.PID and  day='"+day+"'";
        
          pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                
                AllSoldAmount=rs.getDouble("SUM(Amount)");
                //SoldTimes=rs.getInt("Count(Customer.PID)");
                TotalSaleMoneyOfAllProduct=rs.getDouble("SUM(TotalPrice)");
                AllProductProfit=rs.getDouble("SUM(Customer.Profit)");
                
            
             
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Sorry! Something was worng!");
        }finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
   }
    }
    
    
     public void getPieChart(String Product,String day,String PicName) {
       getProductDailInfo(Product,day);
       getAllProductDailyInfo(day);
       
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        
         
        
        dataset.setValue("All Products Profit",AllProductProfit);
        dataset.setValue(ProName+"s Profit",profit);
        
  
        
       JFreeChart chart3 = ChartFactory.createPieChart("Pie Chart", dataset, true, true, true);
       ///JFreeChart chart3 = ChartFactory.createRingChart("Ring Chart", dataset, true, true, true);

        PiePlot p = (PiePlot) chart3.getPlot();
        //p.setForegroundAlpha(TOP_ALIGNMENT);
        
        ChartPanel fr = new ChartPanel(chart3);
        fr.setVisible(true);
        fr.setSize(800,680);
 try{
           final ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
           final File f=new File(PicName);
           ChartUtilities.saveChartAsPNG(f, chart3, 800,680,info);
       }catch(Exception e){
           
       }

    }
    
}
