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

/**
 *
 * @author MSI
 */
public class LastDaysReportOfSoldTimes {
    String currency="Taka";
    String BarcodeText = "Barcode UPCA";
    public LastDaysReportOfSoldTimes() {
    }
    
    
    
    void LastDaysSoldTimesReport(String date,String FileName) {
       
       // DateChange dte=new DateChange();
        
        LastDaysAllProductWithSoldTimes l7bpst = new LastDaysAllProductWithSoldTimes();
        l7bpst.bestProducts(date);
// l7bpp.L7BestSaleProduct.clear();
// l7bpp.L7TBestSaleProductProfit.clear();

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

            for (int i = 0; i < l7bpst.L7BestSaleProduct.size(); i++) {
                table4.addCell((i + 1) + "");
                table4.addCell(l7bpst.L7BestSaleProduct.get(i));
                table4.addCell(l7bpst.L7TBestSaleProductSoldTimes.get(i) + "");

            }

            d.add(table4);

            d.add(new Paragraph("\n"));

            d.close();

        } catch (Exception e) {

        }

    }
}
