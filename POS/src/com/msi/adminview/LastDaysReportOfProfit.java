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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author MSI
 */
public class LastDaysReportOfProfit {

    String currency = "Taka";
    String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    ImageIcon format = null;

    void LastDaysProfitReport(String dateString,String FileName) {
      

        //DateChange date = new DateChange();
        LastDaysAllProductWithProfit l7bpp1 = new LastDaysAllProductWithProfit();
        l7bpp1.bestProducts(dateString);
        l7bpp1.L7BestSaleProduct.clear();
        l7bpp1.L7TBestSaleProductProfit.clear();
        LastDaysAllProductWithProfit l7bpp = new LastDaysAllProductWithProfit();
        l7bpp.bestProducts(dateString);
       LastDaysLossyProducts tlp=new LastDaysLossyProducts();
       tlp.getInfo(dateString);
        
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";

        Document d = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(FileName));
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
            Paragraph pa = new Paragraph("Last 30 days Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
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

            for (int i = 0; i < l7bpp.L7BestSaleProduct.size(); i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(l7bpp.L7BestSaleProduct.get(i));
                table5.addCell(l7bpp.L7TBestSaleProductProfit.get(i) + "");

            }

            d.add(table5);
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
            for (int i = 0; i < l7bpp.L7TBestSaleProductProfit.size(); i++) {
                TodayProfit = TodayProfit + l7bpp.L7TBestSaleProductProfit.get(i);
            }
            
            double TotalLoss = 0;
            for (int i = 0; i < tlp.LossArraList.size(); i++) {
                TotalLoss = TotalLoss - tlp.LossArraList.get(i);
            }

           
            String ProfitOrLossString;
            double ProfitOrLoss = (TodayProfit + TotalLoss);

            String Operator = ProfitOrLoss + "";

            if (Operator.startsWith("-")) {
                Paragraph pTP = new Paragraph("Total Loss: " + (-ProfitOrLoss) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));

                ProfitOrLossString = "------------------------Total Loss: " + (-ProfitOrLoss) + " " + currency;
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            } else {
                Paragraph pTP = new Paragraph("Total Profit: " + ProfitOrLoss + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
                ProfitOrLossString = "------------------------Total Profit was: " + (ProfitOrLoss) + " " + currency;
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            }

            d.close();

        } catch (Exception e) {

        }

    }
}
