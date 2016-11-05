/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.msi.login.LoginForm;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.msi.DAO.CategoryDAO;
import com.msi.DAO.EmployeeCategoryDAO;
import com.msi.DAO.EmployeeDAO;
import com.msi.DAO.ProductDAO;
import com.msi.DAO.SupplierDAO;
import com.msi.connection.Connection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Writer;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author MSI
 */
public class index extends javax.swing.JFrame {

    /**
     * Creates new form index
     */
    DefaultListModel listModel1 = new DefaultListModel();
    DefaultListModel listModel = new DefaultListModel();
    DefaultListModel listModel_1 = new DefaultListModel();
    DefaultListModel listModel2 = new DefaultListModel();
    DefaultListModel listModel3 = new DefaultListModel();
    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    DateChange d = new DateChange();
    String s = d.s;
    String currency = "Taka";
    double h1;
    double h24;
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
    double h23;
    double h22;
    String TodaysBestProduct = null;
    double TodaysBestProductAmount = 0;
    String TodaysBestProductUnit = null;

    String TodaysBestProductByPrice = null;
    double TodaysBestProductTotalPrice = 0;
    String TodaysBestProductByPriceUnit = null;

    String TodaysBestProductBySaleNumber = null;
    int TodaysBestProductTotalSale = 0;

    String TodaysBestProductByProfit = null;
    double TodaysBestProductTotalProfit = 0;

    //  int x = 0, x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0, x13 = 0, x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, x23 = 0;
    JFreeChart chart, chart1;
    ChartPanel fr, fr2;
    ArrayList<String> TBestSaleProduct2 = new ArrayList();
    ArrayList<Double> TBestSaleProductAmount2 = new ArrayList();
    ArrayList<String> TBestSaleProductUnit2 = new ArrayList();

    ArrayList<String> TBestSaleProduct = new ArrayList();
    ArrayList<Double> TBestSaleProductAmount = new ArrayList();
    ArrayList<String> TBestSaleProductUnit = new ArrayList();

    ArrayList<String> TBestSaleProductByPrice = new ArrayList();
    ArrayList<Double> TBestSaleProductTotalPrice = new ArrayList();
    ArrayList<String> TBestSaleProductByPriceUnit = new ArrayList();

    ArrayList<String> TBestSaleProductBySaleNumber2 = new ArrayList();
    ArrayList<Integer> TBestSaleProductTotalSaleTime2 = new ArrayList();

    ArrayList<String> TBestSaleProductBySaleNumber = new ArrayList();
    ArrayList<Integer> TBestSaleProductTotalSaleTime = new ArrayList();

    ArrayList<String> TBestSaleProductByProfit2 = new ArrayList();
    ArrayList<Double> TBestSaleProductTotalProfit2 = new ArrayList();

    ArrayList<String> TBestSaleProductByProfit = new ArrayList();
    ArrayList<Double> TBestSaleProductTotalProfit = new ArrayList();

    ArrayList<String> AllEmplyeeID = new ArrayList();
    ArrayList<String> AllEmplyeeID2 = new ArrayList();
    ArrayList<String> AllEmplyeeID3 = new ArrayList();
    String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    byte[] product_image = null;
    byte[] product_image2 = null;
    ImageIcon format = null;
    ImageIcon format2 = null;
    ImageIcon formatFake = null;
    ImageIcon formatofProduct = null;
    int x = 0, y = 100;
    int count = 0;
static String ECName=null;
    public index() {
        initComponents();

        super.setLocationRelativeTo(null);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        CategoryDAO catDAO = (CategoryDAO) context.getBean("CategoryDAO");
        //  Category category = new Category();
        catDAO.getProductCategoryList(listModel1, listModel_1, lstProductCategory, lstProductCategory2);
        catDAO.Update_Table(tblCategory);
        catDAO.getProductCategoryID(lblId);
        catDAO.fillProductCategorycmb(cmbPCat);
        context.close();
        lst.setVisible(false);
        conn = Connection.DBconnector();

        pnlCategory.setVisible(false);
//        pnlAddProduct.setVisible(false);
        AllProduct.setVisible(false);

        //getID();
        // fillcmb();
        // getPID(cmbProCat, txtID);
//        getPID(cmbPCat, txtPID);
        ConfigurableApplicationContext context2 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        SupplierDAO suppDAO = (SupplierDAO) context2.getBean("SupplierDAO");

        suppDAO.getSupplierTable(tblSupplier);
        suppDAO.fillcmb_Supplier(cmbSupplier);

        context2.close();

        //getSupplierTable(tblSupplier);
        //fillcmb_Supplier(cmbSupplier);
        // System.out.print(h10);
        getBarChart(s);
        getLineChart(s);
        getPieChart(s);
        getRingChart(s);
        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeCategoryDAO empDAO = (EmployeeCategoryDAO) context3.getBean("EmployeeCategoryDAO");

        empDAO.fillcmb_Employee(cmbEType);

        empDAO.getEmployeeCategoryList(listModel, Employee_Category);

        EmployeeCategory empCat = new EmployeeCategory();
        empCat.setECName((String) Employee_Category.getSelectedValue());

        empDAO.Update_Employee_Table_By_Category(empCat, tblEmployeeInfo);

        context3.close();

        //  Update_Employee_Table_By_Category();
        pnlProduct2.setVisible(false);
        lblDate.setText(d.s);
        insertAllEmployeeWithTodaysDateIntoEmployeeAttendenceTable();
        setValuesToTheEmployeeAttendenceJTable();
        btnSignIn.setVisible(false);
        tpBPImage.setVisible(false);
        pnlProductMain.setVisible(false);

    }

    public void setValuesToTheEmployeeAttendenceJTable() {

        try {
            String sql = "SELECT EmployeeAttendence.EID as ID,EName as Name,status from Employee_Info,EmployeeAttendence where EmployeeAttendence.EID=Employee_Info.EID and date='" + d.s + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tblEmpAttendence.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
    }

    public void insertAllEmployeeWithTodaysDateIntoEmployeeAttendenceTable() {
        AllEmplyeeID.clear();
        try {
            String sql = "Select EID from Employee_Info";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                AllEmplyeeID.add(rs.getString("EID"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());

        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
            }
        }

        try {
            String sql = "Select EID from EmployeeAttendence where date='" + d.s + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                AllEmplyeeID2.add(rs.getString("EID"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());

        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
            }
        }

        if (AllEmplyeeID2.isEmpty()) {

            for (int i = 0; i < AllEmplyeeID.size(); i++) {
                try {

                    String sql = "insert into EmployeeAttendence (Date,EID,Status) values(?,?,?)";
                    pst = conn.prepareStatement(sql);

                    pst.setString(1, d.s);
                    pst.setString(2, AllEmplyeeID.get(i));
                    pst.setString(3, "A");
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
                } finally {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
                    }
                }
            }
        } else if (AllEmplyeeID2.size() > 0 && AllEmplyeeID.size() > AllEmplyeeID2.size()) {

            AllEmplyeeID.removeAll(AllEmplyeeID2);

            for (int i = 0; i < AllEmplyeeID.size(); i++) {
                try {

                    String sql = "insert into EmployeeAttendence (Date,EID,Status) values(?,?,?)";
                    pst = conn.prepareStatement(sql);

                    pst.setString(1, d.s);
                    pst.setString(2, AllEmplyeeID.get(i));
                    pst.setString(3, "A");
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
                } finally {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
                    }
                }
            }
        } else if (AllEmplyeeID2.size() > 0 && AllEmplyeeID2.size() > AllEmplyeeID.size()) {

            AllEmplyeeID2.removeAll(AllEmplyeeID);

            for (int i = 0; i < AllEmplyeeID2.size(); i++) {
                try {

                    String sql = "delete from EmployeeAttendence where date=? and EID=?";
                    pst = conn.prepareStatement(sql);

                    pst.setString(1, d.s);
                    pst.setString(2, AllEmplyeeID2.get(i));

                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
                } finally {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
                    }
                }
            }

        }
    }

//    public void getImageIconAndOthers(Employee emp, EmployeeCategory empCat) {
//        //  String psd = null, un = null;
//        
////        try {
////
////            String sql = "SELECT EID,EName,EAge,ESalary,ECName,EImage,password,username,JoinDate\n"
////                    + "                    from Employee_Info,Employee_Category where  Employee_Info.ECID=Employee_Category.ECID and EID='" + Table_click + "'";
////            pst = conn.prepareStatement(sql);
////            rs = pst.executeQuery();
////            if (rs.next()) {
////                // txtEID.setText(rs.getString(1));
////                emp.setEID(rs.getString(1));
////                //txtEName.setText(rs.getString(2));
////                emp.setEName(rs.getString(2));
////                // txtAge.setText(rs.getString(3));
////                emp.setEAge(rs.getInt(3));
////                //txtSalary.setText(rs.getString(4));
////                emp.setESalary(rs.getInt(4));
////                //cmbEType.setSelectedItem(rs.getString(5));
////                empCat.setECName(rs.getString(5));
////
////                emp.setEImage(rs.getBytes(6));
////
////                emp.setUsername(rs.getString("username"));
////                emp.setPassword(rs.getString("password"));
////
////                // byte[] imagedata = rs.getBytes(6);
////               format = new ImageIcon(emp.getEImage());
//////                java.awt.Image img = format.getImage();
//////                java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);
//////
//////                format = new ImageIcon(newimg);
//////                //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
//////                lblImage.setIcon(format);
////
//////                psd = rs.getString("password");
//////                un = rs.getString("username");
////                //  String date=rs.getString("JoinDate");
////                emp.setJoinDate(rs.getString("JoinDate"));
////                //((JTextField) DCJD.getDateEditor().getUiComponent()).setText(rs.getString("JoinDate"));
////
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                pst.close();
////                rs.close();
////            } catch (Exception e) {
////                JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
////            }
////        }
//
//        //EName,EAge,ESalary,ECName,EImage,password,username,JoinDat
//        int row = tblEmployeeInfo.getSelectedRow();
//        String Table_click = (tblEmployeeInfo.getModel().getValueAt(row, 0).toString());
//         ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
//                EmployeeDAO employeeDAO = (EmployeeDAO) context3.getBean("EmployeeDAO");
//
//                Employee employee = new Employee();
//                EmployeeCategory employeeCat=new EmployeeCategory();
//                employee.setEID(Table_click);
//                employeeDAO.getImageIconAndOthers(employee, employeeCat);
//               txtEID.setText(employee.getEID());
//               txtEName.setText(employee.getEName());
//               txtAge.setText(employee.getEAge()+"");
//             txtSalary.setText(employee.getESalary()+"");
//             cmbEType.setSelectedItem(employeeCat.getECName());
//             ((JTextField) DCJD.getDateEditor().getUiComponent()).setText(employee.getJoinDate());
//        format = new ImageIcon(emp.getEImage());
//        java.awt.Image img = format.getImage();
//        java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);
//
//        format = new ImageIcon(newimg);
//        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
//        lblImage.setIcon(format);
//        if (emp.getPassword() == null && emp.getUsername() == null) {
//            btnSignIn.setVisible(true);
//        } else {
//            btnSignIn.setVisible(false);
//        }
//        context3.close();
//
//    }
    public void getChartValue(String dateString) {

        h1 = Chart(h1, "01:00:00", "01:59:59", dateString);
        h2 = Chart(h2, "02:00:00", "02:59:59", dateString);
        h3 = Chart(h3, "03:00:00", "03:59:59", dateString);
        h4 = Chart(h4, "04:00:00", "04:59:59", dateString);
        h5 = Chart(h5, "05:00:00", "05:59:59", dateString);
        h6 = Chart(h6, "06:00:00", "06:59:59", dateString);
        h7 = Chart(h7, "07:00:00", "07:59:59", dateString);
        h8 = Chart(h8, "08:00:00", "08:59:59", dateString);
        h9 = Chart(h9, "09:00:00", "09:59:59", dateString);
        h10 = Chart(h10, "10:00:00", "10:59:59", dateString);

        h11 = Chart(h11, "11:00:00", "11:59:59", dateString);
        h12 = Chart(h12, "12:00:00", "12:59:59", dateString);
        h13 = Chart(h13, "13:00:00", "13:59:59", dateString);
        h14 = Chart(h14, "14:00:00", "14:59:59", dateString);
        h15 = Chart(h15, "15:00:00", "15:59:59", dateString);
        h16 = Chart(h16, "16:00:00", "16:59:59", dateString);
        h17 = Chart(h17, "17:00:00", "17:59:59", dateString);
        h18 = Chart(h18, "18:00:00", "18:59:59", dateString);
        h19 = Chart(h19, "19:00:00", "19:59:59", dateString);

        h20 = Chart(h20, "20:00:00", "20:59:59", dateString);
        h21 = Chart(h21, "21:00:00", "21:59:59", dateString);
        h22 = Chart(h22, "22:00:00", "22:59:59", dateString);
        h23 = Chart(h23, "23:00:00", "23:59:59", dateString);
        h24 = Chart(h24, "00:00:00", "00:59:59", dateString);

    }

    public double Chart(double h, String sTime, String eTime, String date) {

        try {

            String sql = "SELECT  SUM(TotalPrice) \n"
                    + "FROM Customer \n"
                    + "WHERE Time BETWEEN '" + sTime + "' AND '" + eTime + "' \n"
                    + "and date ='" + date + "'";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                // ChartData c=new ChartData();
                h = rs.getDouble(1);
                return h;
                //System.out.print(hh);
            }
        } catch (Exception e) {

        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
        return h;
    }

    public void getRingChart(String dateString) {
        getChartValue(dateString);
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Morning", h6 + h7 + h8 + h9 + h10);
        dataset.setValue("Noon", h11 + h12 + h13 + h14);
        dataset.setValue("Afternoon", h15 + h16 + h17 + h18);
        dataset.setValue("Night", h19 + h20 + h21 + h22 + h23 + h2 + h1 + h2 + h3 + h4 + h5);

        //JFreeChart chart3 = ChartFactory.createPieChart("Pie Chart", dataset, true, true, true);
        JFreeChart chart3 = ChartFactory.createRingChart("Ring Chart", dataset, true, true, true);

        chart3.setBackgroundPaint(Color.white);
        chart3.getTitle().setPaint(Color.black);
        PiePlot p = (PiePlot) chart3.getPlot();

        p.setBackgroundPaint(Color.WHITE);
        //p.setForegroundAlpha(TOP_ALIGNMENT);

        ChartPanel fr = new ChartPanel(chart3);
        fr.setVisible(true);
        fr.setBounds(10, 10, 370, 235);
        pnlRingChart.removeAll();
        pnlRingChart.add(fr);
        pnlRingChart.validate();

        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File("ringchart.png");
            ChartUtilities.saveChartAsPNG(f, chart3, 600, 400, info);
        } catch (Exception e) {

        }

