/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.userview;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.msi.connection.Connection;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class SalesReport {
java.sql.Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
      String BarcodeText = "Barcode UPCA";
      
      ArrayList<String>Item=new ArrayList();
      ArrayList<Double>Amount=new ArrayList();
      ArrayList<String>Unit=new ArrayList();
       ArrayList<Double>PricePerUnit=new ArrayList();
        ArrayList<Double>TotalPrice=new ArrayList();
        double total=0;
    public SalesReport() {
        conn = Connection.DBconnector();
        
    }
    
    public void openReport(){
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ".\\Salesreport\\report.pdf");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    public void getValues(String id){
        try{
            String sql="Select PName,Amount,Unit,PPrice, TotalPrice from product,Customer where Product.PID=Customer.PID  and CID='"+id+"'";
            pst = conn.prepareStatement(sql);
            rs=pst.executeQuery();
       while(rs.next()){
                String name=rs.getString("PName");
                double amount=rs.getDouble("Amount");
                String unit=rs.getString("Unit");
                double price=rs.getDouble("PPrice");
                double TPrice=rs.getDouble("TotalPrice");
               Item.add(name);
               Amount.add(amount);
               Unit.add(unit);
               PricePerUnit.add(price);
               TotalPrice.add(TPrice);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
           try{
           pst.close();
           rs.close();
       }catch(Exception e){
               
               }
       }
    }
    public void getTotal(){
        for(int i=0;i<TotalPrice.size();i++){
            total=total+TotalPrice.get(i);
        }
    }
    public void createReport(String id){
        
        getValues(id);
        getTotal();
         Document d = new Document();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
        try {

            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(".\\Salesreport\\report.pdf"));
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
            Paragraph pa = new Paragraph("Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));

            Paragraph pDate = new Paragraph(new Date().toString()+", Sales ID:"+id);
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
            PdfPTable tableP = new PdfPTable(6);

//            PdfPCell cell16 = new PdfPCell(new Paragraph("Item, Amount, Unit, Price per unit and Total price", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
//               cell16.setColspan(7);
//            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell16.setBackgroundColor(BaseColor.GRAY);
//            cell16.setPaddingBottom(10);
//            tableP.addCell(cell16);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Item", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell18);
            
            PdfPCell cell19= new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell19);
            
            PdfPCell cell20 = new PdfPCell(new Paragraph("Price/unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell20);
            
            PdfPCell cell21= new PdfPCell(new Paragraph("Price", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell21);
            for (int i = 0; i < Item.size(); i++) {
                tableP.addCell((i + 1) + "");
                tableP.addCell(Item.get(i));
                tableP.addCell(Amount.get(i) + "");
                tableP.addCell(Unit.get(i));
                tableP.addCell(PricePerUnit.get(i) + "");
                tableP.addCell(TotalPrice.get(i)+"");
               
            }
            PdfPCell cell9 = new PdfPCell(new Paragraph("Total Price: "+ total+ " Taka.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell9.setColspan(7);
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setBackgroundColor(BaseColor.GRAY);
            cell9.setPaddingBottom(10);
            tableP.addCell(cell9);
           

            d.add(tableP);

            d.close();

        } catch (Exception e) {

        }
    }
    
}
