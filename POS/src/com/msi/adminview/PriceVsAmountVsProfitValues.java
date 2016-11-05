package com.msi.adminview;

import com.msi.connection.Connection;
import java.awt.Color;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 *
 * @author MSI
 */
public class PriceVsAmountVsProfitValues {

    /**
     * SELECT PName,PPrice,SUM(Amount),SUM(TotalPrice),
     * SUM(TotalPrice)-SUM(Amount)*purchasePricePerUnit as profit from
     * Customer,Product where Product.PID=Customer.PID GROUP BY Customer.PID
     * ORDER BY PPrice DESC; Creates new form NewJFrame
     */
    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ArrayList<String> price = new ArrayList();
    ArrayList<Double> amount = new ArrayList();
    ArrayList<Double> profit = new ArrayList();
    int[] Pamount = new int[3];
    int[] PProfit = new int[3];
    int AveragePrice;
    int MaxPrice;
    int totalProfit = 0;
    String[] percentage = new String[3];

    public PriceVsAmountVsProfitValues() {

        conn = Connection.DBconnector();
        //bb();
        //getInfo(0,16);
        getAveragePrice();
        getTotalProfit();
        getInfo(0, AveragePrice, 0);
    
        getInfo(AveragePrice, AveragePrice + AveragePrice / 3, 1);
        getInfo(AveragePrice + AveragePrice / 3, MaxPrice, 2);

    }
    //SELECT SUM(PPrice)/Sum(PID) as AveragePrice from Product where PPrice>0    

    public void getAveragePrice() {
        try {
            String sql = "SELECT MAX(PPrice),Avg(PPrice) as AveragePrice from Product where PPrice>0";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                AveragePrice = (int) rs.getDouble("AveragePrice");
                MaxPrice = (int) rs.getDouble("MAX(PPrice)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void getTotalProfit() {
        try {
            String sql = "SELECT SUM(Amount),SUM(TotalPrice),\n"
                    + "   SUM(Customer.profit) from Customer,Product  \n"
                    + "    where Product.PID=Customer.PID";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                totalProfit = (int) rs.getDouble("SUM(Customer.profit)");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void getInfo(double s, double e, int j) {
        price.add(s + "  T0   " + e);
        amount.clear();
        profit.clear();
        try {
            String sql = "SELECT PName,PPrice,SUM(Amount),SUM(TotalPrice),\n"
                    + "    SUM(Customer.profit) from Customer,Product  \n"
                    + "     where Product.PID=Customer.PID and PPrice>'" + s + "'  and PPrice<'" + e + "'  GROUP BY  Customer.PID    ORDER BY PPrice DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                // price.add(s+" TO "+e);
                amount.add(rs.getDouble("SUM(Amount)"));
                profit.add(rs.getDouble("SUM(Customer.profit)"));
            }
            double TAM = 0;
            double TPR = 0;
            for (int i = 0; i < amount.size(); i++) {
                TAM = TAM + amount.get(i);
                TPR = TPR + profit.get(i);
            }
            Pamount[j] = (int) TAM;
            PProfit[j] = (int) TPR;
            double value = (PProfit[j] * 100) / totalProfit;
            percentage[j] = value + "%";
        } catch (Exception ev) {
            JOptionPane.showMessageDialog(null, ev.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ev) {
                JOptionPane.showMessageDialog(null, ev.getMessage());
            }
        }
    }
}