//       System.out.print(c.h6+c.h7+c.h8+c.h9+c.h10+c.h11+c.h12+c.h13+c.h14+c.h15+c.h16+c.h17+c.h18+ c.h19+c.h20+c.h21+c.h22+c.h23);
// System.out.print(h15);
    }

    public void getBarChart(String dateString) {
        getChartValue(dateString);
//        int x = 0, x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0, x13 = 0, x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, x23 = 0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(h6 + h7 + h8 + h9 + h10, "Sale", "Morning");
        dataset.setValue(h11 + h12 + h13 + h14, "Sale", "Noon");
        dataset.setValue(h15 + h16 + h17 + h18, "Sale", "Afternoon");
        dataset.setValue(h19 + h20 + h21 + h22 + h23 + h2 + h1 + h2 + h3 + h4 + h5, "Sale", "Night");

        //dataset.setValue(334, "Sale", "7:00-11:59 PM");
        chart = ChartFactory.createBarChart("Bar Chart", "Period", "Money", dataset, PlotOrientation.VERTICAL, true, false, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        p.setBackgroundPaint(Color.WHITE);

        ChartPanel fr2 = new ChartPanel(chart);
        fr2.setVisible(true);
        fr2.setBounds(10, 10, 380, 235);
        // fr2.setSize(380, 235);

        pnlBarChart.removeAll();
        pnlBarChart.add(fr2);
        pnlBarChart.validate();
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File("barchart.png");
            ChartUtilities.saveChartAsPNG(f, chart, 600, 400, info);
        } catch (Exception e) {

        }

    }

    public void getLineChart(String dateString) {
        getChartValue(dateString);
//        int x = 0, x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0, x13 = 0, x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, x23 = 0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(h6 + h7 + h8 + h9 + h10, "Sale", "Morning");
        dataset.setValue(h11 + h12 + h13 + h14, "Sale", "Noon");
        dataset.setValue(h15 + h16 + h17 + h18, "Sale", "Afternoon");
        dataset.setValue(h19 + h20 + h21 + h22 + h23 + h2 + h1 + h2 + h3 + h4 + h5, "Sale", "Night");

        chart1 = ChartFactory.createLineChart("Line Chart", "Period", "Money", dataset, PlotOrientation.VERTICAL, true, false, false);
        chart1.setBackgroundPaint(Color.white);
        chart1.getTitle().setPaint(Color.black);
        CategoryPlot p = chart1.getCategoryPlot();

        p.setRangeGridlinePaint(Color.black);
        p.setBackgroundPaint(Color.WHITE);

        ChartPanel fr3 = new ChartPanel(chart1);
        fr3.setVisible(true);
        fr3.setBounds(10, 10, 380, 235);

        pnlLineChart.removeAll();
        pnlLineChart.add(fr3);
        pnlLineChart.validate();

        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File("linechart.png");
            ChartUtilities.saveChartAsPNG(f, chart1, 600, 400, info);
        } catch (Exception e) {

        }

    }

    public void getPieChart(String dateString) {
        BarChart b = new BarChart(dateString);
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Morning", b.h6 + b.h7 + b.h8 + b.h9 + b.h10);
        dataset.setValue("Noon", b.h11 + b.h12 + b.h13 + b.h14);
        dataset.setValue("Afternoon", b.h15 + b.h16 + b.h17 + b.h18);
        dataset.setValue("Night", b.h19 + b.h20 + b.h21 + b.h22 + b.h23 + b.h2 + b.h1 + b.h2 + b.h3 + b.h4 + b.h5);

        JFreeChart chart3 = ChartFactory.createPieChart("Pie Chart", dataset, true, true, true);
        chart3.setBackgroundPaint(Color.white);
        chart3.getTitle().setPaint(Color.black);
        PiePlot p = (PiePlot) chart3.getPlot();

        p.setBackgroundPaint(Color.WHITE);

        p.setBackgroundPaint(Color.WHITE);
        //p.setForegroundAlpha(TOP_ALIGNMENT);

        ChartPanel fr = new ChartPanel(chart3);
        fr.setVisible(true);
        fr.setBounds(10, 10, 380, 235);

        pnlPieChart.removeAll();
        pnlPieChart.add(fr);
        pnlPieChart.validate();
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File("piechart.png");
            ChartUtilities.saveChartAsPNG(f, chart3, 600, 400, info);
        } catch (Exception e) {

        }

    }

    private void fillProductCategorycmb(JComboBox cmbPCat) {
        cmbPCat.removeAllItems();
        cmbPCat.addItem("");
        try {
            String sql = "select *from ProductCategory ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("CatName");
                //  cmbProCat.addItem(name);
                cmbPCat.addItem(name);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

//    private void fillcmb_Supplier(JComboBox cmb) {
//        cmb.removeAllItems();
//        cmb.addItem("");
//        try {
//            String sql = "select *from Supplier";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String name = rs.getString("SName");
//                cmb.addItem(name);
//
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//
//    }
//    private void fillcmb_Employee(JComboBox cmbEType) {
//        try {
//            String sql = "select *from Employee_Category";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String name = rs.getString("ECName");
//                cmbEType.addItem(name);
//
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//
//    }
//    public void getProductCategoryList(DefaultListModel listModel,DefaultListModel listModel_1) {
//
//        listModel.clear();
//        listModel_1.clear();
//        try {
//            String sql = "select *from ProductCategory";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            lstProductCategory.setModel(listModel);
//            lstProductCategory2.setModel(listModel_1);
//            while (rs.next()) {
//                listModel.addElement(rs.getString("CatName"));
//                listModel_1.addElement(rs.getString("CatName"));
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//
//            } catch (Exception e) {
//
//            }
//        }
//
//    }
    public void getEmployeeCategoryList(DefaultListModel listModel, JList Employee_Category) {

        listModel.clear();
        try {
            String sql = "select *from Employee_Category";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Employee_Category.setModel(listModel);
            while (rs.next()) {
                listModel.addElement(rs.getString("ECName"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();

            } catch (Exception e) {

            }
        }

    }
//    private void Update_Table() {
//
//        try {
//            String sql = "SELECT CatID as ID,CatName as Category from ProductCategory";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            tblCategory.setModel(DbUtils.resultSetToTableModel(rs));
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//    }
//    public void update_table_Employee(){
//    try {
//            String sql = "SELECT EID as Employee_ID,EName as Name,EAge as Age,ESalary as Salary,ECName as Employee_Category,EImage as Image\n" +
//"                    from Employee_Info,Employee_Category where  Employee_Info.ECID=Employee_Category.ECID and Employee_Info.ECID='" + CatID + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            tblEmployeeInfo.setModel(DbUtils.resultSetToTableModel(rs));
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//    }
//    private void Update_Table_By_Category(JList ls, JTable tbl) {
//        int CatID = 0;
//        try {
//            String sql = "SELECT CatID from ProductCategory where CatName='" + ls.getSelectedValue() + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                String add1 = rs.getString(1);
//
//                CatID = Integer.parseInt(add1);
//            }
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        }
//        try {
//            String sql = "SELECT PID as ID,PName as Name,PPrice as Price,PAmount as Amount,Unit,PurchasePricePerUnit as Purchase_Price from Product where CatID='" + CatID + "' ";//and addInfo=1
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            tbl.setModel(DbUtils.resultSetToTableModel(rs));
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//    }

//    private void Update_Employee_Table_By_Category(EmployeeCategory empCat,JTable tblEmployeeInfo) {
//        //int CatID = 0;
//        try {
//            String sql = "SELECT ECID from Employee_Category where ECName='" + empCat.getECName() + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                String add1 = rs.getString(1);
//                empCat.setECATID(Integer.parseInt(add1));
//                // CatID = Integer.parseInt(add1);
//            }
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        }
//        try {
//            String sql = "SELECT EID as ID,EName as Name,EAge as Age,ESalary as Salary,ECName as Category,EImage as Image\n"
//                    + "                    from Employee_Info,Employee_Category where  Employee_Info.ECID=Employee_Category.ECID and Employee_Info.ECID='" + empCat.getECATID() + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            tblEmployeeInfo.setModel(DbUtils.resultSetToTableModel(rs));
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//
//    }
//    public void getProductCategoryID(JLabel lblId) {
//
//        try {
//            String sql = "select max(CatID) from ProductCategory";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                String add1 = rs.getString(1);
//
//                int a = Integer.parseInt(add1);
//
//                lblId.setText(a + 1 + "");
//
//                // lblId.setText(a + "");
//            }
//
//        } catch (Exception e) {
//
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//    }
    public void getPID(JComboBox cmb, JTextField txt) {

        int catID = 0;
        int a = 0;
        String add1 = null;

        String row = (String) cmb.getSelectedItem();
        try {

            String sql = "select CatID from ProductCategory where CatName='" + row + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                catID = rs.getInt("catID");
                // System.out.print(catID);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

        try {
            String sql = "Select Max(ROWID) from product where CatID='" + catID + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                add1 = rs.getString(1);

                a = Integer.parseInt(add1);

                //System.out.println(a);
            }

        } catch (Exception e) {

        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
        if (add1 != null) {
            txt.setText(a + 1 + "");
            cmb.setSelectedItem(row);

        } else {
            try {
                String sql = "Select ROWID from product where CatID='" + catID + "' Limit 1";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    add1 = rs.getString(1);

                    a = Integer.parseInt(add1);

                    txt.setText(a + "");
                    cmb.setSelectedItem(row);
                    //System.out.println(a);
                }

            } catch (Exception e) {

            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jmEmployeeStatus = new javax.swing.JPopupMenu();
        Present = new javax.swing.JMenuItem();
        Absent = new javax.swing.JMenuItem();
        jmAdminStatus = new javax.swing.JPopupMenu();
        changePass = new javax.swing.JMenuItem();
        logOut = new javax.swing.JMenuItem();
        jmEP = new javax.swing.JPopupMenu();
        Monthly = new javax.swing.JMenuItem();
        Yearly = new javax.swing.JMenuItem();
        jmEA = new javax.swing.JPopupMenu();
        MonthlyAR = new javax.swing.JMenuItem();
        YearlyAR = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblAdminName = new javax.swing.JLabel();
        btnSettings = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlCategory = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        btnCategoryAdd = new javax.swing.JButton();
        btnUpdateCategory = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        txtSPhone = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSEmail = new javax.swing.JTextField();
        txtSName = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        btnAddSupplier = new javax.swing.JButton();
        txtSAddress = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tpBPImage = new javax.swing.JTabbedPane();
        pnlAnimation = new javax.swing.JPanel();
        lblBP2 = new javax.swing.JLabel();
        lblBP1 = new javax.swing.JLabel();
        lblBP3 = new javax.swing.JLabel();
        lblBP4 = new javax.swing.JLabel();
        lblBP5 = new javax.swing.JLabel();
        lblBP6 = new javax.swing.JLabel();
        lblBP7 = new javax.swing.JLabel();
        lblBP8 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        AllProduct = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProductCategory = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductDetails = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtPID = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnUpdateProduct = new javax.swing.JButton();
        cmbPCat = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtPPrice = new javax.swing.JTextField();
        txtPAmount = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        cmbSupplier = new javax.swing.JComboBox();
        txtPPP = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        cmbPUnit = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtImagePath = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        lblPImage = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Employee_Category = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblEmployeeInfo = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtEID = new javax.swing.JTextField();
        txtEName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        pnlImage = new javax.swing.JDesktopPane();
        lblImage = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        txtPath = new javax.swing.JTextField();
        cmbEType = new javax.swing.JComboBox();
        txtECategory = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        DCJD = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnSignIn = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblEmpAttendence = new javax.swing.JTable();
        jButton41 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        pnlPieChart = new javax.swing.JPanel();
        pnlBarChart = new javax.swing.JPanel();
        pnlLineChart = new javax.swing.JPanel();
        pnlRingChart = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lst = new javax.swing.JList();
        CBSP = new javax.swing.JCheckBox();
        pnlProductMain = new javax.swing.JPanel();
        pnlProduct2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstProductCategory2 = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblProductDetails2 = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        RDBSM = new javax.swing.JRadioButton();
        RDBSD = new javax.swing.JRadioButton();
        RDB12M = new javax.swing.JRadioButton();
        jButton16 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        btnPR = new javax.swing.JButton();
        btnAR = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton39 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        Present.setText("Present");
        Present.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PresentActionPerformed(evt);
            }
        });
        jmEmployeeStatus.add(Present);

        Absent.setText("Absent");
        Absent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbsentActionPerformed(evt);
            }
        });
        jmEmployeeStatus.add(Absent);

        changePass.setText("Change password");
        changePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassActionPerformed(evt);
            }
        });
        jmAdminStatus.add(changePass);

        logOut.setText("Log out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        jmAdminStatus.add(logOut);

        Monthly.setText("Monthly Performance Report");
        Monthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthlyActionPerformed(evt);
            }
        });
        jmEP.add(Monthly);

        Yearly.setText("Yearly Performance Report");
        Yearly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearlyActionPerformed(evt);
            }
        });
        jmEP.add(Yearly);

        MonthlyAR.setText("Monthly attendence report");
        MonthlyAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthlyARActionPerformed(evt);
            }
        });
        jmEA.add(MonthlyAR);

        YearlyAR.setText("Yearly attendence report");
        YearlyAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearlyARActionPerformed(evt);
            }
        });
        jmEA.add(YearlyAR);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(57, 64, 70));

        jPanel4.setBackground(new java.awt.Color(57, 64, 70));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });

        lblAdminName.setFont(new java.awt.Font("Siyam Rupali", 0, 18)); // NOI18N
        lblAdminName.setForeground(new java.awt.Color(255, 255, 255));
        lblAdminName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdminName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/img-profile.jpg"))); // NOI18N
        lblAdminName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnSettings.setBackground(new java.awt.Color(57, 64, 70));
        btnSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/cog.png"))); // NOI18N
        btnSettings.setBorder(null);
        btnSettings.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                btnSettingsMouseWheelMoved(evt);
            }
        });
        btnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSettingsMouseEntered(evt);
            }
        });
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdminName)
                    .addComponent(btnSettings))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(57, 64, 70));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseEntered(evt);
            }
        });

        pnlCategory.setBackground(new java.awt.Color(57, 64, 70));

        tblCategory.setBackground(new java.awt.Color(57, 64, 70));
        tblCategory.setForeground(new java.awt.Color(255, 255, 255));
        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryMouseClicked(evt);
            }
        });
        tblCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCategoryKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCategoryKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategory);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("All Category:");

        txtCategory.setForeground(new java.awt.Color(77, 100, 141));
        txtCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryActionPerformed(evt);
            }
        });

        lblId.setBackground(new java.awt.Color(57, 64, 70));
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setOpaque(true);

        btnCategoryAdd.setBackground(new java.awt.Color(57, 64, 70));
        btnCategoryAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnCategoryAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/plus.png"))); // NOI18N
        btnCategoryAdd.setToolTipText("Add Category");
        btnCategoryAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryAddActionPerformed(evt);
            }
        });

        btnUpdateCategory.setBackground(new java.awt.Color(57, 64, 70));
        btnUpdateCategory.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/update.png"))); // NOI18N
        btnUpdateCategory.setText("Update Category");
        btnUpdateCategory.setToolTipText("Update Category");
        btnUpdateCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCategoryActionPerformed(evt);
            }
        });

        tblSupplier.setBackground(new java.awt.Color(57, 64, 70));
        tblSupplier.setForeground(new java.awt.Color(255, 255, 255));
        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        tblSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSupplierKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSupplierKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tblSupplier);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("All Supplier:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Category Name:");

        jPanel11.setBackground(new java.awt.Color(57, 64, 70));

        btnUpdate.setBackground(new java.awt.Color(57, 64, 70));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/update.png"))); // NOI18N
        btnUpdate.setText("Update Supplier");
        btnUpdate.setToolTipText("Update Supplier");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblID.setBackground(new java.awt.Color(57, 64, 70));
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setOpaque(true);

        txtSPhone.setForeground(new java.awt.Color(77, 100, 141));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Address:");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Phone:");

        txtSEmail.setForeground(new java.awt.Color(77, 100, 141));

        txtSName.setForeground(new java.awt.Color(77, 100, 141));

        jButton13.setBackground(new java.awt.Color(57, 64, 70));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/pdf.png"))); // NOI18N
        jButton13.setText("See Items of this supplier");
        jButton13.setToolTipText("See Items of this supplier");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        btnAddSupplier.setBackground(new java.awt.Color(57, 64, 70));
        btnAddSupplier.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/plus.png"))); // NOI18N
        btnAddSupplier.setToolTipText("Add Supplier");
        btnAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupplierActionPerformed(evt);
            }
        });

        txtSAddress.setForeground(new java.awt.Color(77, 100, 141));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Supplier Name:");

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Email:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtSName, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(txtSEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(jButton13)))
                .addContainerGap())
        );

        tpBPImage.setForeground(new java.awt.Color(255, 255, 255));

        pnlAnimation.setBackground(new java.awt.Color(57, 64, 70));
        pnlAnimation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblBP2.setBackground(new java.awt.Color(255, 255, 255));
        lblBP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP2.setOpaque(true);

        lblBP1.setBackground(new java.awt.Color(255, 255, 255));
        lblBP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP1.setOpaque(true);

        lblBP3.setBackground(new java.awt.Color(255, 255, 255));
        lblBP3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP3.setOpaque(true);

        lblBP4.setBackground(new java.awt.Color(255, 255, 255));
        lblBP4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP4.setOpaque(true);

        lblBP5.setBackground(new java.awt.Color(255, 255, 255));
        lblBP5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP5.setOpaque(true);

        lblBP6.setBackground(new java.awt.Color(255, 255, 255));
        lblBP6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP6.setOpaque(true);

        lblBP7.setBackground(new java.awt.Color(255, 255, 255));
        lblBP7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP7.setOpaque(true);

        lblBP8.setBackground(new java.awt.Color(255, 255, 255));
        lblBP8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBP8.setOpaque(true);

        javax.swing.GroupLayout pnlAnimationLayout = new javax.swing.GroupLayout(pnlAnimation);
        pnlAnimation.setLayout(pnlAnimationLayout);
        pnlAnimationLayout.setHorizontalGroup(
            pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnimationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnimationLayout.createSequentialGroup()
                        .addComponent(lblBP1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(lblBP2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnimationLayout.createSequentialGroup()
                        .addComponent(lblBP3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBP4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAnimationLayout.createSequentialGroup()
                        .addComponent(lblBP5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBP6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnimationLayout.createSequentialGroup()
                        .addComponent(lblBP7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBP8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlAnimationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblBP1, lblBP2, lblBP3, lblBP4, lblBP5, lblBP6, lblBP7, lblBP8});

        pnlAnimationLayout.setVerticalGroup(
            pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnimationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBP1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBP2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBP3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBP4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBP5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBP6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBP7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBP8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pnlAnimationLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblBP1, lblBP2, lblBP3, lblBP4, lblBP5, lblBP6, lblBP7, lblBP8});

        tpBPImage.addTab("<html><body><p>Todays Best selling Products</p></body></html>", pnlAnimation);

        jButton12.setBackground(new java.awt.Color(57, 64, 70));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/arrow.png"))); // NOI18N
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCategoryLayout = new javax.swing.GroupLayout(pnlCategory);
        pnlCategory.setLayout(pnlCategoryLayout);
        pnlCategoryLayout.setHorizontalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCategoryLayout.createSequentialGroup()
                        .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9)
                            .addComponent(jScrollPane1)
                            .addGroup(pnlCategoryLayout.createSequentialGroup()
                                .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlCategoryLayout.createSequentialGroup()
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlCategoryLayout.createSequentialGroup()
                                                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCategoryAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnUpdateCategory))
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpBPImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlCategoryLayout.setVerticalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpBPImage, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateCategory)
                    .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCategoryAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCategoryLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCategoryLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html><body><br><br><p style=\"Color:White\">Category and Suppliers</p><br><br></body></html>", pnlCategory);

        AllProduct.setBackground(new java.awt.Color(57, 64, 70));

        jPanel21.setBackground(new java.awt.Color(57, 64, 70));

        lstProductCategory.setBackground(new java.awt.Color(57, 64, 70));
        lstProductCategory.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstProductCategory.setForeground(new java.awt.Color(255, 255, 255));
        lstProductCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstProductCategoryMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstProductCategoryMouseReleased(evt);
            }
        });
        lstProductCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lstProductCategoryKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(lstProductCategory);

        tblProductDetails.setBackground(new java.awt.Color(57, 64, 70));
        tblProductDetails.setForeground(new java.awt.Color(255, 255, 255));
        tblProductDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductDetails.setGridColor(new java.awt.Color(123, 164, 168));
        tblProductDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductDetailsMouseClicked(evt);
            }
        });
        tblProductDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProductDetailsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductDetailsKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductDetails);

        jLabel3.setBackground(new java.awt.Color(57, 64, 70));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Product Category");

        jLabel4.setBackground(new java.awt.Color(57, 64, 70));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Info");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(572, 572, 572))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        txtPID.setEditable(false);
        txtPID.setBackground(new java.awt.Color(57, 64, 70));
        txtPID.setForeground(new java.awt.Color(255, 255, 255));
        txtPID.setText("ID");
        txtPID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPIDActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(57, 64, 70));
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Product Name");

        txtProductName.setForeground(new java.awt.Color(77, 100, 141));
        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(57, 64, 70));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/plus.png"))); // NOI18N
        jButton3.setToolTipText("Add New Product");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnUpdateProduct.setBackground(new java.awt.Color(57, 64, 70));
        btnUpdateProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/update.png"))); // NOI18N
        btnUpdateProduct.setText("Update record");
        btnUpdateProduct.setToolTipText("Update record");
        btnUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductActionPerformed(evt);
            }
        });

        cmbPCat.setForeground(new java.awt.Color(77, 100, 141));
        cmbPCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cmbPCat.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cmbPCatAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cmbPCat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbPCatFocusGained(evt);
            }
        });
        cmbPCat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbPCatPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbPCat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPCatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmbPCatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmbPCatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmbPCatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbPCatMouseReleased(evt);
            }
        });

        jLabel39.setBackground(new java.awt.Color(57, 64, 70));
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Category");

        jLabel40.setBackground(new java.awt.Color(57, 64, 70));
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Price");

        txtPPrice.setForeground(new java.awt.Color(77, 100, 141));
        txtPPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPPriceActionPerformed(evt);
            }
        });

        txtPAmount.setForeground(new java.awt.Color(77, 100, 141));
        txtPAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPAmountActionPerformed(evt);
            }
        });

        jLabel43.setBackground(new java.awt.Color(57, 64, 70));
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Amount");

        jLabel44.setBackground(new java.awt.Color(57, 64, 70));
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Unit");

        jLabel46.setBackground(new java.awt.Color(57, 64, 70));
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Supplier");

        cmbSupplier.setForeground(new java.awt.Color(77, 100, 141));
        cmbSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        txtPPP.setForeground(new java.awt.Color(77, 100, 141));
        txtPPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPPPActionPerformed(evt);
            }
        });

        jLabel45.setBackground(new java.awt.Color(57, 64, 70));
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Purchase price/unit");

        cmbPUnit.setForeground(new java.awt.Color(77, 100, 141));
        cmbPUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "KG", "LITER", "UNIT", "" }));
        cmbPUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPUnitActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(57, 64, 70));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/pdf.png"))); // NOI18N
        jButton8.setText("Report");
        jButton8.setToolTipText("Create Report of The Selected Product");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(57, 64, 70));
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/pdf.png"))); // NOI18N
        jButton36.setText("All products stock report");
        jButton36.setToolTipText("See All products stock report");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(57, 64, 70));
        jButton2.setText("Attach Image");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtImagePath.setToolTipText("Image Path");
        txtImagePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImagePathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPImage, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDesktopPane1.setLayer(lblPImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel39))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPCat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(txtPPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(txtPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPPP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDesktopPane1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtImagePath))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(3, 3, 3))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel40)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel44))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbPUnit)
                                .addComponent(txtPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addGap(26, 26, 26)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel45)
                                .addComponent(jLabel46))
                            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(btnUpdateProduct)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton36))
                    .addComponent(txtImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout AllProductLayout = new javax.swing.GroupLayout(AllProduct);
        AllProduct.setLayout(AllProductLayout);
        AllProductLayout.setHorizontalGroup(
            AllProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        AllProductLayout.setVerticalGroup(
            AllProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllProductLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html><body><br><br><p style=\"Color:White\">All Product</p><br><br></body></html>", AllProduct);

        jTabbedPane3.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseEntered(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(57, 64, 70));
        jPanel17.setForeground(new java.awt.Color(255, 255, 255));

        Employee_Category.setBackground(new java.awt.Color(57, 64, 70));
        Employee_Category.setForeground(new java.awt.Color(255, 255, 255));
        Employee_Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Employee_CategoryMouseReleased(evt);
            }
        });
        Employee_Category.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Employee_CategoryKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(Employee_Category);

        tblEmployeeInfo.setBackground(new java.awt.Color(57, 64, 70));
        tblEmployeeInfo.setForeground(new java.awt.Color(255, 255, 255));
        tblEmployeeInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEmployeeInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeInfoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblEmployeeInfoMouseReleased(evt);
            }
        });
        tblEmployeeInfo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblEmployeeInfoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEmployeeInfoKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblEmployeeInfo);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Employee ID");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Employee Name");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Age");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Salary");

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Employee type");

        txtEID.setForeground(new java.awt.Color(77, 100, 141));

        txtEName.setForeground(new java.awt.Color(77, 100, 141));

        txtAge.setForeground(new java.awt.Color(77, 100, 141));

        txtSalary.setForeground(new java.awt.Color(77, 100, 141));

        pnlImage.setDesktopManager(null);
        pnlImage.setMaximumSize(new java.awt.Dimension(159, 95));
        pnlImage.setMinimumSize(new java.awt.Dimension(159, 95));
        pnlImage.setOpaque(false);

        javax.swing.GroupLayout pnlImageLayout = new javax.swing.GroupLayout(pnlImage);
        pnlImage.setLayout(pnlImageLayout);
        pnlImageLayout.setHorizontalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlImageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        pnlImageLayout.setVerticalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlImage.setLayer(lblImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton19.setBackground(new java.awt.Color(57, 64, 70));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Attach Image");
        jButton19.setToolTipText("Attach Image of This Employee");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        txtPath.setForeground(new java.awt.Color(77, 100, 141));

        cmbEType.setForeground(new java.awt.Color(77, 100, 141));
        cmbEType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbETypeActionPerformed(evt);
            }
        });

        txtECategory.setForeground(new java.awt.Color(77, 100, 141));
        txtECategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtECategoryActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(57, 64, 70));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/plus.png"))); // NOI18N
        jButton4.setToolTipText("Add Employee Category");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(57, 64, 70));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/update.png"))); // NOI18N
        jButton5.setToolTipText("Update Employee Category");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(57, 64, 70));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/delete_01.gif"))); // NOI18N
        jButton6.setToolTipText("Delete Category");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        DCJD.setDateFormatString("yyyy-MM-dd");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Joining date");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setOpaque(false);

        jButton17.setBackground(new java.awt.Color(57, 64, 70));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/plus.png"))); // NOI18N
        jButton17.setToolTipText("Add New Employee");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(57, 64, 70));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/update.png"))); // NOI18N
        jButton18.setToolTipText("Update Selected Employee Info");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(57, 64, 70));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/delete_01.gif"))); // NOI18N
        jButton1.setToolTipText("Delete Employee");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(57, 64, 70));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/pdf.png"))); // NOI18N
        jButton7.setToolTipText("Create Report of Selected Employee");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnSignIn.setBackground(new java.awt.Color(57, 64, 70));
        btnSignIn.setForeground(new java.awt.Color(255, 255, 255));
        btnSignIn.setText("Sign in");
        btnSignIn.setToolTipText("Assign Employee As a Salesman");
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSignIn)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton17, jButton7});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton18)
                    .addComponent(btnSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton17, jButton7});

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtECategory)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtEID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEName)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(txtAge, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtSalary, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEType, 0, 143, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DCJD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton5});

        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtECategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jButton19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbEType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(DCJD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtEID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(302, Short.MAX_VALUE))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton4, jButton5});

        jTabbedPane3.addTab("Employee List", jPanel17);

        jPanel15.setBackground(new java.awt.Color(57, 64, 70));

        tblEmpAttendence.setBackground(new java.awt.Color(57, 64, 70));
        tblEmpAttendence.setForeground(new java.awt.Color(255, 255, 255));
        tblEmpAttendence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblEmpAttendence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpAttendenceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblEmpAttendenceMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblEmpAttendenceMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblEmpAttendenceMouseReleased(evt);
            }
        });
        jScrollPane10.setViewportView(tblEmpAttendence);

        jButton41.setBackground(new java.awt.Color(57, 64, 70));
        jButton41.setForeground(new java.awt.Color(255, 255, 255));
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/s_reload.png"))); // NOI18N
        jButton41.setText("Reload");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(57, 64, 70));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date:");

        lblDate.setBackground(new java.awt.Color(57, 64, 70));
        lblDate.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                        .addGap(467, 467, 467))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton41)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(769, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Employees todays attendance  record", jPanel14);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("<html><body><br><br><p style=\"Color:White\">Employee  management</p><br><br></body></html>", jPanel12);

        jTabbedPane2.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane2.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        jTabbedPane2.setOpaque(true);
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseEntered(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(57, 64, 70));

        pnlPieChart.setBackground(new java.awt.Color(57, 64, 70));
        pnlPieChart.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                pnlPieChartAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        pnlPieChart.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlPieChartMouseDragged(evt);
            }
        });
        pnlPieChart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pnlPieChartFocusGained(evt);
            }
        });
        pnlPieChart.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlPieChartMouseWheelMoved(evt);
            }
        });
        pnlPieChart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPieChartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlPieChartMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlPieChartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlPieChartMouseReleased(evt);
            }
        });
        pnlPieChart.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlPieChartComponentResized(evt);
            }
        });

        javax.swing.GroupLayout pnlPieChartLayout = new javax.swing.GroupLayout(pnlPieChart);
        pnlPieChart.setLayout(pnlPieChartLayout);
        pnlPieChartLayout.setHorizontalGroup(
            pnlPieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        pnlPieChartLayout.setVerticalGroup(
            pnlPieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );

        pnlBarChart.setBackground(new java.awt.Color(57, 64, 70));
        pnlBarChart.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlBarChartMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout pnlBarChartLayout = new javax.swing.GroupLayout(pnlBarChart);
        pnlBarChart.setLayout(pnlBarChartLayout);
        pnlBarChartLayout.setHorizontalGroup(
            pnlBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        pnlBarChartLayout.setVerticalGroup(
            pnlBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlLineChart.setBackground(new java.awt.Color(57, 64, 70));
        pnlLineChart.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlLineChartMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout pnlLineChartLayout = new javax.swing.GroupLayout(pnlLineChart);
        pnlLineChart.setLayout(pnlLineChartLayout);
        pnlLineChartLayout.setHorizontalGroup(
            pnlLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlLineChartLayout.setVerticalGroup(
            pnlLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlRingChart.setBackground(new java.awt.Color(57, 64, 70));
        pnlRingChart.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlRingChartMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout pnlRingChartLayout = new javax.swing.GroupLayout(pnlRingChart);
        pnlRingChart.setLayout(pnlRingChartLayout);
        pnlRingChartLayout.setHorizontalGroup(
            pnlRingChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlRingChartLayout.setVerticalGroup(
            pnlRingChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jLabel22.setFont(new java.awt.Font("Rod", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Todays Sales Trends by period");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRingChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRingChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("<html><body><br><p>Sale Chart</p><br></Body></html>", jPanel13);

        jPanel16.setBackground(new java.awt.Color(57, 64, 70));

        jPanel19.setBackground(new java.awt.Color(57, 64, 70));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel19.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Days Phase:");

        jButton27.setBackground(new java.awt.Color(57, 64, 70));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("<html>profit Report</html>");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(new java.awt.Color(57, 64, 70));
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("<html>product sold time report</html>");
        jButton28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(57, 64, 70));
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("<html>product sold amount report</html>");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(57, 64, 70));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-charts-graphs.png"))); // NOI18N
        jButton10.setText("<html><p>Create Todays</p><p></p>General Report</p></html>");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(108, 108, 108))
        );

        jPanel20.setBackground(new java.awt.Color(57, 64, 70));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel20.setPreferredSize(new java.awt.Dimension(276, 322));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("7 Days Phase:");

        jButton30.setBackground(new java.awt.Color(57, 64, 70));
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("<html>profit Report</html>");
        jButton30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setBackground(new java.awt.Color(57, 64, 70));
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("<html>product sold time report</html>");
        jButton31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(57, 64, 70));
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("<html>product sold amount report</html>");
        jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(57, 64, 70));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-charts-graphs.png"))); // NOI18N
        jButton11.setText("<html><p style='text-align:center';>Create last 7</p><p style='text-align:center';>Days</p></p>General Report</p></html>");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jButton11))
                .addContainerGap())
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(109, 109, 109))
        );

        jPanel22.setBackground(new java.awt.Color(57, 64, 70));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel22.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("30 Days Phase:");

        jButton33.setBackground(new java.awt.Color(57, 64, 70));
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setText("<html>profit Report</html>");
        jButton33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setBackground(new java.awt.Color(57, 64, 70));
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("<html>product sold time report</html>");
        jButton34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(57, 64, 70));
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("<html>product sold amount report</html>");
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(57, 64, 70));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-charts-graphs.png"))); // NOI18N
        jButton14.setText("<html><p style='text-align:center';>Create last 30</p><p style='text-align:center';>Days</p></p>General Report</p></html>");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14)
                    .addComponent(jButton33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jButton34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jButton35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("<html><body><br><p>General Report</p><br></body></html>", jPanel16);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jPanel27.setBackground(new java.awt.Color(57, 64, 70));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        jPanel27.setForeground(new java.awt.Color(255, 255, 255));

        dateChooser.setBackground(new java.awt.Color(255, 255, 255));
        dateChooser.setForeground(new java.awt.Color(159, 166, 173));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jButton21.setBackground(new java.awt.Color(57, 64, 70));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-charts-graphs.png"))); // NOI18N
        jButton21.setText("<html><p>Create general report</p></html> ");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(57, 64, 70));
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("<html><p>Create profit report</p></html> ");
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(57, 64, 70));
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("<html><p>Create sold amount report</p></html> ");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Create Report of A  Certain Date");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(jButton22)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );

        jPanel8.setBackground(new java.awt.Color(57, 64, 70));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jButton26.setBackground(new java.awt.Color(57, 64, 70));
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("<html><p>Create sold amount report</p></html> ");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(57, 64, 70));
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("<html><p>Create profit report</p></html> ");
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(57, 64, 70));
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-charts-graphs.png"))); // NOI18N
        jButton24.setText("<html><p>Create general report </p></html> ");
        jButton24.setActionCommand("<html><p>Create general report</p><p> between the choosen dates</p></html> ");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jDateChooser2.setBackground(new java.awt.Color(57, 64, 70));
        jDateChooser2.setForeground(new java.awt.Color(159, 166, 173));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jDateChooser3.setBackground(new java.awt.Color(57, 64, 70));
        jDateChooser3.setForeground(new java.awt.Color(159, 166, 173));
        jDateChooser3.setDateFormatString("yyyy-MM-dd");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Create Sale Report of time between two Dates ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton24)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton24, jButton25, jButton26});

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(110, 110, 110))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("<html><body><br><p>More Reports</p><br></body></html>", jPanel26);

        jPanel23.setBackground(new java.awt.Color(57, 64, 70));

        lst.setBackground(new java.awt.Color(57, 64, 70));
        lst.setForeground(new java.awt.Color(255, 255, 255));
        lst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane6.setViewportView(lst);

        CBSP.setBackground(new java.awt.Color(57, 64, 70));
        CBSP.setForeground(new java.awt.Color(255, 255, 255));
        CBSP.setText("Select a Product");
        CBSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBSPActionPerformed(evt);
            }
        });

        pnlProductMain.setBackground(new java.awt.Color(57, 64, 70));

        pnlProduct2.setBackground(new java.awt.Color(57, 64, 70));

        lstProductCategory2.setBackground(new java.awt.Color(57, 64, 70));
        lstProductCategory2.setForeground(new java.awt.Color(255, 255, 255));
        lstProductCategory2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstProductCategory2MouseReleased(evt);
            }
        });
        jScrollPane7.setViewportView(lstProductCategory2);

        tblProductDetails2.setBackground(new java.awt.Color(57, 64, 70));
        tblProductDetails2.setForeground(new java.awt.Color(255, 255, 255));
        tblProductDetails2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tblProductDetails2);

        javax.swing.GroupLayout pnlProduct2Layout = new javax.swing.GroupLayout(pnlProduct2);
        pnlProduct2.setLayout(pnlProduct2Layout);
        pnlProduct2Layout.setHorizontalGroup(
            pnlProduct2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduct2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );
        pnlProduct2Layout.setVerticalGroup(
            pnlProduct2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProduct2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProduct2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout pnlProductMainLayout = new javax.swing.GroupLayout(pnlProductMain);
        pnlProductMain.setLayout(pnlProductMainLayout);
        pnlProductMainLayout.setHorizontalGroup(
            pnlProductMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductMainLayout.createSequentialGroup()
                .addComponent(pnlProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
        pnlProductMainLayout.setVerticalGroup(
            pnlProductMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProductMainLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlProduct2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel25.setBackground(new java.awt.Color(57, 64, 70));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setForeground(new java.awt.Color(255, 255, 255));

        RDBSM.setBackground(new java.awt.Color(57, 64, 70));
        buttonGroup1.add(RDBSM);
        RDBSM.setForeground(new java.awt.Color(255, 255, 255));
        RDBSM.setText("Select a Month");
        RDBSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDBSMActionPerformed(evt);
            }
        });

        RDBSD.setBackground(new java.awt.Color(57, 64, 70));
        buttonGroup1.add(RDBSD);
        RDBSD.setForeground(new java.awt.Color(255, 255, 255));
        RDBSD.setText("Select a Day");
        RDBSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDBSDActionPerformed(evt);
            }
        });

        RDB12M.setBackground(new java.awt.Color(57, 64, 70));
        buttonGroup1.add(RDB12M);
        RDB12M.setForeground(new java.awt.Color(255, 255, 255));
        RDB12M.setText("Select a year");
        RDB12M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDB12MActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RDBSD)
                    .addComponent(RDB12M)
                    .addComponent(RDBSM))
                .addGap(17, 17, 17))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(RDBSM)
                .addGap(18, 18, 18)
                .addComponent(RDBSD)
                .addGap(18, 18, 18)
                .addComponent(RDB12M, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton16.setBackground(new java.awt.Color(57, 64, 70));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/pdf.png"))); // NOI18N
        jButton16.setText("<html><p Style=\"text-Align:Center;\">Create Report</p></html>");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(pnlProductMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(CBSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(236, 236, 236))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton16)))
                        .addGap(362, 362, 362))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBSP, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addGap(3, 3, 3)
                .addComponent(pnlProductMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );

        jTabbedPane2.addTab("<html><body><br><p>More Specific</p><br></body></html>", jPanel23);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("<html><body><br><br><p style=\"Color:White\">Management support panel</p><br><br></body></html>", jPanel5);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane4.setBackground(new java.awt.Color(57, 64, 70));
        jTabbedPane4.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane4.setOpaque(true);

        jPanel24.setBackground(new java.awt.Color(57, 64, 70));
        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel24MouseEntered(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(57, 64, 70));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-grid-tables.png"))); // NOI18N
        jButton15.setText("<html><p Style=\"text-Align:Center;\">Monthly budgeting</p></html>");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(57, 64, 70));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("<html><p Style=\"text-Align:Center;\">Make Decision on Products price vs sold amount vs profit</p></html>");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(57, 64, 70));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-dashboard.png"))); // NOI18N
        jButton9.setText("Last 5 years general report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        btnPR.setBackground(new java.awt.Color(57, 64, 70));
        btnPR.setForeground(new java.awt.Color(255, 255, 255));
        btnPR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-dashboard.png"))); // NOI18N
        btnPR.setText("Sales staff  performance report");
        btnPR.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnPRMouseMoved(evt);
            }
        });
        btnPR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnPRFocusLost(evt);
            }
        });
        btnPR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPRMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPRMouseReleased(evt);
            }
        });
        btnPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPRActionPerformed(evt);
            }
        });

        btnAR.setBackground(new java.awt.Color(57, 64, 70));
        btnAR.setForeground(new java.awt.Color(255, 255, 255));
        btnAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-dashboard.png"))); // NOI18N
        btnAR.setText("Employee attendence report");
        btnAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnARMouseEntered(evt);
            }
        });
        btnAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAR, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(btnPR, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addGap(625, 625, 625))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPR, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAR, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        jPanel24Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAR, btnPR, jButton15, jButton20, jButton9});

        jTabbedPane4.addTab("Make Decision", jPanel24);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("<html><body><br><br><p style=\"Color:White\">Decision support panel</p><br><br></body></html>", jPanel9);

        jPanel10.setBackground(new java.awt.Color(57, 64, 70));

        jButton39.setBackground(new java.awt.Color(57, 64, 70));
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Clear database & set everything new");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(57, 64, 70));
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Change password");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                .addGap(635, 635, 635))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html><body><br><br><p style=\"Color:White\">Extreme decision making arena</p><br><br></body></html>", jPanel10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(2, 2, 2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBackground(new java.awt.Color(57, 64, 70));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/cog.png"))); // NOI18N
        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setBackground(new java.awt.Color(221, 103, 101));
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setText("Log out");
        jMenuItem2.setOpaque(true);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/exit-button-th (2).png"))); // NOI18N
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-charts-graphs.png"))); // NOI18N
        jMenu3.setText("General ");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Todays General Report");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Last Seven Days General Report");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Last Thirty Days General Report");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Last 5 Years General Report");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/ajax-loader.gif"))); // NOI18N
        jMenu2.setText("Stock");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setText("All Products Stock Report");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-notifications.png"))); // NOI18N
        jMenu4.setText("Employee");

        jMenu5.setText("Performane Report");

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Sales Staffs  Monthly Performance Report");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Sales Staffs Yearly Performance Report");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenu4.add(jMenu5);

        jMenu8.setText("Attendence Report");

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem15.setText("Employees Monthly Attendence Report");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem16.setText("Employees Yearly Attendence Report");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenu4.add(jMenu8);

        jMenuBar1.add(jMenu4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/icon-grid-tables.png"))); // NOI18N
        jMenu6.setText("Budgetting");

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem13.setText("Monthly Budgetting");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/help.png"))); // NOI18N

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setText("About");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setText("Help");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RDB12MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDB12MActionPerformed
        String[] year = {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035",
            "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"};
        lst.setVisible(true);
        getMonthsORDays(year);
        lst.setModel(listModel3);
    }//GEN-LAST:event_RDB12MActionPerformed

    private void RDBSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDBSDActionPerformed
        String[] days = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
        lst.setVisible(true);
        getMonthsORDays(days);
        lst.setModel(listModel3);
    }//GEN-LAST:event_RDBSDActionPerformed

    private void RDBSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDBSMActionPerformed
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        lst.setVisible(true);
        getMonthsORDays(months);
        lst.setModel(listModel3);
    }//GEN-LAST:event_RDBSMActionPerformed

    private void lstProductCategory2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProductCategory2MouseReleased
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");

        proDAO.Update_Table_By_Category((String) lstProductCategory2.getSelectedValue(), tblProductDetails2);
        context.close();

    }//GEN-LAST:event_lstProductCategory2MouseReleased

    private void CBSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBSPActionPerformed
        if (CBSP.isSelected()) {
            if (RDBSM.isSelected() || RDBSD.isSelected() || RDB12M.isSelected()) {
                if (lst.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must select a value from the List!");
                    CBSP.setSelected(false);
                } else {
                    pnlProductMain.setVisible(true);
                    pnlProduct2.setVisible(true);
                    CBSP.setSelected(true);

                }
            } else {
                JOptionPane.showMessageDialog(null, "You must select an option first!");
                CBSP.setSelected(false);
            }
        } else {
            CBSP.setSelected(false);
        }
    }//GEN-LAST:event_CBSPActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (txtEName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please,select an employee!");
        } else {
            try {
                SpecificEmployeeReport se = new SpecificEmployeeReport();
                se.CreateReport(txtEID.getText());
                ReportOpen ro = new ReportOpen();
                ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeeReport\\report.pdf");
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cmbEType.removeAllItems();

        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeCategoryDAO empDAO = (EmployeeCategoryDAO) context3.getBean("EmployeeCategoryDAO");
        EmployeeCategory empCat = new EmployeeCategory();

        empCat.setECName(txtECategory.getText());
        // System.out.print(txtECategory.getText());
        empDAO.delete(empCat);
        empDAO.fillcmb_Employee(cmbEType);
        empDAO.getEmployeeCategoryList(listModel, Employee_Category);
        context3.close();

        //getEmployeeCategoryList(listModel, Employee_Category);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cmbEType.removeAllItems();

//        try {
//
//            String sql = "Update Employee_Category set ECName='" + txtECategory.getText() + "' where ECName='" + Employee_Category.getSelectedValue() + "'";
//
//            pst = conn.prepareStatement(sql);
//            pst.execute();
//            getEmployeeCategoryList(listModel, Employee_Category);
//            JOptionPane.showMessageDialog(null, "Category updated!");
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No option is selected!");
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
        
        System.out.print(ECName);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeCategoryDAO empDAO = (EmployeeCategoryDAO) context.getBean("EmployeeCategoryDAO");
        EmployeeCategory empCat = new EmployeeCategory();
        empCat.setECName(txtECategory.getText());
        empDAO.update(empCat, ECName);
        //   fillcmb_Employee(cmbEType);
        empDAO.fillcmb_Employee(cmbEType);
        empDAO.getEmployeeCategoryList(listModel, Employee_Category);
        // getEmployeeCategoryList(listModel, Employee_Category);
        context.close();
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (txtECategory.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty field found!");
        } else {
            cmbEType.removeAllItems();

            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            EmployeeCategoryDAO empDAO = (EmployeeCategoryDAO) context.getBean("EmployeeCategoryDAO");
            EmployeeCategory empCategory = new EmployeeCategory();
            empCategory.setECName(txtECategory.getText());
            empDAO.add(empCategory);

            empDAO.getEmployeeCategoryList(listModel, Employee_Category);
            empDAO.fillcmb_Employee(cmbEType);
            // getEmployeeCategoryList(listModel, Employee_Category);
            context.close();

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (txtEID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No Employee was Selected!");
        } else {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            EmployeeCategoryDAO empCatDAO = (EmployeeCategoryDAO) context.getBean("EmployeeCategoryDAO");
            EmployeeDAO empDAO = (EmployeeDAO) context.getBean("EmployeeDAO");
            EmployeeCategory empCat = new EmployeeCategory();
            Employee emp = new Employee();
            emp.setEID(txtEID.getText());
            empDAO.delete(emp);

            empCat.setECName((String) Employee_Category.getSelectedValue());
            empCatDAO.Update_Employee_Table_By_Category(empCat, tblEmployeeInfo);
            context.close();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            filename = f.getAbsolutePath();
            txtPath.setText(filename);

            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            person_image = bos.toByteArray();
            format = new ImageIcon(person_image);
            java.awt.Image img = format.getImage();
            java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

            format = new ImageIcon(newimg);
            //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            lblImage.setIcon(format);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        // int catID = 0;
        if (txtEID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID can't be empty.");
        } else {

            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            EmployeeCategoryDAO empDAOCat = (EmployeeCategoryDAO) context.getBean("EmployeeCategoryDAO");
            EmployeeDAO empDAO = (EmployeeDAO) context.getBean("EmployeeDAO");
            Employee emp = new Employee();
            EmployeeCategory empCat = new EmployeeCategory();
            empCat.setECName((String) cmbEType.getSelectedItem());
            emp.setEID(txtEID.getText());
            emp.setEName(txtEName.getText());
            emp.setEAge(Integer.parseInt(txtAge.getText()));
            emp.setESalary(Integer.parseInt(txtSalary.getText()));
            emp.setEImage(person_image);
            empDAO.update(emp, empCat);
            empCat.setECName((String) Employee_Category.getSelectedValue());
            empDAOCat.Update_Employee_Table_By_Category(empCat, tblEmployeeInfo);
            context.close();
        }

//            try {
//                String sql = "select ECID from Employee_Category where ECName='" + cmbEType.getSelectedItem() + "'";
//                pst = conn.prepareStatement(sql);
//                rs = pst.executeQuery();
//
//                while (rs.next()) {
//                    catID = rs.getInt(1);
//
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//
//            try {
//                String sql = "delete from Employee_Info where EID=?";
//                pst = conn.prepareStatement(sql);
//                pst.setString(1, txtEID.getText());
//                pst.execute();
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Sorry! The action can't be performed!");
//            }
//
//            try {
//                String sql = "insert into Employee_Info (EID,EName,EAge,ESalary,ECID,EImage) values(?,?,?,?,?,?)";
//                pst = conn.prepareStatement(sql);
//
//                pst.setString(1, txtEID.getText());
//
//                pst.setString(2, txtEName.getText());
//                pst.setString(3, txtAge.getText());
//                pst.setString(4, txtSalary.getText());
//                pst.setInt(5, catID);
//                //pst.setString(6,txtBloodGroup.getText());
//                pst.setBytes(6, person_image);
//
//                pst.execute();
//
//                JOptionPane.showMessageDialog(null, "Saved.");
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//            } finally {
//                try {
//                    pst.close();
//                    rs.close();
//                } catch (Exception e) {
//
//                }
//            }
//
//        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String date = ((JTextField) DCJD.getDateEditor().getUiComponent()).getText().trim();
        int catID = 0;
        if (txtEID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID can't be empty.");
        } else if (txtPath.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Image need to be added!");
        } else if (((JTextField) DCJD.getDateEditor().getUiComponent()).getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must enter joining date!");
        } else {

            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            EmployeeDAO empDAO = (EmployeeDAO) context.getBean("EmployeeDAO");
            EmployeeCategoryDAO empCatDAO = (EmployeeCategoryDAO) context.getBean("EmployeeCategoryDAO");

            Employee emp = new Employee();

            EmployeeCategory empCat = new EmployeeCategory();
            empCat.setECName((String) cmbEType.getSelectedItem());
            emp.setEID(txtEID.getText());
            emp.setEName(txtEName.getText());
            emp.setEAge(Integer.parseInt(txtAge.getText()));
            emp.setESalary(Integer.parseInt(txtSalary.getText()));
            emp.setEImage(person_image);
            emp.setJoinDate(date);
            empDAO.add(emp, empCat);
            empCat.setECName((String) Employee_Category.getSelectedValue());

            empCatDAO.Update_Employee_Table_By_Category(empCat, tblEmployeeInfo);
            context.close();

        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void tblEmployeeInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmployeeInfoKeyReleased
        int row = tblEmployeeInfo.getSelectedRow();
        String Table_click = (tblEmployeeInfo.getModel().getValueAt(row, 0).toString());
        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeDAO employeeDAO = (EmployeeDAO) context3.getBean("EmployeeDAO");

        Employee employee = new Employee();
        EmployeeCategory employeeCat = new EmployeeCategory();
        employee.setEID(Table_click);
        employeeDAO.getImageIconAndOthers(employee, employeeCat);
        txtEID.setText(employee.getEID());
        txtEName.setText(employee.getEName());
        txtAge.setText(employee.getEAge() + "");
        txtSalary.setText(employee.getESalary() + "");
        cmbEType.setSelectedItem(employeeCat.getECName());
        ((JTextField) DCJD.getDateEditor().getUiComponent()).setText(employee.getJoinDate());
        format = new ImageIcon(employee.getEImage());
        java.awt.Image img = format.getImage();
        java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

        format = new ImageIcon(newimg);
        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        lblImage.setIcon(format);
        if (employee.getPassword() == null && employee.getUsername() == null) {
            btnSignIn.setVisible(true);
        } else {
            btnSignIn.setVisible(false);
        }
        context3.close();
    }//GEN-LAST:event_tblEmployeeInfoKeyReleased

    private void tblEmployeeInfoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmployeeInfoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEmployeeInfoKeyPressed

    private void tblEmployeeInfoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeInfoMouseReleased
        int row = tblEmployeeInfo.getSelectedRow();
        String Table_click = (tblEmployeeInfo.getModel().getValueAt(row, 0).toString());
        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeDAO employeeDAO = (EmployeeDAO) context3.getBean("EmployeeDAO");

        Employee employee = new Employee();
        EmployeeCategory employeeCat = new EmployeeCategory();
        employee.setEID(Table_click);
        employeeDAO.getImageIconAndOthers(employee, employeeCat);
        txtEID.setText(employee.getEID());
        txtEName.setText(employee.getEName());
        txtAge.setText(employee.getEAge() + "");
        txtSalary.setText(employee.getESalary() + "");
        cmbEType.setSelectedItem(employeeCat.getECName());
        ((JTextField) DCJD.getDateEditor().getUiComponent()).setText(employee.getJoinDate());
        format = new ImageIcon(employee.getEImage());
        java.awt.Image img = format.getImage();
        java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

        format = new ImageIcon(newimg);
        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        lblImage.setIcon(format);
        if (employee.getPassword() == null && employee.getUsername() == null) {
            btnSignIn.setVisible(true);
        } else {
            btnSignIn.setVisible(false);
        }
        context3.close();
    }//GEN-LAST:event_tblEmployeeInfoMouseReleased

    private void tblEmployeeInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeInfoMouseClicked
        int row = tblEmployeeInfo.getSelectedRow();
        String Table_click = (tblEmployeeInfo.getModel().getValueAt(row, 0).toString());
        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeDAO employeeDAO = (EmployeeDAO) context3.getBean("EmployeeDAO");

        Employee employee = new Employee();
        EmployeeCategory employeeCat = new EmployeeCategory();
        employee.setEID(Table_click);
        employeeDAO.getImageIconAndOthers(employee, employeeCat);
        txtEID.setText(employee.getEID());
        txtEName.setText(employee.getEName());
        txtAge.setText(employee.getEAge() + "");
        txtSalary.setText(employee.getESalary() + "");
        cmbEType.setSelectedItem(employeeCat.getECName());
        ((JTextField) DCJD.getDateEditor().getUiComponent()).setText(employee.getJoinDate());
        format = new ImageIcon(employee.getEImage());
        java.awt.Image img = format.getImage();
        java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

        format = new ImageIcon(newimg);
        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        lblImage.setIcon(format);
        if (employee.getPassword() == null && employee.getUsername() == null) {
            btnSignIn.setVisible(true);
        } else {
            btnSignIn.setVisible(false);
        }
        context3.close();     // TODO add your handling code here:
    }//GEN-LAST:event_tblEmployeeInfoMouseClicked

    private void Employee_CategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Employee_CategoryKeyReleased

        
        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeCategoryDAO empDAO = (EmployeeCategoryDAO) context3.getBean("EmployeeCategoryDAO");

        EmployeeCategory empCat = new EmployeeCategory();
        empCat.setECName((String) Employee_Category.getSelectedValue());

        empDAO.Update_Employee_Table_By_Category(empCat, tblEmployeeInfo);
        context3.close();
        txtECategory.setText((String) Employee_Category.getSelectedValue());
        ECName=(String) Employee_Category.getSelectedValue();
    }//GEN-LAST:event_Employee_CategoryKeyReleased

//
//    void Last7DaysProfitReport() {
//        Last7DaysAllProductWithProfit l7bpp1 = new Last7DaysAllProductWithProfit();
//        l7bpp1.L7BestSaleProduct.clear();
//        l7bpp1.L7TBestSaleProductProfit.clear();
//        Last7DaysAllProductWithProfit l7bpp = new Last7DaysAllProductWithProfit();
//        BarChartOf7Days b7 = new BarChartOf7Days();
//        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
//
//        Document d = new Document();
//
//        try {
//            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(".\\7Dayreports\\" + s + "To" + b7.r7D + "_Profit.pdf"));
//            d.open();
//
//            //Barcode 
//            PdfContentByte CB = writer.getDirectContent();
//            BarcodeEAN codeEAN = new BarcodeEAN();
//            codeEAN.setCode("1234567891011");
//            d.add(new Paragraph(BarcodeText));
//            codeEAN.setCodeType(Barcode.UPCA);
//            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
//
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//
//            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
//            image.scaleAbsolute(550, 200);
//            image.setAlignment(Element.ALIGN_CENTER);
//
//            d.add(image);
//
//            d.add(new Paragraph(empty));
//            Paragraph pa = new Paragraph("Last 7 days Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
//            pa.setAlignment(Element.ALIGN_CENTER);
//
//            d.add(pa);
//            d.add(new Paragraph("\n"));
//
//            Paragraph pDate = new Paragraph(new Date().toString());
//            pDate.setAlignment(Element.ALIGN_CENTER);
//            d.add(pDate);
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph(empty));
//
//            d.newPage();
//            //Barcode
//            d.add(new Paragraph(BarcodeText));
//            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
//            d.add(new Paragraph("\n"));
//
//            PdfPTable table5 = new PdfPTable(3);
//
//            PdfPCell cell16 = new PdfPCell(new Paragraph("Best selling product(by Profit)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
//            cell16.setColspan(4);
//            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell16.setBackgroundColor(BaseColor.GRAY);
//            cell16.setPaddingBottom(10);
//            table5.addCell(cell16);
//
//            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table5.addCell(cellID4);
//
//            PdfPCell cell17 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table5.addCell(cell17);
//
//            PdfPCell cell18 = new PdfPCell(new Paragraph("Profit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table5.addCell(cell18);
//
//            for (int i = 0; i < l7bpp.L7BestSaleProduct.size(); i++) {
//                table5.addCell((i + 1) + "");
//                table5.addCell(l7bpp.L7BestSaleProduct.get(i));
//                table5.addCell(l7bpp.L7TBestSaleProductProfit.get(i) + "");
//
//            }
//
//            d.add(table5);
//            d.add(new Paragraph("\n"));
//
//            double Profit = 0;
//            for (int i = 0; i < l7bpp.L7TBestSaleProductProfit.size(); i++) {
//                Profit = Profit + l7bpp.L7TBestSaleProductProfit.get(i);
//            }
//            Paragraph pTP = new Paragraph("Last 7 Days Total Profit: " + Profit + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
//            pTP.setAlignment(Element.ALIGN_CENTER);
//            d.add(pTP);
//
//            d.close();
//
//        } catch (Exception e) {
//
//        }
//
//    }
//
//    void Last7DaysSoldTimesReport() {
//        Last7DaysAllProductWithSoldTimes l7bpst = new Last7DaysAllProductWithSoldTimes();
//// l7bpp.L7BestSaleProduct.clear();
//// l7bpp.L7TBestSaleProductProfit.clear();
//
//        BarChartOf7Days b7 = new BarChartOf7Days();
//        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
//
//        Document d = new Document();
//
//        try {
//            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(".\\7Dayreports\\" + s + "To" + b7.r7D + "_SoldTimes.pdf"));
//            d.open();
//
//            //Barcode 
//            PdfContentByte CB = writer.getDirectContent();
//            BarcodeEAN codeEAN = new BarcodeEAN();
//            codeEAN.setCode("1234567891011");
//            d.add(new Paragraph(BarcodeText));
//            codeEAN.setCodeType(Barcode.UPCA);
//            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
//
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//
//            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
//            image.scaleAbsolute(550, 200);
//            image.setAlignment(Element.ALIGN_CENTER);
//
//            d.add(image);
//
//            d.add(new Paragraph(empty));
//            Paragraph pa = new Paragraph("Last 7 days Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
//            pa.setAlignment(Element.ALIGN_CENTER);
//
//            d.add(pa);
//            d.add(new Paragraph("\n"));
//
//            Paragraph pDate = new Paragraph(new Date().toString());
//            pDate.setAlignment(Element.ALIGN_CENTER);
//            d.add(pDate);
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph(empty));
//
//            d.newPage();
//            //Barcode
//            d.add(new Paragraph(BarcodeText));
//            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
//            d.add(new Paragraph("\n"));
//
//            PdfPTable table4 = new PdfPTable(3);
//
//            PdfPCell cell13 = new PdfPCell(new Paragraph("Best selling product(by Total sold Times)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
//            cell13.setColspan(4);
//            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell13.setBackgroundColor(BaseColor.GRAY);
//            cell13.setPaddingBottom(10);
//            table4.addCell(cell13);
//
//            PdfPCell cellID3 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cellID3.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table4.addCell(cellID3);
//
//            PdfPCell cell14 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table4.addCell(cell14);
//
//            PdfPCell cell15 = new PdfPCell(new Paragraph("Sold Times", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table4.addCell(cell15);
//
//            for (int i = 0; i < l7bpst.L7BestSaleProduct.size(); i++) {
//                table4.addCell((i + 1) + "");
//                table4.addCell(l7bpst.L7BestSaleProduct.get(i));
//                table4.addCell(l7bpst.L7TBestSaleProductSoldTimes.get(i) + "");
//
//            }
//
//            d.add(table4);
//
//            d.add(new Paragraph("\n"));
//
//            d.close();
//
//        } catch (Exception e) {
//
//        }
//
//    }
//    void Last7DaysSoldAmountReport() {
//        Last7DaysAllPoductWithSoldAmount l7bpsa = new Last7DaysAllPoductWithSoldAmount();
//// l7bpp.L7BestSaleProduct.clear();
//// l7bpp.L7TBestSaleProductProfit.clear();
//
//        BarChartOf7Days b7 = new BarChartOf7Days();
//        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
//
//        Document d = new Document();
//
//        try {
//            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(".\\7Dayreports\\" + s + "To" + b7.r7D + "_SoldAmount.pdf"));
//            d.open();
//
//            //Barcode 
//            PdfContentByte CB = writer.getDirectContent();
//            BarcodeEAN codeEAN = new BarcodeEAN();
//            codeEAN.setCode("1234567891011");
//            d.add(new Paragraph(BarcodeText));
//            codeEAN.setCodeType(Barcode.UPCA);
//            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
//
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph("\n"));
//
//            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
//            image.scaleAbsolute(550, 200);
//            image.setAlignment(Element.ALIGN_CENTER);
//
//            d.add(image);
//
//            d.add(new Paragraph(empty));
//            Paragraph pa = new Paragraph("Last 7 days Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
//            pa.setAlignment(Element.ALIGN_CENTER);
//
//            d.add(pa);
//            d.add(new Paragraph("\n"));
//
//            Paragraph pDate = new Paragraph(new Date().toString());
//            pDate.setAlignment(Element.ALIGN_CENTER);
//            d.add(pDate);
//            d.add(new Paragraph("\n"));
//            d.add(new Paragraph(empty));
//
//            d.newPage();
//            //Barcode
//            d.add(new Paragraph(BarcodeText));
//            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
//            d.add(new Paragraph("\n"));
//
//            PdfPTable table2 = new PdfPTable(4);
//
//            PdfPCell cell5 = new PdfPCell(new Paragraph("Best selling product(by sold Amount)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
//            cell5.setColspan(4);
//            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell5.setBackgroundColor(BaseColor.GRAY);
//            cell5.setPaddingBottom(10);
//            table2.addCell(cell5);
//
//            PdfPCell cellID = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cellID.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table2.addCell(cellID);
//
//            PdfPCell cell6 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table2.addCell(cell6);
//
//            PdfPCell cell7 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table2.addCell(cell7);
//
//            PdfPCell cell8 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table2.addCell(cell8);
//
//            for (int i = 0; i < l7bpsa.L7BestSaleProduct.size(); i++) {
//                table2.addCell((i + 1) + "");
//                table2.addCell(l7bpsa.L7BestSaleProduct.get(i));
//                table2.addCell(l7bpsa.L7TBestSaleProductAmount.get(i) + "");
//                table2.addCell(l7bpsa.L7TBestSaleProductUnit.get(i));
//            }
//
//            d.add(table2);
//
//            d.close();
//
//        } catch (Exception e) {
//
//        }
//
//    }

    private void Employee_CategoryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Employee_CategoryMouseReleased

        ConfigurableApplicationContext context3 = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeCategoryDAO empDAO = (EmployeeCategoryDAO) context3.getBean("EmployeeCategoryDAO");

        EmployeeCategory empCat = new EmployeeCategory();
        empCat.setECName((String) Employee_Category.getSelectedValue());

        empDAO.Update_Employee_Table_By_Category(empCat, tblEmployeeInfo);
        context3.close();
        txtECategory.setText((String) Employee_Category.getSelectedValue());
        ECName=(String) Employee_Category.getSelectedValue();
    }//GEN-LAST:event_Employee_CategoryMouseReleased

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        //Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -30);
        dt = c.getTime();

        String r30D = dateFormat.format(dt).trim();
        LastDaysGeneralReport l = new LastDaysGeneralReport();
        l.LastDayspdfReport(r30D, ".\\30Dayreports\\" + s + "To" + r30D + ".pdf", "Last 30 days Sales report");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\30Dayreports\\" + s + "To" + r30D + ".pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -7);
        dt = c.getTime();

        String r7D = dateFormat.format(dt).trim();
        LastDaysGeneralReport l = new LastDaysGeneralReport();
        l.LastDayspdfReport(r7D, ".\\7Dayreports\\" + s + "To" + r7D + ".pdf", "Last 7 days Sales report");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\7Dayreports\\" + s + "To" + r7D + ".pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
