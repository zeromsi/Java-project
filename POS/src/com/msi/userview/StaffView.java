/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.userview;

import com.itextpdf.text.List;
import com.msi.DAO.CategoryDAO;
import com.msi.adminview.Category;
import com.msi.adminview.index;
import com.msi.connection.Connection;
import com.msi.login.LoginForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author MSI
 */
public class StaffView extends javax.swing.JFrame {

    java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ArrayList<String> tab = new ArrayList<String>();
    ArrayList<String> pnllist = new ArrayList<String>();
    ArrayList<JPanel> Jpnllist = new ArrayList<JPanel>();
    public ArrayList<JButton> AllButton = new ArrayList<JButton>();
    ArrayList<String> allBtnName = new ArrayList<String>();

    //JButton buttons[] = new JButton[20];
    ArrayList<JButton> buttons1 = new ArrayList();
    ArrayList<String> btnName = new ArrayList<String>();
    ArrayList<String> Unit = new ArrayList<String>();
    ArrayList<Double> Price = new ArrayList<Double>();
    ArrayList<Double> PAmount = new ArrayList<Double>();

    ArrayList<JButton> buttons1BySearch = new ArrayList();
    ArrayList<String> btnNameBySearch = new ArrayList<String>();
    ArrayList<String> UnitBySearch = new ArrayList<String>();
    ArrayList<Double> PriceBySearch = new ArrayList<Double>();
    ArrayList<Double> PAmountBySearch = new ArrayList<Double>();

    ArrayList<JButton> buttons1ByBookmark = new ArrayList();
    ArrayList<String> btnNameByBookmark = new ArrayList<String>();
    ArrayList<String> UnitByBookmark = new ArrayList<String>();
    ArrayList<Double> PriceByBookmark = new ArrayList<Double>();
    ArrayList<Double> PAmountByBookmark = new ArrayList<Double>();

    ArrayList<ImageIcon> format = new ArrayList();
    ArrayList<ImageIcon> formatBySearch = new ArrayList();
    ArrayList<ImageIcon> formatByBookmark = new ArrayList();

    JPanel pnl = new JPanel();
    String EID = null;
    DateClass dt = new DateClass();
    DefaultListModel listModel = new DefaultListModel();

    int count = 0, countClick = 0;
    JPanel pnlSearch, pnlBookmark;
ImageIcon imgFake=null;
    public StaffView() {
        initComponents();
        super.setLocationRelativeTo(null);
        conn = Connection.DBconnector();
        getAllTab();
        for (int i = tabpnl.getTabCount()-1; i >= 0; i--) {
            tabpnl.setSelectedIndex(i);
        }
        getSaleID();
        getTotal();

        listModel.addElement("Serial: " + txtID.getText());
        btnClear.setVisible(false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        dt = c.getTime();
        String dateString = dateFormat.format(dt).trim();
        lblGenInfo.setText("<html><body><marquee><p> Today is " + dateString + "</p></marquee></body></html>");
    }

    public void getAllButton() {
        AllButton.clear();
        allBtnName.clear();
        String name;
        try {
            String sql = "select PName from Product";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("PName");
                allBtnName.add(name);

            }
            for (int i = 0; i < allBtnName.size(); i++) {
                AllButton.add(new JButton(allBtnName.get(i)));
                AllButton.get(i).setEnabled(true);

                //btnName.get(i).setBounds(50, 200, 200, 200);
                //buttons1.get(i).setBounds(50, 200, 200, 200);
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

    }

    public void getAllTab() {
        AllButton.clear();
        String name;
        tab.clear();
        try {
            String sql = "select *from ProductCategory";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("CatName");
                tab.add(name);

                Jpnllist.add(new JPanel());
                //  pnllist.add(name);

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

        for (int i = 0; i < tab.size(); i++) {
            tabpnl.addTab(tab.get(i), Jpnllist.get(i));
            Jpnllist.get(i).setLayout(new GridLayout(0,10));
          
            
        }

        // get(); 
        for (int i = 0; i < Jpnllist.size(); i++) {
            Jpnllist.get(i).setBackground(Color.white);
            //  Jpnllist.get(i).setSize(200, 200);
            Jpnllist.get(i).setVisible(true);
            getButton(i + 1);

            AllButton.addAll(buttons1);

            for (int j = 0; j < buttons1.size(); j++) {

                Jpnllist.get(i).add(buttons1.get(j));

//                  buttons1.get(j).addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent ev) {
//
//                        btnFunction(AllButton.get(18).getText());
//                    }
//                });
            }

        }
        String get = null;

        //<-- accesible 
        for (int i = 0; i < AllButton.size(); i++) {
            String str = AllButton.get(i).getName();
            JButton btn = AllButton.get(i);
            AllButton.get(i).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ev) {

                    btnFunction(str, btn);

                }
            });
        }

    }

