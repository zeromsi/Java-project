/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.implementation;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.msi.DAO.ProductDAO;
import com.msi.adminview.Category;
import com.msi.adminview.Product;
import com.msi.adminview.Supplier;
import com.msi.connection.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MSI
 */
public class ProductDAOImplementation implements ProductDAO {
  DefaultListModel listModelCat = new DefaultListModel();
    @Override
    public void add(Product pro, Category cat, Supplier sup) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();
        try {

            String sql = "select CatID from ProductCategory where CatName='" + cat.getCatName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                cat.setCatID(rs.getInt("catID"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {

                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

        try {

            String sql = "select SID from Supplier where SName='" + sup.getName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                sup.setId(rs.getInt("SID"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {

                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
        try {

            String Query = "Insert into Product (PName,CatID,PPrice,PAmount,Unit,purchasePricePerUnit,totalPurchasingPrice,SID,Image) values(?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(Query);
            pst.setString(1, pro.getPName());
            pst.setInt(2, cat.getCatID());
            pst.setDouble(3, pro.getPPrice());
            pst.setDouble(4, pro.getPAmount());
            pst.setString(5, pro.getUnit());
            pst.setDouble(6, pro.getPurchasePricePerUnit());
            pst.setDouble(7, pro.getPAmount() * pro.getPurchasePricePerUnit());
            pst.setInt(8, sup.getId());
            pst.setBytes(9, pro.getProduct_image());

            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something isn't ok!" + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (Exception e) {

            }
        }
    }

    public void Update_Table_By_Category(String proCat, JTable tbl) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();
        int CatID = 0;
        try {
            String sql = "SELECT CatID from ProductCategory where CatName='" + proCat + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString(1);

                CatID = Integer.parseInt(add1);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT PID as ID,PName as Name,PPrice as Price,PAmount as Amount,Unit,PurchasePricePerUnit as Purchase_Price from Product where CatID='" + CatID + "' ";//and addInfo=1
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public void update(Product pro, Category cat, Supplier sup) {
        java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();
        try {

            String sql = "select CatID from ProductCategory where CatName='" + cat.getCatName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                cat.setCatID(rs.getInt("catID"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {

                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }

        try {

            String sql = "select SID from Supplier where SName='" + sup.getName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                sup.setId(rs.getInt("SID"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {

                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
        try {

            String value1 = pro.getPID();
            String value2 = pro.getPName();
            double value3 = pro.getPPrice();
            double value4 =pro.getPAmount();
            String value5 = pro.getUnit();
            String value6 = sup.getName();
            double value7 = pro.getPurchasePricePerUnit();
            int SID=sup.getId();

            String Query = "update Product set PName='" + value2 + "',PPrice='" + value3 + "',PAmount='" + value4 + "',Unit='" + value5 + "',"
                        + "SID='" + SID + "',PurchasePricePerUnit='" + value7 + "',totalPurchasingPrice='" + value7 * value4 + "' where PID='" + value1 + "'";
                pst = conn.prepareStatement(Query);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Updated.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something isn't ok!" + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                }

            } catch (Exception e) {

            }
        }

    }

    @Override
    public void setProductInfoByClickingTable(String Table_click,Product pro, Category cat, Supplier sup) {
       java.sql.Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Connection.DBconnector();
        try{
           String sql = "select PID,SName,PName,PPrice,PAmount,Unit,PurchasePricePerUnit,totalPurchasingPrice,CatName,Image from Product,ProductCategory,Supplier where Product.CatID=ProductCategory.CatID and Product.SID=Supplier.SID and PID='" + Table_click + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
             if (rs.next()) {
                String add1 = rs.getString("PID");
                pro.setPID(add1);
                

                String add2 = rs.getString("PName");
                pro.setPName(add2);

                String add3 = rs.getString("CatName");
               cat.setCatName(add3);

                double add4 = rs.getDouble("PPrice");
               pro.setPPrice(add4);

                double add5 = rs.getDouble("PAmount");
               pro.setPAmount(add5);
               
                String add6 = rs.getString("Unit");
                pro.setUnit(add6);

                double add7 = rs.getDouble("PurchasePricePerUnit");
                pro.setPurchasePricePerUnit(add7);
                String add8=rs.getString("SName");
                sup.setName(add8);
                byte[] imagedata = rs.getBytes(10);
                pro.setProduct_image(imagedata);
             }
       }catch(Exception e){
           
       }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
     public void getValuesofIndividualProduct(Product pro, Category cat) {
          java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
        try {
            conn = Connection.DBconnector();
            String sql = "SELECT Product.PID,PName,CatName,PPrice,Image,PAmount,Unit,PurchasePricePerUnit,TotalPurchasingPrice, SUM(Amount),SUM(TotalPrice), SUM(Customer.profit) from ProductCategory,Product,Customer where Product.CatID=\n"
                    + "ProductCategory.CatID and Product.PID=Customer.PID and  Product.PID='" + pro.getPID() + "' and PName='" + pro.getPName() + "'";
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()) {

//                Pid = id;
//                Pname = name;
                cat.setCatName(rs.getString("CatName"));
                //catName = rs.getString("CatName");

                pro.setPPrice(rs.getDouble("PPrice"));
                //  price = rs.getDouble("PPrice");
                pro.setPAmount(rs.getDouble("PAmount"));
                // PAmount = rs.getDouble("PAmount");
                pro.setUnit(rs.getString("Unit"));
                // Unit = rs.getString("Unit");
                pro.setPurchasePricePerUnit(rs.getDouble("PurchasePricePerUnit"));
                //  PurchasePricePerUnit = rs.getDouble("PurchasePricePerUnit");
                pro.setTotalPurchasingPrice(rs.getDouble("TotalPurchasingPrice"));
                // TotalPurchasingPrice = rs.getDouble("TotalPurchasingPrice");
                pro.setTotalSoldAmount(rs.getDouble("SUM(Amount)"));
                //totalSoldAmount = rs.getDouble("SUM(Amount)");
                pro.setTotalSoldPrice(rs.getDouble("SUM(TotalPrice)"));
                //  TotalSoldPrice = rs.getDouble("SUM(TotalPrice)");
                pro.setProfit(rs.getDouble("SUM(Customer.profit)"));
               //profit = rs.getDouble("SUM(Customer.profit)");
               
                byte[] imagedata = rs.getBytes("Image");
                 pro.setProduct_image(imagedata);
                
                pro.setFormat(new ImageIcon(imagedata));  
                if (pro.getFormat().equals(null)) {
                    pro.setIm(null);
                } else {
                    
                    pro.setIm(Image.getInstance(imagedata)); 
                    pro.getIm().scaleAbsolute(200, 100);
                    //im.scaleAbsolute(200, 100);
                    pro.getIm().setAlignment(Element.ALIGN_RIGHT);
                }
                
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Something went wrong!");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }
                
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    public void bookmarkProduct(Product pro, String name) {
     java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
        int PID = 0;
        String EID = null;
         conn = Connection.DBconnector();
        try {
            String sql = "Select EID from Employee_info where Username='" + name + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                
                EID = rs.getString("EID");
                //PID = rs.getInt("PID");
               
            }
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
        
        
         try {
            String sql = "Select PID from Product where PName='" + pro.getPName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                
               // EID = rs.getString("EID");
                PID = rs.getInt("PID");
               
            }
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
       // System.out.print(EID+" "+PID);

        try {
            String sql = "Insert into bookmark(PID,EID) values(?,?)";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, PID);

            pst.setString(2, EID);
            pst.execute();
            // pn.btnBookmark.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Bookmarked.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
        }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }
                
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    public void fillUnBookmarkedTable(Category cat, JTable tblUnBookmark, String name) {
        ArrayList<String> PName=new ArrayList();
         ArrayList<String> UnBookmarkedPName=new ArrayList();
        PName.clear();
        UnBookmarkedPName.clear();
 java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     conn = Connection.DBconnector();
        try {
            String sql = "SELECT PName from Product,ProductCategory,Bookmark,Employee_Info where Product.CatID=ProductCategory.CatID \n"
                    + "                 and Product.PID=Bookmark.PID and Employee_Info.EID=Bookmark.EID and CatName='" + cat.getCatName() + "' and username='" + name + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PName.add(rs.getString("PName"));
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

        try {
            DefaultTableModel model = (DefaultTableModel) tblUnBookmark.getModel();
            model.setRowCount(0);
            String sql = "SELECT PName as Product_Name from Product,ProductCategory "
                    + "where Product.CatID=ProductCategory.CatID  and CatName='" + cat.getCatName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                UnBookmarkedPName.add(rs.getString("Product_Name"));
            }

            UnBookmarkedPName.removeAll(PName);
                  // System.out.print(UnBookmarkedPName);

            for (int i = 0; i < UnBookmarkedPName.size(); i++) {
                model.addRow(new Object[]{UnBookmarkedPName.get(i)});
            }
          //  tbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }
                
            } catch (Exception e) {
                
            }
        }
    }

    @Override
    public void UnBookmarkedProduct(Product pro, String name) {
       
 java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     conn = Connection.DBconnector();
     int PID = 0;
        String EID = null;

        try {
            String sql = "Select Bookmark.PID,Bookmark.EID from Bookmark,Product,Employee_Info where product.PID=bookmark.PID and Employee_Info.EID=bookmark.EID "
                    + "and username='" + name + "' and PName='" + pro.getPName() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                PID = rs.getInt("PID");
                EID = rs.getString("EID");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR." + e.getMessage());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        int p = JOptionPane.showConfirmDialog(null, "You wanna delete this!", "Delete", JOptionPane.YES_NO_CANCEL_OPTION);
        if (p == 0) {

            try {
                String sql = "delete from bookmark where EID='" + EID + "' and PID='" + PID + "'";
                pst = conn.prepareStatement(sql);

                pst.execute();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }
                
            } catch (Exception e) {
                
            }
        }

        }else{
             try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }
                
            } catch (Exception e) {
                
            }
        }
     
     
     
    }

    @Override
    public void fillBookmarkedTable(Category cat, JTable tblBookmark, String name) {
        java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     conn = Connection.DBconnector();
        try {
            String sql = "SELECT PName as Product_Name from Product,ProductCategory,Bookmark,Employee_Info where Product.CatID=ProductCategory.CatID "
                    + "and Product.PID=Bookmark.PID and Employee_Info.EID=Bookmark.EID and CatName='" + cat.getCatName() + "' and username='" + name + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tblBookmark.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                    rs.close();
                }
                
            } catch (Exception e) {
                
            }
        }
    }

    
    

}