//        dayspdfReport(s, ".\\Dayreports\\" + s + ".pdf");
//        //       openTodaysReport();
//        ReportOpen ro = new ReportOpen();
//        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\" + s + ".pdf");        

//  String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        dayspdfReport(s, ".\\Dayreports\\ SelectedDaysReport.pdf");
        //       openTodaysReport();
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\ SelectedDaysReport.pdf");

// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void pnlRingChartMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlRingChartMouseWheelMoved
        RingChart r = new RingChart(s);
        r.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_pnlRingChartMouseWheelMoved

    private void pnlLineChartMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlLineChartMouseWheelMoved
        LineChart l = new LineChart(s);
        l.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_pnlLineChartMouseWheelMoved

    private void pnlBarChartMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlBarChartMouseWheelMoved
        BarChart b = new BarChart(s);
        b.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlBarChartMouseWheelMoved

    private void pnlPieChartComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlPieChartComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartComponentResized

    private void pnlPieChartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPieChartMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartMouseReleased

    private void pnlPieChartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPieChartMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartMousePressed

    private void pnlPieChartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPieChartMouseEntered
        //        PieChart p = new PieChart();
        //        p.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartMouseEntered

    private void pnlPieChartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPieChartMouseClicked

    }//GEN-LAST:event_pnlPieChartMouseClicked

    private void pnlPieChartMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlPieChartMouseWheelMoved
        PieChart p = new PieChart(s);
        p.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartMouseWheelMoved

    private void pnlPieChartFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pnlPieChartFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartFocusGained

    private void pnlPieChartMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPieChartMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartMouseDragged

    private void pnlPieChartAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pnlPieChartAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPieChartAncestorAdded

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        individualProductReport i = new individualProductReport();
        if (txtProductName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Select a product!");
        } else {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");

            try {

                Product product = new Product();
                Category category = new Category();
                product.setPID(txtPID.getText());
                product.setPName(txtProductName.getText());
                proDAO.getValuesofIndividualProduct(product, category);

               /// i.getValues(txtPID.getText(), txtProductName.getText());
                if (product.getTotalSoldAmount() == 0.0) {
                    JOptionPane.showMessageDialog(null, "This Item hasn't been sold yet!");
                } else {
                    i.CreateReport(".\\IndividualProductReport\\report.pdf", product, category);

                    //  System.out.print(txtPID.getText());
                    ReportOpen ro = new ReportOpen();
                    ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\IndividualProductReport\\report.pdf");
                }
                context.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "This item hasn't been sold yet!");
            }

        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void cmbPCatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPCatMouseReleased

    }//GEN-LAST:event_cmbPCatMouseReleased

    private void cmbPCatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPCatMousePressed

    }//GEN-LAST:event_cmbPCatMousePressed

    private void cmbPCatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPCatMouseExited

    }//GEN-LAST:event_cmbPCatMouseExited

    private void cmbPCatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPCatMouseEntered

    }//GEN-LAST:event_cmbPCatMouseEntered

    private void cmbPCatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPCatMouseClicked

    }//GEN-LAST:event_cmbPCatMouseClicked

    private void cmbPCatPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbPCatPopupMenuWillBecomeInvisible
        // getPID(cmbPCat, txtPID);
        lstProductCategory.setSelectedValue(cmbPCat.getSelectedItem(), true);
    }//GEN-LAST:event_cmbPCatPopupMenuWillBecomeInvisible

    private void cmbPCatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPCatFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPCatFocusGained

    private void cmbPCatAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cmbPCatAncestorAdded

    }//GEN-LAST:event_cmbPCatAncestorAdded

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        if (txtProductName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product name!");
        } else if (txtPPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product price!");
        } else if (txtPAmount.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product amount!");
        } else if (cmbPUnit.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "You need to Select a product unit!");
        } else if (txtPPP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product purchase price per unit!");
        } else if (cmbSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "You need to Select a product supplier!");
        } else {

            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");
            Product product = new Product();
            Category category = new Category();
            Supplier supplier = new Supplier();

            category.setCatName((String) cmbPCat.getSelectedItem());
            supplier.setName((String) cmbSupplier.getSelectedItem());
            product.setPName(txtProductName.getText());
            product.setPID(txtPID.getText());
            product.setPPrice(Double.parseDouble(txtPPrice.getText()));
            product.setPAmount(Double.parseDouble(txtPAmount.getText()));
            product.setPurchasePricePerUnit(Double.parseDouble(txtPPP.getText()));
            product.setUnit((String) cmbPUnit.getSelectedItem());
            proDAO.update(product, category, supplier);
            proDAO.Update_Table_By_Category((String) lstProductCategory.getSelectedValue(), tblProductDetails);

//              Update_Table_By_Category(lstProductCategory, tblProductDetails);
            // getPID(cmbPCat, txtPID);
            context.close();

            if (Double.parseDouble(txtPAmount.getText()) < 1) {
                btnUpdateProduct.setEnabled(true);
            } else {
                btnUpdateProduct.setEnabled(false);

            }

//            int catID = 0;
//            int SID = 0;
//            String row = (String) cmbPCat.getSelectedItem();
//            try {
//
//                String sql = "select CatID from ProductCategory where CatName='" + row + "'";
//                pst = conn.prepareStatement(sql);
//                rs = pst.executeQuery();
//                if (rs.next()) {
//                    catID = rs.getInt("catID");
//                }
//
//            } catch (Exception e) {
//
//                JOptionPane.showMessageDialog(null, e);
//            }
//            try {
//
//                String sql = "select SID from Supplier where SName='" + cmbSupplier.getSelectedItem() + "'";
//                pst = conn.prepareStatement(sql);
//                rs = pst.executeQuery();
//                if (rs.next()) {
//                    SID = rs.getInt("SID");
//                }
//
//            } catch (Exception e) {
//
//                JOptionPane.showMessageDialog(null, e);
//            }
//            try {
//                String value1 = txtPID.getText();
//                String value2 = txtProductName.getText();
//                double value3 = Double.parseDouble(txtPPrice.getText());
//                double value4 = Double.parseDouble(txtPAmount.getText());
//                String value5 = (String) cmbPUnit.getSelectedItem();
//                String value6 = (String) cmbSupplier.getSelectedItem();
//                double value7 = Double.parseDouble(txtPPP.getText());
//                //double TPPrice=
//
//                String sql = "update Product set PName='" + value2 + "',PPrice='" + value3 + "',PAmount='" + value4 + "',Unit='" + value5 + "',"
//                        + "SID='" + SID + "',PurchasePricePerUnit='" + value7 + "',totalPurchasingPrice='" + Double.parseDouble(txtPPP.getText()) * Double.parseDouble(txtPAmount.getText()) + "' where PID='" + value1 + "'";
//                pst = conn.prepareStatement(sql);
//                pst.execute();
//                JOptionPane.showMessageDialog(null, "Updated.");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//
//            } finally {
//                try {
//                    pst.close();
//                    rs.close();
//                } catch (Exception e) {
//
//                }
            // }
        }
    }//GEN-LAST:event_btnUpdateProductActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (txtProductName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product name!");
        } else if (txtPPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product price!");
        } else if (txtPAmount.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product amount!");
        } else if (cmbPUnit.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "You need to Select a product unit!");
        } else if (txtPPP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to enter a product purchase price per unit!");
        } else if (cmbSupplier.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "You need to Select a product supplier!");
        } else if (txtImagePath.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to Add a product image!");
        } else {

            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");
            Product product = new Product();
            Category category = new Category();
            Supplier supplier = new Supplier();
            category.setCatName((String) cmbPCat.getSelectedItem());
            supplier.setName((String) cmbSupplier.getSelectedItem());
            product.setPName(txtProductName.getText());
            product.setPPrice(Double.parseDouble(txtPPrice.getText()));
            product.setPAmount(Double.parseDouble(txtPAmount.getText()));
            product.setPurchasePricePerUnit(Double.parseDouble(txtPPP.getText()));
            product.setUnit((String) cmbPUnit.getSelectedItem());
            product.setProduct_image(product_image);
            proDAO.add(product, category, supplier);
            proDAO.Update_Table_By_Category((String) lstProductCategory.getSelectedValue(), tblProductDetails);
            context.close();
            // Update_Table_By_Category(lstProductCategory, tblProductDetails);
            // getPID(cmbPCat, txtPID);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void txtPIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPIDActionPerformed

    private void tblProductDetailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductDetailsKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP);
        int row = tblProductDetails.getSelectedRow();
        String Table_click = (tblProductDetails.getModel().getValueAt(row, 0).toString());
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");
        Product product = new Product();
        Category category = new Category();
        Supplier supplier = new Supplier();
        proDAO.setProductInfoByClickingTable(Table_click, product, category, supplier);
        txtPID.setText(product.getPID());
        txtProductName.setText(product.getPName());
        cmbPCat.setSelectedItem(category.getCatName());
        txtPPrice.setText(product.getPPrice() + "");
        txtPAmount.setText(product.getPAmount() + "");
        cmbPUnit.setSelectedItem(product.getUnit());
        txtPPP.setText(product.getPurchasePricePerUnit() + "");
        cmbSupplier.setSelectedItem(supplier.getName());
        format = new ImageIcon(product.getProduct_image());
        java.awt.Image img = format.getImage();
        java.awt.Image newimg = img.getScaledInstance(134, 100, java.awt.Image.SCALE_SMOOTH);

        format = new ImageIcon(newimg);
        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        lblPImage.setIcon(format);
        if (Double.parseDouble(txtPAmount.getText()) < 1) {
            btnUpdateProduct.setEnabled(true);
        } else {
            btnUpdateProduct.setEnabled(false);

        }
        context.close();

    }//GEN-LAST:event_tblProductDetailsKeyReleased

    private void tblProductDetailsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductDetailsKeyPressed

    }//GEN-LAST:event_tblProductDetailsKeyPressed

    private void tblProductDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductDetailsMouseClicked
        int row = tblProductDetails.getSelectedRow();
        String Table_click = (tblProductDetails.getModel().getValueAt(row, 0).toString());
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");
        Product product = new Product();
        Category category = new Category();
        Supplier supplier = new Supplier();
        proDAO.setProductInfoByClickingTable(Table_click, product, category, supplier);
        txtPID.setText(product.getPID());
        txtProductName.setText(product.getPName());
        cmbPCat.setSelectedItem(category.getCatName());
        txtPPrice.setText(product.getPPrice() + "");
        txtPAmount.setText(product.getPAmount() + "");
        cmbPUnit.setSelectedItem(product.getUnit());
        txtPPP.setText(product.getPurchasePricePerUnit() + "");
        cmbSupplier.setSelectedItem(supplier.getName());
        format = new ImageIcon(product.getProduct_image());
        java.awt.Image img = format.getImage();
        java.awt.Image newimg = img.getScaledInstance(134, 100, java.awt.Image.SCALE_SMOOTH);

        format = new ImageIcon(newimg);
        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        lblPImage.setIcon(format);
        if (Double.parseDouble(txtPAmount.getText()) < 1) {
            btnUpdateProduct.setEnabled(true);
        } else {
            btnUpdateProduct.setEnabled(false);

        }
        context.close();