    ArrayList<JButton> getButton(int id) {
        btnName.clear();
        Unit.clear();
        Price.clear();
        buttons1.clear();
        PAmount.clear();
        format.clear();
        String name;
        try {
            String sql = "select PName,PPrice,PAmount,Unit,Image from Product where CatID='" + id + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("PName");
                btnName.add(name);
                Unit.add(rs.getString("Unit"));
                Price.add(rs.getDouble("PPrice"));
                PAmount.add(rs.getDouble("Pamount"));

                byte[] imagedata = rs.getBytes(5);
                if(imagedata.equals("")){
                    format.add(null);
                }else{
                format.add(new ImageIcon(imagedata));
                }
                //lblImage.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                // lblImage.setIcon(format);
            }
            for (int i = 0; i < btnName.size(); i++) {
                if (PAmount.get(i) < 5) {
                    buttons1.add(new JButton("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                            + Price.get(i) + "/" + Unit.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:DD6765'>"
                            + PAmount.get(i) + " " + Unit.get(i).substring(0, 1) + ".</P><p Style='Font-size:16px;color:#394046;'>" + btnName.get(i) + "</p><hr></body></html>"));
                } else {
                    buttons1.add(new JButton("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                            + Price.get(i) + "/" + Unit.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:Green'>"
                            + PAmount.get(i) + " " + Unit.get(i).substring(0, 1) + ".</P><p Style='Font-size:16px;color:#394046;'>" + btnName.get(i) + "</p><hr></body></html>"));
                }

                java.awt.Image img = format.get(i).getImage();
              
                //java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

                //  format.get(i).equals( new ImageIcon(img));
                
                if(format.get(i).equals(null)){
                    buttons1.get(i).setIcon(imgFake);
                }else{
                buttons1.get(i).setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
                }
                
                buttons1.get(i).setName(btnName.get(i));
                buttons1.get(i).setBackground(Color.decode("#FFFFFF"));

                buttons1.get(i).setEnabled(true);

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

        return buttons1;
    }

    ArrayList<JButton> getButtonBySearch(String txt) {
        btnNameBySearch.clear();
        UnitBySearch.clear();
        PriceBySearch.clear();
        buttons1BySearch.clear();
        PAmountBySearch.clear();
        formatBySearch.clear();
        String name;
        try {
            String sql = "select PName,PPrice,Unit,PAmount,Image from Product where PName Like '%" + txt + "%'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("PName");
                btnNameBySearch.add(name);
                UnitBySearch.add(rs.getString("Unit"));
                PriceBySearch.add(rs.getDouble("PPrice"));
                PAmountBySearch.add(rs.getDouble("PAmount"));

                byte[] imagedata = rs.getBytes(5);
                formatBySearch.add(new ImageIcon(imagedata));
            }
            for (int i = 0; i < btnNameBySearch.size(); i++) {
                //   buttons1BySearch.add(new JButton("<html><body><div Style='color:white;background:gray;Text-align:center;'><p >" + PriceBySearch.get(i) + "/" + UnitBySearch.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:Green'>" + PAmountBySearch.get(i) + " " + UnitBySearch.get(i).substring(0, 1) + ".</p><p Style='Font-size:16px;'>" + btnNameBySearch.get(i) + "</p></body></html>"));

                if (PAmountBySearch.get(i) < 5) {
                    buttons1BySearch.add(new JButton("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                            + PriceBySearch.get(i) + "/" + UnitBySearch.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:#DD6765'>"
                            + PAmountBySearch.get(i) + " " + UnitBySearch.get(i).substring(0, 1) + ".</P><p Style='Font-size:16px;color:#394046;'>" + btnNameBySearch.get(i) + "</p><hr></body></html>"));
                } else {
                    buttons1BySearch.add(new JButton("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                            + PriceBySearch.get(i) + "/" + UnitBySearch.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:Green'>"
                            + PAmountBySearch.get(i) + " " + UnitBySearch.get(i).substring(0, 1) + ".</P><p Style='Font-size:16px;color:#394046;'>" + btnNameBySearch.get(i) + "</p><hr></body></html>"));
                }

                java.awt.Image img = formatBySearch.get(i).getImage();
                //java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

                //  format.get(i).equals( new ImageIcon(img));
                buttons1BySearch.get(i).setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

                buttons1BySearch.get(i).setName(btnNameBySearch.get(i));
                buttons1BySearch.get(i).setBackground(Color.decode("#FFFFFF"));

                buttons1BySearch.get(i).setEnabled(true);

                // buttons1BySearch.get(i).setEnabled(true);
//               buttons1.get(i).setName(btnName.get(i));
                //btnName.get(i).setBounds(50, 200, 200, 200);
                //buttons1.get(i).setBounds(50, 200, 200, 200);
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

        return buttons1BySearch;
    }

    ArrayList<JButton> getButtonByBookmark(String txt) {
        btnNameByBookmark.clear();
        UnitByBookmark.clear();
        PriceByBookmark.clear();
        buttons1ByBookmark.clear();
        PAmountByBookmark.clear();
        String name;
        try {
            String sql = "select PName,PPrice,Unit,PAmount,Image from Product,Bookmark,Employee_info  where "
                    + "Product.PID=Bookmark.PID and Bookmark.EID=Employee_info.EID and Username='" + txt + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("PName");
                btnNameByBookmark.add(name);
                UnitByBookmark.add(rs.getString("Unit"));
                PriceByBookmark.add(rs.getDouble("PPrice"));
                PAmountByBookmark.add(rs.getDouble("PAmount"));

                byte[] imagedata = rs.getBytes(5);
                formatByBookmark.add(new ImageIcon(imagedata));
            }
            for (int i = 0; i < PAmountByBookmark.size(); i++) {
                if (PAmountByBookmark.get(i) < 5) {
                    buttons1ByBookmark.add(new JButton("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                            + PriceByBookmark.get(i) + "/" + UnitByBookmark.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:#DD6765'>"
                            + PAmountByBookmark.get(i) + " " + UnitByBookmark.get(i).substring(0, 1) + ".</p><p Style='Font-size:16px;color:#394046;'>" + btnNameByBookmark.get(i) + "</p><hr></body></html>"));

                } else {
                    buttons1ByBookmark.add(new JButton("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                            + PriceByBookmark.get(i) + "/" + UnitByBookmark.get(i).substring(0, 1) + ".</p></div><p Style='Text-align:right; color:Green'>"
                            + PAmountByBookmark.get(i) + " " + UnitByBookmark.get(i).substring(0, 1) + ".</p><p Style='Font-size:16px;color:#394046;'>" + btnNameByBookmark.get(i) + "</p><hr></body></html>"));

                }

                java.awt.Image img = formatByBookmark.get(i).getImage();
                //java.awt.Image newimg = img.getScaledInstance(125, 80, java.awt.Image.SCALE_SMOOTH);

                //  format.get(i).equals( new ImageIcon(img));
                buttons1ByBookmark.get(i).setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

                buttons1ByBookmark.get(i).setName(btnNameByBookmark.get(i));
                buttons1ByBookmark.get(i).setBackground(Color.decode("#FFFFFF"));

                buttons1ByBookmark.get(i).setEnabled(true);

//               buttons1.get(i).setName(btnName.get(i));
                //btnName.get(i).setBounds(50, 200, 200, 200);
                //buttons1.get(i).setBounds(50, 200, 200, 200);
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

        return buttons1ByBookmark;
    }

    public void getTotal() {
        int a = Integer.parseInt(txtID.getText());

        String sql1 = "SELECT SUM(TotalPrice) FROM Customer  where CID= '" + a + "'";
        try {
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            if (rs.next()) {
                double b = rs.getDouble(1);
                txtTotal.setText(" " + b + "");
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

    public void getSaleID() {

        try {
            String sql = "select max(CID) from Customer";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString(1);
                if (add1 == null) {
                    add1 = "0";
                }
                int a = Integer.parseInt(add1);

                txtID.setText(a + 1 + "");

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


    
     void btnFunction(String btn, JButton BTN) {
        if (!txtSearch.getText().isEmpty() || btnNext.getText().equalsIgnoreCase("clear")) {
            JOptionPane.showMessageDialog(null, "Access denied!You must clear search content!");
        } else {
            dt.day = "";
            dt.month = "";
            try {

                String sql = "SELECT EID FROM Employee_Info  where  username='" + lblName.getText().trim() + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    EID = rs.getString("EID");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }
            }

            InputDialogue pn = new InputDialogue();

            dt.getSqlDate();

            //pn.txtID.setText(id + "");
            // pn.PID=id;
            try {
                String sql = "SELECT PID,PName,PPrice,PAmount,Unit FROM Product  where  Product.PName='" + btn + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    pn.txtID.setText(rs.getInt("PID") + "");
                    pn.txtName.setText(rs.getString("PName"));
                    pn.txtPrice.setText(rs.getDouble("PPrice") + "");
                    pn.txtEAmount.setText(rs.getDouble("PAmount") + "");
                    pn.lblUnit.setText(rs.getString("Unit"));
                    pn.lblUnit1.setText(pn.lblUnit.getText());
                    pn.lblUnit2.setText(pn.lblUnit.getText());
                    pn.lblEID.setText(EID);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {

                }
            }
            pn.setVisible(true);

            BookmarkClass bm = new BookmarkClass();
            boolean bol = bm.Bookmark(Integer.parseInt(pn.txtID.getText()), EID);
//
//Icon I1 = new ImageIcon(getClass().getResource("com/msi/image/star_list.png"));
//        Icon I2 = new ImageIcon(getClass().getResource("com/msi/image/bookmark.png"));
            if (bol == true) {
                pn.btnBookmark.setEnabled(false);
            } else if (bol == false) {
                pn.btnBookmark.setEnabled(true);
            }
            pn.btnAdd.addActionListener(evt -> {
                String a = null;
                double ItemS1P = 0;
                double b = 0;
                double TotalSItem1P = 0;
                double PAmount = 0;
                double Amount = 0;
                double profit = 0;
                int id = 0;
                double PurchasePricePerUnit = 0;
                String unit = null;
                try {
                    String s = "Select PID,PPrice,PAmount,Unit,PurchasePricePerUnit from product where PName='" + btn + "'";
                    pst = conn.prepareStatement(s);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        id = rs.getInt("PID");
                        ItemS1P = rs.getDouble("PPrice");
                        b = Double.parseDouble(pn.txtAmount.getText());
                        PAmount = rs.getDouble("PAmount");
                        Amount = PAmount;
                        unit = rs.getString("Unit");
                       // profit = rs.getDouble("profit");
                        PurchasePricePerUnit = rs.getDouble("PurchasePricePerUnit");
                        b = Math.abs(b);

                        lstItem.setModel(listModel);

                        TotalSItem1P = ItemS1P * b;

                        PAmount = PAmount - b;
                        double profitC = (b * ItemS1P) - (b * PurchasePricePerUnit);
                        try {
                            String sql = "insert into Customer (CID,PID,Amount,TotalPrice,Day,Month,profit,EID) values(?,?,?,?,?,?,?,?)";
                            pst = conn.prepareStatement(sql);

                            pst.setString(1, txtID.getText());
                            pst.setString(2, id + "");

                            pst.setDouble(3, b);
                            pst.setDouble(4, TotalSItem1P);
                            pst.setString(5, dt.day.trim());
                            pst.setString(6, dt.month.trim());
                            pst.setDouble(7, profitC);
                            pst.setString(8, EID);

                            if (PAmount >= 0) {
                               // profit = profit + (b * ItemS1P) - (b * PurchasePricePerUnit);
                                PreparedStatement pst2 = null;
                                ResultSet rs2 = null;
                                try {
                                    String query = "update Product set PAmount= '" + PAmount + "' where PID='" + id + "'";
                                    pst2 = conn.prepareStatement(query);
                                    pst2.execute();
                                    //pst.setString(1, txtCategory.getText());                
                                    pst.execute();

                                    listModel.addElement(btn + "(" + b + ")" + "------------" + TotalSItem1P);

                                    double RecentPAmount = PAmount;
                                    if (RecentPAmount < 5) {
                                        BTN.setText("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                                                + ItemS1P + "/" + unit.substring(0, 1) + ".</p></div><p Style='Text-align:right; color:#DD6765'>"
                                                + RecentPAmount + " " + unit.substring(0, 1) + ".</P><p Style='Font-size:16px;color:#394046;'>" + btn + "</p><hr></body></html>");
                                    } else {
                                        BTN.setText("<html><body><hr><div Style='color:white;background:gray;Text-align:center;'><p >"
                                                + ItemS1P + "/" + unit.substring(0, 1) + ".</p></div><p Style='Text-align:right; color:Green'>"
                                                + RecentPAmount + " " + unit.substring(0, 1) + ".</P><p Style='Font-size:16px;color:#394046;'>" + btn + "</p><hr></body></html>");
                                    }

                                } catch (Exception e) {

                                } finally {
                                    try {
                                        pst2.close();
                                        rs2.close();
                                    } catch (Exception e) {

                                    }
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Sorry!Insufficient amount! You've only " + Amount + " " + unit);
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Something isn't ok!");
                        }

                        // btn.setEnabled(false);
                        getTotal();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "You've not inputed any value!");
                } finally {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {

                    }
                }

            });

            pn.btnBookmark.addActionListener(evt -> {
                try {
                    String sql = "Insert into bookmark(PID,EID) values(?,?)";
                    pst = conn.prepareStatement(sql);

                    pst.setInt(1, Integer.parseInt(pn.txtID.getText()));

                    pst.setString(2, EID);
                    pst.execute();
                    pn.btnBookmark.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Bookmarked.");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
                } finally {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
                    }
                }
            });
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

        jpSettings = new javax.swing.JPopupMenu();
        miBP = new javax.swing.JMenuItem();
        miCP = new javax.swing.JMenuItem();
        miLO = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstItem = new javax.swing.JList();
        txtTotal = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtProductSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBookmark = new javax.swing.JButton();
        lblGenInfo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabpnl = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        miBP.setText("Bookmark product");
        miBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBPActionPerformed(evt);
            }
        });
        jpSettings.add(miBP);

        miCP.setText("Change password");
        miCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCPActionPerformed(evt);
            }
        });
        jpSettings.add(miCP);

        miLO.setText("Log out");
        miLO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLOActionPerformed(evt);
            }
        });
        jpSettings.add(miLO);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(57, 64, 70));

        jPanel5.setBackground(new java.awt.Color(57, 64, 70));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
        });

        lblName.setBackground(new java.awt.Color(57, 64, 70));
        lblName.setForeground(new java.awt.Color(82, 149, 139));
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/img-profile.jpg"))); // NOI18N
        lblName.setOpaque(true);
        lblName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNameMouseEntered(evt);
            }
        });

        txtID.setEditable(false);

        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtSearchMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtSearchMouseReleased(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lstItem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lstItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lstItemMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstItemMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lstItem);

        txtTotal.setEditable(false);

        btnNext.setBackground(new java.awt.Color(82, 149, 139));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/ic_media_next.png"))); // NOI18N
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(57, 64, 70));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/b_print.png"))); // NOI18N
        jButton2.setToolTipText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(82, 149, 139));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Report");
        jButton3.setToolTipText("Create Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSettings.setBackground(new java.awt.Color(57, 64, 70));
        btnSettings.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/cog.png"))); // NOI18N
        btnSettings.setToolTipText("Settings");
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

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/b_search.png"))); // NOI18N
        jLabel1.setText("Search by sale ID");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sale ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(3, 3, 3)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtID)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSettings))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(57, 64, 70));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(57, 64, 70));

        txtProductSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtProductSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProductSearchMouseClicked(evt);
            }
        });
        txtProductSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductSearchActionPerformed(evt);
            }
        });
        txtProductSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductSearchKeyTyped(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(57, 64, 70));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/b_search.png"))); // NOI18N
        btnSearch.setToolTipText("Search by Product Name");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(57, 64, 70));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/b_drop.png"))); // NOI18N
        btnClear.setToolTipText("Clear Search");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnBookmark.setBackground(new java.awt.Color(57, 64, 70));
        btnBookmark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/star_list.png"))); // NOI18N
        btnBookmark.setToolTipText("See Bookmarked product");
        btnBookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookmarkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(btnBookmark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnClear))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnBookmark, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblGenInfo.setBackground(new java.awt.Color(57, 64, 70));
        lblGenInfo.setForeground(new java.awt.Color(82, 149, 139));
        lblGenInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGenInfo.setText("Todays is a great day!");
        lblGenInfo.setOpaque(true);

        tabpnl.setBackground(new java.awt.Color(57, 64, 70));
        tabpnl.setForeground(new java.awt.Color(255, 255, 255));
        tabpnl.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabpnl.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jScrollPane2.setViewportView(tabpnl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 217, Short.MAX_VALUE)
                        .addComponent(lblGenInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblGenInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBackground(new java.awt.Color(57, 64, 70));

        jMenu1.setText("File");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Change password");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/star_list.png"))); // NOI18N
        jMenuItem6.setText("Bookmark");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Log out");
        jMenuItem1.setOpaque(true);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/msi/image/exit-button-th (2).png"))); // NOI18N
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Report");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Create Report");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Print");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

        btnNext.setText("Next");
         btnNext.setBackground(Color.decode("#52958B"));
        txtSearch.setText("");
        getSaleID();
        // txtSearch.setText("Search by sale ID");
        listModel.removeAllElements();
        txtTotal.setText("0.0");
        listModel.addElement("Serial: " + txtID.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed
    public void searchgetTotal() {

        String sql1 = "SELECT SUM(TotalPrice) FROM Customer  where CID=?";
        try {
            pst = conn.prepareStatement(sql1);
            pst.setString(1, txtSearch.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                double b = rs.getDouble(1);
                txtTotal.setText(" " + b + "");
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
    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        btnNext.setText("Clear");
        btnNext.setBackground(Color.red);
        listModel.removeAllElements();
        listModel.add(0, "Serial: " + txtSearch.getText());
        txtTotal.setText("0.0");
        String Add1 = null;
        double Add2 = 0;
        double Add3 = 0;

        try {
            String sql = "SELECT PName,Amount,TotalPrice from Customer,Product where  Product.PID=Customer.PID and CID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtSearch.getText());
            rs = pst.executeQuery();

            while (rs.next()) {

                Add1 = rs.getString("PName");
                Add2 = rs.getDouble("Amount");
                Add3 = rs.getDouble("TotalPrice");

                String QueryTotal = Add1 + "(" + Add2 + ")" + "--------" + Add3;

                listModel.addElement(QueryTotal);
            }

            lstItem.setModel(listModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
            searchgetTotal();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseReleased

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        txtSearch.setText("");          // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Print Print = new Print();
        listModel.addElement("============================================");
        listModel.addElement("<html><body><pre style='color:Black;font-size:16px;'>  Total:" + txtTotal.getText() + " " + Currency.Currency+"</pre></body></html>");
        Print.printComponenet(lstItem);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        StringTokenizer st = new StringTokenizer(listModel.getElementAt(0).toString(), " ");
        String serial = st.nextToken().trim();
        String ser = st.nextToken(serial).trim();

        SalesReport sr = new SalesReport();
        sr.createReport(ser);
        sr.openReport();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    public void deleteSelectedListItem() {
        String string = (String) lstItem.getSelectedValue();
        int index = lstItem.getSelectedIndex();

        //String[] tok;
        StringTokenizer st = new StringTokenizer(string, "(");
        String ProductName = st.nextToken();
        String CustomerId = txtID.getText();
        double Price = 0;
        //double PurchasePricePerUnit=0;
        int ProductId = 0;
        double ProductAmount = 0;
        double purchasePricePerUnit = 0;
        double profit = 0;
        try {
            String sql = "Select PID,PPrice,purchasePricePerUnit,profit from Product where PName='" + ProductName + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                ProductId = rs.getInt("PID");
                Price = rs.getDouble("PPrice");
                purchasePricePerUnit = rs.getDouble("purchasePricePerUnit");
                profit = rs.getDouble("profit");
            }

        } catch (Exception e) {

        }

        try {
            String sql = "Select Amount from Customer where PID='" + ProductId + "' and CID='" + CustomerId + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                ProductAmount = rs.getInt("Amount");
            }

        } catch (Exception e) {

        }

        profit = profit - ((Price * ProductAmount) - (purchasePricePerUnit * ProductAmount));
        try {
            String sql = "Update Product set PAmount=PAmount+'" + ProductAmount + "',profit='" + profit + "' where PName='" + ProductName + "' and PID='" + ProductId + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {

        }

        try {
            String sql = "Delete from Customer where CID='" + CustomerId + "' and PID='" + ProductId + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();

            listModel.removeElementAt(index);
            double TotalPrice = Double.parseDouble(txtTotal.getText());
            txtTotal.setText((TotalPrice - Price * ProductAmount) + "");

        } catch (Exception e) {

        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

    }
    private void lstItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstItemMouseReleased
        if (!btnNext.getText().equals("Clear")) {
            int p = JOptionPane.showConfirmDialog(null, "You wanna delete this!", "Delete", JOptionPane.YES_NO_CANCEL_OPTION);
            try {
                if (p == 0) {
                    if (lstItem.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "You can't delete customer ID!");

                    } else {
                        deleteSelectedListItem();
                    }

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR.");
            }
        }

    }//GEN-LAST:event_lstItemMouseReleased

    private void txtProductSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductSearchKeyReleased


    }//GEN-LAST:event_txtProductSearchKeyReleased

    private void txtProductSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductSearchKeyTyped

    private void txtProductSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductSearchKeyPressed

    private void txtProductSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProductSearchMouseClicked

    }//GEN-LAST:event_txtProductSearchMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        pnlSearch = new JPanel();

        pnlSearch.setName("Search");
        pnlSearch.setBackground(Color.white);
        getButtonBySearch(txtProductSearch.getText());
        //System.out.print(buttons1BySearch.get(0).getName());

        int tabIndex = tabpnl.getTabCount();

        for (int j = 0; j < buttons1BySearch.size(); j++) {
            JButton btn = buttons1BySearch.get(j);
            pnlSearch.add(buttons1BySearch.get(j));
            pnlSearch.setLayout(new GridLayout(0, 10));

            String str = buttons1BySearch.get(j).getName();

            buttons1BySearch.get(j).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ev) {

                    btnFunction(str, btn);
                }
            });

        }

