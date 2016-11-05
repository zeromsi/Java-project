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
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class ReportBetweenTwoTime_Profit {

    String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    ImageIcon format = null;
    String currency = "Taka";
    
    public void pdfReport(String startYear, String endYear, String URL, String Days) {
        DateChange date = new DateChange();
        Sales sal = new Sales();
        LossyProductOfBetweenTwoDates tlp = new LossyProductOfBetweenTwoDates();
        tlp.getInfo(startYear, endYear);
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
        
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
            Paragraph pa = new Paragraph(Days, FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);
            
            d.add(pa);
            d.add(new Paragraph("\n"));
            
            Paragraph pDate = new Paragraph(new Date().toString());
            pDate.setAlignment(Element.ALIGN_CENTER);
            d.add(pDate);
            d.add(new Paragraph("\n"));
            d.add(new Paragraph(empty));
            
            d.add(new Paragraph("\n"));
            
            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
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
            sal.getValOfProductByProfitOfYear(startYear, endYear);
            for (int i = 0; i < 15; i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(sal.PName.get(i));
                table5.addCell(sal.Profit.get(i) + "");
                
            }
            
            d.add(table5);
       d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

   

            PdfPTable tablel = new PdfPTable(3);

            PdfPCell cell1l = new PdfPCell(new Paragraph("Product By Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell1l.setColspan(4);
            cell1l.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1l.setBackgroundColor(BaseColor.GRAY);
            cell1l.setPaddingBottom(10);
            tablel.addCell(cell1l);

            PdfPCell cellIDl = new PdfPCell(new Paragraph("Serial", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellIDl.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cellIDl);

            PdfPCell celll = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll);

            PdfPCell celll2 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll2);

         

            for (int i = 0; i < tlp.PName.size(); i++) {

                tablel.addCell(i+"");
                tablel.addCell(tlp.PName.get(i));
                tablel.addCell(-tlp.Profit.get(i) + " " + currency);
                
            }

            d.add(tablel);
            d.add(new Paragraph("\n"));
            
            double Profit = 0;
             double Loss = 0;
            for (int i = 0; i < sal.Profit.size(); i++) {
                Profit = Profit + sal.Profit.get(i);
            }
            
              for (int i = 0; i < tlp.Profit.size(); i++) {
                Loss = Loss - tlp.Profit.get(i);
            }
            
              double profitOrLoss=(Profit-Loss);
            
              String Str=profitOrLoss+"";
              //System.out.print(Profit+" "+Loss);
              if(Str.startsWith("-")){
                  Paragraph pTP = new Paragraph("Total Loss: " + (-profitOrLoss) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
            pTP.setAlignment(Element.ALIGN_CENTER);
            d.add(pTP);
              }else{
                       
            Paragraph pTP = new Paragraph("Total Profit: " + profitOrLoss + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
            pTP.setAlignment(Element.ALIGN_CENTER);
            d.add(pTP);
              }
            
            d.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }    
}