//        try {
//            int row = tblProductDetails.getSelectedRow();
//            String Table_click = (tblProductDetails.getModel().getValueAt(row, 0).toString());
//            String sql = "select PID,SName,PName,PPrice,PAmount,Unit,PurchasePricePerUnit,totalPurchasingPrice,CatName,Image from Product,ProductCategory,Supplier where Product.CatID=ProductCategory.CatID and Product.SID=Supplier.SID and PID='" + Table_click + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                String add1 = rs.getString("PID");
//                txtPID.setText(add1);
//
//                String add2 = rs.getString("PName");
//                txtProductName.setText(add2);
//
//                String add3 = rs.getString("CatName");
//                cmbPCat.setSelectedItem(add3);
//
//                double add4 = rs.getDouble("PPrice");
//                txtPPrice.setText(add4 + "");
//
//                double add5 = rs.getDouble("PAmount");
//                txtPAmount.setText(add5 + "");
//
//                String add6 = rs.getString("Unit");
//                cmbPUnit.setSelectedItem(add6);
//
//                double add7 = rs.getDouble("PurchasePricePerUnit");
//                txtPPP.setText(add7 + "");
//
//                cmbSupplier.setSelectedItem(rs.getString("SName"));
//
//                byte[] imagedata = rs.getBytes(10);
//
//                if (imagedata == null) {
//
//                    lblPImage.setIcon(formatFake);
//                    lblPImage.setText("No Image!");
//                } else {
//                    format = new ImageIcon(imagedata);
//                    java.awt.Image img = format.getImage();
//                    java.awt.Image newimg = img.getScaledInstance(134, 100, java.awt.Image.SCALE_SMOOTH);
//
//                    format = new ImageIcon(newimg);
//                    //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
//                    lblPImage.setIcon(format);
//                }
//
//                if (add5 < 1) {
//                    btnUpdateProduct.setEnabled(true);
//                } else {
//                    btnUpdateProduct.setEnabled(false);
//
//                }
//            }
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                rs.close();
//                pst.close();
//            } catch (Exception e) {
//
//            }
//        }

    }//GEN-LAST:event_tblProductDetailsMouseClicked

    private void lstProductCategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstProductCategoryKeyReleased
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");
        proDAO.Update_Table_By_Category((String) lstProductCategory.getSelectedValue(), tblProductDetails);
        context.close();