//        count = count + 1;
//        if (count == 1) {
        //tabpnl.add("Search", new JPanel()).setBackground(Color.white);
        tabpnl.add(pnlSearch, tabIndex);
        tabpnl.setSelectedIndex(tabIndex);
//        } else if (txtProductSearch.getText().trim().equals("")) {
//            if (tabpnl.getTitleAt(tabIndex - 1).equals("Search")) {
//                tabpnl.remove(tabIndex - 1);
//                count = 0;
//                buttons1BySearch.clear();
//            }
//        }
        btnSearch.setVisible(false);
        btnClear.setVisible(true);

        //System.out.print(count);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        btnSearch.setVisible(true);
        btnClear.setVisible(false);
        txtProductSearch.setText("");
        tabpnl.remove(pnlSearch);
tabpnl.setSelectedIndex(0);
        buttons1BySearch.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        jpSettings.show(btnSettings, 0, 18);


    }//GEN-LAST:event_btnSettingsActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
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
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(1);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String UN = lblName.getText().trim();
        ChangePassword cp = new ChangePassword();
        cp.txtUN.setText(UN);
        cp.setVisible(true);

        cp.jButton1.addActionListener(new ActionListener() {
            String pass = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "Select password from Employee_Info where username='" + cp.txtUN.getText().trim() + "'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        pass = rs.getString("password");
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
                if (cp.txtUN.getText().equals("") && cp.txtPSD.getText().equals("") && cp.txtNPSD.getText().equals("") && cp.txtNPSDConfirm.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty field found!");
                } else if (!cp.txtPSD.getText().equals(pass)) {
                    JOptionPane.showMessageDialog(null, "Password not matched for due username!");
                } else if (!cp.txtNPSD.getText().equals(cp.txtNPSDConfirm.getText())) {
                    JOptionPane.showMessageDialog(null, "New Password and confirm password must be same!");
                } else if (cp.txtNPSD.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Password contains at least 8 character!");
                } else {
                    try {

                        String PSD = cp.txtNPSD.getText();

                        String sql = "update Employee_Info set Password='" + PSD + "' where username='" + cp.txtUN.getText().trim() + "'";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Operation successful.");
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
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Print Print = new Print();
        Print.printComponenet(lstItem);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        StringTokenizer st = new StringTokenizer(listModel.getElementAt(0).toString(), " ");
        String serial = st.nextToken().trim();
        String ser = st.nextToken(serial).trim();

        SalesReport sr = new SalesReport();
        sr.createReport(ser);
        sr.openReport();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnBookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookmarkActionPerformed

        if (countClick == 0) {
            countClick = countClick + 1;
            pnlBookmark = new JPanel();

            pnlBookmark.setName("Bookmark");
            pnlBookmark.setBackground(Color.white);
            getButtonByBookmark(lblName.getText().trim());

            int tabIndex = tabpnl.getTabCount();

            for (int j = 0; j < buttons1ByBookmark.size(); j++) {
                JButton btn = buttons1ByBookmark.get(j);
                pnlBookmark.add(buttons1ByBookmark.get(j));
                pnlBookmark.setLayout(new GridLayout(0, 10));

                String str = buttons1ByBookmark.get(j).getName();

                buttons1ByBookmark.get(j).addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent ev) {

                        btnFunction(str, btn);
                    }
                });

            }
            tabpnl.add(pnlBookmark, tabIndex);
            tabpnl.setSelectedIndex(tabIndex);

        } else if (countClick == 1) {

            tabpnl.remove(pnlBookmark);
tabpnl.setSelectedIndex(0);
            countClick = 0;
        }

    }//GEN-LAST:event_btnBookmarkActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        if (countClick == 0) {
            countClick = countClick + 1;
            pnlBookmark = new JPanel();

            pnlBookmark.setName("Bookmark");
            pnlBookmark.setBackground(Color.white);
            getButtonByBookmark(lblName.getText().trim());

            int tabIndex = tabpnl.getTabCount();

            for (int j = 0; j < buttons1ByBookmark.size(); j++) {

                pnlBookmark.add(buttons1ByBookmark.get(j));
                pnlBookmark.setLayout(new GridLayout(0, 10));

                String str = buttons1ByBookmark.get(j).getName();

                buttons1ByBookmark.get(j).addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent ev) {

//                       btnFunction(str);
                    }
                });

            }
            tabpnl.add(pnlBookmark, tabIndex);
            tabpnl.setSelectedIndex(tabIndex);

        } else if (countClick == 1) {

            tabpnl.remove(pnlBookmark);

            countClick = 0;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void miCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCPActionPerformed

        String UN = lblName.getText().trim();
        ChangePassword cp = new ChangePassword();
        cp.txtUN.setText(UN);
        cp.setVisible(true);

        cp.jButton1.addActionListener(new ActionListener() {
            String pass = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "Select password from Employee_Info where username='" + cp.txtUN.getText().trim() + "'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        pass = rs.getString("password");
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
                if (cp.txtUN.getText().equals("") && cp.txtPSD.getText().equals("") && cp.txtNPSD.getText().equals("") && cp.txtNPSDConfirm.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty field found!");
                } else if (!cp.txtPSD.getText().equals(pass)) {
                    JOptionPane.showMessageDialog(null, "Password not matched for due username!");
                } else if (!cp.txtNPSD.getText().equals(cp.txtNPSDConfirm.getText())) {
                    JOptionPane.showMessageDialog(null, "New Password and confirm password must be same!");
                } else if (cp.txtNPSD.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Password contains at least 8 character!");
                } else {
                    try {

                        String PSD = cp.txtNPSD.getText();

                        String sql = "update Employee_Info set Password='" + PSD + "' where username='" + cp.txtUN.getText().trim() + "'";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Operation successful.");
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
    }//GEN-LAST:event_miCPActionPerformed

    private void miBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBPActionPerformed
DefaultListModel listModelCat = new DefaultListModel();
        BookmarkForm bf = new BookmarkForm();
        bf.setVisible(true);
        
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            CategoryDAO catDAO = (CategoryDAO) context.getBean("CategoryDAO");
            Category category = new Category();
        category.listModelCat.clear();
        catDAO.getCategory(category);
//        BookmarkAndUnBookmarkProductInfo bubp = new BookmarkAndUnBookmarkProductInfo();
//        bubp.listModelCat.clear();
//        bubp.getCategory();
        bf.PCat.setModel(category.listModelCat);
        bf.PCat2.setModel(category.listModelCat);
        bf.lblEName.setText(lblName.getText());
context.close();
    }//GEN-LAST:event_miBPActionPerformed

    private void miLOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLOActionPerformed
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
    }//GEN-LAST:event_miLOActionPerformed

    private void btnSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsMouseEntered
        jpSettings.show(btnSettings, 0, 18);        // TODO add your handling code here:
    }//GEN-LAST:event_btnSettingsMouseEntered

    private void txtProductSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductSearchActionPerformed

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
jpSettings.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseEntered

    private void lblNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNameMouseEntered
       jpSettings.setVisible(false);        // TODO add your handling code here:
 // TODO add your handling code here:
    }//GEN-LAST:event_lblNameMouseEntered

    private void txtSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseEntered
jpSettings.setVisible(false);        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseEntered

    private void lstItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstItemMouseEntered
jpSettings.setVisible(false);        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_lstItemMouseEntered

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBookmark;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu jpSettings;
    private javax.swing.JLabel lblGenInfo;
    public javax.swing.JLabel lblName;
    private javax.swing.JList lstItem;
    private javax.swing.JMenuItem miBP;
    private javax.swing.JMenuItem miCP;
    private javax.swing.JMenuItem miLO;
    private javax.swing.JTabbedPane tabpnl;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtProductSearch;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
