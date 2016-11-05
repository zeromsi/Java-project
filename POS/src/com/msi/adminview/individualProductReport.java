/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

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
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * SELECT
 * PID,PName,CatName,PPrice,PAmount,Unit,PurchasePricePerUnit,TotalPurchasingPrice
 * from ProductCategory,Product where Product.CatID= ProductCategory.CatID and
 * PID=1 and PName='Pro1';
 *
 * @author MSI
 */
public class individualProductReport {


    String BarcodeText = "Barcode UPCA";

  public  void CreateReport(String URL,Product pro, Category cat) {
        //getValues(id, name);
  
      // System.out.print(Pid);
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
            Paragraph pa = new Paragraph("Product Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
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
            if(pro.getIm()!=null){
                 Image image2 = Image.getInstance(pro.getIm());
          //  image2.scaleAbsolute(550, 200);
            image2.setAlignment(Element.ALIGN_RIGHT);
            d.add(image2);
            }
            
            d.add(new Paragraph("\n"));
            PdfPTable tableP = new PdfPTable(6);

            PdfPCell cell = new PdfPCell(new Paragraph("General Information" + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD, BaseColor.WHITE)));
            cell.setColspan(6);
            cell.setPaddingBottom(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableP.addCell(cell);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("ID", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Category", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell18);

            PdfPCell cell19 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell19);

            PdfPCell cell20 = new PdfPCell(new Paragraph("Price", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell20);

            PdfPCell cell21 = new PdfPCell(new Paragraph("Purchase Price/unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell21);

            tableP.addCell(pro.getPID());
            tableP.addCell(pro.getPName());
            tableP.addCell(cat.getCatName());
            tableP.addCell(pro.getPAmount() + " "+pro.getUnit());
            tableP.addCell(pro.getPPrice() + " Take");
            tableP.addCell(pro.getPurchasePricePerUnit() + " Taka");
            d.add(tableP);
            
            
            
             d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            PdfPTable table = new PdfPTable(4);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Sales Information" + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD, BaseColor.WHITE)));
            cell2.setColspan(4);
            cell2.setPaddingBottom(15);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell2);

//            PdfPCell cellID4 = new PdfPCell(new Paragraph("ID", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
//            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
//            tableP.addCell(cellID4);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Total Sold Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Total Sold", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell5);

            String profitStr="";
            profitStr=pro.getProfit()+"";
            double profit=pro.getProfit();
            if(profitStr.startsWith("-")){
                PdfPCell cell16 = new PdfPCell(new Paragraph("Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell16);
            profit=-(pro.getProfit());
            }else if(profitStr.startsWith("")){
                PdfPCell cell16 = new PdfPCell(new Paragraph("Profit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell16);
            }
            
            

          
          
            table.addCell(pro.getPName());
            table.addCell(pro.getTotalSoldAmount()+" "+pro.getUnit());
            table.addCell(pro.getTotalSoldPrice() + " Taka");
            table.addCell(profit + " Taka");
     
            d.add(table);
            

            d.close();

        } catch (Exception e) {

        }

    }
}