//        int catID = 0;
//        int a = 0;
//        String add1 = "";
//
//        String row = (String) lstProductCategory.getSelectedValue();
//        try {
//
//            String sql = "select CatID from ProductCategory where CatName='" + row + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                catID = rs.getInt("catID");
//                // System.out.print(catID);
//            }
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        }
//
//        try {
//            String sql = "Select Max(ROWID) from product where CatID='" + catID + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                add1 = rs.getString(1);
//
//                a = Integer.parseInt(add1);
//
//                //System.out.println(a);
//            }
//
//        } catch (Exception e) {
//
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//        if (add1 != null) {
//            txtPID.setText(a + 1 + "");
//            cmbPCat.setSelectedItem(row);
//        } else {
//            try {
//                String sql = "Select ROWID from product where CatID='" + catID + "' Limit 1";
//                pst = conn.prepareStatement(sql);
//                rs = pst.executeQuery();
//
//                if (rs.next()) {
//                    add1 = rs.getString(1);
//
//                    a = Integer.parseInt(add1);
//
//                    txtPID.setText(a + "");
//                    cmbPCat.setSelectedItem(row);
//                    //System.out.println(a);
//                }
//
//            } catch (Exception e) {
//
//            } finally {
//                try {
//                    pst.close();
//                    rs.close();
//                } catch (Exception e) {
//
//                }
//            }
//        }
    }//GEN-LAST:event_lstProductCategoryKeyReleased

    private void lstProductCategoryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProductCategoryMouseReleased
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        ProductDAO proDAO = (ProductDAO) context.getBean("ProductDAO");
        proDAO.Update_Table_By_Category((String) lstProductCategory.getSelectedValue(), tblProductDetails);
        context.close();

        //Update_Table_By_Category(lstProductCategory, tblProductDetails);
//        int catID = 0;
//        int a = 0;
//        String add1 = null;
//
//        String row = (String) lstProductCategory.getSelectedValue();
//        try {
//
//            String sql = "select CatID from ProductCategory where CatName='" + row + "'";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                catID = rs.getInt("catID");
//                // System.out.print(catID);
//            }
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        }
//
//        try {
//            String sql = "Select Max(ROWID) from product where CatID='" + catID + "' and AddInfo=1";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                add1 = rs.getString(1);
//
//                a = Integer.parseInt(add1);
//
//                //System.out.println(a);
//            }
//
//        } catch (Exception e) {
//
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//        if (add1 != null) {
//            txtPID.setText(a + 1 + "");
//            cmbPCat.setSelectedItem(row);
//
//        } else {
//            try {
//                String sql = "Select ROWID from product where CatID='" + catID + "' Limit 1";
//                pst = conn.prepareStatement(sql);
//                rs = pst.executeQuery();
//
//                if (rs.next()) {
//                    add1 = rs.getString(1);
//
//                    a = Integer.parseInt(add1);
//
//                    txtPID.setText(a + "");
//                    cmbPCat.setSelectedItem(row);
//                    //System.out.println(a);
//                }
//
//            } catch (Exception e) {
//
//            } finally {
//                try {
//                    pst.close();
//                    rs.close();
//                } catch (Exception e) {
//
//                }
//            }
//        }

    }//GEN-LAST:event_lstProductCategoryMouseReleased

    private void lstProductCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProductCategoryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lstProductCategoryMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (txtSName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You must select one supplier!");
        } else {
            try {
                SpecificSupplierAndProducts_Report s = new SpecificSupplierAndProducts_Report();
                int id = Integer.parseInt(lblID.getText());
                s.report(id, ".\\Supplier\\report.pdf");
                ReportOpen ro = new ReportOpen();
                ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Supplier\\report.pdf");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String s = txtSEmail.getText().trim();
        Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher m = p.matcher(s);
        if (m.find()) {
            if (txtSName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry! You need supllier name to complete this action!");
            } else if (txtSAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry! You need supllier address to complete this action!");
            } else if (txtSPhone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry! You need supllier Phone/moblie number to complete this action!");
            } else {

                ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
                SupplierDAO suppDAO = (SupplierDAO) context.getBean("SupplierDAO");
                Supplier supplier = new Supplier();
                supplier.setName(txtSName.getText());
                supplier.setAddress(txtSAddress.getText());
                supplier.setPhone(txtSPhone.getText());
                supplier.setEmail(txtSEmail.getText());
                supplier.setId(Integer.parseInt(lblID.getText()));
                suppDAO.update(supplier);
                suppDAO.getSupplierTable(tblSupplier);
                suppDAO.fillcmb_Supplier(cmbSupplier);
                context.close();

                // fillcmb_Supplier(cmbSupplier);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry!Your email address format is not ok!");
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupplierActionPerformed
        String s = txtSEmail.getText().trim();
        Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher m = p.matcher(s);
        if (m.find()) {

            if (txtSName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry! You need supllier name to complete this action!");
            } else if (txtSAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry! You need supllier address to complete this action!");
            } else if (txtSPhone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry! You need supllier Phone/moblie number to complete this action!");
            } else {
                ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
                SupplierDAO suppDAO = (SupplierDAO) context.getBean("SupplierDAO");
                Supplier supplier = new Supplier();
                supplier.setName(txtSName.getText());
                supplier.setAddress(txtSAddress.getText());
                supplier.setPhone(txtSPhone.getText());
                supplier.setEmail(txtSEmail.getText());
                suppDAO.add(supplier);
                suppDAO.getSupplierTable(tblSupplier);
                suppDAO.fillcmb_Supplier(cmbSupplier);
                context.close();
                context.close();
                txtSName.setText("");
                txtSAddress.setText("");
                txtSPhone.setText("");
                txtSEmail.setText("");
                //getSupplierTable(tblSupplier);
                //   fillcmb_Supplier(cmbSupplier);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry!Your email address format is not ok!");
        }
    }//GEN-LAST:event_btnAddSupplierActionPerformed

    private void tblSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSupplierKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP);
        try {
            int row = tblSupplier.getSelectedRow();
            String Table_click = (tblSupplier.getModel().getValueAt(row, 0).toString());
            String sql = "select *from Supplier where SName='" + Table_click + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtSName.setText(rs.getString("SName"));
                txtSAddress.setText(rs.getString("SAddress"));
                txtSPhone.setText(rs.getString("SPhone"));
                txtSEmail.setText(rs.getString("SEmail"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {

            } catch (Exception ex) {

            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }

            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblSupplierKeyReleased

    private void tblSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSupplierKeyPressed

    }//GEN-LAST:event_tblSupplierKeyPressed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        try {
            int row = tblSupplier.getSelectedRow();
            String Table_click = (tblSupplier.getModel().getValueAt(row, 0).toString());
            String sql = "select *from Supplier where SID='" + Table_click + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                lblID.setText(rs.getString("SID"));
                txtSName.setText(rs.getString("SName"));
                txtSAddress.setText(rs.getString("SAddress"));
                txtSPhone.setText(rs.getString("SPhone"));
                txtSEmail.setText(rs.getString("SEmail"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {

            } catch (Exception ex) {

            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }

            }

        }
    }//GEN-LAST:event_tblSupplierMouseClicked

    private void btnUpdateCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCategoryActionPerformed

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        CategoryDAO catDAO = (CategoryDAO) context.getBean("CategoryDAO");
        Category category = new Category();
        category.setCatName(txtCategory.getText());
        category.setCatID(Integer.parseInt(lblId.getText()));
        catDAO.update(category);
        catDAO.getProductCategoryList(listModel1, listModel_1, lstProductCategory, lstProductCategory2);
        catDAO.Update_Table(tblCategory);
        catDAO.getProductCategoryID(lblId);
        catDAO.fillProductCategorycmb(cmbPCat);
        context.close();

        //getProductCategoryList(listModel,listModel_1);
        //   Update_Table();
        // getID();
    }//GEN-LAST:event_btnUpdateCategoryActionPerformed

    private void btnCategoryAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryAddActionPerformed

        if (txtCategory.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You can't add NULL value!");
        } else {

            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            CategoryDAO catDAO = (CategoryDAO) context.getBean("CategoryDAO");
            Category category = new Category();
            category.setCatName(txtCategory.getText());
            catDAO.add(category);
            catDAO.getProductCategoryList(listModel1, listModel_1, lstProductCategory, lstProductCategory2);
            catDAO.Update_Table(tblCategory);
            catDAO.getProductCategoryID(lblId);
            catDAO.fillProductCategorycmb(cmbPCat);
            context.close();
                 // getProductCategoryList(listModel,listModel_1);

            // getProductCategoryList();
            //  Update_Table();
//            getID();
            // fillcmb();
        }
    }//GEN-LAST:event_btnCategoryAddActionPerformed

    private void tblCategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCategoryKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP);

        try {
            int row = tblCategory.getSelectedRow();
            String Table_click = (tblCategory.getModel().getValueAt(row, 0).toString());
            String sql = "select *from ProductCategory where CatID='" + Table_click + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("CatID");
                lblId.setText(add1);

                String add2 = rs.getString("CatName");
                txtCategory.setText(add2);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCategoryKeyReleased

    private void tblCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCategoryKeyPressed

    }//GEN-LAST:event_tblCategoryKeyPressed

    private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMouseClicked
        try {
            int row = tblCategory.getSelectedRow();
            String Table_click = (tblCategory.getModel().getValueAt(row, 0).toString());
            String sql = "select *from ProductCategory where CatID='" + Table_click + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("CatID");
                lblId.setText(add1);

                String add2 = rs.getString("CatName");
                txtCategory.setText(add2);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {

            } catch (Exception ex) {

            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }

            }

        }

    }//GEN-LAST:event_tblCategoryMouseClicked

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        jmAdminStatus.show(btnSettings, 0, 15);


    }//GEN-LAST:event_btnSettingsActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        MonthlyBudgetReport m = new MonthlyBudgetReport();
        m.getReport(".\\monthly.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\monthly.pdf");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (!RDB12M.isSelected() && !RDBSM.isSelected() && !RDBSD.isSelected()) {
            JOptionPane.showMessageDialog(null, "Sorry! You must select an Option.");
        } else if (RDB12M.isSelected() && !CBSP.isSelected()) {

            String date = "";
            String val = (String) lst.getSelectedValue();
            char[] c = val.toCharArray();
            for (int i = 0; i < 4; i++) {
                date = date + c[i];
            }

            String startDate = date + "-01-01".trim();
            String endDate = date + "-12-31".trim();

            YearlyReport y = new YearlyReport();
            y.pdfReport(startDate, endDate, ".\\YearlyReport\\YearlyReport.pdf", "Monthly Sales Report");

            ReportOpen ro = new ReportOpen();
            ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\YearlyReport\\YearlyReport.pdf");

        } else if (RDBSM.isSelected() && !CBSP.isSelected()) {
            // String val = (String) lst.getSelectedValue();
            int month = lst.getSelectedIndex() + 1;
            String mon = month + "";
            if (mon.length() < 2) {
                mon = "0" + mon;
            } else {
                mon = mon;
            }
            String date = "";
            String val = s;
            char[] c = val.toCharArray();
            for (int i = 0; i < 4; i++) {
                date = date + c[i];
            }

            String startDate = date + "-" + mon + "-01".trim();
            String endDate = date + "-" + mon + "-31".trim();

            MonthlyReport m = new MonthlyReport();
            m.MonthlypdfReport(startDate, endDate, ".\\MonthlyReport\\MonthlyReport.pdf", "Monthly Sales Report");
            ReportOpen ro = new ReportOpen();
            ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\MonthlyReport\\MonthlyReport.pdf");

//            MonthlyReport m = new MonthlyReport();
//            m.MonthlypdfReport(val, ".\\MonthlyReport\\MonthlyReport.pdf", "Monthly Sales Report");
//            ReportOpen ro = new ReportOpen();
//            ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\MonthlyReport\\MonthlyReport.pdf");
        } else if (RDBSD.isSelected() && !CBSP.isSelected()) {

            String val = (String) lst.getSelectedValue();
            DayReport d = new DayReport();
            d.DailypdfReport(val, ".\\DayReport\\DayReport.pdf", "Day Sales Report");

            ReportOpen ro = new ReportOpen();
            ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\DayReport\\DayReport.pdf");
        } else if (RDBSM.isSelected() && CBSP.isSelected()) {

            if (lstProductCategory2.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "You must select a value!");
            } else {
                try {
                    int row = tblProductDetails2.getSelectedRow();

                    int month = lst.getSelectedIndex() + 1;
                    String mon = month + "";
                    if (mon.length() < 2) {
                        mon = "0" + mon;
                    } else {
                        mon = mon;
                    }
                    String date = "";
                    String val = s;
                    char[] c = val.toCharArray();
                    for (int i = 0; i < 4; i++) {
                        date = date + c[i];
                    }

                    String startDate = date + "-" + mon + "-01".trim();
                    String endDate = date + "-" + mon + "-31".trim();

                    String Table_click = (tblProductDetails2.getModel().getValueAt(row, 1).toString());
                    ProductReportAgainstTime_Chapter_Month p = new ProductReportAgainstTime_Chapter_Month();

                    p.CreateReport(Table_click, startDate, endDate, ".\\ProductSReport\\ProductSReport.pdf", "PieChartOfProductProfitAgainstTime.png");
                    ReportOpen ro = new ReportOpen();
                    ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\ProductSReport\\ProductSReport.pdf");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You must select a value from the table!");
                }
            }
        } else if (RDBSD.isSelected() && CBSP.isSelected()) {

            if (lstProductCategory2.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "You must select a value!");
            } else {
                try {
                    int row = tblProductDetails2.getSelectedRow();
                    String Table_click = (tblProductDetails2.getModel().getValueAt(row, 1).toString());
                    ProductReportAgainstTime_Chapter_Day p = new ProductReportAgainstTime_Chapter_Day();
                    p.CreateReport(Table_click, lst.getSelectedValue().toString(), ".\\ProductSReport\\ProductSReportDay.pdf", "PieChartOfProductProfitAgainstTime.png");
                    ReportOpen ro = new ReportOpen();
                    ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\ProductSReport\\ProductSReportDay.pdf");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You must select a value from the table!");
                }
            }
        } else if (RDB12M.isSelected() && CBSP.isSelected()) {

            if (lstProductCategory2.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "You must select a value!");
            } else {

                String date = "";
                String val = (String) lst.getSelectedValue();
                char[] c = val.toCharArray();
                for (int i = 0; i < 4; i++) {
                    date = date + c[i];
                }

                String startDate = date + "-01-01".trim();
                String endDate = date + "-12-31".trim();
                try {
                    int row = tblProductDetails2.getSelectedRow();
                    String Table_click = (tblProductDetails2.getModel().getValueAt(row, 1).toString());
                    ProductReportAgainstTime_Chapter_Year p = new ProductReportAgainstTime_Chapter_Year();
                    p.CreateReport(Table_click, startDate, endDate, ".\\ProductSReport\\ProductSReportDay.pdf", "PieChartOfProductProfitAgainstTime_Year.png");
                    // p.CreateReport(Table_click, lst.getSelectedValue().toString(), ".\\ProductSReport\\ProductSReportDay.pdf", "PieChartOfProductProfitAgainstTime.png");
                    ReportOpen ro = new ReportOpen();
                    ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\ProductSReport\\ProductSReportDay.pdf");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You must select a value from the table!");
                }
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        PriceVsAmountVsProfitReport PAP = new PriceVsAmountVsProfitReport();
        PAP.getReport(".\\DecisionReports\\PriceVsAmountVsProfit.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\DecisionReports\\PriceVsAmountVsProfit.pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        dayspdfReport(date, ".\\Dayreports\\ SelectedDaysReport.pdf");
        //       openTodaysReport();
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\ SelectedDaysReport.pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        allProductwithProfit_Report(date, ".\\Dayreports\\SelectedDates_ProductWithProfit.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\SelectedDates_ProductWithProfit.pdf");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        allProductwithSoldAmount_Report(date, ".\\Dayreports\\SelectedDates_ProductWithSoldAmount.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\SelectedDates_ProductWithSoldAmount.pdf");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        String Sdate = ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText().trim();
        String Edate = ((JTextField) jDateChooser3.getDateEditor().getUiComponent()).getText().trim();
        ReportBetweenTwoTime_Profit r = new ReportBetweenTwoTime_Profit();
        r.pdfReport(Sdate, Edate, ".\\TwoDateReport\\TwoDateReportProfit.pdf", "Sales Report");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\TwoDateReport\\TwoDateReportProfit.pdf");
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        String Sdate = ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText().trim();

        String Edate = ((JTextField) jDateChooser3.getDateEditor().getUiComponent()).getText().trim();

        ReportBetweenTwoTime_Amount y = new ReportBetweenTwoTime_Amount();
        y.pdfReport(Sdate, Edate, ".\\TwoDateReport\\TwoDateReportAmount.pdf", "Sales Report");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\TwoDateReport\\TwoDateReportAmount.pdf");
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
//        allProductwithProfit_Report(s, ".\\Dayreports\\" + s + "_ProductWithProfit.pdf");
//
//        ReportOpen ro = new ReportOpen();
//        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\" + s + "_ProductWithProfit.pdf");

        //   String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        allProductwithProfit_Report(s, ".\\Dayreports\\SelectedDates_ProductWithProfit.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\SelectedDates_ProductWithProfit.pdf");
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        allProductwithSoldTimes_Report(s);

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\" + s + "_ProductWithTimes.pdf");
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
//        allProductwithSoldAmount_Report(s, ".\\Dayreports\\" + s + "_ProductWithSoldAmount.pdf");
//
//        ReportOpen ro = new ReportOpen();
//        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\" + s + "_ProductWithSoldAmount.pdf");

        //String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        allProductwithSoldAmount_Report(s, ".\\Dayreports\\SelectedDates_ProductWithSoldAmount.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\SelectedDates_ProductWithSoldAmount.pdf");
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -7);
        dt = c.getTime();

        String dateString = dateFormat.format(dt).trim();

        LastDaysReportOfProfit l = new LastDaysReportOfProfit();
        l.LastDaysProfitReport(dateString, ".\\7Dayreports\\" + s + "To" + dateString + "_Profit.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\7Dayreports\\" + s + "To" + dateString + "_Profit.pdf");
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -7);
        dt = c.getTime();

        String r7D = dateFormat.format(dt).trim();

        LastDaysReportOfSoldTimes l = new LastDaysReportOfSoldTimes();
        l.LastDaysSoldTimesReport(r7D, ".\\7Dayreports\\" + s + "To" + r7D + "_SoldTimes.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\7Dayreports\\" + s + "To" + r7D + "_SoldTimes.pdf");
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -7);
        dt = c.getTime();

        String r7D = dateFormat.format(dt).trim();

        LastDaysReportOfSoldAmount l = new LastDaysReportOfSoldAmount();
        l.LastDaysSoldAmountReport(r7D, ".\\7Dayreports\\" + s + "To" + r7D + "_SoldAmount.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\7Dayreports\\" + s + "To" + r7D + "_SoldAmount.pdf");

    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -30);
        dt = c.getTime();

        String dateString = dateFormat.format(dt).trim();

        LastDaysReportOfProfit l = new LastDaysReportOfProfit();
        l.LastDaysProfitReport(dateString, ".\\30Dayreports\\" + s + "To" + dateString + "_Profit.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\30Dayreports\\" + s + "To" + dateString + "_Profit.pdf");
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -30);
        dt = c.getTime();

        String r30D = dateFormat.format(dt).trim();

        LastDaysReportOfSoldTimes l = new LastDaysReportOfSoldTimes();
        l.LastDaysSoldTimesReport(r30D, ".\\30Dayreports\\" + s + "To" + r30D + "_SoldTimes.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\30Dayreports\\" + s + "To" + r30D + "_SoldTimes.pdf");
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -30);
        dt = c.getTime();

        String r30D = dateFormat.format(dt).trim();

        LastDaysReportOfSoldAmount l = new LastDaysReportOfSoldAmount();
        l.LastDaysSoldAmountReport(r30D, ".\\30Dayreports\\" + s + "To" + r30D + "_SoldAmount.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\30Dayreports\\" + s + "To" + r30D + "_SoldAmount.pdf");
    }//GEN-LAST:event_jButton35ActionPerformed

    private void txtECategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtECategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtECategoryActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
//        AllProductStockInfo a = new AllProductStockInfo();
//        a.createReport(".\\StockReport.pdf");

        ProductStockInfoReport psr = new ProductStockInfoReport();
        psr.createReport(".\\StockReport.pdf");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\StockReport.pdf");
    }//GEN-LAST:event_jButton36ActionPerformed

    private void btnPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPRActionPerformed

        int x = btnPR.getWidth();
        int y = btnPR.getHeight();
        jmEP.show(btnPR, x / 2, y / 2);


    }//GEN-LAST:event_btnPRActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        LastFiveYearsReport l = new LastFiveYearsReport();
        l.createReport(".\\LastFiveYearsReport.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\LastFiveYearsReport.pdf");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tblEmpAttendenceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpAttendenceMouseReleased
        if (tblEmpAttendence.getSelectedRowCount() != 0) {
            if (evt.isPopupTrigger()) {
                jmEmployeeStatus.show(tblEmpAttendence, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tblEmpAttendenceMouseReleased

    private void PresentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PresentActionPerformed
        try {
            int row = tblEmpAttendence.getSelectedRow();
            String Table_click = (tblEmpAttendence.getModel().getValueAt(row, 0).toString());
            String sql = "update EmployeeAttendence set status='P' where EID='" + Table_click + "' and date='" + d.s + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            setValuesToTheEmployeeAttendenceJTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
        }
    }//GEN-LAST:event_PresentActionPerformed

    private void tblEmpAttendenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpAttendenceMouseClicked
        if (evt.isPopupTrigger()) {
            jmEmployeeStatus.show(tblEmpAttendence, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblEmpAttendenceMouseClicked

    private void tblEmpAttendenceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpAttendenceMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEmpAttendenceMousePressed

    private void AbsentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbsentActionPerformed
        try {
            int row = tblEmpAttendence.getSelectedRow();
            String Table_click = (tblEmpAttendence.getModel().getValueAt(row, 0).toString());
            String sql = "update EmployeeAttendence set status='A' where EID='" + Table_click + "' and date='" + d.s + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            setValuesToTheEmployeeAttendenceJTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
        }
    }//GEN-LAST:event_AbsentActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed

        insertAllEmployeeWithTodaysDateIntoEmployeeAttendenceTable();
        setValuesToTheEmployeeAttendenceJTable();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            this.dispose();
            conn.close();
            pst.close();
            rs.close();
            LoginForm l = new LoginForm();
            l.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dayspdfReport(s, ".\\Dayreports\\" + s + ".pdf");
        //       openTodaysReport();
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\Dayreports\\" + s + ".pdf");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        LastFiveYearsReport l = new LastFiveYearsReport();
        l.createReport(".\\LastFiveYearsReport.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\LastFiveYearsReport.pdf");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String Month = "";
        String val = dc.s;

        char[] c1 = val.toCharArray();
        for (int i = 5; i < 7; i++) {
            Month = Month + c1[i];
        }

        char[] c2 = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c2[i];
        }

        String startDate = date + "-" + Month + "-01".trim();
        String endDate = date + "-" + Month + "-31".trim();
        System.out.print(startDate + " " + endDate);
        EmployeePerformanceReport e = new EmployeePerformanceReport();
        e.Reprot(".\\EmployeePerformanceReport_Monthly.pdf", startDate, endDate, "EmployeePerformance_MonthReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeePerformanceReport_Monthly.pdf");
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        MonthlyBudgetReport m = new MonthlyBudgetReport();
        m.getReport(".\\monthly.pdf");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\monthly.pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -7);
        dt = c.getTime();

        String r7D = dateFormat.format(dt).trim();
        LastDaysGeneralReport l = new LastDaysGeneralReport();
        l.LastDayspdfReport(r7D, ".\\7Dayreports\\" + s + "To" + r7D + ".pdf", "Last 7 days Sales report");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\7Dayreports\\" + s + "To" + r7D + ".pdf");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        //Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -30);
        dt = c.getTime();

        String r30D = dateFormat.format(dt).trim();
        LastDaysGeneralReport l = new LastDaysGeneralReport();
        l.LastDayspdfReport(r30D, ".\\30Dayreports\\" + s + "To" + r30D + ".pdf", "Last 30 days Sales report");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\30Dayreports\\" + s + "To" + r30D + ".pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        ProductStockInfoReport psr = new ProductStockInfoReport();
        psr.createReport(".\\StockReport.pdf");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\StockReport.pdf");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignInActionPerformed

        java.awt.Image img = format.getImage();
        java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

        format = new ImageIcon(newimg);

        String EID = txtEID.getText();
        String EName = txtEName.getText();

        EmployeeSignIn esi = new EmployeeSignIn();
        esi.setVisible(true);
        esi.lblEID.setText(EID);
        esi.lblEName.setText(EName);

        //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        esi.lblImage.setIcon(format);

        esi.jButton1.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "Select username from Employee_Info where username='" + esi.txtUN.getText().trim() + "'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        count = count + 1;
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error!");
                } finally {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error!");
                    }
                }

                if (esi.txtUN.getText().equals("") || esi.txtPSD.getText().equals("") || esi.txtPSDConfirm.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty field found!");
                } else if (count > 0) {
                    JOptionPane.showMessageDialog(null, "User name already exists!");
                    esi.txtUN.setText("");
                    count = 0;
                } else if (!esi.txtPSD.getText().equals(esi.txtPSDConfirm.getText())) {
                    JOptionPane.showMessageDialog(null, "Passwords not matched!!");
                    esi.txtPSD.setText("");
                    esi.txtPSDConfirm.setText("");
                } else if (esi.txtPSD.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Passwords must contain at least 8 character!");
                } else {
                    try {
                        String UN = esi.txtUN.getText();
                        String PSD = esi.txtPSD.getText();

                        String sql = "update Employee_Info set Password='" + PSD + "',Username='" + UN + "' where EID='" + esi.lblEID.getText().trim() + "'";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Now You're signed.");
                        //  this.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error!" + ex.getMessage());
                    } finally {
                        try {
                            pst.close();
                            rs.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error!" + ex.getMessage());
                        }
                    }

                }

            }
        });
    }//GEN-LAST:event_btnSignInActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        ChangePasswordOfAdmin cpa = new ChangePasswordOfAdmin();
        cpa.setVisible(true);
        String un = null, psd = null;
        try {
            String sql = "Select Username,password from user";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                un = rs.getString("UserName");

                cpa.lblName.setText(un);
                psd = rs.getString("Password");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR." + ex.getMessage());
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception ev) {
                JOptionPane.showMessageDialog(null, "ERROR." + ev.getMessage());
            }
        }
        cpa.txtUN.setText(cpa.lblName.getText());
        cpa.jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String Pass = null;
                try {
                    String sql = "Select password from user where Username='" + cpa.lblName.getText().trim() + "'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs.next()) {

                        Pass = rs.getString("Password");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR." + ex.getMessage());
                } finally {
                    try {
                        rs.close();
                        pst.close();
                    } catch (Exception ev) {
                        JOptionPane.showMessageDialog(null, "ERROR." + ev.getMessage());
                    }
                }

                if (cpa.txtUN.getText().equals("") || cpa.txtPSD.getText().equals("") || cpa.txtNPSDConfirm.getText().equals("") || cpa.txtNPSD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty field found!");
                } else if (!cpa.txtPSD.getText().equals(Pass)) {
                    JOptionPane.showMessageDialog(null, "Passwords not matched!!");
                } else if (!cpa.txtNPSD.getText().equals(cpa.txtNPSDConfirm.getText())) {
                    JOptionPane.showMessageDialog(null, "New Password and Confirm Password must be same!");
                } else if (cpa.txtNPSD.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Passwords must contain at least 8 character!");
                } else {
                    try {
                        String sql = "Update User set Username='" + cpa.txtUN.getText() + "',password='" + cpa.txtNPSD.getText() + "' where username='" + cpa.lblName.getText().trim() + "'";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Change has been made!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "ERROR." + ex.getMessage());
                    } finally {
                        try {
                            pst.close();
                            rs.close();
                        } catch (Exception ev) {
                            JOptionPane.showMessageDialog(null, "ERROR." + ev.getMessage());
                        }
                    }
                }
            }

        });

    }//GEN-LAST:event_jButton37ActionPerformed

    private void txtPAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAmountActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        SetDatabaseNewForm sdnf = new SetDatabaseNewForm();
        sdnf.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void changePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassActionPerformed
        ChangePasswordOfAdmin cpa = new ChangePasswordOfAdmin();
        cpa.setVisible(true);
        String un = null, psd = null;
        try {
            String sql = "Select Username,password from user";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                un = rs.getString("UserName");

                cpa.lblName.setText(un);
                psd = rs.getString("Password");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR." + ex.getMessage());
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception ev) {
                JOptionPane.showMessageDialog(null, "ERROR." + ev.getMessage());
            }
        }
        cpa.txtUN.setText(cpa.lblName.getText());
        cpa.jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String Pass = null;
                try {
                    String sql = "Select password from user where Username='" + cpa.lblName.getText().trim() + "'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs.next()) {

                        Pass = rs.getString("Password");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR." + ex.getMessage());
                } finally {
                    try {
                        rs.close();
                        pst.close();
                    } catch (Exception ev) {
                        JOptionPane.showMessageDialog(null, "ERROR." + ev.getMessage());
                    }
                }

                if (cpa.txtUN.getText().equals("") || cpa.txtPSD.getText().equals("") || cpa.txtNPSDConfirm.getText().equals("") || cpa.txtNPSD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty field found!");
                } else if (!cpa.txtPSD.getText().equals(Pass)) {
                    JOptionPane.showMessageDialog(null, "Passwords not matched!!");
                } else if (!cpa.txtNPSD.getText().equals(cpa.txtNPSDConfirm.getText())) {
                    JOptionPane.showMessageDialog(null, "New Password and Confirm Password must be same!");
                } else if (cpa.txtNPSD.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Passwords must contain at least 8 character!");
                } else {
                    try {
                        String sql = "Update User set Username='" + cpa.txtUN.getText() + "',password='" + cpa.txtNPSD.getText() + "' where username='" + cpa.lblName.getText().trim() + "'";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Change has been made!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "ERROR." + ex.getMessage());
                    } finally {
                        try {
                            pst.close();
                            rs.close();
                        } catch (Exception ev) {
                            JOptionPane.showMessageDialog(null, "ERROR." + ev.getMessage());
                        }
                    }
                }
            }

        });
    }//GEN-LAST:event_changePassActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        try {
            this.dispose();
            conn.close();
            pst.close();
            rs.close();
            LoginForm l = new LoginForm();
            l.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_logOutActionPerformed

    private void cmbETypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbETypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbETypeActionPerformed

    private void MonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthlyActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String Month = "";
        String val = dc.s;

        char[] c1 = val.toCharArray();
        for (int i = 5; i < 7; i++) {
            Month = Month + c1[i];
        }

        char[] c2 = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c2[i];
        }

        String startDate = date + "-" + Month + "-01".trim();
        String endDate = date + "-" + Month + "-31".trim();

        EmployeePerformanceReport e = new EmployeePerformanceReport();
        e.Reprot(".\\EmployeePerformanceReport_Monthly.pdf", startDate, endDate, "EmployeePerformance_MonthReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeePerformanceReport_Monthly.pdf");

    }//GEN-LAST:event_MonthlyActionPerformed

    private void YearlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearlyActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String val = dc.s;
        char[] c = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c[i];
        }

        String startDate = date + "-01-01".trim();
        String endDate = date + "-12-31".trim();

        EmployeePerformanceReport e = new EmployeePerformanceReport();
        e.Reprot(".\\EmployeePerformance_YearReport.pdf", startDate, endDate, "EmployeePerformance_YearReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeePerformance_YearReport.pdf");


    }//GEN-LAST:event_YearlyActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String val = dc.s;;
        char[] c = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c[i];
        }

        String startDate = date + "-01-01".trim();
        String endDate = date + "-12-31".trim();

        EmployeePerformanceReport e = new EmployeePerformanceReport();
        e.Reprot(".\\EmployeePerformance_YearReport.pdf", startDate, endDate, "EmployeePerformance_YearReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeePerformance_YearReport.pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        String Sdate = ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText().trim();

        String Edate = ((JTextField) jDateChooser3.getDateEditor().getUiComponent()).getText().trim();

        ReportBetweenTwoTimes y = new ReportBetweenTwoTimes();
        y.pdfReport(Sdate, Edate, ".\\TwoDateReport\\TwoDateReport.pdf", "Sales Report");

        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\TwoDateReport\\TwoDateReport.pdf");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void txtPPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPPPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPPPActionPerformed

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void txtPPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPPriceActionPerformed

    private void cmbPUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPUnitActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            filename = f.getAbsolutePath();
            txtImagePath.setText(filename);

            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            product_image = bos.toByteArray();
            format = new ImageIcon(product_image);
            java.awt.Image img = format.getImage();
            java.awt.Image newimg = img.getScaledInstance(134, 100, java.awt.Image.SCALE_SMOOTH);

            format = new ImageIcon(newimg);
            //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            lblPImage.setIcon(format);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\About.html");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void btnSettingsMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_btnSettingsMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSettingsMouseWheelMoved

    private void btnSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsMouseEntered
        jmAdminStatus.show(btnSettings, 0, 15);            // TODO add your handling code here:
    }//GEN-LAST:event_btnSettingsMouseEntered

    private void tblEmpAttendenceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpAttendenceMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEmpAttendenceMouseEntered

    private void btnPRMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPRMouseEntered

        int x = btnPR.getWidth();
        int y = btnPR.getHeight();
        jmEP.show(btnPR, x / 2, y / 2);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnPRMouseEntered

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void txtImagePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImagePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagePathActionPerformed

    void getBest8Product(String date) {
        //ArrayList< java.awt.Image>img=new ArrayList();

        JLabel[] lblBP = {lblBP1, lblBP2, lblBP3, lblBP4, lblBP5, lblBP6, lblBP7, lblBP8};
        ArrayList<JLabel> lblBPArray = new ArrayList();
        ImageIcon imgFake = null;
        for (int i = 0; i < lblBP.length; i++) {
            lblBPArray.add(i, lblBP[i]);
        }

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        getBestEightProductIcon BEF = (getBestEightProductIcon) context.getBean("getBestEightProductIcon");
        BEF.getImageIcons(date);
//        getBestEightProductIcon BEP = new getBestEightProductIcon();
//        BEP.getImageIcons(date);
        try {
            for (int i = 0; i < 8; i++) {

                //  img.add(BEP.format.get(i).getImage());
                if (BEF.format.get(i).equals(null)) {
                    lblBPArray.get(i).setIcon(imgFake);
                    //System.out.print("No");
                    //lblBPArray.get(i).setText("No Image");
                } else {
                    java.awt.Image img = BEF.format.get(i).getImage();
                    lblBPArray.get(i).setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(152, 114, java.awt.Image.SCALE_DEFAULT)));
                    //     System.out.print("Yes");
                }
            }
        } catch (Exception e) {

        }
        context.close();
    }
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //  jTabbedPane6.setVisible(true);     

        if (count == 0) {
            tpBPImage.setVisible(true);
            getBest8Product(s);
            count = count + 1;

        } else if (count == 1) {
            tpBPImage.setVisible(false);
            count = 0;
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
        if (count == 0) {
            tpBPImage.setVisible(true);
            getBest8Product(s);
            count = count + 1;

        } else if (count == 1) {
            tpBPImage.setVisible(false);
            count = 0;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseEntered

    private void btnARMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnARMouseEntered
        int x = btnAR.getWidth();
        int y = btnAR.getHeight();
        jmEA.show(btnAR, x / 2, y / 2);        // TODO add your handling code here:
    }//GEN-LAST:event_btnARMouseEntered

    private void btnARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnARActionPerformed
        int x = btnAR.getWidth();
        int y = btnAR.getHeight();
        jmEA.show(btnAR, x / 2, y / 2);          // TODO add your handling code here:
    }//GEN-LAST:event_btnARActionPerformed

    private void MonthlyARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthlyARActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String Month = "";
        String val = dc.s;

        char[] c1 = val.toCharArray();
        for (int i = 5; i < 7; i++) {
            Month = Month + c1[i];
        }

        char[] c2 = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c2[i];
        }

        String startDate = date + "-" + Month + "-01".trim();
        String endDate = date + "-" + Month + "-31".trim();
        
        //System.out.print(startDate+" "+endDate);

        AttendenceReport a = new AttendenceReport();
        a.Reprot(".\\EmployeeAttendenceReport_Monthly.pdf", startDate, endDate, "EmployeeAttendence_MonthReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeeAttendenceReport_Monthly.pdf");

    }//GEN-LAST:event_MonthlyARActionPerformed

    private void YearlyARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearlyARActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String val = dc.s;
        char[] c = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c[i];
        }

        String startDate = date + "-01-01".trim();
        String endDate = date + "-12-31".trim();

        AttendenceReport a = new AttendenceReport();
        a.Reprot(".\\EmployeeAttendence_YearReport.pdf", startDate, endDate, "EmployeeAttendence_YearReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeeAttendence_YearReport.pdf");        // TODO add your handling code here:
    }//GEN-LAST:event_YearlyARActionPerformed

    private void btnPRMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPRMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPRMouseMoved

    private void btnPRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPRMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPRMouseReleased

    private void btnPRFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnPRFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPRFocusLost

    private void jPanel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseEntered
        jmEP.setVisible(false);
        jmEA.setVisible(false);

        jmAdminStatus.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel24MouseEntered

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        jmAdminStatus.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jTabbedPane3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseEntered
        jmAdminStatus.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane3MouseEntered

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String Month = "";
        String val = dc.s;

        char[] c1 = val.toCharArray();
        for (int i = 5; i < 7; i++) {
            Month = Month + c1[i];
        }

        char[] c2 = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c2[i];
        }

        String startDate = date + "-" + Month + "-01".trim();
        String endDate = date + "-" + Month + "-31".trim();

        AttendenceReport a = new AttendenceReport();
        a.Reprot(".\\EmployeeAttendenceReport_Monthly.pdf", startDate, endDate, "EmployeeAttendence_MonthReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeeAttendenceReport_Monthly.pdf");
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        DateChange dc = new DateChange();
        String date = "";
        String val = dc.s;
        char[] c = val.toCharArray();
        for (int i = 0; i < 4; i++) {
            date = date + c[i];
        }

        String startDate = date + "-01-01".trim();
        String endDate = date + "-12-31".trim();

        AttendenceReport a = new AttendenceReport();
        a.Reprot(".\\EmployeeAttendence_YearReport.pdf", startDate, endDate, "EmployeeAttendence_YearReport.png");
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\EmployeeAttendence_YearReport.pdf");          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jTabbedPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseEntered
        jmAdminStatus.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseEntered

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        ReportOpen ro = new ReportOpen();
        ro.OpenReport("rundll32 url.dll,FileProtocolHandler " + ".\\HelpAdmin.html");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jTabbedPane2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseEntered
        jmAdminStatus.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseEntered

    private void txtCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryActionPerformed

    public void getMonthsORDays(String[] str) {
        listModel3.removeAllElements();
        for (int i = 0; i < str.length; i++) {
            listModel3.add(i, str[i]);
        }

    }

//    public void getSID(JTable tbl) {
//        try {
//            String sql = "select max(SID) from Supplier";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            if (rs.next()) {
//                String add1 = rs.getString(1);
//
//                int a = Integer.parseInt(add1);
//
//                lblID.setText(a + 1 + "");
//
//            }
//
//        } catch (Exception e) {
//
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
//        SupplierDAO suppDAO = (SupplierDAO) context.getBean("SupplierDAO");
//
//        suppDAO.getSupplierTable(tblSupplier);
//        context.close();
//    }
//    public void getSupplierTable(JTable tbl) {
//        try {
//            String sql = "SELECT SID as ID,SName as Name,SAddress as Address,SPhone as Phone, SEmail as Email from Supplier";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            tbl.setModel(DbUtils.resultSetToTableModel(rs));
//
//        } catch (Exception e) {
//
//            JOptionPane.showMessageDialog(null, e);
//        } finally {
//            try {
//                pst.close();
//                rs.close();
//            } catch (Exception e) {
//
//            }
//        }
//    }
    private void getdaysBest15ProductbySoldAmount(String dateString) {
        TBestSaleProductAmount.clear();
        TBestSaleProduct.clear();
        TBestSaleProductUnit.clear();
        String TodaysBestProductId = null;

        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT SUM(Amount),Unit,PName FROM  Customer,Product where Customer.PID=Product.PID and Date ='" + dateString + "' \n"
                    + "GROUP BY Customer.PID ORDER BY SUM(Amount) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                //TodaysBestProductId = rs.getString("PID");
                TodaysBestProductAmount = rs.getDouble("SUM(Amount)");
                TBestSaleProductAmount.add(TodaysBestProductAmount);

                // TBestSaleProductAmount2.add(TodaysBestProductAmount);
                TodaysBestProduct = rs.getString("PName");
                TBestSaleProduct.add(TodaysBestProduct);
                //TBestSaleProduct2.add(TodaysBestProduct);

                TodaysBestProductUnit = rs.getString("Unit");
                TBestSaleProductUnit.add(TodaysBestProductUnit);
                //  TBestSaleProductUnit2.add(TodaysBestProductUnit);
                // cmb1.addItem(TBestSaleProduct);
            }

            double[] d = new double[15];
            String[] s1 = new String[15];
            String[] s2 = new String[15];

            for (int i = 0; i < d.length; i++) {
                TBestSaleProductAmount.add(d[i]);
                TBestSaleProduct.add(s1[i]);
                TBestSaleProductUnit.add(s2[i]);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
    }

    private void getdaysBestProduct(String dateString) {
        TBestSaleProductAmount2.clear();
        TBestSaleProduct2.clear();
        TBestSaleProductUnit2.clear();
        String TodaysBestProductId = null;

        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT SUM(Amount),Unit,PName FROM  Customer,Product where Customer.PID=Product.PID and Date ='" + dateString + "' \n"
                    + "GROUP BY Customer.PID ORDER BY SUM(Amount) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                //TodaysBestProductId = rs.getString("PID");
                TodaysBestProductAmount = rs.getDouble("SUM(Amount)");
                //TBestSaleProductAmount.add(TodaysBestProductAmount);

                TBestSaleProductAmount2.add(TodaysBestProductAmount);

                TodaysBestProduct = rs.getString("PName");
                //TBestSaleProduct.add(TodaysBestProduct);
                TBestSaleProduct2.add(TodaysBestProduct);

                TodaysBestProductUnit = rs.getString("Unit");
                //TBestSaleProductUnit.add(TodaysBestProductUnit);
                TBestSaleProductUnit2.add(TodaysBestProductUnit);
                // cmb1.addItem(TBestSaleProduct);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

    private void getdaysBest15ProductByPrice(String dateString) {
        TBestSaleProductTotalPrice.clear();
        TBestSaleProductByPrice.clear();
        TBestSaleProductByPriceUnit.clear();
        String TodaysBestProductId = null;

        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT SUM(TotalPrice),Unit,PName FROM  Customer,Product where Customer.PID=Product.PID and Date ='" + dateString + "' \n"
                    + "GROUP BY Customer.PID ORDER BY SUM(TotalPrice) DESC ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                //TodaysBestProductId = rs.getString("PID");
                TodaysBestProductTotalPrice = rs.getDouble("SUM(TotalPrice)");
                TBestSaleProductTotalPrice.add(TodaysBestProductTotalPrice);

                TodaysBestProductByPrice = rs.getString("PName");
                TBestSaleProductByPrice.add(TodaysBestProductByPrice);

                TodaysBestProductByPriceUnit = rs.getString("Unit");
                TBestSaleProductByPriceUnit.add(TodaysBestProductByPriceUnit);
                // cmb1.addItem(TBestSaleProduct);
            }

            double[] d = new double[15];
            String[] s1 = new String[15];
            String[] s2 = new String[15];

            for (int i = 0; i < d.length; i++) {
                TBestSaleProductTotalPrice.add(d[i]);
                TBestSaleProductByPrice.add(s1[i]);
                TBestSaleProductByPriceUnit.add(s2[i]);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

    private void getdaysBest15ProductsBySaleNumber(String dateString) {
        TBestSaleProductBySaleNumber.clear();
        TBestSaleProductTotalSaleTime.clear();
        String TodaysBestProductId = null;

        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT PName,Count(Customer.PID)\n"
                    + " FROM  Customer,Product where Customer.PID=Product.PID and Date ='" + dateString + "'\n"
                    + " GROUP BY  Customer.PID\n"
                    + "    ORDER BY COUNT(*) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                TodaysBestProductBySaleNumber = rs.getString("PName");
                TBestSaleProductBySaleNumber.add(TodaysBestProductBySaleNumber);
                //TBestSaleProductBySaleNumber2.add(TodaysBestProductBySaleNumber);

                TodaysBestProductTotalSale = rs.getInt("Count(Customer.PID)");
                TBestSaleProductTotalSaleTime.add(TodaysBestProductTotalSale);
                //  TBestSaleProductTotalSaleTime2.add(TodaysBestProductTotalSale);
                // cmb1.addItem(TBestSaleProduct);
            }

            int[] d = new int[200];
            String[] s1 = new String[200];

            for (int i = 0; i < d.length; i++) {
                TBestSaleProductTotalSaleTime.add(d[i]);
                TBestSaleProductBySaleNumber.add(s1[i]);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

    private void getdaysBestProductBySaleNumber1(String dateString) {

        String TodaysBestProductId = null;
        TBestSaleProductBySaleNumber2.clear();
        TBestSaleProductTotalSaleTime2.clear();

        try {
//            String sql = "SELECT PID,SUM(Amount) FROM  Customer where  Date ='"+s+"'\n" +
//"GROUP BY  PID ORDER BY SUM(Amount) DESC LIMIT 5";

            String sql = "SELECT PName,Count(Customer.PID)\n"
                    + " FROM  Customer,Product where Customer.PID=Product.PID and Date ='" + dateString + "'\n"
                    + " GROUP BY  Customer.PID\n"
                    + "    ORDER BY COUNT(*) DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                TodaysBestProductBySaleNumber = rs.getString("PName");
                //TBestSaleProductBySaleNumber.add(TodaysBestProductBySaleNumber);
                TBestSaleProductBySaleNumber2.add(TodaysBestProductBySaleNumber);

                TodaysBestProductTotalSale = rs.getInt("Count(Customer.PID)");
                //TBestSaleProductTotalSaleTime.add(TodaysBestProductTotalSale);
                TBestSaleProductTotalSaleTime2.add(TodaysBestProductTotalSale);
                // cmb1.addItem(TBestSaleProduct);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

    private void getdaysBest15ProductsByProfit(String dateString) {
        TBestSaleProductByProfit.clear();
        TBestSaleProductTotalProfit.clear();
        String TodaysBestProductId = null;

        try {

            String sql = "SELECT PName,SUM(Amount),PPrice,SUM(TotalPrice),SUM(Customer.profit) from Customer,Product  "
                    + "where Product.PID=Customer.PID and Date='" + dateString + "' and Customer.profit>0 GROUP BY  Customer.PID ORDER BY Customer.profit DESC ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                TodaysBestProductByProfit = rs.getString("PName");
                TBestSaleProductByProfit.add(TodaysBestProductByProfit);
                //  TBestSaleProductByProfit2.add(TodaysBestProductByProfit);
                TodaysBestProductTotalProfit = rs.getDouble("SUM(Customer.profit)");
                TBestSaleProductTotalProfit.add(TodaysBestProductTotalProfit);
                //TBestSaleProductTotalProfit2.add(TodaysBestProductTotalProfit);
                // cmb1.addItem(TBestSaleProduct);
            }

            double[] d = new double[15];
            String[] s1 = new String[15];

            for (int i = 0; i < d.length; i++) {
                TBestSaleProductTotalProfit.add(d[i]);
                TBestSaleProductByProfit.add(s1[i]);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong! May be you haven't sold 5 different items today");
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

    private void getdaysBestProductByProfit(String dateString) {

        TBestSaleProductByProfit2.clear();
        TBestSaleProductTotalProfit2.clear();;
        String TodaysBestProductId = null;

        try {

            String sql = "SELECT PName,SUM(Amount),PPrice,SUM(TotalPrice),SUM(Customer.Profit)  from Customer,Product  "
                    + "where Product.PID=Customer.PID and Date='" + dateString + "' and Customer.profit>0 GROUP BY  Customer.PID ORDER BY Customer.profit DESC";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                TodaysBestProductByProfit = rs.getString("PName");
                // TBestSaleProductByProfit.add(TodaysBestProductByProfit);
                TBestSaleProductByProfit2.add(TodaysBestProductByProfit);
                TodaysBestProductTotalProfit = rs.getDouble("SUM(Customer.Profit)");
                //TBestSaleProductTotalProfit.add(TodaysBestProductTotalProfit);
                TBestSaleProductTotalProfit2.add(TodaysBestProductTotalProfit);
                // cmb1.addItem(TBestSaleProduct);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error." + e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }

    public void allProductwithProfit_Report(String dateString, String URL) {
        getdaysBestProductByProfit(dateString);
        TodaysLossyProducts tlp = new TodaysLossyProducts();
        tlp.getInfo(dateString);
        Document d = new Document();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
        try {

            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(URL));
            d.open();

            //Barcode 
            PdfContentByte CB = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCode("1234567891011");
            d.add(new Paragraph(BarcodeText));
            codeEAN.setCodeType(Barcode.UPCA);
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
            image.scaleAbsolute(550, 200);
            image.setAlignment(Element.ALIGN_CENTER);

            d.add(image);

            d.add(new Paragraph(empty));
            Paragraph pa = new Paragraph("Todays Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));

            Paragraph pDate = new Paragraph(new Date().toString());
            pDate.setAlignment(Element.ALIGN_CENTER);
            d.add(pDate);
            d.add(new Paragraph("\n"));
            d.add(new Paragraph(empty));

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            PdfPTable tableP = new PdfPTable(3);

            PdfPCell cell16 = new PdfPCell(new Paragraph("All Sold Product With Profit)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell16.setColspan(4);
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setBackgroundColor(BaseColor.GRAY);
            cell16.setPaddingBottom(10);
            tableP.addCell(cell16);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Profit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell18);

            for (int i = 0; i < TBestSaleProductByProfit2.size(); i++) {
                tableP.addCell((i + 1) + "");
                tableP.addCell(TBestSaleProductByProfit2.get(i));
                tableP.addCell(TBestSaleProductTotalProfit2.get(i) + "");
            }

            d.add(tableP);
            d.add(new Paragraph("\n"));

            PdfPTable tablel = new PdfPTable(4);

            PdfPCell cell1l = new PdfPCell(new Paragraph("Product By Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell1l.setColspan(5);
            cell1l.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1l.setBackgroundColor(BaseColor.GRAY);
            cell1l.setPaddingBottom(10);
            tablel.addCell(cell1l);

            PdfPCell cellIDl = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellIDl.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cellIDl);

            PdfPCell celll = new PdfPCell(new Paragraph("Sold Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll);

            PdfPCell celll2 = new PdfPCell(new Paragraph("Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll2);

            PdfPCell celll3 = new PdfPCell(new Paragraph("Loss Per Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll3);

            for (int i = 0; i < tlp.ProductNameArrayList.size(); i++) {

                tablel.addCell(tlp.ProductNameArrayList.get(i));
                tablel.addCell(tlp.ProductAmountArraList.get(i) + " " + tlp.Unit.get(i));
                tablel.addCell(tlp.LossArraList.get(i) + " " + currency);

                tablel.addCell(tlp.LossPerProductArraList.get(i) + " " + currency);

            }

            d.add(tablel);

            double TodayProfit = 0;
            for (int i = 0; i < TBestSaleProductTotalProfit2.size(); i++) {
                TodayProfit = TodayProfit + TBestSaleProductTotalProfit2.get(i);
            }

            double TotalLoss = 0;
            for (int i = 0; i < tlp.LossArraList.size(); i++) {
                TotalLoss = TotalLoss - tlp.LossArraList.get(i);
            }

            double ProfitOrLoss = (TodayProfit + TotalLoss);

            String Operator = ProfitOrLoss + "";

            if (Operator.startsWith("-")) {
                Paragraph pTP = new Paragraph("Total Loss: " + (-ProfitOrLoss) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            } else {
                Paragraph pTP = new Paragraph("Total Profit: " + ProfitOrLoss + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            }

            d.add(new Paragraph("\n"));

            d.close();

        } catch (Exception e) {

        }

    }

    public void allProductwithSoldTimes_Report(String dateString) {

        //  TodayspdfReport();
        getdaysBestProductBySaleNumber1(dateString);
        Document d = new Document();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
        try {

            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(".\\Dayreports\\" + dateString + "_ProductWithTimes.pdf"));
            d.open();

            //Barcode 
            PdfContentByte CB = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCode("1234567891011");
            d.add(new Paragraph(BarcodeText));
            codeEAN.setCodeType(Barcode.UPCA);
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
            image.scaleAbsolute(550, 200);
            image.setAlignment(Element.ALIGN_CENTER);

            d.add(image);

            d.add(new Paragraph(empty));
            Paragraph pa = new Paragraph("Todays Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));

            Paragraph pDate = new Paragraph(new Date().toString());
            pDate.setAlignment(Element.ALIGN_CENTER);
            d.add(pDate);
            d.add(new Paragraph("\n"));
            d.add(new Paragraph(empty));

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            PdfPTable table5 = new PdfPTable(3);

            PdfPCell cell16 = new PdfPCell(new Paragraph("All Sold Product With Sold Times", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell16.setColspan(4);
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setBackgroundColor(BaseColor.GRAY);
            cell16.setPaddingBottom(10);
            table5.addCell(cell16);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Times", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell18);

            for (int i = 0; i < TBestSaleProductBySaleNumber2.size(); i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(TBestSaleProductBySaleNumber2.get(i));
                table5.addCell(TBestSaleProductTotalSaleTime2.get(i) + "");
            }

            d.add(table5);

            d.close();

        } catch (Exception e) {

        }

    }

    public void allProductwithSoldAmount_Report(String dateString, String URL) {
        //TodayspdfReport();
        getdaysBestProduct(dateString);
        Document d = new Document();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
        try {

            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(URL));
            d.open();

            //Barcode 
            PdfContentByte CB = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCode("1234567891011");
            d.add(new Paragraph(BarcodeText));
            codeEAN.setCodeType(Barcode.UPCA);
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
            image.scaleAbsolute(550, 200);
            image.setAlignment(Element.ALIGN_CENTER);

            d.add(image);

            d.add(new Paragraph(empty));
            Paragraph pa = new Paragraph("Todays Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));

            Paragraph pDate = new Paragraph(new Date().toString());
            pDate.setAlignment(Element.ALIGN_CENTER);
            d.add(pDate);
            d.add(new Paragraph("\n"));
            d.add(new Paragraph(empty));

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            PdfPTable table5 = new PdfPTable(4);

            PdfPCell cell16 = new PdfPCell(new Paragraph("All Sold Product With Sold Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell16.setColspan(4);
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setBackgroundColor(BaseColor.GRAY);
            cell16.setPaddingBottom(10);
            table5.addCell(cell16);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell18);

            PdfPCell cell19 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell19);

            for (int i = 0; i < TBestSaleProduct2.size(); i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(TBestSaleProduct2.get(i));
                table5.addCell(TBestSaleProductAmount2.get(i) + "");
                table5.addCell(TBestSaleProductUnit2.get(i));
            }

            d.add(table5);

            d.close();

        } catch (Exception e) {

        }

    }

    public void dayspdfReport(String dateString, String URL) {
        InsufficientAmountProducts ias = new InsufficientAmountProducts();
        TodaysLossyProducts tlp = new TodaysLossyProducts();
        getdaysBest15ProductbySoldAmount(dateString);
        getdaysBest15ProductByPrice(dateString);
        getdaysBest15ProductsBySaleNumber(dateString);
        getdaysBest15ProductsByProfit(dateString);
        tlp.getInfo(dateString);
        BarChart b = new BarChart(dateString);
        LineChart L = new LineChart(dateString);
        PieChart p = new PieChart(dateString);
        RingChart r = new RingChart(dateString);
        getPieChart(dateString);
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";

        double Morning = b.h6 + b.h7 + b.h8 + b.h9 + b.h10;
        double Noon = b.h11 + b.h12 + b.h13 + b.h14;
        double afterNoon = b.h15 + b.h16 + b.h17 + b.h18;
        double Night = b.h19 + b.h20 + b.h21 + b.h22 + b.h23 + b.h2 + b.h1 + b.h2 + b.h3 + b.h4 + b.h5;

        double[] ar = {Morning, Noon, afterNoon, Night};
        String[] daypart = {"Morning", "Noon", "Afternoon", "Night"};

        String[] time = {"06:00:00-10:59:59 AM", "11:00:00-2:59:59 PM", "3:00:00-6:59:59 PM", "7:00:00-05:59:59 PM"};

        double highest = ar[0];
        String highestDayPart = daypart[0];
        String highestTime = time[0];

        double totalSale = b.h1 + b.h2 + b.h3 + b.h4 + b.h5 + b.h6 + b.h7 + b.h8 + b.h9 + b.h10 + b.h11 + b.h12 + b.h13 + b.h14 + b.h15 + b.h16 + b.h17 + b.h18 + b.h19 + b.h20 + b.h21 + b.h22 + b.h23 + b.h24;
        Document d = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(URL));
            d.open();

            //Barcode 
            PdfContentByte CB = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCode("1234567891011");
            d.add(new Paragraph(BarcodeText));
            codeEAN.setCodeType(Barcode.UPCA);
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
            image.scaleAbsolute(550, 200);
            image.setAlignment(Element.ALIGN_CENTER);

            d.add(image);

            d.add(new Paragraph(empty));
            Paragraph pa = new Paragraph("Todays Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));

            Paragraph pDate = new Paragraph(new Date().toString());
            pDate.setAlignment(Element.ALIGN_CENTER);
            d.add(pDate);
            d.add(new Paragraph("\n"));
            d.add(new Paragraph(empty));

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("Hourly Sales Report" + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD, BaseColor.WHITE)));
            cell.setColspan(4);
            cell.setPaddingBottom(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Time", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);

            String[] hour = {"12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM"};
            double[] value = {b.h24, b.h1, b.h2, b.h3, b.h4, b.h5, b.h6, b.h7, b.h8, b.h9, b.h10, b.h11, b.h12, b.h13, b.h14, b.h15, b.h16, b.h17, b.h18, b.h19, b.h20, b.h21, b.h22, b.h23};

            for (int i = 0; i < hour.length; i++) {
                table.addCell(hour[i]);
                table.addCell("" + value[i] + " " + currency);
            }

            PdfPCell cell3 = new PdfPCell(new Paragraph("Total Sale", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.GRAY)));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(totalSale + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.GRAY)));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell4);
            d.add(table);
            d.newPage();

            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            Paragraph BarChartP = new Paragraph("Hourly sales report in Bar Chart:", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
            BarChartP.setAlignment(Element.ALIGN_CENTER);
            d.add(BarChartP);

            d.add(new Paragraph("\n"));
            Image barchart = Image.getInstance("barchart1offull.png");
            barchart.scaleAbsolute(520, 300);
            d.add(barchart);

            d.add(new Paragraph("\n"));
            Image barchart2 = Image.getInstance("barchart2offull.png");
            barchart2.scaleAbsolute(520, 300);
            d.add(barchart2);

            List l = new List(true, 20);

            ArrayList<String> DP = new ArrayList();
            ArrayList<Double> total = new ArrayList();
            ArrayList<String> Time = new ArrayList();

            for (int j = ar.length - 1; j >= 0; j--) {
                for (int i = 0; i < ar.length - 1; i++) {
                    if (ar[i] > ar[i + 1]) {
                        highest = ar[i + 1];
                        highestDayPart = daypart[i + 1];
                        highestTime = time[i + 1];

                        ar[i + 1] = ar[i];
                        ar[i] = highest;
                        daypart[i + 1] = daypart[i];
                        daypart[i] = highestDayPart;
                        time[i + 1] = time[i];
                        time[i] = highestTime;

                    }
                }
                DP.add(daypart[j]);
                total.add(ar[j]);
                Time.add(time[j]);

            }

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            Paragraph BarPieP = new Paragraph("Hourly sales report in Pie Chart:", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
            BarPieP.setAlignment(Element.ALIGN_CENTER);
            d.add(BarPieP);
            d.add(new Paragraph("\n"));
            Image piechart = Image.getInstance("piechart.png");
            piechart.scaleAbsolute(520, 300);
            d.add(piechart);
            d.add(new Paragraph("\n"));
            l.add("Highest Selling Period        :" + DP.get(0) + ", Time:  " + Time.get(0) + ", Totol Sale:  " + total.get(0) + " Take");
            l.add("Second Highest Selling Period :" + DP.get(1) + ", Time: " + Time.get(1) + ", Totol Sale:" + total.get(1) + " Take");
            l.add("Third Highest Selling Period  :" + DP.get(2) + ", Time: " + Time.get(2) + ", Totol Sale:" + total.get(2) + " Take");
            l.add("Lowest Selling Period         :" + DP.get(3) + ", Time: " + Time.get(3) + ", Totol Sale:" + total.get(3) + " Take");
            d.add(l);

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            PdfPTable table2 = new PdfPTable(4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Best selling product(by sold Amount)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setBackgroundColor(BaseColor.GRAY);
            cell5.setPaddingBottom(10);
            table2.addCell(cell5);

            PdfPCell cellID = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cellID);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell6);

            PdfPCell cell7 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell8);

            for (int i = 0; i < 15; i++) {
                table2.addCell((i + 1) + "");
                table2.addCell(TBestSaleProduct.get(i));
                table2.addCell(TBestSaleProductAmount.get(i) + "");
                table2.addCell(TBestSaleProductUnit.get(i));
            }

            d.add(table2);

            d.add(new Paragraph("\n"));
            PdfPTable table3 = new PdfPTable(4);

            PdfPCell cell9 = new PdfPCell(new Paragraph("Best selling product(by Total Price)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell9.setColspan(4);
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setBackgroundColor(BaseColor.GRAY);
            cell9.setPaddingBottom(10);
            table3.addCell(cell9);

            PdfPCell cellID2 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cellID2);

            PdfPCell cell10 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell10);

            PdfPCell cell11 = new PdfPCell(new Paragraph("Total Price", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell11);

            PdfPCell cell12 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell12);

            for (int i = 0; i < 15; i++) {
                table3.addCell((i + 1) + "");
                table3.addCell(TBestSaleProductByPrice.get(i));
                table3.addCell(TBestSaleProductTotalPrice.get(i) + "");
                table3.addCell(TBestSaleProductByPriceUnit.get(i));
            }

            d.add(table3);
            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            PdfPTable table4 = new PdfPTable(3);

            PdfPCell cell13 = new PdfPCell(new Paragraph("Best selling product(by Total sold Times)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell13.setColspan(4);
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setBackgroundColor(BaseColor.GRAY);
            cell13.setPaddingBottom(10);
            table4.addCell(cell13);

            PdfPCell cellID3 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellID3);

            PdfPCell cell14 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell14);

            PdfPCell cell15 = new PdfPCell(new Paragraph("Sold Times", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell15);

            for (int i = 0; i < 15; i++) {
                table4.addCell((i + 1) + "");
                table4.addCell(TBestSaleProductBySaleNumber.get(i));
                table4.addCell(TBestSaleProductTotalSaleTime.get(i) + "");

            }

            d.add(table4);

            d.add(new Paragraph("\n"));

            PdfPTable table5 = new PdfPTable(3);

            PdfPCell cell16 = new PdfPCell(new Paragraph("Best selling product(by Profit)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell16.setColspan(4);
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setBackgroundColor(BaseColor.GRAY);
            cell16.setPaddingBottom(10);
            table5.addCell(cell16);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Profit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell18);

            for (int i = 0; i < 15; i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(TBestSaleProductByProfit.get(i));
                table5.addCell(TBestSaleProductTotalProfit.get(i) + "");

            }

            d.add(table5);
            d.add(new Paragraph("\n"));

            double TodayProfit = 0;
            for (int i = 0; i < TBestSaleProductTotalProfit.size(); i++) {
                TodayProfit = TodayProfit + TBestSaleProductTotalProfit.get(i);
            }

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            //.......
            PdfPTable tablel = new PdfPTable(4);

            PdfPCell cell1l = new PdfPCell(new Paragraph("Product By Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell1l.setColspan(5);
            cell1l.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1l.setBackgroundColor(BaseColor.GRAY);
            cell1l.setPaddingBottom(10);
            tablel.addCell(cell1l);

            PdfPCell cellIDl = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellIDl.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cellIDl);

            PdfPCell celll = new PdfPCell(new Paragraph("Sold Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll);

            PdfPCell celll2 = new PdfPCell(new Paragraph("Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll2);

            PdfPCell celll3 = new PdfPCell(new Paragraph("Loss Per Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll3);

            for (int i = 0; i < tlp.ProductNameArrayList.size(); i++) {

                tablel.addCell(tlp.ProductNameArrayList.get(i));
                tablel.addCell(tlp.ProductAmountArraList.get(i) + " " + tlp.Unit.get(i));
                tablel.addCell(tlp.LossArraList.get(i) + " " + currency);

                tablel.addCell(tlp.LossPerProductArraList.get(i) + " " + currency);

            }

            d.add(tablel);
            d.add(new Paragraph("\n"));

            double TotalLoss = 0;
            for (int i = 0; i < tlp.LossArraList.size(); i++) {
                TotalLoss = TotalLoss - tlp.LossArraList.get(i);
            }

            String ProfitOrLossString;
            double ProfitOrLoss = (TodayProfit + TotalLoss);

            String Operator = ProfitOrLoss + "";

            if (Operator.startsWith("-")) {
                Paragraph pTP = new Paragraph("Total Loss: " + (-ProfitOrLoss) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));

                ProfitOrLossString = "------------------------Todays Total Loss: " + (-ProfitOrLoss) + " " + currency;
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            } else {
                Paragraph pTP = new Paragraph("Total Profit: " + ProfitOrLoss + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
                ProfitOrLossString = "------------------------Todays Total Profit was: " + (ProfitOrLoss) + " " + currency;
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            }

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            PdfPTable table6 = new PdfPTable(4);

            PdfPCell cell19 = new PdfPCell(new Paragraph("Product with Insufficient Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.RED)));
            cell19.setColspan(4);
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell19.setBackgroundColor(BaseColor.GRAY);
            cell19.setPaddingBottom(10);
            table6.addCell(cell19);

            PdfPCell cellID5 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID5.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cellID5);

            PdfPCell cell20 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell20);

            PdfPCell cell21 = new PdfPCell(new Paragraph("Existing Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell21);

            PdfPCell cell22 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell22);

            for (int i = 0; i < ias.insufficientProductNameArrayList.size(); i++) {
                table6.addCell((i + 1) + "");
                table6.addCell(ias.insufficientProductNameArrayList.get(i));
                table6.addCell(ias.InsufficientProductAmountArraList.get(i) + "");
                table6.addCell(ias.insufficientProductUnitArrayList.get(i) + "");

            }

            d.add(table6);

//            InsufficientProductAmountArraList.add(d[i]);
//                insufficientProductNameArrayList.add(s1[i]);
            d.newPage();
//Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            d.add(new Paragraph("Short Description:", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY)));
            d.add(new Paragraph(empty));

            d.add(new Paragraph("Todays top 5 sold products(by number of times) were:" + TBestSaleProductBySaleNumber.get(0) + "," + TBestSaleProductBySaleNumber.get(1) + "," + TBestSaleProductBySaleNumber.get(2) + "," + TBestSaleProductBySaleNumber.get(3) + "," + TBestSaleProductBySaleNumber.get(4)));
            d.add(new Paragraph("------------------------More people need these products."));
            d.add(new Paragraph("Todays top 5 sold products(by sold amount) were:" + TBestSaleProduct.get(0) + "," + TBestSaleProduct.get(1) + "," + TBestSaleProduct.get(2) + "," + TBestSaleProduct.get(3) + "," + TBestSaleProduct.get(4)));
            d.add(new Paragraph("------------------------You need to keep more amount of  these products."));

            d.add(new Paragraph("Todays top 5 profitable products were:" + TBestSaleProductByProfit.get(0) + "," + TBestSaleProductByProfit.get(1) + "," + TBestSaleProductByProfit.get(2) + "," + TBestSaleProductByProfit.get(3) + "," + TBestSaleProductByProfit.get(4)));
            d.add(new Paragraph("------------------------These items helps you to make more profit."));
            d.add(new Paragraph(ProfitOrLossString));
            d.add(new Paragraph("-----------------------------------------------Best of luck for tomorrow--------------------------------------------------"));
            d.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Absent;
    private javax.swing.JPanel AllProduct;
    private javax.swing.JCheckBox CBSP;
    private com.toedter.calendar.JDateChooser DCJD;
    private javax.swing.JList Employee_Category;
    private javax.swing.JMenuItem Monthly;
    private javax.swing.JMenuItem MonthlyAR;
    private javax.swing.JMenuItem Present;
    private javax.swing.JRadioButton RDB12M;
    private javax.swing.JRadioButton RDBSD;
    private javax.swing.JRadioButton RDBSM;
    private javax.swing.JMenuItem Yearly;
    private javax.swing.JMenuItem YearlyAR;
    private javax.swing.JButton btnAR;
    private javax.swing.JButton btnAddSupplier;
    private javax.swing.JButton btnCategoryAdd;
    private javax.swing.JButton btnPR;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton btnSignIn;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateCategory;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem changePass;
    private javax.swing.JComboBox cmbEType;
    private javax.swing.JComboBox cmbPCat;
    private javax.swing.JComboBox cmbPUnit;
    private javax.swing.JComboBox cmbSupplier;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPopupMenu jmAdminStatus;
    private javax.swing.JPopupMenu jmEA;
    private javax.swing.JPopupMenu jmEP;
    private javax.swing.JPopupMenu jmEmployeeStatus;
    public static javax.swing.JLabel lblAdminName;
    private javax.swing.JLabel lblBP1;
    private javax.swing.JLabel lblBP2;
    private javax.swing.JLabel lblBP3;
    private javax.swing.JLabel lblBP4;
    private javax.swing.JLabel lblBP5;
    private javax.swing.JLabel lblBP6;
    private javax.swing.JLabel lblBP7;
    private javax.swing.JLabel lblBP8;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblPImage;
    private javax.swing.JMenuItem logOut;
    private javax.swing.JList lst;
    private javax.swing.JList lstProductCategory;
    private javax.swing.JList lstProductCategory2;
    private javax.swing.JPanel pnlAnimation;
    private javax.swing.JPanel pnlBarChart;
    private javax.swing.JPanel pnlCategory;
    private javax.swing.JDesktopPane pnlImage;
    private javax.swing.JPanel pnlLineChart;
    private javax.swing.JPanel pnlPieChart;
    private javax.swing.JPanel pnlProduct2;
    private javax.swing.JPanel pnlProductMain;
    private javax.swing.JPanel pnlRingChart;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblEmpAttendence;
    private javax.swing.JTable tblEmployeeInfo;
    private javax.swing.JTable tblProductDetails;
    private javax.swing.JTable tblProductDetails2;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTabbedPane tpBPImage;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtECategory;
    private javax.swing.JTextField txtEID;
    private javax.swing.JTextField txtEName;
    private javax.swing.JTextField txtImagePath;
    private javax.swing.JTextField txtPAmount;
    private javax.swing.JTextField txtPID;
    private javax.swing.JTextField txtPPP;
    private javax.swing.JTextField txtPPrice;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSAddress;
    private javax.swing.JTextField txtSEmail;
    private javax.swing.JTextField txtSName;
    private javax.swing.JTextField txtSPhone;
    private javax.swing.JTextField txtSalary;
    // End of variables declaration//GEN-END:variables

}
